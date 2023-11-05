package Clases;

import java.util.ArrayList;

public class MetodoConstructor extends Metodo {
	private static final long serialVersionUID = 1L;

	
	public MetodoConstructor(String nombre, String modificadorAcceso,
			ArrayList<Parametro> parametros) {
		super(nombre, modificadorAcceso, parametros);
	}

	
	public String toString() {
		
		String cadenaSalida = this.modificadorAcceso + " " + this.nombre + "(";


		for (Parametro p : this.parametros) {
			cadenaSalida += p.toString() + ", ";
		}
		if (this.parametros.size() != 0)
			cadenaSalida = cadenaSalida.substring(0, cadenaSalida.length() - 2) + ")";
		else
			cadenaSalida += ")";

		return cadenaSalida;
	}

}
