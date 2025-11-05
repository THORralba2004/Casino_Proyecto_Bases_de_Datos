package co.edu.unbosque.dao;

import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.parser.ParseException;
import co.edu.unbosque.json.EventoJuegoJSON;
import co.edu.unbosque.model.EventoJuegoDTO;
import co.edu.unbosque.model.ICrud;

public class EventoJuegoDAO implements ICrud {

    private ArrayList<EventoJuegoDTO> listaEventos;

    @Override
    public String agregar(Object registro) {
        String mensaje = null;
        int respuesta = 0;
        try {
            respuesta = EventoJuegoJSON.postJSON((EventoJuegoDTO) registro);
            mensaje = String.valueOf(respuesta);
            System.out.println("EventoJuego agregado, respuesta: " + mensaje);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mensaje;
    }

    @Override
    public Object consultar() {
        try {
            listaEventos = EventoJuegoJSON.getJSON();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return listaEventos;
    }

    @Override
    public String actualizar(Object id, Object registro) {
        String mensaje = null;
        try {
            int respuesta = EventoJuegoJSON.updateJSON((int) id, (EventoJuegoDTO) registro);
            mensaje = String.valueOf(respuesta);
            System.out.println("EventoJuego actualizado, respuesta: " + mensaje);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mensaje;
    }

    @Override
    public String eliminar(Object id) {
        String mensaje = null;
        try {
            int respuesta = EventoJuegoJSON.deleteJSON((int) id);
            mensaje = String.valueOf(respuesta);
            System.out.println("EventoJuego eliminado, respuesta: " + mensaje);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mensaje;
    }

    @Override
    public Object listar() {
        return listaEventos;
    }
}
