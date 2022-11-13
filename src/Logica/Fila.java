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
	
	public Juego getJuego() {
		return miJuego;
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
	
	public boolean puedoPonerPlanta(int pos) {
		return misPlantas[pos] == null;
	}
	/*
	public void chequearColisiones() {
		boolean huboColision=false;
		Iterator<Lanzable> itLanzables=misLanzables.iterator();
		Iterator<Zombie> itZombie=misZombies.iterator();
		Zombie auxZombie;
		int cont=0;
		Lanzable auxLanzable;
		List<Lanzable> aRemover;
		while (itZombie.hasNext()) {//Itero lista de zombies
			aRemover=new ArrayList<Lanzable>();
			auxZombie=itZombie.next();
			huboColision=false;
			while(itLanzables.hasNext() && !huboColision) {//Itero lista de lanzables y si detecto una colision freno.
				auxLanzable=itLanzables.next();
				huboColision=verColisiones(auxZombie,auxLanzable);
				if(huboColision) {
					auxZombie.visitarProyectil(auxLanzable);
					aRemover.add(auxLanzable);
					//probar agregar auxLanzable a juego.agregarLanzablesAEliminar() para que se borre la grafica
				}
					
			}
				cont=0;
				huboColision=false;
			while(!huboColision && cont<9) {//Itero lista de plantas y si detecto una colision freno.
				if(misPlantas[cont]!= null) {
						huboColision=verColisiones(auxZombie,misPlantas[cont]);
						if(huboColision) {
							auxZombie.visitarPlanta(misPlantas[cont]);
							auxZombie.setEstrategia(new atacarZombie());
						}
					}
				
				cont++;
			}
		
		}
	}*/  //METODO VIEJO DE JULI SIN BORRAR EL PROYECTIL
		
	public void chequearColisiones() {
		boolean huboColision=false;
		List<Lanzable> lanzablesClone = new CopyOnWriteArrayList<Lanzable>(misLanzables);
		Iterator<Lanzable> itLanzables = lanzablesClone.iterator();
		Iterator<Zombie> itZombie=misZombies.iterator();
		Zombie auxZombie;
		int cont=0;
		Lanzable auxLanzable;
		while (itZombie.hasNext()) {//Itero lista de zombies
			auxZombie=itZombie.next();
			huboColision=false;
			while(itLanzables.hasNext() && !huboColision) {//Itero lista de lanzables y si detecto una colision freno.
				auxLanzable=itLanzables.next();
				huboColision=verColisiones(auxZombie,auxLanzable);
				if(huboColision) {
					auxZombie.visitarProyectil(auxLanzable);
					miJuego.agregarLanzableAEliminar(auxLanzable);
				}
			}
			miJuego.removerLanzables();
			cont=0;
			huboColision=false;
			while(!huboColision && cont<9) {//Itero lista de plantas y si detecto una colision freno.
				if(misPlantas[cont]!= null) {
						huboColision=verColisiones(auxZombie,misPlantas[cont]);
						if(huboColision) {
							auxZombie.visitarPlanta(misPlantas[cont]);
							auxZombie.setEstrategia(new atacarZombie());
						}
					}
				
				cont++;
			}
		
		}
	}
	
	private Rectangle armarHitboxEntidad(Entidad e) {
		Dimension dimensionEntidad=new Dimension(e.getAncho(),e.getAlto());
		Point ubicacionEntidad=new Point(e.getX(),e.getY());
		
		Rectangle hitboxEntidad=new Rectangle(ubicacionEntidad,dimensionEntidad);
		return hitboxEntidad;
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
	
	public void resetearListaPlantas() {
		int cont=0;
		for (Planta p: misPlantas) {
			if (p!=null) {
				p.getEntidadGrafica().borrarGrafica();
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
	}
	
	public void resetearListaZombies() {
		List<Zombie> zombiesClone = new CopyOnWriteArrayList<Zombie>(misZombies);
		Iterator<Zombie> itZombies = zombiesClone.iterator();
		while(itZombies.hasNext()) {
			Zombie z = itZombies.next();
			z.getEntidadGrafica().borrarGrafica();
			misZombies.remove(z);
		}
	}
	
	public void moverZombies() {
		List<Zombie> zombiesClone = new CopyOnWriteArrayList<Zombie>(misZombies);
		Iterator<Zombie> itZombies = zombiesClone.iterator();
		while(itZombies.hasNext()) {
			Zombie z = itZombies.next();
			z.realizarAccion();
		}
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
	
}
