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
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class FormularioParaPeces extends JPanel implements ActionListener {

	JTextField[] campos;
	JPanel panelInputs = new JPanel(new SpringLayout());
	JPanel panelBotones = new JPanel();
	LeerEscribirObjectStreams guardar = new LeerEscribirObjectStreams();

	public FormularioParaPeces() {
		panelBotones.setLayout(new FlowLayout(FlowLayout.LEFT));
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);

		String etiquetas[] = { "Nombre", "Especie", "Edad", "Razon de visita" };
		campos = new JTextField[etiquetas.length];
		Font fuenteFormulario = new Font("Poppins", Font.PLAIN, 16);
		for (int i = 0; i < etiquetas.length; i++) {

			panelInputs.setBackground(Color.white);

			campos[i] = new JTextField();
			campos[i].setFont(fuenteFormulario);
			campos[i].setBackground(Color.decode("#f2f2f2"));

			campos[i].setBorder(BorderFactory.createCompoundBorder(new CustomeBorder(),
					new EmptyBorder(new Insets(15, 25, 15, 25))));

			campos[i].setName(etiquetas[i]);
			panelInputs.add(new JLabel(etiquetas[i]));
			panelInputs.add(campos[i]);
		}

		SpringUtilities.makeCompactGrid(panelInputs, 4, 2, 10, 10, 10, 10);

		JLabel titulo = new JLabel("¿Quien nos visita el dia de hoy?");

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
			// contador++;
			ArrayList<Venta> datos = new ArrayList<>();

			for (JTextField campo : campos) {
				try {
					validarCampo(campo.getText(), campo.getName());
				} catch (CamposVaciosException e1) {
					JOptionPane.showMessageDialog(this, e1.getMessage());
					return;
				}
			}

			Venta nuevaVenta = new Venta();
			nuevaVenta.setNombre(campos[0].getText());
			nuevaVenta.setEspecie(campos[1].getText());
			nuevaVenta.setEdad(Integer.parseInt(campos[2].getText()));
			nuevaVenta.setRazon(campos[3].getText());
			try {
				guardar.escribirDatos(nuevaVenta, "src/archivos/datosCompra2.txt");
				JOptionPane.showMessageDialog(this, "Se ha guardado la compra.");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
