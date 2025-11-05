package co.edu.unbosque.json;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import co.edu.unbosque.config.Config;
import co.edu.unbosque.model.DashboardDTO;

public class DashboardJSON {

    private static URL url;
    private static String sitio = Config.getBaseUrl() + "/";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    // ✅ Consultar estadísticas del dashboard
    public static DashboardDTO getJSON() throws IOException, ParseException {
        // En una implementación real, tendrías un endpoint específico para dashboard
        // Por ahora, simulamos la construcción de datos desde múltiples endpoints
        DashboardDTO dashboard = new DashboardDTO();
        
        try {
            // Obtener total clientes
            url = new URL(sitio + "clientes");
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("GET");
            http.setRequestProperty("Accept", "application/json");
            
            InputStream respuesta = http.getInputStream();
            byte[] inp = respuesta.readAllBytes();
            String jsonClientes = new String(inp, StandardCharsets.UTF_8);
            
            // Parsear y contar clientes (esto es una simulación)
            dashboard.setTotalClientes(obtenerCountDesdeJSON(jsonClientes));
            http.disconnect();

        } catch (Exception e) {
            // En caso de error, usar valores por defecto
            dashboard.setTotalClientes(0);
        }

        try {
            // Obtener total empleados
            url = new URL(sitio + "empleados");
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("GET");
            http.setRequestProperty("Accept", "application/json");
            
            InputStream respuesta = http.getInputStream();
            byte[] inp = respuesta.readAllBytes();
            String jsonEmpleados = new String(inp, StandardCharsets.UTF_8);
            
            dashboard.setTotalEmpleados(obtenerCountDesdeJSON(jsonEmpleados));
            http.disconnect();

        } catch (Exception e) {
            dashboard.setTotalEmpleados(0);
        }

        try {
            // Obtener torneos activos
            url = new URL(sitio + "torneos");
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("GET");
            http.setRequestProperty("Accept", "application/json");
            
            InputStream respuesta = http.getInputStream();
            byte[] inp = respuesta.readAllBytes();
            String jsonTorneos = new String(inp, StandardCharsets.UTF_8);
            
            dashboard.setTorneosActivos(obtenerCountDesdeJSON(jsonTorneos));
            http.disconnect();

        } catch (Exception e) {
            dashboard.setTorneosActivos(0);
        }

        // Valores simulados para las demás métricas
        dashboard.setTotalApuestasHoy(45);
        dashboard.setTotalMovimientosHoy(28);
        dashboard.setIngresosHoy(new BigDecimal("1250000"));
        dashboard.setEgresosHoy(new BigDecimal("780000"));
        dashboard.setInventarioTotalFichas(12500);
        dashboard.setUltimaActualizacion(LocalDateTime.now());

        return dashboard;
    }

    // ✅ Método auxiliar para contar elementos en un JSON array
    private static int obtenerCountDesdeJSON(String json) throws ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(json);
        if (obj instanceof org.json.simple.JSONArray) {
            return ((org.json.simple.JSONArray) obj).size();
        }
        return 0;
    }

    // ✅ Método para obtener estadísticas específicas (si tu backend lo soporta)
    public static DashboardDTO getEstadisticasJSON() throws IOException, ParseException {
        // Este método usaría un endpoint específico del backend para estadísticas
        // Por ahora, usamos el método general
        return getJSON();
    }
}