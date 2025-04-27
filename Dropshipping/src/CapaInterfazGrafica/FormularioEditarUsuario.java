package CapaInterfazGrafica;

import CapaLogica.Usuario;
import CapaLogica.GestorUsuarios;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FormularioEditarUsuario extends JFrame {

    private Usuario usuario;
    private GestorUsuarios gestorUsuarios;
    private GestionUsuariosFrame parentFrame;

    private JTextField txtNombreCompleto, txtCedula, txtNacimiento, txtCorreo, txtPassword;

    public FormularioEditarUsuario(Usuario usuario, GestorUsuarios gestorUsuarios, GestionUsuariosFrame parentFrame) {
        this.usuario = usuario;
        this.gestorUsuarios = gestorUsuarios;
        this.parentFrame = parentFrame;

        setTitle("Editar Usuario");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(6, 2, 5, 5));

        add(new JLabel("Nombre Completo:"));
        txtNombreCompleto = new JTextField(usuario.getNombreCompleto());
        add(txtNombreCompleto);

        add(new JLabel("Cédula Identidad:"));
        txtCedula = new JTextField(usuario.getCedulaIdentidad());
        add(txtCedula);

        add(new JLabel("Fecha Nacimiento:"));
        txtNacimiento = new JTextField(usuario.getFechaNacimiento());
        add(txtNacimiento);

        add(new JLabel("Correo Electrónico:"));
        txtCorreo = new JTextField(usuario.getCorreoElectronico());
        add(txtCorreo);

        add(new JLabel("Contraseña:"));
        txtPassword = new JTextField(usuario.getPassword());
        add(txtPassword);

        JButton btnGuardar = new JButton("Guardar Cambios");
        add(btnGuardar);

        JButton btnCancelar = new JButton("Cancelar");
        add(btnCancelar);

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarCambios();
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // cerrar ventana
            }
        });
    }

    private void guardarCambios() {
        usuario.setNombreCompleto(txtNombreCompleto.getText());
        usuario.setCedulaIdentidad(txtCedula.getText());
        usuario.setFechaNacimiento(txtNacimiento.getText());
        usuario.setCorreoElectronico(txtCorreo.getText());
        usuario.setPassword(txtPassword.getText());

        gestorUsuarios.actualizarUsuario(usuario);
        parentFrame.actualizarTabla();
        JOptionPane.showMessageDialog(this, "Usuario actualizado correctamente.");
        dispose();
    }
}