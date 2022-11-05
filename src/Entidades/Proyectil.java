package Entidades;

public class Proyectil extends Entidad{
	protected int valorAccion;
	
	public Proyectil(int valorAccion) {
		this.valorAccion = valorAccion;
	}
	
	public void mover() {
		x = x+5;
	}
	public void chocar(Zombie z) {
		z.restarVida(valorAccion);
	}
	public int getValorAccion() {
		return valorAccion;
	}
}
