// Mesa.java
package co.edu.unbosque.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "mesas")
public class Mesa {
    @Id
    private String id;
    private Integer nroMesa;
    private Integer limiteJugadores;
    private String idEvento;
    
    public Mesa() {}

    public Mesa(Integer nroMesa, Integer limiteJugadores, String idEvento) {
        this.nroMesa = nroMesa;
        this.limiteJugadores = limiteJugadores;
        this.idEvento = idEvento;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getNroMesa() {
		return nroMesa;
	}

	public void setNroMesa(Integer nroMesa) {
		this.nroMesa = nroMesa;
	}

	public Integer getLimiteJugadores() {
		return limiteJugadores;
	}

	public void setLimiteJugadores(Integer limiteJugadores) {
		this.limiteJugadores = limiteJugadores;
	}

	public String getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(String idEvento) {
		this.idEvento = idEvento;
	}

    // Getters y setters...
    
}