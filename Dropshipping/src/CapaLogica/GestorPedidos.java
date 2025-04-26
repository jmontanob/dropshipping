package CapaLogica;

import java.util.ArrayList;
import java.util.List;

/**
 * Controlador para pedidos.
 */
public class GestorPedidos {

    private List<Pedido> pedidos;

    public GestorPedidos() {
        pedidos = new ArrayList<>();
    }

    public void agregarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public List<Pedido> obtenerPedidos() {
        return pedidos;
    }

    public String obtenerResumenPedidos() {
        StringBuilder sb = new StringBuilder();
        for (Pedido p : pedidos) {
            sb.append(p.toString()).append("\n");
        }
        return sb.toString();
    }
}