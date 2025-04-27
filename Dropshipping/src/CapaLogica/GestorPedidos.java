package CapaLogica;

import CapaDAO.*;
import java.sql.SQLException;
import java.util.List;

public class GestorPedidos {

    private DAOPedido daoPedido;

    public GestorPedidos() throws SQLException {
        this.daoPedido = new DAOPedido();
    }

    public void agregarPedido(Pedido pedido) throws SQLException {
        daoPedido.guardarPedido(pedido);
    }

    public List<Pedido> obtenerPedidosPorComprador(String nombreUsuario) throws SQLException {
        return daoPedido.obtenerPedidosPorComprador(nombreUsuario);  // Aquí se pasa nombreUsuario como String
    }

    public List<Pedido> obtenerTodosLosPedidos() throws SQLException {
        return daoPedido.obtenerTodosLosPedidos();
    }
}