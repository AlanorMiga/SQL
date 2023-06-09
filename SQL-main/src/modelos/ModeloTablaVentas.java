package modelos;

import java.awt.Color;
import java.awt.Component;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

public class ModeloTablaVentas extends AbstractTableModel {

	private Vector<Venta> datos = new Vector<Venta>();
	private String nombreColumnas[] = { "Nombre", "Especie", "Edad", "Razon de visita" };

	public ModeloTablaVentas() {

	}

	@Override
	public int getRowCount() {
		return datos.size();
	}

	@Override
	public int getColumnCount() {
		return nombreColumnas.length;
	}

	@Override
	public Object getValueAt(int fila, int columna) {

		Venta v = datos.elementAt(fila);

		switch (columna) {
		case 0:
			return v.getNombre();
		case 1:
			return v.getEspecie();
		case 2:
			return v.getEdad();
		case 3:
			return v.getRazon();
		}

		return null;
	}

	@Override
	public String getColumnName(int columna) {
		return nombreColumnas[columna];
	}

	@Override
	public Class<?> getColumnClass(int columna) {
		switch (columna) {

		case 2:

			return Integer.class;
		}

		return String.class;
	}

	@Override
	public void setValueAt(Object valor, int fila, int columna) {
		Venta v = datos.elementAt(fila);

		switch (columna) {
		case 0:
			v.setNombre((String) valor);
			break;
		case 1:
			v.setEspecie((String) valor);
			break;
		case 2:
			v.setEdad((int) valor);
			break;
		case 3:
			v.setRazon((String) valor);
			break;
		}
	}

	// @Override
	// public boolean isCellEditable(int fila, int columna) {
	// if(columna >= 0 && columna < 5) {
	// return true;
	// }

	// return false;
	// }

	public void addRow(Venta v) {
		datos.add(v);
		fireTableRowsInserted(datos.size() - 1, datos.size() - 1);
	}

	public Venta getRow(int fila) {
		return datos.elementAt(fila);
	}

	public void vaciar() {
		int filas = getRowCount();

		datos.removeAllElements();
		fireTableRowsDeleted(0, filas);
	}

	public void removeRow(int row) {
		datos.removeElementAt(row);
	}

}
