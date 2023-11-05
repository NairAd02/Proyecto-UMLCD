package util;

import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;

import Clases.Clase;
import Clases.GestorUML;
import Interfaz.AgregarAtributo;
import Interfaz.AgregarMetodo;
import Interfaz.EditarClase;
import Interfaz.EliminarClase;
import Interfaz.Principal;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenu;

public class MenuContextual extends JPopupMenu {
	private static final long serialVersionUID = 1L;

	private PanelClase pe;
	private Clase clase;
	private JMenuItem mntmGenerarMtodosGets;
	private JMenuItem mntmAadirConstructorCon;

	public MenuContextual(PanelClase p, Clase c) {
		pe=p;
		clase = c;
		JMenuItem mntmAadirAtributo = new JMenuItem("A\u00F1adir Atributo");
		mntmAadirAtributo.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				AgregarAtributo atri = new AgregarAtributo(pe, clase);
				atri.setVisible(true);
				Principal.getInstance().setEnabled(false);
			}
		});

		JMenuItem mntmEditarClase = new JMenuItem("Editar Clase");
		mntmEditarClase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditarClase edi = new EditarClase(pe, clase);
				pe.setMover(false);
				edi.setVisible(true);
				Principal.getInstance().setEnabled(false);
			}
		});
		add(mntmEditarClase);
		add(mntmAadirAtributo);

		JMenuItem mntmAadirMetodo = new JMenuItem("A\u00F1adir Metodo");
		mntmAadirMetodo.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				AgregarMetodo metod = new AgregarMetodo(pe, clase);
				metod.setVisible(true);
				Principal.getInstance().setEnabled(false);
			}
		});
		add(mntmAadirMetodo);

		this.addItemGenerarGetsAndSets();
		JMenuItem mntmEliminar = new JMenuItem("Eliminar");
		mntmEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				EliminarClase eli = new EliminarClase(pe, clase);
				eli.setVisible(true);
				Principal.getInstance().setEnabled(false);

			}
		});


		add(mntmEliminar);
	}

	private void addItemGenerarGetsAndSets () {
		mntmGenerarMtodosGets = new JMenuItem("Generar m\u00E9todos gets y sets");
		mntmGenerarMtodosGets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestorUML.getInstancie().getProyectoSeleccionado().getDiagramaSeleccionado().generarGetAndSetAtributos(clase);
				pe.actualizarMetodos(); // se actualiza la infomacion de los metodos del panel clase		
			}
		});

		JMenu mnGenerarConstructor = new JMenu("Generar constructor");
		add(mnGenerarConstructor);

		JMenuItem mntmAadirConstructor = new JMenuItem("A\u00F1adir constructor");
		mntmAadirConstructor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		mnGenerarConstructor.add(mntmAadirConstructor);

		mntmAadirConstructorCon = new JMenuItem("A\u00F1adir constructor con campos");
		mntmAadirConstructorCon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestorUML.getInstancie().getProyectoSeleccionado().getDiagramaSeleccionado().generarConstructorConTodosAtributos(clase); // se le genera un constructor a la clase
				pe.actualizarMetodos(); // se actualiza la informacion de los metodos del panel clase
			}
		});
		mnGenerarConstructor.add(mntmAadirConstructorCon);
		add(mntmGenerarMtodosGets);
	}
}
