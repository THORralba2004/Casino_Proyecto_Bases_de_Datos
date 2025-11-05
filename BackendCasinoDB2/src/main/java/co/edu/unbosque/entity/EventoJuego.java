package co.edu.unbosque.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "eventojuego")
public class EventoJuego {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEvento")
    private Integer idEvento;

    @Column(name = "idTipoJuego")
    private Integer idTipoJuego;

    @Column(name = "fechaAlta")
    private LocalDate fechaAlta;

    @Column(name = "estado", length = 20)
    private String estado;

    public EventoJuego() {}

    public EventoJuego(Integer idTipoJuego, LocalDate fechaAlta, String estado) {
        this.idTipoJuego = idTipoJuego;
        this.fechaAlta = fechaAlta;
        this.estado = estado;
    }

    // Getters y Setters
    public Integer getIdEvento() { return idEvento; }
    public void setIdEvento(Integer idEvento) { this.idEvento = idEvento; }
    public Integer getIdTipoJuego() { return idTipoJuego; }
    public void setIdTipoJuego(Integer idTipoJuego) { this.idTipoJuego = idTipoJuego; }
    public LocalDate getFechaAlta() { return fechaAlta; }
    public void setFechaAlta(LocalDate fechaAlta) { this.fechaAlta = fechaAlta; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

}