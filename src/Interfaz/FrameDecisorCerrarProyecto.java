package Interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import util.PanelPestannaProyecto;
import Clases.GestorUML;
import Logica.ManejoDirectorios;

public class FrameDecisorCerrarProyecto extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblGuardar;
	private JLabel lblTexto;
	private JPanel panelGuardar;
	private JPanel panelCancelar;
	private JPanel panelNoGuardar;


	/**
	 * Create the frame.
	 */


	public FrameDecisorCerrarProyecto() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 509, 224);
		setUndecorated(true);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 204, 204));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblTexto = new JLabel("Se detectaron cambios importantes, desea ");
		lblTexto.setHorizontalAlignment(SwingConstants.CENTER);
		lblTexto.setFont(new Font("Dialog", Font.BOLD, 19));
		lblTexto.setBounds(10, 41, 489, 46);
		contentPane.add(lblTexto);

		panelGuardar = new JPanel();
		panelGuardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panelGuardar.setBackground(new Color(104,137,148));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelGuardar.setBackground(SystemColor.inactiveCaptionBorder);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					GestorUML.getInstancie().guardarAllProyectos(); // se guardan todos los proyectos que fueron modificaos		
					System.exit(0); // se finaliza la ejecución de la aplicación
				} catch (FileNotFoundException e1) {

					e1.printStackTrace();
				} catch (IOException e1) {

					e1.printStackTrace();
				}

			}
		});
		panelGuardar.setBounds(20, 135, 138, 55);
		panelGuardar.setBackground(SystemColor.inactiveCaptionBorder);
		contentPane.add(panelGuardar);
		panelGuardar.setLayout(null);

		lblGuardar = new JLabel("Si");
		lblGuardar.setHorizontalAlignment(SwingConstants.CENTER);
		lblGuardar.setBounds(12, 11, 113, 33);
		panelGuardar.add(lblGuardar);
		lblGuardar.setFont(new Font("Dialog", Font.BOLD, 19));


		panelCancelar = new JPanel();
		panelCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panelCancelar.setBackground(new Color(104,137,148));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panelCancelar.setBackground(SystemColor.inactiveCaptionBorder);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Principal.getInstance().setEnabled(true);
				dispose();
			}
		});
		panelCancelar.setBounds(361, 135, 138, 55);
		panelCancelar.setBackground(SystemColor.inactiveCaptionBorder);
		contentPane.add(panelCancelar);
		panelCancelar.setLayout(null);

		JLabel lblCancelar = new JLabel("   Cancelar");
		lblCancelar.setBounds(10, 9, 120, 37);
		panelCancelar.add(lblCancelar);
		lblCancelar.setFont(new Font("Dialog", Font.BOLD, 19));

		panelNoGuardar = new JPanel();
		panelNoGuardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
			@Override
			public void mousePressed(MouseEvent e) {
				System.exit(0); // Se detiene la ejecución de la aplicación
			}
		});
		panelNoGuardar.setLayout(null);
		panelNoGuardar.setBackground(SystemColor.inactiveCaptionBorder);
		panelNoGuardar.setBounds(190, 135, 138, 55);
		contentPane.add(panelNoGuardar);

		JLabel lblNoGuardar = new JLabel("No");
		lblNoGuardar.setHorizontalAlignment(SwingConstants.CENTER);
		lblNoGuardar.setFont(new Font("Dialog", Font.BOLD, 19));
		lblNoGuardar.setBounds(12, 11, 113, 33);
		panelNoGuardar.add(lblNoGuardar);
		
		JLabel lblGuardarlos = new JLabel("guardarlos antes de salir?");
		lblGuardarlos.setHorizontalAlignment(SwingConstants.CENTER);
		lblGuardarlos.setFont(new Font("Dialog", Font.BOLD, 19));
		lblGuardarlos.setBounds(10, 85, 489, 46);
		contentPane.add(lblGuardarlos);

	}


	public JLabel getLblTexto() {
		return lblTexto;
	}


	public void setTextoLblTexto(String texto) {
		this.lblTexto.setText(texto);
	}
}
