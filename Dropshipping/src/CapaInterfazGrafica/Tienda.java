package CapaInterfazGrafica;
import CapaLogica.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

class TiendaUI {
    private JFrame frame;
    private DefaultListModel<String> listModel;
    private JList<String> productList;
    private List<Producto> productos;

    public TiendaUI() {
        productos = new ArrayList<>();
        frame = new JFrame("Gestión de Productos");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        productList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(productList);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton addButton = new JButton("Agregar Producto");
        addButton.addActionListener(e -> agregarProducto());
        panel.add(addButton, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void agregarProducto() {
        String nombre = JOptionPane.showInputDialog("Nombre del producto:");
        String categoria = JOptionPane.showInputDialog("Categoría del producto:");
        double precio = Double.parseDouble(JOptionPane.showInputDialog("Precio del producto:"));
        int inventario = Integer.parseInt(JOptionPane.showInputDialog("Cantidad en inventario:"));

        Producto nuevoProducto = new Producto(nombre, categoria, precio, 0, "", inventario, null);
        productos.add(nuevoProducto);
        actualizarLista();
    }

    private void actualizarLista() {
        listModel.clear();
        for (Producto p : productos) {
            if (p.getInventarioDisponible() > 0) {
                listModel.addElement(p.toString());
            }
        }
    }

    public static void main(String[] args) {
        new TiendaUI();
    }
}