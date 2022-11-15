package Entidades;
import Estrategias.ZombieStrategy;
import Estrategias.atacarZombie;
import Estrategias.moverZombie;
import GUI.EntidadGrafica;
import GUI.Ventana;

public class Zombie extends Entidad {
	protected boolean comiendo;
	protected int vida;
	protected int daño;
	protected int velocidad;
	protected ZombieStrategy estrategiaAtacar,estrategiaMover;
	protected Planta plantaAtacada;
	protected String miRutaAtacando;
	protected String rutaEnUso;
	
	public Zombie(int vida,int daño,int velocidad,Ventana v, String graf,String grafAtaque) {
		comiendo=false;
		this.vida=vida;
		this.velocidad=velocidad;
		this.daño=daño;
		entidadGrafica=new EntidadGrafica(v, this, graf);
		estrategiaAtacar=new atacarZombie();
		estrategiaMover=new moverZombie();
		miRuta=graf;
		miRutaAtacando=grafAtaque;
		rutaEnUso=graf;
		plantaAtacada=null;
	}
	
	public void realizarAccion(){
		if(plantaAtacada==null) {
			if(rutaEnUso==miRutaAtacando) {
				getEntidadGrafica().cambiarGrafica(miRuta, this);
				rutaEnUso=miRuta;
			}
			estrategiaMover.realizarAccion(this);
		}
		else {
			if(rutaEnUso==miRuta) {
				getEntidadGrafica().cambiarGrafica(miRutaAtacando, this);
				rutaEnUso=miRutaAtacando;
			}
			estrategiaAtacar.realizarAccion(this);
		}
			
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
	public int getVelocidad() {
		return velocidad;
	}
	public void restarVida(int i) {
		vida-=i;
		if(vida<=0)
			morir();
	}
	
	public void morir(){
		miFila.getJuego().getAdministradorJuego().agregarZombieAEliminar(this);
		entidadGrafica.borrarGrafica();
	}
	
	public void visitarPlanta(Planta p) {
		p.chocar(this);
	}
	
	 
	public void visitarProyectil(Lanzable p) {
		p.chocar(this);
	}
	
	
	public void setPlantaAtacada(Planta atacada) {
		plantaAtacada=atacada;
	}
	
	public String getMiRutaMover() {
		return miRuta;
	}
	
	public String getMiRutaAtaque() {
		return miRutaAtacando;
	}
}
