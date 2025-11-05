// Cliente.java
package co.edu.unbosque.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;

@Document(collection = "clientes")
public class Cliente {
    @Id
    private String id;
    private String nombre;
    private String documento;
    private LocalDate fechaRegistro;
    private String telefono;
    private String email;
    
    public Cliente() {}

    public Cliente(String nombre, String documento, LocalDate fechaRegistro, String telefono, String email) {
        this.nombre = nombre;
        this.documento = documento;
        this.fechaRegistro = fechaRegistro;
        this.telefono = telefono;
        this.email = email;
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

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public LocalDate getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(LocalDate fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

    // Getters y setters...
    
}