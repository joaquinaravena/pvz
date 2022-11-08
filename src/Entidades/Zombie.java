package Entidades;
import GUI.EntidadGrafica;
import GUI.Ventana;
import Logica.*;

public class Zombie extends Entidad {
	protected boolean comiendo;
	protected int vida;
	protected int daño;
	protected int velocidad;
	protected ZombieStrategy miEstrategia;
	protected Planta plantaAtacada;
	
	
	public Zombie(int vida,int daño,int velocidad,Ventana v) {
		comiendo=false;
		this.vida=vida;
		this.velocidad=velocidad;
		this.daño=daño;
		entidadGrafica=new EntidadGrafica(v);
		miEstrategia=new moverZombie();
		plantaAtacada=null;
	}
	
	public void realizarAccion(){
		System.out.println("Empezo acci0on");
		miEstrategia.realizarAccion(this);
	}
	
	public Planta getPlantaAtacada() {
		return plantaAtacada;
	}
	
	public int getDaño() {
		return daño;
	}
	
	public int getVida() {
		return vida;
	}
	
	public void restarVida(int i) {
		vida-=i;
		if(vida<=0)
			morir();
	}
	
	public void morir() {
		//modificar con el metodo de eliminar grafica y ver como esta implementada la planta
	}
	
	public void visitarPlanta(Planta p) {
		p.realizarAccion();
	}
	
	 
	public void visitarProyectil(Proyectil p) {
		p.chocar(this);
	}
	
	public void setEstrategia(ZombieStrategy nueva) {
		miEstrategia=nueva;
	}
	
	public void setPlantaAtacada(Planta atacada) {
		plantaAtacada=atacada;
	}
}
