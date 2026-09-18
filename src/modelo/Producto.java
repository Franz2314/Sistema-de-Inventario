package modelo;

public class Producto {

    private int id;
    private String nombre;
    private double precio;
    private int stock;

    // Constructor vacío
    public Producto() {
    }

    // Constructor principal
    public Producto(int id, String nombre, double precio, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    // Constructor usado por ProductoDAO
    public Producto(String codigo, String nombre, int cantidad, double precio) {
        this.id = Integer.parseInt(codigo);
        this.nombre = nombre;
        this.stock = cantidad;
        this.precio = precio;
    }

    // GETTERS

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    // Estos dos métodos los usa tu ProductoDAO

    public int getCantidad() {
        return stock;
    }

    public String getCodigo() {
        return String.valueOf(id);
    }

    // SETTERS

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}