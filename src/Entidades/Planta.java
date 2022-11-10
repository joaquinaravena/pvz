package Entidades;
import java.util.ArrayList;
import java.util.List;

import GUI.EntidadGrafica;
import GUI.Ventana;
import Logica.moverZombie;

public class Planta extends Entidad {
	protected int precio;
	protected int vida;
	protected int daño;
	protected Lanzable miProyectil;
	protected boolean tieneProyectil;
	protected List<Zombie> zombiesQueMeAtacan;
	//ancho y alto representan las dimensiones.
	//Las plantas se crean sin una posición establecida.
	public Planta(int precio, int vida, int daño, Ventana v, String graf,Lanzable proyectil) {
		this.precio = precio;
		this.vida = vida;
		this.daño=daño;
		entidadGrafica = new EntidadGrafica(v, this, graf);
		miProyectil = proyectil;
		zombiesQueMeAtacan=new ArrayList<Zombie>();
		if(miProyectil==null)
			tieneProyectil=false;
		else 
			tieneProyectil=true;
	}

	public void restarVida(int i) {
		if(vida <= 0)
			morir();
		else
			vida -= i;		
	}
	public void morir() {
			miFila.borrarPlanta((this.x / 74)-2);
			miProyectil.morir();
			miFila.getJuego().agregarPlantaAEliminar(this);
			getEntidadGrafica().borrarGrafica();
			System.out.println(zombiesQueMeAtacan.size());
			for(Zombie z:zombiesQueMeAtacan) {
				z.setPlantaAtacada(null);
				z.setEstrategia(new moverZombie());
			}
		
	}
	

	public void realizarAccion(Ventana v) {
			Lanzable aDisparar=miProyectil.clone(v);
			aDisparar.setX(x);
			aDisparar.setY(y);
			aDisparar.getEntidadGrafica().getGrafica().setLocation(x, y);
			miFila.agregarProyectiles(aDisparar);
	}
	
	public int getDaño() {
		return daño;
	}
	public int getPrecio() {
		return precio;
	}
	public Lanzable getProyectil() {
		return miProyectil;
	}
	public Planta clone(Ventana v) {
		Planta p = new Planta(this.precio, this.vida, this.daño ,v, this.entidadGrafica.getRutaGrafica(),miProyectil);
		return p;
	}
	
	public void chocar(Zombie z) {
		if(z.getPlantaAtacada()==null) {
			zombiesQueMeAtacan.add(z);
			z.setPlantaAtacada(this);
		}
	}
}
