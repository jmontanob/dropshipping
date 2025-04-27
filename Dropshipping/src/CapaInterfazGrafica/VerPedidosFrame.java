package CapaInterfazGrafica;

import CapaLogica.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Ventana para visualizar todos los pedidos realizados.
 */
public class VerPedidosFrame extends JFrame {

    private GestorPedidos gestorPedidos;
    private JTextArea txtPedidos;

    public VerPedidosFrame(GestorPedidos gestorPedidos) {
        this.gestorPedidos = gestorPedidos;

        setTitle("Listado de Pedidos");
        setSize(500, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel label = new JLabel("Pedidos registrados:", SwingConstants.LEFT);
        label.setBounds(30, 20, 200, 25);
        add(label);

        txtPedidos = new JTextArea();
        txtPedidos.setEditable(false);

        JScrollPane scroll = new JScrollPane(txtPedidos);
        scroll.setBounds(30, 60, 420, 250);
        add(scroll);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(180, 320, 120, 30);
        add(btnActualizar);

        btnActualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarPedidos();
            }
        });

        mostrarPedidos(); // Carga inicial
    }

    private void mostrarPedidos() {
        try {
            List<Pedido> lista = gestorPedidos.obtenerTodosLosPedidos(); // Llamada a obtener todos los pedidos
            StringBuilder sb = new StringBuilder();

            if (lista.isEmpty()) {
                sb.append("No hay pedidos registrados.\n");
            } else {
                for (Pedido p : lista) {
                    sb.append(p.toString()).append("\n");
                }
            }

            txtPedidos.setText(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar los pedidos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}