package co.edu.unbosque.model;

public class InventarioFichaDTO {

    private Integer idInventario;
    private String nombreBoveda;
    private Integer saldo;

    public InventarioFichaDTO() {}

    public InventarioFichaDTO(String nombreBoveda, Integer saldo) {
        this.nombreBoveda = nombreBoveda;
        this.saldo = saldo;
    }

    public InventarioFichaDTO(Integer idInventario, String nombreBoveda, Integer saldo) {
        this.idInventario = idInventario;
        this.nombreBoveda = nombreBoveda;
        this.saldo = saldo;
    }

    // Getters y Setters
    public Integer getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(Integer idInventario) {
        this.idInventario = idInventario;
    }

    public String getNombreBoveda() {
        return nombreBoveda;
    }

    public void setNombreBoveda(String nombreBoveda) {
        this.nombreBoveda = nombreBoveda;
    }

    public Integer getSaldo() {
        return saldo;
    }

    public void setSaldo(Integer saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "InventarioFichaDTO [idInventario=" + idInventario + ", nombreBoveda=" + nombreBoveda + ", saldo=" + saldo + "]";
    }
}