package CapaLogica;

import CapaDAO.DAOUsuario;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GestorUsuarios {

    private List<Usuario> listaUsuarios;

    public GestorUsuarios() {
        listaUsuarios = new ArrayList<>();
        cargarUsuariosDesdeBD();
    }

    private void cargarUsuariosDesdeBD() {
        try {
            DAOUsuario dao = new DAOUsuario();
            listaUsuarios = dao.obtenerTodosLosUsuarios();
            dao.cerrarConexion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public Usuario obtenerUsuarioPorNombre(String nombreUsuario) {
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getNombreUsuario().equals(nombreUsuario)) {
                return usuario;
            }
        }
        return null;
    }

    public void actualizarUsuario(Usuario usuarioActualizado) {
        try {
            DAOUsuario dao = new DAOUsuario();
            dao.actualizarUsuario(usuarioActualizado);
            dao.cerrarConexion();
            cargarUsuariosDesdeBD(); // Recargar la lista actualizada
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarUsuario(String nombreUsuario) {
        try {
            DAOUsuario dao = new DAOUsuario();
            dao.eliminarUsuario(nombreUsuario);
            dao.cerrarConexion();
            listaUsuarios.removeIf(u -> u.getNombreUsuario().equals(nombreUsuario));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}