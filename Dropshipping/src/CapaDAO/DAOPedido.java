package CapaDAO;

import CapaLogica.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOPedido {

    private Connection connection;

    public DAOPedido() throws SQLException {
        Configuracion config = new Configuracion();
        this.connection = DriverManager.getConnection(config.getStringConexion());
    }

    public void guardarPedido(Pedido pedido) throws SQLException {
        String query = "INSERT INTO pedidos (idComprador, total) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, pedido.getCliente().getNombreUsuario());
            stmt.setDouble(2, pedido.getTotal());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int idPedido = generatedKeys.getInt(1);
                    }
                }
            }
        }
    }

    public List<Pedido> obtenerPedidosPorComprador(String nombreUsuario) throws SQLException {
        String query = "SELECT * FROM pedidos WHERE idComprador = (SELECT id FROM usuarios WHERE nombreUsuario = ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nombreUsuario);
            ResultSet rs = stmt.executeQuery();

            List<Pedido> pedidos = new ArrayList<>();
            while (rs.next()) {
                int idPedido = rs.getInt("idPedido");
                Comprador comprador = obtenerCompradorPorNombreUsuario(nombreUsuario);
                Pedido pedido = new Pedido(comprador);
                pedidos.add(pedido);
            }
            return pedidos;
        }
    }

    public List<Pedido> obtenerTodosLosPedidos() throws SQLException {
        String query = "SELECT * FROM pedidos";  // Aquí obtenemos todos los pedidos
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();

            List<Pedido> pedidos = new ArrayList<>();
            while (rs.next()) {
                int idPedido = rs.getInt("idPedido");
                String nombreUsuario = rs.getString("idComprador");
                Comprador comprador = obtenerCompradorPorNombreUsuario(nombreUsuario);
                Pedido pedido = new Pedido(comprador);
                pedidos.add(pedido);
            }
            return pedidos;
        }
    }

    public Comprador obtenerCompradorPorNombreUsuario(String nombreUsuario) throws SQLException {
        String query = "SELECT * FROM usuarios WHERE nombreUsuario = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nombreUsuario);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String nombreCompleto = rs.getString("nombreCompleto");
                String cedulaIdentidad = rs.getString("cedulaIdentidad");
                String fechaNacimiento = rs.getString("fechaNacimiento");
                String correoElectronico = rs.getString("correoElectronico");
                String password = rs.getString("password");
                String rol = rs.getString("rol");

                return new Comprador(nombreUsuario, nombreCompleto, cedulaIdentidad, fechaNacimiento, correoElectronico, password);
            }
        }
        return null; // Si no se encuentra el comprador
    }

    public void cerrarConexion() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}