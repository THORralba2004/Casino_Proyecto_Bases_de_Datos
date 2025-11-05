// EventoJuegoDTO.java
package co.edu.unbosque.model;

import java.time.LocalDate;

public class EventoJuegoDTO {

    private String idEvento;
    private String idTipoJuego;
    private LocalDate fechaAlta;
    private String estado;

    public EventoJuegoDTO() {
    }

    public EventoJuegoDTO(String idEvento, String idTipoJuego, LocalDate fechaAlta, String estado) {
        this.idEvento = idEvento;
        this.idTipoJuego = idTipoJuego;
        this.fechaAlta = fechaAlta;
        this.estado = estado;
    }

    public String getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(String idEvento) {
        this.idEvento = idEvento;
    }

    public String getIdTipoJuego() {
        return idTipoJuego;
    }

    public void setIdTipoJuego(String idTipoJuego) {
        this.idTipoJuego = idTipoJuego;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}