package co.edu.unbosque.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DashboardDTO {

    private Integer totalClientes;
    private Integer totalEmpleados;
    private Integer totalApuestasHoy;
    private Integer totalMovimientosHoy;
    private Integer torneosActivos;
    private BigDecimal ingresosHoy;
    private BigDecimal egresosHoy;
    private Integer inventarioTotalFichas;
    private LocalDateTime ultimaActualizacion;

    public DashboardDTO() {}

    // Getters y Setters
    public Integer getTotalClientes() {
        return totalClientes;
    }

    public void setTotalClientes(Integer totalClientes) {
        this.totalClientes = totalClientes;
    }

    public Integer getTotalEmpleados() {
        return totalEmpleados;
    }

    public void setTotalEmpleados(Integer totalEmpleados) {
        this.totalEmpleados = totalEmpleados;
    }

    public Integer getTotalApuestasHoy() {
        return totalApuestasHoy;
    }

    public void setTotalApuestasHoy(Integer totalApuestasHoy) {
        this.totalApuestasHoy = totalApuestasHoy;
    }

    public Integer getTotalMovimientosHoy() {
        return totalMovimientosHoy;
    }

    public void setTotalMovimientosHoy(Integer totalMovimientosHoy) {
        this.totalMovimientosHoy = totalMovimientosHoy;
    }

    public Integer getTorneosActivos() {
        return torneosActivos;
    }

    public void setTorneosActivos(Integer torneosActivos) {
        this.torneosActivos = torneosActivos;
    }

    public BigDecimal getIngresosHoy() {
        return ingresosHoy;
    }

    public void setIngresosHoy(BigDecimal ingresosHoy) {
        this.ingresosHoy = ingresosHoy;
    }

    public BigDecimal getEgresosHoy() {
        return egresosHoy;
    }

    public void setEgresosHoy(BigDecimal egresosHoy) {
        this.egresosHoy = egresosHoy;
    }

    public Integer getInventarioTotalFichas() {
        return inventarioTotalFichas;
    }

    public void setInventarioTotalFichas(Integer inventarioTotalFichas) {
        this.inventarioTotalFichas = inventarioTotalFichas;
    }

    public LocalDateTime getUltimaActualizacion() {
        return ultimaActualizacion;
    }

    public void setUltimaActualizacion(LocalDateTime ultimaActualizacion) {
        this.ultimaActualizacion = ultimaActualizacion;
    }

    public BigDecimal getBalanceHoy() {
        if (ingresosHoy != null && egresosHoy != null) {
            return ingresosHoy.subtract(egresosHoy);
        }
        return BigDecimal.ZERO;
    }

    @Override
    public String toString() {
        return "DashboardDTO [totalClientes=" + totalClientes + ", totalEmpleados=" + totalEmpleados
                + ", totalApuestasHoy=" + totalApuestasHoy + ", totalMovimientosHoy=" + totalMovimientosHoy
                + ", torneosActivos=" + torneosActivos + ", ingresosHoy=" + ingresosHoy + ", egresosHoy=" + egresosHoy
                + ", inventarioTotalFichas=" + inventarioTotalFichas + ", ultimaActualizacion=" + ultimaActualizacion
                + "]";
    }
}