package Entidades;

import GUI.SolGrafico;
import GUI.Ventana;

public class Sol extends Lanzable{
	protected int contador;
	protected boolean solDePlanta;
	
	public Sol(Ventana v,String graf,boolean solDePlanta) {
		super();
		this.solDePlanta=solDePlanta;
		contador=0;
		this.alto = 45;
		this.ancho = 45;
		entidadGrafica=new SolGrafico(v,this,graf, v.getJuego());
		entidadGrafica.getGrafica().setBounds(30, 0, alto, ancho);
		entidadGrafica.borrarGrafica();
	}
	
	public void mover() {
		if(solDePlanta) {
			if(contador<4) {
				y=y-10;
				x=x-4;
				getEntidadGrafica().getGrafica().setBounds(getEntidadGrafica().getGrafica().getX()-4, getEntidadGrafica().getGrafica().getY()-10, getEntidadGrafica().getGrafica().getWidth(), getEntidadGrafica().getGrafica().getHeight());
				contador++;
			}
			else if(contador <11) {
				y=y+10;
				x=x-3;
				contador++;
				getEntidadGrafica().getGrafica().setBounds(getEntidadGrafica().getGrafica().getX()-3, getEntidadGrafica().getGrafica().getY()+10, getEntidadGrafica().getGrafica().getWidth(), getEntidadGrafica().getGrafica().getHeight());
			}
		}
		else {
			if(y<400) {
				y=y+10;
				getEntidadGrafica().getGrafica().setBounds(getEntidadGrafica().getGrafica().getX(), getEntidadGrafica().getGrafica().getY()+10, getEntidadGrafica().getGrafica().getWidth(), getEntidadGrafica().getGrafica().getHeight());
			}
		}
		
	}
	
	public Sol clone(Ventana v) {
		Sol aRetornar=new Sol(v,this.getEntidadGrafica().getRutaGrafica(),solDePlanta);
		return aRetornar;
	}

	@Override
	public void chocar(Zombie z) {
	}

	@Override
	public void morir() {
		this.getEntidadGrafica().getVentana().getJuego().getAdministradorJuego().agregarLanzableAEliminar(this);
		entidadGrafica.borrarGrafica();
	}
	
}
