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


public class LabelDiagrama extends JLabel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Principal pe;
	private DiagramasAbrir di;
	private Diagrama diagrama;


	public Principal getPe() {
		return pe;
	}

	public DiagramasAbrir getDi() {
		return di;
	}

	public LabelDiagrama(Diagrama diagrama, DiagramasAbrir diagramaAbrir){

		this.di = diagramaAbrir;
		this.diagrama = diagrama;
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
				mostrarDiagrama();
				Principal.getInstance().setEnabled(true);
				di.dispose();
			}
		});
		setHorizontalAlignment(SwingConstants.LEFT);
		setText(diagrama.getNombre());
		setBackground(new Color(153, 204, 204));
		setFont(new Font("Dialog", Font.BOLD, 24));
		setVisible(true);

	}

	private void mostrarDiagrama () {
		GestorUML.getInstancie().getProyectoSeleccionado().mostrarDiagrama(diagrama);
		Principal.getInstance().actualizarPanelPestannaDiagramas(); // se actualiza la informacion de los diagramas
		Principal.getInstance().actualizarEstado(); // se actualiza el estado del panel diagrama
	}

}
