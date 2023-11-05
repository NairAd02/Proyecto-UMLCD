package Clases;

import java.util.ArrayList;

public class MetodoOrdinario extends Metodo {
	private static final long serialVersionUID = 1L;
	private boolean abstracto;
	private String tipoRetorno;
	
	
	public MetodoOrdinario(String nombre, String modificadorAcceso, boolean abstracto,	String tipoRetorno, ArrayList<Parametro> parametros) {
		super(nombre, modificadorAcceso, parametros);
		this.abstracto = abstracto;
		this.tipoRetorno = tipoRetorno;
	}
	
	public MetodoOrdinario(String nombre, String modificadorAcceso, boolean abstracto,
			String tipoRetorno) {
		super(nombre, modificadorAcceso);
		this.abstracto = abstracto;
		this.tipoRetorno = tipoRetorno;
	}
	
	public MetodoOrdinario(String nombre, String modificadorAcceso, boolean abstracto,
			String tipoRetorno, Parametro parametro) {
		super(nombre, modificadorAcceso, parametro);
		this.abstracto = abstracto;
		this.tipoRetorno = tipoRetorno;
	}


	public boolean isAbstracto() {
		return abstracto;
	}

	public void setAbstracto(boolean abstracto) {
		this.abstracto = abstracto;
	}

	public String getTipoRetorno() {
		return tipoRetorno;
	}

	public void setTipoRetorno(String tipoRetorno) {
		this.tipoRetorno = tipoRetorno;
	}


	public  String toString () { // Metodo para definir como se va a mostrar la informacion del metodo
		String cadenaSalida = this.modificadorAcceso + " " + this.nombre + "(";


		for (Parametro p : this.parametros) {
			cadenaSalida += p.toString() + ", ";
		}
		if (this.parametros.size() != 0)
			cadenaSalida = cadenaSalida.substring(0, cadenaSalida.length() - 2) + ")" + ": " + this.tipoRetorno;
		else
			cadenaSalida += ")" + ": " + this.tipoRetorno;

		return cadenaSalida;
	}
}
