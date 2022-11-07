package Entidades;

import GUI.EntidadGrafica;
import GUI.Ventana;

public class Planta extends Entidad {
	protected int precio;
	protected int vida;
	protected Proyectil miProyectil;
	
	//ancho y alto representan las dimensiones.
	//Las plantas se crean sin una posici�n establecida.
	public Planta(int precio, int vida, int da�o, Ventana v) {
		this.precio = precio;
		this.vida = vida;
		miProyectil = new Proyectil(da�o);
		grafica = new EntidadGrafica(v);
	}

	public void restarVida(int i) {
		vida -= i;
	}
	//if vida <= 0
	public void morir() {
		//borra la gr�fica y lo elimina de todas las listas a las que pertenece
	}
	public void realizarAccion(Zombie z) {
		miProyectil.mover();
	}
	//Se puede sacar si la colision la maneja �nicamente el zombie
	public void chocarZombie(Zombie z) {
		
	}
	public int getPrecio() {
		return precio;
	}
	public Proyectil getProyectil() {
		return miProyectil;
	}
	public Planta clone(Ventana v) {
		Planta p = new Planta(this.precio, this.vida, this.getProyectil().getValorAccion() ,v);
		return p;
	}
}
