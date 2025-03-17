package CapaLogica;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa un pedido realizado en la tienda.
 */

public class Pedido {
    private int idPedido;
    private Comprador cliente;
    private List<Producto> productos;
    private double total;

    public Pedido(int idPedido, Comprador cliente) {
        this.idPedido = idPedido;
        this.cliente = cliente;
        this.productos = new ArrayList<>();
        this.total = 0.0;
    }

    public int getIdPedido() { return idPedido; }
    public Comprador getCliente() { return cliente; }
    public List<Producto> getProductos() { return productos; }
    public double getTotal() { return total; }

    @Override
    public String toString() {
        return "Pedido ID: " + idPedido + " - Cliente: " + cliente.getNombreCompleto() + " - Total: $" + total;
    }
}