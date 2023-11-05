package util;

import javax.swing.JPanel;

import Clases.Diagrama;
import Clases.GestorUML;
import Interfaz.DiagramasAbrir;
import Interfaz.Principal;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.SystemColor;

public class PanelDiagrama extends JPanel {


	private static final long serialVersionUID = 1L;
	private DiagramasAbrir di;
	private Diagrama diagrama;
	private JLabel lblDiagrama;
	private JLabel lblX;

	public PanelDiagrama(Diagrama d, DiagramasAbrir diagramaAbrir) {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mostrarDiagrama();
				Principal.getInstance().setEnabled(true);
				di.dispose();
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
		FlowLayout flowLayout = (FlowLayout) getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		this.di = diagramaAbrir;
		this.diagrama = d;
		setBackground(new Color(153, 204, 204));
		lblDiagrama = new JLabel(this.diagrama.getNombre());
		lblDiagrama.setForeground(SystemColor.textText);
		lblDiagrama.setFont(new Font("Tahoma", Font.BOLD, 20));

		add(lblDiagrama);

		lblX = new JLabel("X");
		lblX.setForeground(SystemColor.textText);
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				GestorUML.getInstancie().getProyectoSeleccionado().eliminacionFisicaDiagrama(diagrama); // se elimina fisicamente el diagrama
				di.llenarPanel();
				Principal.getInstance().actualizarPanelPestannaDiagramas();
				Principal.getInstance().actualizarEstado();
			}
			@Override
			public void mouseEntered(MouseEvent e) {

			}
			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		lblX.setFont(new Font("Tahoma", Font.BOLD, 24));
		add(lblX);
	}

	private void mostrarDiagrama () {
		GestorUML.getInstancie().getProyectoSeleccionado().mostrarDiagrama(diagrama);
		Principal.getInstance().actualizarPanelPestannaDiagramas(); // se actualiza la informacion de los diagramas
		Principal.getInstance().actualizarEstado(); // se actualiza el estado del panel diagrama
	}

}
