package co.edu.unbosque.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "metodopago")
public class MetodoPago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMetodo")
    private Integer idMetodo;

    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;



    public MetodoPago() {}

    public MetodoPago(String nombre) {
        this.nombre = nombre;
    }

    // Getters y Setters
    public Integer getIdMetodo() { return idMetodo; }
    public void setIdMetodo(Integer idMetodo) { this.idMetodo = idMetodo; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
}