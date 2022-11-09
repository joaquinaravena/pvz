package Entidades;

import javax.swing.ImageIcon;

import GUI.EntidadGrafica;
import GUI.Ventana;
import Logica.Fila;

public class Proyectil extends Lanzable{
	protected int valorAccion;
	
	public Proyectil(int valorAccion,Ventana v,String graf,Fila f) {
		this.valorAccion = valorAccion;
		entidadGrafica=new EntidadGrafica(v,this,graf);
		entidadGrafica.getGrafica().setIcon(new ImageIcon(new ImageIcon("/Recursos/pea.png").getImage().getScaledInstance(getAncho(), getAlto(), 0)));
		entidadGrafica.getGrafica().setBounds(v.getBordeDerecho(), v.getLinea(getNumeroFila()), entidadGrafica.getGrafica().getIcon().getIconWidth(),entidadGrafica.getGrafica().getIcon().getIconHeight());
		miFila=f;
	}
	
	
	public void mover() {
		System.out.println("Movio");
		x = x+5;
		getEntidadGrafica().getGrafica().setBounds(getEntidadGrafica().getGrafica().getX()+5, getEntidadGrafica().getGrafica().getY(), getEntidadGrafica().getGrafica().getWidth(), getEntidadGrafica().getGrafica().getHeight());
		getEntidadGrafica().actualizarGrafica();
	}
	public void chocar(Zombie z) {
		z.restarVida(valorAccion);
	}
	public int getValorAccion() {
		return valorAccion;
	}
	
	public Proyectil clone(Ventana v) {
		Proyectil aRetornar=new Proyectil(this.valorAccion,v,this.entidadGrafica.getRutaGrafica(),miFila);
		return aRetornar;
	}
}
