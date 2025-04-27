package CapaInterfazGrafica;

import CapaLogica.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Menú para el administrador.
 */
public class MenuAdministrador extends JFrame {

    private GestorProductos gestorProductos;
    private GestorPedidos gestorPedidos;

    // Constructor que recibe los objetos GestorProductos y GestorPedidos
    public MenuAdministrador(GestorProductos gestorProductos, GestorPedidos gestorPedidos) {
        this.gestorProductos = gestorProductos;
        this.gestorPedidos = gestorPedidos;

        setTitle("Menú Administrador");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel label = new JLabel("Bienvenido Administrador", SwingConstants.CENTER);
        label.setBounds(50, 20, 300, 30);
        add(label);

        JButton btnGestionarUsuarios = new JButton("Gestionar Usuarios");
        btnGestionarUsuarios.setBounds(100, 70, 200, 30);
        add(btnGestionarUsuarios);

        btnGestionarUsuarios.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GestionUsuariosFrame frame = new GestionUsuariosFrame(new GestorUsuarios());
                frame.setVisible(true);
            }
        });

        JButton btnVerPedidos = new JButton("Ver Pedidos");
        btnVerPedidos.setBounds(100, 110, 200, 30);
        add(btnVerPedidos);

        btnVerPedidos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VerPedidosFrame verPedidos = new VerPedidosFrame(gestorPedidos);
                verPedidos.setVisible(true);
            }
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