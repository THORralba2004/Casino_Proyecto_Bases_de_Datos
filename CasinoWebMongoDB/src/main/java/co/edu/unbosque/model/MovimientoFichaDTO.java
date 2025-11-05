// MovimientoFichaDTO.java
package co.edu.unbosque.model;

import java.time.LocalDateTime;

public class MovimientoFichaDTO {

    private String idMov;
    private String idInventario;
    private String idEmpleado;
    private LocalDateTime fechaHora;
    private Integer cantidad;
    private String tipoMov; // INGRESO o EGRESO
    private String observacion;

    public MovimientoFichaDTO() {}

    public MovimientoFichaDTO(String idInventario, String idEmpleado, Integer cantidad, String tipoMov, String observacion) {
        this.idInventario = idInventario;
        this.idEmpleado = idEmpleado;
        this.cantidad = cantidad;
        this.tipoMov = tipoMov;
        this.observacion = observacion;
        this.fechaHora = LocalDateTime.now();
    }

    public MovimientoFichaDTO(String idMov, String idInventario, String idEmpleado, LocalDateTime fechaHora, 
                             Integer cantidad, String tipoMov, String observacion) {
        this.idMov = idMov;
        this.idInventario = idInventario;
        this.idEmpleado = idEmpleado;
        this.fechaHora = fechaHora;
        this.cantidad = cantidad;
        this.tipoMov = tipoMov;
        this.observacion = observacion;
    }

    // Getters y Setters
    public String getIdMov() {
        return idMov;
    }

    public void setIdMov(String idMov) {
        this.idMov = idMov;
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

    @Override
    public String toString() {
        return "MovimientoFichaDTO [idMov=" + idMov + ", idInventario=" + idInventario + ", idEmpleado=" + idEmpleado
                + ", fechaHora=" + fechaHora + ", cantidad=" + cantidad + ", tipoMov=" + tipoMov + ", observacion="
                + observacion + "]";
    }
}