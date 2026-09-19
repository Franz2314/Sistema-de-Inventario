package modelo;

/** Una línea de la boleta: producto vendido, cantidad y precio al momento de la venta. */
public class DetalleBoleta {

    private final String codigo;
    private final String descripcion;
    private final int cantidad;
    private final double precio;

    public DetalleBoleta(String codigo, String descripcion, int cantidad, double precio) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public double getSubtotal() {
        return cantidad * precio;
    }
}
