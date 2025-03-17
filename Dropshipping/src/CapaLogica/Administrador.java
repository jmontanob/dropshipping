package CapaLogica;

/**
  Representa un administrador del sistema.
  Hereda de Usuario e implementa el método mostrarInformacion().
 **/
public class Administrador extends Usuario {
    public Administrador(String nombreUsuario, String nombreCompleto, String cedulaIdentidad, String fechaNacimiento, String correoElectronico, String password) {
        super(nombreUsuario, nombreCompleto, cedulaIdentidad, fechaNacimiento, correoElectronico, password);
    }

     /**
      Muestra información del administrador.
     **/
    @Override
    public void mostrarInformacion() {
        System.out.println("Administrador: " + nombreCompleto);
    }
}
