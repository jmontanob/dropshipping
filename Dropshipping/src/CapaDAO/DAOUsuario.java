package CapaDAO;

import CapaLogica.*;
import java.sql.*;

public class DAOUsuario {
    private Connection conn;

    public DAOUsuario() throws SQLException {
        // Usamos la configuración para obtener los valores de la base de datos
        Configuracion config = new Configuracion();
        String url = config.getStringConexion();  // Obtenemos la URL de conexión
        conn = DriverManager.getConnection(url);  // Ya incluye usuario y password en la URL
    }

    public void registrarUsuario(Usuario usuario, String rol) throws SQLException {
        String sql = "INSERT INTO usuarios (nombreUsuario, nombreCompleto, cedulaIdentidad, fechaNacimiento, correoElectronico, password, rol) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, usuario.getNombreUsuario());
        stmt.setString(2, usuario.getNombreCompleto());
        stmt.setString(3, usuario.getCedulaIdentidad());
        stmt.setString(4, usuario.getFechaNacimiento());
        stmt.setString(5, usuario.getCorreoElectronico());
        stmt.setString(6, usuario.getPassword());
        stmt.setString(7, rol);
        stmt.executeUpdate();
        stmt.close();
    }

    public Usuario buscarUsuario(String nombreUsuario) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE nombreUsuario = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, nombreUsuario);
        ResultSet rs = stmt.executeQuery();

        Usuario usuario = null;

        if (rs.next()) {
            String nombreCompleto = rs.getString("nombreCompleto");
            String cedula = rs.getString("cedulaIdentidad");
            String nacimiento = rs.getString("fechaNacimiento");
            String correo = rs.getString("correoElectronico");
            String password = rs.getString("password");
            String rol = rs.getString("rol");

            switch (rol) {
                case "Administrador":
                    usuario = new Administrador(nombreUsuario, nombreCompleto, cedula, nacimiento, correo, password);
                    break;
                case "Vendedor":
                    usuario = new Vendedor(nombreUsuario, nombreCompleto, cedula, nacimiento, correo, password);
                    break;
                case "Comprador":
                    usuario = new Comprador(nombreUsuario, nombreCompleto, cedula, nacimiento, correo, password);
                    break;
            }
        }

        rs.close();
        stmt.close();
        return usuario;
    }

    public String obtenerRolUsuario(String nombreUsuario) throws SQLException {
        String sql = "SELECT rol FROM usuarios WHERE nombreUsuario = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, nombreUsuario);
        ResultSet rs = stmt.executeQuery();

        String rol = null;
        if (rs.next()) {
            rol = rs.getString("rol");
        }

        rs.close();
        stmt.close();
        return rol;
    }

    public void cerrarConexion() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }
}