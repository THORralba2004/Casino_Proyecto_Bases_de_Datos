// Transaccion.java
package co.edu.unbosque.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document(collection = "transacciones")
public class Transaccion {
    @Id
    private String id;
    private String idEmpleado;
    private String idMetodo;
    private String idCliente;
    private LocalDateTime fechaHora;
    private BigDecimal monto;
    private String tipo;
    private String observacion;
    
    public Transaccion() {}

    public Transaccion(String idEmpleado, String idMetodo, String idCliente, LocalDateTime fechaHora, 
                      BigDecimal monto, String tipo, String observacion) {
        this.idEmpleado = idEmpleado;
        this.idMetodo = idMetodo;
        this.idCliente = idCliente;
        this.fechaHora = fechaHora;
        this.monto = monto;
        this.tipo = tipo;
        this.observacion = observacion;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(String idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getIdMetodo() {
		return idMetodo;
	}

	public void setIdMetodo(String idMetodo) {
		this.idMetodo = idMetodo;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

    // Getters y setters...
    
    
}