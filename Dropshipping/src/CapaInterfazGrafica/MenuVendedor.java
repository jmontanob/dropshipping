package CapaInterfazGrafica;

import CapaLogica.GestorPedidos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Menú para el vendedor.
 */
public class MenuVendedor extends JFrame {

    public MenuVendedor() {
        setTitle("Menú Vendedor");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel label = new JLabel("Bienvenido Vendedor", SwingConstants.CENTER);
        label.setBounds(50, 20, 300, 30);
        add(label);

        JButton btnListarPedidos = new JButton("Listar Pedidos");
        btnListarPedidos.setBounds(100, 70, 200, 30);
        add(btnListarPedidos);

        btnListarPedidos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GestorPedidos gestor = new GestorPedidos();
                String resumen = gestor.obtenerResumenPedidos();
                JOptionPane.showMessageDialog(null, resumen);
            }
        });

        GestorPedidos gestorPedidos = new GestorPedidos(); // Puedes inyectar si lo usas desde Login

        JButton btnVerPedidos = new JButton("Ver Pedidos");
        btnVerPedidos.setBounds(100, 110, 200, 30);
        add(btnVerPedidos);

        btnVerPedidos.addActionListener(e -> {
            VerPedidosFrame frame = new VerPedidosFrame(gestorPedidos);
            frame.setVisible(true);
        });

    }
}