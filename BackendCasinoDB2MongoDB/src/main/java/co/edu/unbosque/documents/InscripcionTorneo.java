// InscripcionTorneo.java
package co.edu.unbosque.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigDecimal;
import java.time.LocalDate;

@Document(collection = "inscripcionesTorneo")
public class InscripcionTorneo {
    @Id
    private String id;
    private String idEvento;
    private String idCliente;
    private LocalDate fechaInscripcion;
    private BigDecimal feePagado;
    
    public InscripcionTorneo() {}

    public InscripcionTorneo(String idEvento, String idCliente, LocalDate fechaInscripcion, BigDecimal feePagado) {
        this.idEvento = idEvento;
        this.idCliente = idCliente;
        this.fechaInscripcion = fechaInscripcion;
        this.feePagado = feePagado;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(String idEvento) {
		this.idEvento = idEvento;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public LocalDate getFechaInscripcion() {
		return fechaInscripcion;
	}

	public void setFechaInscripcion(LocalDate fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}

	public BigDecimal getFeePagado() {
		return feePagado;
	}

	public void setFeePagado(BigDecimal feePagado) {
		this.feePagado = feePagado;
	}

    // Getters y setters...
    
    
}