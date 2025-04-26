package CapaInterfazGrafica;

import CapaLogica.*;

import javax.swing.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class LoginFrame extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JComboBox<String> roleSelector;
    private JButton btnLogin;
    private JButton btnRegister;

    private GestorProductos gestorProductos;
    private GestorPedidos gestorPedidos;

    // Simulated user database: Map<username, password|role>
    private Map<String, String[]> userData = new HashMap<>();

    public LoginFrame(GestorProductos gestorProductos, GestorPedidos gestorPedidos) {
        this.gestorProductos = gestorProductos;
        this.gestorPedidos = gestorPedidos;

        setTitle("Inicio de Sesión");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblUsername = new JLabel("Usuario:");
        lblUsername.setBounds(30, 30, 80, 25);
        add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setBounds(120, 30, 200, 25);
        add(txtUsername);

        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setBounds(30, 70, 80, 25);
        add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(120, 70, 200, 25);
        add(txtPassword);

        JLabel lblRole = new JLabel("Rol:");
        lblRole.setBounds(30, 110, 80, 25);
        add(lblRole);

        roleSelector = new JComboBox<>(new String[]{"Administrador", "Vendedor", "Comprador"});
        roleSelector.setBounds(120, 110, 200, 25);
        add(roleSelector);

        btnLogin = new JButton("Iniciar sesión");
        btnLogin.setBounds(50, 160, 130, 30);
        add(btnLogin);

        btnRegister = new JButton("Registrarse");
        btnRegister.setBounds(200, 160, 130, 30);
        add(btnRegister);

        // Dummy user for testing
        userData.put("admin", new String[]{"admin123", "Administrador"});
        userData.put("vendedor", new String[]{"vend123", "Vendedor"});
        userData.put("cliente", new String[]{"cli123", "Comprador"});

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String user = txtUsername.getText().trim();
                String pass = new String(txtPassword.getPassword());
                String selectedRole = (String) roleSelector.getSelectedItem();

                if (userData.containsKey(user)) {
                    String[] userInfo = userData.get(user);
                    if (userInfo[0].equals(pass) && userInfo[1].equals(selectedRole)) {
                        openMenu(selectedRole);
                    } else {
                        JOptionPane.showMessageDialog(null, "Contraseña o rol incorrecto.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario no registrado.");
                }
            }
        });

        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RegistroFrame registroFrame = new RegistroFrame(gestorProductos, gestorPedidos, userData);
                registroFrame.setVisible(true);
            }
        });
    }

    private void openMenu(String role) {
        switch (role) {
            case "Administrador":
                new MenuAdministrador(gestorProductos, gestorPedidos).setVisible(true);
                break;
            case "Vendedor":
                new MenuVendedor(gestorProductos, gestorPedidos).setVisible(true);
                break;
            case "Comprador":
                new MenuComprador(gestorProductos, gestorPedidos).setVisible(true);
                break;
        }
        dispose();
    }
}