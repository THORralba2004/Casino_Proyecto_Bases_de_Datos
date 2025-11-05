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

import co.edu.unbosque.config.Config;
import co.edu.unbosque.model.InventarioFichaDTO;

public class InventarioFichaJSON {

    private static URL url;
    private static String sitio = Config.getBaseUrl() + "/";

    // ✅ Consultar todos los inventarios
    public static ArrayList<InventarioFichaDTO> getJSON() throws IOException, ParseException {
        url = new URL(sitio + "inventario-fichas");
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("GET");
        http.setRequestProperty("Accept", "application/json");

        InputStream respuesta = http.getInputStream();
        byte[] inp = respuesta.readAllBytes();
        String json = new String(inp, StandardCharsets.UTF_8);

        ArrayList<InventarioFichaDTO> lista = parsingInventarios(json);
        http.disconnect();
        return lista;
    }

    // ✅ Parsear JSON → Lista
    public static ArrayList<InventarioFichaDTO> parsingInventarios(String json) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONArray inventarios = (JSONArray) parser.parse(json);
        ArrayList<InventarioFichaDTO> lista = new ArrayList<>();
        Iterator<?> i = inventarios.iterator();

        while (i.hasNext()) {
            JSONObject innerObj = (JSONObject) i.next();
            InventarioFichaDTO inventario = new InventarioFichaDTO();

            Object id = innerObj.get("idInventario");
            Object nombreBoveda = innerObj.get("nombreBoveda");
            Object saldo = innerObj.get("saldo");

            if (id != null) inventario.setIdInventario(Integer.parseInt(id.toString()));
            if (nombreBoveda != null) inventario.setNombreBoveda(nombreBoveda.toString());
            if (saldo != null) inventario.setSaldo(Integer.parseInt(saldo.toString()));

            lista.add(inventario);
        }
        return lista;
    }

    // ✅ Consultar por ID
    public static InventarioFichaDTO getJSONById(int id) throws IOException, ParseException {
        url = new URL(sitio + "inventario-fichas/" + id);
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
            InventarioFichaDTO inventario = new InventarioFichaDTO();
            
            Object idObj = obj.get("idInventario");
            Object nombreBoveda = obj.get("nombreBoveda");
            Object saldo = obj.get("saldo");

            if (idObj != null) inventario.setIdInventario(Integer.parseInt(idObj.toString()));
            if (nombreBoveda != null) inventario.setNombreBoveda(nombreBoveda.toString());
            if (saldo != null) inventario.setSaldo(Integer.parseInt(saldo.toString()));
            
            http.disconnect();
            return inventario;
        } else {
            http.disconnect();
            return null;
        }
    }

    // ✅ Agregar inventario
    public static int postJSON(InventarioFichaDTO inventario) throws IOException {
        url = new URL(sitio + "inventario-fichas");
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("Accept", "application/json");
        http.setRequestProperty("Content-Type", "application/json");

        String data = "{"
                + "\"nombreBoveda\":\"" + inventario.getNombreBoveda() + "\","
                + "\"saldo\":" + inventario.getSaldo()
                + "}";

        try (OutputStream os = http.getOutputStream()) {
            os.write(data.getBytes(StandardCharsets.UTF_8));
        }

        int respuesta = http.getResponseCode();
        http.disconnect();
        return respuesta;
    }

 // ✅ Actualizar inventario completo (asegúrate de que esté así)
    public static int updateJSON(int id, InventarioFichaDTO inventario) throws IOException {
        url = new URL(sitio + "inventario-fichas/" + id);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("PUT");
        http.setDoOutput(true);
        http.setRequestProperty("Accept", "application/json");
        http.setRequestProperty("Content-Type", "application/json");

        String data = "{"
                + "\"idInventario\":" + id + ","
                + "\"nombreBoveda\":\"" + inventario.getNombreBoveda() + "\","
                + "\"saldo\":" + inventario.getSaldo()
                + "}";

        try (OutputStream os = http.getOutputStream()) {
            os.write(data.getBytes(StandardCharsets.UTF_8));
        }

        int respuesta = http.getResponseCode();
        http.disconnect();
        return respuesta;
    }

    // ✅ Eliminar inventario
    public static int deleteJSON(int id) throws IOException {
        url = new URL(sitio + "inventario-fichas/" + id);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("DELETE");
        http.setRequestProperty("Accept", "application/json");

        int respuesta = http.getResponseCode();
        http.disconnect();
        return respuesta;
    }
    
 // ✅ Método para actualizar solo el saldo (CORREGIDO)
    public static int updateSaldoJSON(int id, int nuevoSaldo, String nombreBoveda) throws IOException {
        url = new URL(sitio + "inventario-fichas/" + id);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("PUT");
        http.setDoOutput(true);
        http.setRequestProperty("Accept", "application/json");
        http.setRequestProperty("Content-Type", "application/json");

        String data = "{"
                + "\"idInventario\":" + id + ","
                + "\"nombreBoveda\":\"" + nombreBoveda + "\","
                + "\"saldo\":" + nuevoSaldo
                + "}";

        try (OutputStream os = http.getOutputStream()) {
            os.write(data.getBytes(StandardCharsets.UTF_8));
        }

        int respuesta = http.getResponseCode();
        http.disconnect();
        return respuesta;
    }

    // ✅ Método para consultar solo el saldo actual
    public static int getSaldoJSON(int id) throws IOException, ParseException {
        InventarioFichaDTO inventario = getJSONById(id);
        return inventario != null ? inventario.getSaldo() : 0;
    }
}