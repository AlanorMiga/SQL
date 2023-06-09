package vistas;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import modelos.ModeloTablaVentas;
import modelos.Venta;
import oyentes.EscuchaVentas;

public class VistaVentas extends JPanel {

	Ventana ventanaForum;
	public JTable tablaVentas;
	public ModeloTablaVentas modeloVentas;
	JButton cargar, nuevo, borrarFila, borrar, guardar, editar;

	public VistaVentas() {
		setLayout(new BorderLayout());
		agregarBotones();
		inicializarTabla();
		tablaVentas.setFont(new Font("POPPINS", Font.PLAIN, 12));
		tablaVentas.getTableHeader().setDefaultRenderer(new LostHeaderRenderer());
		tablaVentas.setBackground(Color.white);
		tablaVentas.setFillsViewportHeight(true);
		this.setBackground(Color.white);

	}

	private void inicializarTabla() {
		String columnas[] = { "Nombre", "Especie", "Edad", "Razon de visita" };
		modeloVentas = new ModeloTablaVentas();

		tablaVentas = new JTable(modeloVentas);
		JScrollPane scroll = new JScrollPane(tablaVentas);

		add(scroll, BorderLayout.CENTER);
	}

	private void agregarBotones() {
		JPanel botones = new JPanel();
		botones.setBackground(Color.white);

		guardar = new JButton("Guardar");
		guardar.setBorder(
				BorderFactory.createCompoundBorder(new CustomBorder(), new EmptyBorder(new Insets(15, 25, 15, 25))));
		guardar.setBackground(Color.decode("#038aff"));
		guardar.setForeground(Color.white);

		cargar = new JButton("Cargar");
		cargar.setBorder(
				BorderFactory.createCompoundBorder(new CustomBorder(), new EmptyBorder(new Insets(15, 25, 15, 25))));
		cargar.setBackground(Color.decode("#038aff"));
		cargar.setForeground(Color.white);

		nuevo = new JButton("Nueva fila");
		nuevo.setBorder(
				BorderFactory.createCompoundBorder(new CustomBorder(), new EmptyBorder(new Insets(15, 25, 15, 25))));
		nuevo.setBackground(Color.decode("#038aff"));
		nuevo.setForeground(Color.white);

		borrarFila = new JButton("Borrar fila");
		borrarFila.setBorder(
				BorderFactory.createCompoundBorder(new CustomBorder(), new EmptyBorder(new Insets(15, 25, 15, 25))));
		borrarFila.setBackground(Color.decode("#038aff"));
		borrarFila.setForeground(Color.white);

		editar = new JButton("Editar");
		editar.setBorder(
				BorderFactory.createCompoundBorder(new CustomBorder(), new EmptyBorder(new Insets(15, 25, 15, 25))));
		editar.setBackground(Color.decode("#038aff"));
		editar.setForeground(Color.white);

		borrar = new JButton("Borrar todo");
		borrar.setBorder(
				BorderFactory.createCompoundBorder(new CustomBorder(), new EmptyBorder(new Insets(15, 25, 15, 25))));
		borrar.setBackground(Color.decode("#038aff"));
		borrar.setForeground(Color.white);

		botones.add(guardar);
		botones.add(cargar);
		botones.add(nuevo);
		botones.add(borrarFila);
		botones.add(editar);
		botones.add(borrar);

		add(botones, BorderLayout.SOUTH);
	}

	public void asignarListeners(EscuchaVentas listener) {
		guardar.addActionListener(listener);
		cargar.addActionListener(listener);
		nuevo.addActionListener(listener);
		borrarFila.addActionListener(listener);
		borrar.addActionListener(listener);
		editar.addActionListener(listener);
	}

	public class LostHeaderRenderer extends JLabel implements TableCellRenderer {

		public LostHeaderRenderer() {
			setFont(new Font("Consolas", Font.BOLD, 14));
			setOpaque(true);
			setForeground(Color.WHITE);
			setBackground(Color.decode("#038aff"));
			setBorder(BorderFactory.createCompoundBorder(new CustomBorder(),
					new EmptyBorder(new Insets(15, 25, 15, 25))));
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			setText(value.toString());
			return this;
		}

	}
}
