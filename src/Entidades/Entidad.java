package Entidades;

import GUI.EntidadGrafica;

public abstract class Entidad {
	
	protected int x;
	protected int y;
	protected int ancho;
	protected int alto;
	protected EntidadGrafica grafica;

	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getAncho() {
		return ancho;
	}
	public int getAlto() {
		return alto;
	}

}
