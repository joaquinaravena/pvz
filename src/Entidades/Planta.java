package Entidades;
import GUI.EntidadGrafica;
import GUI.Ventana;

public class Planta extends Entidad {
	protected int precio;
	protected int vida;
	protected int daño;
	protected Lanzable miProyectil;
	protected boolean tieneProyectil;
	//ancho y alto representan las dimensiones.
	//Las plantas se crean sin una posición establecida.
	public Planta(int precio, int vida, int daño, Ventana v, String graf,Lanzable proyectil) {
		this.precio = precio;
		this.vida = vida;
		this.daño=daño;
		entidadGrafica = new EntidadGrafica(v, this, graf);
		miProyectil = proyectil;
		if(miProyectil==null)
			tieneProyectil=false;
		else 
			tieneProyectil=true;
	}

	public void restarVida(int i) {
		vida -= i;
	}
	public void morir() {
		if(vida <= 0) {
			entidadGrafica.borrarGrafica();
			//eliminar de las listas
		}
	}
	

	public void realizarAccion(Ventana v) {
			Lanzable aDisparar=miProyectil.clone(v);
			aDisparar.setX(x);
			aDisparar.setY(y);
			aDisparar.getEntidadGrafica().getGrafica().setLocation(x, y);
			miFila.agregarProyectiles(aDisparar);
	}
	
	public int getDaño() {
		return daño;
	}
	public int getPrecio() {
		return precio;
	}
	public Lanzable getProyectil() {
		return miProyectil;
	}
	public Planta clone(Ventana v) {
		Planta p = new Planta(this.precio, this.vida, this.daño ,v, this.entidadGrafica.getRutaGrafica(),miProyectil);
		return p;
	}
}
