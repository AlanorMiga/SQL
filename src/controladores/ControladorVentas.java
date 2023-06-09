package controladores;

import java.io.IOException;


import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import modelos.ModeloTablaVentas;
import DB.Venta;
import oyentes.EscuchaVentas;
import vistas.VistaVentas;

public class ControladorVentas {

	VistaVentas vista;
	ModeloTablaVentas modeloVentas;
	LecturaEscritura io;
	EscuchaVentas listener;

	public ControladorVentas(VistaVentas vista) {
		this.modeloVentas = vista.modeloVentas;
		this.vista = vista;
		listener = new EscuchaVentas(this, vista.tablaVentas);
		vista.asignarListeners(listener);
		io = new LecturaEscritura(modeloVentas);
	}

	public void agregarFila() {
		modeloVentas.addRow(new Venta());
	}

	public void borrarFila(int indice) {
	    modeloVentas.removeRow(indice);
	}
	
	public void borrarTodo() {
		modeloVentas.vaciar();
	}

	public void cargarTabla() {
		borrarTodo();
		io.cargarDatos();

	}

	public void guardarEnArchivo() {
		try {
			io.guardar();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "No se pudo guardar: " + e.getMessage());
		}
	}

}
