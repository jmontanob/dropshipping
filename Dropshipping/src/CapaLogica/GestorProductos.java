package CapaLogica;

import java.util.ArrayList;
import java.util.List;

/**
 * Controlador para gestionar productos.
 */
public class GestorProductos {

    private List<Producto> productos;

    public GestorProductos() {
        productos = new ArrayList<>();
    }

    /**
     * Agrega un nuevo producto a la lista.
     */
    public void agregarProducto(String nombre, String categoria, double precio, double peso, String dimensiones, int inventarioDisponible, Vendedor vendedor) {
        productos.add(new Producto(nombre, categoria, precio, peso, dimensiones, inventarioDisponible, vendedor));
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