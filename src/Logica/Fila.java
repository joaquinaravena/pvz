package Logica;
import Entidades.*;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
public class Fila {
	private List<Zombie> misZombies;
	private Planta[] misPlantas;
	private List<Lanzable> misLanzables;
	protected Juego miJuego;
	
	public Fila(Juego j) {
		miJuego = j;
		misZombies=new ArrayList<Zombie>();
		misPlantas=new Planta[9];
		misLanzables=new ArrayList<Lanzable>();
	}
	public void agregarPlanta(Planta p,int pos) {
		misPlantas[pos]=p;
		misPlantas[pos].setFila(this);
	}
	
	public void removerPlanta(int pos) {
		misPlantas[pos]=null;
	}
	
	public void agregarZombie(Zombie z, int fila) {
		z.setNumeroFila(fila);
		z.setFila(this);
		z.setX(z.getEntidadGrafica().getGrafica().getX());
		z.setY(z.getEntidadGrafica().getGrafica().getY());
		z.setAncho(z.getEntidadGrafica().getGrafica().getWidth());
		z.setAlto(z.getEntidadGrafica().getGrafica().getHeight());
		z.getEntidadGrafica().actualizarGrafica();
		misZombies.add(z);
	}
	
	public void removerZombie(Zombie z) {
		misZombies.remove(z);
	}
	
	public void agregarLanzable(Lanzable p) {
		misLanzables.add(p);
	}
	
	public void removerLanzable(Lanzable p) {
		misLanzables.remove(p);
	}
	/*
	 * Itera la lista de zombies de la fila y chequea si hay lanzables o plantas con las que pueda colisionar usando el m�todo verColisiones
	 */
	public void chequearColisiones() {
		boolean huboColision=false;
		List<Lanzable> lanzablesClone = new CopyOnWriteArrayList<Lanzable>(misLanzables);
		Iterator<Lanzable> itLanzables = lanzablesClone.iterator();
		List<Zombie> lanzablesZombies = new CopyOnWriteArrayList<Zombie>(misZombies);
		Iterator<Zombie> itZombie=lanzablesZombies.iterator();
		Zombie auxZombie;
		int cont=0;
		Lanzable auxLanzable;
		while (itZombie.hasNext()) {//Itero lista de zombies
			auxZombie=itZombie.next();
			while(itLanzables.hasNext()) {//Itero lista de lanzables y si detecto una colision freno.
				auxLanzable=itLanzables.next();
				huboColision=verColisiones(auxZombie,auxLanzable);
				if(huboColision) 
					auxZombie.visitarProyectil(auxLanzable);
			}
			miJuego.getAdministradorJuego().removerLanzables();
			cont=0;
			huboColision=false;
			while(!huboColision && cont<9) {//Itero lista de plantas y si detecto una colision freno.
				if(misPlantas[cont]!= null) {
						huboColision=verColisiones(auxZombie,misPlantas[cont]);
						if(huboColision) {
							auxZombie.visitarPlanta(misPlantas[cont]);
						}
					}
				cont++;
			}
		
		}
	}

	/**
	 * Calcula si hay colision entre dos entidades a y b.
	 * @param a Entidad a
	 * @param b Entidad b
	 * @return true si hubo colision, false caso contrario.
	 */
	private boolean verColisiones(Entidad a,Entidad b) {
		Rectangle hitboxEntidadA=armarHitboxEntidad(a);
		Rectangle hitboxEntidadB=armarHitboxEntidad(b);
		boolean colisiono=false;		
		if(hitboxEntidadA.intersects(hitboxEntidadB))
			colisiono=true;
		return colisiono;
	}
	
	private Rectangle armarHitboxEntidad(Entidad e) {
		Dimension dimensionEntidad=new Dimension(e.getAncho(),e.getAlto());
		Point ubicacionEntidad=new Point(e.getX(),e.getY());
		Rectangle hitboxEntidad=new Rectangle(ubicacionEntidad,dimensionEntidad);
		return hitboxEntidad;
	}
	
	public void resetearListaPlantas() {
		int cont=0;
		for (Planta p: misPlantas) {
			if (p!=null) {
				p.morir();
			}
			misPlantas[cont]=null;
			cont++;
		}
	}
	
	public void resetearListaLanzables() {
		List<Lanzable> lanzablesClone = new CopyOnWriteArrayList<Lanzable>(misLanzables);
		Iterator<Lanzable> itLanzables = lanzablesClone.iterator();
		while(itLanzables.hasNext()) {
			Lanzable p = itLanzables.next();
			p.getEntidadGrafica().borrarGrafica();
			misLanzables.remove(p);
		}
		lanzablesClone.clear();
	}
	
	public void resetearListaZombies() {
		List<Zombie> zombiesClone = new CopyOnWriteArrayList<Zombie>(misZombies);
		Iterator<Zombie> itZombies = zombiesClone.iterator();
		while(itZombies.hasNext()) {
			Zombie z = itZombies.next();
			z.getEntidadGrafica().borrarGrafica();
			misZombies.remove(z);
		}
		zombiesClone.clear();
	}
	
	public void moverZombies() {
		List<Zombie> zombiesClone = new CopyOnWriteArrayList<Zombie>(misZombies);
		Iterator<Zombie> itZombies = zombiesClone.iterator();
		while(itZombies.hasNext()) {
			Zombie z = itZombies.next();
			z.realizarAccion();
		}
		zombiesClone.clear();
	}
	
	public void accionPlantas() {
		for(int i=0;i<misPlantas.length;i++) {
			if(misPlantas[i]!=null)
				misPlantas[i].realizarAccion(miJuego.getVentana());
		}
	}
	
	public void moverLanzables() {
		List<Lanzable> lanzablesClone = new CopyOnWriteArrayList<Lanzable>(misLanzables);
		Iterator<Lanzable> itLanzables = lanzablesClone.iterator();
		while(itLanzables.hasNext()) {
			Lanzable p = itLanzables.next();
			p.mover();
			if(p.getX()>=miJuego.getVentana().getBordeDerecho() || p.getY()>=miJuego.getVentana().getBordeInferior()) {
				p.morir();
			}
		}
		lanzablesClone.clear();
	}
	
	public boolean hayZombies() {
		return !misZombies.isEmpty();
	}
	
	public Planta getPlanta(int i) {
		return misPlantas[i];
	}
	
	public void borrarPlanta(int i) {
		misPlantas[i]=null;
	}
	
	public Juego getJuego() {
		return miJuego;
	}
	
}
