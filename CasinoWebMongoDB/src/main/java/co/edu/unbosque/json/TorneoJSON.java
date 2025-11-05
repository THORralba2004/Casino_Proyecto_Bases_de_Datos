// TorneoJSON.java
package co.edu.unbosque.json;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import co.edu.unbosque.config.Config;
import co.edu.unbosque.model.TorneoDTO;

public class TorneoJSON {

    private static URL url;
    private static String sitio = Config.getBaseUrl() + "/";

    // ✅ Consultar todos los torneos
    public static ArrayList<TorneoDTO> getJSON() throws IOException, ParseException {
        url = new URL(sitio + "torneos");
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("GET");
        http.setRequestProperty("Accept", "application/json");

        InputStream respuesta = http.getInputStream();
        byte[] inp = respuesta.readAllBytes();
        String json = new String(inp, StandardCharsets.UTF_8);

        ArrayList<TorneoDTO> lista = parsingTorneos(json);
        http.disconnect();
        return lista;
    }

    // ✅ Parsear JSON → Lista
    public static ArrayList<TorneoDTO> parsingTorneos(String json) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONArray torneos = (JSONArray) parser.parse(json);
        ArrayList<TorneoDTO> lista = new ArrayList<>();
        Iterator<?> i = torneos.iterator();

        while (i.hasNext()) {
            JSONObject innerObj = (JSONObject) i.next();
            TorneoDTO torneo = new TorneoDTO();

            Object idEvento = innerObj.get("idEvento");
            Object nombre = innerObj.get("nombre");
            Object fechaInicio = innerObj.get("fechaInicio");
            Object fechaFin = innerObj.get("fechaFin");
            Object buyIn = innerObj.get("buyIn");
            Object premio = innerObj.get("premio");

            if (idEvento != null) torneo.setIdEvento(idEvento.toString());
            if (nombre != null) torneo.setNombre(nombre.toString());
            if (fechaInicio != null) torneo.setFechaInicio(LocalDate.parse(fechaInicio.toString()));
            if (fechaFin != null) {
                try {
                    torneo.setFechaFin(LocalDate.parse(fechaFin.toString()));
                } catch (Exception e) {
                    torneo.setFechaFin(null); // Permitir fechaFin nula
                }
            }
            if (buyIn != null) {
                try {
                    torneo.setBuyIn(new BigDecimal(buyIn.toString()));
                } catch (Exception e) {
                    torneo.setBuyIn(BigDecimal.ZERO);
                }
            }
            if (premio != null) {
                try {
                    torneo.setPremio(new BigDecimal(premio.toString()));
                } catch (Exception e) {
                    torneo.setPremio(BigDecimal.ZERO);
                }
            }

            lista.add(torneo);
        }
        return lista;
    }

    // ✅ Consultar por ID
    public static TorneoDTO getJSONById(String id) throws IOException, ParseException {
        url = new URL(sitio + "torneos/" + id);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("GET");
        http.setRequestProperty("Accept", "application/json");
        
        int responseCode = http.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            InputStream respuesta = http.getInputStream();
            byte[] inp = respuesta.readAllBytes();
            String json = new String(inp, StandardCharsets.UTF_8);
            
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(json);
            TorneoDTO torneo = new TorneoDTO();
            
            Object idEvento = obj.get("idEvento");
            Object nombre = obj.get("nombre");
            Object fechaInicio = obj.get("fechaInicio");
            Object fechaFin = obj.get("fechaFin");
            Object buyIn = obj.get("buyIn");
            Object premio = obj.get("premio");

            if (idEvento != null) torneo.setIdEvento(idEvento.toString());
            if (nombre != null) torneo.setNombre(nombre.toString());
            if (fechaInicio != null) torneo.setFechaInicio(LocalDate.parse(fechaInicio.toString()));
            if (fechaFin != null) {
                try {
                    torneo.setFechaFin(LocalDate.parse(fechaFin.toString()));
                } catch (Exception e) {
                    torneo.setFechaFin(null);
                }
            }
            if (buyIn != null) {
                try {
                    torneo.setBuyIn(new BigDecimal(buyIn.toString()));
                } catch (Exception e) {
                    torneo.setBuyIn(BigDecimal.ZERO);
                }
            }
            if (premio != null) {
                try {
                    torneo.setPremio(new BigDecimal(premio.toString()));
                } catch (Exception e) {
                    torneo.setPremio(BigDecimal.ZERO);
                }
            }
            
            http.disconnect();
            return torneo;
        } else {
            http.disconnect();
            return null;
        }
    }

    // ✅ Agregar torneo (CORREGIDO)
    public static int postJSON(TorneoDTO torneo) throws IOException {
        url = new URL(sitio + "torneos");
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("Accept", "application/json");
        http.setRequestProperty("Content-Type", "application/json");

        // Construir JSON de forma segura
        StringBuilder dataBuilder = new StringBuilder();
        dataBuilder.append("{");
        dataBuilder.append("\"nombre\":\"").append(escapeJson(torneo.getNombre())).append("\"");
        dataBuilder.append(",\"fechaInicio\":\"").append(torneo.getFechaInicio()).append("\"");
        
        if (torneo.getFechaFin() != null) {
            dataBuilder.append(",\"fechaFin\":\"").append(torneo.getFechaFin()).append("\"");
        }
        
        dataBuilder.append(",\"buyIn\":").append(torneo.getBuyIn() != null ? torneo.getBuyIn() : "0");
        dataBuilder.append(",\"premio\":").append(torneo.getPremio() != null ? torneo.getPremio() : "0");
        dataBuilder.append("}");

        String data = dataBuilder.toString();
        System.out.println("📤 Enviando datos: " + data);

        try (OutputStream os = http.getOutputStream()) {
            os.write(data.getBytes(StandardCharsets.UTF_8));
        }

        int respuesta = http.getResponseCode();
        System.out.println("📥 Respuesta del servidor: " + respuesta);
        http.disconnect();
        return respuesta;
    }

    // ✅ Actualizar torneo (CORREGIDO)
    public static int updateJSON(String id, TorneoDTO torneo) throws IOException {
        url = new URL(sitio + "torneos/" + id);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("PUT");
        http.setDoOutput(true);
        http.setRequestProperty("Accept", "application/json");
        http.setRequestProperty("Content-Type", "application/json");

        StringBuilder dataBuilder = new StringBuilder();
        dataBuilder.append("{");
        dataBuilder.append("\"idEvento\":\"").append(id).append("\"");
        dataBuilder.append(",\"nombre\":\"").append(escapeJson(torneo.getNombre())).append("\"");
        dataBuilder.append(",\"fechaInicio\":\"").append(torneo.getFechaInicio()).append("\"");
        
        if (torneo.getFechaFin() != null) {
            dataBuilder.append(",\"fechaFin\":\"").append(torneo.getFechaFin()).append("\"");
        } else {
            dataBuilder.append(",\"fechaFin\":null");
        }
        
        dataBuilder.append(",\"buyIn\":").append(torneo.getBuyIn() != null ? torneo.getBuyIn() : "0");
        dataBuilder.append(",\"premio\":").append(torneo.getPremio() != null ? torneo.getPremio() : "0");
        dataBuilder.append("}");

        String data = dataBuilder.toString();
        System.out.println("📤 Actualizando torneo: " + data);

        try (OutputStream os = http.getOutputStream()) {
            os.write(data.getBytes(StandardCharsets.UTF_8));
        }

        int respuesta = http.getResponseCode();
        http.disconnect();
        return respuesta;
    }

    // ✅ Eliminar torneo
    public static int deleteJSON(String id) throws IOException {
        url = new URL(sitio + "torneos/" + id);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("DELETE");
        http.setRequestProperty("Accept", "application/json");

        int respuesta = http.getResponseCode();
        http.disconnect();
        return respuesta;
    }

    // ✅ Método auxiliar para escapar caracteres JSON
    private static String escapeJson(String input) {
        if (input == null) return "";
        return input.replace("\\", "\\\\")
                   .replace("\"", "\\\"")
                   .replace("\b", "\\b")
                   .replace("\f", "\\f")
                   .replace("\n", "\\n")
                   .replace("\r", "\\r")
                   .replace("\t", "\\t");
    }
}