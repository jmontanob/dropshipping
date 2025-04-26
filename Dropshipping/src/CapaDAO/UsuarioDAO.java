package CapaDAO;

import CapaLogica.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public void agregarUsuario(Usuario usuario) {
        String sql = "INSERT INTO Usuarios (nombreUsuario, nombreCompleto, cedulaIdentidad, fechaNacimiento, correoElectronico, password, tipo) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, usuario.getNombreUsuario());
            pstmt.setString(2, usuario.getNombreCompleto());
            pstmt.setString(3, usuario.getCedulaIdentidad());
            pstmt.setDate(4, Date.valueOf(usuario.getFechaNacimiento()));
            pstmt.setString(5, usuario.getCorreoElectronico());
            pstmt.setString(6, usuario.getPassword());
            pstmt.setString(7, usuario instanceof Administrador ? "Administrador" : "Comprador");
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Usuario> obtenerUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM Usuarios";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                // Aquí deberías crear un objeto Usuario y agregarlo a la lista
                // Por simplicidad, no se implementa la lógica de creación de Usuario
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }
}