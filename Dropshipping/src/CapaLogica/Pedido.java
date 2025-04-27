package CapaLogica;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa un pedido realizado en la tienda.
 */
public class Pedido {
    private static int contadorId = 1;

    private int idPedido;
    private Comprador cliente;
    private List<Producto> productos;
    private double total;

    public Pedido(Comprador cliente) {
        this.idPedido = contadorId++;
        this.cliente = cliente;
        this.productos = new ArrayList<>();
        this.total = 0.0;
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
        total += producto.getPrecio();
    }

    public int getIdPedido() { return idPedido; }
    public Comprador getCliente() { return cliente; }
    public List<Producto> getProductos() { return productos; }
    public double getTotal() { return total; }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Pedido ID: " + idPedido + " - Cliente: " + cliente.getNombreCompleto() + " - Total: $" + total;
    }
}