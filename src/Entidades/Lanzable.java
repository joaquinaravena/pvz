package Entidades;

public abstract class Lanzable extends Entidad {
	public abstract Lanzable clone();
	public abstract void mover();
	public abstract void chocar(Zombie z);
	
}
