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
    public void agregarProducto(String nombre, double precio) {
        productos.add(new Producto(nombre, precio));
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
}