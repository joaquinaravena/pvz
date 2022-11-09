package Logica;
import Entidades.*;
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
		Planta auxPlanta;
		Lanzable auxProyectil;
		while (itZombie.hasNext()) {//Itero lista de zombies
			auxZombie=itZombie.next();
			huboColision=false;
			while(itProyectiles.hasNext() && !huboColision) {//Itero lista de proyectiles y si detecto una colision freno.
				auxProyectil=itProyectiles.next();
				huboColision=colisionan(auxZombie,auxProyectil);
				if(huboColision)
					auxZombie.visitarProyectil(auxProyectil);
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
		
	private boolean colisionan(Entidad a,Entidad b) {
		int inicioEntidadA,inicioEntidadB,finalEntidadA,finalEntidadB;
		inicioEntidadA=a.getX();
		finalEntidadA=a.getX()+a.getAncho();
		//Armo el intervalo de la entidad a
		inicioEntidadB=b.getX();
		finalEntidadB=b.getX()+b.getAncho();
		//Armo el intervalo de la etidad b
		boolean colisionan=false;
		if(inicioEntidadA<=inicioEntidadB && inicioEntidadB<=finalEntidadA)
			colisionan=true;
		else if(inicioEntidadA<=finalEntidadB && finalEntidadB<=finalEntidadA)
				colisionan=true;
		
		return colisionan;
	}
	
	public void resetearListaZombies() {
		for (Zombie z: misZombies) {
			z.getEntidadGrafica().borrarGrafica();
			misZombies.remove(z);
		}
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
