package CapaInterfazGrafica;

import CapaLogica.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Ventana de inicio de sesión para la aplicación.
 */
public class LoginFrame extends JFrame {

    private JTextField txtCorreo;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    private GestorProductos gestorProductos;
    private GestorPedidos gestorPedidos;

    /**
     * Constructor de LoginFrame que recibe los gestores compartidos.
     */
    public LoginFrame(GestorProductos gestorProductos, GestorPedidos gestorPedidos) {
        this.gestorProductos = gestorProductos;
        this.gestorPedidos = gestorPedidos;

        setTitle("Inicio de Sesión");
        setSize(350, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblCorreo = new JLabel("Correo:");
        lblCorreo.setBounds(30, 30, 80, 25);
        add(lblCorreo);

        txtCorreo = new JTextField();
        txtCorreo.setBounds(120, 30, 180, 25);
        add(txtCorreo);

        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setBounds(30, 70, 80, 25);
        add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(120, 70, 180, 25);
        add(txtPassword);

        btnLogin = new JButton("Iniciar sesión");
        btnLogin.setBounds(100, 120, 130, 30);
        add(btnLogin);

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String correo = txtCorreo.getText().trim();
                String password = new String(txtPassword.getPassword());

                // Validación de prueba (puedes conectar esto con la capa lógica real)
                if (correo.equals("admin@tienda.com")) {
                    new MenuAdministrador(gestorProductos, gestorPedidos).setVisible(true);
                    dispose();
                } else if (correo.equals("comprador@tienda.com")) {
                    new MenuComprador(gestorProductos, gestorPedidos).setVisible(true);
                    dispose();
                } else if (correo.equals("vendedor@tienda.com")) {
                    new MenuVendedor(gestorProductos, gestorPedidos).setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario no reconocido.");
                }
            }
        });
    }
}