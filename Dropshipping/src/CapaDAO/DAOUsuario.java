package CapaDAO;

import CapaLogica.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOUsuario {
    private Connection conn;

    public DAOUsuario() throws SQLException {
        Configuracion config = new Configuracion();
        String url = config.getStringConexion();
        conn = DriverManager.getConnection(url);
    }

    public void registrarUsuario(Usuario usuario, String rol) throws SQLException {
        String sql = "INSERT INTO usuarios (nombreUsuario, nombreCompleto, cedulaIdentidad, fechaNacimiento, correoElectronico, password, rol) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNombreUsuario());
            stmt.setString(2, usuario.getNombreCompleto());
            stmt.setString(3, usuario.getCedulaIdentidad());
            stmt.setString(4, usuario.getFechaNacimiento());
            stmt.setString(5, usuario.getCorreoElectronico());
            stmt.setString(6, usuario.getPassword());
            stmt.setString(7, rol);
            stmt.executeUpdate();
        }
    }

    public Usuario buscarUsuario(String nombreUsuario) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE nombreUsuario = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombreUsuario);
            ResultSet rs = stmt.executeQuery();

            Usuario usuario = null;
            if (rs.next()) {
                usuario = crearUsuarioDesdeResultSet(rs);
            }
            rs.close();
            return usuario;
        }
    }

    public String obtenerRolUsuario(String nombreUsuario) throws SQLException {
        String sql = "SELECT rol FROM usuarios WHERE nombreUsuario = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombreUsuario);
            ResultSet rs = stmt.executeQuery();
            String rol = null;
            if (rs.next()) {
                rol = rs.getString("rol");
            }
            rs.close();
            return rol;
        }
    }

    public void actualizarUsuario(Usuario usuario) throws SQLException {
        String sql = "UPDATE usuarios SET nombreCompleto = ?, cedulaIdentidad = ?, fechaNacimiento = ?, correoElectronico = ?, password = ? WHERE nombreUsuario = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNombreCompleto());
            stmt.setString(2, usuario.getCedulaIdentidad());
            stmt.setString(3, usuario.getFechaNacimiento());
            stmt.setString(4, usuario.getCorreoElectronico());
            stmt.setString(5, usuario.getPassword());
            stmt.setString(6, usuario.getNombreUsuario());
            stmt.executeUpdate();
        }
    }

    public void eliminarUsuario(String nombreUsuario) throws SQLException {
        String sql = "DELETE FROM usuarios WHERE nombreUsuario = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombreUsuario);
            stmt.executeUpdate();
        }
    }

    public List<Usuario> obtenerTodosLosUsuarios() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = crearUsuarioDesdeResultSet(rs);
                if (usuario != null) {
                    usuarios.add(usuario);
                }
            }
        }
        return usuarios;
    }

    private Usuario crearUsuarioDesdeResultSet(ResultSet rs) throws SQLException {
        String nombreUsuario = rs.getString("nombreUsuario");
        String nombreCompleto = rs.getString("nombreCompleto");
        String cedula = rs.getString("cedulaIdentidad");
        String nacimiento = rs.getString("fechaNacimiento");
        String correo = rs.getString("correoElectronico");
        String password = rs.getString("password");
        String rol = rs.getString("rol");

        switch (rol) {
            case "Administrador":
                return new Administrador(nombreUsuario, nombreCompleto, cedula, nacimiento, correo, password);
            case "Vendedor":
                return new Vendedor(nombreUsuario, nombreCompleto, cedula, nacimiento, correo, password);
            case "Comprador":
                return new Comprador(nombreUsuario, nombreCompleto, cedula, nacimiento, correo, password);
            default:
                return null;
        }
    }

    public void cerrarConexion() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }
}