package Interfaz;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.*;

import util.PanelClase;
import Clases.Atributo;
import Clases.GestorUML;
import Logica.Operaciones;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.border.LineBorder;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class ModificarAtributo extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private PanelClase pe;

	private JPanel panelCancelar;

	private JLabel labelCancelar;

	private JPanel panelConfirmar;

	private JLabel labelModificar;
	private JLabel labelTitulo;
	private JComboBox<String> comboBoxAcceso;
	private JLabel labelAccesos;
	private JLabel lblTipoDeDato;
	private JLabel labelNombre;
	private JTextField textFieldNombre;
	private JTextField textFieldTipoDato;

	private int mouseX;
	private int mouseY;
	private JLabel lblNombreAtributo;
	private JLabel lblRojo;
	private Atributo atributo;


	/**
	 * Create the frame.
	 */
	public ModificarAtributo(PanelClase p, Atributo a) {
		pe = p;
		atributo = a;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 391, 280);
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
				contentPane.requestFocus();
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
		panelConfirmar.setBounds(42, 216, 98, 38);
		panelConfirmar.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent arg0) {
				panelConfirmar.setBackground(new Color(104,137,148));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				panelConfirmar.setBackground(SystemColor.inactiveCaptionBorder);
				lblRojo.setVisible(false);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				if(!textFieldNombre.getText().equals("") && !textFieldTipoDato.getText().equals("") && Operaciones.isNumeroPrimeraPosicion(textFieldNombre.getText()) && Operaciones.isNumeroPrimeraPosicion(textFieldTipoDato.getText())){
					GestorUML.getInstancie().getProyectoSeleccionado().getDiagramaSeleccionado().modificarAtributo(atributo, textFieldNombre.getText(), textFieldTipoDato.getText(), determinarModificadorDeAcceso()); // Se modifica el atributo
					pe.actualizarAtributos(); // se actualiza la informacion de los atributos para apreciar los cambios
					pe.actualizarDimensionesClase(); // se actualiza las dimensiones de la clase en caso de que el atributo exceda los limites de la clase
					Principal.getInstance().setEnabled(true);
					dispose();
				}
			}
		});
		contentPane.add(panelConfirmar);

		labelModificar = new JLabel("Modificar");

		labelModificar.setBounds(10, 1, 82, 33);
		panelConfirmar.add(labelModificar);
		labelModificar.setFont(new Font("Dialog", Font.BOLD, 16));


		panelCancelar = new JPanel();
		panelCancelar.setLayout(null);
		panelCancelar.setBackground(SystemColor.inactiveCaptionBorder);
		panelCancelar.setBounds(254, 216, 98, 38);
		panelCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Principal.getInstance().setEnabled(true);
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

		labelTitulo = new JLabel("Modificar Atributo:");
		labelTitulo.setFont(new Font("Dialog", Font.BOLD, 16));
		labelTitulo.setBounds(20, 11, 149, 38);
		contentPane.add(labelTitulo);



		labelAccesos = new JLabel("Modificador de acceso:");
		labelAccesos.setFont(new Font("Dialog", Font.BOLD, 14));
		labelAccesos.setBounds(20, 60, 187, 25);
		contentPane.add(labelAccesos);

		lblTipoDeDato = new JLabel("Tipo de Dato:");
		lblTipoDeDato.setFont(new Font("Dialog", Font.BOLD, 14));
		lblTipoDeDato.setBounds(16, 146, 124, 25);
		contentPane.add(lblTipoDeDato);

		labelNombre = new JLabel("Nombre:");
		labelNombre.setFont(new Font("Dialog", Font.BOLD, 14));
		labelNombre.setBounds(42, 110, 68, 25);
		contentPane.add(labelNombre);

		lblNombreAtributo = new JLabel(atributo.toString());
		lblNombreAtributo.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNombreAtributo.setBounds(167, 19, 214, 23);
		contentPane.add(lblNombreAtributo);

		lblRojo = new JLabel("Es necesario completar ambos campos");
		lblRojo.setVisible(false);
		lblRojo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRojo.setForeground(Color.RED);
		lblRojo.setBounds(42, 191, 257, 14);
		contentPane.add(lblRojo);

		addTextFieldNombre();
		addTexFieldTipoDato();
		addComboboxAcceso();
	}

	private void addTextFieldNombre () { // TEXTFIELD NOMBRE

		textFieldNombre = new JTextField(atributo.getNombre());
		textFieldNombre.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {	
				if (textFieldNombre.getText().equalsIgnoreCase("")){
					textFieldNombre.setText(atributo.getNombre());
					textFieldNombre.setForeground(Color.white);
				}
			}
		});
		textFieldNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if(textFieldNombre.getSelectedText()==null)
					textFieldNombre.setText(textFieldNombre.getText().trim());
			}
		});
		textFieldNombre.setForeground(Color.LIGHT_GRAY);
		textFieldNombre.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				textFieldNombre.requestFocus();
				if(textFieldNombre.getText().equalsIgnoreCase(atributo.getNombre())){
					textFieldNombre.setText("");
					textFieldNombre.setForeground(Color.black);
				}	
			}
		});
		textFieldNombre.setBounds(154, 112, 164, 24);
		contentPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);
	}

	private void addTexFieldTipoDato () { // TEXTFIELD TIPO DE DATO

		textFieldTipoDato = new JTextField(atributo.getTipoDato());
		textFieldTipoDato.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (textFieldTipoDato.getText().equalsIgnoreCase("")){
					textFieldTipoDato.setText(atributo.getTipoDato());
					textFieldTipoDato.setForeground(Color.white);
				}
			}
		});
		textFieldTipoDato.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if(textFieldTipoDato.getSelectedText()==null)
					textFieldTipoDato.setText(textFieldTipoDato.getText().trim());
			}
		});
		textFieldTipoDato.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				textFieldTipoDato.requestFocus();
				if(textFieldTipoDato.getText().equalsIgnoreCase(atributo.getTipoDato())){
					textFieldTipoDato.setText("");
					textFieldTipoDato.setForeground(Color.black);
				}
			}
		});
		textFieldTipoDato.setForeground(Color.LIGHT_GRAY);
		textFieldTipoDato.setColumns(10);
		textFieldTipoDato.setBounds(154, 148, 164, 24);
		contentPane.add(textFieldTipoDato);
	}

	private void addComboboxAcceso () { // COMBOBOX ACCESO

		comboBoxAcceso = new JComboBox<String>();
		comboBoxAcceso.setModel(new DefaultComboBoxModel<String>(new String[] {"public", "private", "protected"}));

		if(atributo.getVisibilidad().equalsIgnoreCase("+"))
			comboBoxAcceso.setSelectedIndex(0);
		else if(atributo.getVisibilidad().equalsIgnoreCase("-"))
			comboBoxAcceso.setSelectedIndex(1);
		else if(atributo.getVisibilidad().equalsIgnoreCase("#"))
			comboBoxAcceso.setSelectedIndex(2);

		comboBoxAcceso.setFont(new Font("Dialog", Font.BOLD, 13));

		comboBoxAcceso.setBounds(199, 61, 119, 24);
		contentPane.add(comboBoxAcceso);
	}

	private String determinarModificadorDeAcceso (){
		String acceso = "";

		if (comboBoxAcceso.getSelectedIndex() == 0) // se seleccionó public
			acceso = "+";
		else if (comboBoxAcceso.getSelectedIndex() == 1) // se seleccionó private
			acceso = "-";
		else // se seleccionó protected
			acceso = "#";

		return acceso;

	}
}
