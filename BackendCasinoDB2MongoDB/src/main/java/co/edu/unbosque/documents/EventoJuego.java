package co.edu.unbosque.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;

@Document(collection = "eventosJuego")
public class EventoJuego {
    @Id
    private String id;
    private String idTipoJuego;
    private LocalDate fechaAlta;
    private String estado;
    
    public EventoJuego() {}

    public EventoJuego(String idTipoJuego, LocalDate fechaAlta, String estado) {
        this.idTipoJuego = idTipoJuego;
        this.fechaAlta = fechaAlta;
        this.estado = estado;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTipoJuegoId() { return idTipoJuego; }
    public void setTipoJuegoId(String idTipoJuego) { this.idTipoJuego = idTipoJuego; }
    public LocalDate getFechaAlta() { return fechaAlta; }
    public void setFechaAlta(LocalDate fechaAlta) { this.fechaAlta = fechaAlta; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}