package Clases;

import java.io.Serializable;

public class Parametro extends Variable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	public Parametro( String tipoDato) {
		super(tipoDato);

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Parametro(){
		super();
	}

	public boolean equals(Parametro p){
		boolean verificador = false;

		if(this.equalsTipoDato(p))
			verificador = true;

		return verificador;
	}
	
	public String toString (){ // Metodo para definir como se va a mostrar la informacion del parametro
		return this.tipoDato + " " + this.nombre;
	}

}
