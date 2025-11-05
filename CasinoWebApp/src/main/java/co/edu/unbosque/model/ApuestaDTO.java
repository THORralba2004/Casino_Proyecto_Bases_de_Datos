package co.edu.unbosque.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ApuestaDTO {

    private int idApuesta;
    private int idCliente;
    private int idEvento;
    private int idEmpleado;
    private LocalDateTime fechaHora;
    private double monto;
    private String resultado;
    private double gananciaPerdida;
    private String estado;

    public ApuestaDTO() {}

    public int getIdApuesta() {
        return idApuesta;
    }

    public void setIdApuesta(int idApuesta) {
        this.idApuesta = idApuesta;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
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
