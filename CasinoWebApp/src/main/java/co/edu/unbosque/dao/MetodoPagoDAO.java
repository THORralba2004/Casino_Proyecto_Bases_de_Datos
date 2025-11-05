package co.edu.unbosque.dao;

import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;

import co.edu.unbosque.json.MetodoPagoJSON;
import co.edu.unbosque.model.MetodoPagoDTO;
import co.edu.unbosque.model.ICrud;

public class MetodoPagoDAO implements ICrud {

    private ArrayList<MetodoPagoDTO> listaMetodos;

    @Override
    public String agregar(Object registro) {
        try {
            MetodoPagoDTO metodo = (MetodoPagoDTO) registro;
            int respuesta = MetodoPagoJSON.postJSON(metodo);
            return String.valueOf(respuesta);
        } catch (IOException e) {
            e.printStackTrace();
            return "500";
        }
    }

    @Override
    public Object consultar() {
        try {
            listaMetodos = MetodoPagoJSON.getJSON();
            return listaMetodos;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return new ArrayList<MetodoPagoDTO>();
        }
    }

    @Override
    public String actualizar(Object id, Object registro) {
        try {
            int respuesta = MetodoPagoJSON.updateJSON((int) id, (MetodoPagoDTO) registro);
            return String.valueOf(respuesta);
        } catch (IOException e) {
            e.printStackTrace();
            return "500";
        }
    }

    @Override
    public String eliminar(Object id) {
        try {
            int respuesta = MetodoPagoJSON.deleteJSON((int) id);
            return String.valueOf(respuesta);
        } catch (IOException e) {
            e.printStackTrace();
            return "500";
        }
    }

    @Override
    public Object listar() {
        return listaMetodos;
    }

    // ✅ Consultar por ID
    public MetodoPagoDTO consultarPorId(int id) {
        try {
            return MetodoPagoJSON.getByIdJSON(id);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
