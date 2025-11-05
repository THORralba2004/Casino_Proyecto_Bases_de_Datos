package co.edu.unbosque.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransaccionDTO {

    private Integer idTransaccion;
    private Integer idEmpleado;
    private Integer idMetodo;
    private Integer idCliente;
    private LocalDateTime fechaHora;
    private BigDecimal monto;
    private String tipo; // DEPOSITO, RETIRO, PAGO, etc.
    private String observacion;

    public TransaccionDTO() {}

    public TransaccionDTO(Integer idEmpleado, Integer idMetodo, Integer idCliente, BigDecimal monto, String tipo, String observacion) {
        this.idEmpleado = idEmpleado;
        this.idMetodo = idMetodo;
        this.idCliente = idCliente;
        this.monto = monto;
        this.tipo = tipo;
        this.observacion = observacion;
        this.fechaHora = LocalDateTime.now();
    }

    public TransaccionDTO(Integer idTransaccion, Integer idEmpleado, Integer idMetodo, Integer idCliente, 
                         LocalDateTime fechaHora, BigDecimal monto, String tipo, String observacion) {
        this.idTransaccion = idTransaccion;
        this.idEmpleado = idEmpleado;
        this.idMetodo = idMetodo;
        this.idCliente = idCliente;
        this.fechaHora = fechaHora;
        this.monto = monto;
        this.tipo = tipo;
        this.observacion = observacion;
    }

    // Getters y Setters
    public Integer getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(Integer idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Integer getIdMetodo() {
        return idMetodo;
    }

    public void setIdMetodo(Integer idMetodo) {
        this.idMetodo = idMetodo;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
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

    @Override
    public String toString() {
        return "TransaccionDTO [idTransaccion=" + idTransaccion + ", idEmpleado=" + idEmpleado + ", idMetodo=" + idMetodo
                + ", idCliente=" + idCliente + ", fechaHora=" + fechaHora + ", monto=" + monto + ", tipo=" + tipo
                + ", observacion=" + observacion + "]";
    }
}