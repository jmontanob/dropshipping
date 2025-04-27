package CapaDAO;

import CapaLogica.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOProducto {
    private Connection conn;

    public DAOProducto() throws SQLException {
        Configuracion config = new Configuracion();
        String url = config.getStringConexion();
        conn = DriverManager.getConnection(url);
    }

    // Agregar un nuevo producto a la base de datos
    public void agregarProducto(Producto producto) throws SQLException {
        String sql = "INSERT INTO productos (nombre, categoria, precio, peso, dimensiones, inventarioDisponible, vendedor_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, producto.getNombre());
            stmt.setString(2, producto.getCategoria());
            stmt.setDouble(3, producto.getPrecio());
            stmt.setDouble(4, producto.getPeso());
            stmt.setString(5, producto.getDimensiones());
            stmt.setInt(6, producto.getInventarioDisponible());
            stmt.setInt(7, producto.getVendedor().getId());
            stmt.executeUpdate();
        }
    }

    // Obtener todos los productos disponibles (con inventario > 0)
    public List<Producto> obtenerProductosConInventario() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM productos WHERE inventarioDisponible > 0";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String categoria = rs.getString("categoria");
                double precio = rs.getDouble("precio");
                double peso = rs.getDouble("peso");
                String dimensiones = rs.getString("dimensiones");
                int inventarioDisponible = rs.getInt("inventarioDisponible");
                int vendedorId = rs.getInt("vendedor_id");

                // Obtener el vendedor asociado al producto
                Vendedor vendedor = obtenerVendedorPorId(vendedorId);

                Producto producto = new Producto(nombre, categoria, precio, peso, dimensiones, inventarioDisponible, vendedor);
                productos.add(producto);
            }
        }
        return productos;
    }

    // Obtener un producto por su nombre
    public Producto obtenerProductoPorNombre(String nombre) throws SQLException {
        String sql = "SELECT * FROM productos WHERE nombre = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String categoria = rs.getString("categoria");
                double precio = rs.getDouble("precio");
                double peso = rs.getDouble("peso");
                String dimensiones = rs.getString("dimensiones");
                int inventarioDisponible = rs.getInt("inventarioDisponible");
                int vendedorId = rs.getInt("vendedor_id");

                Vendedor vendedor = obtenerVendedorPorId(vendedorId);

                return new Producto(nombre, categoria, precio, peso, dimensiones, inventarioDisponible, vendedor);
            }
            return null; // Producto no encontrado
        }
    }

    // Actualizar el inventario de un producto
    public void actualizarInventario(Producto producto, int cantidadVendida) throws SQLException {
        String sql = "UPDATE productos SET inventarioDisponible = inventarioDisponible - ? WHERE nombre = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, cantidadVendida);
            stmt.setString(2, producto.getNombre());
            stmt.executeUpdate();
        }
    }

    // Obtener el vendedor por ID
    private Vendedor obtenerVendedorPorId(int vendedorId) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE id = ? AND rol = 'Vendedor'";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, vendedorId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String nombreUsuario = rs.getString("nombreUsuario");
                String nombreCompleto = rs.getString("nombreCompleto");
                String cedula = rs.getString("cedulaIdentidad");
                String nacimiento = rs.getString("fechaNacimiento");
                String correo = rs.getString("correoElectronico");
                String password = rs.getString("password");

                return new Vendedor(nombreUsuario, nombreCompleto, cedula, nacimiento, correo, password);
            }
            return null;
        }
    }

    public void cerrarConexion() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }
}