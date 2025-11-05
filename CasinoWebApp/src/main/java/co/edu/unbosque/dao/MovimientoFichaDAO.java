package co.edu.unbosque.dao;

import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.parser.ParseException;
import co.edu.unbosque.json.MovimientoFichaJSON;
import co.edu.unbosque.model.MovimientoFichaDTO;
import co.edu.unbosque.model.ICrud;

public class MovimientoFichaDAO implements ICrud {

    private ArrayList<MovimientoFichaDTO> listaMovimientos;

    @Override
    public String agregar(Object registro) {
        String mensaje = null;
        try {
            MovimientoFichaDTO movimiento = (MovimientoFichaDTO) registro;
            
            System.out.println("🔧 Procesando nuevo movimiento...");
            System.out.println("📦 Bóveda: " + movimiento.getIdInventario());
            System.out.println("👤 Empleado: " + movimiento.getIdEmpleado());
            System.out.println("💰 Cantidad: " + movimiento.getCantidad());
            System.out.println("📊 Tipo: " + movimiento.getTipoMov());
            
            // Primero actualizar el inventario
            InventarioFichaDAO inventarioDAO = new InventarioFichaDAO();
            String resultadoInventario = inventarioDAO.procesarMovimiento(
                movimiento.getIdInventario(), 
                movimiento.getCantidad(), 
                movimiento.getTipoMov()
            );
            
            // Verificar si la actualización del inventario fue exitosa
            if (resultadoInventario != null && resultadoInventario.startsWith("2")) {
                // Si el inventario se actualizó correctamente, guardar el movimiento
                int respuesta = MovimientoFichaJSON.postJSON(movimiento);
                mensaje = String.valueOf(respuesta);
                System.out.println("✅ Movimiento agregado. Respuesta: " + mensaje);
                
                // Actualizar la lista local
                if (listaMovimientos != null) {
                    movimiento.setIdMov(obtenerUltimoId() + 1); // Simular ID
                    listaMovimientos.add(movimiento);
                }
            } else {
                // Si hubo error en el inventario, retornar ese error
                mensaje = resultadoInventario;
                System.out.println("❌ Error al actualizar inventario: " + resultadoInventario);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            mensaje = "ERROR: " + e.getMessage();
        }
        return mensaje;
    }

    @Override
    public Object consultar() {
        try {
            listaMovimientos = MovimientoFichaJSON.getJSON();
            System.out.println("📋 Movimientos consultados: " + (listaMovimientos != null ? listaMovimientos.size() : 0));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            listaMovimientos = new ArrayList<>(); // Inicializar lista vacía en caso de error
        }
        return listaMovimientos;
    }

    @Override
    public String actualizar(Object id, Object registro) {
        String mensaje = null;
        try {
            MovimientoFichaDTO movimientoNuevo = (MovimientoFichaDTO) registro;
            MovimientoFichaDTO movimientoViejo = consultarPorId((int) id);
            
            System.out.println("🔧 Actualizando movimiento ID: " + id);
            
            if (movimientoViejo != null) {
                // Primero revertir el movimiento viejo
                InventarioFichaDAO inventarioDAO = new InventarioFichaDAO();
                String tipoMovRevertido = movimientoViejo.getTipoMov().equals("INGRESO") ? "EGRESO" : "INGRESO";
                String resultadoRevertir = inventarioDAO.procesarMovimiento(
                    movimientoViejo.getIdInventario(), 
                    movimientoViejo.getCantidad(), 
                    tipoMovRevertido
                );
                
                if (resultadoRevertir != null && resultadoRevertir.startsWith("2")) {
                    System.out.println("✅ Movimiento viejo revertido en inventario");
                    
                    // Luego aplicar el nuevo movimiento
                    String resultadoAplicar = inventarioDAO.procesarMovimiento(
                        movimientoNuevo.getIdInventario(), 
                        movimientoNuevo.getCantidad(), 
                        movimientoNuevo.getTipoMov()
                    );
                    
                    if (resultadoAplicar != null && resultadoAplicar.startsWith("2")) {
                        System.out.println("✅ Nuevo movimiento aplicado en inventario");
                        
                        // Si ambos inventarios se actualizaron, guardar el movimiento actualizado
                        int respuesta = MovimientoFichaJSON.updateJSON((int) id, movimientoNuevo);
                        mensaje = String.valueOf(respuesta);
                        System.out.println("✅ Movimiento actualizado. Respuesta: " + mensaje);
                        
                        // Actualizar la lista local
                        if (listaMovimientos != null) {
                            for (int i = 0; i < listaMovimientos.size(); i++) {
                                if (listaMovimientos.get(i).getIdMov() == (int) id) {
                                    listaMovimientos.set(i, movimientoNuevo);
                                    break;
                                }
                            }
                        }
                    } else {
                        mensaje = resultadoAplicar;
                        System.out.println("❌ Error al aplicar nuevo movimiento: " + resultadoAplicar);
                        
                        // Revertir la reversión si falla la aplicación
                        inventarioDAO.procesarMovimiento(
                            movimientoViejo.getIdInventario(), 
                            movimientoViejo.getCantidad(), 
                            movimientoViejo.getTipoMov()
                        );
                        System.out.println("🔄 Reversión revertida por fallo en nueva aplicación");
                    }
                } else {
                    mensaje = resultadoRevertir;
                    System.out.println("❌ Error al revertir movimiento viejo: " + resultadoRevertir);
                }
            } else {
                mensaje = "ERROR: Movimiento no encontrado";
                System.out.println("❌ Movimiento no encontrado para ID: " + id);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            mensaje = "ERROR: " + e.getMessage();
        }
        return mensaje;
    }

    @Override
    public String eliminar(Object id) {
        String mensaje = null;
        try {
            System.out.println("🗑 Eliminando movimiento ID: " + id);
            
            // Primero obtener el movimiento para saber qué revertir
            MovimientoFichaDTO movimiento = consultarPorId((int) id);
            if (movimiento != null) {
                System.out.println("📦 Movimiento encontrado - Bóveda: " + movimiento.getIdInventario() + 
                                 ", Cantidad: " + movimiento.getCantidad() + ", Tipo: " + movimiento.getTipoMov());
                
                // Revertir el movimiento en el inventario
                InventarioFichaDAO inventarioDAO = new InventarioFichaDAO();
                String tipoMovRevertido = movimiento.getTipoMov().equals("INGRESO") ? "EGRESO" : "INGRESO";
                String resultadoInventario = inventarioDAO.procesarMovimiento(
                    movimiento.getIdInventario(), 
                    movimiento.getCantidad(), 
                    tipoMovRevertido
                );
                
                if (resultadoInventario != null && resultadoInventario.startsWith("2")) {
                    System.out.println("✅ Inventario revertido correctamente");
                    
                    // Si el inventario se revertió correctamente, eliminar el movimiento
                    int respuesta = MovimientoFichaJSON.deleteJSON((int) id);
                    mensaje = String.valueOf(respuesta);
                    System.out.println("✅ Movimiento eliminado. Respuesta: " + mensaje);
                    
                    // Actualizar la lista local
                    if (listaMovimientos != null) {
                        listaMovimientos.removeIf(m -> m.getIdMov() == (int) id);
                    }
                } else {
                    mensaje = resultadoInventario;
                    System.out.println("❌ Error al revertir inventario: " + resultadoInventario);
                }
            } else {
                mensaje = "ERROR: Movimiento no encontrado";
                System.out.println("❌ Movimiento no encontrado para ID: " + id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            mensaje = "ERROR: " + e.getMessage();
        }
        return mensaje;
    }

    @Override
    public Object listar() {
        return listaMovimientos;
    }

    // ✅ Método para consultar por ID
    public MovimientoFichaDTO consultarPorId(int id) {
        try {
            MovimientoFichaDTO movimiento = MovimientoFichaJSON.getJSONById(id);
            System.out.println("🔍 Consultando movimiento ID: " + id + " - " + (movimiento != null ? "Encontrado" : "No encontrado"));
            return movimiento;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // ✅ Método para filtrar por tipo de movimiento
    public ArrayList<MovimientoFichaDTO> filtrarPorTipo(String tipoMov) {
        if (listaMovimientos == null) {
            consultar();
        }
        ArrayList<MovimientoFichaDTO> filtrados = new ArrayList<>();
        if (listaMovimientos != null) {
            for (MovimientoFichaDTO movimiento : listaMovimientos) {
                if (movimiento.getTipoMov().equalsIgnoreCase(tipoMov)) {
                    filtrados.add(movimiento);
                }
            }
        }
        System.out.println("📊 Movimientos filtrados por " + tipoMov + ": " + filtrados.size());
        return filtrados;
    }

    // ✅ Método para filtrar por bóveda
    public ArrayList<MovimientoFichaDTO> filtrarPorBoveda(int idInventario) {
        if (listaMovimientos == null) {
            consultar();
        }
        ArrayList<MovimientoFichaDTO> filtrados = new ArrayList<>();
        if (listaMovimientos != null) {
            for (MovimientoFichaDTO movimiento : listaMovimientos) {
                if (movimiento.getIdInventario() == idInventario) {
                    filtrados.add(movimiento);
                }
            }
        }
        System.out.println("📊 Movimientos filtrados por bóveda " + idInventario + ": " + filtrados.size());
        return filtrados;
    }

    // ✅ Método para obtener movimientos recientes
    public ArrayList<MovimientoFichaDTO> obtenerMovimientosRecientes(int cantidad) {
        if (listaMovimientos == null) {
            consultar();
        }
        if (listaMovimientos != null && listaMovimientos.size() > cantidad) {
            ArrayList<MovimientoFichaDTO> recientes = new ArrayList<>(
                listaMovimientos.subList(Math.max(0, listaMovimientos.size() - cantidad), listaMovimientos.size())
            );
            System.out.println("🕒 Últimos " + recientes.size() + " movimientos obtenidos");
            return recientes;
        }
        System.out.println("🕒 Todos los movimientos obtenidos: " + (listaMovimientos != null ? listaMovimientos.size() : 0));
        return listaMovimientos;
    }

    // ✅ Método para obtener estadísticas
    public String obtenerEstadisticas() {
        if (listaMovimientos == null) {
            consultar();
        }
        
        int totalIngresos = 0;
        int totalEgresos = 0;
        int cantidadMovimientos = 0;
        
        if (listaMovimientos != null) {
            cantidadMovimientos = listaMovimientos.size();
            for (MovimientoFichaDTO movimiento : listaMovimientos) {
                if ("INGRESO".equals(movimiento.getTipoMov())) {
                    totalIngresos += movimiento.getCantidad();
                } else {
                    totalEgresos += movimiento.getCantidad();
                }
            }
        }
        
        String estadisticas = String.format(
            "Estadísticas: %d movimientos | Ingresos: %d fichas | Egresos: %d fichas | Diferencia: %d fichas",
            cantidadMovimientos, totalIngresos, totalEgresos, (totalIngresos - totalEgresos)
        );
        
        System.out.println("📈 " + estadisticas);
        return estadisticas;
    }

    // ✅ Método auxiliar para obtener el último ID (simulación)
    private int obtenerUltimoId() {
        if (listaMovimientos == null || listaMovimientos.isEmpty()) {
            return 0;
        }
        int maxId = 0;
        for (MovimientoFichaDTO movimiento : listaMovimientos) {
            if (movimiento.getIdMov() > maxId) {
                maxId = movimiento.getIdMov();
            }
        }
        return maxId;
    }

    // ✅ Método para verificar si un egreso es posible
    public boolean verificarEgresoPosible(int idInventario, int cantidad) {
        try {
            InventarioFichaDAO inventarioDAO = new InventarioFichaDAO();
            int saldoActual = inventarioDAO.obtenerSaldoActual(idInventario);
            boolean posible = saldoActual >= cantidad;
            System.out.println("🔍 Verificación egreso - Bóveda: " + idInventario + 
                             ", Saldo: " + saldoActual + ", Cantidad: " + cantidad + 
                             ", Posible: " + posible);
            return posible;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // ✅ Método para obtener el saldo actual de una bóveda
    public int obtenerSaldoBoveda(int idInventario) {
        try {
            InventarioFichaDAO inventarioDAO = new InventarioFichaDAO();
            int saldo = inventarioDAO.obtenerSaldoActual(idInventario);
            System.out.println("💰 Saldo bóveda " + idInventario + ": " + saldo + " fichas");
            return saldo;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}