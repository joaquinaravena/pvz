package Entidades;

import GUI.EntidadGrafica;
import GUI.Ventana;

public class Proyectil extends Lanzable{
	protected int valorAccion;
	
	public Proyectil(int valorAccion) {
		this.valorAccion = valorAccion;
		//Preguntar joaco como arreglar esto
		//entidadGrafica=new EntidadGrafica(v,this,graf);
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
	
	public Proyectil clone() {
		//Y esto tmb
		Proyectil aRetornar=new Proyectil(this.valorAccion);
		aRetornar.setFila(this.miFila);
		return aRetornar;
	}
}
