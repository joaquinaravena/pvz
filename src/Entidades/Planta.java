package Entidades;

import GUI.EntidadGrafica;
import GUI.Ventana;

public class Planta extends Entidad {
	protected int precio;
	protected int vida;
	protected Proyectil miProyectil;
	
	//ancho y alto representan las dimensiones.
	//Las plantas se crean sin una posición establecida.
	public Planta(int precio, int vida, int daño, Ventana v, String graf) {
		this.precio = precio;
		this.vida = vida;
		miProyectil = new Proyectil(daño);
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
	public void realizarAccion() {
		miFila.agregarProyectiles(miProyectil);
	}
	//Se puede sacar si la colision la maneja únicamente el zombie
	public void chocarZombie(Zombie z) {
		
	}
	public int getPrecio() {
		return precio;
	}
	public Proyectil getProyectil() {
		return miProyectil;
	}
	public Planta clone(Ventana v) {
		Planta p = new Planta(this.precio, this.vida, this.getProyectil().getValorAccion() ,v, this.entidadGrafica.getRutaGrafica());
		return p;
	}
}
