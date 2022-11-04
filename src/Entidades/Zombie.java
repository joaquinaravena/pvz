package Entidades;
import GUI.EntidadGrafica;
import Logica.*;

public abstract class Zombie extends Entidad {
	private boolean comiendo;
	private int vida;
	private int daño;
	private int velocidad;
	private ZombieStrategy miEstrategia;
	private Planta plantaAtacada;
	
	
	public Zombie(int vida,int daño,int velocidad) {
		comiendo=false;
		this.vida=vida;
		this.velocidad=velocidad;
		this.daño=daño;
		grafica=new EntidadGrafica();//Cuando este implementado entidad grafica acomodarlo.
		miEstrategia=new moverZombie();
		plantaAtacada=null;
	}
	
	public void realizarAccion(){
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
		p.chocar(this);
	}
	//ARREGLAR ESTO PQ ES INSTANCE OF 
	public void visitarGuisanteNegro(GuisanteNegro g) {
		g.chocar(this);
	}
	
	public void visitarGuisanteNegro(GuisanteVerde g) {
		g.chocar(this);
	}
	
	public void setEstrategia(ZombieStrategy nueva) {
		miEstrategia=nueva;
	}
	
	public void setPlantaAtacada(Planta atacada) {
		plantaAtacada=atacada;
	}
}
