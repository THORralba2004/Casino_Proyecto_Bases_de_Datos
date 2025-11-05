// TipoJuego.java
package co.edu.unbosque.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tiposJuego")
public class TipoJuego {
    @Id
    private String id;
    private String nombre;
    
    public TipoJuego() {}

    public TipoJuego(String nombre) {
        this.nombre = nombre;
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

    // Getters y setters...
    
}