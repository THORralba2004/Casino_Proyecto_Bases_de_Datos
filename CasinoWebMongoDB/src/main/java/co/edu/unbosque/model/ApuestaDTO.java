// ApuestaDTO.java
package co.edu.unbosque.model;

import java.time.LocalDateTime;

public class ApuestaDTO {

    private String idApuesta;
    private String idCliente;
    private String idEvento;
    private String idEmpleado;
    private LocalDateTime fechaHora;
    private double monto;
    private String resultado;
    private double gananciaPerdida;
    private String estado;

    public ApuestaDTO() {}

    public String getIdApuesta() {
        return idApuesta;
    }

    public void setIdApuesta(String idApuesta) {
        this.idApuesta = idApuesta;
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

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public double getGananciaPerdida() {
        return gananciaPerdida;
    }

    public void setGananciaPerdida(double gananciaPerdida) {
        this.gananciaPerdida = gananciaPerdida;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}