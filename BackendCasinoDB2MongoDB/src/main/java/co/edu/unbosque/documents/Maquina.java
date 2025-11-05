// Maquina.java
package co.edu.unbosque.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigDecimal;

@Document(collection = "maquinas")
public class Maquina {
    @Id
    private String id;
    private String codigoSerie;
    private BigDecimal denominacion;
    private String idEvento;
    
    public Maquina() {}

    public Maquina(String codigoSerie, BigDecimal denominacion, String idEvento) {
        this.codigoSerie = codigoSerie;
        this.denominacion = denominacion;
        this.idEvento = idEvento;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCodigoSerie() {
		return codigoSerie;
	}

	public void setCodigoSerie(String codigoSerie) {
		this.codigoSerie = codigoSerie;
	}

	public BigDecimal getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(BigDecimal denominacion) {
		this.denominacion = denominacion;
	}

	public String getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(String idEvento) {
		this.idEvento = idEvento;
	}

    // Getters y setters...
    
    
}