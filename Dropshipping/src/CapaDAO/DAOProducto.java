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
        String sql = "INSERT INTO productos (nombre, categoria, precio, peso, dimensiones, inventarioDisponible, nombreUsuarioVendedor) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, producto.getNombre());
            stmt.setString(2, producto.getCategoria());
            stmt.setDouble(3, producto.getPrecio());
            stmt.setDouble(4, producto.getPeso());
            stmt.setString(5, producto.getDimensiones());
            stmt.setInt(6, producto.getInventarioDisponible());
            stmt.setString(7, producto.getVendedor().getNombreUsuario());
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
                Producto producto = crearProductoDesdeResultSet(rs);
                if (producto != null) {
                    productos.add(producto);
                }
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
                return crearProductoDesdeResultSet(rs);
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

    // Obtener productos de un vendedor específico (que tengan inventario)
    public List<Producto> obtenerProductosPorVendedor(String nombreUsuarioVendedor) throws SQLException {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM productos WHERE nombreUsuarioVendedor = ? AND inventarioDisponible > 0";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombreUsuarioVendedor);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Producto producto = crearProductoDesdeResultSet(rs);
                if (producto != null) {
                    productos.add(producto);
                }
            }
        }
        return productos;
    }

    // Método privado para construir un producto a partir de un ResultSet
    private Producto crearProductoDesdeResultSet(ResultSet rs) throws SQLException {
        String nombre = rs.getString("nombre");
        String categoria = rs.getString("categoria");
        double precio = rs.getDouble("precio");
        double peso = rs.getDouble("peso");
        String dimensiones = rs.getString("dimensiones");
        int inventarioDisponible = rs.getInt("inventarioDisponible");
        String nombreUsuarioVendedor = rs.getString("nombreUsuarioVendedor");

        // Crear el vendedor asociado (solo con el nombre de usuario, lo demás podría ser null)
        Vendedor vendedor = new Vendedor(nombreUsuarioVendedor, null, null, null, null, null);

        return new Producto(nombre, categoria, precio, peso, dimensiones, inventarioDisponible, vendedor);
    }

    // Cerrar la conexión
    public void cerrarConexion() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }
}