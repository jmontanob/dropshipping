package CapaLogica;

/**
 * Representa un producto en la tienda.
 */

public class Producto {
    private String nombre;
    private String categoria;
    private double precio;
    private double peso;
    private String dimensiones;
    private int inventarioDisponible;
    private Vendedor vendedor;

    public Producto(String nombre, String categoria, double precio, double peso, String dimensiones, int inventarioDisponible, Vendedor vendedor) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.peso = peso;
        this.dimensiones = dimensiones;
        this.inventarioDisponible = inventarioDisponible;
        this.vendedor = vendedor;
    }

    public String getNombre() { return nombre; }
    public String getCategoria() { return categoria; }
    public double getPrecio() { return precio; }
    public double getPeso() { return peso; }
    public String getDimensiones() { return dimensiones; }
    public int getInventarioDisponible() { return inventarioDisponible; }
    public Vendedor getVendedor() { return vendedor; }

    public void setInventarioDisponible(int cantidad) { this.inventarioDisponible = cantidad; }

    @Override
    public String toString() {
        return nombre + " - " + categoria + " - $" + precio + " - Stock: " + inventarioDisponible;
    }
}
