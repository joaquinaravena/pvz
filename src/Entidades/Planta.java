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
	protected Lanzable miLanzable;
	protected boolean tieneLanzable;
	protected List<Zombie> zombiesQueMeAtacan;
	//ancho y alto representan las dimensiones.
	//Las plantas se crean sin una posición establecida.
	public Planta(int precio, int vida, int daño, Ventana v, String graf,Lanzable proyectil) {
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
	}

	public void restarVida(int i) {
		if(vida <= 0)
			morir();
		else
			vida -= i;		
	}
	public void morir() {
			miFila.borrarPlanta((this.x/74)-2);
			miLanzable.morir();
			getEntidadGrafica().borrarGrafica();
			for(Zombie z:zombiesQueMeAtacan) {
				z.setPlantaAtacada(null);
				z.setEstrategia(new moverZombie());
			}
		
	}
	

	public void realizarAccion(Ventana v) {
			Lanzable aDisparar=miLanzable.clone(v);
			aDisparar.setX(x);
			aDisparar.setY(y);
			aDisparar.getEntidadGrafica().getGrafica().setLocation(x, y);
			aDisparar.setFila(miFila);
			miFila.agregarLanzable(aDisparar);
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
		Planta p = new Planta(this.precio, this.vida, this.daño ,v, this.entidadGrafica.getRutaGrafica(),miLanzable);
		return p;
	}
	
	public void chocar(Zombie z) {
		if(z.getPlantaAtacada()==null) {
			zombiesQueMeAtacan.add(z);
			z.setPlantaAtacada(this);
		}
	}
}
