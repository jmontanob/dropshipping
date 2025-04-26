package CapaInterfazGrafica;

import CapaLogica.GestorPedidos;
import CapaLogica.GestorProductos;
import CapaLogica.Producto;
import CapaLogica.Vendedor;

/**
 * Clase principal que inicia la aplicación.
 */
public class TiendaUI {

    public static void main(String[] args) {
        // Crear gestores compartidos
        GestorProductos gestorProductos = new GestorProductos();
        GestorPedidos gestorPedidos = new GestorPedidos();

        // Cargar productos simulados (opcional)
        Vendedor vendedorPrueba = new Vendedor("Carlos", "Ventas Latam", "carlos@tienda.com");

        gestorProductos.agregarProducto(
                new Producto("Laptop", "Electrónica", 1000, 2.5, "35x25x2 cm", 10, vendedorPrueba)
        );
        gestorProductos.agregarProducto(
                new Producto("Audífonos", "Accesorios", 50, 0.2, "15x15x5 cm", 20, vendedorPrueba)
        );

        // Iniciar el login, pasando los gestores
        LoginFrame login = new LoginFrame(gestorProductos, gestorPedidos);
        login.setVisible(true);
    }
}