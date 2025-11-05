// MovimientoFichaJSON.java
package co.edu.unbosque.json;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import co.edu.unbosque.config.Config;
import co.edu.unbosque.model.MovimientoFichaDTO;

public class MovimientoFichaJSON {

    private static URL url;
    private static String sitio = Config.getBaseUrl() + "/";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    // ✅ Consultar todos los movimientos
    public static ArrayList<MovimientoFichaDTO> getJSON() throws IOException, ParseException {
        url = new URL(sitio + "movimientos-ficha");
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("GET");
        http.setRequestProperty("Accept", "application/json");

        InputStream respuesta = http.getInputStream();
        byte[] inp = respuesta.readAllBytes();
        String json = new String(inp, StandardCharsets.UTF_8);

        ArrayList<MovimientoFichaDTO> lista = parsingMovimientos(json);
        http.disconnect();
        return lista;
    }

    // ✅ Parsear JSON → Lista
    public static ArrayList<MovimientoFichaDTO> parsingMovimientos(String json) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONArray movimientos = (JSONArray) parser.parse(json);
        ArrayList<MovimientoFichaDTO> lista = new ArrayList<>();
        Iterator<?> i = movimientos.iterator();

        while (i.hasNext()) {
            JSONObject innerObj = (JSONObject) i.next();
            MovimientoFichaDTO movimiento = new MovimientoFichaDTO();

            Object id = innerObj.get("id");
            Object idInventario = innerObj.get("idInventario");
            Object idEmpleado = innerObj.get("idEmpleado");
            Object fechaHora = innerObj.get("fechaHora");
            Object cantidad = innerObj.get("cantidad");
            Object tipoMov = innerObj.get("tipoMov");
            Object observacion = innerObj.get("observacion");

            if (id != null) movimiento.setIdMov(id.toString());
            if (idInventario != null) movimiento.setIdInventario(idInventario.toString());
            if (idEmpleado != null) movimiento.setIdEmpleado(idEmpleado.toString());
            
            // Parsear fecha/hora
            if (fechaHora != null) {
                String fechaHoraStr = fechaHora.toString();
                movimiento.setFechaHora(LocalDateTime.parse(fechaHoraStr, formatter));
            }
            
            if (cantidad != null) movimiento.setCantidad(Integer.parseInt(cantidad.toString()));
            if (tipoMov != null) movimiento.setTipoMov(tipoMov.toString());
            if (observacion != null) movimiento.setObservacion(observacion.toString());

            lista.add(movimiento);
        }
        return lista;
    }

    // ✅ Consultar por ID
    public static MovimientoFichaDTO getJSONById(String id) throws IOException, ParseException {
        url = new URL(sitio + "movimientos-ficha/" + id);
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
            MovimientoFichaDTO movimiento = new MovimientoFichaDTO();
            
            Object idObj = obj.get("id");
            Object idInventario = obj.get("idInventario");
            Object idEmpleado = obj.get("idEmpleado");
            Object fechaHora = obj.get("fechaHora");
            Object cantidad = obj.get("cantidad");
            Object tipoMov = obj.get("tipoMov");
            Object observacion = obj.get("observacion");

            if (idObj != null) movimiento.setIdMov(idObj.toString());
            if (idInventario != null) movimiento.setIdInventario(idInventario.toString());
            if (idEmpleado != null) movimiento.setIdEmpleado(idEmpleado.toString());
            
            // Parsear fecha/hora
            if (fechaHora != null) {
                String fechaHoraStr = fechaHora.toString();
                movimiento.setFechaHora(LocalDateTime.parse(fechaHoraStr, formatter));
            }
            
            if (cantidad != null) movimiento.setCantidad(Integer.parseInt(cantidad.toString()));
            if (tipoMov != null) movimiento.setTipoMov(tipoMov.toString());
            if (observacion != null) movimiento.setObservacion(observacion.toString());
            
            http.disconnect();
            return movimiento;
        } else {
            http.disconnect();
            return null;
        }
    }

    // ✅ Agregar movimiento
    public static int postJSON(MovimientoFichaDTO movimiento) throws IOException {
        url = new URL(sitio + "movimientos-ficha");
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("Accept", "application/json");
        http.setRequestProperty("Content-Type", "application/json");

        String data = "{"
                + "\"idInventario\":\"" + movimiento.getIdInventario() + "\","
                + "\"idEmpleado\":\"" + movimiento.getIdEmpleado() + "\","
                + "\"fechaHora\":\"" + (movimiento.getFechaHora() != null ? movimiento.getFechaHora().format(formatter) : LocalDateTime.now().format(formatter)) + "\","
                + "\"cantidad\":" + movimiento.getCantidad() + ","
                + "\"tipoMov\":\"" + movimiento.getTipoMov() + "\","
                + "\"observacion\":\"" + (movimiento.getObservacion() != null ? movimiento.getObservacion() : "") + "\""
                + "}";

        try (OutputStream os = http.getOutputStream()) {
            os.write(data.getBytes(StandardCharsets.UTF_8));
        }

        int respuesta = http.getResponseCode();
        http.disconnect();
        return respuesta;
    }

    // ✅ Actualizar movimiento
    public static int updateJSON(String id, MovimientoFichaDTO movimiento) throws IOException {
        url = new URL(sitio + "movimientos-ficha/" + id);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("PUT");
        http.setDoOutput(true);
        http.setRequestProperty("Accept", "application/json");
        http.setRequestProperty("Content-Type", "application/json");

        String data = "{"
                + "\"id\":\"" + id + "\","
                + "\"idInventario\":\"" + movimiento.getIdInventario() + "\","
                + "\"idEmpleado\":\"" + movimiento.getIdEmpleado() + "\","
                + "\"fechaHora\":\"" + (movimiento.getFechaHora() != null ? movimiento.getFechaHora().format(formatter) : LocalDateTime.now().format(formatter)) + "\","
                + "\"cantidad\":" + movimiento.getCantidad() + ","
                + "\"tipoMov\":\"" + movimiento.getTipoMov() + "\","
                + "\"observacion\":\"" + (movimiento.getObservacion() != null ? movimiento.getObservacion() : "") + "\""
                + "}";

        try (OutputStream os = http.getOutputStream()) {
            os.write(data.getBytes(StandardCharsets.UTF_8));
        }

        int respuesta = http.getResponseCode();
        http.disconnect();
        return respuesta;
    }

    // ✅ Eliminar movimiento
    public static int deleteJSON(String id) throws IOException {
        url = new URL(sitio + "movimientos-ficha/" + id);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("DELETE");
        http.setRequestProperty("Accept", "application/json");

        int respuesta = http.getResponseCode();
        http.disconnect();
        return respuesta;
    }
}