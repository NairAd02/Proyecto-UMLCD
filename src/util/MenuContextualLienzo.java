package util;

import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;

import Clases.GestorUML;
import Interfaz.AgregarClase;
import Interfaz.Principal;
import Interfaz.SeleccionClaseA;
import Logica.ManejoDirectorios;
import mensajesError.AlmenosDos;

import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.JMenu;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class MenuContextualLienzo extends JPopupMenu {
	private static final long serialVersionUID = 1L;

	private Principal pe;
	private JMenuItem mntmCancelar;
	private JMenuItem mntmAgregarClase;
	private JMenuItem mntmGenerarimg;
	private JMenu mnEstablecerRelacin;
	private JMenuItem mntmHerencia;
	private JMenuItem mntmComposicin;
	private JMenuItem mntmAgregacin;
	public MenuContextualLienzo(Principal p){

		mntmAgregarClase = new JMenuItem("Agregar Clase");
		mntmAgregarClase.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				AgregarClase cl = new AgregarClase(pe);
				cl.setVisible(true);
				pe.setEnabled(false);
				

			}
		});
		add(mntmAgregarClase);

		mntmCancelar = new JMenuItem("Cancelar Relación");
		mntmCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mntmCancelar.setVisible(false);
				pe.getLienzo().cancelarHerencia();
				pe.getLienzo().repaint();
				pe.getLienzo().revalidate();
			}
		});
		
		mnEstablecerRelacin = new JMenu("Establecer Relaci\u00F3n");
		add(mnEstablecerRelacin);
		
		mntmHerencia = new JMenuItem("Herencia");
		mnEstablecerRelacin.add(mntmHerencia);
		
		mntmComposicin = new JMenuItem("Composici\u00F3n");
		mnEstablecerRelacin.add(mntmComposicin);
		
		mntmAgregacin = new JMenuItem("Agregaci\u00F3n");
		mnEstablecerRelacin.add(mntmAgregacin);
		add(mntmCancelar);
		
		mntmGenerarimg = new JMenuItem("GenerarImg");
		mntmGenerarimg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Se crea un buffer para almacenar la imagen
				BufferedImage image = new BufferedImage(Principal.getInstance().getLienzo().getWidth(), Principal.getInstance().getLienzo().getHeight(), BufferedImage.TYPE_INT_RGB);
				
				Graphics2D g2d = image.createGraphics();
				
				Principal.getInstance().getLienzo().paint(g2d); // se dibuja la imagen apartir del contenido del panel
				
				g2d.dispose();
				
				try {
					ManejoDirectorios.guardarImagen(image, GestorUML.getInstancie().getProyectoSeleccionado().getDiagramaSeleccionado().getNombre()); // se guarda la imagen
				} catch (IOException e1) {
					
					e1.printStackTrace();
				} 
			}
		});
		add(mntmGenerarimg);

		mntmCancelar.setVisible(false);
		pe = p;

	}
	public JMenuItem getMntmCancelar() {
		return mntmCancelar;
	}
	
	
	



}
