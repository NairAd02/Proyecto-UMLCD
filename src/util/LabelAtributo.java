package util;

import javax.swing.JLabel;

import Clases.Atributo;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class LabelAtributo extends JLabel {
	private static final long serialVersionUID = 1L;
	
	private Atributo atributo;
	private PanelClase panelClase;
	 public LabelAtributo(PanelClase p, Atributo a){
		 atributo = a;
		 panelClase = p;
	 	setFont(new Font("Microsoft YaHei", Font.PLAIN, 16));
	 	addMouseListener(new MouseAdapter() {
	 		@Override
	 		public void mousePressed(MouseEvent e) {
	 			
	 		}
	 	});
		
		 setText(atributo.toString());
		 setComponentPopupMenu(new MenuContextualAtributo(panelClase, atributo));
		 	
	 }
	 
	
}
