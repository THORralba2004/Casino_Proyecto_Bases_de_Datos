package co.edu.unbosque.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "mesa")
public class Mesa {
    @Id
    @Column(name = "idEvento")
    private Integer idEvento;

    @Column(name = "nroMesa")
    private Integer nroMesa;

    @Column(name = "limiteJugadores")
    private Integer limiteJugadores;

    public Mesa() {}

    public Mesa(Integer idEvento, Integer nroMesa, Integer limiteJugadores) {
        this.idEvento = idEvento;
        this.nroMesa = nroMesa;
        this.limiteJugadores = limiteJugadores;
    }

    // Getters y Setters
    public Integer getIdEvento() { return idEvento; }
    public void setIdEvento(Integer idEvento) { this.idEvento = idEvento; }
    public Integer getNroMesa() { return nroMesa; }
    public void setNroMesa(Integer nroMesa) { this.nroMesa = nroMesa; }
    public Integer getLimiteJugadores() { return limiteJugadores; }
    public void setLimiteJugadores(Integer limiteJugadores) { this.limiteJugadores = limiteJugadores; }
}