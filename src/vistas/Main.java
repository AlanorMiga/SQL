package vistas;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import DB.*;

public class Main {

	public static void main(String[] args) {

//		VistaVentas panelTabla = new VistaVentas();
//		Ventana ventana = new Ventana(panelTabla);

		FormularioParaAdmins panelAdmin = new FormularioParaAdmins();
		Ventana ventana = new Ventana(panelAdmin);
		
		ImageIcon img = new ImageIcon("src/img1/fishIcon.png");
		ventana.setIconImage(img.getImage());
		ventana.setResizable(false);
		
//		System.out.println(Venta.verVenta(1));

//		FormularioParaPeces formularioParaPeces= new FormularioParaPeces();
//		Ventana ventanaForum= new Ventana(formularioParaPeces);

//		AgregarCompra ventanaForum = new AgregarCompra();

	}

}
