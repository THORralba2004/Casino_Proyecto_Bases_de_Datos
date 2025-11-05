package co.edu.unbosque.dao;

import java.io.IOException;
import org.json.simple.parser.ParseException;
import co.edu.unbosque.json.DashboardJSON;
import co.edu.unbosque.model.DashboardDTO;

public class DashboardDAO {

    private DashboardDTO datosDashboard;

    // ✅ Consultar datos del dashboard
    public DashboardDTO consultar() {
        try {
            datosDashboard = DashboardJSON.getJSON();
            System.out.println("📊 Dashboard actualizado: " + 
                datosDashboard.getTotalClientes() + " clientes, " +
                datosDashboard.getTotalApuestasHoy() + " apuestas hoy");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            // Datos por defecto en caso de error
            datosDashboard = new DashboardDTO();
            datosDashboard.setTotalClientes(0);
            datosDashboard.setTotalEmpleados(0);
            datosDashboard.setTotalApuestasHoy(0);
            datosDashboard.setTotalMovimientosHoy(0);
            datosDashboard.setTorneosActivos(0);
            datosDashboard.setIngresosHoy(new java.math.BigDecimal("0"));
            datosDashboard.setEgresosHoy(new java.math.BigDecimal("0"));
            datosDashboard.setInventarioTotalFichas(0);
            datosDashboard.setUltimaActualizacion(java.time.LocalDateTime.now());
        }
        return datosDashboard;
    }

    // ✅ Obtener datos rápidos (con cache)
    public DashboardDTO obtenerDatosRapidos() {
        if (datosDashboard == null) {
            return consultar();
        }
        
        // Si los datos tienen más de 5 minutos, actualizar
        if (datosDashboard.getUltimaActualizacion()
            .plusMinutes(5)
            .isBefore(java.time.LocalDateTime.now())) {
            return consultar();
        }
        
        return datosDashboard;
    }

    // ✅ Método para forzar actualización
    public DashboardDTO actualizar() {
        return consultar();
    }

    // ✅ Método para obtener estadísticas específicas
    public String obtenerResumen() {
        DashboardDTO datos = obtenerDatosRapidos();
        
        return String.format(
            "📊 Resumen Casino | 👥 %d clientes | 🎰 %d apuestas hoy | 💰 $%,.2f balance | 🏆 %d torneos activos",
            datos.getTotalClientes(),
            datos.getTotalApuestasHoy(),
            datos.getBalanceHoy(),
            datos.getTorneosActivos()
        );
    }

    // ✅ Método para verificar salud del sistema
    public String verificarSaludSistema() {
        DashboardDTO datos = obtenerDatosRapidos();
        
        StringBuilder salud = new StringBuilder();
        salud.append("🔍 Salud del Sistema:\n");
        
        if (datos.getTotalClientes() > 0) {
            salud.append("✅ Clientes: ").append(datos.getTotalClientes()).append("\n");
        } else {
            salud.append("⚠️  Clientes: 0\n");
        }
        
        if (datos.getTotalEmpleados() > 0) {
            salud.append("✅ Empleados: ").append(datos.getTotalEmpleados()).append("\n");
        } else {
            salud.append("⚠️  Empleados: 0\n");
        }
        
        if (datos.getInventarioTotalFichas() > 1000) {
            salud.append("✅ Inventario: ").append(datos.getInventarioTotalFichas()).append(" fichas\n");
        } else {
            salud.append("⚠️  Inventario bajo: ").append(datos.getInventarioTotalFichas()).append(" fichas\n");
        }
        
        if (datos.getBalanceHoy().compareTo(java.math.BigDecimal.ZERO) > 0) {
            salud.append("✅ Balance positivo: $").append(datos.getBalanceHoy()).append("\n");
        } else {
            salud.append("⚠️  Balance negativo: $").append(datos.getBalanceHoy()).append("\n");
        }
        
        salud.append("🕒 Actualizado: ").append(datos.getUltimaActualizacion().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss")));
        
        return salud.toString();
    }
}