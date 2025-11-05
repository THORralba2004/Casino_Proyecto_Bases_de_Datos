package co.edu.unbosque.dao;

import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.parser.ParseException;
import co.edu.unbosque.json.InventarioFichaJSON;
import co.edu.unbosque.model.InventarioFichaDTO;
import co.edu.unbosque.model.ICrud;

public class InventarioFichaDAO implements ICrud {

    private ArrayList<InventarioFichaDTO> listaInventarios;

    @Override
    public String agregar(Object registro) {
        String mensaje = null;
        try {
            int respuesta = InventarioFichaJSON.postJSON((InventarioFichaDTO) registro);
            mensaje = String.valueOf(respuesta);
            System.out.println("Inventario agregado. Respuesta: " + mensaje);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mensaje;
    }

    @Override
    public Object consultar() {
        try {
            listaInventarios = InventarioFichaJSON.getJSON();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return listaInventarios;
    }

    @Override
    public String actualizar(Object id, Object registro) {
        String mensaje = null;
        try {
            int respuesta = InventarioFichaJSON.updateJSON((String) id, (InventarioFichaDTO) registro);
            mensaje = String.valueOf(respuesta);
            System.out.println("Inventario actualizado. Respuesta: " + mensaje);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mensaje;
    }

    @Override
    public String eliminar(Object id) {
        String mensaje = null;
        try {
            int respuesta = InventarioFichaJSON.deleteJSON((String) id);
            mensaje = String.valueOf(respuesta);
            System.out.println("Inventario eliminado. Respuesta: " + mensaje);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mensaje;
    }

    @Override
    public Object listar() {
        return listaInventarios;
    }

    // ✅ Método para consultar por ID
    public InventarioFichaDTO consultarPorId(String id) {
        try {
            return InventarioFichaJSON.getJSONById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // ✅ Método adicional para obtener el total de fichas
    public int obtenerTotalFichas() {
        if (listaInventarios == null) {
            consultar();
        }
        int total = 0;
        if (listaInventarios != null) {
            for (InventarioFichaDTO inventario : listaInventarios) {
                total += inventario.getSaldo();
            }
        }
        return total;
    }
    
    public String actualizarSaldo(String id, int nuevoSaldo, String nombreBoveda) {
        String mensaje = null;
        try {
            int respuesta = InventarioFichaJSON.updateSaldoJSON(id, nuevoSaldo, nombreBoveda);
            mensaje = String.valueOf(respuesta);
            System.out.println("Saldo actualizado. Respuesta: " + mensaje);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mensaje;
    }

    // ✅ Método para obtener el saldo actual
    public int obtenerSaldoActual(String id) {
        try {
            return InventarioFichaJSON.getSaldoJSON(id);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public String procesarMovimiento(String idInventario, int cantidad, String tipoMov) {
        try {
            // Obtener inventario completo para tener el nombreBoveda
            InventarioFichaDTO inventario = consultarPorId(idInventario);
            if (inventario == null) {
                return "ERROR: Bóveda no encontrada";
            }
            
            int saldoActual = inventario.getSaldo();
            String nombreBoveda = inventario.getNombreBoveda();
            int nuevoSaldo;
            
            // Calcular nuevo saldo según el tipo de movimiento
            if ("INGRESO".equalsIgnoreCase(tipoMov)) {
                nuevoSaldo = saldoActual + cantidad;
            } else if ("EGRESO".equalsIgnoreCase(tipoMov)) {
                // Validar que haya suficiente saldo
                if (saldoActual < cantidad) {
                    return "ERROR: Saldo insuficiente. Saldo actual: " + saldoActual;
                }
                nuevoSaldo = saldoActual - cantidad;
            } else {
                return "ERROR: Tipo de movimiento no válido: " + tipoMov;
            }
            
            // Actualizar el saldo manteniendo el nombreBoveda
            return actualizarSaldo(idInventario, nuevoSaldo, nombreBoveda);
            
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR: " + e.getMessage();
        }
    }
}