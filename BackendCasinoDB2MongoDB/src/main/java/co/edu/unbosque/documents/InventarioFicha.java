// InventarioFicha.java
package co.edu.unbosque.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "inventariosFicha")
public class InventarioFicha {
    @Id
    private String id;
    private String nombreBoveda;
    private Integer saldo;
    
    public InventarioFicha() {}

    public InventarioFicha(String nombreBoveda, Integer saldo) {
        this.nombreBoveda = nombreBoveda;
        this.saldo = saldo;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombreBoveda() {
		return nombreBoveda;
	}

	public void setNombreBoveda(String nombreBoveda) {
		this.nombreBoveda = nombreBoveda;
	}

	public Integer getSaldo() {
		return saldo;
	}

	public void setSaldo(Integer saldo) {
		this.saldo = saldo;
	}

    // Getters y setters...
    
    
}