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

    public String getNombreUsuario() { return nombreUsuario; }
    public String getNombreCompleto() { return nombreCompleto; }
    public String getCedulaIdentidad() { return cedulaIdentidad; }
    public String getFechaNacimiento() { return fechaNacimiento; }
    public String getCorreoElectronico() { return correoElectronico; }
    public String getPassword() { return password; }

    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }
    public void setCedulaIdentidad(String cedulaIdentidad) { this.cedulaIdentidad = cedulaIdentidad; }
    public void setFechaNacimiento(String fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
    public void setCorreoElectronico(String correoElectronico) { this.correoElectronico = correoElectronico; }
    public void setPassword(String password) { this.password = password; }

    public abstract void mostrarInformacion();
}

