// EmpleadoJSON.java
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
import co.edu.unbosque.model.EmpleadoDTO;

public class EmpleadoJSON {

    private static URL url;
    private static String sitio = Config.getBaseUrl() + "/";

    // ✅ Consultar todos los empleados
    public static ArrayList<EmpleadoDTO> getJSON() throws IOException, ParseException {
        url = new URL(sitio + "empleados");
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("GET");
        http.setRequestProperty("Accept", "application/json");

        InputStream respuesta = http.getInputStream();
        byte[] inp = respuesta.readAllBytes();
        String json = new String(inp, StandardCharsets.UTF_8);

        ArrayList<EmpleadoDTO> lista = parsingEmpleados(json);
        http.disconnect();
        return lista;
    }

    // ✅ Parsear JSON → Lista
    public static ArrayList<EmpleadoDTO> parsingEmpleados(String json) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONArray empleados = (JSONArray) parser.parse(json);
        ArrayList<EmpleadoDTO> lista = new ArrayList<>();
        Iterator<?> i = empleados.iterator();

        while (i.hasNext()) {
            JSONObject innerObj = (JSONObject) i.next();
            EmpleadoDTO e = new EmpleadoDTO();

            Object id = innerObj.get("id");
            Object nombre = innerObj.get("nombre");
            Object cargo = innerObj.get("cargo");
            Object salario = innerObj.get("salario");
            Object activo = innerObj.get("activo");

            if (id != null) e.setIdEmpleado(id.toString());
            if (nombre != null) e.setNombre(nombre.toString());
            if (cargo != null) e.setCargo(cargo.toString());
            if (salario != null) e.setSalario(Double.parseDouble(salario.toString()));
            if (activo != null) e.setActivo(activo.toString());

            lista.add(e);
        }
        return lista;
    }

    // ✅ Agregar
    public static int postJSON(EmpleadoDTO empleado) throws IOException {
        url = new URL(sitio + "empleados");
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("Accept", "application/json");
        http.setRequestProperty("Content-Type", "application/json");

        String data = "{"
                + "\"nombre\":\"" + empleado.getNombre() + "\","
                + "\"cargo\":\"" + empleado.getCargo() + "\","
                + "\"salario\":" + empleado.getSalario() + ","
                + "\"activo\":\"" + empleado.getActivo() + "\""
                + "}";

        try (OutputStream os = http.getOutputStream()) {
            os.write(data.getBytes(StandardCharsets.UTF_8));
        }

        int respuesta = http.getResponseCode();
        http.disconnect();
        return respuesta;
    }

    // ✅ Actualizar
    public static int updateJSON(String id, EmpleadoDTO empleado) throws IOException {
        url = new URL(sitio + "empleados/" + id);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("PUT");
        http.setDoOutput(true);
        http.setRequestProperty("Accept", "application/json");
        http.setRequestProperty("Content-Type", "application/json");

        String data = "{"
                + "\"id\":\"" + id + "\","
                + "\"nombre\":\"" + empleado.getNombre() + "\","
                + "\"cargo\":\"" + empleado.getCargo() + "\","
                + "\"salario\":" + empleado.getSalario() + ","
                + "\"activo\":\"" + empleado.getActivo() + "\""
                + "}";

        try (OutputStream os = http.getOutputStream()) {
            os.write(data.getBytes(StandardCharsets.UTF_8));
        }

        int respuesta = http.getResponseCode();
        http.disconnect();
        return respuesta;
    }

    // ✅ Eliminar
    public static int deleteJSON(String id) throws IOException {
        url = new URL(sitio + "empleados/" + id);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("DELETE");
        http.setRequestProperty("Accept", "application/json");

        int respuesta = http.getResponseCode();
        http.disconnect();
        return respuesta;
    }
    
    public static EmpleadoDTO getJSONById(String id) throws IOException, ParseException {
        url = new URL(sitio + "empleados/" + id);
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
            EmpleadoDTO empleado = new EmpleadoDTO();
            
            Object idObj = obj.get("id");
            Object nombre = obj.get("nombre");
            Object cargo = obj.get("cargo");
            Object salario = obj.get("salario");
            Object activo = obj.get("activo");

            if (idObj != null) empleado.setIdEmpleado(idObj.toString());
            if (nombre != null) empleado.setNombre(nombre.toString());
            if (cargo != null) empleado.setCargo(cargo.toString());
            if (salario != null) empleado.setSalario(Double.parseDouble(salario.toString()));
            if (activo != null) empleado.setActivo(activo.toString());
            
            http.disconnect();
            return empleado;
        } else {
            http.disconnect();
            return null;
        }
    }
}