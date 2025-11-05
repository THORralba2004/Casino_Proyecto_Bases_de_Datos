// Torneo.java
package co.edu.unbosque.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigDecimal;
import java.time.LocalDate;

@Document(collection = "torneos")
public class Torneo {
    @Id
    private String id;
    private String nombre;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private BigDecimal buyIn;
    private BigDecimal premio;
    private String idEvento;
    
    public Torneo() {}

    public Torneo(String nombre, LocalDate fechaInicio, LocalDate fechaFin, BigDecimal buyIn, BigDecimal premio, String idEvento) {
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.buyIn = buyIn;
        this.premio = premio;
        this.idEvento = idEvento;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(String idEvento) {
		this.idEvento = idEvento;
	}

    // Getters y setters...
}