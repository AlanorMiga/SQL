package controladores;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import modelos.ModeloTablaVentas;
import modelos.Venta;

public class LecturaEscritura {

	ModeloTablaVentas modeloVentas;

	public LecturaEscritura(ModeloTablaVentas modeloVentas) {
		this.modeloVentas = modeloVentas;
	}

	public void cargarDatos() throws IOException {
		ObjectInputStream leer = null;

		try {
			leer = new ObjectInputStream(new BufferedInputStream(new FileInputStream("src/archivos/datosCompra2.txt")));

			while (true) {
				Venta venta = (Venta) leer.readObject();
				modeloVentas.addRow(venta);
			}
		} catch (EOFException ex) {

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (leer != null) {
				leer.close();
			}
		}
	}

	public void guardar() throws IOException {
		ObjectOutputStream escribir = null;

		try {
			escribir = new ObjectOutputStream(
					new BufferedOutputStream(new FileOutputStream("src/archivos/datosCompra2.txt")));

			for (int fila = 0; fila < modeloVentas.getRowCount(); fila++) {
				if (!validar(fila, 0) || !validar(fila, 1) || !validar(fila, 2)) {
					continue;
				}

				escribir.writeObject(modeloVentas.getRow(fila));
			}

			JOptionPane.showMessageDialog(null, "Se han guardado los datos");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (escribir != null) {
				escribir.close();
			}
		}
	}

	public boolean validar(int fila, int columna) {
		if (modeloVentas.getValueAt(fila, columna) == null || modeloVentas.getValueAt(fila, columna).equals("")) {
			return false;
		}

		return true;
	}

}
