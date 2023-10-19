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
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JSeparator;
import javax.swing.border.LineBorder;

public class PantallaCompleta extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JLabel lblVolver;
	private JPanel panel;
	private JPanel panelDiagramas;
	private JPanel panel_1;
	private JLabel lblOpciones;
	private JPanel panel_2;
	private JSeparator separator;
	private JPanel panelAddClase;
	private JPanel panelAddHerencia;
	private JPanel panelAddAgregacion;
	private JPanel panelAddComposiscion;
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
		lblVolver.setForeground(SystemColor.infoText);
		lblVolver.setFont(new Font("Tahoma", Font.BOLD, 18));
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
		
		panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBackground(SystemColor.activeCaptionBorder);
		contentPane.add(panel_1, BorderLayout.WEST);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		lblOpciones = new JLabel("  Opciones   ");
		lblOpciones.setFont(new Font("Tahoma", Font.BOLD, 25));
		panel_1.add(lblOpciones, BorderLayout.NORTH);
		
		panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.activeCaptionBorder);
		panel_1.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(null);
		
		separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		separator.setForeground(Color.BLACK);
		separator.setBounds(0, 8, 150, 2);
		panel_2.add(separator);
		
		panelAddClase = new JPanel();
		panelAddClase.setBackground(new Color(0,58,87));
		panelAddClase.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelAddClase.setBounds(10, 21, 130, 32);
		panel_2.add(panelAddClase);
		
		JLabel labelClase = new JLabel("Clase");
		labelClase.setForeground(SystemColor.textHighlightText);
		labelClase.setFont(new Font("Tahoma", Font.BOLD, 18));
		panelAddClase.add(labelClase);
		
		panelAddHerencia = new JPanel();
		panelAddHerencia.setBackground(new Color(0,58,87));
		panelAddHerencia.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelAddHerencia.setBounds(10, 64, 130, 32);
		panel_2.add(panelAddHerencia);
		
		JLabel labelHerencia = new JLabel("Herencia");
		labelHerencia.setForeground(SystemColor.textHighlightText);
		labelHerencia.setFont(new Font("Tahoma", Font.BOLD, 18));
		panelAddHerencia.add(labelHerencia);
		
		panelAddComposiscion = new JPanel();
		panelAddComposiscion.setBackground(new Color(0,58,87));
		panelAddComposiscion.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelAddComposiscion.setBounds(10, 107, 130, 32);
		panel_2.add(panelAddComposiscion);
		
		JLabel labelComposicion = new JLabel("Composici\u00F3n");
		labelComposicion.setForeground(SystemColor.textHighlightText);
		labelComposicion.setFont(new Font("Tahoma", Font.BOLD, 18));
		panelAddComposiscion.add(labelComposicion);
		
		panelAddAgregacion = new JPanel();
		panelAddAgregacion.setBackground(new Color(0,58,87));
		panelAddAgregacion.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelAddAgregacion.setBounds(10, 150, 130, 32);
		panel_2.add(panelAddAgregacion);
		
		JLabel labelAgregacion = new JLabel("Agregaci\u00F3n");
		labelAgregacion.setForeground(SystemColor.textHighlightText);
		labelAgregacion.setFont(new Font("Tahoma", Font.BOLD, 18));
		panelAddAgregacion.add(labelAgregacion);
	}
}
