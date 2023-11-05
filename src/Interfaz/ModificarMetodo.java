package Interfaz;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import mensajesError.AbstractoAConcreta;
import mensajesError.NocumpleSobrec;
import util.JTextFieldMejorado;
import util.PanelClase;
import util.ParametroTableModel;
import Clases.GestorUML;
import Clases.Metodo;
import Clases.MetodoOrdinario;
import Clases.Parametro;
import Logica.Operaciones;



public class ModificarMetodo extends JFrame {
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
	private JLabel lblTipoDeRetorno;
	private JLabel labelNombre;
	private JTextField textFieldNombre;
	private JTextField textFieldTipoDato;
	private boolean isAbstracto;
	private  Font font;

	private int mouseX;
	private int mouseY;
	private JLabel lblParmetros;
	private JPanel panel;
	private JTextFieldMejorado textFieldParamTipoDeDato;
	private JScrollPane scrollPane;
	private JLabel labelPlus;
	private JLabel labelMinus;
	private JPanel panelPlus;
	private JPanel panelMinus;
	private JTable table;
	private ButtonGroup bg;
	private JRadioButton rdbtnAbstracto;
	private JRadioButton rdbtnConcreto;
	private JLabel lblNombreMetodo;
	private JLabel labelRojoCategoria;
	private JLabel labelRojoCampos;
	private JLabel lblAadir;
	private JLabel lblRemover;
	private JLabel labelRojoParametros;
	private JLabel label;
	private Metodo metodo;
	private JTextFieldMejorado textFieldParametroNombre;




	public PanelClase getPe() {
		return pe;
	}

	/**
	 * Create the frame.
	 */
	public ModificarMetodo(PanelClase p, Metodo m) {
		pe = p;
		metodo = m;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 609, 304);
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
				table.setColumnSelectionAllowed(false);
				table.setCellSelectionEnabled(false);
				table.clearSelection();
				mouseX= e.getX();
				mouseY= e.getY();
			}
		});
		contentPane.setBackground(new Color(153, 204, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panelConfirmar = new JPanel();
		panelConfirmar.setLayout(null);
		panelConfirmar.setBackground(SystemColor.inactiveCaptionBorder);
		panelConfirmar.setBounds(320, 248, 98, 38);
		panelConfirmar.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent arg0) {
				panelConfirmar.setBackground(new Color(104,137,148));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				panelConfirmar.setBackground(SystemColor.inactiveCaptionBorder);
				labelRojoCampos.setVisible(false);
				labelRojoCategoria.setVisible(false);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				if(!textFieldNombre.getText().equals("")&&!textFieldTipoDato.getText().equals("")&&(rdbtnAbstracto.isSelected()||rdbtnConcreto.isSelected())){
					modificarMetodo();
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
		panelCancelar.setBounds(482, 248, 98, 38);
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

		labelTitulo = new JLabel("Modificar Metodo:");
		labelTitulo.setFont(new Font("Dialog", Font.BOLD, 16));
		labelTitulo.setBounds(25, 11, 147, 38);
		contentPane.add(labelTitulo);



		labelAccesos = new JLabel("Modificador de acceso:");
		labelAccesos.setFont(new Font("Dialog", Font.BOLD, 14));
		labelAccesos.setBounds(15, 144, 187, 25);
		contentPane.add(labelAccesos);

		lblTipoDeRetorno = new JLabel("     Tipo de Retorno:");
		lblTipoDeRetorno.setFont(new Font("Dialog", Font.BOLD, 14));
		lblTipoDeRetorno.setBounds(0, 216, 142, 25);
		contentPane.add(lblTipoDeRetorno);

		labelNombre = new JLabel("Nombre:");
		labelNombre.setFont(new Font("Dialog", Font.BOLD, 14));
		labelNombre.setBounds(71, 180, 68, 25);
		contentPane.add(labelNombre);





		lblParmetros = new JLabel("Tipo de Dato:");
		lblParmetros.setFont(new Font("Dialog", Font.BOLD, 12));
		lblParmetros.setBounds(501, 84, 98, 14);
		contentPane.add(lblParmetros);

		panel = new JPanel();
		panel.setBounds(320, 47, 159, 147);
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		scrollPane = new JScrollPane();

		panel.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();


		table.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				table.setColumnSelectionAllowed(true);
				table.setCellSelectionEnabled(true);
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				table.setColumnSelectionAllowed(false);
				table.setCellSelectionEnabled(false);
				table.clearSelection();
			}
		});
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				table.setColumnSelectionAllowed(true);
				table.setCellSelectionEnabled(true);
			}
		});
		table.setModel(new ParametroTableModel());
		table.getTableHeader().setResizingAllowed(false);
		table.getTableHeader().setReorderingAllowed(false);
		addParametrosDefualtTable();
		scrollPane.setViewportView(table);

		textFieldParamTipoDeDato = new JTextFieldMejorado();
		textFieldParamTipoDeDato.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				textFieldParamTipoDeDato.requestFocus();
			}
		});
		textFieldParamTipoDeDato.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if(textFieldParamTipoDeDato.getSelectedText()==null)
					textFieldParamTipoDeDato.setText(textFieldParamTipoDeDato.getText().trim());
			}
		});
		textFieldParamTipoDeDato.setLimite(30);
		textFieldParamTipoDeDato.setBounds(501, 101, 98, 20);
		contentPane.add(textFieldParamTipoDeDato);
		textFieldParamTipoDeDato.setColumns(10);

		panelPlus = new JPanel();
		panelPlus.setBounds(549, 144, 50, 50);
		panelPlus.setBackground(new Color(153,204,204));
		panelPlus.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!textFieldParamTipoDeDato.getText().equals("") && !textFieldParametroNombre.getText().equals("")){ // HACER VERIFICACION DE DUPLICIDAD DE PARAMETROS
					((ParametroTableModel) table.getModel()).adicionar(textFieldParamTipoDeDato.getText(), textFieldParametroNombre.getText());
					textFieldParamTipoDeDato.setText("");
					textFieldParametroNombre.setText("");
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				panelPlus.setBackground(new Color(104,137,148));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				panelPlus.setBackground(new Color(153,204,204));
			}
		});
		contentPane.add(panelPlus);
		panelPlus.setLayout(null);

		labelPlus = new JLabel("");
		labelPlus.setBounds(0, 0, 50, 50);
		panelPlus.add(labelPlus);
		labelPlus.setIcon(new ImageIcon(AgregarMetodo.class.getResource("/images/plus.png")));

		panelMinus = new JPanel();
		panelMinus.setBounds(489, 144, 50, 50);
		panelMinus.setBackground(new Color(153,204,204));
		panelMinus.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(table.getSelectedRowCount()!=0)
					elimniarParametros();
				else
					labelRojoParametros.setVisible(true);
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				panelMinus.setBackground(new Color(104,137,148));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				panelMinus.setBackground(new Color(153,204,204));
				labelRojoParametros.setVisible(false);
			}
		});
		contentPane.add(panelMinus);
		panelMinus.setLayout(null);

		labelMinus = new JLabel("");
		labelMinus.setBounds(0, 0, 50, 50);
		panelMinus.add(labelMinus);
		labelMinus.setIcon(new ImageIcon(AgregarMetodo.class.getResource("/images/minus.png")));


		lblNombreMetodo = new JLabel(metodo.getNombre());
		lblNombreMetodo.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNombreMetodo.setBounds(169, 13, 206, 35);
		contentPane.add(lblNombreMetodo);

		labelRojoCategoria = new JLabel("Debe seleccionar una categor\u00EDa");
		labelRojoCategoria.setVisible(false);
		labelRojoCategoria.setForeground(Color.RED);
		labelRojoCategoria.setFont(new Font("Tahoma", Font.BOLD, 13));
		labelRojoCategoria.setBounds(25, 113, 219, 24);
		contentPane.add(labelRojoCategoria);

		labelRojoCampos = new JLabel("Es necesario completar ambos campos");
		labelRojoCampos.setVisible(false);
		labelRojoCampos.setForeground(Color.RED);
		labelRojoCampos.setFont(new Font("Tahoma", Font.BOLD, 13));
		labelRojoCampos.setBounds(21, 248, 257, 14);
		contentPane.add(labelRojoCampos);

		lblAadir = new JLabel("A\u00F1adir:");
		lblAadir.setFont(new Font("Dialog", Font.BOLD, 12));
		lblAadir.setBounds(549, 132, 50, 14);
		contentPane.add(lblAadir);

		lblRemover = new JLabel("Remover:");
		lblRemover.setFont(new Font("Dialog", Font.BOLD, 12));
		lblRemover.setBounds(489, 132, 61, 14);
		contentPane.add(lblRemover);

		labelRojoParametros = new JLabel("Primero seleccione el par\u00E1metro a remover");
		labelRojoParametros.setVisible(false);
		labelRojoParametros.setFont(new Font("Tahoma", Font.BOLD, 12));
		labelRojoParametros.setForeground(Color.RED);
		labelRojoParametros.setBounds(337, 201, 262, 20);
		contentPane.add(labelRojoParametros);

		JSeparator separator = new JSeparator();
		separator.setBounds(579, 239, 1, 47);
		contentPane.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 74, 252, 2);
		contentPane.add(separator_1);

		label = new JLabel("Tipo de m\u00E9todo");
		label.setFont(new Font("Dialog", Font.BOLD, 14));
		label.setBounds(79, 49, 119, 27);
		contentPane.add(label);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(337, 34, 252, 2);
		contentPane.add(separator_2);

		JLabel lblAadirParmetro = new JLabel("A\u00F1adir Par\u00E1metro:");
		lblAadirParmetro.setHorizontalAlignment(SwingConstants.CENTER);
		lblAadirParmetro.setFont(new Font("Dialog", Font.BOLD, 14));
		lblAadirParmetro.setBounds(337, 11, 250, 24);
		contentPane.add(lblAadirParmetro);

		addTextFieldNombre();
		addTextFieldTipoDato();
		addComboBoxAcceso();
		addRadioButtons();
	}

	//BOTONES

	private void addTextFieldNombre () {

		textFieldNombre = new JTextField(metodo.getNombre());
		textFieldNombre.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (textFieldNombre.getText().equalsIgnoreCase("")){
					textFieldNombre.setText(metodo.getNombre());
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
				if(textFieldNombre.getText().equalsIgnoreCase(metodo.getNombre())){
					textFieldNombre.setText("");
					textFieldNombre.setForeground(Color.black);
				}

			}
		});

		textFieldNombre.setBounds(149, 180, 164, 24);
		contentPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);
	}

	private void addTextFieldTipoDato () {
		textFieldTipoDato = new JTextField(((MetodoOrdinario) metodo).getTipoRetorno());
		textFieldTipoDato.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {

				if (textFieldTipoDato.getText().equalsIgnoreCase("")){
					textFieldTipoDato.setText(((MetodoOrdinario) metodo).getTipoRetorno());
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
		textFieldTipoDato.setForeground(Color.LIGHT_GRAY);
		textFieldTipoDato.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				textFieldTipoDato.requestFocus();
				if(textFieldTipoDato.getText().equalsIgnoreCase(((MetodoOrdinario) metodo).getTipoRetorno())){
					textFieldTipoDato.setText("");
					textFieldTipoDato.setForeground(Color.black);
				}

			}
		});
		textFieldTipoDato.setColumns(10);
		textFieldTipoDato.setBounds(149, 216, 164, 24);
		contentPane.add(textFieldTipoDato);
	}

	private void addComboBoxAcceso () {
		comboBoxAcceso = new JComboBox<String>();
		comboBoxAcceso.setModel(new DefaultComboBoxModel<String>(new String[] {"public", "private", "protected"}));

		if(metodo.getModificadorAcceso().equalsIgnoreCase("+"))
			comboBoxAcceso.setSelectedIndex(0);
		else if(metodo.getModificadorAcceso().equalsIgnoreCase("-"))
			comboBoxAcceso.setSelectedIndex(1);
		else if(metodo.getModificadorAcceso().equalsIgnoreCase("#"))
			comboBoxAcceso.setSelectedIndex(2);

		comboBoxAcceso.setFont(new Font("Dialog", Font.BOLD, 13));
		comboBoxAcceso.setBounds(194, 145, 119, 24);
		contentPane.add(comboBoxAcceso);

	}

	private void addRadioButtons () {
		rdbtnAbstracto = new JRadioButton("Abstracto");
		rdbtnAbstracto.setFont(new Font("Tahoma", Font.BOLD, 12));
		rdbtnAbstracto.setBackground(new Color(153,204,204));
		rdbtnAbstracto.setBounds(32, 83, 91, 23);
		contentPane.add(rdbtnAbstracto);

		rdbtnConcreto = new JRadioButton("Concreto");
		rdbtnConcreto.setFont(new Font("Tahoma", Font.BOLD, 12));
		rdbtnConcreto.setBackground(new Color(153,204,204));
		rdbtnConcreto.setBounds(149, 83, 91, 23);
		contentPane.add(rdbtnConcreto);

		if(((MetodoOrdinario) metodo).isAbstracto())
			rdbtnAbstracto.setSelected(true);
		else
			rdbtnConcreto.setSelected(true);

		bg = new ButtonGroup();
		bg.add(rdbtnConcreto);
		bg.add(rdbtnAbstracto);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNombre.setBounds(501, 42, 71, 14);
		contentPane.add(lblNombre);
		
		textFieldParametroNombre = new JTextFieldMejorado();
		textFieldParametroNombre.setLimite(30);
		textFieldParametroNombre.setColumns(10);
		textFieldParametroNombre.setBounds(501, 60, 98, 20);
		contentPane.add(textFieldParametroNombre);

	}



	// FIN BOTONES

	public ArrayList<Parametro> obtenerParametrosTabla(){ // Metodo para obtener los parametros
		ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		int cantRows = ((ParametroTableModel)table.getModel()).getRowCount();

		for(int i=0 ;i<cantRows;i++ ){
			parametros.add(new Parametro(String.valueOf(((ParametroTableModel)table.getModel()).getValueAt(i, 0)), String.valueOf(((ParametroTableModel)table.getModel()).getValueAt(i, 1)))); // TEMPORAL

		}

		return parametros;

	}

	public ArrayList<String> obtenerStringsTabla(){
		ArrayList<String> parametros = new ArrayList<String>();

		int cantRows = ((ParametroTableModel)table.getModel()).getRowCount();

		for(int i=0 ;i<cantRows;i++ ){
			parametros.add(String.valueOf(((ParametroTableModel)table.getModel()).getValueAt(i, 0)));

		}

		return parametros;


	}

	public String mostrarParametros(ArrayList<String> parametros){
		String mostrar = "";

		for(int i = 0; i<parametros.size();i++){
			mostrar+=parametros.get(i) + ", ";
		}

		return mostrar.substring(0, mostrar.length()-2);

	}



	public void elimniarParametros(){

		for(int i=0;table.getSelectedRows().length!=0;i++){
			System.out.println(table.getSelectedRows().length);
			i=0;	
			((ParametroTableModel)table.getModel()).removeRow(table.getSelectedRows()[i]);

		}

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

	private boolean determinarAbstraccion (){ // Metodo para saber si se selecciono la opcion de abstracto o no
		boolean isAbstract = false;

		if (rdbtnAbstracto.isSelected())
			isAbstracto = true;

		return isAbstract;
	}

	private void addParametrosDefualtTable(){
		ArrayList<Parametro> parametros = metodo.getParametros();

		for(int i=0; i<parametros.size();i++){
			((ParametroTableModel) table.getModel()).adicionar(parametros.get(i).getTipoDato(), parametros.get(i).getNombre()); // TEMPORAL
		}
	}

	private void modificarMetodo(){
		GestorUML.getInstancie().getProyectoSeleccionado().getDiagramaSeleccionado().modificarMetodoOrdinario(metodo, textFieldNombre.getText(), textFieldTipoDato.getText(), determinarModificadorDeAcceso(), determinarAbstraccion(), obtenerParametrosTabla()); // Se modifica el metodo
		pe.actualizarMetodos(); // se actualiza la informacion de los metodos en caso de modificacion
	}
}
