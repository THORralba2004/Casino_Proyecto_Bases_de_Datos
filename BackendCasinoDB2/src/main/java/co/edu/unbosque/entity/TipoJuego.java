package co.edu.unbosque.entity;


import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tipojuego")
public class TipoJuego {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTipoJuego")
    private Integer idTipoJuego;

    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;



    public TipoJuego() {}

    public TipoJuego(String nombre) {
        this.nombre = nombre;
    }

    // Getters y Setters
    public Integer getIdTipoJuego() { return idTipoJuego; }
    public void setIdTipoJuego(Integer idTipoJuego) { this.idTipoJuego = idTipoJuego; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
}