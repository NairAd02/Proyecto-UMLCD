package util;

import javax.swing.JPanel;

import javax.swing.JLabel;

import Clases.GestorUML;
import Clases.Proyecto;
import Interfaz.FrameDecisorPestannaProyecto;
import Interfaz.Principal;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.Cursor;

public class PanelPestannaProyecto extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Proyecto proyecto;
	private JLabel labelNombreProyecto;
	private JLabel lblX;
	/**
	 * Create the panel.
	 */




	public PanelPestannaProyecto(Proyecto p) {
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setBorder(new LineBorder(new Color(0, 0, 0)));
		this.proyecto = p;
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) { // MEJORAR EL ENCAPSULAMIENTO
				Proyecto proyectoAnterior = GestorUML.getInstancie().getProyectoSeleccionado(); // se guarda el proyecto seleccionado anterior
				GestorUML.getInstancie().setProyectoSeleccionado(proyecto); // se actualiza el proyecto seleccionado
				Principal.getInstance().actualizarColorPanelPestannaProyecto(proyectoAnterior);
				colorPanel();
				Principal.getInstance().actualizarPanelPestannaDiagramas(); // se actualiza la informacion de los digramas con los diagramas del nuevo proyecto
				Principal.getInstance().actualizarEstado(); // se actualiza el estado del panel diagrama
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				setBackground(SystemColor.info);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				colorPanel();
			}
		});
		FlowLayout flowLayout = (FlowLayout) getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);

		labelNombreProyecto = new JLabel(proyecto.getNombre());
		labelNombreProyecto.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add(labelNombreProyecto);

		lblX = new JLabel("X");
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (GestorUML.getInstancie().getProyectoSeleccionado().isModificado()) { // si el proyecto fue modificado
					FrameDecisorPestannaProyecto frameDecisor = new FrameDecisorPestannaProyecto(PanelPestannaProyecto.this);
					frameDecisor.setVisible(true);
					Principal.getInstance().setEnabled(false);
				}
				else // si no
					cerrarProyecto();

			}
			@Override
			public void mouseEntered(MouseEvent e) {

			}
			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		lblX.setFont(new Font("Tahoma", Font.BOLD, 18));
		add(lblX);

		colorPanel();
	}

	public Proyecto getProyecto() {
		return proyecto;
	}
	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}


	// Operaciones

	public void cerrarProyecto () {
		GestorUML.getInstancie().deleteProyecto(proyecto); // se elimina el proyecto
		Principal.getInstance().actualizarPanelProyectos();
		Principal.getInstance().actualizarPanelPestannaDiagramas();
		Principal.getInstance().actualizarEstado(); // se actualiza el estado del panel diagrama
	}

	public void colorPanel () {
		if (GestorUML.getInstancie().getProyectoSeleccionado().equals(proyecto))
			setBackground(SystemColor.activeCaption);
		else
			setBackground(SystemColor.inactiveCaptionBorder);
	}

	// Fin operaciones

}
