package co.edu.unbosque.dao;

import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.parser.ParseException;
import co.edu.unbosque.json.EmpleadoJSON;
import co.edu.unbosque.model.EmpleadoDTO;
import co.edu.unbosque.model.ICrud;

public class EmpleadoDAO implements ICrud {

    private ArrayList<EmpleadoDTO> listaEmpleados;

    @Override
    public String agregar(Object registro) {
        String mensaje = null;
        try {
            int respuesta = EmpleadoJSON.postJSON((EmpleadoDTO) registro);
            mensaje = String.valueOf(respuesta);
            System.out.println("Empleado agregado. Respuesta: " + mensaje);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mensaje;
    }

    @Override
    public Object consultar() {
        try {
            listaEmpleados = EmpleadoJSON.getJSON();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return listaEmpleados;
    }

    @Override
    public String actualizar(Object id, Object registro) {
        String mensaje = null;
        try {
            int respuesta = EmpleadoJSON.updateJSON((int) id, (EmpleadoDTO) registro);
            mensaje = String.valueOf(respuesta);
            System.out.println("Empleado actualizado. Respuesta: " + mensaje);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mensaje;
    }

    @Override
    public String eliminar(Object id) {
        String mensaje = null;
        try {
            int respuesta = EmpleadoJSON.deleteJSON((int) id);
            mensaje = String.valueOf(respuesta);
            System.out.println("Empleado eliminado. Respuesta: " + mensaje);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mensaje;
    }

    @Override
    public Object listar() {
        return listaEmpleados;
    }
    
    public EmpleadoDTO consultarPorId(int id) {
        try {
            return EmpleadoJSON.getJSONById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
