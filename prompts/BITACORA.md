# Bitacora de tecnicas avanzadas

Laboratorio 07: Tecnicas Avanzadas de Prompting. Herramienta de IA usada: (escribe aqui cual usaste)

##	Ejercicio	2:	Zero-shot, one-shot y few-shot
| Tipo | Aciertos (de 5) | Formato de la respuesta | Todas con el mismo formato (Si/No) |
|------|-----------------|-------------------------|------------------------------------|
| Zero-shot | 5 | Tabla con resumen y notas | No |
| One-shot | 5 | Lista con etiqueta y una explicacion extra | No |
| Few-shot | 5 | Solo 5 lineas: texto -> etiqueta | Si |
##	Ejercicio	3:	Chain of Thought
| Pedido | Respuesta de la IA | Muestra los pasos (Si/No) | Correcta (Si/No) |
|--------|--------------------|---------------------------|------------------|
| Directo | 318.60 | No | Si |
| Paso a paso | S/ 318,60 | Si | Si |
Ver el razonamiento permite comprobar cada calculo con la calculadora y saber en que paso esta el error si la IA se equivoca.
Con la respuesta directa solo se ve un numero, y aunque sea correcto no se sabe como se llego a el.
##	Ejercicio	4:	Role prompting
| Version | Vocabulario (sencillo/tecnico) | Usa ejemplos o codigo | A quien le sirve mas |
|---------|-------------------------------|-----------------------|----------------------|
| A. Sin rol | Intermedio, con algunos terminos tecnicos como tipo de dato y alcance | Ejemplo corto en Python y la comparacion de la caja con etiqueta | A cualquier lector que quiere una definicion general |
| B. Rol docente | Sencillo, sin terminos tecnicos al inicio | Comparacion de la caja de galletas y codigo Python muy basico | A estudiantes que nunca han programado |
| C. Rol senior | Tecnico, con tipado estatico, tipos primitivos, referencias, heap y final | Codigo en Java con int, double, String y boolean | A un companero desarrollador que ya programa |
##	Ejercicio	5:	Descomposicion
Pedido de una sola vez: la IA entrego una pagina web de inventario en un solo archivo HTML, con productos de ejemplo, busqueda, filtros, botones para agregar y quitar stock, editar, eliminar y un resumen. Eligio el lenguaje y el tipo de aplicacion sin preguntar, no mostro requisitos ni diseno de clases y uso el simbolo $ como moneda. Fue directo al resultado final y no se pudo revisar el razonamiento.

Paso 1: entrego los 5 requisitos principales del sistema de inventario.

Paso 2: entrego el diseno de clases con sus atributos y tipos de dato.

Paso 3: entrego el codigo Java de la clase Producto con atributos, constructor, getters y setters con validaciones, metodos de stock, equals, hashCode, toString y un main de prueba. Al compilar salio el error file not found porque el archivo no estaba en la carpeta C:\lab07-tecnicas. Se guardo Producto.java en esa carpeta y se compilo de nuevo.

Paso 4: propuso 3 mejoras para la clase Producto: validaciones en los setters, equals, hashCode y toString, y metodos para aumentar y disminuir stock.

Comparacion: el pedido de una sola vez dio una aplicacion completa pero con decisiones que yo no pedi, como el lenguaje y la moneda. Por pasos pude revisar primero los requisitos, luego el diseno y despues el codigo, corregir el rumbo en cada etapa y obtener una clase Producto que encaja con lo que necesitaba.
##	Ejercicio	6:	Prompt estructurado y autocritica
```text
<rol>Actua como analista de pruebas de software.</rol>

<contexto>Login web con correo y contrasena. La cuenta se bloquea despues de 3 intentos fallidos.</contexto>

<tarea>Piensa paso a paso que puede fallar y escribe 6 casos de prueba.</tarea>

<formato>Tabla con las columnas: ID, escenario, datos de entrada, resultado esperado.</formato>

Revisa tu tabla: faltan casos limite como campos vacios, correo sin @ o contrasena con espacios? Agrega los que falten e indica cuales agregaste.
```
| Que revisar | Cumple (Si / No) |
|-------------|------------------|
| Tiene las 4 columnas pedidas? | Si |
| Incluye el bloqueo despues de 3 intentos? | Si |
| Incluye casos con campos vacios? | Si |
| Indica que casos agrego en la autocritica? | Si |
| Hay algun caso repetido o que no tenga sentido? | No |
