package CapaLogica;

/**
  Representa un comprador en la tienda.
  Hereda de Usuario e implementa el método mostrarInformacion().
 **/
public class Comprador extends Usuario {
    public Comprador(String nombreUsuario, String nombreCompleto, String cedulaIdentidad, String fechaNacimiento, String correoElectronico, String password) {
        super(nombreUsuario, nombreCompleto, cedulaIdentidad, fechaNacimiento, correoElectronico, password);
    }

    /**
      Muestra información del comprador.
    **/
    @Override
    public void mostrarInformacion() {
        System.out.println("Comprador: " + nombreCompleto + " - " + correoElectronico);
    }
}
