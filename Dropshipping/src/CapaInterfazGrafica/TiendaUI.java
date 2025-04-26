package CapaInterfazGrafica;

import CapaLogica.*;

/**
 * Clase principal de la aplicación.
 * Lanza la interfaz de login y gestiona el flujo inicial.
 */
public class TiendaUI {

    public static void main(String[] args) {
        // Instancias compartidas de lógica
        GestorProductos gestorProductos = new GestorProductos();
        GestorPedidos gestorPedidos = new GestorPedidos();

        // Lanza la ventana de login, sin datos precargados
        LoginFrame login = new LoginFrame(gestorProductos, gestorPedidos);
        login.setVisible(true);
    }
}