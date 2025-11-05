package co.edu.unbosque.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaccion")
public class Transaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTransaccion")
    private Integer idTransaccion;

    @Column(name = "idEmpleado")
    private Integer idEmpleado;

    @Column(name = "idMetodo")
    private Integer idMetodo;

    @Column(name = "idCliente")
    private Integer idCliente;

    @Column(name = "fechaHora")
    private LocalDateTime fechaHora = LocalDateTime.now();

    @Column(name = "monto", precision = 10, scale = 2)
    private BigDecimal monto;

    @Column(name = "tipo", length = 20)
    private String tipo;

    @Column(name = "observacion", length = 255)
    private String observacion;

    public Transaccion() {}

    public Transaccion(Integer idEmpleado, Integer idMetodo, Integer idCliente, BigDecimal monto, String tipo, String observacion) {
        this.idEmpleado = idEmpleado;
        this.idMetodo = idMetodo;
        this.idCliente = idCliente;
        this.monto = monto;
        this.tipo = tipo;
        this.observacion = observacion;
    }

    // Getters y Setters
    public Integer getIdTransaccion() { return idTransaccion; }
    public void setIdTransaccion(Integer idTransaccion) { this.idTransaccion = idTransaccion; }
    public Integer getIdEmpleado() { return idEmpleado; }
    public void setIdEmpleado(Integer idEmpleado) { this.idEmpleado = idEmpleado; }
    public Integer getIdMetodo() { return idMetodo; }
    public void setIdMetodo(Integer idMetodo) { this.idMetodo = idMetodo; }
    public Integer getIdCliente() { return idCliente; }
    public void setIdCliente(Integer idCliente) { this.idCliente = idCliente; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }
    public BigDecimal getMonto() { return monto; }
    public void setMonto(BigDecimal monto) { this.monto = monto; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getObservacion() { return observacion; }
    public void setObservacion(String observacion) { this.observacion = observacion; }
}