package CapaInterfazGrafica;

import CapaLogica.GestorPedidos;
import CapaLogica.GestorProductos;
import CapaLogica.Pedido;
import CapaLogica.Producto;
import CapaLogica.Comprador;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Ventana para crear un nuevo pedido como comprador.
 */
public class CrearPedidoFrame extends JFrame {

    private GestorProductos gestorProductos;
    private GestorPedidos gestorPedidos;
    private Pedido pedido;

    private JComboBox<String> comboProductos;
    private JTextArea txtCarrito;
    private JTextField txtNombreUsuario, txtNombreCompleto, txtCedula, txtFechaNacimiento, txtCorreoElectronico, txtPassword;

    public CrearPedidoFrame(GestorProductos gestorProductos, GestorPedidos gestorPedidos) {
        this.gestorProductos = gestorProductos;
        this.gestorPedidos = gestorPedidos;

        setTitle("Crear Pedido");
        setSize(500, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Campos para la información del comprador
        JLabel lblNombreUsuario = new JLabel("Nombre de usuario:");
        lblNombreUsuario.setBounds(30, 20, 180, 25);
        add(lblNombreUsuario);

        txtNombreUsuario = new JTextField();
        txtNombreUsuario.setBounds(200, 20, 250, 25);
        add(txtNombreUsuario);

        JLabel lblNombreCompleto = new JLabel("Nombre completo:");
        lblNombreCompleto.setBounds(30, 60, 180, 25);
        add(lblNombreCompleto);

        txtNombreCompleto = new JTextField();
        txtNombreCompleto.setBounds(200, 60, 250, 25);
        add(txtNombreCompleto);

        JLabel lblCedula = new JLabel("Cédula de identidad:");
        lblCedula.setBounds(30, 100, 180, 25);
        add(lblCedula);

        txtCedula = new JTextField();
        txtCedula.setBounds(200, 100, 250, 25);
        add(txtCedula);

        JLabel lblFechaNacimiento = new JLabel("Fecha de nacimiento:");
        lblFechaNacimiento.setBounds(30, 140, 180, 25);
        add(lblFechaNacimiento);

        txtFechaNacimiento = new JTextField();
        txtFechaNacimiento.setBounds(200, 140, 250, 25);
        add(txtFechaNacimiento);

        JLabel lblCorreoElectronico = new JLabel("Correo electrónico:");
        lblCorreoElectronico.setBounds(30, 180, 180, 25);
        add(lblCorreoElectronico);

        txtCorreoElectronico = new JTextField();
        txtCorreoElectronico.setBounds(200, 180, 250, 25);
        add(txtCorreoElectronico);

        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setBounds(30, 220, 180, 25);
        add(lblPassword);

        txtPassword = new JTextField();
        txtPassword.setBounds(200, 220, 250, 25);
        add(txtPassword);

        // ComboBox para seleccionar el producto
        JLabel lblProducto = new JLabel("Seleccionar producto:");
        lblProducto.setBounds(30, 260, 180, 25);
        add(lblProducto);

        comboProductos = new JComboBox<>();
        actualizarComboProductos();
        comboProductos.setBounds(200, 260, 250, 25);
        add(comboProductos);

        JButton btnAgregar = new JButton("Agregar al pedido");
        btnAgregar.setBounds(150, 300, 180, 30);
        add(btnAgregar);

        txtCarrito = new JTextArea();
        txtCarrito.setEditable(false);
        JScrollPane scroll = new JScrollPane(txtCarrito);
        scroll.setBounds(30, 340, 420, 100);
        add(scroll);

        JButton btnFinalizar = new JButton("Finalizar Pedido");
        btnFinalizar.setBounds(150, 450, 180, 30);
        add(btnFinalizar);

        // Acción para agregar productos al pedido
        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (pedido == null) {
                    // Crear el comprador con la información recogida
                    String nombreUsuario = txtNombreUsuario.getText().trim();
                    String nombreCompleto = txtNombreCompleto.getText().trim();
                    String cedula = txtCedula.getText().trim();
                    String fechaNacimiento = txtFechaNacimiento.getText().trim();
                    String correoElectronico = txtCorreoElectronico.getText().trim();
                    String password = txtPassword.getText().trim();

                    if (nombreCompleto.isEmpty() || correoElectronico.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Por favor completa todos los campos.");
                        return;
                    }

                    Comprador comprador = new Comprador(nombreUsuario, nombreCompleto, cedula, fechaNacimiento, correoElectronico, password);
                    pedido = new Pedido(comprador);
                }

                String nombreProducto = (String) comboProductos.getSelectedItem();
                Producto producto = gestorProductos.buscarProducto(nombreProducto);
                if (producto != null) {
                    pedido.agregarProducto(producto);
                    txtCarrito.append("Agregado: " + producto.getNombre() + " - $" + producto.getPrecio() + "\n");
                }
            }
        });

        // Acción para finalizar el pedido
        btnFinalizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (pedido == null || pedido.getProductos().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "El pedido está vacío.");
                    return;
                }
                try {
                    gestorPedidos.agregarPedido(pedido);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                JOptionPane.showMessageDialog(null, "Pedido guardado exitosamente.");
                dispose();
            }
        });
    }

    private void actualizarComboProductos() {
        comboProductos.removeAllItems();
        for (Producto p : gestorProductos.obtenerProductos()) {
            comboProductos.addItem(p.getNombre());
        }
    }
}