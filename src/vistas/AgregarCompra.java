package vistas;

import exceptions.CamposVaciosException;
import controladores.LeerEscribirObjectStreams;
import controladores.LecturaEscritura;
import modelos.ModeloTablaVentas;
import modelos.Venta;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class AgregarCompra extends JFrame implements ActionListener {
	int contador;
	ModeloTablaVentas modelo = new ModeloTablaVentas();
	JTextField campos[];
	JPanel panelInputs = new JPanel(new SpringLayout());
	JPanel panelUsuarios = new JPanel();
	JTextField numeroUsuario = new JTextField();
	ModeloTablaVentas modeloCompras;
	LeerEscribirObjectStreams guardar = new LeerEscribirObjectStreams();

//	ControladorVentas controladorVentas;

	public AgregarCompra() {

		setLayout(new BorderLayout());

		String etiquetas[] = { "Nombre", "Apellido", "Edad", "Nacionalidad" };
		campos = new JTextField[etiquetas.length];

		for (int i = 0; i < etiquetas.length; i++) {
			campos[i] = new JTextField(20);
			campos[i].setName(etiquetas[i]);
			panelInputs.add(new JLabel(etiquetas[i]));
			panelInputs.add(campos[i]);
		}

		SpringUtilities.makeCompactGrid(panelInputs, 4, 2, 10, 10, 10, 10);

		add(panelInputs, BorderLayout.CENTER);

		JPanel botones = new JPanel();

		JButton guardar = new JButton("Guardar");
		guardar.addActionListener(this);
		botones.add(guardar);
		JButton cancelar = new JButton("Cancelar");
		cancelar.addActionListener(this);
		botones.add(cancelar);

		add(botones, BorderLayout.SOUTH);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("Cancelar")) {
			this.dispose();
		} else if (e.getActionCommand().equals("Guardar")) {
			contador++;
			ArrayList<Venta> datos = new ArrayList<>();

			for (JTextField campo : campos) {
				try {
					validarCampo(campo.getText(), campo.getName());
				} catch (CamposVaciosException e1) {
					JOptionPane.showMessageDialog(this, e1.getMessage());
					return;
				}
			}

			// Ya validados, se a aden a la lista
			Venta nuevaVenta = new Venta();
			nuevaVenta.setNombre(campos[0].getText());
			nuevaVenta.setEspecie(campos[1].getText());
			nuevaVenta.setEdad(Integer.parseInt(campos[2].getText()));
			nuevaVenta.setRazon(campos[3].getText());
			try {
				guardar.escribirDatos(nuevaVenta, "src/archivos/datosCompra.txt");
				JOptionPane.showMessageDialog(this, "Se ha guardado la compra.");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	}

	public void validarCampo(String cadena, String campo) throws CamposVaciosException {
		if (cadena.isEmpty()) {
			throw new CamposVaciosException(campo);
		}

	}

}