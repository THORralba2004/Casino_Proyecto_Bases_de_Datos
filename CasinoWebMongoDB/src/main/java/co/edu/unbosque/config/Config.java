package co.edu.unbosque.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    
    private static Properties propiedades = new Properties();
    
    static {
        cargarConfiguracion();
    }
    
    private static void cargarConfiguracion() {
        try (InputStream input = Config.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input != null) {
                propiedades.load(input);
            } else {
                // Valor por defecto si no encuentra el archivo
                propiedades.setProperty("backend.port", "8089");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            // Valor por defecto en caso de error
            propiedades.setProperty("backend.port", "8089");
        }
    }
    
    public static String getBackendPort() {
        return propiedades.getProperty("backend.port", "8089");
    }
    
    public static String getBaseUrl() {
        return "http://localhost:" + getBackendPort();
    }
}