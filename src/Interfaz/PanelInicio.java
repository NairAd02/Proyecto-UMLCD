package Interfaz;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Color;

import javax.swing.SwingConstants;

import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.CardLayout;

public class PanelInicio extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Principal pe;
	private JLabel lblNewLabel;

	public PanelInicio(Principal p) {
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				pe.getPanelHerramDesp().setVisible(false);
				pe.setDesplegadoHerram(false);
				pe.getPanelArchivoDesp().setVisible(false);
				pe.setDesplegadoArchivo(false);
			}
		});
		pe = p;
		setBorder(null);
		setBackground(SystemColor.textHighlightText);
				actualizarPosiscionImagenUML();

lblNewLabel = new JLabel("");

lblNewLabel.addMouseListener(new MouseAdapter() {
	@Override
	public void mousePressed(MouseEvent e) {
		pe.getPanelHerramDesp().setVisible(false);
		pe.setDesplegadoHerram(false);
		pe.getPanelArchivoDesp().setVisible(false);
		pe.setDesplegadoArchivo(false);
		FrameNuevoDiagrama nuevoDiagrama = new FrameNuevoDiagrama(pe);
		nuevoDiagrama.setVisible(true);
		pe.setEnabled(false);
	}
});
setLayout(new BorderLayout(0, 0));
lblNewLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
lblNewLabel.setBorder(null);
lblNewLabel.setIcon(new ImageIcon(PanelInicio.class.getResource("/images/UMLSCREEN.png")));
add(lblNewLabel);
	}
	
	public void actualizarPosiscionImagenUML(){
		
	}
}
