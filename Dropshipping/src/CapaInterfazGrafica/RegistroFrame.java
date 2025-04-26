package CapaInterfazGrafica;

import CapaLogica.*;

import javax.swing.*;
import java.awt.event.*;
import java.util.Map;

public class RegistroFrame extends JFrame {

    private JTextField txtNombreUsuario;
    private JTextField txtNombreCompleto;
    private JTextField txtCedula;
    private JTextField txtNacimiento;
    private JTextField txtCorreo;
    private JPasswordField txtPassword;
    private JComboBox<String> cmbRol;
    private JButton btnRegistrar;

    private GestorProductos gestorProductos;
    private GestorPedidos gestorPedidos;
    private Map<String, String[]> userData;

    public RegistroFrame(GestorProductos gestorProductos, GestorPedidos gestorPedidos, Map<String, String[]> userData) {
        this.gestorProductos = gestorProductos;
        this.gestorPedidos = gestorPedidos;
        this.userData = userData;

        setTitle("Registro de Usuario");
        setSize(400, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblRol = new JLabel("Rol:");
        lblRol.setBounds(30, 20, 100, 25);
        cmbRol = new JComboBox<>(new String[]{"Administrador", "Vendedor", "Comprador"});
        cmbRol.setBounds(140, 20, 200, 25);
        add(lblRol); add(cmbRol);

        JLabel lblUsuario = new JLabel("Nombre usuario:");
        lblUsuario.setBounds(30, 60, 100, 25);
        txtNombreUsuario = new JTextField();
        txtNombreUsuario.setBounds(140, 60, 200, 25);
        add(lblUsuario); add(txtNombreUsuario);

        JLabel lblNombreCompleto = new JLabel("Nombre completo:");
        lblNombreCompleto.setBounds(30, 100, 100, 25);
        txtNombreCompleto = new JTextField();
        txtNombreCompleto.setBounds(140, 100, 200, 25);
        add(lblNombreCompleto); add(txtNombreCompleto);

        JLabel lblCedula = new JLabel("Cédula:");
        lblCedula.setBounds(30, 140, 100, 25);
        txtCedula = new JTextField();
        txtCedula.setBounds(140, 140, 200, 25);
        add(lblCedula); add(txtCedula);

        JLabel lblNacimiento = new JLabel("Fecha nac. (dd/mm/aaaa):");
        lblNacimiento.setBounds(30, 180, 150, 25);
        txtNacimiento = new JTextField();
        txtNacimiento.setBounds(190, 180, 150, 25);
        add(lblNacimiento); add(txtNacimiento);

        JLabel lblCorreo = new JLabel("Correo:");
        lblCorreo.setBounds(30, 220, 100, 25);
        txtCorreo = new JTextField();
        txtCorreo.setBounds(140, 220, 200, 25);
        add(lblCorreo); add(txtCorreo);

        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setBounds(30, 260, 100, 25);
        txtPassword = new JPasswordField();
        txtPassword.setBounds(140, 260, 200, 25);
        add(lblPassword); add(txtPassword);

        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(130, 310, 120, 30);
        add(btnRegistrar);

        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String rol = (String) cmbRol.getSelectedItem();
                String usuario = txtNombreUsuario.getText().trim();
                String nombre = txtNombreCompleto.getText().trim();
                String cedula = txtCedula.getText().trim();
                String nacimiento = txtNacimiento.getText().trim();
                String correo = txtCorreo.getText().trim();
                String password = new String(txtPassword.getPassword());

                if (usuario.isEmpty() || password.isEmpty() || nombre.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Completa todos los campos.");
                    return;
                }

                if (userData.containsKey(usuario)) {
                    JOptionPane.showMessageDialog(null, "El usuario ya existe.");
                    return;
                }

                Usuario nuevoUsuario = null;
                switch (rol) {
                    case "Administrador":
                        nuevoUsuario = new Administrador(usuario, nombre, cedula, nacimiento, correo, password);
                        break;
                    case "Vendedor":
                        nuevoUsuario = new Vendedor(usuario, nombre, cedula, nacimiento, correo, password);
                        break;
                    case "Comprador":
                        nuevoUsuario = new Comprador(usuario, nombre, cedula, nacimiento, correo, password);
                        break;
                }

                // Guardar en el mapa (puedes guardar el objeto si prefieres)
                userData.put(usuario, new String[]{password, rol});
                JOptionPane.showMessageDialog(null, "Usuario registrado con éxito.");
                dispose(); // cerrar ventana de registro
            }
        });
    }
}