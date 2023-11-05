package Clases;

import java.io.Serializable;
import java.util.ArrayList;

public class Abstracta extends Clase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Abstracta(String nombre, int posicionX, int posicionY, int dimensionX, int dimensionY) throws Exception {
		super(nombre, posicionX, posicionY, dimensionX, dimensionY);

	}

	public Abstracta(){
		super();
	}

	public ArrayList<Metodo> getAbstractos(){
		ArrayList<Metodo> metodosAbstractos = new ArrayList<Metodo>();
		for(Metodo a:this.metodos){
			if( a instanceof MetodoOrdinario && ((MetodoOrdinario) a).isAbstracto())
				metodosAbstractos.add(a);
		}


		return metodosAbstractos;

	}

	public boolean soloMetedosAbstractos(){
		boolean veredicto = true;

		for(int i = 0; i<this.metodos.size() && veredicto == true; i++){
			if( this.metodos.get(i) instanceof MetodoOrdinario &&!((MetodoOrdinario) this.metodos.get(i)).isAbstracto())
				veredicto = false;
		}

		return veredicto;

	}  

}
