package co.edu.unbosque.dao;

import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;

import co.edu.unbosque.json.TipoJuegoJSON;
import co.edu.unbosque.model.ICrud;
import co.edu.unbosque.model.TipoJuegoDTO;

public class TipoJuegoDAO implements ICrud {

    private ArrayList<TipoJuegoDTO> listaTipoJuegos;

    @Override
    public String agregar(Object registro) {
        String mensaje = null;
        int respuesta = 0;
        try {
            respuesta = TipoJuegoJSON.postJSON((TipoJuegoDTO) registro);
            mensaje = String.valueOf(respuesta);
            System.out.println("Respuesta: " + mensaje);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mensaje;
    }
    
    public TipoJuegoDTO obtenerPorId(String id) {
        TipoJuegoDTO tipo = null;
        try {
            tipo = TipoJuegoJSON.getByIdJSON(id);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return tipo;
    }


    @Override
    public Object consultar() {
        try {
            listaTipoJuegos = TipoJuegoJSON.getJSON();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return listaTipoJuegos;
    }

    @Override
    public String actualizar(Object id, Object registro) {
        String mensaje = null;
        try {
            int respuesta = TipoJuegoJSON.updateJSON((String) id, (TipoJuegoDTO) registro);
            mensaje = String.valueOf(respuesta);
            System.out.println("TipoJuego actualizado, respuesta: " + mensaje);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mensaje;
    }


    @Override
    public String eliminar(Object id) {
        String mensaje = null;
        try {
            int respuesta = TipoJuegoJSON.deleteJSON((String) id);
            mensaje = String.valueOf(respuesta);
            System.out.println("TipoJuego eliminado, respuesta: " + mensaje);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mensaje;
    }

    @Override
    public Object listar() {
        return listaTipoJuegos;
    }
}
