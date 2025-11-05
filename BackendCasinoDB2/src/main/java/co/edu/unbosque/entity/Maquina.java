package co.edu.unbosque.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "maquina")
public class Maquina {
    @Id
    @Column(name = "idEvento")
    private Integer idEvento;

    @Column(name = "codigoSerie", length = 50)
    private String codigoSerie;

    @Column(name = "denominacion", precision = 10, scale = 2)
    private BigDecimal denominacion;

    public Maquina() {}

    public Maquina(Integer idEvento, String codigoSerie, BigDecimal denominacion) {
        this.idEvento = idEvento;
        this.codigoSerie = codigoSerie;
        this.denominacion = denominacion;
    }

    // Getters y Setters
    public Integer getIdEvento() { return idEvento; }
    public void setIdEvento(Integer idEvento) { this.idEvento = idEvento; }
    public String getCodigoSerie() { return codigoSerie; }
    public void setCodigoSerie(String codigoSerie) { this.codigoSerie = codigoSerie; }
    public BigDecimal getDenominacion() { return denominacion; }
    public void setDenominacion(BigDecimal denominacion) { this.denominacion = denominacion; }
}