package CapaInterfazGrafica;

import CapaLogica.GestorPedidos;
import CapaLogica.GestorProductos;
import CapaLogica.Pedido;
import CapaLogica.Producto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Ventana para crear un nuevo pedido como comprador.
 */
public class CrearPedidoFrame extends JFrame {

    private GestorProductos gestorProductos;
    private GestorPedidos gestorPedidos;
    private Pedido pedido;

    private JComboBox<String> comboProductos;
    private JTextArea txtCarrito;
    private JTextField txtComprador;

    public CrearPedidoFrame(GestorProductos gestorProductos, GestorPedidos gestorPedidos) {
        this.gestorProductos = gestorProductos;
        this.gestorPedidos = gestorPedidos;

        setTitle("Crear Pedido");
        setSize(500, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblNombre = new JLabel("Nombre del comprador:");
        lblNombre.setBounds(30, 20, 180, 25);
        add(lblNombre);

        txtComprador = new JTextField();
        txtComprador.setBounds(200, 20, 250, 25);
        add(txtComprador);

        JLabel lblProducto = new JLabel("Seleccionar producto:");
        lblProducto.setBounds(30, 60, 180, 25);
        add(lblProducto);

        comboProductos = new JComboBox<>();
        actualizarComboProductos();
        comboProductos.setBounds(200, 60, 250, 25);
        add(comboProductos);

        JButton btnAgregar = new JButton("Agregar al pedido");
        btnAgregar.setBounds(150, 100, 180, 30);
        add(btnAgregar);

        txtCarrito = new JTextArea();
        txtCarrito.setEditable(false);
        JScrollPane scroll = new JScrollPane(txtCarrito);
        scroll.setBounds(30, 140, 420, 150);
        add(scroll);

        JButton btnFinalizar = new JButton("Finalizar Pedido");
        btnFinalizar.setBounds(150, 310, 180, 30);
        add(btnFinalizar);

        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (pedido == null) {
                    String nombreComprador = txtComprador.getText().trim();
                    if (nombreComprador.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Por favor ingresa tu nombre.");
                        return;
                    }
                    pedido = new Pedido(nombreComprador);
                }

                String nombreProducto = (String) comboProductos.getSelectedItem();
                Producto producto = gestorProductos.buscarProducto(nombreProducto);
                if (producto != null) {
                    pedido.agregarProducto(producto);
                    txtCarrito.append("Agregado: " + producto.getNombre() + " - $" + producto.getPrecio() + "\n");
                }
            }
        });

        btnFinalizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (pedido == null || pedido.getProductos().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "El pedido está vacío.");
                    return;
                }
                gestorPedidos.agregarPedido(pedido);
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