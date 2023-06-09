package vistas;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import DB.*;

public class Main {

	public static void main(String[] args) {

		FormularioParaAdmins panelAdmin = new FormularioParaAdmins();
		Ventana ventana = new Ventana(panelAdmin);
		ventana.setResizable(false);

	}

}