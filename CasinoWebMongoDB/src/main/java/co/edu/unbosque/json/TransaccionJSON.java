// TransaccionJSON.java
package co.edu.unbosque.json;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import co.edu.unbosque.config.Config;
import co.edu.unbosque.model.TransaccionDTO;

public class TransaccionJSON {

    private static URL url;
    private static String sitio = Config.getBaseUrl() + "/";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    // ✅ Consultar todas las transacciones
    public static ArrayList<TransaccionDTO> getJSON() throws IOException, ParseException {
        url = new URL(sitio + "transacciones");
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("GET");
        http.setRequestProperty("Accept", "application/json");

        InputStream respuesta = http.getInputStream();
        byte[] inp = respuesta.readAllBytes();
        String json = new String(inp, StandardCharsets.UTF_8);

        ArrayList<TransaccionDTO> lista = parsingTransacciones(json);
        http.disconnect();
        return lista;
    }

    // ✅ Parsear JSON → Lista
    public static ArrayList<TransaccionDTO> parsingTransacciones(String json) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONArray transacciones = (JSONArray) parser.parse(json);
        ArrayList<TransaccionDTO> lista = new ArrayList<>();
        Iterator<?> i = transacciones.iterator();

        while (i.hasNext()) {
            JSONObject innerObj = (JSONObject) i.next();
            TransaccionDTO transaccion = new TransaccionDTO();

            Object id = innerObj.get("id");
            Object idEmpleado = innerObj.get("idEmpleado");
            Object idMetodo = innerObj.get("idMetodo");
            Object idCliente = innerObj.get("idCliente");
            Object fechaHora = innerObj.get("fechaHora");
            Object monto = innerObj.get("monto");
            Object tipo = innerObj.get("tipo");
            Object observacion = innerObj.get("observacion");

            if (id != null) transaccion.setIdTransaccion(id.toString());
            if (idEmpleado != null) transaccion.setIdEmpleado(idEmpleado.toString());
            if (idMetodo != null) transaccion.setIdMetodo(idMetodo.toString());
            if (idCliente != null) transaccion.setIdCliente(idCliente.toString());
            
            // Parsear fecha/hora
            if (fechaHora != null) {
                String fechaHoraStr = fechaHora.toString();
                transaccion.setFechaHora(LocalDateTime.parse(fechaHoraStr, formatter));
            }
            
            if (monto != null) transaccion.setMonto(new BigDecimal(monto.toString()));
            if (tipo != null) transaccion.setTipo(tipo.toString());
            if (observacion != null) transaccion.setObservacion(observacion.toString());

            lista.add(transaccion);
        }
        return lista;
    }

    // ✅ Consultar por ID
    public static TransaccionDTO getJSONById(String id) throws IOException, ParseException {
        url = new URL(sitio + "transacciones/" + id);
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
            TransaccionDTO transaccion = new TransaccionDTO();
            
            Object idObj = obj.get("id");
            Object idEmpleado = obj.get("idEmpleado");
            Object idMetodo = obj.get("idMetodo");
            Object idCliente = obj.get("idCliente");
            Object fechaHora = obj.get("fechaHora");
            Object monto = obj.get("monto");
            Object tipo = obj.get("tipo");
            Object observacion = obj.get("observacion");

            if (idObj != null) transaccion.setIdTransaccion(idObj.toString());
            if (idEmpleado != null) transaccion.setIdEmpleado(idEmpleado.toString());
            if (idMetodo != null) transaccion.setIdMetodo(idMetodo.toString());
            if (idCliente != null) transaccion.setIdCliente(idCliente.toString());
            
            // Parsear fecha/hora
            if (fechaHora != null) {
                String fechaHoraStr = fechaHora.toString();
                transaccion.setFechaHora(LocalDateTime.parse(fechaHoraStr, formatter));
            }
            
            if (monto != null) transaccion.setMonto(new BigDecimal(monto.toString()));
            if (tipo != null) transaccion.setTipo(tipo.toString());
            if (observacion != null) transaccion.setObservacion(observacion.toString());
            
            http.disconnect();
            return transaccion;
        } else {
            http.disconnect();
            return null;
        }
    }

    // ✅ Agregar transacción
    public static int postJSON(TransaccionDTO transaccion) throws IOException {
        url = new URL(sitio + "transacciones");
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("Accept", "application/json");
        http.setRequestProperty("Content-Type", "application/json");

        String fechaHoraStr = transaccion.getFechaHora() != null ? 
            transaccion.getFechaHora().format(formatter) : 
            LocalDateTime.now().format(formatter);

        String data = "{"
                + "\"idEmpleado\":\"" + transaccion.getIdEmpleado() + "\","
                + "\"idMetodo\":\"" + transaccion.getIdMetodo() + "\","
                + "\"idCliente\":\"" + transaccion.getIdCliente() + "\","
                + "\"fechaHora\":\"" + fechaHoraStr + "\","
                + "\"monto\":" + transaccion.getMonto() + ","
                + "\"tipo\":\"" + transaccion.getTipo() + "\","
                + "\"observacion\":\"" + (transaccion.getObservacion() != null ? transaccion.getObservacion() : "") + "\""
                + "}";

        try (OutputStream os = http.getOutputStream()) {
            os.write(data.getBytes(StandardCharsets.UTF_8));
        }

        int respuesta = http.getResponseCode();
        http.disconnect();
        return respuesta;
    }

    // ✅ Actualizar transacción
    public static int updateJSON(String id, TransaccionDTO transaccion) throws IOException {
        url = new URL(sitio + "transacciones/" + id);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("PUT");
        http.setDoOutput(true);
        http.setRequestProperty("Accept", "application/json");
        http.setRequestProperty("Content-Type", "application/json");

        String fechaHoraStr = transaccion.getFechaHora() != null ? 
            transaccion.getFechaHora().format(formatter) : 
            LocalDateTime.now().format(formatter);

        String data = "{"
                + "\"id\":\"" + id + "\","
                + "\"idEmpleado\":\"" + transaccion.getIdEmpleado() + "\","
                + "\"idMetodo\":\"" + transaccion.getIdMetodo() + "\","
                + "\"idCliente\":\"" + transaccion.getIdCliente() + "\","
                + "\"fechaHora\":\"" + fechaHoraStr + "\","
                + "\"monto\":" + transaccion.getMonto() + ","
                + "\"tipo\":\"" + transaccion.getTipo() + "\","
                + "\"observacion\":\"" + (transaccion.getObservacion() != null ? transaccion.getObservacion() : "") + "\""
                + "}";

        try (OutputStream os = http.getOutputStream()) {
            os.write(data.getBytes(StandardCharsets.UTF_8));
        }

        int respuesta = http.getResponseCode();
        http.disconnect();
        return respuesta;
    }

    // ✅ Eliminar transacción
    public static int deleteJSON(String id) throws IOException {
        url = new URL(sitio + "transacciones/" + id);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("DELETE");
        http.setRequestProperty("Accept", "application/json");

        int respuesta = http.getResponseCode();
        http.disconnect();
        return respuesta;
    }
}