package CapaLogica;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Configuracion {
    private String claseJDBC;
    private String stringConexion;

    public String getClaseJDBC() {
        return claseJDBC;
    }

    public void setClaseJDBC(String claseJDBC) {
        this.claseJDBC = claseJDBC;
    }

    public String getStringConexion() {
        return stringConexion;
    }

    public void setStringConexion(String stringConexion) {
        this.stringConexion = stringConexion;
    }

    public Configuracion() {
        //Se ejecuta cuando se instancia la clase
        leerConfiguracion();
    }

    public Configuracion(String claseJDBC, String strinConexion) {
        this.claseJDBC = claseJDBC;
        this.stringConexion = strinConexion;
    }

    public void leerConfiguracion() {
        Properties prop = new Properties();
        try
        {
            FileInputStream fis = new FileInputStream("C:/Users/danim/Documents/GitHub/dropshipping/Dropshipping/src/CapaLogica/config.properties");
            prop.load(fis);
            this.setClaseJDBC(prop.getProperty("claseJDBC"));
            this.setStringConexion(prop.getProperty("stringConexion"));
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}