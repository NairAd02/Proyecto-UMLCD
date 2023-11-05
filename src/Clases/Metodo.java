package Clases;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Metodo implements TipoValidable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String nombre;
	protected String modificadorAcceso;
	protected ArrayList<Parametro> parametros;




	public Metodo(String nombre, String modificadorAcceso, ArrayList<Parametro> parametros) {

		this.setNombre(nombre);
		this.setModificadorAcceso(modificadorAcceso);
		this.setParametros(parametros);
	}

	public Metodo(String nombre, String modificadorAcceso) {

		this.setNombre(nombre);
		this.setModificadorAcceso(modificadorAcceso);
		this.parametros = new ArrayList<Parametro>();
	}

	public Metodo(String nombre, String modificadorAcceso, Parametro parametro) {

		this.setNombre(nombre);
		this.setModificadorAcceso(modificadorAcceso);
		this.parametros = new ArrayList<Parametro>();
		this.parametros.add(parametro);
	}

	public Metodo(){}


	public String getModificadorAcceso() {
		return modificadorAcceso;
	}

	public void setModificadorAcceso(String modificadorAcceso) {
		this.modificadorAcceso = modificadorAcceso;
	}

	public ArrayList<Parametro> getParametros() {
		return parametros;
	}

	public int cantParametros(){
		return this.parametros.size();
	}

	public void setParametros(ArrayList<Parametro> parametros) {
		if(parametros!=null)
			this.parametros = parametros;
		else
			throw new IllegalArgumentException();
	}

	public void setNombre(String nombre) {
		if(nombre!=null && !nombre.equals(""))
			this.nombre = nombre;
		else
			throw new IllegalArgumentException();

	}

	public String getNombre() {
		return nombre;
	}

	@Override
	public boolean validarTipo() {

		return false;
	}

	public boolean comprobarParametros(ArrayList<String> parametros){
		boolean x = true;

		if(parametros.size()==this.parametros.size()){

			for(int i=0; i<this.parametros.size() && x==true;i++){
				if(!parametros.get(i).equals(this.parametros.get(i).getTipoDato()))
					x = false;

			}
		}
		else{
			x = false;
		}

		return x;
	}

	

	private boolean equalsNombre(Metodo m){
		boolean verificador = false;

		if(m.getNombre().equals(this.nombre))
			verificador = true;

		return verificador;
	}

	private boolean equalsModificadorAcceso (Metodo m){
		boolean verificador = false;

		if(m.getModificadorAcceso().equals(this.modificadorAcceso))
			verificador = true;

		return verificador;
	}

	

	private boolean equalsParametros (Metodo m){
		boolean verificador = true;
		ArrayList<Parametro> p = m.getParametros();
		if(this.parametros.size() == p.size()){
			for (int i = 0; i < this.parametros.size() && verificador; i++) {
				if(!this.parametros.get(i).equals(p.get(i)))
					verificador = false;
			}
		}
		else
			verificador = false;


		return verificador;
	}

	public abstract String toString (); // Metodo para definir como se va a mostrar la informacion del metodo
		


}
