package co.edu.unbosque.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "torneo")
public class Torneo {
    @Id
    @Column(name = "idEvento")
    private Integer idEvento;

    @Column(name = "nombre", length = 100)
    private String nombre;

    @Column(name = "fechaInicio")
    private LocalDate fechaInicio;

    @Column(name = "fechaFin")
    private LocalDate fechaFin;

    @Column(name = "buyIn", precision = 10, scale = 2)
    private BigDecimal buyIn;

    @Column(name = "premio", precision = 10, scale = 2)
    private BigDecimal premio;

    public Torneo() {}

    public Torneo(Integer idEvento, String nombre, LocalDate fechaInicio, LocalDate fechaFin, BigDecimal buyIn, BigDecimal premio) {
        this.idEvento = idEvento;
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.buyIn = buyIn;
        this.premio = premio;
    }

    // Getters y Setters
    public Integer getIdEvento() { return idEvento; }
    public void setIdEvento(Integer idEvento) { this.idEvento = idEvento; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }
    public LocalDate getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }
    public BigDecimal getBuyIn() { return buyIn; }
    public void setBuyIn(BigDecimal buyIn) { this.buyIn = buyIn; }
    public BigDecimal getPremio() { return premio; }
    public void setPremio(BigDecimal premio) { this.premio = premio; }
}