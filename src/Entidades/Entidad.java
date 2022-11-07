package Entidades;

import GUI.EntidadGrafica;

public abstract class Entidad {
	
	protected int x;
	protected int y;
	protected final int ancho = 10;
	protected final int alto = 20;
	protected String miRuta;
	protected EntidadGrafica grafica;

	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
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
	public EntidadGrafica getGrafica() {
		return grafica;
	}

}
