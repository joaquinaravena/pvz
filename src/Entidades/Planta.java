package Entidades;
import java.util.ArrayList;

import java.util.List;

import Estrategias.PlantaStrategy;
import GUI.EntidadGrafica;
import GUI.Ventana;

public class Planta extends Entidad {
	protected int precio;
	protected int vida;
	protected int daño;
	protected Lanzable miLanzable;
	protected boolean tieneLanzable;
	protected PlantaStrategy miEstrategia;
	protected List<Zombie> zombiesQueMeAtacan;
	
	//ancho y alto representan las dimensiones.
	//Las plantas se crean sin una posición establecida.
	public Planta(int precio, int vida, int daño, Ventana v, String graf,Lanzable proyectil,PlantaStrategy estrategia) {
		this.precio = precio;
		this.vida = vida;
		this.daño=daño;
		entidadGrafica = new EntidadGrafica(v, this, graf);
		miLanzable = proyectil;
		zombiesQueMeAtacan=new ArrayList<Zombie>();
		if(miLanzable==null)
			tieneLanzable=false;
		else 
			tieneLanzable=true;
		miEstrategia=estrategia;
	}

	public void restarVida(int i) {
		if(vida <= 0)
			morir();
		else
			vida -= i;		
	}
	public void morir() {
		miFila.getJuego().getAdministradorJuego().agregarPlantaAEliminar(this);
		entidadGrafica.borrarGrafica();
	}
	

	public void realizarAccion(Ventana v) {
		miEstrategia.realizarAccion(this, v);
	}
	
	public int getDaño() {
		return daño;
	}
	public int getPrecio() {
		return precio;
	}
	public Lanzable getLanzable() {
		return miLanzable;
	}
	public Planta clone(Ventana v) {
		Planta p = new Planta(this.precio, this.vida, this.daño ,v, this.entidadGrafica.getRutaGrafica(),miLanzable,miEstrategia);
		return p;
	}
	
	public void chocar(Zombie z) {
		if(z.getPlantaAtacada()==null) {
			zombiesQueMeAtacan.add(z);
			z.setPlantaAtacada(this);
		}
	}
	
	public List<Zombie> getZombiesAtacan(){
		return zombiesQueMeAtacan;
	}
	
	public int getVida() {
		return vida;
	}
}
