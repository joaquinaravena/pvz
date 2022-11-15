package Entidades;

import GUI.SolGrafico;
import GUI.Ventana;
import Logica.Juego;

public class Sol extends Lanzable{
	int contador;
	boolean solDePlanta;
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
				x=x-2;
				getEntidadGrafica().getGrafica().setBounds(getEntidadGrafica().getGrafica().getX()-2, getEntidadGrafica().getGrafica().getY()-10, getEntidadGrafica().getGrafica().getWidth(), getEntidadGrafica().getGrafica().getHeight());
				contador++;
			}
			else if(contador <11) {
				y=y+10;
				x=x-2;
				contador++;
				getEntidadGrafica().getGrafica().setBounds(getEntidadGrafica().getGrafica().getX()-2, getEntidadGrafica().getGrafica().getY()+10, getEntidadGrafica().getGrafica().getWidth(), getEntidadGrafica().getGrafica().getHeight());
			}
			getEntidadGrafica().actualizarGrafica();
		}
		else {
			if(y<400) {
				y=y+10;
				getEntidadGrafica().getGrafica().setBounds(getEntidadGrafica().getGrafica().getX(), getEntidadGrafica().getGrafica().getY()+10, getEntidadGrafica().getGrafica().getWidth(), getEntidadGrafica().getGrafica().getHeight());
				getEntidadGrafica().actualizarGrafica();
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
	public void morir(Juego j) {
		j.getAdministradorJuego().agregarLanzableAEliminar(this);
		entidadGrafica.borrarGrafica();
	}
	
}
