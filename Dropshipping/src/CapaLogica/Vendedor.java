package CapaLogica;

/**
  Representa un vendedor en la tienda.
  No hereda de Usuario, ya que tiene atributos distintos y no requiere autenticación.
 **/
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

    /**
      Devuelve una representación en texto del vendedor.
     **/
    @Override
    public String toString() {
        return "Vendedor: " + nombre + " - " + ubicacion;
    }
}
