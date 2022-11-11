package Entidades;
import GUI.EntidadGrafica;
import GUI.Ventana;
import Logica.*;

public class Zombie extends Entidad {
	protected boolean comiendo;
	protected int vida;
	protected int da�o;
	protected int velocidad;
	protected ZombieStrategy miEstrategia;
	protected Planta plantaAtacada;
	
	
	public Zombie(int vida,int da�o,int velocidad,Ventana v, String graf) {
		comiendo=false;
		this.vida=vida;
		this.velocidad=velocidad;
		this.da�o=da�o;
		entidadGrafica=new EntidadGrafica(v, this, graf);
		miEstrategia=new moverZombie();
		plantaAtacada=null;
	}
	
	public void realizarAccion(){
		miEstrategia.realizarAccion(this);
	}
	
	public Planta getPlantaAtacada() {
		return plantaAtacada;
	}
	
	public int getDa�o() {
		return da�o;
	}
	
	public int getVida() {
		return vida;
	}
	public int getVelocidad() {
		return velocidad;
	}
	public void restarVida(int i) {
		vida-=i;
		if(vida<=0)
			morir();
	}
	
	public void morir(){
		miFila.getJuego().agregarZombieAEliminar(this);
	}
	
	public void visitarPlanta(Planta p) {
		p.chocar(this);
	}
	
	 
	public void visitarProyectil(Lanzable p) {
		p.chocar(this);
	}
	
	public void setEstrategia(ZombieStrategy nueva) {
		miEstrategia=nueva;
	}
	
	public void setPlantaAtacada(Planta atacada) {
		plantaAtacada=atacada;
	}
}
