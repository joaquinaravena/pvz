package Entidades;

import javax.swing.ImageIcon;

import GUI.EntidadGrafica;
import GUI.Ventana;

public class Planta extends Entidad {
	protected int precio;
	protected int vida;
	protected int daño;
	protected Lanzable miProyectil;
	boolean hayZombie;
	//ancho y alto representan las dimensiones.
	//Las plantas se crean sin una posición establecida.
	public Planta(int precio, int vida, int daño, Ventana v, String graf) {
		this.precio = precio;
		this.vida = vida;
		miProyectil = new Proyectil(daño);
		miProyectil.getEntidadGrafica().getGrafica().setIcon(new ImageIcon("/Recursos/pea.png"));
		this.daño=daño;
		entidadGrafica = new EntidadGrafica(v, this, graf);
		hayZombie=false;
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
	public void realizarAccion() {
		if(hayZombie) {
			Lanzable aDisparar=miProyectil.clone();
			miFila.agregarProyectiles(aDisparar);
		}
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
		Planta p = new Planta(this.precio, this.vida, this.daño ,v, this.entidadGrafica.getRutaGrafica());
		return p;
	}
}
