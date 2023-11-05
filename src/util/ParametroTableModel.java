package util;

import javax.swing.table.DefaultTableModel;

import Clases.Parametro;

public class ParametroTableModel extends DefaultTableModel {
	private static final long serialVersionUID = 1L;

	public ParametroTableModel(){

		String[] columnNames = {"Tipo de Dato", "Nombre"};    
		this.setColumnIdentifiers(columnNames); 
		

	}
	
	public void adicionar(String tipoDeDato, String nombre){
		Object[] newRow = new Object[]{tipoDeDato, nombre};
		addRow(newRow);
	}
	
	
	

}
