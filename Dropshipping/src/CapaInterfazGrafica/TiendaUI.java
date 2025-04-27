package CapaInterfazGrafica;

import CapaLogica.*;

import java.sql.SQLException;


public class TiendaUI {

    public static void main(String[] args) throws SQLException {

        GestorProductos gestorProductos = new GestorProductos();
        GestorPedidos gestorPedidos = new GestorPedidos();

        LoginFrame login = new LoginFrame(gestorProductos, gestorPedidos);
        login.setVisible(true);
    }
}