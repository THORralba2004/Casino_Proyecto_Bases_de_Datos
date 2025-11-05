// EmpleadoDTO.java
package co.edu.unbosque.model;

public class EmpleadoDTO {

    private String idEmpleado;
    private String nombre;
    private String cargo;
    private double salario;
    private String activo;

    // ✅ Constructor vacío
    public EmpleadoDTO() {
    }

    // ✅ Constructor completo
    public EmpleadoDTO(String idEmpleado, String nombre, String cargo, double salario, String activo) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.cargo = cargo;
        this.salario = salario;
        this.activo = activo;
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "EmpleadoDTO [idEmpleado=" + idEmpleado + ", nombre=" + nombre + ", cargo=" + cargo
                + ", salario=" + salario + ", activo=" + activo + "]";
    }
}