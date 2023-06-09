package vistas;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controladores.ControladorVentas;

public class Ventana extends JFrame {

	public Ventana(VistaVentas panelito) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 500);
		setTitle("Fishpy :D (tabla) ");
		ImageIcon img = new ImageIcon("src/img1/fishIcon.png");
		setIconImage(img.getImage());
		ControladorVentas controlador = new ControladorVentas(panelito);
		add(panelito);
		this.setLocationRelativeTo(null);
		showOnScreen(1, this);
		setVisible(true);
	}

	public Ventana(JPanel panelito) {
		setSize(800, 500);
		setTitle("Fishpy :D");
		add(panelito);
		ImageIcon img = new ImageIcon("src/img1/fishIcon.png");
		setIconImage(img.getImage());
		this.setLocationRelativeTo(null);
		showOnScreen(1, this);
		setVisible(true);
	}

	public static void showOnScreen(int screen, JFrame frame) {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] gd = ge.getScreenDevices();
		int width = 0, height = 0;

		if (screen > -1 && screen < gd.length) {
			width = gd[screen].getDefaultConfiguration().getBounds().width;
			height = gd[screen].getDefaultConfiguration().getBounds().height;
			frame.setLocation(
					((width / 2) - (frame.getSize().width / 2)) + gd[screen].getDefaultConfiguration().getBounds().x,
					((height / 2) - (frame.getSize().height / 2)) + gd[screen].getDefaultConfiguration().getBounds().y);
		}
	}
}
