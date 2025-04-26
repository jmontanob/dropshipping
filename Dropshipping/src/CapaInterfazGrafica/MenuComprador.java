package CapaInterfazGrafica;

import CapaLogica.Producto;
import CapaLogica.GestorProductos;
import CapaLogica.GestorPedidos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Menú para el comprador.
 */
public class MenuComprador extends JFrame {

    public MenuComprador() {
        setTitle("Menú Comprador");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel label = new JLabel("Bienvenido Comprador", SwingConstants.CENTER);
        label.setBounds(50, 20, 300, 30);
        add(label);

        JButton btnVerProductos = new JButton("Ver Productos");
        btnVerProductos.setBounds(100, 70, 200, 30);
        add(btnVerProductos);

        btnVerProductos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GestorProductos gestor = new GestorProductos();
                List<Producto> productos = gestor.obtenerProductos();

                StringBuilder listado = new StringBuilder("Productos:\n");
                for (Producto p : productos) {
                    listado.append(p.getNombre())
                            .append(" - $").append(p.getPrecio()).append("\n");
                }

                JOptionPane.showMessageDialog(null, listado.toString());
            }
        });

        GestorProductos gestorProductos = new GestorProductos();
        GestorPedidos gestorPedidos = new GestorPedidos();

        // Carga de productos simulada
        gestorProductos.agregarProducto("Laptop", 1000);
        gestorProductos.agregarProducto("Mouse", 25);
        gestorProductos.agregarProducto("Teclado", 45);

        JButton btnNuevoPedido = new JButton("Nuevo Pedido");
        btnNuevoPedido.setBounds(100, 110, 200, 30);
        add(btnNuevoPedido);

        btnNuevoPedido.addActionListener(e -> {
            CrearPedidoFrame frame = new CrearPedidoFrame(gestorProductos, gestorPedidos);
            frame.setVisible(true);
        });
    }
}