package Logica;
import Entidades.*;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.*;
public class Fila {
	private List<Zombie> misZombies;
	private Planta[] misPlantas;
	private List<Lanzable> misProyectiles;
	protected Juego miJuego;
	
	public Fila(Juego j) {
		miJuego = j;
		misZombies=new ArrayList<Zombie>();
		misPlantas=new Planta[9];
		misProyectiles=new ArrayList<Lanzable>();
	}
	
	public Juego getJuego() {
		return miJuego;
	}
	
	public void agregarPlanta(Planta p,int pos) {
		misPlantas[pos]=p;
		misPlantas[pos].setFila(this);
	}
	
	public void removerPlanta(Planta p,int pos) {
		misPlantas[pos]=null;
	}
	
	public void agregarZombie(Zombie z, int fila) {
		z.setNumeroFila(fila);
		z.setFila(this);
		misZombies.add(z);
	}
	
	public void removerZombie(Zombie z) {
		misZombies.remove(z);
	}
	
	public void agregarProyectiles(Lanzable p) {
		misProyectiles.add(p);
		System.out.println("Proyectiles = "+misProyectiles.size());
	}
	
	public void removerProyectil(Lanzable p) {
		misProyectiles.remove(p);
	}
	
	public boolean puedoPonerPlanta(int pos) {
		return misPlantas[pos-1] == null;
	}
	
	public void chequearColisiones() {
		boolean huboColision=false;
		Iterator<Lanzable> itProyectiles=misProyectiles.iterator();
		Iterator<Zombie> itZombie=misZombies.iterator();
		
		Zombie auxZombie;
		
		Lanzable auxProyectil;
		while (itZombie.hasNext()) {//Itero lista de zombies
			auxZombie=itZombie.next();
			huboColision=false;
			while(itProyectiles.hasNext() && !huboColision) {//Itero lista de proyectiles y si detecto una colision freno.
				auxProyectil=itProyectiles.next();
				huboColision=verColisiones(auxZombie,auxProyectil);
				if(huboColision) {
					auxZombie.visitarProyectil(auxProyectil);
				}
					
			}
			huboColision=false;
			/**
			while(itPlanta.hasNext() && !huboColision) {//Itero lista de plantas y si detecto una colision freno.
				auxPlanta=itPlanta.next();
				huboColision=colisionan(auxZombie,auxPlanta);
				if(huboColision)
					auxZombie.visitarPlanta(auxPlanta,miJuego.getVentana());
			}
			**/
		}
	}
		
	private Rectangle armarHitboxEntidad(Entidad e) {
		Dimension dimensionEntidad=new Dimension(e.getEntidadGrafica().getGrafica().getIcon().getIconWidth(),e.getEntidadGrafica().getGrafica().getIcon().getIconHeight());
		Point ubicacionEntidad=new Point(e.getEntidadGrafica().getGrafica().getX(),e.getEntidadGrafica().getGrafica().getY());
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
			p.getEntidadGrafica().borrarGrafica();
			misPlantas[cont]=null;
			cont++;
		}
	}
	
	public void resetearListaProyectiles() {
		for (Lanzable p: misProyectiles) {
			p.getEntidadGrafica().borrarGrafica();
			misProyectiles.remove(p);
		}
	}
	
	public void moverZombies() {
		for(Zombie z : misZombies) 
			z.realizarAccion();
	}
	
	public void accionPlantas() {
		for(int i=0;i<misPlantas.length;i++) {
			if(misPlantas[i]!=null)
				misPlantas[i].realizarAccion(miJuego.getVentana());
		}
	}
	
	public void moverProyectiles() {
		for(Lanzable p: misProyectiles)
			p.mover();
	}
	
	public boolean hayZombies() {
		return misZombies.isEmpty();
	}
	
}
