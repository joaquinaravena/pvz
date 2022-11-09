package Entidades;

import GUI.Ventana;

public class Sol extends Lanzable{
	
	public Sol() {
		super();
	}
	
	public void mover() {
		y=y-10;
	}
	
	public Sol clone(Ventana v) {
		Sol aRetornar=new Sol();
		return aRetornar;
	}

	@Override
	public void chocar(Zombie z) {
	}
}
