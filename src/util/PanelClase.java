package util;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import javax.swing.border.LineBorder;

import mensajesError.HerenciaError;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Cursor;

import Clases.Atributo;
import Clases.Clase;
import Clases.Diagrama;
import Clases.Metodo;
import Interfaz.EditarClase;
import Interfaz.EliminarClase;
import Interfaz.Principal;

import java.awt.Font;
import java.awt.SystemColor;
import java.util.List;

public class PanelClase extends JPanel {
	private static final long serialVersionUID = 1L;

	int mouseX;
	int mouseY;
	private transient Principal pe;
	private Point top, bottom, left, right;
	private PanelClase claseSeleccionada;
	private JLabel lblNombreclase;
	private JPanel panelNombreClase;
	private JPanel panelAtributos;
	private JPanel panelMetodos;
	private JLabel labelSeleccionado;
	private Point flechaFinal;
	private Clase clase;
	private boolean mover;


	/**
	 * Create the panel.
	 */

	public Clase getClase() {
		return clase;
	}
	public void setClase(Clase clase) {
		this.clase = clase;
	}

	public boolean isMover() {
		return mover;
	}
	public void setMover(boolean mover) {
		this.mover = mover;
	}
	public Principal getPe() {
		return pe;
	}
	public void setPe(Principal pe) {
		this.pe = pe;
	}


	public PanelClase getClaseSeleccionada() {
		return claseSeleccionada;
	}
	public void setClaseSeleccionada(PanelClase panelEliminar) {
		this.claseSeleccionada = panelEliminar;
	}

	public JLabel getLblNombreclase() {
		return lblNombreclase;
	}
	public JPanel getPanel() {
		return panelNombreClase;
	}
	public void setPanel(JPanel panel) {
		this.panelNombreClase = panel;
	}
	public JPanel getPanelNombreClase() {
		return panelNombreClase;
	}
	public JPanel getPanelAtributos() {
		return panelAtributos;
	}
	public JPanel getPanelMetodos() {
		return panelMetodos;
	}

	public JLabel getLabelSeleccionado() {
		return labelSeleccionado;
	}
	public void setLabelSeleccionado(JLabel labelSeleccionado) {
		this.labelSeleccionado = labelSeleccionado;
	}



	public PanelClase(Principal p, Clase c) {
		mover = true;
		clase = c;

		setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));


		pe=p;
		setBackground(SystemColor.control);
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(new BorderLayout(0, 0));

		panelNombreClase = new JPanel();
		panelNombreClase.setBackground(SystemColor.textHighlightText);
		panelNombreClase.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(panelNombreClase, BorderLayout.NORTH);
		panelNombreClase.setLayout(new BorderLayout(0, 0));

		lblNombreclase = new JLabel(clase.getNombre()); 
		lblNombreclase.setFont(new Font("Microsoft YaHei", Font.PLAIN, 16));


		lblNombreclase.setHorizontalAlignment(SwingConstants.CENTER);
		panelNombreClase.add(lblNombreclase);

		panelAtributos = new JPanel();
		panelAtributos.setBackground(SystemColor.control);
		panelAtributos.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(panelAtributos, BorderLayout.CENTER);
		panelAtributos.setLayout(new BoxLayout(panelAtributos, BoxLayout.Y_AXIS));

		panelMetodos = new JPanel();
		panelMetodos.setBackground(SystemColor.control);
		panelMetodos.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(panelMetodos, BorderLayout.SOUTH);
		panelMetodos.setLayout(new BoxLayout(panelMetodos, BoxLayout.Y_AXIS));

		actualizarAtributos();
		actualizarMetodos();
		accionesPanelClase();
		actualizarDimensionesClase();
		actualizarColorClase();

	}


	public void actualizarAtributos (){ // Metodo para actualizar la infomacion de los atributos de una clase
		List<Atributo> atributos = clase.getAtributos();
		panelAtributos.removeAll();
		for (Atributo a : atributos) {
			panelAtributos.add(new LabelAtributo(PanelClase.this, a));
		}

		repaint();
		revalidate();
	}

	public void actualizarMetodos (){ // Metodo para actualizar la infomacion de los atributos de una clase
		List<Metodo> metodos = clase.getMetodos();
		panelMetodos.removeAll();
		for (Metodo m : metodos) {
			panelMetodos.add(new LabelMetodo(PanelClase.this, m));
		}

		repaint();
		revalidate();
	}

	public void actualizarNombreClase () { // Metodo para actualizar el nombre de la clase
		this.lblNombreclase.setText(clase.getNombre());
		repaint();
		revalidate();
	}

	public void actualizarPosicionesClase (int posX, int posY) { // Metodo para actualizar las posiciones de la clase
		clase.setPosicionX(posX);
		clase.setPosicionY(posY);
	}

	public void actualizarDimensionesClase () { // Metodo para actualizar las posiciones de la clase
		setLocation(clase.getPosicionX(), clase.getPosicionY());
		clase.setDimensionX(getPreferredSize().width + 50);
		clase.setDimensionY(getPreferredSize().height + 50);
		setSize(clase.getDimensionX(), clase.getDimensionY());
		repaint();
		revalidate();
	}

	public void actualizarColorClase () { // Metodo para actualizar el color de la clase

		if (clase.getColor().equalsIgnoreCase("Amarillo"))
			pintarAmarrillo();
		else if (clase.getColor().equalsIgnoreCase("Gris"))
			pintarGris();
		else if (clase.getColor().equalsIgnoreCase("Azul"))
			pintarAzul();

		repaint();
		revalidate();

	}

	// Metodos para pintar el relieve de la clase de un color
	private void pintarAmarrillo () {
		setBackground(SystemColor.info);
		panelAtributos.setBackground(SystemColor.info);
		panelMetodos.setBackground(SystemColor.info);
	}

	private void pintarGris () {
		setBackground(SystemColor.control);
		panelAtributos.setBackground(SystemColor.control);
		panelMetodos.setBackground(SystemColor.control);
	}

	private void pintarAzul () {
		setBackground(SystemColor.activeCaption);
		panelAtributos.setBackground(SystemColor.activeCaption);
		panelMetodos.setBackground(SystemColor.activeCaption);
	}


	public void setMidPoint(){
		top = new Point(this.getLocation().x + ((this.getPreferredSize().width+50) / 2), this.getLocation().y);
		bottom = new Point(this.getLocation().x + ((this.getPreferredSize().width+50) / 2), this.getLocation().y + this.getPreferredSize().height+50);
		left = new Point(this.getLocation().x , this.getLocation().y + ((this.getPreferredSize().height+50)/2));
		right = new Point(this.getLocation().x  + this.getPreferredSize().width+50, this.getLocation().y + ((this.getPreferredSize().height+50)/2));
	}

	private void flechaHerencia(PanelClase c1, PanelClase c2){
		System.out.println("Clase 1"+ c1 +"Clase2" + c2);
		if(c1.getLocation().y > c2.getLocation().y + c2.getPreferredSize().height+50){
			System.out.println("Clase dos esta mas arriba de la clase 1");
			pe.setFlechaInicio(c1.top);
			c2.flechaFinal = c2.bottom;
		}
		else if (c1.getLocation().y + c1.getPreferredSize().height+50 < c2.getLocation().y){
			System.out.println("Clase dos esta mas abajo de la clase 1");
			pe.setFlechaInicio(c1.bottom);
			c2.flechaFinal = c2.top;
		}
		else{
			if(c1.getLocation().x < c2.getLocation().x){
				System.out.println("Clase dos esta a la derecha de la clase 1");
				pe.setFlechaInicio(c1.right);
				c2.flechaFinal = c2.left;
			}
			else{
				System.out.println("Clase dos esta a la izquierda de la clase 1");
				pe.setFlechaInicio(c1.left);
				c2.flechaFinal = c2.right;	
			}

		}

	}

	public void accionesPanelClase(){
		setComponentPopupMenu(new MenuContextual(PanelClase.this, clase));

		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mover = true;
				pe.getPanelHerramDesp().setVisible(false);
				pe.getPanelArchivoDesp().setVisible(false);
				claseSeleccionada = (PanelClase) e.getComponent();
				if(pe.isEliminar()){					
					EliminarClase eli = new EliminarClase(PanelClase.this, clase);
					eli.setVisible(true);
					pe.setEnabled(false);
					pe.setEliminar(false);
					pe.setEliminarPressed(false);
				}
				else if (pe.isHerencia()){
					mover = false;
					if(pe.isHerenciaClase1()){
						boolean x = true;
						String clasePadre = pe.getClaseSeleccionada1().getLblNombreclase().getText();
						String claseHija = lblNombreclase.getText();

						try {
							//pe.getDiagrama().addHerencia(clasePadre, claseHija);
						} catch (Exception e1) {
							HerenciaError her = new HerenciaError(PanelClase.this, e1.getMessage());
							her.setVisible(true);
							pe.setEnabled(false);	
							x = false;
							mover = false;
						}
						if(x){

							pe.setPadreFlecha(lblNombreclase.getText());
							flechaHerencia(pe.getClaseSeleccionada1(), PanelClase.this);
							System.out.println(pe.getPadreFlecha());
							Flecha f = new Flecha(pe.getFlechaInicio(), flechaFinal, pe.getPadreFlecha(), pe.getHijoFlecha());

							pe.getLienzo().addHerencia(f);
							//Diagrama.getInstance().addFlechaHerencia(f);

							pe.setRelacionesPressed(false);
							pe.getMenuLienzo().getMntmCancelar().setVisible(false);
						
							pe.setHerencia(false);
							pe.setHerenciaClase1(false);
							pe.getLienzo().repaint();
							pe.getLienzo().revalidate();
						}
					}
					else{
						System.out.println(getLocation().x);
						System.out.println(getLocation().y);
						pe.setFlechaInicio(new Point(getLocation().x, getLocation().y));
						pe.setHijoFlecha(lblNombreclase.getText());
						pe.setHerenciaClase1(true);
						pe.setClaseSeleccionada1(PanelClase.this);
					}	
				}
				else if (pe.isEditar()){				
					EditarClase edi = new EditarClase(PanelClase.this, clase);
					setMover(false);
					edi.setVisible(true);
					pe.setEnabled(false);
					pe.setEditar(false);
					pe.setEditarPressed(false);

				}
				else{
					mouseX = e.getX();
					mouseY = e.getY();
				}

			}
			@Override
			public void mouseEntered(MouseEvent arg0) {

			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if(!pe.isEliminar() && mover){
					int x = e.getXOnScreen()-pe.getLienzo().getLocationOnScreen().x;
					int y = e.getYOnScreen()-pe.getLienzo().getLocationOnScreen().y;
					if((x - mouseX)>0&&(y-mouseY>0)&&(x - mouseX)<2000-getWidth()&&
							(y - mouseY)<2000-getHeight()){
						setLocation(x - mouseX, y-mouseY);
						actualizarPosicionesClase(x - mouseX, y-mouseY); // se actualiza las posiciones en la logica del programa

					}


					setMidPoint();
					pe.getLienzo().buscarRelacionesHerencia(lblNombreclase.getText(),new Point(getLocation().x,getLocation().y));
					pe.getLienzo().repaint();
					pe.getLienzo().revalidate();
				}
			}
		});
	}




}
