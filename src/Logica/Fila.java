package Logica;
import Entidades.*;
import java.util.*;
public class Fila {
	private List<Zombie> misZombies;
	private List<Planta> misPlantas;
	private List<Proyectil> misProyectiles;
	protected Juego miJuego;
	
	public Fila(Juego j) {
		miJuego = j;
		misZombies=new ArrayList<Zombie>();
		misPlantas=new ArrayList<Planta>();
		misProyectiles=new ArrayList<Proyectil>();
	}
	
	public Juego getJuego() {
		return miJuego;
	}
	
	public void agregarPlanta(Planta p) {
		misPlantas.add(p);
	}
	
	public void removerPlanta(Planta p) {
		misPlantas.remove(p);
	}
	
	public void agregarZombie(Zombie z, int fila) {
		z.setNumeroFila(fila);
		misZombies.add(z);
	}
	
	public void removerZombie(Zombie z) {
		misZombies.remove(z);
	}
	
	public void agregarProyectiles(Proyectil p) {
		misProyectiles.add(p);
	}
	
	public void removerProyectil(Proyectil p) {
		misProyectiles.remove(p);
	}
	
	public boolean puedoPonerPlanta(int x,int y) {
		//Preguntar a joaco como hacerlo.
		return true;
	}
	
	public void chequearColisiones() {
		boolean huboColision=false;
		Iterator<Proyectil> itProyectiles=misProyectiles.iterator();
		Iterator<Zombie> itZombie=misZombies.iterator();
		Iterator<Planta> itPlanta=misPlantas.iterator();
		Zombie auxZombie;
		Planta auxPlanta;
		Proyectil auxProyectil;
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
				while(itPlanta.hasNext() && !huboColision) {//Itero lista de plantas y si detecto una colision freno.
					auxPlanta=itPlanta.next();
					huboColision=colisionan(auxZombie,auxPlanta);
					if(huboColision)
						auxZombie.visitarPlanta(auxPlanta);
				}
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
	
	public void resetearListas() {
		
	}
	
}
