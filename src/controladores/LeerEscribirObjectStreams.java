package controladores;

import modelos.Venta;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.table.TableModel;

public class LeerEscribirObjectStreams {

	public ArrayList<Venta> leerDatos(String archivo) throws IOException {
		ObjectInputStream leer = null;
		ArrayList<Venta> compras = new ArrayList<>();

		try {
			leer = new ObjectInputStream(new BufferedInputStream(new FileInputStream(archivo)));

			try {
				while (true) {
					compras.add((Venta) leer.readObject()); // Leemos objeto
				}
			} catch (EOFException ex) {
				// System.out.println("Fin del archivo");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		} finally {
			if (leer != null) {
				leer.close();
			}
		}
		return compras;
	}

	public void escribirDatos(Venta compra, String archivo) throws IOException {
		ObjectOutputStream escribir = null;

		try {
			// Se revisa si el archivo existe para saber si añadir los headers o no.
			File f = new File(archivo);
			if (!f.isDirectory() && f.length() > 0) {
				// Con esta clase, no se añaden los headers al archivo
				escribir = new AppendObjectOutputStream(new BufferedOutputStream(new FileOutputStream(archivo, true)));
			} else {
				escribir = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(archivo, true)));
			}
			escribir.writeObject(compra);

		} catch (IOException ex) {
			System.out.println("No se pudo abrir un archivo.");
		} finally {
			if (escribir != null) {
				escribir.close();
			}
		}

	}
	
}
