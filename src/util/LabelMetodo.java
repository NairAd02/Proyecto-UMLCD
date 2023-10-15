package util;

import javax.swing.JLabel;

import Clases.Metodo;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Cursor;

public class LabelMetodo extends JLabel {
	private static final long serialVersionUID = 1L;
	
	private PanelClase pe;
	private Metodo metodo;
	public LabelMetodo(PanelClase p, Metodo m){
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setForeground(SystemColor.activeCaptionText);
		setFont(new Font("Microsoft YaHei", Font.PLAIN, 16));
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				pe.getPe().getPanelHerramDesp().setVisible(false);
	 			pe.getPe().getPanelArchivoDesp().setVisible(false);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				setForeground(SystemColor.activeCaption);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				setForeground(SystemColor.activeCaptionText);
			}
		});
		pe = p;
		metodo = m;
		
		setText(m.toString());
		 
		 setComponentPopupMenu(new MenuContextualMetodo(pe, metodo));
	}
	
	public PanelClase getPe() {
		return pe;
	}
	public void setPe(PanelClase pe) {
		this.pe = pe;
	}
	public Metodo getMetodo() {
		return metodo;
	}
	public void setMetodo(Metodo metodo) {
		this.metodo = metodo;
	}

}
