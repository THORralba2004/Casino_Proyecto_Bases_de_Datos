// TorneoDTO.java
package co.edu.unbosque.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TorneoDTO {

    private String idEvento;
    private String nombre;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private BigDecimal buyIn;
    private BigDecimal premio;

    public TorneoDTO() {}

    public TorneoDTO(String nombre, LocalDate fechaInicio, LocalDate fechaFin, BigDecimal buyIn, BigDecimal premio) {
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.buyIn = buyIn;
        this.premio = premio;
    }

    public TorneoDTO(String idEvento, String nombre, LocalDate fechaInicio, LocalDate fechaFin, BigDecimal buyIn, BigDecimal premio) {
        this.idEvento = idEvento;
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.buyIn = buyIn;
        this.premio = premio;
    }

    // Getters y Setters
    public String getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(String idEvento) {
        this.idEvento = idEvento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public BigDecimal getBuyIn() {
        return buyIn;
    }

    public void setBuyIn(BigDecimal buyIn) {
        this.buyIn = buyIn;
    }

    public BigDecimal getPremio() {
        return premio;
    }

    public void setPremio(BigDecimal premio) {
        this.premio = premio;
    }

    @Override
    public String toString() {
        return "TorneoDTO [idEvento=" + idEvento + ", nombre=" + nombre + ", fechaInicio=" + fechaInicio
                + ", fechaFin=" + fechaFin + ", buyIn=" + buyIn + ", premio=" + premio + "]";
    }
}