package co.edu.unbosque.model;

import java.time.LocalDate;

public class EventoJuegoDTO {

    private int idEvento;
    private int idTipoJuego;
    private LocalDate fechaAlta;
    private String estado;

    public EventoJuegoDTO() {
    }

    public EventoJuegoDTO(int idEvento, int idTipoJuego, LocalDate fechaAlta, String estado) {
        this.idEvento = idEvento;
        this.idTipoJuego = idTipoJuego;
        this.fechaAlta = fechaAlta;
        this.estado = estado;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public int getIdTipoJuego() {
        return idTipoJuego;
    }

    public void setIdTipoJuego(int idTipoJuego) {
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
