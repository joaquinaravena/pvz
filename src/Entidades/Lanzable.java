package Entidades;

import GUI.Ventana;
import Logica.Fila;

public abstract class Lanzable extends Entidad {
	public abstract Lanzable clone(Ventana v);
	public abstract void mover();
	public abstract void chocar(Zombie z);
	
}
