package Entidades;

public abstract class Proyectil extends Entidad{
	protected int valorAccion;
	public void mover() {
		x = x+5;
	}
	public void chocar(Zombie z) {
		z.restarVida(valorAccion);
	}
}
