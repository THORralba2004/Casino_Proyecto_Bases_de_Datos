// ApuestaJSON.java
package co.edu.unbosque.json;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import co.edu.unbosque.config.Config;
import co.edu.unbosque.model.ApuestaDTO;

public class ApuestaJSON {

    private static URL url;
    private static String sitio = Config.getBaseUrl() + "/";

    public static ArrayList<ApuestaDTO> getJSON() throws IOException, ParseException {
        url = new URL(sitio + "apuestas");
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("GET");
        http.setRequestProperty("Accept", "application/json");

        InputStream respuesta = http.getInputStream();
        byte[] inp = respuesta.readAllBytes();
        String json = new String(inp, StandardCharsets.UTF_8);

        ArrayList<ApuestaDTO> lista = parsingApuesta(json);
        http.disconnect();
        return lista;
    }

    public static ArrayList<ApuestaDTO> parsingApuesta(String json) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONArray apuestas = (JSONArray) parser.parse(json);
        ArrayList<ApuestaDTO> lista = new ArrayList<>();
        Iterator i = apuestas.iterator();

        while (i.hasNext()) {
            JSONObject innerObj = (JSONObject) i.next();
            ApuestaDTO a = new ApuestaDTO();

            Object id = innerObj.get("idApuesta");
            if (id != null) a.setIdApuesta(id.toString());
            if (innerObj.get("id") != null) a.setIdCliente(innerObj.get("id").toString());
            if (innerObj.get("idEvento") != null) a.setIdEvento(innerObj.get("idEvento").toString());
            if (innerObj.get("idEmpleado") != null) a.setIdEmpleado(innerObj.get("idEmpleado").toString());
            if (innerObj.get("fechaHora") != null) a.setFechaHora(LocalDateTime.parse(innerObj.get("fechaHora").toString()));
            if (innerObj.get("monto") != null) a.setMonto(Double.parseDouble(innerObj.get("monto").toString()));
            if (innerObj.get("resultado") != null) a.setResultado(innerObj.get("resultado").toString());
            if (innerObj.get("gananciaPerdida") != null) a.setGananciaPerdida(Double.parseDouble(innerObj.get("gananciaPerdida").toString()));
            if (innerObj.get("estado") != null) a.setEstado(innerObj.get("estado").toString());

            lista.add(a);
        }
        return lista;
    }

    public static int postJSON(ApuestaDTO a) throws IOException {
        url = new URL(sitio + "apuestas");
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("Accept", "application/json");
        http.setRequestProperty("Content-Type", "application/json");

        String data = "{"
                + "\"id\":\"" + a.getIdCliente() + "\","
                + "\"idEvento\":\"" + a.getIdEvento() + "\","
                + "\"idEmpleado\":\"" + a.getIdEmpleado() + "\","
                + "\"fechaHora\":\"" + a.getFechaHora() + "\","
                + "\"monto\":" + a.getMonto() + ","
                + "\"resultado\":\"" + a.getResultado() + "\","
                + "\"gananciaPerdida\":" + a.getGananciaPerdida() + ","
                + "\"estado\":\"" + a.getEstado() + "\""
                + "}";

        byte[] out = data.getBytes(StandardCharsets.UTF_8);
        OutputStream stream = http.getOutputStream();
        stream.write(out);
        int respuesta = http.getResponseCode();
        http.disconnect();
        return respuesta;
    }

    public static int deleteJSON(String id) throws IOException {
        URL url = new URL(sitio + "apuestas/" + id);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("DELETE");
        http.setRequestProperty("Accept", "application/json");
        int respuesta = http.getResponseCode();
        http.disconnect();
        return respuesta;
    }

    public static int updateJSON(String id, ApuestaDTO a) throws IOException {
        URL url = new URL(sitio + "apuestas/" + id);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("PUT");
        http.setDoOutput(true);
        http.setRequestProperty("Accept", "application/json");
        http.setRequestProperty("Content-Type", "application/json");

        String data = "{"
                + "\"id\":\"" + id + "\","
                + "\"idCliente\":\"" + a.getIdCliente() + "\","
                + "\"idEvento\":\"" + a.getIdEvento() + "\","
                + "\"idEmpleado\":\"" + a.getIdEmpleado() + "\","
                + "\"fechaHora\":\"" + a.getFechaHora() + "\","
                + "\"monto\":" + a.getMonto() + ","
                + "\"resultado\":\"" + a.getResultado() + "\","
                + "\"gananciaPerdida\":" + a.getGananciaPerdida() + ","
                + "\"estado\":\"" + a.getEstado() + "\""
                + "}";

        byte[] out = data.getBytes(StandardCharsets.UTF_8);
        OutputStream stream = http.getOutputStream();
        stream.write(out);
        int respuesta = http.getResponseCode();
        http.disconnect();
        return respuesta;
    }
}