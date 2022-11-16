package Entidades;

import GUI.Ventana;

public abstract class Lanzable extends Entidad {
	public abstract Lanzable clone(Ventana v);
	public abstract void mover();
	public abstract void chocar(Zombie z);
	public abstract void morir() ;
	
}
