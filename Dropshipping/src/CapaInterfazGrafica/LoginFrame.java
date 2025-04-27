package CapaInterfazGrafica;

import CapaLogica.*;
import CapaDAO.DAOUsuario;

import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;

public class LoginFrame extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JComboBox<String> roleSelector;
    private JButton btnLogin;
    private JButton btnRegister;

    private GestorProductos gestorProductos;
    private GestorPedidos gestorPedidos;

    private Usuario usuarioLogueado; // NUEVA VARIABLE

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

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String user = txtUsername.getText().trim();
                String pass = new String(txtPassword.getPassword());
                String selectedRole = (String) roleSelector.getSelectedItem();

                try {
                    DAOUsuario daoUsuario = new DAOUsuario();
                    Usuario usuario = daoUsuario.buscarUsuario(user);

                    if (usuario != null) {
                        String rolUsuario = daoUsuario.obtenerRolUsuario(user);

                        if (usuario.getPassword().equals(pass) && rolUsuario.equals(selectedRole)) {
                            usuarioLogueado = usuario; // GUARDAR EL USUARIO
                            daoUsuario.cerrarConexion();
                            openMenu(rolUsuario);
                        } else {
                            daoUsuario.cerrarConexion();
                            JOptionPane.showMessageDialog(null, "Contraseña o rol incorrecto.");
                        }
                    } else {
                        daoUsuario.cerrarConexion();
                        JOptionPane.showMessageDialog(null, "Usuario no registrado.");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos.");
                }
            }
        });

        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RegistroFrame registroFrame = new RegistroFrame(gestorProductos, gestorPedidos);
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
                // MANDAR TAMBIÉN EL VENDEDOR LOGUEADO
                if (usuarioLogueado instanceof Vendedor) {
                    new MenuVendedor(gestorProductos, gestorPedidos, (Vendedor) usuarioLogueado).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Error: usuario no es un vendedor.");
                }
                break;
            case "Comprador":
                new MenuComprador(gestorProductos, gestorPedidos).setVisible(true);
                break;
        }
        dispose();
    }
}