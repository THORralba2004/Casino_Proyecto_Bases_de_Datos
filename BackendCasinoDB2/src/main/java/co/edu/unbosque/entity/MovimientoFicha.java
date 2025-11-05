package co.edu.unbosque.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "movimientoficha")
public class MovimientoFicha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMov")
    private Integer idMov;

    @Column(name = "idInventario")
    private Integer idInventario;

    @Column(name = "idEmpleado")
    private Integer idEmpleado;

    @Column(name = "fechaHora")
    private LocalDateTime fechaHora = LocalDateTime.now();

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "tipoMov", length = 20)
    private String tipoMov;

    @Column(name = "observacion", length = 255)
    private String observacion;

    public MovimientoFicha() {}

    public MovimientoFicha(Integer idInventario, Integer idEmpleado, Integer cantidad, String tipoMov, String observacion) {
        this.idInventario = idInventario;
        this.idEmpleado = idEmpleado;
        this.cantidad = cantidad;
        this.tipoMov = tipoMov;
        this.observacion = observacion;
    }

    // Getters y Setters
    public Integer getIdMov() { return idMov; }
    public void setIdMov(Integer idMov) { this.idMov = idMov; }
    public Integer getIdInventario() { return idInventario; }
    public void setIdInventario(Integer idInventario) { this.idInventario = idInventario; }
    public Integer getIdEmpleado() { return idEmpleado; }
    public void setIdEmpleado(Integer idEmpleado) { this.idEmpleado = idEmpleado; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }
    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
    public String getTipoMov() { return tipoMov; }
    public void setTipoMov(String tipoMov) { this.tipoMov = tipoMov; }
    public String getObservacion() { return observacion; }
    public void setObservacion(String observacion) { this.observacion = observacion; }
}