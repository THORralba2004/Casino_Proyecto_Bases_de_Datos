// TipoJuegoDTO.java
package co.edu.unbosque.model;

public class TipoJuegoDTO {

    private String idTipoJuego;
    private String nombre;

    public TipoJuegoDTO() {}

    public TipoJuegoDTO(String idTipoJuego, String nombre) {
        this.idTipoJuego = idTipoJuego;
        this.nombre = nombre;
    }

    public String getIdTipoJuego() {
        return idTipoJuego;
    }

    public void setIdTipoJuego(String idTipoJuego) {
        this.idTipoJuego = idTipoJuego;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}