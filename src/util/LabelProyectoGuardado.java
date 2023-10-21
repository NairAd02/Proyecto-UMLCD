package util;

import javax.swing.JLabel;

import Clases.GestorUML;
import Clases.Proyecto;
import Interfaz.FrameProyectosAbrir;
import Interfaz.Principal;
import Logica.ManejoDirectorios;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.Color;
import java.awt.Font;

public class LabelProyectoGuardado extends JLabel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombreProyecto;
	private FrameProyectosAbrir frameProyectosAbrir;

	public LabelProyectoGuardado (FrameProyectosAbrir f, String n) {
		setOpaque(true);
		setFont(new Font("Tahoma", Font.BOLD, 24));
		this.nombreProyecto = n;
		this.frameProyectosAbrir = f;
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					cargarProyecto();
					Principal.getInstance().setEnabled(true);
					frameProyectosAbrir.dispose();		

				} catch (FileNotFoundException e1) {

					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {

					e1.printStackTrace();
				} catch (IOException e1) {

					e1.printStackTrace();
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				setBackground(new Color(95, 158, 160));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				setBackground(new Color(153, 204, 204));
			}
		});
		setBackground(new Color(153, 204, 204));
		setText(nombreProyecto); // se le asigna nombre al label



	}

	private void cargarProyecto () throws FileNotFoundException, ClassNotFoundException, IOException {
		Proyecto proyecto =	(Proyecto) ManejoDirectorios.recuperarArchivo(nombreProyecto);
		proyecto.restablecerEstadoDeModificacion(); // se restablece el estado de modificación del proyecto a false (false es que no está modificado)
		GestorUML.getInstancie().mostrarProyecto(proyecto);
		Principal.getInstance().actualizarPanelProyectos(); // se actualiza el panel de proyectos
		Principal.getInstance().actualizarPanelPestannaDiagramas(); // se actualiza el panel de los diagramas con los diagramas del proyecto
		Principal.getInstance().actualizarEstado(); // se actualiza el estado del panel diagrama
	}

}
