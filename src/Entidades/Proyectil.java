package Entidades;


import GUI.EntidadGrafica;
import GUI.Ventana;
import Logica.Fila;

public class Proyectil extends Lanzable{
	protected int valorAccion;
	
	public Proyectil(int valorAccion,Ventana v,String graf,Fila f) {
		this.ancho = 30;
		this.alto = 30;
		this.valorAccion = valorAccion;
		entidadGrafica=new EntidadGrafica(v,this,graf);
		entidadGrafica.getGrafica().setBounds(0, 30, this.ancho, this.alto);
		entidadGrafica.borrarGrafica();
		miFila=f;
	}
	
	
	public void mover() {
		x = x+30;
		getEntidadGrafica().getGrafica().setBounds(getEntidadGrafica().getGrafica().getX()+30, getEntidadGrafica().getGrafica().getY(), getEntidadGrafica().getGrafica().getWidth(), getEntidadGrafica().getGrafica().getHeight());
	}
	public void chocar(Zombie z) {
		z.restarVida(valorAccion);
		this.morir();
	}
	public int getValorAccion() {
		return valorAccion;
	}
	
	public Proyectil clone(Ventana v) {
		Proyectil aRetornar=new Proyectil(this.valorAccion,v,this.entidadGrafica.getRutaGrafica(),miFila);
		return aRetornar;
	}
	
	public void morir() {
		miFila.getJuego().getAdministradorJuego().agregarLanzableAEliminar(this);
		entidadGrafica.borrarGrafica();
	}
}
