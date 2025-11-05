package co.edu.unbosque.dao;

import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;

import co.edu.unbosque.json.ClienteJSON;
import co.edu.unbosque.model.ClienteDTO;
import co.edu.unbosque.model.ICrud;

public class ClienteDAO implements ICrud {

    private ArrayList<ClienteDTO> listaClientes;

    @Override
    public String agregar(Object registro) {
        String mensaje = null;
        try {
            int respuesta = ClienteJSON.postJSON((ClienteDTO) registro);
            mensaje = String.valueOf(respuesta);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mensaje;
    }

    @Override
    public Object consultar() {
        try {
            listaClientes = ClienteJSON.getJSON();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return listaClientes;
    }

    @Override
    public String actualizar(Object id, Object registro) {
        String mensaje = null;
        try {
            int respuesta = ClienteJSON.updateJSON((int) id, (ClienteDTO) registro);
            mensaje = String.valueOf(respuesta);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mensaje;
    }

    @Override
    public String eliminar(Object id) {
        String mensaje = null;
        try {
            int respuesta = ClienteJSON.deleteJSON((int) id);
            mensaje = String.valueOf(respuesta);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mensaje;
    }

    @Override
    public Object listar() {
        return listaClientes;
    }
    
    public ClienteDTO consultarPorId(int id) {
        try {
            return ClienteJSON.getJSONById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
