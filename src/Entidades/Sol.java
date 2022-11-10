package Entidades;

import GUI.EntidadGrafica;
import GUI.Ventana;

public class Sol extends Lanzable{
	int contador;
	public Sol(Ventana v,String graf) {
		super();
		contador=0;
		entidadGrafica=new EntidadGrafica(v,this,graf);
		entidadGrafica.getGrafica().setBounds(v.getBordeDerecho(), v.getLinea(getNumeroFila()), entidadGrafica.getGrafica().getIcon().getIconWidth(), entidadGrafica.getGrafica().getIcon().getIconHeight());
	}
	
	public void mover() {
		if(contador<4) {
			y=y-20;
			getEntidadGrafica().getGrafica().setBounds(getEntidadGrafica().getGrafica().getX(), getEntidadGrafica().getGrafica().getY()-20, getEntidadGrafica().getGrafica().getWidth(), getEntidadGrafica().getGrafica().getHeight());
			contador++;
		}
		else {
			y=y+20;
			getEntidadGrafica().getGrafica().setBounds(getEntidadGrafica().getGrafica().getX(), getEntidadGrafica().getGrafica().getY()+20, getEntidadGrafica().getGrafica().getWidth(), getEntidadGrafica().getGrafica().getHeight());
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
		miFila.removerProyectil(this);
		entidadGrafica.borrarGrafica();
	}
	
}
