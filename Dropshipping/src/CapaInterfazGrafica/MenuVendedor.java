package CapaInterfazGrafica;

import CapaLogica.GestorPedidos;
import CapaLogica.GestorProductos;
import CapaLogica.Vendedor;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Menú para el vendedor.
 */
public class MenuVendedor extends JFrame {

    private GestorProductos gestorProductos;
    private GestorPedidos gestorPedidos;
    private Vendedor vendedor;  // Vendedor autenticado

    // Constructor modificado para aceptar los objetos GestorProductos, GestorPedidos y Vendedor
    public MenuVendedor(GestorProductos gestorProductos, GestorPedidos gestorPedidos) {
        this.gestorProductos = gestorProductos;
        this.gestorPedidos = gestorPedidos;
        this.vendedor = vendedor;  // Asignamos el vendedor actual

        setTitle("Menú Vendedor");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel label = new JLabel("Bienvenido " + vendedor.getNombre(), SwingConstants.CENTER);
        label.setBounds(50, 20, 300, 30);
        add(label);

        JButton btnListarPedidos = new JButton("Listar Pedidos");
        btnListarPedidos.setBounds(100, 70, 200, 30);
        add(btnListarPedidos);

        btnListarPedidos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String resumen = gestorPedidos.obtenerResumenPedidos();
                JOptionPane.showMessageDialog(null, resumen);
            }
        });

        JButton btnVerPedidos = new JButton("Ver Pedidos");
        btnVerPedidos.setBounds(100, 110, 200, 30);
        add(btnVerPedidos);

        btnVerPedidos.addActionListener(e -> {
            VerPedidosFrame frame = new VerPedidosFrame(gestorPedidos);
            frame.setVisible(true);
        });

        // Botón para registrar un producto
        JButton btnRegistrarProducto = new JButton("Registrar Producto");
        btnRegistrarProducto.setBounds(100, 150, 200, 30);
        add(btnRegistrarProducto);

        // Acción al presionar el botón de registrar producto
        btnRegistrarProducto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Mostrar ventana de registro de producto
                mostrarVentanaRegistroProducto();
            }
        });
    }

    // Método para mostrar la ventana de registro de producto
    private void mostrarVentanaRegistroProducto() {
        // Crear el formulario para ingresar los datos del producto
        JFrame ventanaRegistro = new JFrame("Registrar Producto");
        ventanaRegistro.setSize(400, 300);
        ventanaRegistro.setLayout(null);
        ventanaRegistro.setLocationRelativeTo(null);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(20, 30, 100, 30);
        ventanaRegistro.add(lblNombre);

        JTextField txtNombre = new JTextField();
        txtNombre.setBounds(120, 30, 200, 30);
        ventanaRegistro.add(txtNombre);

        JLabel lblCategoria = new JLabel("Categoría:");
        lblCategoria.setBounds(20, 70, 100, 30);
        ventanaRegistro.add(lblCategoria);

        JTextField txtCategoria = new JTextField();
        txtCategoria.setBounds(120, 70, 200, 30);
        ventanaRegistro.add(txtCategoria);

        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setBounds(20, 110, 100, 30);
        ventanaRegistro.add(lblPrecio);

        JTextField txtPrecio = new JTextField();
        txtPrecio.setBounds(120, 110, 200, 30);
        ventanaRegistro.add(txtPrecio);

        JLabel lblPeso = new JLabel("Peso:");
        lblPeso.setBounds(20, 150, 100, 30);
        ventanaRegistro.add(lblPeso);

        JTextField txtPeso = new JTextField();
        txtPeso.setBounds(120, 150, 200, 30);
        ventanaRegistro.add(txtPeso);

        JLabel lblDimensiones = new JLabel("Dimensiones:");
        lblDimensiones.setBounds(20, 190, 100, 30);
        ventanaRegistro.add(lblDimensiones);

        JTextField txtDimensiones = new JTextField();
        txtDimensiones.setBounds(120, 190, 200, 30);
        ventanaRegistro.add(txtDimensiones);

        JLabel lblInventario = new JLabel("Inventario:");
        lblInventario.setBounds(20, 230, 100, 30);
        ventanaRegistro.add(lblInventario);

        JTextField txtInventario = new JTextField();
        txtInventario.setBounds(120, 230, 200, 30);
        ventanaRegistro.add(txtInventario);

        // Botón para registrar el producto
        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(150, 270, 100, 30);
        ventanaRegistro.add(btnRegistrar);

        // Acción al presionar el botón de registrar
        btnRegistrar.addActionListener(e -> {
            String nombre = txtNombre.getText();
            String categoria = txtCategoria.getText();
            double precio = Double.parseDouble(txtPrecio.getText());
            double peso = Double.parseDouble(txtPeso.getText());
            String dimensiones = txtDimensiones.getText();
            int inventario = Integer.parseInt(txtInventario.getText());

            // Usamos el vendedor autenticado
            // El vendedor es el que ha iniciado sesión en la aplicación

            // Registrar el producto en el gestor
            gestorProductos.agregarProducto(nombre, categoria, precio, peso, dimensiones, inventario, vendedor);

            JOptionPane.showMessageDialog(null, "Producto registrado con éxito.");
            ventanaRegistro.dispose(); // Cerrar la ventana después de registrar el producto
        });

        ventanaRegistro.setVisible(true);
    }
}