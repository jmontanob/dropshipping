package CapaLogica;

public class Administrador extends Usuario {
    public Administrador(String nombreUsuario, String nombreCompleto, String cedulaIdentidad, String fechaNacimiento, String correoElectronico, String password) {
        super(nombreUsuario, nombreCompleto, cedulaIdentidad, fechaNacimiento, correoElectronico, password);
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Administrador: " + nombreCompleto);
    }
}