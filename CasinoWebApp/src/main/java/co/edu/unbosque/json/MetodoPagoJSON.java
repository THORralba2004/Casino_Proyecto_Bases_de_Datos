package co.edu.unbosque.json;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import co.edu.unbosque.model.MetodoPagoDTO;

public class MetodoPagoJSON {

    private static URL url;
    private static String sitio = "http://localhost:8088/";

    // ✅ Obtener todos los métodos de pago
    public static ArrayList<MetodoPagoDTO> getJSON() throws IOException, ParseException {
        url = new URL(sitio + "metodos-pago");
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("GET");
        http.setRequestProperty("Accept", "application/json");

        InputStream respuesta = http.getInputStream();
        byte[] inp = respuesta.readAllBytes();
        String json = new String(inp, StandardCharsets.UTF_8);

        ArrayList<MetodoPagoDTO> lista = parsingMetodosPago(json);
        http.disconnect();
        return lista;
    }

    // ✅ Parsear JSON → Lista
    public static ArrayList<MetodoPagoDTO> parsingMetodosPago(String json) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONArray metodos = (JSONArray) parser.parse(json);
        ArrayList<MetodoPagoDTO> lista = new ArrayList<>();
        Iterator<?> i = metodos.iterator();

        while (i.hasNext()) {
            JSONObject innerObj = (JSONObject) i.next();
            MetodoPagoDTO m = new MetodoPagoDTO();

            Object id = innerObj.get("idMetodo");
            if (id != null) m.setIdMetodo(Integer.parseInt(id.toString()));

            if (innerObj.get("nombre") != null) m.setNombre(innerObj.get("nombre").toString());
            if (innerObj.get("descripcion") != null) m.setDescripcion(innerObj.get("descripcion").toString());
            if (innerObj.get("estado") != null) m.setEstado(innerObj.get("estado").toString());

            lista.add(m);
        }
        return lista;
    }

    // ✅ Obtener por ID
    public static MetodoPagoDTO getByIdJSON(int id) throws IOException, ParseException {
        url = new URL(sitio + "metodos-pago/" + id);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("GET");
        http.setRequestProperty("Accept", "application/json");

        if (http.getResponseCode() == 200) {
            InputStream respuesta = http.getInputStream();
            byte[] inp = respuesta.readAllBytes();
            String json = new String(inp, StandardCharsets.UTF_8);

            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(json);

            MetodoPagoDTO m = new MetodoPagoDTO();
            if (obj.get("idMetodo") != null) m.setIdMetodo(Integer.parseInt(obj.get("idMetodo").toString()));
            if (obj.get("nombre") != null) m.setNombre(obj.get("nombre").toString());
            if (obj.get("descripcion") != null) m.setDescripcion(obj.get("descripcion").toString());
            if (obj.get("estado") != null) m.setEstado(obj.get("estado").toString());

            http.disconnect();
            return m;
        }
        http.disconnect();
        return null;
    }

    // ✅ Agregar nuevo método de pago
    public static int postJSON(MetodoPagoDTO metodo) throws IOException {
        url = new URL(sitio + "metodos-pago");
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("Accept", "application/json");
        http.setRequestProperty("Content-Type", "application/json");

        String data = "{"
                + "\"nombre\":\"" + metodo.getNombre() + "\","
                + "\"descripcion\":\"" + metodo.getDescripcion() + "\","
                + "\"estado\":\"" + metodo.getEstado() + "\""
                + "}";

        OutputStream os = http.getOutputStream();
        os.write(data.getBytes(StandardCharsets.UTF_8));

        int respuesta = http.getResponseCode();
        http.disconnect();
        return respuesta;
    }

    // ✅ Actualizar método de pago
    public static int updateJSON(int id, MetodoPagoDTO metodo) throws IOException {
        url = new URL(sitio + "metodos-pago/" + id);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("PUT");
        http.setDoOutput(true);
        http.setRequestProperty("Accept", "application/json");
        http.setRequestProperty("Content-Type", "application/json");

        String data = "{"
                + "\"idMetodo\":" + id + ","
                + "\"nombre\":\"" + metodo.getNombre() + "\","
                + "\"descripcion\":\"" + metodo.getDescripcion() + "\","
                + "\"estado\":\"" + metodo.getEstado() + "\""
                + "}";

        OutputStream os = http.getOutputStream();
        os.write(data.getBytes(StandardCharsets.UTF_8));

        int respuesta = http.getResponseCode();
        http.disconnect();
        return respuesta;
    }

    // ✅ Eliminar método de pago
    public static int deleteJSON(int id) throws IOException {
        url = new URL(sitio + "metodos-pago/" + id);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("DELETE");
        http.setRequestProperty("Accept", "application/json");

        int respuesta = http.getResponseCode();
        http.disconnect();
        return respuesta;
    }
}
