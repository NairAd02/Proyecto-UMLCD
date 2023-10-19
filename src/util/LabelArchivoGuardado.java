package util;

import javax.swing.JLabel;





import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import Clases.Diagrama;
import Clases.GestorUML;
import Interfaz.DiagramasAbrir;
import Interfaz.Principal;
import Logica.ManejoDirectorios;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;


public class LabelArchivoGuardado extends JLabel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Principal pe;
	private DiagramasAbrir di;


	public Principal getPe() {
		return pe;
	}

	public DiagramasAbrir getDi() {
		return di;
	}

	public LabelArchivoGuardado(String nombre, Principal p, DiagramasAbrir d){
		pe = p;
		di = d;
		setOpaque(true);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setBackground(new Color(95, 158, 160));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				setBackground(new Color(153, 204, 204));
			}
			@Override
			public void mousePressed(MouseEvent e) {

				cargarArchivo(); 


			}
		});
		setHorizontalAlignment(SwingConstants.LEFT);
		setText(nombre);
		setBackground(new Color(153, 204, 204));
		setFont(new Font("Dialog", Font.BOLD, 24));
		setVisible(true);

	}

	public void cargarArchivo(){
		Diagrama diagrama = null;
		try {
			diagrama = (Diagrama) ManejoDirectorios.recuperarArchivo(getText());

			if (!GestorUML.getInstancie().comprobarExistenciaDiagrama(diagrama)) { // Si no esta dentro de los diagramas previamente cargados
				diagrama.setEstadoModificacion(false); // se indica que su estado de modificacion es false en caso de que sea true
				GestorUML.getInstancie().addDiagrama(diagrama); // se carga en memoria
			}
			else // se esta dentro de los diagramas previamente cargadoes
				GestorUML.getInstancie().setDiagramaSeleccionado(diagrama); // se actualiza como diagrama cargado

			pe.actualizarPanelPestannaDiagramas(); // se actualiza la pestaña de diagramas
			pe.setDiagrama(diagrama);
			
			if (GestorUML.getInstancie().getDiagramas().size() == 1) // si fue el primer diagrama que se añadio entonces se crea un lienzo
				crearLienzo();
			else // si no, solo se actualiza
				Principal.getInstancie().actualizarLienzo();
			
			Principal.getInstancie().setEnabled(true);
			di.dispose();


		} catch (FileNotFoundException e1) {

			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {

			e1.printStackTrace();
		} catch (IOException e1) {

			e1.printStackTrace();
		}
	}

	public void crearLienzo(){
		pe.habilitarPrograma();

	}

}
