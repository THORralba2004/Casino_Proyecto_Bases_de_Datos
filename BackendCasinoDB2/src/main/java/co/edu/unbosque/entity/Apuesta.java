package co.edu.unbosque.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "apuesta")
public class Apuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idApuesta")
    private Integer idApuesta;

    @Column(name = "idCliente")
    private Integer idCliente;

    @Column(name = "idEvento")
    private Integer idEvento;

    @Column(name = "idEmpleado")
    private Integer idEmpleado;

    @Column(name = "fechaHora")
    private LocalDateTime fechaHora = LocalDateTime.now();

    @Column(name = "monto", precision = 10, scale = 2)
    private BigDecimal monto;

    @Column(name = "resultado", length = 20)
    private String resultado;

    @Column(name = "gananciaPerdida", precision = 10, scale = 2)
    private BigDecimal gananciaPerdida;

    @Column(name = "estado", length = 20)
    private String estado;

    public Apuesta() {}

    public Apuesta(Integer idCliente, Integer idEvento, Integer idEmpleado, BigDecimal monto, String resultado, BigDecimal gananciaPerdida, String estado) {
        this.idCliente = idCliente;
        this.idEvento = idEvento;
        this.idEmpleado = idEmpleado;
        this.monto = monto;
        this.resultado = resultado;
        this.gananciaPerdida = gananciaPerdida;
        this.estado = estado;
    }

    // Getters y Setters
    public Integer getIdApuesta() { return idApuesta; }
    public void setIdApuesta(Integer idApuesta) { this.idApuesta = idApuesta; }
    public Integer getIdCliente() { return idCliente; }
    public void setIdCliente(Integer idCliente) { this.idCliente = idCliente; }
    public Integer getIdEvento() { return idEvento; }
    public void setIdEvento(Integer idEvento) { this.idEvento = idEvento; }
    public Integer getIdEmpleado() { return idEmpleado; }
    public void setIdEmpleado(Integer idEmpleado) { this.idEmpleado = idEmpleado; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }
    public BigDecimal getMonto() { return monto; }
    public void setMonto(BigDecimal monto) { this.monto = monto; }
    public String getResultado() { return resultado; }
    public void setResultado(String resultado) { this.resultado = resultado; }
    public BigDecimal getGananciaPerdida() { return gananciaPerdida; }
    public void setGananciaPerdida(BigDecimal gananciaPerdida) { this.gananciaPerdida = gananciaPerdida; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}