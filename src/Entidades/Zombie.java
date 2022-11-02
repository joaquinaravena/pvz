package Entidades;
import GUI.EntidadGrafica;
import Logica.*;

public abstract class Zombie {
	private contextoZombie contexto;
	private boolean comiendo;
	private int vida;
	private int da�o;
	private int velocidad;
	private EntidadGrafica grafica;
	
	public Zombie(int vida,int da�o,int velocidad) {
		comiendo=false;
		this.vida=vida;
		this.velocidad=velocidad;
		this.da�o=da�o;
		grafica=new EntidadGrafica();//Cuando este implementado entidad grafica acomodarlo.
		contexto=new contextoZombie();
	}
	
	
	
	public void realizarAccion(){
		//Implementar con la estrategia.
	}
	
	public int getDa�o() {
		return da�o;
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
