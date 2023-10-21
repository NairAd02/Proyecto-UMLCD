package Interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import util.JTextFieldMejorado;
import Clases.GestorUML;
import Clases.Proyecto;
import Logica.ManejoDirectorios;

public class FrameNuevoProyecto extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Principal pe;
	private JLabel labelNombre;
	private JTextFieldMejorado textFieldNombreProyecto;
	private JPanel panelConfirmar;
	private JLabel labelConfirmar;
	private JPanel panelCancelar;
	private JLabel labelCancelar;
	private JLabel lblErrorTexto;
	private JLabel lblDiagramaSameNameError;

	/**
	 * Create the frame.
	 */
	public FrameNuevoProyecto(Principal p) {
		pe = p;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 428, 232);
		setUndecorated(true);

		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 204, 204));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		labelNombre = new JLabel("Ingrese el nombre del nuevo proyecto");
		labelNombre.setFont(new Font("Dialog", Font.BOLD, 16));
		labelNombre.setBounds(28, 31, 318, 38);
		contentPane.add(labelNombre);

		textFieldNombreProyecto = new JTextFieldMejorado();
		textFieldNombreProyecto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(textFieldNombreProyecto.getSelectedText()==null)	
					textFieldNombreProyecto.setText(textFieldNombreProyecto.getText().trim());

			}
		});
		textFieldNombreProyecto.setLimite(40);
		textFieldNombreProyecto.setBounds(28, 75, 360, 34);
		contentPane.add(textFieldNombreProyecto);


		panelConfirmar = new JPanel();
		panelConfirmar.setLayout(null);
		panelConfirmar.setBackground(SystemColor.inactiveCaptionBorder);
		panelConfirmar.setBounds(28, 167, 109, 38);
		panelConfirmar.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent arg0) {
				panelConfirmar.setBackground(new Color(104,137,148));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				panelConfirmar.setBackground(SystemColor.inactiveCaptionBorder);				
				lblErrorTexto.setVisible(false);
				lblDiagramaSameNameError.setVisible(false);
			}
			@Override
			public void mousePressed(MouseEvent e) {

				crearNuevoProyecto();
				try {
					ManejoDirectorios.guardarArchivo(GestorUML.getInstancie().getProyectoSeleccionado()); // se guarda el proyecto en memoria externa
					pe.setEnabled(true);
					dispose();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}


			}
		});


		contentPane.add(panelConfirmar);

		labelConfirmar = new JLabel("    Confirmar");

		labelConfirmar.setBounds(0, 0, 109, 38);
		panelConfirmar.add(labelConfirmar);
		labelConfirmar.setFont(new Font("Dialog", Font.BOLD, 16));

		panelCancelar = new JPanel();
		panelCancelar.setLayout(null);
		panelCancelar.setBackground(SystemColor.inactiveCaptionBorder);
		panelCancelar.setBounds(279, 167, 109, 38);
		panelCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				pe.setEnabled(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				panelCancelar.setBackground(new Color(104,137,148));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				panelCancelar.setBackground(SystemColor.inactiveCaptionBorder);
			}
		});
		contentPane.add(panelCancelar);

		labelCancelar = new JLabel("     Cancelar");
		labelCancelar.setFont(new Font("Dialog", Font.BOLD, 16));
		labelCancelar.setBounds(0, 0, 109, 38);
		panelCancelar.add(labelCancelar);

		lblErrorTexto = new JLabel("Es necesario un nombre");
		lblErrorTexto.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblErrorTexto.setForeground(Color.RED);
		lblErrorTexto.setVisible(false);
		lblErrorTexto.setBounds(28, 120, 139, 23);
		contentPane.add(lblErrorTexto);

		lblDiagramaSameNameError = new JLabel("Ya existe un diagrama con el mismo nombre");
		lblDiagramaSameNameError.setVisible(false);
		lblDiagramaSameNameError.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDiagramaSameNameError.setForeground(Color.RED);
		lblDiagramaSameNameError.setBounds(28, 21, 390, 14);
		contentPane.add(lblDiagramaSameNameError);

	}

	private void crearNuevoProyecto () {
		GestorUML.getInstancie().addProyecto(new Proyecto(textFieldNombreProyecto.getText()));
		Principal.getInstance().actualizarPanelProyectos();
		Principal.getInstance().actualizarPanelPestannaDiagramas();
		Principal.getInstance().actualizarEstado();
	}

}