package CapaLogica;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import CapaDAO.DAOProducto;

public class GestorProductos {

    private List<Producto> productos;

    public GestorProductos() throws SQLException {
        productos = new ArrayList<>();
        cargarProductos(); // Cargar los productos desde la base de datos al iniciar
    }

    /**
     * Agrega un nuevo producto a la lista y la base de datos.
     */
    public void agregarProducto(String nombre, String categoria, double precio, double peso, String dimensiones, int inventarioDisponible, Vendedor vendedor) {
        Producto nuevoProducto = new Producto(nombre, categoria, precio, peso, dimensiones, inventarioDisponible, vendedor);
        try {
            // Llamar al DAOProducto para guardar el producto en la base de datos
            DAOProducto daoProducto = new DAOProducto();
            daoProducto.agregarProducto(nuevoProducto);  // Guardar el producto en la base de datos
            productos.add(nuevoProducto);  // También agregarlo a la lista en memoria
        } catch (SQLException e) {
            System.err.println("Error al agregar producto a la base de datos: " + e.getMessage());
        }
    }

    /**
     * Carga todos los productos desde la base de datos.
     */
    public void cargarProductos() throws SQLException {
        DAOProducto daoProducto = new DAOProducto();
        try {
            productos = daoProducto.obtenerTodosLosProductos();  // Obtener todos los productos de la base de datos
        } catch (SQLException e) {
            System.err.println("Error al cargar los productos desde la base de datos: " + e.getMessage());
        }
    }

    /**
     * Devuelve todos los productos registrados.
     */
    public List<Producto> obtenerProductos() {
        return productos;
    }

    /**
     * Busca un producto por nombre (simple).
     */
    public Producto buscarProducto(String nombre) {
        for (Producto p : productos) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Devuelve todos los productos de un vendedor específico.
     */
    public List<Producto> obtenerProductosPorVendedor(String nombreUsuarioVendedor) {
        List<Producto> productosDelVendedor = new ArrayList<>();
        for (Producto p : productos) {
            if (p.getVendedor().getNombreUsuario().equals(nombreUsuarioVendedor)) {
                productosDelVendedor.add(p);
            }
        }
        return productosDelVendedor;
    }
}