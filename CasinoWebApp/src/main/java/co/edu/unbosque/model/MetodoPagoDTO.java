package co.edu.unbosque.model;

public class MetodoPagoDTO {

    private int idMetodo;
    private String nombre;
    private String descripcion;
    private String estado; // ACTIVO / INACTIVO

    public MetodoPagoDTO() {}

    public MetodoPagoDTO(int idMetodo, String nombre, String descripcion, String estado) {
        this.idMetodo = idMetodo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    // Getters y Setters
    public int getIdMetodo() {
        return idMetodo;
    }

    public void setIdMetodo(int idMetodo) {
        this.idMetodo = idMetodo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "MetodoPagoDTO [idMetodo=" + idMetodo + ", nombre=" + nombre + 
               ", descripcion=" + descripcion + ", estado=" + estado + "]";
    }
}
