package Entidades;
import GUI.EntidadGrafica;
import Logica.*;

public abstract class Zombie {
	private contextoZombie contexto;
	private boolean comiendo;
	private int vida;
	private int daño;
	private int velocidad;
	private EntidadGrafica grafica;
	
	public Zombie(int vida,int daño,int velocidad) {
		comiendo=false;
		this.vida=vida;
		this.velocidad=velocidad;
		this.daño=daño;
		grafica=new EntidadGrafica();//Cuando este implementado entidad grafica acomodarlo.
		contexto=new contextoZombie();
	}
	
	
	
	public void realizarAccion(){
		//Implementar con la estrategia.
	}
	
	public int getDaño() {
		return daño;
	}
	
	public void restarVida(int i) {
		vida-=i;
		if(vida<=0)
			morir();
	}
	
	public void morir() {
		//modificar con el metodo de eliminar grafica y ver como esta implementada la planta
	}
	
	public int getVida() {
		return vida;
	}
	
	public void visitarPlanta(Plata p) {
		p.chocar(this);
	}
	public void visitarProyectil(Proyectil p) {
		p.chocar(this);
	}
	
	
}
