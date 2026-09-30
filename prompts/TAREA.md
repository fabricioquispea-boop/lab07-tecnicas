# Tarea: Mi prompt avanzado

## Tarea elegida

Generar **casos de prueba** para un formulario de registro de usuarios con los campos nombre, correo y contraseña.

**Reglas del formulario:**

- La contraseña debe tener mínimo 8 caracteres, una mayúscula y un número.
- El correo no puede estar repetido.

Es una tarea útil en desarrollo de software porque una buena lista de casos de prueba permite encontrar errores antes de publicar el sistema.

---

## Versión 1: prompt básico

```text
Dame casos de prueba para un registro de usuarios.
```

- **Técnica agregada:** ninguna, es el punto de partida.
- **Por qué:** sirve como base para comparar las otras versiones.
- **Qué pasó en la respuesta:** la IA entregó 47 casos divididos en 9 secciones, como flujo exitoso, seguridad, usabilidad y compatibilidad. Supuso campos que no existen en mi formulario, como confirmación de contraseña, términos y condiciones, teléfono y fecha de nacimiento. La tabla tenía solo 3 columnas y no incluía datos de entrada concretos. Fue una respuesta muy larga y general, difícil de usar para probar mi formulario.

---

## Versión 2: rol, contexto y prompt estructurado

```text
<rol>Actua como analista de pruebas de software con experiencia en aplicaciones web.</rol>

<contexto>Formulario de registro web con los campos: nombre, correo y contrasena.
La contrasena debe tener minimo 8 caracteres, una mayuscula y un numero.
El correo no puede estar repetido.</contexto>

<tarea>Escribe 8 casos de prueba para el formulario.</tarea>

<formato>Tabla con las columnas: ID, escenario, datos de entrada, resultado esperado.</formato>
```

- **Técnicas agregadas:** role prompting y prompt estructurado con etiquetas.
- **Por qué:** el rol da un punto de vista técnico, el contexto le dice qué reglas tiene mi formulario y el formato le indica cómo presentar la respuesta.
- **Qué mejoró:** la IA entregó exactamente 8 casos, en una tabla con las 4 columnas pedidas y con datos de entrada concretos. Cubrió las tres reglas de la contraseña, el correo repetido, el formato de correo, los campos vacíos y el límite de 8 caracteres. Ya no inventó campos que no existen.
- **Qué todavía faltaba:** no mostró su razonamiento, no revisó su propia tabla, no incluyó casos con espacios ni correo sin @ (solo uno sin dominio) y usó IDs como CP-01 porque yo no indiqué un formato. Solo sugirió casos adicionales en una nota al final, sin escribirlos.

---

## Versión 3: prompt final

```text
<rol>Actua como analista de pruebas de software con experiencia en aplicaciones web,
que explica sus casos a un equipo de desarrollo.</rol>

<contexto>Formulario de registro web con los campos: nombre, correo y contrasena.
La contrasena debe tener minimo 8 caracteres, una mayuscula y un numero.
El correo no puede estar repetido.</contexto>

<tarea>
1. Piensa paso a paso que puede fallar en cada campo antes de escribir los casos.
2. Escribe 8 casos de prueba distintos entre si.
3. Revisa tu tabla y agrega los casos limite que falten (campos vacios, correo sin @,
   contrasena de exactamente 8 caracteres, espacios). Indica cuales agregaste.
</tarea>

<ejemplo>
RG-01 | Contrasena sin mayuscula | Nombre: Ana, Correo: ana@mail.com, Contrasena: clave1234 | El sistema rechaza el registro y muestra un mensaje de error
</ejemplo>

<formato>Tabla con las columnas: ID, escenario, datos de entrada, resultado esperado.
Usa IDs con el formato RG-01, RG-02, etc. Responde en espanol.</formato>
```

- **Técnicas agregadas:** chain of thought (pensar paso a paso qué puede fallar), few-shot (una fila de ejemplo), descomposición en pasos numerados y autocrítica (revisar la tabla y agregar casos límite). Además precisé el rol para que explique los casos a un equipo de desarrollo.
- **Por qué:** el ejemplo fija el formato de los IDs, el análisis previo evita casos superficiales y la autocrítica cubre los casos límite que en la versión 2 quedaron solo como sugerencia.
- **Qué mejoró:** primero hizo un análisis de lo que puede fallar en nombre, correo y contraseña. Luego entregó los 8 casos con IDs RG-01 a RG-08 y después agregó 9 casos límite (RG-09 a RG-17), marcados en una columna "Agregado". Incluyó la contraseña de exactamente 8 caracteres, cada campo vacío por separado, correo sin @, espacios y correo duplicado con otra capitalización. Terminó con notas para el equipo de desarrollo, como la ambigüedad de las contraseñas con espacios.
- **Qué no quedó perfecto:** RG-08 y RG-13 repiten la prueba de correo sin @, y RG-15 no tiene un resultado esperado definido porque depende de una regla que el formulario no aclara. Además la tabla final tiene 17 casos y no 8, porque la autocrítica agregó 9.

---

## Técnicas usadas en el prompt final

| Parte del prompt final | Técnica |
| --- | --- |
| Actúa como analista de pruebas de software con experiencia en aplicaciones web, que explica sus casos a un equipo de desarrollo | **Role prompting** |
| Etiquetas rol, contexto, tarea, ejemplo y formato | **Prompt estructurado** |
| Piensa paso a paso qué puede fallar en cada campo | **Chain of thought** |
| Fila de ejemplo RG-01 | **Few-shot** |
| Pasos 1, 2 y 3 de la tarea | **Descomposición** |
| Revisa tu tabla y agrega los casos límite que falten | **Autocrítica** |

---

## Evaluación del resultado

| Criterio | Cumple (Sí / No) |
| --- | --- |
| La tabla tiene las 4 columnas pedidas | Sí |
| Todas las filas usan el formato de ID RG-01, RG-02... | Sí |
| Muestra el análisis de lo que puede fallar antes de la tabla | Sí |
| Incluye casos límite: campos vacíos, correo sin @, 8 caracteres y espacios | Sí |
| Indica qué casos agregó en la autocrítica | Sí |
| Todos los casos son distintos entre sí | No |
| Todos los resultados esperados están definidos | No |

> Los dos **No** son problemas que encontré al revisar la respuesta: RG-08 y RG-13 repiten la prueba del correo sin @, y RG-15 deja el resultado por confirmar. Esto confirma que la IA puede equivocarse incluso cuando se revisa a sí misma, y que la revisión final es mía.

---

## Por qué elegí estas técnicas

Elegí **role prompting** y **prompt estructurado** porque el prompt básico dio una respuesta larga y general con campos que mi formulario no tiene; el rol y el contexto la centraron en mi caso. Usé **few-shot** porque en la versión 2 la IA eligió sus propios IDs (CP-01), y con un ejemplo logré que todos usaran el formato RG-01 y una tabla uniforme. Agregué **chain of thought** y **descomposición** para que primero analizara qué puede fallar y después escribiera los casos, y **autocrítica** porque la versión 2 solo sugirió casos adicionales sin escribirlos. No usé varios chats porque la tarea cabe en un solo mensaje bien estructurado, y no me quedé con el prompt básico porque entregaba una lista que no servía para probar mi formulario.