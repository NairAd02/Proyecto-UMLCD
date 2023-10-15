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

import util.LabelArchivoGuardado;
import util.PanelPenstannaDiagrama;
import Clases.GestorUML;
import Logica.ManejoDirectorios;

public class FrameDecisorPestannaDiagrama extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblGuardar;
	private JLabel lblTexto;
	private JPanel panelGuardar;
	private JPanel panelCancelar;
	private PanelPenstannaDiagrama panelPestannaDiagrama;
	private String nombreDiagrama;
	private JLabel lblNombreDiagrama;


	/**
	 * Create the frame.
	 */


	public FrameDecisorPestannaDiagrama(PanelPenstannaDiagrama p) {
		panelPestannaDiagrama = p;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 509, 224);
		setUndecorated(true);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 204, 204));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblTexto = new JLabel("Desea guardar los cambios efectuados en:");
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
					ManejoDirectorios.guardarArchivo(GestorUML.getInstancie().getDiagramaSeleccionado()); // Se guarda la informacion de ese diagrama antes de cerrarlo
				} catch (FileNotFoundException e1) {

					e1.printStackTrace();
				} catch (IOException e1) {

					e1.printStackTrace();
				}
				panelPestannaDiagrama.cerrarDiagrama(); // se cierra la pestaña del diagrama
				Principal.getInstancie().setEnabled(true);
				dispose();
			}
		});
		panelGuardar.setBounds(20, 135, 138, 55);
		panelGuardar.setBackground(SystemColor.inactiveCaptionBorder);
		contentPane.add(panelGuardar);
		panelGuardar.setLayout(null);

		lblGuardar = new JLabel("Guardar");
		lblGuardar.setHorizontalAlignment(SwingConstants.CENTER);
		lblGuardar.setBounds(18, 7, 113, 40);
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
				Principal.getInstancie().setEnabled(true);
				dispose();
			}
		});
		panelCancelar.setBounds(361, 135, 138, 55);
		panelCancelar.setBackground(SystemColor.inactiveCaptionBorder);
		contentPane.add(panelCancelar);
		panelCancelar.setLayout(null);

		JLabel lblCancelar = new JLabel("   Cancelar");
		lblCancelar.setBounds(14, 7, 120, 40);
		panelCancelar.add(lblCancelar);
		lblCancelar.setFont(new Font("Dialog", Font.BOLD, 19));

		JPanel panelNoGuardar = new JPanel();
		panelNoGuardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
			@Override
			public void mousePressed(MouseEvent e) {

				panelPestannaDiagrama.cerrarDiagrama(); // se cierra la pestaña del diagrama
				Principal.getInstancie().setEnabled(true);
				dispose();

			}
		});
		panelNoGuardar.setLayout(null);
		panelNoGuardar.setBackground(SystemColor.inactiveCaptionBorder);
		panelNoGuardar.setBounds(190, 135, 138, 55);
		contentPane.add(panelNoGuardar);

		JLabel lblNoGuardar = new JLabel("No Guardar");
		lblNoGuardar.setHorizontalAlignment(SwingConstants.CENTER);
		lblNoGuardar.setFont(new Font("Dialog", Font.BOLD, 19));
		lblNoGuardar.setBounds(10, 11, 113, 33);
		panelNoGuardar.add(lblNoGuardar);

		lblNombreDiagrama = new JLabel(GestorUML.getInstancie().getDiagramaSeleccionado().getNombre());
		lblNombreDiagrama.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombreDiagrama.setFont(new Font("Dialog", Font.BOLD, 19));
		lblNombreDiagrama.setBounds(10, 78, 489, 46);
		contentPane.add(lblNombreDiagrama);

	}


	public JLabel getLblTexto() {
		return lblTexto;
	}


	public void setTextoLblTexto(String texto) {
		this.lblTexto.setText(texto);
	}
}
