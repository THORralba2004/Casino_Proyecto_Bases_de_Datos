package co.edu.unbosque.dao;

import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;

import co.edu.unbosque.json.ApuestaJSON;
import co.edu.unbosque.model.ApuestaDTO;
import co.edu.unbosque.model.ICrud;

public class ApuestaDAO implements ICrud {

    private ArrayList<ApuestaDTO> listaApuestas;

    @Override
    public String agregar(Object registro) {
        String mensaje = null;
        try {
            int respuesta = ApuestaJSON.postJSON((ApuestaDTO) registro);
            mensaje = String.valueOf(respuesta);
            System.out.println("Apuesta creada. Respuesta: " + mensaje);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mensaje;
    }

    @Override
    public Object consultar() {
        try {
            listaApuestas = ApuestaJSON.getJSON();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return listaApuestas;
    }

    @Override
    public String actualizar(Object id, Object registro) {
        String mensaje = null;
        try {
            int respuesta = ApuestaJSON.updateJSON((int) id, (ApuestaDTO) registro);
            mensaje = String.valueOf(respuesta);
            System.out.println("Apuesta actualizada. Respuesta: " + mensaje);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mensaje;
    }

    @Override
    public String eliminar(Object id) {
        String mensaje = null;
        try {
            int respuesta = ApuestaJSON.deleteJSON((int) id);
            mensaje = String.valueOf(respuesta);
            System.out.println("Apuesta eliminada. Respuesta: " + mensaje);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mensaje;
    }

    @Override
    public Object listar() {
        return listaApuestas;
    }
}
