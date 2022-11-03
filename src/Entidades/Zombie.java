package Entidades;
import GUI.EntidadGrafica;
import Logica.*;

public abstract class Zombie {
	private boolean comiendo;
	private int vida;
	private int da�o;
	private int velocidad;
	private EntidadGrafica grafica;
	private ZombieStrategy miEstrategia;
	
	
	public Zombie(int vida,int da�o,int velocidad) {
		comiendo=false;
		this.vida=vida;
		this.velocidad=velocidad;
		this.da�o=da�o;
		grafica=new EntidadGrafica();//Cuando este implementado entidad grafica acomodarlo.
		miEstrategia=new moverZombie();
	}
	
	public void realizarAccion(){
		miEstrategia.realizarAccion(this);
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
	public void visitarGuisanteNegro(GuisanteNegro g) {
		g.chocar(this);
	}
	
	public void visitarGuisanteNegro(GuisanteVerde g) {
		g.chocar(this);
	}
	
	public void setEstrategia(ZombieStrategy nueva) {
		miEstrategia=nueva;
	}
	
}
