package co.edu.unbosque.model;

public class TipoJuegoDTO {

    private int idTipoJuego;
    private String nombre;

    public TipoJuegoDTO() {}

    public TipoJuegoDTO(int idTipoJuego, String nombre) {
        this.idTipoJuego = idTipoJuego;
        this.nombre = nombre;
    }

    public int getIdTipoJuego() {
        return idTipoJuego;
    }

    public void setIdTipoJuego(int idTipoJuego) {
        this.idTipoJuego = idTipoJuego;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
