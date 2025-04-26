package CapaInterfazGrafica;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Menú para el administrador.
 */
public class MenuAdministrador extends JFrame {

    public MenuAdministrador() {
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

        JButton btnVerPedidos = new JButton("Ver Pedidos");
        btnVerPedidos.setBounds(100, 110, 200, 30);
        add(btnVerPedidos);

        btnVerPedidos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Aquí se mostraría la lista de pedidos (por implementar)");
            }
        });
    }
}