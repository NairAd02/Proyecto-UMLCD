package Interfaz;

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
import java.awt.SystemColor;

public class PantallaCompleta extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JLabel lblVolver;
	private JPanel panel;
	private JPanel panelDiagramas;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public PantallaCompleta(JPanel p) {
		panelDiagramas = p;
		setUndecorated(true);
		setResizable(false);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		panel = new JPanel();
		panel.setBackground(new Color(47, 79, 79));
		FlowLayout fl_panel = (FlowLayout) panel.getLayout();
		fl_panel.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel, BorderLayout.NORTH);
		contentPane.add(panelDiagramas, BorderLayout.CENTER);

		//contentPane.add(pe.getScrollPane(),BorderLayout.CENTER);

		lblVolver = new JLabel("Volver");
		lblVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				Principal.getInstancie().setVisible(true);
				Principal.getInstancie().setEnabled(true);					
				panelDiagramas.setBounds(226, 100, 874, 600);
				Principal.getInstancie().getPanelContenedor().add(panelDiagramas); 
				Principal.getInstancie().repaint();
				Principal.getInstancie().revalidate();
				dispose();
			}
		});
		lblVolver.setIcon(new ImageIcon(PantallaCompleta.class.getResource("/images/door.png")));
		lblVolver.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblVolver);
	}

}
