package Entidades;

import GUI.EntidadGrafica;
import GUI.Ventana;

public class Planta extends Entidad {
	protected int precio;
	protected int vida;
	protected Proyectil miProyectil;
	
	//x e y representan la posicion, ancho y alto las dimensiones.
	public Planta(int x, int y, int ancho, int alto,int precio, int vida, int daño, Ventana v) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.precio = precio;
		this.vida = vida;
		miProyectil = new Proyectil(daño);
		grafica = new EntidadGrafica(v);
	}
	
	public void restarVida(int i) {
		vida -= i;
	}
	//if vida <= 0
	public void morir() {
		//borra la gráfica y lo elimina de todas las listas a las que pertenece
	}
	public void realizarAccion(Zombie z) {
		miProyectil.mover();
	}
	//Se puede sacar si la colision la maneja únicamente el zombie
	public void chocarZombie(Zombie z) {
		
	}
	public int getPrecio() {
		return precio;
	}
}
