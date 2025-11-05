// MovimientoFicha.java
package co.edu.unbosque.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "movimientosFicha")
public class MovimientoFicha {
    @Id
    private String id;
    private String idInventario;
    private String idEmpleado;
    private LocalDateTime fechaHora;
    private Integer cantidad;
    private String tipoMov;
    private String observacion;
    
    public MovimientoFicha() {}

    public MovimientoFicha(String idInventario, String idEmpleado, LocalDateTime fechaHora, 
                          Integer cantidad, String tipoMov, String observacion) {
        this.idInventario = idInventario;
        this.idEmpleado = idEmpleado;
        this.fechaHora = fechaHora;
        this.cantidad = cantidad;
        this.tipoMov = tipoMov;
        this.observacion = observacion;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdInventario() {
		return idInventario;
	}

	public void setIdInventario(String idInventario) {
		this.idInventario = idInventario;
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

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public String getTipoMov() {
		return tipoMov;
	}

	public void setTipoMov(String tipoMov) {
		this.tipoMov = tipoMov;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

    // Getters y setters...
    
    
}