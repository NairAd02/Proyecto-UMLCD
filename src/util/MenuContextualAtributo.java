package util;


import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;

import Clases.Atributo;
import Clases.GestorUML;
import Interfaz.ModificarAtributo;
import Interfaz.Principal;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuContextualAtributo  extends JPopupMenu{
	private static final long serialVersionUID = 1L;
	private Atributo atributo;

	private PanelClase pe;
	public MenuContextualAtributo(PanelClase p, Atributo a){
		pe = p;
		atributo = a;

		JMenuItem mntmEliminaratributo = new JMenuItem("EliminarAtributo");
		mntmEliminaratributo.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				GestorUML.getInstancie().getProyectoSeleccionado().getDiagramaSeleccionado().deleteAtributoClase(pe.getClase(), atributo); // se elimina el atributo de la clase
				pe.actualizarAtributos(); // se actualiza la informacion de los atributos de la clase
			}
		});

		JMenuItem mntmModificarAtributo = new JMenuItem("Modificar Atributo");
		mntmModificarAtributo.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {

				ModificarAtributo modiAtri = new ModificarAtributo(pe, atributo);
				modiAtri.setVisible(true);
				Principal.getInstance().setEnabled(false);
			}
		});
		add(mntmModificarAtributo);
		add(mntmEliminaratributo);
		
		JMenuItem mntmGenerarGetsY = new JMenuItem("Generar m\u00E9todos get y set");
		mntmGenerarGetsY.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
					GestorUML.getInstancie().getProyectoSeleccionado().getDiagramaSeleccionado().generarGetAndSetAtributo(pe.getClase(), atributo);
					pe.actualizarMetodos(); // se actualiza la infomarcion de los metodos en el panel clase		
			}
		});
		add(mntmGenerarGetsY);


	}



}
