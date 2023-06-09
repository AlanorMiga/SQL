package vistas;

import vistas.Ventana;
import controladores.ControladorVentas;
import exceptions.CamposVaciosException;
import controladores.LecturaEscritura;
import modelos.ModeloTablaVentas;
import modelos.Venta;
import controladores.LeerEscribirObjectStreams;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.border.AbstractBorder;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class FormularioParaAdmins extends JPanel implements ActionListener {

	JTextField campoNombre;
	JPasswordField campoContrasena;
	JPanel panelInputs = new JPanel(new SpringLayout());
	JPanel panelBotones = new JPanel();
	LeerEscribirObjectStreams guardar = new LeerEscribirObjectStreams();

	public FormularioParaAdmins() {
		panelBotones.setLayout(new FlowLayout(FlowLayout.LEFT));
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);

		String etiquetas[] = { "Nombre", "Contrasena" }; // Etiquetas modificadas

		Font fuenteFormulario = new Font("Poppins", Font.PLAIN, 16);

		panelInputs.setBackground(Color.white);

		campoNombre = new JTextField();
		campoNombre.setFont(fuenteFormulario);
		campoNombre.setBackground(Color.decode("#f2f2f2"));
		campoNombre.setBorder(BorderFactory.createCompoundBorder(new CustomeBorder(),
				new EmptyBorder(new Insets(15, 25, 15, 25))));
		campoNombre.setName(etiquetas[0]);
		panelInputs.add(new JLabel(etiquetas[0]));
		panelInputs.add(campoNombre);

		campoContrasena = new JPasswordField();
		campoContrasena.setFont(fuenteFormulario);
		campoContrasena.setBackground(Color.decode("#f2f2f2"));
		campoContrasena.setBorder(BorderFactory.createCompoundBorder(new CustomeBorder(),
				new EmptyBorder(new Insets(15, 25, 15, 25))));
		campoContrasena.setName(etiquetas[1]);
		panelInputs.add(new JLabel(etiquetas[1]));
		panelInputs.add(campoContrasena);

		SpringUtilities.makeCompactGrid(panelInputs, 2, 2, 10, 10, 10, 10);

		JLabel titulo = new JLabel("Bienvenido, administrador");
		titulo.setFont(new Font("Poppins", Font.BOLD, 30));
		titulo.setForeground(Color.decode("#038aff"));

		Box box1 = new Box(BoxLayout.X_AXIS);
		Box box2 = new Box(BoxLayout.X_AXIS);

		Component rigidArea = Box.createRigidArea(new Dimension(25, 100));

		Imagenes imagenPez = new Imagenes(250, 250);
		imagenPez.settearImagen(imagenRandom());

		JButton guardar = new JButton("Guardar");
		JButton cancelar = new JButton("Cancelar");

		guardar.setBorder(
				BorderFactory.createCompoundBorder(new CustomeBorder(), new EmptyBorder(new Insets(15, 25, 15, 25))));

		cancelar.setBorder(
				BorderFactory.createCompoundBorder(new CustomeBorder(), new EmptyBorder(new Insets(15, 25, 15, 25))));

		guardar.setBackground(Color.decode("#038aff"));
		guardar.setForeground(Color.white);

		cancelar.setBackground(Color.decode("#038aff"));
		cancelar.setForeground(Color.white);

		panelBotones.setBackground(Color.white);

		box1.add(Box.createHorizontalStrut(25));
		box2.add(rigidArea);
		box2.add(titulo);
		panelBotones.add(Box.createRigidArea(new Dimension(15, 50)));
		add(imagenPez, BorderLayout.EAST);
		add(panelInputs, BorderLayout.CENTER);
		add(box2, BorderLayout.NORTH);
		add(box1, BorderLayout.WEST);
		panelBotones.add(cancelar);
		cancelar.addActionListener(this);
		panelBotones.add(guardar);
		guardar.addActionListener(this);
		add(panelBotones, BorderLayout.SOUTH);

	}

	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("Cancelar")) {
			SwingUtilities.windowForComponent((JButton) e.getSource()).dispose();
		} else if (e.getActionCommand().equals("Guardar")) {
			String nombre = campoNombre.getText();
			String contraseña = new String(campoContrasena.getPassword());

			try {
				validarCampo(nombre, "Nombre");
				validarCampo(contraseña, "Contraseña");
			} catch (CamposVaciosException e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage());
				return;
			}

			// Resto del código para guardar la información

			SwingUtilities.windowForComponent((JButton) e.getSource()).dispose();

		}

	}

	public void validarCampo(String cadena, String campo) throws CamposVaciosException {
		if (cadena.isEmpty()) {
			throw new CamposVaciosException(campo);
		}

	}

	public String imagenRandom() {
		String[] imagenes = { "azulcropped.png", "globocropped.png", "pezclowncroped.png" };

		return imagenes[(int) (Math.random() * imagenes.length) + 0];
	}

}