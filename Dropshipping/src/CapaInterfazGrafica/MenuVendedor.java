package CapaInterfazGrafica;

import CapaLogica.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.List;

/**
 * Menú para vendedores.
 */
public class MenuVendedor extends JFrame {

    private GestorProductos gestorProductos;
    private GestorPedidos gestorPedidos;
    private Vendedor vendedorLogueado; // NUEVO: guardar el vendedor logueado

    private JTextArea textArea;

    public MenuVendedor(GestorProductos gestorProductos, GestorPedidos gestorPedidos, Vendedor vendedorLogueado) {
        this.gestorProductos = gestorProductos;
        this.gestorPedidos = gestorPedidos;
        this.vendedorLogueado = vendedorLogueado;

        setTitle("Menú Vendedor");
        setSize(600, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JButton btnAgregarProducto = new JButton("Agregar Producto");
        btnAgregarProducto.setBounds(20, 20, 180, 30);
        add(btnAgregarProducto);

        JButton btnVerMisProductos = new JButton("Ver Mis Productos");
        btnVerMisProductos.setBounds(20, 70, 180, 30);
        add(btnVerMisProductos);

        JButton btnCerrar = new JButton("Cerrar Sesión");
        btnCerrar.setBounds(20, 120, 180, 30);
        add(btnCerrar);

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(220, 20, 340, 300);
        add(scrollPane);

        // --- Listeners ---
        btnAgregarProducto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarProducto();
            }
        });

        btnVerMisProductos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarMisProductos();
            }
        });

        btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LoginFrame(gestorProductos, gestorPedidos).setVisible(true);
            }
        });
    }

    /**
     * Abre un diálogo para agregar un nuevo producto.
     */
    private void agregarProducto() {
        JTextField nombreField = new JTextField();
        JTextField categoriaField = new JTextField();
        JTextField precioField = new JTextField();
        JTextField pesoField = new JTextField();
        JTextField dimensionesField = new JTextField();
        JTextField inventarioField = new JTextField();

        Object[] campos = {
                "Nombre:", nombreField,
                "Categoría:", categoriaField,
                "Precio:", precioField,
                "Peso:", pesoField,
                "Dimensiones:", dimensionesField,
                "Inventario Disponible:", inventarioField
        };

        int opcion = JOptionPane.showConfirmDialog(this, campos, "Nuevo Producto", JOptionPane.OK_CANCEL_OPTION);
        if (opcion == JOptionPane.OK_OPTION) {
            try {
                String nombre = nombreField.getText();
                String categoria = categoriaField.getText();
                double precio = Double.parseDouble(precioField.getText());
                double peso = Double.parseDouble(pesoField.getText());
                String dimensiones = dimensionesField.getText();
                int inventario = Integer.parseInt(inventarioField.getText());

                gestorProductos.agregarProducto(nombre, categoria, precio, peso, dimensiones, inventario, vendedorLogueado);

                JOptionPane.showMessageDialog(this, "Producto agregado exitosamente.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Error en formato de número.");
            }
        }
    }

    /**
     * Muestra en el área de texto solo los productos del vendedor logueado.
     */
    private void mostrarMisProductos() {
        List<Producto> misProductos = gestorProductos.obtenerProductosPorVendedor(vendedorLogueado.getNombreUsuario());

        textArea.setText("");
        if (misProductos.isEmpty()) {
            textArea.append("No tienes productos registrados.\n");
        } else {
            for (Producto p : misProductos) {
                textArea.append(
                        "Nombre: " + p.getNombre() + "\n" +
                                "Categoría: " + p.getCategoria() + "\n" +
                                "Precio: $" + p.getPrecio() + "\n" +
                                "Peso: " + p.getPeso() + " kg\n" +
                                "Dimensiones: " + p.getDimensiones() + "\n" +
                                "Inventario Disponible: " + p.getInventarioDisponible() + "\n" +
                                "------------------------------\n"
                );
            }
        }
    }
}