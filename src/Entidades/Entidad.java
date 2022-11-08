package Entidades;

import Logica.*;

import GUI.EntidadGrafica;

public abstract class Entidad {
	
	protected int x;
	protected int y;
	protected final int ancho = 60;
	protected final int alto = 60;
	protected String miRuta;
	protected EntidadGrafica entidadGrafica;
	protected int fila;
	protected Fila miFila;

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
	public EntidadGrafica getEntidadGrafica() {
		return entidadGrafica;
	}
	public void setFila(Fila f) {
		miFila = f;
	}
	public Fila getFila() {
		return miFila;
	}
	public void setNumeroFila(int i) {
		fila = i;
		entidadGrafica.actualizarFilaGrafica(i);
	}
	public int getNumeroFila() {
		return fila;
	}

}
