package CapaLogica;

public class Vendedor {
    private String nombre;
    private String ubicacion;
    private String correoContacto;
    private String numeroTelefono;

    public Vendedor(String nombre, String ubicacion, String correoContacto, String numeroTelefono) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.correoContacto = correoContacto;
        this.numeroTelefono = numeroTelefono;
    }

    public String getNombre() { return nombre; }
    public String getUbicacion() { return ubicacion; }
    public String getCorreoContacto() { return correoContacto; }
    public String getNumeroTelefono() { return numeroTelefono; }

    @Override
    public String toString() {
        return "Vendedor: " + nombre + " - " + ubicacion;
    }
}