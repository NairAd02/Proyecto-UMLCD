package util;


import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import Clases.GestorUML;
import Clases.Metodo;
import Interfaz.ModificarMetodo;
import Interfaz.Principal;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuContextualMetodo extends JPopupMenu {
	private static final long serialVersionUID = 1L;

	private PanelClase pe;

	private Metodo metodo;
	public MenuContextualMetodo(PanelClase p, Metodo m) {
		pe = p;
		metodo = m;
		JMenuItem mntmEliminarMetodo = new JMenuItem("Eliminar Metodo");
		mntmEliminarMetodo.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				GestorUML.getInstancie().getProyectoSeleccionado().getDiagramaSeleccionado().deleteMetodoClase(pe.getClase(), metodo); // Se elimina el metodo de la clase
				pe.actualizarMetodos(); // Se actualiza la informacion de los metodos de la clase
			}
		});

		JMenuItem mntmModificarMetodo = new JMenuItem("Modificar Metodo");
		mntmModificarMetodo.addMouseListener(new MouseAdapter()  {
			@Override
			public void mousePressed(MouseEvent arg0) {
				ModificarMetodo modiMetod = new ModificarMetodo(pe, metodo);
				modiMetod.setVisible(true);
				Principal.getInstance().setEnabled(false);
			}
		});
		add(mntmModificarMetodo);
		add(mntmEliminarMetodo);
	}





}
