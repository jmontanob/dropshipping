package CapaInterfazGrafica;

import CapaLogica.GestorUsuarios;
import CapaLogica.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class GestionUsuariosFrame extends JFrame {

    private GestorUsuarios gestorUsuarios;
    private JTable tablaUsuarios;
    private DefaultTableModel modeloTabla;

    public GestionUsuariosFrame(GestorUsuarios gestorUsuarios) {
        this.gestorUsuarios = gestorUsuarios;

        setTitle("Gestión de Usuarios");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        modeloTabla = new DefaultTableModel(new Object[]{"Usuario", "Nombre Completo", "Cédula", "Nacimiento", "Correo", "Rol"}, 0);
        tablaUsuarios = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaUsuarios);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        JButton btnEditar = new JButton("Editar");
        JButton btnEliminar = new JButton("Eliminar");
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        add(panelBotones, BorderLayout.SOUTH);

        cargarUsuarios();

        // Acción de Editar
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = tablaUsuarios.getSelectedRow();
                if (filaSeleccionada != -1) {
                    editarUsuario(filaSeleccionada);
                } else {
                    JOptionPane.showMessageDialog(null, "Selecciona un usuario para editar.");
                }
            }
        });

        // Acción de Eliminar
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = tablaUsuarios.getSelectedRow();
                if (filaSeleccionada != -1) {
                    eliminarUsuario(filaSeleccionada);
                } else {
                    JOptionPane.showMessageDialog(null, "Selecciona un usuario para eliminar.");
                }
            }
        });
    }

    private void cargarUsuarios() {
        modeloTabla.setRowCount(0); // limpia
        List<Usuario> usuarios = gestorUsuarios.getListaUsuarios();
        for (Usuario u : usuarios) {
            modeloTabla.addRow(new Object[]{
                    u.getNombreUsuario(),
                    u.getNombreCompleto(),
                    u.getCedulaIdentidad(),
                    u.getFechaNacimiento(),
                    u.getCorreoElectronico(),
                    u instanceof CapaLogica.Administrador ? "Administrador" :
                            u instanceof CapaLogica.Vendedor ? "Vendedor" : "Comprador"
            });
        }
    }

    private void editarUsuario(int fila) {
        String nombreUsuario = (String) modeloTabla.getValueAt(fila, 0);
        Usuario usuario = gestorUsuarios.obtenerUsuarioPorNombre(nombreUsuario);

        if (usuario != null) {
            FormularioEditarUsuario formulario = new FormularioEditarUsuario(usuario, gestorUsuarios, this);
            formulario.setVisible(true);
        }
    }

    private void eliminarUsuario(int fila) {
        int confirmacion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de eliminar este usuario?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            String nombreUsuario = (String) modeloTabla.getValueAt(fila, 0);
            gestorUsuarios.eliminarUsuario(nombreUsuario);
            cargarUsuarios();
        }
    }

    public void actualizarTabla() {
        cargarUsuarios();  // refresca los datos
    }
}