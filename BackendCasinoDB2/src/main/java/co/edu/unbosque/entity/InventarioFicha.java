package co.edu.unbosque.entity;


import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "inventarioficha")
public class InventarioFicha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idInventario")
    private Integer idInventario;

    @Column(name = "nombreBoveda", length = 50)
    private String nombreBoveda;

    @Column(name = "saldo")
    private Integer saldo;



    public InventarioFicha() {}

    public InventarioFicha(String nombreBoveda, Integer saldo) {
        this.nombreBoveda = nombreBoveda;
        this.saldo = saldo;
    }

    // Getters y Setters
    public Integer getIdInventario() { return idInventario; }
    public void setIdInventario(Integer idInventario) { this.idInventario = idInventario; }
    public String getNombreBoveda() { return nombreBoveda; }
    public void setNombreBoveda(String nombreBoveda) { this.nombreBoveda = nombreBoveda; }
    public Integer getSaldo() { return saldo; }
    public void setSaldo(Integer saldo) { this.saldo = saldo; }
    
}