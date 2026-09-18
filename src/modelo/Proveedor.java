package modelo;

public class Proveedor implements Gestionable, Reportable {

    private String id;
    private String nombre;
    private String telefono;
    private String correo;

    public Proveedor(String id, String nombre, String telefono, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
    }

    @Override
    public void guardar() {
        System.out.println("Proveedor " + nombre + " guardado correctamente.");
    }

    @Override
    public void eliminar() {
        System.out.println("Proveedor " + nombre + " eliminado del sistema.");
    }

    @Override
    public void generarReporte() {
        System.out.println("Generando reporte del proveedor: " + nombre);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}