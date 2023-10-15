package util;

import javax.swing.JLabel;

import Clases.Atributo;
import Interfaz.Principal;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.SystemColor;

public class LabelAtributo extends JLabel {
	private static final long serialVersionUID = 1L;
	
	private Atributo atributo;
	private PanelClase panelClase;
	 public LabelAtributo(PanelClase p, Atributo a){
	 	setForeground(SystemColor.activeCaptionText);
	 	setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		 atributo = a;
		 panelClase = p;
	 	setFont(new Font("Microsoft YaHei", Font.PLAIN, 16));
	 	addMouseListener(new MouseAdapter() {
	 		
	 		@Override
	 		public void mouseEntered(MouseEvent e) {
	 			setForeground(SystemColor.activeCaption);
	 		}
	 		@Override
	 		public void mouseExited(MouseEvent e) {
	 			setForeground(SystemColor.activeCaptionText);
	 		}
	 		@Override
	 		public void mousePressed(MouseEvent e) {
	 			Principal.getInstancie().getPanelHerramDesp().setVisible(false);
	 			Principal.getInstancie().getPanelArchivoDesp().setVisible(false);
	 		}
	 	});
		
		 setText(atributo.toString());
		 setComponentPopupMenu(new MenuContextualAtributo(panelClase, atributo));
		 	
	 }
	 
	
}
