// EventoJuegoJSON.java
package co.edu.unbosque.json;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import co.edu.unbosque.config.Config;
import co.edu.unbosque.model.EventoJuegoDTO;

public class EventoJuegoJSON {

    private static URL url;
    private static String sitio = Config.getBaseUrl() + "/";

    // Obtener lista de eventos
    public static ArrayList<EventoJuegoDTO> getJSON() throws IOException, ParseException {
        url = new URL(sitio + "eventos-juego");
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("GET");
        http.setRequestProperty("Accept", "application/json");

        InputStream respuesta = http.getInputStream();
        byte[] inp = respuesta.readAllBytes();
        String json = new String(inp, StandardCharsets.UTF_8);

        ArrayList<EventoJuegoDTO> lista = parsingEventos(json);
        http.disconnect();
        return lista;
    }

    // Parsear JSON → Lista de DTO
    public static ArrayList<EventoJuegoDTO> parsingEventos(String json) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONArray eventos = (JSONArray) parser.parse(json);
        ArrayList<EventoJuegoDTO> lista = new ArrayList<>();
        Iterator<?> i = eventos.iterator();

        while (i.hasNext()) {
            JSONObject innerObj = (JSONObject) i.next();
            EventoJuegoDTO e = new EventoJuegoDTO();

            Object idEvento = innerObj.get("id");
            Object idTipoJuego = innerObj.get("idTipoJuego");
            Object fechaAlta = innerObj.get("fechaAlta");
            Object estado = innerObj.get("estado");

            if (idEvento != null) e.setIdEvento(idEvento.toString());
            if (idTipoJuego != null) e.setIdTipoJuego(idTipoJuego.toString());
            if (fechaAlta != null) e.setFechaAlta(LocalDate.parse(fechaAlta.toString()));
            if (estado != null) e.setEstado(estado.toString());

            lista.add(e);
        }
        return lista;
    }

    // Agregar evento
    public static int postJSON(EventoJuegoDTO evento) throws IOException {
        url = new URL(sitio + "eventos-juego");
        HttpURLConnection http = (HttpURLConnection) url.openConnection();

        try {
            http.setRequestMethod("POST");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }

        http.setDoOutput(true);
        http.setRequestProperty("Accept", "application/json");
        http.setRequestProperty("Content-Type", "application/json");

        String data = "{"
                + "\"idTipoJuego\": \"" + evento.getIdTipoJuego() + "\","
                + "\"fechaAlta\": \"" + evento.getFechaAlta() + "\","
                + "\"estado\": \"" + evento.getEstado() + "\""
                + "}";

        byte[] out = data.getBytes(StandardCharsets.UTF_8);
        OutputStream stream = http.getOutputStream();
        stream.write(out);

        int respuesta = http.getResponseCode();
        http.disconnect();
        return respuesta;
    }

    // Actualizar evento
    public static int updateJSON(String id, EventoJuegoDTO evento) throws IOException {
        URL url = new URL(sitio + "eventos-juego/" + id);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("PUT");
        http.setDoOutput(true);
        http.setRequestProperty("Accept", "application/json");
        http.setRequestProperty("Content-Type", "application/json");

        String data = "{"
                + "\"id\": \"" + id + "\","
                + "\"idTipoJuego\": \"" + evento.getIdTipoJuego() + "\","
                + "\"fechaAlta\": \"" + evento.getFechaAlta() + "\","
                + "\"estado\": \"" + evento.getEstado() + "\""
                + "}";

        byte[] out = data.getBytes(StandardCharsets.UTF_8);
        OutputStream stream = http.getOutputStream();
        stream.write(out);

        int respuesta = http.getResponseCode();
        http.disconnect();
        return respuesta;
    }

    // Eliminar evento
    public static int deleteJSON(String id) throws IOException {
        URL url = new URL(sitio + "eventos-juego/" + id);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("DELETE");
        http.setRequestProperty("Accept", "application/json");

        int respuesta = http.getResponseCode();
        http.disconnect();
        return respuesta;
    }
}