package oyentes;

import vistas.FormularioParaPeces;
import vistas.FormularioParaPecesEditar;
import vistas.Ventana;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.Normalizer.Form;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import controladores.ControladorVentas;
import modelos.Venta;

public class EscuchaVentas implements ActionListener{
	
	
	ControladorVentas controladorVentas;
	JTable tabla;
	Ventana forum;
	
	
	public EscuchaVentas(ControladorVentas controladorVentas, JTable tabla) {
		
		this.controladorVentas = controladorVentas;
		this.tabla= tabla;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		switch(e.getActionCommand()) {
		case "Nueva fila":

		//controladorVentas.agregarFila();
		FormularioParaPeces forum = new FormularioParaPeces();
		Ventana ventanaFormulario= new Ventana(forum);
		ventanaFormulario.addWindowListener(new WindowAdapter() {
			//for closing
			@Override
			public void windowDeactivated(WindowEvent e) {
				controladorVentas.cargarTabla();
			}
			//for closed
	
			@Override
			public void windowClosed(WindowEvent e) {
			}
		});

			break;
		case "Borrar todo":
			controladorVentas.borrarTodo();
			break;
		case "Guardar":
			controladorVentas.guardarEnArchivo();
			break;
		case "Cargar":
			controladorVentas.cargarTabla();
			break;
		case "Editar":
			
			FormularioParaPecesEditar forumEditar = new FormularioParaPecesEditar(tabla);
			Ventana ventanaFormularioEditar= new Ventana(forumEditar);
			
			System.out.println("hola");
			ventanaFormularioEditar.addWindowListener(new WindowAdapter() {
				//for closing
				@Override
				public void windowDeactivated(WindowEvent e) {
					controladorVentas.cargarTabla();
				}
				//for closed
		
				@Override
				public void windowClosed(WindowEvent e) {
				}
			});
			break;
		}
	}

	
	
}
