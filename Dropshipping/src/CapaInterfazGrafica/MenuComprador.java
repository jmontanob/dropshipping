package CapaInterfazGrafica;

import CapaLogica.GestorProductos;
import CapaLogica.GestorPedidos;
import CapaLogica.Producto;
import CapaLogica.Vendedor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Menú para el comprador.
 */
public class MenuComprador extends JFrame {

    private GestorProductos gestorProductos;
    private GestorPedidos gestorPedidos;

    // Constructor modificado para aceptar los parámetros
    public MenuComprador(GestorProductos gestorProductos, GestorPedidos gestorPedidos) {
        this.gestorProductos = gestorProductos;
        this.gestorPedidos = gestorPedidos;

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
                List<Producto> productos = gestorProductos.obtenerProductos();

                StringBuilder listado = new StringBuilder("Productos:\n");
                for (Producto p : productos) {
                    listado.append(p.getNombre())
                            .append(" - $").append(p.getPrecio()).append("\n");
                }

                JOptionPane.showMessageDialog(null, listado.toString());
            }
        });

        JButton btnNuevoPedido = new JButton("Nuevo Pedido");
        btnNuevoPedido.setBounds(100, 110, 200, 30);
        add(btnNuevoPedido);

        btnNuevoPedido.addActionListener(e -> {
            CrearPedidoFrame frame = new CrearPedidoFrame(gestorProductos, gestorPedidos);
            frame.setVisible(true);
        });

        JButton btnCerrar = new JButton("Cerrar Sesión");
        btnCerrar.setBounds(100, 150, 200, 30);
        add(btnCerrar);

        btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LoginFrame(gestorProductos, gestorPedidos).setVisible(true);
            }
        });


    }
}