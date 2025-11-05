package co.edu.unbosque.dao;

import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.parser.ParseException;
import co.edu.unbosque.json.TorneoJSON;
import co.edu.unbosque.model.TorneoDTO;
import co.edu.unbosque.model.ICrud;

public class TorneoDAO implements ICrud {

    private ArrayList<TorneoDTO> listaTorneos;

    @Override
    public String agregar(Object registro) {
        String mensaje = null;
        try {
            int respuesta = TorneoJSON.postJSON((TorneoDTO) registro);
            mensaje = String.valueOf(respuesta);
            System.out.println("✅ Torneo agregado. Respuesta: " + mensaje);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mensaje;
    }

    @Override
    public Object consultar() {
        try {
            listaTorneos = TorneoJSON.getJSON();
            System.out.println("📋 Torneos consultados: " + (listaTorneos != null ? listaTorneos.size() : 0));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            listaTorneos = new ArrayList<>();
        }
        return listaTorneos;
    }

    @Override
    public String actualizar(Object id, Object registro) {
        String mensaje = null;
        try {
            int respuesta = TorneoJSON.updateJSON((int) id, (TorneoDTO) registro);
            mensaje = String.valueOf(respuesta);
            System.out.println("✅ Torneo actualizado. Respuesta: " + mensaje);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mensaje;
    }

    @Override
    public String eliminar(Object id) {
        String mensaje = null;
        try {
            int respuesta = TorneoJSON.deleteJSON((int) id);
            mensaje = String.valueOf(respuesta);
            System.out.println("✅ Torneo eliminado. Respuesta: " + mensaje);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mensaje;
    }

    @Override
    public Object listar() {
        return listaTorneos;
    }

    // ✅ Método para consultar por ID
    public TorneoDTO consultarPorId(int id) {
        try {
            return TorneoJSON.getJSONById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // ✅ Método para filtrar torneos activos
    public ArrayList<TorneoDTO> filtrarActivos() {
        if (listaTorneos == null) {
            consultar();
        }
        ArrayList<TorneoDTO> activos = new ArrayList<>();
        if (listaTorneos != null) {
            java.time.LocalDate hoy = java.time.LocalDate.now();
            for (TorneoDTO torneo : listaTorneos) {
                if (torneo.getFechaFin() == null || torneo.getFechaFin().isAfter(hoy) || torneo.getFechaFin().isEqual(hoy)) {
                    activos.add(torneo);
                }
            }
        }
        System.out.println("🎯 Torneos activos: " + activos.size());
        return activos;
    }

    // ✅ Método para filtrar torneos finalizados
    public ArrayList<TorneoDTO> filtrarFinalizados() {
        if (listaTorneos == null) {
            consultar();
        }
        ArrayList<TorneoDTO> finalizados = new ArrayList<>();
        if (listaTorneos != null) {
            java.time.LocalDate hoy = java.time.LocalDate.now();
            for (TorneoDTO torneo : listaTorneos) {
                if (torneo.getFechaFin() != null && torneo.getFechaFin().isBefore(hoy)) {
                    finalizados.add(torneo);
                }
            }
        }
        System.out.println("🏁 Torneos finalizados: " + finalizados.size());
        return finalizados;
    }

    // ✅ Método para obtener torneos con mayor premio
    public ArrayList<TorneoDTO> obtenerTorneosMayorPremio(int cantidad) {
        if (listaTorneos == null) {
            consultar();
        }
        if (listaTorneos != null) {
            listaTorneos.sort((t1, t2) -> t2.getPremio().compareTo(t1.getPremio()));
            if (listaTorneos.size() > cantidad) {
                return new ArrayList<>(listaTorneos.subList(0, cantidad));
            }
        }
        return listaTorneos;
    }

    // ✅ Método para obtener estadísticas
    public String obtenerEstadisticas() {
        if (listaTorneos == null) {
            consultar();
        }
        
        int totalTorneos = 0;
        int torneosActivos = 0;
        int torneosFinalizados = 0;
        java.math.BigDecimal totalPremios = java.math.BigDecimal.ZERO;
        
        if (listaTorneos != null) {
            totalTorneos = listaTorneos.size();
            java.time.LocalDate hoy = java.time.LocalDate.now();
            
            for (TorneoDTO torneo : listaTorneos) {
                totalPremios = totalPremios.add(torneo.getPremio());
                if (torneo.getFechaFin() != null && torneo.getFechaFin().isBefore(hoy)) {
                    torneosFinalizados++;
                } else {
                    torneosActivos++;
                }
            }
        }
        
        String estadisticas = String.format(
            "Estadísticas: %d torneos | %d activos | %d finalizados | Total premios: $%,.2f",
            totalTorneos, torneosActivos, torneosFinalizados, totalPremios
        );
        
        System.out.println("📈 " + estadisticas);
        return estadisticas;
    }
}