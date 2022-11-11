package Entidades;

import GUI.EntidadGrafica;
import GUI.Ventana;

public class Sol extends Lanzable{
	int contador;
	public Sol(Ventana v,String graf) {
		super();
		contador=0;
		this.alto = 45;
		this.ancho = 45;
		entidadGrafica=new EntidadGrafica(v,this,graf);
		entidadGrafica.getGrafica().setBounds(30, 0, alto, ancho);
		entidadGrafica.borrarGrafica();
	}
	
	public void mover() {
		if(contador<4) {
			y=y-10;
			x=x-1;
			getEntidadGrafica().getGrafica().setBounds(getEntidadGrafica().getGrafica().getX()-1, getEntidadGrafica().getGrafica().getY()-10, getEntidadGrafica().getGrafica().getWidth(), getEntidadGrafica().getGrafica().getHeight());
			contador++;
		}
		else {
			y=y+10;
			x=x-1;
			getEntidadGrafica().getGrafica().setBounds(getEntidadGrafica().getGrafica().getX()-1, getEntidadGrafica().getGrafica().getY()+10, getEntidadGrafica().getGrafica().getWidth(), getEntidadGrafica().getGrafica().getHeight());
		}
		getEntidadGrafica().actualizarGrafica();
	}
	
	public Sol clone(Ventana v) {
		Sol aRetornar=new Sol(v,this.getEntidadGrafica().getRutaGrafica());
		return aRetornar;
	}

	@Override
	public void chocar(Zombie z) {
	}

	@Override
	public void morir() {
		miFila.getJuego().agregarLanzableAEliminar(this);
	}
	
}
