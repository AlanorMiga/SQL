package vistas;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Imagenes extends JButton {

	private ImageIcon botonImagen;

	public Imagenes(int anchura, int altura) {
		this.setHorizontalTextPosition(SwingConstants.CENTER);
		this.setVerticalTextPosition(SwingConstants.CENTER);
		this.setBorderPainted(false);// elimina el borde
		this.setOpaque(false);// lo hace invisible
		this.setContentAreaFilled(false);// elimina el contenido
		this.setBounds(0, 0, anchura, altura);// posicion y tamano del boton
//			botonImagen= createImageIcon();// se le asigna un archivo con el metodo createImageIcon
//			this.setIcon(new ImageIcon(botonImagen.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH)));
		// se le asigna la imagen al boton y la imagen se escala al tamano del boton

	}

	protected ImageIcon createImageIcon(String nombreFigura) {
		java.net.URL imgURL = getClass().getClassLoader().getResource("img1/" + nombreFigura);
		System.out.println("" + imgURL);

		/* si encuentra en la carpeta la imagen indicada */
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			/* Devuelve por default la imagen de ficha azul */
			System.out.println("no");
			return null;
		}
	}

	public void settearImagen(String nombreImagen) {
		botonImagen = createImageIcon(nombreImagen);
		this.setIcon(new ImageIcon(
				botonImagen.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH)));

	}

}