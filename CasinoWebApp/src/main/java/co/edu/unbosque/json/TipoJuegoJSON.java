package co.edu.unbosque.json;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import co.edu.unbosque.config.Config;
import co.edu.unbosque.model.TipoJuegoDTO;

public class TipoJuegoJSON {
    
    private static URL url;
    private static String sitio = Config.getBaseUrl() + "/";

    // Obtener lista
    public static ArrayList<TipoJuegoDTO> getJSON() throws IOException, ParseException {
        url = new URL(sitio + "tipos-juego");
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("GET");
        http.setRequestProperty("Accept", "application/json");

        InputStream respuesta = http.getInputStream();
        byte[] inp = respuesta.readAllBytes();
        String json = new String(inp, StandardCharsets.UTF_8);

        ArrayList<TipoJuegoDTO> lista = parsingTipoJuego(json);
        http.disconnect();
        return lista;
    }

    // Convertir JSON → Lista
    public static ArrayList<TipoJuegoDTO> parsingTipoJuego(String json) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONArray tipoJuegos = (JSONArray) parser.parse(json);
        ArrayList<TipoJuegoDTO> lista = new ArrayList<>();
        Iterator i = tipoJuegos.iterator();
        while (i.hasNext()) {
            JSONObject innerObj = (JSONObject) i.next();
            TipoJuegoDTO t = new TipoJuegoDTO();

            // ✅ Usa los nombres reales de tu API
            Object id = innerObj.get("idTipoJuego");
            Object nombre = innerObj.get("nombre");

            if (id != null) t.setIdTipoJuego(Integer.parseInt(id.toString()));
            if (nombre != null) t.setNombre(nombre.toString());

            lista.add(t);
        }
        return lista;
    }
    
    public static TipoJuegoDTO getByIdJSON(int id) throws IOException, ParseException {
        if (id <= 0) return null;

        URL url = new URL(sitio + "tipos-juego/" + id);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("GET");
        http.setRequestProperty("Accept", "application/json");

        int status = http.getResponseCode();
        if (status == 404) {
            http.disconnect();
            return null;
        }

        InputStream respuesta = http.getInputStream();
        byte[] inp = respuesta.readAllBytes();
        String json = new String(inp, StandardCharsets.UTF_8);
        http.disconnect();

        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(json);

        TipoJuegoDTO tipo = new TipoJuegoDTO();
        Object idObj = obj.get("idTipoJuego");
        Object nombreObj = obj.get("nombre");

        if (idObj != null) tipo.setIdTipoJuego(Integer.parseInt(idObj.toString()));
        if (nombreObj != null) tipo.setNombre(nombreObj.toString());

        return tipo;
    }




    // Agregar tipo de juego
    public static int postJSON(TipoJuegoDTO tipo) throws IOException {
        url = new URL(sitio + "tipos-juego");
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        try {
            http.setRequestMethod("POST");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        http.setDoOutput(true);
        http.setRequestProperty("Accept", "application/json");
        http.setRequestProperty("Content-Type", "application/json");

        String data = "{\"nombre\":\"" + tipo.getNombre() + "\"}";
        byte[] out = data.getBytes(StandardCharsets.UTF_8);

        OutputStream stream = http.getOutputStream();
        stream.write(out);
        int respuesta = http.getResponseCode();
        http.disconnect();
        return respuesta;
    }
    
    public static int deleteJSON(int id) throws IOException {
        URL url = new URL("http://localhost:8088/tipos-juego/" + id);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("DELETE");
        http.setRequestProperty("Accept", "application/json");
        int respuesta = http.getResponseCode();
        http.disconnect();
        return respuesta;
    }
    
    public static int updateJSON(int id, TipoJuegoDTO tipoJuego) throws IOException {
        URL url = new URL("http://localhost:8088/tipos-juego/" + id);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("PUT");
        http.setDoOutput(true);
        http.setRequestProperty("Accept", "application/json");
        http.setRequestProperty("Content-Type", "application/json");

        String data = "{"
                + "\"idTipoJuego\": " + id + ","
                + "\"nombre\": \"" + tipoJuego.getNombre() + "\""
                + "}";

        byte[] out = data.getBytes(StandardCharsets.UTF_8);
        OutputStream stream = http.getOutputStream();
        stream.write(out);

        int respuesta = http.getResponseCode();
        http.disconnect();
        return respuesta;
    }

}
