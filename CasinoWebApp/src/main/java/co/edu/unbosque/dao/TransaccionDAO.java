package co.edu.unbosque.dao;

import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.parser.ParseException;
import co.edu.unbosque.json.TransaccionJSON;
import co.edu.unbosque.model.TransaccionDTO;
import co.edu.unbosque.model.ICrud;

public class TransaccionDAO implements ICrud {

    private ArrayList<TransaccionDTO> listaTransacciones;

    @Override
    public String agregar(Object registro) {
        String mensaje = null;
        try {
            TransaccionDTO transaccion = (TransaccionDTO) registro;
            int respuesta = TransaccionJSON.postJSON(transaccion);
            mensaje = String.valueOf(respuesta);
            System.out.println("✅ Transacción agregada. Respuesta: " + mensaje);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mensaje;
    }

    @Override
    public Object consultar() {
        try {
            listaTransacciones = TransaccionJSON.getJSON();
            System.out.println("📋 Transacciones consultadas: " + (listaTransacciones != null ? listaTransacciones.size() : 0));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            listaTransacciones = new ArrayList<>();
        }
        return listaTransacciones;
    }

    @Override
    public String actualizar(Object id, Object registro) {
        String mensaje = null;
        try {
            int respuesta = TransaccionJSON.updateJSON((int) id, (TransaccionDTO) registro);
            mensaje = String.valueOf(respuesta);
            System.out.println("✅ Transacción actualizada. Respuesta: " + mensaje);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mensaje;
    }

    @Override
    public String eliminar(Object id) {
        String mensaje = null;
        try {
            int respuesta = TransaccionJSON.deleteJSON((int) id);
            mensaje = String.valueOf(respuesta);
            System.out.println("✅ Transacción eliminada. Respuesta: " + mensaje);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mensaje;
    }

    @Override
    public Object listar() {
        return listaTransacciones;
    }

    // ✅ Método para consultar por ID
    public TransaccionDTO consultarPorId(int id) {
        try {
            return TransaccionJSON.getJSONById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // ✅ Método para filtrar por tipo de transacción
    public ArrayList<TransaccionDTO> filtrarPorTipo(String tipo) {
        if (listaTransacciones == null) {
            consultar();
        }
        ArrayList<TransaccionDTO> filtradas = new ArrayList<>();
        if (listaTransacciones != null) {
            for (TransaccionDTO transaccion : listaTransacciones) {
                if (transaccion.getTipo() != null && transaccion.getTipo().equalsIgnoreCase(tipo)) {
                    filtradas.add(transaccion);
                }
            }
        }
        System.out.println("📊 Transacciones filtradas por " + tipo + ": " + filtradas.size());
        return filtradas;
    }

    // ✅ Método para filtrar por cliente
    public ArrayList<TransaccionDTO> filtrarPorCliente(int idCliente) {
        if (listaTransacciones == null) {
            consultar();
        }
        ArrayList<TransaccionDTO> filtradas = new ArrayList<>();
        if (listaTransacciones != null) {
            for (TransaccionDTO transaccion : listaTransacciones) {
                if (transaccion.getIdCliente() != null && transaccion.getIdCliente() == idCliente) {
                    filtradas.add(transaccion);
                }
            }
        }
        System.out.println("👤 Transacciones para cliente " + idCliente + ": " + filtradas.size());
        return filtradas;
    }

    // ✅ Método para filtrar por empleado
    public ArrayList<TransaccionDTO> filtrarPorEmpleado(int idEmpleado) {
        if (listaTransacciones == null) {
            consultar();
        }
        ArrayList<TransaccionDTO> filtradas = new ArrayList<>();
        if (listaTransacciones != null) {
            for (TransaccionDTO transaccion : listaTransacciones) {
                if (transaccion.getIdEmpleado() != null && transaccion.getIdEmpleado() == idEmpleado) {
                    filtradas.add(transaccion);
                }
            }
        }
        System.out.println("💼 Transacciones para empleado " + idEmpleado + ": " + filtradas.size());
        return filtradas;
    }

    // ✅ Método para obtener transacciones recientes
    public ArrayList<TransaccionDTO> obtenerTransaccionesRecientes(int cantidad) {
        if (listaTransacciones == null) {
            consultar();
        }
        if (listaTransacciones != null && listaTransacciones.size() > cantidad) {
            ArrayList<TransaccionDTO> recientes = new ArrayList<>(
                listaTransacciones.subList(Math.max(0, listaTransacciones.size() - cantidad), listaTransacciones.size())
            );
            System.out.println("🕒 Últimas " + recientes.size() + " transacciones obtenidas");
            return recientes;
        }
        System.out.println("🕒 Todas las transacciones obtenidas: " + (listaTransacciones != null ? listaTransacciones.size() : 0));
        return listaTransacciones;
    }

    // ✅ Método para obtener estadísticas
    public String obtenerEstadisticas() {
        if (listaTransacciones == null) {
            consultar();
        }
        
        int totalTransacciones = 0;
        java.math.BigDecimal totalIngresos = java.math.BigDecimal.ZERO;
        java.math.BigDecimal totalEgresos = java.math.BigDecimal.ZERO;
        
        if (listaTransacciones != null) {
            totalTransacciones = listaTransacciones.size();
            for (TransaccionDTO transaccion : listaTransacciones) {
                if (transaccion.getMonto() != null) {
                    if (transaccion.getTipo() != null && 
                        (transaccion.getTipo().equalsIgnoreCase("DEPOSITO") || 
                         transaccion.getTipo().equalsIgnoreCase("INGRESO"))) {
                        totalIngresos = totalIngresos.add(transaccion.getMonto());
                    } else {
                        totalEgresos = totalEgresos.add(transaccion.getMonto());
                    }
                }
            }
        }
        
        String estadisticas = String.format(
            "Estadísticas: %d transacciones | Ingresos: $%,.2f | Egresos: $%,.2f | Balance: $%,.2f",
            totalTransacciones, totalIngresos, totalEgresos, totalIngresos.subtract(totalEgresos)
        );
        
        System.out.println("📈 " + estadisticas);
        return estadisticas;
    }

    // ✅ Método para obtener el total por tipo
    public java.math.BigDecimal obtenerTotalPorTipo(String tipo) {
        if (listaTransacciones == null) {
            consultar();
        }
        
        java.math.BigDecimal total = java.math.BigDecimal.ZERO;
        if (listaTransacciones != null) {
            for (TransaccionDTO transaccion : listaTransacciones) {
                if (transaccion.getTipo() != null && transaccion.getTipo().equalsIgnoreCase(tipo) && transaccion.getMonto() != null) {
                    total = total.add(transaccion.getMonto());
                }
            }
        }
        return total;
    }
}