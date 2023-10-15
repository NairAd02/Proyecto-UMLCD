package Interfaz;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;



import javax.swing.*;

import mensajesError.AtributosMismoName;
import util.JTextFieldMejorado;
import util.PanelClase;
import Clases.Atributo;
import Clases.Clase;
import Clases.GestorUML;
import Logica.Operaciones;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.border.LineBorder;

public class AgregarAtributo extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private PanelClase pe;
	private JPanel panelCancelar;

	private JLabel labelCancelar;

	private JPanel panelConfirmar;

	private JLabel labelConfirmar;
	private JLabel labelTitulo;
	private JComboBox<String> comboBoxAcceso;
	private JLabel labelAccesos;
	private JLabel lblTipoDeDato;
	private JLabel labelNombre;
	private JTextField textFieldNombre;
	private JTextField textFieldTipoDato;
	private int mouseX;
	private int mouseY;
	private JLabel labelRojos;
	private Clase clase;


	/**
	 * Create the frame.
	 */
	public AgregarAtributo(PanelClase p, Clase c) {
		pe = p;
		clase = c;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 391, 226);
		setUndecorated(true);

		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x= e.getXOnScreen();
				int y= e.getYOnScreen();

				setLocation(x - mouseX , y - mouseY );

			}
		});
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX= e.getX();
				mouseY= e.getY();
			}
		});
		contentPane.setBackground(new Color(153, 204, 204));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panelConfirmar = new JPanel();
		panelConfirmar.setLayout(null);
		panelConfirmar.setBackground(SystemColor.inactiveCaptionBorder);
		panelConfirmar.setBounds(55, 172, 98, 38);
		panelConfirmar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {


				if(!textFieldNombre.getText().equals("") && !textFieldTipoDato.getText().equals("") && Operaciones.isNumeroPrimeraPosicion(textFieldNombre.getText()) && Operaciones.isNumeroPrimeraPosicion(textFieldTipoDato.getText())){
					try {
						addAtributo(); // se añade el atributo a la clase
						pe.actualizarAtributos(); // se actualiza la informacion de los atributos
						pe.actualizarDimensionesClase(); // se actualiza las dimensiones de la clase en caso de que el atributo exceda los limites de la clase
						Principal.getInstancie().setEnabled(true);
						dispose();		
					} catch (Exception e1) {
						AtributosMismoName sameName = new AtributosMismoName(AgregarAtributo.this);
						sameName.setVisible(true);
						setEnabled(false);
					}

				}
				else{
					labelRojos.setVisible(true);

				}

			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				panelConfirmar.setBackground(new Color(104,137,148));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				panelConfirmar.setBackground(SystemColor.inactiveCaptionBorder);
				labelRojos.setVisible(false);
			}
		});
		contentPane.add(panelConfirmar);

		labelConfirmar = new JLabel("  Agregar");

		labelConfirmar.setBounds(10, 1, 82, 33);
		panelConfirmar.add(labelConfirmar);
		labelConfirmar.setFont(new Font("Dialog", Font.BOLD, 16));


		panelCancelar = new JPanel();
		panelCancelar.setLayout(null);
		panelCancelar.setBackground(SystemColor.inactiveCaptionBorder);
		panelCancelar.setBounds(244, 172, 98, 38);
		panelCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				pe.getPe().setEnabled(true);
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

		labelCancelar = new JLabel(" Cancelar");
		labelCancelar.setFont(new Font("Dialog", Font.BOLD, 16));
		labelCancelar.setBounds(10, 1, 82, 33);
		panelCancelar.add(labelCancelar);

		labelTitulo = new JLabel("Ingrese los datos del nuevo atributo:");
		labelTitulo.setFont(new Font("Dialog", Font.BOLD, 16));
		labelTitulo.setBounds(55, 11, 318, 38);
		contentPane.add(labelTitulo);

		comboBoxAcceso = new JComboBox<String>();
		comboBoxAcceso.setModel(new DefaultComboBoxModel <String>(new String[] {"public", "private", "protected"}));
		comboBoxAcceso.setFont(new Font("Dialog", Font.BOLD, 13));
		comboBoxAcceso.setBounds(199, 52, 119, 24);
		contentPane.add(comboBoxAcceso);

		labelAccesos = new JLabel("Modificador de acceso:");
		labelAccesos.setFont(new Font("Dialog", Font.BOLD, 14));
		labelAccesos.setBounds(20, 51, 187, 25);
		contentPane.add(labelAccesos);

		lblTipoDeDato = new JLabel("Tipo de Dato:");
		lblTipoDeDato.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTipoDeDato.setFont(new Font("Dialog", Font.BOLD, 14));
		lblTipoDeDato.setBounds(23, 123, 124, 25);
		contentPane.add(lblTipoDeDato);

		labelNombre = new JLabel("Nombre:");
		labelNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		labelNombre.setFont(new Font("Dialog", Font.BOLD, 14));
		labelNombre.setBounds(76, 87, 68, 25);
		contentPane.add(labelNombre);

		textFieldNombre = new JTextFieldMejorado();
		textFieldNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if(textFieldNombre.getSelectedText()==null)
					textFieldNombre.setText(textFieldNombre.getText().trim());
			}
		});
		textFieldNombre.setBounds(154, 87, 164, 24);
		contentPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);

		textFieldTipoDato = new JTextFieldMejorado();
		textFieldTipoDato.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if(textFieldTipoDato.getSelectedText()==null)
					textFieldTipoDato.setText(textFieldTipoDato.getText().trim());
			}
		});
		textFieldTipoDato.setColumns(10);
		textFieldTipoDato.setBounds(154, 123, 164, 24);
		contentPane.add(textFieldTipoDato);

		labelRojos = new JLabel("Es necesario completar ambos campos");
		labelRojos.setVisible(false);
		labelRojos.setForeground(Color.RED);
		labelRojos.setFont(new Font("Tahoma", Font.BOLD, 12));
		labelRojos.setBounds(50, 153, 257, 14);
		contentPane.add(labelRojos);


	}

	private void addAtributo () throws Exception{
		String acceso = "";

		if (comboBoxAcceso.getSelectedIndex() == 0) // se seleccionó public
			acceso = "+";
		else if (comboBoxAcceso.getSelectedIndex() == 1) // se seleccionó private
			acceso = "-";
		else // se seleccionó protected
			acceso = "#";

		GestorUML.getInstancie().getDiagramaSeleccionado().addAtributoClase(clase, new Atributo(textFieldNombre.getText(), textFieldTipoDato.getText(), acceso));  // se añade el atributo a la clase
	}
}
