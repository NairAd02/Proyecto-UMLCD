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
	private JLabel lblNewLabel;
	private JLabel lblAadeDiagrama;
	private int estado;

	public PanelInicio(int e) {  // si el estado es 1 se añade el evento de añadir diagrama, si el estado es 0 se añade el evento de añadir proyecto
		this.estado = e;
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

			}
		});

		setBorder(null);
		setBackground(SystemColor.textHighlightText);


		lblNewLabel = new JLabel("");

		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (estado == 1){
					FrameNuevoDiagrama nuevoDiagrama = new FrameNuevoDiagrama(Principal.getInstance());
					nuevoDiagrama.setVisible(true);
				}
				else if (estado == 0){
					FrameNuevoProyecto nuevoProyecto = new FrameNuevoProyecto(Principal.getInstance());
					nuevoProyecto.setVisible(true);			
				}

				Principal.getInstance().setEnabled(false);
			}
		});
		setLayout(new BorderLayout(0, 0));
		lblNewLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBorder(null);
		lblNewLabel.setIcon(new ImageIcon(PanelInicio.class.getResource("/images/UMLSCREEN.png")));
		add(lblNewLabel);
		lblAadeDiagrama = new JLabel("A\u00F1ade Diagrama");
		lblAadeDiagrama.setFont(new Font("Tahoma", Font.BOLD, 54));
		lblAadeDiagrama.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblAadeDiagrama, BorderLayout.NORTH);
		textLabel();

	}

	private void textLabel () {
		if (estado == 1) 
			lblAadeDiagrama.setText("Añadir Diagrama");	
		else if (estado == 0)
			lblAadeDiagrama.setText("Añadir Proyecto");
	}


}
