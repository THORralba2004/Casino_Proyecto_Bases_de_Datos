// Apuesta.java
package co.edu.unbosque.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document(collection = "apuestas")
public class Apuesta {
    @Id
    private String id;
    private String idCliente;
    private String idEvento;
    private String idEmpleado;
    private LocalDateTime fechaHora;
    private BigDecimal monto;
    private String resultado;
    private BigDecimal gananciaPerdida;
    private String estado;
    
    public Apuesta() {}

    public Apuesta(String idCliente, String idEvento, String idEmpleado, LocalDateTime fechaHora, 
                   BigDecimal monto, String resultado, BigDecimal gananciaPerdida, String estado) {
        this.idCliente = idCliente;
        this.idEvento = idEvento;
        this.idEmpleado = idEmpleado;
        this.fechaHora = fechaHora;
        this.monto = monto;
        this.resultado = resultado;
        this.gananciaPerdida = gananciaPerdida;
        this.estado = estado;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public String getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(String idEvento) {
		this.idEvento = idEvento;
	}

	public String getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(String idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public LocalDateTime getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(LocalDateTime fechaHora) {
		this.fechaHora = fechaHora;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public BigDecimal getGananciaPerdida() {
		return gananciaPerdida;
	}

	public void setGananciaPerdida(BigDecimal gananciaPerdida) {
		this.gananciaPerdida = gananciaPerdida;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

    	
    // Getters y setters...
}