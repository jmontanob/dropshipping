package CapaLogica;

/**
  Clase abstracta base para los usuarios de la tienda.
  Define atributos comunes a todos los usuarios y un método abstracto.
 **/
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

    public String getNombreUsuario() { return nombreUsuario; }
    public String getNombreCompleto() { return nombreCompleto; }
    public String getCedulaIdentidad() { return cedulaIdentidad; }
    public String getFechaNacimiento() { return fechaNacimiento; }
    public String getCorreoElectronico() { return correoElectronico; }
    public String getPassword() { return password; }

    /**
      Método abstracto que debe ser implementado por las clases hijas.
      Su propósito es mostrar información específica del usuario.
     **/
    public abstract void mostrarInformacion();
}

