package Entidades;

import javax.swing.ImageIcon;

import GUI.EntidadGrafica;
import GUI.Ventana;

public class Planta extends Entidad {
	protected int precio;
	protected int vida;
	protected int daño;
	protected Lanzable miProyectil;
	//ancho y alto representan las dimensiones.
	//Las plantas se crean sin una posición establecida.
	public Planta(int precio, int vida, int daño, Ventana v, String graf) {
		this.precio = precio;
		this.vida = vida;
		miProyectil = new Proyectil(daño,v,"Recursos/pea.png",miFila);
		this.daño=daño;
		entidadGrafica = new EntidadGrafica(v, this, graf);
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
			aDisparar.setX(this.getX());
			aDisparar.setY(this.getY());
			aDisparar.getEntidadGrafica().getGrafica().setIcon(new ImageIcon("/Recursos/pea.png"));
			aDisparar.getEntidadGrafica().actualizarFilaGrafica(getNumeroFila());
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
		Planta p = new Planta(this.precio, this.vida, this.daño ,v, this.entidadGrafica.getRutaGrafica());
		return p;
	}
}
