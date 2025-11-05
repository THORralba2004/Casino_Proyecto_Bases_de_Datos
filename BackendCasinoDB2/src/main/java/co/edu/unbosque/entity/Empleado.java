package co.edu.unbosque.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "empleado")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEmpleado")
    private Integer idEmpleado;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "cargo", length = 50)
    private String cargo;
    
    @Column(name = "salario")
    private double salario;

    @Column(name = "activo", length = 1)
    private String activo = "S";


    public Empleado() {}

    public Empleado(String nombre, String cargo, double salario, String activo) {
        this.nombre = nombre;
        this.cargo = cargo;
        this.salario = salario;
        this.activo = activo;
    }

    // Getters y Setters
    public Integer getIdEmpleado() { return idEmpleado; }
    public void setIdEmpleado(Integer idEmpleado) { this.idEmpleado = idEmpleado; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }
    public double getSalario() { return salario; }
    public void setSalario(double salario) { this.salario = salario; }
    public String getActivo() { return activo; }
    public void setActivo(String activo) { this.activo = activo; }
    
}
