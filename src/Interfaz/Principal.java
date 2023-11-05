package Interfaz;

import grafo.LinkedGraph;
import grafo.Vertex;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Frame;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.ComponentOrientation;

import javax.swing.SwingConstants;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.JSeparator;
import javax.swing.border.LineBorder;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JScrollPane;

import util.MenuContextualLienzo;
import util.PanelClase;
import util.PanelPenstannaDiagrama;
import util.PanelPestannaProyecto;
import Clases.Abstracta;
import Clases.Clase;
import Clases.Concreta;
import Clases.Diagrama;
import Clases.GestorUML;
import Clases.Proyecto;
import Logica.ManejoDirectorios;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Principal extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JPanel panelSuperior;
	private JPanel panelLateral;
	private JLabel lblOpciones;
	private JPanel panelOpciones;
	private JSeparator separator;
	private JPanel panelAddClase;
	private JPanel panelAddHerencia;
	private JPanel panelAddAgregacion;
	private JPanel panelAddComposiscion;
	private JPanel panelMenuDiagramas;
	private JMenuBar menuBar;
	private JMenu mnArchivo;
	private JMenuBar menuBar_1;
	private JMenu mnNewMenu;
	private JPanel panelProyectos;
	private JPanel panelDiagramas;
	private JPanel panelPestannaDiagramas;
	private JScrollPane scrollPane;
	private Lienzo lienzo;
	private boolean isInsertar, isRelacion;
	private String radioBotonTipoClase;
	private String nombreClase;
	private PanelInicio panelInicio;
	private static Principal principal; // singlenton
	private JMenuItem mntmNuevoProyecto;
	private JMenuItem mntmAbrirProyecto;
	private JMenuItem menuItemGuardarProyecto;
	private JPanel panelSuperiorPanelDiagramas;
	private JPanel panelOpcionesDiagramas;
	private JLabel lblAadirDiagrama;
	private JLabel lblVerDiagramas;
	private JPanel panelMarcoSuperior;
	private JLabel lblSalir;
	private JMenuItem mntmGuardarProyectos;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 * 
	 *
	 */


	public static Principal getInstance() {
		if (principal == null)
			principal = new Principal();


		return principal;

	}
	private Principal() {
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaptionText);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		panelSuperior = new JPanel();
		panelSuperior.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelSuperior.setBackground(new Color(47, 79, 79));
		contentPane.add(panelSuperior, BorderLayout.NORTH);
		panelSuperior.setLayout(new BorderLayout(0, 0));

		panelMenuDiagramas = new JPanel();
		panelMenuDiagramas.setBackground(new Color(47, 79, 79));
		panelSuperior.add(panelMenuDiagramas, BorderLayout.WEST);

		menuBar = new JMenuBar();
		menuBar.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelMenuDiagramas.add(menuBar);

		mnArchivo = new JMenu("Archivo");
		mnArchivo.setIcon(new ImageIcon(Principal.class.getResource("/images/home.png")));
		mnArchivo.setForeground(Color.BLACK);
		mnArchivo.setFont(new Font("Segoe UI", Font.BOLD, 18));
		menuBar.add(mnArchivo);

		mntmNuevoProyecto = new JMenuItem("Nuevo Proyecto");
		mntmNuevoProyecto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrameNuevoProyecto frameNuevoProyecto = new FrameNuevoProyecto(Principal.this);
				frameNuevoProyecto.setVisible(true);
				setEnabled(false);
			}
		});
		mntmNuevoProyecto.setForeground(Color.BLACK);
		mntmNuevoProyecto.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmNuevoProyecto.setBorder(new LineBorder(new Color(0, 0, 0)));
		mnArchivo.add(mntmNuevoProyecto);

		mntmAbrirProyecto = new JMenuItem("Abrir Proyecto");
		mntmAbrirProyecto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Se crea un sistema de exploracion
				
				 JFileChooser fileChooser = new JFileChooser();
			        int returnValue = fileChooser.showOpenDialog(null);
			        if (returnValue == JFileChooser.APPROVE_OPTION) {// si se seleccionó un archivo
			            File selectedFile = fileChooser.getSelectedFile();
			            
						try {
							Proyecto proyecto = (Proyecto) ManejoDirectorios.recuperarArchivo(selectedFile.getAbsolutePath());
							proyecto.restablecerEstadoDeModificacion(); // se restablece el estado de modificación del proyecto a false (false es que no está modificado)
				    		GestorUML.getInstancie().mostrarProyecto(proyecto);
				    		Principal.getInstance().actualizarPanelProyectos(); // se actualiza el panel de proyectos
				    		Principal.getInstance().actualizarPanelPestannaDiagramas(); // se actualiza el panel de los diagramas con los diagramas del proyecto
				    		Principal.getInstance().actualizarEstado(); // se actualiza el estado del panel diagrama
						} catch (FileNotFoundException e1) {
							
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							
							e1.printStackTrace();
						} catch (IOException e1) {
							// No es compatible el archivo seleccionado
							e1.printStackTrace();
						}
			    		
			        }
				/*
				FrameProyectosAbrir frameProyectosAbrir = new FrameProyectosAbrir(Principal.this);
				frameProyectosAbrir.setVisible(true);
				setEnabled(false)*/
			}
		});
		mntmAbrirProyecto.setForeground(Color.BLACK);
		mntmAbrirProyecto.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmAbrirProyecto.setBorder(new LineBorder(new Color(0, 0, 0)));
		mnArchivo.add(mntmAbrirProyecto);

		menuItemGuardarProyecto = new JMenuItem("Guardar");
		menuItemGuardarProyecto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (GestorUML.getInstancie().getProyectoSeleccionado() != null) {
					try {
						ManejoDirectorios.guardarArchivo(GestorUML.getInstancie().getProyectoSeleccionado(), GestorUML.getInstancie().getProyectoSeleccionado().getRutaDeGuardado());
						GestorUML.getInstancie().getProyectoSeleccionado().restablecerEstadoDeModificacion(); // restablecer estado de modificacion a false, una vez se haya guardado el proyecto
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		menuItemGuardarProyecto.setForeground(Color.BLACK);
		menuItemGuardarProyecto.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnArchivo.add(menuItemGuardarProyecto);
		
		mntmGuardarProyectos = new JMenuItem("Guardar Proyectos");
		mntmGuardarProyectos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					GestorUML.getInstancie().guardarAllProyectos(); // se guardan todos los proyectos cargados
				} catch (FileNotFoundException e1) {
					
					e1.printStackTrace();
				} catch (IOException e1) {
					
					e1.printStackTrace();
				} 
			}
		});
		mntmGuardarProyectos.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnArchivo.add(mntmGuardarProyectos);

		menuBar_1 = new JMenuBar();
		menuBar_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelMenuDiagramas.add(menuBar_1);

		mnNewMenu = new JMenu("Herramientas");
		mnNewMenu.setBorder(new LineBorder(new Color(0, 0, 0)));
		mnNewMenu.setIcon(new ImageIcon(Principal.class.getResource("/images/gear.png")));
		mnNewMenu.setForeground(Color.BLACK);
		mnNewMenu.setFont(new Font("Segoe UI", Font.BOLD, 18));
		menuBar_1.add(mnNewMenu);
		
		panelMarcoSuperior = new JPanel();
		panelMarcoSuperior.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelMarcoSuperior.setBackground(new Color(47, 79, 79));
		FlowLayout flowLayout_2 = (FlowLayout) panelMarcoSuperior.getLayout();
		flowLayout_2.setAlignment(FlowLayout.RIGHT);
		panelSuperior.add(panelMarcoSuperior, BorderLayout.NORTH);
		
		lblSalir = new JLabel("");
		lblSalir.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (GestorUML.getInstancie().comprobarEstadoModificacion()) { // Si hubo alguna modificación
					FrameDecisorCerrarProyecto frameDecisorCerrarProyecto = new FrameDecisorCerrarProyecto();
					frameDecisorCerrarProyecto.setVisible(true);
					setEnabled(false);
				}
				else
					System.exit(0); // se finaliza la ejecución del programa
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblSalir.setBackground(SystemColor.red);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblSalir.setBackground(new Color(47, 79, 79));
			}
		});
		lblSalir.setBackground(new Color(47, 79, 79));
		lblSalir.setOpaque(true);
		lblSalir.setIcon(new ImageIcon(Principal.class.getResource("/images/cross.png")));
		panelMarcoSuperior.add(lblSalir);

		panelLateral = new JPanel();
		panelLateral.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelLateral.setBackground(SystemColor.window);
		contentPane.add(panelLateral, BorderLayout.WEST);
		panelLateral.setLayout(new BorderLayout(0, 0));

		lblOpciones = new JLabel("  Diagramas->   ");
		lblOpciones.setFont(new Font("Tahoma", Font.BOLD, 21));
		panelLateral.add(lblOpciones, BorderLayout.NORTH);

		panelOpciones = new JPanel();
		panelOpciones.setBackground(SystemColor.window);
		panelLateral.add(panelOpciones, BorderLayout.CENTER);
		panelOpciones.setLayout(null);

		separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		separator.setForeground(Color.BLACK);
		separator.setBounds(0, 14, 171, 2);
		panelOpciones.add(separator);

		panelAddClase = new JPanel();
		panelAddClase.setBackground(new Color(0,58,87));
		panelAddClase.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelAddClase.setBounds(20, 21, 130, 32);
		panelOpciones.add(panelAddClase);

		JLabel labelClase = new JLabel("Clase");
		labelClase.setForeground(SystemColor.textHighlightText);
		labelClase.setFont(new Font("Tahoma", Font.BOLD, 18));
		panelAddClase.add(labelClase);

		panelAddHerencia = new JPanel();
		panelAddHerencia.setBackground(new Color(0,58,87));
		panelAddHerencia.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelAddHerencia.setBounds(20, 64, 130, 32);
		panelOpciones.add(panelAddHerencia);

		JLabel labelHerencia = new JLabel("Herencia");
		labelHerencia.setForeground(SystemColor.textHighlightText);
		labelHerencia.setFont(new Font("Tahoma", Font.BOLD, 18));
		panelAddHerencia.add(labelHerencia);

		panelAddComposiscion = new JPanel();
		panelAddComposiscion.setBackground(new Color(0,58,87));
		panelAddComposiscion.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelAddComposiscion.setBounds(20, 107, 130, 32);
		panelOpciones.add(panelAddComposiscion);

		JLabel labelComposicion = new JLabel("Composici\u00F3n");
		labelComposicion.setForeground(SystemColor.textHighlightText);
		labelComposicion.setFont(new Font("Tahoma", Font.BOLD, 18));
		panelAddComposiscion.add(labelComposicion);

		panelAddAgregacion = new JPanel();
		panelAddAgregacion.setBackground(new Color(0,58,87));
		panelAddAgregacion.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelAddAgregacion.setBounds(20, 150, 130, 32);
		panelOpciones.add(panelAddAgregacion);

		JLabel labelAgregacion = new JLabel("Agregaci\u00F3n");
		labelAgregacion.setForeground(SystemColor.textHighlightText);
		labelAgregacion.setFont(new Font("Tahoma", Font.BOLD, 18));
		panelAddAgregacion.add(labelAgregacion);

		JPanel panelInferior = new JPanel();
		panelInferior.setBackground(SystemColor.window);
		panelInferior.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(panelInferior, BorderLayout.SOUTH);
		panelInferior.setLayout(new BorderLayout(0, 0));

		JLabel lblProyectos = new JLabel("  Proyectos ->");
		lblProyectos.setFont(new Font("Tahoma", Font.BOLD, 25));
		panelInferior.add(lblProyectos, BorderLayout.WEST);

		panelProyectos = new JPanel();
		panelProyectos.setBackground(SystemColor.window);
		FlowLayout flowLayout_1 = (FlowLayout) panelProyectos.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panelInferior.add(panelProyectos, BorderLayout.CENTER);

		panelDiagramas = new JPanel();
		panelDiagramas.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelDiagramas.setBackground(SystemColor.inactiveCaptionBorder);
		contentPane.add(panelDiagramas, BorderLayout.CENTER);
		panelDiagramas.setLayout(new BorderLayout(0, 0));

		panelSuperiorPanelDiagramas = new JPanel();
		panelDiagramas.add(panelSuperiorPanelDiagramas, BorderLayout.NORTH);
		panelSuperiorPanelDiagramas.setLayout(new BorderLayout(0, 0));
		
		panelPestannaDiagramas = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelPestannaDiagramas.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panelPestannaDiagramas.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelPestannaDiagramas.setBackground(SystemColor.window);
		panelSuperiorPanelDiagramas.add(panelPestannaDiagramas, BorderLayout.CENTER);
		
		panelOpcionesDiagramas = new JPanel();
		panelOpcionesDiagramas.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelOpcionesDiagramas.setBackground(SystemColor.window);
		panelSuperiorPanelDiagramas.add(panelOpcionesDiagramas, BorderLayout.EAST);
		panelOpcionesDiagramas.setLayout(new GridLayout(0, 1, 0, 0));
		panelOpcionesDiagramas.setVisible(false);
		lblAadirDiagrama = new JLabel("A\u00F1adir Diagrama");
		lblAadirDiagrama.setHorizontalAlignment(SwingConstants.CENTER);
		lblAadirDiagrama.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblAadirDiagrama.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				FrameNuevoDiagrama frameNuevoDiagrama = new FrameNuevoDiagrama(Principal.this);
				frameNuevoDiagrama.setVisible(true);
				setEnabled(false);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblAadirDiagrama.setForeground(SystemColor.white);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblAadirDiagrama.setForeground(SystemColor.inactiveCaptionText);
			}
		});
		lblAadirDiagrama.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblAadirDiagrama.setForeground(SystemColor.inactiveCaptionText);
		lblAadirDiagrama.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelOpcionesDiagramas.add(lblAadirDiagrama);
		
		lblVerDiagramas = new JLabel("Ver Diagramas");
		lblVerDiagramas.setHorizontalAlignment(SwingConstants.CENTER);
		lblVerDiagramas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblVerDiagramas.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				DiagramasAbrir frameDiagramasAbrir = new DiagramasAbrir(Principal.this);
				frameDiagramasAbrir.setVisible(true);
				setEnabled(false);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblVerDiagramas.setForeground(SystemColor.white);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblVerDiagramas.setForeground(SystemColor.inactiveCaptionText);
			}
		});
		lblVerDiagramas.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblVerDiagramas.setForeground(SystemColor.inactiveCaptionText);
		lblVerDiagramas.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelOpcionesDiagramas.add(lblVerDiagramas);

		// estado inicial del panel diagrama
		panelInicio = new PanelInicio(0);
		panelDiagramas.add(panelInicio, BorderLayout.CENTER);
		
	}




	public String getNombreClase() {
		return nombreClase;
	}
	public void setNombreClase(String nombreClase) {
		this.nombreClase = nombreClase;
	}
	public boolean isInsertar() {
		return isInsertar;
	}
	public void setInsertar(boolean isInsertar) {
		this.isInsertar = isInsertar;
	}
	public boolean isRelacion() {
		return isRelacion;
	}
	public void setRelacion(boolean isRelacion) {
		this.isRelacion = isRelacion;
	}
	public String getRadioBotonTipoClase() {
		return radioBotonTipoClase;
	}
	public void setRadioBotonTipoClase(String radioBotonTipoClase) {
		this.radioBotonTipoClase = radioBotonTipoClase;
	}
	public Lienzo getLienzo() {
		return lienzo;
	}
	public void setLienzo(Lienzo lienzo) {
		this.lienzo = lienzo;
	}
	public void accionesLienzo(){
		lienzo.setBackground(SystemColor.inactiveCaptionBorder);
		lienzo.setPreferredSize(new Dimension(2000,2000));
		lienzo.setLayout(null);
		lienzo.setComponentPopupMenu(new MenuContextualLienzo(Principal.this));
		lienzo.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				boolean x=true;

				if(isInsertar){
					try {
						addClase(e.getX(), e.getY());
						actualizarLienzo();
					} catch (Exception e1) {

						e1.printStackTrace();
					}

					lienzo.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					isInsertar = false;
				}

			}
		});
		lienzo.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				if(isInsertar)
					lienzo.setCursor(new Cursor(Cursor.HAND_CURSOR));
				else if(isRelacion){
					lienzo.setCursor(new Cursor(Cursor.TEXT_CURSOR));


					lienzo.repaint();
					lienzo.revalidate();

				}
				else 
					lienzo.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			}
		});
	}

	public void addClase (int posiscionX, int posicionY) throws Exception {
		if (radioBotonTipoClase.equalsIgnoreCase("Concreta")){ // Creamos Clase Concreta
			GestorUML.getInstancie().getProyectoSeleccionado().getDiagramaSeleccionado().addClase(new Concreta(nombreClase, posiscionX, posicionY, 100, 100));
		}
		else if (radioBotonTipoClase.equalsIgnoreCase("Abstracta")){ // Creamos Clase Abstracta
			GestorUML.getInstancie().getProyectoSeleccionado().getDiagramaSeleccionado().addClase(new Abstracta(nombreClase, posiscionX, posicionY, 100, 100));	
		}
	}

	// METODOS DE ACTUALIZACION


	// METODOS PARA EL CAMBIO DE ESTADO

	public void actualizarEstado () {

		if (GestorUML.getInstancie().getProyectoSeleccionado() == null) { // si el estado actual del programa es: que no hay ningun proyecto cargado todavia
			// crear panel inicio proyecto
			
			this.crearPanelInicio(0);
		}
		else if (GestorUML.getInstancie().getProyectoSeleccionado().getDiagramaSeleccionado() == null) { // si el estado actual del proyecto es: que no tiene ningun diagrama cargado todavia
			// crear panel inicio diagrama
			this.crearPanelInicio(1);
		}
		else{ // si no es ninguna de las dos anteriores
			// crear y actualizar lienzo
			this.crearLienzo();
		}
	}


	public void crearLienzo(){ // Metodo para crear y añadir un lienzo
		panelOpcionesDiagramas.setVisible(true); // se hace visible el panel de las opciones de los diagramas
		lienzo = new Lienzo();
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(lienzo); // se añade al scrollpane
		panelDiagramas.remove(panelDiagramas.getComponent(panelDiagramas.getComponentCount() - 1)); // se elimina el ultimo panel insertado
		panelDiagramas.add(scrollPane, BorderLayout.CENTER);
		accionesLienzo();
		actualizarLienzo();
	}

	public void crearPanelInicio (int estado) { // Metodo para crear y añadir el panel de inicio de diagrama o de proyecto
		if (estado == 1)
			panelOpcionesDiagramas.setVisible(true); // se hace visible el panel de las opciones de los diagramas
		else if (estado == 0)
			panelOpcionesDiagramas.setVisible(false); // se oculta el panel de las opciones de los diagramas
		
		panelInicio = new PanelInicio(estado);
		panelDiagramas.remove(panelDiagramas.getComponent(panelDiagramas.getComponentCount() - 1)); // se elimina el ultimo panel insertado
		panelDiagramas.add(panelInicio, BorderLayout.CENTER);	

	}


	// FIN DE METODOS PARA EL CAMBIO DE ESTADO

	public void actualizarPanelProyectos () { // Metodo para actualizar las pestañas de los proyectos
		Iterator<Proyecto> iterProyectos = GestorUML.getInstancie().getProyectos().iterator(); // Se obtienen los proyectos
		panelProyectos.removeAll();

		while (iterProyectos.hasNext()) {
			panelProyectos.add(new PanelPestannaProyecto(iterProyectos.next()));
		}



		panelProyectos.repaint();
		panelProyectos.revalidate();
	}


	public void actualizarColorPanelPestannaProyecto (Proyecto proyectoAnterior) { // Metodo para actualizar el color de la pestaña antes seleccionadad
		boolean parada = false;
		// iniciamos una busqueda

		for (int i = 0; i < this.panelProyectos.getComponentCount() &&  !parada; i++) {
			PanelPestannaProyecto panelAux = (PanelPestannaProyecto) this.panelProyectos.getComponent(i);
			if (panelAux.getProyecto().equals(proyectoAnterior)) {
				panelAux.colorPanel(); // se establece el color
				parada = true; // se le pone fin al bucle

			}		
		}
		repaint();
		revalidate();
	}


	public void actualizarLienzo () {
		LinkedGraph clases =  GestorUML.getInstancie().getProyectoSeleccionado().getDiagramaSeleccionado().getGrafoClases();

		// Temporalmente se muestran solo las clases sin sus relaciones

		Iterator<Vertex> iter = clases.getVertices().iterator();
		lienzo.removeAll(); // se remueven todas la clases para volver a insertarlas
		while (iter.hasNext()) {

			Clase claseAux = (Clase) iter.next().getInfo();
			lienzo.add(new PanelClase(claseAux)); // se crea un panel para guardar la informacion de la clase y se añade en el lienzo
		}

		lienzo.repaint();
		lienzo.revalidate();
	}

	public void actualizarPanelPestannaDiagramas () { // Metodo para actualizar las pestañas diagrama

		panelPestannaDiagramas.removeAll();
		if (GestorUML.getInstancie().getProyectoSeleccionado() != null){ // siempre que haya un proyecto seleccionado
			Iterator<Diagrama> diagramasIter = GestorUML.getInstancie().getProyectoSeleccionado().getDiagramasMostrados().iterator();
			while (diagramasIter.hasNext()) {
				this.panelPestannaDiagramas.add(new PanelPenstannaDiagrama(diagramasIter.next())); // se muestran en el panel todos los diagramas abiertos
			}
		}
		panelPestannaDiagramas.repaint();
		panelPestannaDiagramas.revalidate();
	}

	public void actualizarColorPanelPestannaDiagrama (Diagrama diagramaAnterior) { // Metodo para actualizar el color de la pestaña antes seleccionadad
		boolean parada = false;
		// iniciamos una busqueda

		for (int i = 0; i < this.panelPestannaDiagramas.getComponentCount() &&  !parada; i++) {
			PanelPenstannaDiagrama panelAux = (PanelPenstannaDiagrama) this.panelPestannaDiagramas.getComponent(i);
			if (panelAux.getDiagrama().equals(diagramaAnterior)) {
				panelAux.colorPanel(); // se establece el color
				parada = true; // se le pone fin al bucle

			}		
		}
		repaint();
		revalidate();
	}
}
