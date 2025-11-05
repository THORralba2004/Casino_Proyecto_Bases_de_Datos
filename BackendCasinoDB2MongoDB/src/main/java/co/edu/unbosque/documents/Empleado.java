package co.edu.unbosque.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigDecimal;

@Document(collection = "empleados")
public class Empleado {
    @Id
    private String id;
    private String nombre;
    private String cargo;
    private BigDecimal salario;
    private String activo;
    
    public Empleado() {}

    public Empleado(String nombre, String cargo, BigDecimal salario, String activo) {
        this.nombre = nombre;
        this.cargo = cargo;
        this.salario = salario;
        this.activo = activo;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }
    public BigDecimal getSalario() { return salario; }
    public void setSalario(BigDecimal salario) { this.salario = salario; }
    public String getActivo() { return activo; }
    public void setActivo(String activo) { this.activo = activo; }
}