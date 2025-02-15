package CapaLogica;

public abstract class Usuario {
    protected String nombreUsuario;
    protected String nombreCompleto;
    protected String cedulaIdentidad;
    protected String fechaNacimiento;
    protected String correoElectronico;
    protected String password;

    public Usuario(String nombreUsuario, String nombreCompleto, String cedulaIdentidad, String fechaNacimiento, String correoElectronico, String password) {
        this.nombreUsuario = nombreUsuario;
        this.nombreCompleto = nombreCompleto;
        this.cedulaIdentidad = cedulaIdentidad;
        this.fechaNacimiento = fechaNacimiento;
        this.correoElectronico = correoElectronico;
        this.password = password;
    }

    public abstract void mostrarInformacion();
}
