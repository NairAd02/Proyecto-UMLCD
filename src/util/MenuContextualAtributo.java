package util;


import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import Clases.Atributo;
import Interfaz.ModificarAtributo;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
				pe.getClase().eliminarAtributo(atributo);
				pe.actualizarAtributos();
			}
		});

		JMenuItem mntmModificarAtributo = new JMenuItem("Modificar Atributo");
		mntmModificarAtributo.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {

				ModificarAtributo modiAtri = new ModificarAtributo(pe, atributo);
				modiAtri.setVisible(true);
				pe.getPe().setEnabled(false);
			}
		});
		add(mntmModificarAtributo);
		add(mntmEliminaratributo);


	}



}
