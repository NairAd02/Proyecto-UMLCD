package Clases;

import java.io.Serializable;

public class Concreta extends Clase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Concreta(String nombre, int posicionX, int posicionY, int dimensionX, int dimensionY) throws Exception {
		super(nombre, posicionX, posicionY, dimensionX, dimensionY);


	}

	public Concreta(){
		super();
	}

	public void addMetodo(Metodo metodo) throws Exception{
		if(this.validarMetodo(metodo)){
			if (metodo instanceof MetodoConstructor)
				this.metodos.add(metodo);
			else {
				if( metodo instanceof MetodoOrdinario && !((MetodoOrdinario) metodo).isAbstracto())
					this.metodos.add(metodo);
				else
					throw new Exception("Metodo Abstracto");
			}
		}
		else{

			throw new Exception("No cumple sobreCarga");
		}
	}


}
