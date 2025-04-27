package CapaInterfazGrafica;

import CapaLogica.GestorProductos;
import CapaLogica.GestorPedidos;
import CapaLogica.Producto;
import CapaLogica.Pedido;
import CapaLogica.Comprador;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class MenuComprador extends JFrame {

    private GestorProductos gestorProductos;
    private GestorPedidos gestorPedidos;
    private Comprador comprador;  // Cliente comprador

    public MenuComprador(GestorProductos gestorProductos, GestorPedidos gestorPedidos, Comprador comprador) {
        this.gestorProductos = gestorProductos;
        this.gestorPedidos = gestorPedidos;
        this.comprador = comprador;

        setTitle("Menú Comprador");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel label = new JLabel("Bienvenido " + comprador.getNombreCompleto(), SwingConstants.CENTER);
        label.setBounds(50, 20, 300, 30);
        add(label);

        JButton btnVerProductos = new JButton("Ver Productos");
        btnVerProductos.setBounds(100, 70, 200, 30);
        add(btnVerProductos);

        btnVerProductos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Mostrar productos disponibles
                List<Producto> productos = gestorProductos.obtenerProductos();

                StringBuilder listado = new StringBuilder("Productos disponibles:\n");
                for (Producto p : productos) {
                    listado.append(p.getNombre())
                            .append(" - $").append(p.getPrecio()).append("\n");
                }

                // Crear un cuadro de lista para seleccionar productos
                String[] opciones = listado.toString().split("\n");
                String productoSeleccionado = (String) JOptionPane.showInputDialog(null, "Selecciona un producto", "Productos",
                        JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);

                if (productoSeleccionado != null) {
                    // Extraer el nombre del producto seleccionado
                    String nombreProducto = productoSeleccionado.split(" - ")[0];
                    Producto producto = gestorProductos.buscarProducto(nombreProducto);

                    if (producto != null) {
                        // Pedir cantidad
                        String cantidadStr = JOptionPane.showInputDialog("Ingresa la cantidad:");
                        try {
                            int cantidad = Integer.parseInt(cantidadStr);
                            if (cantidad > 0) {
                                // Crear un nuevo pedido si no existe uno
                                Pedido pedido = new Pedido(comprador);

                                // Agregar productos al pedido según la cantidad seleccionada
                                for (int i = 0; i < cantidad; i++) {
                                    pedido.agregarProducto(producto);
                                }

                                // Mostrar resumen
                                JOptionPane.showMessageDialog(null, "Producto: " + producto.getNombre() + "\nCantidad: " + cantidad +
                                        "\nTotal a pagar: $" + pedido.getTotal());

                                // Guardar el pedido
                                gestorPedidos.agregarPedido(pedido);

                                // Confirmar la compra
                                JOptionPane.showMessageDialog(null, "Compra realizada exitosamente.");
                            } else {
                                JOptionPane.showMessageDialog(null, "Cantidad no válida.");
                            }
                        } catch (NumberFormatException | SQLException ex) {
                            JOptionPane.showMessageDialog(null, "Por favor ingresa un número válido.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Producto no encontrado.");
                    }
                }
            }
        });

        JButton btnVerPedidos = new JButton("Ver Pedidos");
        btnVerPedidos.setBounds(100, 110, 200, 30);
        add(btnVerPedidos);

        btnVerPedidos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Mostrar los pedidos del comprador usando el método correcto
                List<Pedido> pedidos = null;
                try {
                    pedidos = gestorPedidos.obtenerPedidosPorComprador(comprador.getNombreUsuario());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                StringBuilder listado = new StringBuilder("Tus pedidos:\n");

                for (Pedido p : pedidos) {
                    listado.append(p.toString()).append("\n");
                }

                JOptionPane.showMessageDialog(null, listado.toString());
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