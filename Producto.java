import java.math.BigDecimal;
import java.util.Objects;

public class Producto {

    private int id;
    private String codigo;          // SKU
    private String nombre;
    private String descripcion;
    private String categoria;
    private String proveedor;
    private BigDecimal precioCompra;
    private BigDecimal precioVenta;
    private int stockActual;
    private int stockMinimo;
    private boolean activo;

    public Producto(int id, String codigo, String nombre, String descripcion,
                    String categoria, String proveedor,
                    BigDecimal precioCompra, BigDecimal precioVenta,
                    int stockActual, int stockMinimo, boolean activo) {
        this.id = id;
        setCodigo(codigo);
        setNombre(nombre);
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.proveedor = proveedor;
        setPrecioCompra(precioCompra);
        setPrecioVenta(precioVenta);
        setStockActual(stockActual);
        setStockMinimo(stockMinimo);
        this.activo = activo;
    }

    // ---------- Getters y setters (con validaciones) ----------

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("El codigo es obligatorio");
        }
        this.codigo = codigo.trim();
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        this.nombre = nombre.trim();
    }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getProveedor() { return proveedor; }
    public void setProveedor(String proveedor) { this.proveedor = proveedor; }

    public BigDecimal getPrecioCompra() { return precioCompra; }
    public void setPrecioCompra(BigDecimal precioCompra) {
        if (precioCompra == null || precioCompra.signum() < 0) {
            throw new IllegalArgumentException("El precio de compra no puede ser negativo");
        }
        this.precioCompra = precioCompra;
    }

    public BigDecimal getPrecioVenta() { return precioVenta; }
    public void setPrecioVenta(BigDecimal precioVenta) {
        if (precioVenta == null || precioVenta.signum() < 0) {
            throw new IllegalArgumentException("El precio de venta no puede ser negativo");
        }
        this.precioVenta = precioVenta;
    }

    public int getStockActual() { return stockActual; }
    public void setStockActual(int stockActual) {
        if (stockActual < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }
        this.stockActual = stockActual;
    }

    public int getStockMinimo() { return stockMinimo; }
    public void setStockMinimo(int stockMinimo) {
        if (stockMinimo < 0) {
            throw new IllegalArgumentException("El stock minimo no puede ser negativo");
        }
        this.stockMinimo = stockMinimo;
    }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    // ---------- Logica de negocio ----------

    public void aumentarStock(int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser positiva");
        }
        this.stockActual += cantidad;
    }

    public void disminuirStock(int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser positiva");
        }
        if (cantidad > stockActual) {
            throw new IllegalStateException("Stock insuficiente para " + nombre);
        }
        this.stockActual -= cantidad;
    }

    public boolean requiereReposicion() {
        return stockActual <= stockMinimo;
    }

    public BigDecimal getValorEnStock() {
        return precioCompra.multiply(BigDecimal.valueOf(stockActual));
    }

    // ---------- equals, hashCode, toString ----------

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Producto)) return false;
        Producto otro = (Producto) o;
        return Objects.equals(codigo, otro.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    @Override
    public String toString() {
        return "Producto{codigo='" + codigo + "', nombre='" + nombre +
               "', stock=" + stockActual + "}";
    }

    // ---------- Prueba rapida (puedes borrar este metodo) ----------

    public static void main(String[] args) {
        Producto agua = new Producto(1, "AGU-001", "Agua mineral 500ml", "Botella de 500 ml",
                "Bebidas", "Distribuidora Sur",
                new BigDecimal("0.80"), new BigDecimal("1.50"), 20, 5, true);

        System.out.println(agua);
        agua.disminuirStock(16);
        System.out.println("Despues de vender 16: " + agua);
        System.out.println("Requiere reposicion? " + agua.requiereReposicion());
    }
}
