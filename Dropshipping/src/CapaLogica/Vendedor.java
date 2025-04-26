package CapaLogica;

public class Vendedor extends Usuario {
    public Vendedor(String nombreUsuario, String nombreCompleto, String cedulaIdentidad, String fechaNacimiento, String correoElectronico, String password) {
        super(nombreUsuario, nombreCompleto, cedulaIdentidad, fechaNacimiento, correoElectronico, password);
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Comprador: " + nombreCompleto + " - " + correoElectronico);
    }
}