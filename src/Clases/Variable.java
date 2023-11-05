package Clases;

import java.io.Serializable;

public abstract class Variable implements TipoValidable, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String nombre;
	protected String tipoDato;


	public Variable(String tipoDato, String nombre) {
		this.setNombre(nombre);
		this.setTipoDato(tipoDato);
	}

	public Variable(){}


	public boolean validarTipo() {
		return false;
	}


	public String getTipoDato() {
		return this.tipoDato;
	}


	public void setTipoDato(String tipoDato) {
		this.tipoDato = tipoDato;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	protected boolean equalsTipoDato(Variable v){
		boolean verificador = false;

		if(this.tipoDato.equals(v.getTipoDato()))
			verificador = true;

		return verificador;
	}
	
	public abstract String toString();


}
