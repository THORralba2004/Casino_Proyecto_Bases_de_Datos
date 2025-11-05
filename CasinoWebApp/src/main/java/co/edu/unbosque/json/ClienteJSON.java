package co.edu.unbosque.json;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import co.edu.unbosque.config.Config;
import co.edu.unbosque.model.ClienteDTO;

public class ClienteJSON {

    private static URL url;
    private static String sitio = Config.getBaseUrl() + "/";;

    public static ArrayList<ClienteDTO> getJSON() throws IOException, ParseException {
        url = new URL(sitio + "clientes");
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("GET");
        http.setRequestProperty("Accept", "application/json");
        InputStream respuesta = http.getInputStream();
        byte[] inp = respuesta.readAllBytes();
        String json = new String(inp, StandardCharsets.UTF_8);
        ArrayList<ClienteDTO> lista = parsingClientes(json);
        http.disconnect();
        return lista;
    }

    public static ArrayList<ClienteDTO> parsingClientes(String json) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONArray clientes = (JSONArray) parser.parse(json);
        ArrayList<ClienteDTO> lista = new ArrayList<>();
        Iterator<?> i = clientes.iterator();
        while (i.hasNext()) {
            JSONObject obj = (JSONObject) i.next();
            ClienteDTO c = new ClienteDTO();

            Object id = obj.get("idCliente");
            Object nombre = obj.get("nombre");
            Object documento = obj.get("docId");
            Object correo = obj.get("email");
            Object telefono = obj.get("telefono");
            Object fechaRegistro = obj.get("fechaRegistro");

            if (id != null) c.setIdCliente(Integer.parseInt(id.toString()));
            if (nombre != null) c.setNombre(nombre.toString());
            if (documento != null) c.setDocumento(documento.toString());
            if (correo != null) c.setCorreo(correo.toString());
            if (telefono != null) c.setTelefono(telefono.toString());
            if (fechaRegistro != null) c.setFechaRegistro(LocalDate.parse(fechaRegistro.toString()));

            lista.add(c);
        }
        return lista;
    }

    public static int postJSON(ClienteDTO cliente) throws IOException {
        url = new URL(sitio + "clientes");
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("Accept", "application/json");
        http.setRequestProperty("Content-Type", "application/json");

        String data = "{"
                + "\"nombre\":\"" + cliente.getNombre() + "\","
                + "\"documento\":\"" + cliente.getDocumento() + "\","
                + "\"email\":\"" + cliente.getCorreo() + "\","
                + "\"telefono\":\"" + cliente.getTelefono() + "\","
                + "\"fechaRegistro\":\"" + cliente.getFechaRegistro() + "\""
                + "}";

        byte[] out = data.getBytes(StandardCharsets.UTF_8);
        OutputStream stream = http.getOutputStream();
        stream.write(out);
        int respuesta = http.getResponseCode();
        http.disconnect();
        return respuesta;
    }

    public static int deleteJSON(int id) throws IOException {
        url = new URL(sitio + "clientes/" + id);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("DELETE");
        int respuesta = http.getResponseCode();
        http.disconnect();
        return respuesta;
    }

    public static int updateJSON(int id, ClienteDTO cliente) throws IOException {
        url = new URL(sitio + "clientes/" + id);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("PUT");
        http.setDoOutput(true);
        http.setRequestProperty("Accept", "application/json");
        http.setRequestProperty("Content-Type", "application/json");

        String data = "{"
                + "\"idCliente\":" + id + ","
                + "\"nombre\":\"" + cliente.getNombre() + "\","
                + "\"documento\":\"" + cliente.getDocumento() + "\","
                + "\"email\":\"" + cliente.getCorreo() + "\","
                + "\"telefono\":\"" + cliente.getTelefono() + "\","
                + "\"fechaRegistro\":\"" + cliente.getFechaRegistro() + "\""
                + "}";

        byte[] out = data.getBytes(StandardCharsets.UTF_8);
        OutputStream stream = http.getOutputStream();
        stream.write(out);
        int respuesta = http.getResponseCode();
        http.disconnect();
        return respuesta;
    }
    
    public static ClienteDTO getJSONById(int id) throws IOException, ParseException {
        url = new URL(sitio + "clientes/" + id);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("GET");
        http.setRequestProperty("Accept", "application/json");
        
        int responseCode = http.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            InputStream respuesta = http.getInputStream();
            byte[] inp = respuesta.readAllBytes();
            String json = new String(inp, StandardCharsets.UTF_8);
            
            // Parsear el JSON individual
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(json);
            ClienteDTO cliente = new ClienteDTO();
            
            Object idObj = obj.get("idCliente");
            Object nombre = obj.get("nombre");
            Object documento = obj.get("docId");
            Object correo = obj.get("email");
            Object telefono = obj.get("telefono");
            Object fechaRegistro = obj.get("fechaRegistro");

            if (idObj != null) cliente.setIdCliente(Integer.parseInt(idObj.toString()));
            if (nombre != null) cliente.setNombre(nombre.toString());
            if (documento != null) cliente.setDocumento(documento.toString());
            if (correo != null) cliente.setCorreo(correo.toString());
            if (telefono != null) cliente.setTelefono(telefono.toString());
            if (fechaRegistro != null) cliente.setFechaRegistro(LocalDate.parse(fechaRegistro.toString()));
            
            http.disconnect();
            return cliente;
        } else {
            http.disconnect();
            return null;
        }
    }
}
