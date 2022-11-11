package Logica;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

import Entidades.*;
import GUI.Ventana;
import Relojes.*;
public class Juego {
	protected RelojMusica miRelojMusica;
	protected RelojPlantas miRelojPlantas;
	protected RelojZombies miRelojZombies;
	protected RelojLanzables miRelojProyectiles;
	protected int soles;
	protected Planta plantaEnEspera;
	protected Ventana miVentana;
	protected int nivelActual;
	protected AdministradorNiveles administrador;
	protected Fila[] filas;
	protected List<Zombie> zombiesNivel;
	protected Builder builder; 
	protected int contadorZombies;
	protected List<Zombie> zombiesAEliminar;
	protected List<Planta> plantasAEliminar;
	protected List<Lanzable> lanzablesAEliminar;
	
	
	public Juego(Ventana v) {
		miRelojMusica = new RelojMusica();
		miRelojZombies = new RelojZombies(this);
		miRelojPlantas = new RelojPlantas(this);
		miRelojProyectiles = new RelojLanzables(this);
		soles = 150;
		plantaEnEspera = null;
		miVentana = v;
		nivelActual = 0;
		administrador = new AdministradorNiveles(this);
		filas = new Fila[6];
		for(int i=0;i<6;i++) {
			filas[i]=new Fila(this);
		}
		zombiesNivel = new ArrayList<Zombie>();
		contadorZombies = 0;
		zombiesAEliminar = new ArrayList<Zombie>();
		plantasAEliminar = new ArrayList<Planta>();
		lanzablesAEliminar = new ArrayList<Lanzable>();
		builder=new Builder(this);
		
	}
	
	public void jugar(){
		if (miRelojZombies.isAlive())
			miRelojZombies.notify();
		else
			miRelojZombies.start();
		miRelojPlantas.start();
		miRelojProyectiles.start();
		//Cambiar el administrador despues
		administrador.nuevoNivel(0);
	}
		
	public void terminarJuego(boolean gane) {
		if(gane)
			miVentana.ganarJuego();
		else
			miVentana.gameOver();
		try {
			miRelojPlantas.wait();
			miRelojZombies.wait();
			miRelojProyectiles.wait();
		}
		catch(Exception e) {
			
		}
		
		for(int i = 1; i <=6; i++) {
			getFila(i).resetearListaPlantas();
			getFila(i).resetearListaLanzables();
			getFila(i).resetearListaZombies();
		}
		soles = 150;
		plantaEnEspera = null;
		nivelActual = 0;
		filas = new Fila[6];
		for(int i=0;i<6;i++) {
			filas[i]=new Fila(this);
		}
		zombiesNivel = new ArrayList<Zombie>();
		zombiesAEliminar = new ArrayList<Zombie>();
		plantasAEliminar = new ArrayList<Planta>();
		lanzablesAEliminar = new ArrayList<Lanzable>();
		contadorZombies = 0;
		
	}
	
	@SuppressWarnings("deprecation")
	public void salir() {
		miRelojPlantas.stop();
		miRelojZombies.stop();
		miRelojMusica.stop();
		miRelojProyectiles.stop();
		System.exit(0);
	}
	
	public void oleada() {
		System.out.println("oleada");
		int i = 1;
		int aleatorio = 0;
		while (i<=3 && !zombiesNivel.isEmpty()) {
			int filaRandom = (int)(Math.random()*2+1+aleatorio);
			zombiesNivel.get(0).setFila(getFila(filaRandom));
			filas[filaRandom-1].agregarZombie(zombiesNivel.get(0), filaRandom);
			zombiesNivel.remove(0);
			i++;
			aleatorio = aleatorio + 2;
		}
	}
	
	public void cambiarNivel() {
		nivelActual++;
		if (nivelActual==2)
			terminarJuego(true);
		else {
			miRelojPlantas.setearActivo(false);
			miRelojZombies.setearActivo(false);
			miRelojProyectiles.setearActivo(false);
			for(int i=0;i<6;i++) {
				filas[i].resetearListaLanzables();
				filas[i].resetearListaPlantas();
			}
			miVentana.cambiarNivel(nivelActual);
			miRelojPlantas.setearActivo(true);
			miRelojZombies.setearActivo(true);
			miRelojProyectiles.setearActivo(true);
			
			administrador.nuevoNivel(nivelActual);
		}
	}
	
	//METODOS PARA REALIZAR ACCION DE ENTIDADES
	
	public void accionPlantas() {
		removerPlantas();
		for (int i=0; i<6; i++)
				filas[i].accionPlantas();
	}
	
	public void accionLanzables() {
		for (int i=0; i<6; i++)
				filas[i].moverLanzables();
	}
	
	public void moverZombies() {
		for (int i=0; i<6; i++) {
			filas[i].moverZombies();
			filas[i].chequearColisiones();
		}		
	}

	
	//METODOS PARA AGREGAR PLANTAS Y ZOMBIES
	
	public void agregarZombieNivel(Zombie z) {
		zombiesNivel.add(z);
	}
	
	public void agregarZombieActivo() { 
		boolean hayZombies = false;
		for (int i=1; i<=6 && !hayZombies; i++) 
			hayZombies = filas[i-1].hayZombies();
		if (!hayZombies && zombiesNivel.isEmpty())
			cambiarNivel();
		if (!zombiesNivel.isEmpty()) {
			if (contadorZombies % 6 == 0 && contadorZombies>0) {
				oleada();
				contadorZombies++;
			}
			else {
				int filaRandom = (int)(Math.random()*6+1);
				zombiesNivel.get(0).setFila(getFila(filaRandom));
				filas[filaRandom-1].agregarZombie(zombiesNivel.get(0), filaRandom);
				zombiesNivel.remove(0);
				contadorZombies++;
				}
			}
		
	}
	
	
	public void agregarPlanta(int x,int y) {
		int posicionArreglo= (x / 74)-2;
		int posicionFila=(y / 65);
		if(filas[posicionFila].puedoPonerPlanta(posicionArreglo)) {
			filas[posicionFila].agregarPlanta(plantaEnEspera, posicionArreglo);
			filas[posicionFila].getPlanta(posicionArreglo).setAncho(plantaEnEspera.getEntidadGrafica().getGrafica().getWidth());
			filas[posicionFila].getPlanta(posicionArreglo).setAlto(plantaEnEspera.getEntidadGrafica().getGrafica().getHeight());
			filas[posicionFila].getPlanta(posicionArreglo).setX(x);
			filas[posicionFila].getPlanta(posicionArreglo).setY(y);
			filas[posicionFila].getPlanta(posicionArreglo).setFila(filas[posicionFila]);
			filas[posicionFila].getPlanta(posicionArreglo).getLanzable().setFila(filas[posicionFila]);
			filas[posicionFila].getPlanta(posicionArreglo).getLanzable().setY(y);
		}
		
	}
	
	//METODOS PARA BORRAR ENTIDADES
	
	public void agregarZombieAEliminar(Zombie z) {
		zombiesAEliminar.add(z);
	}
	
	public void agregarPlantaAEliminar(Planta p) {
		plantasAEliminar.add(p);
	}
	
	public void agregarLanzableAEliminar(Lanzable p) {
		lanzablesAEliminar.add(p);
	}
	
	public void removerZombies() {
		for (Zombie z: zombiesAEliminar) {
			z.getFila().removerZombie(z);
			z.getEntidadGrafica().borrarGrafica();
		}
		zombiesAEliminar.clear();
	}
	
	public void removerPlantas() {
		List<Planta> plantasClone = new CopyOnWriteArrayList<Planta>(plantasAEliminar);
		Iterator<Planta> itPlantas = plantasClone.iterator();
		while(itPlantas.hasNext()) {
			Planta p = itPlantas.next();
			p.getFila().borrarPlanta((p.getX()/74)-2);
			p.getFila().getJuego().agregarLanzableAEliminar(p.getLanzable());
			p.getEntidadGrafica().borrarGrafica();
			for(Zombie z: p.getZombiesAtacan()) {
				z.setPlantaAtacada(null);
				z.setEstrategia(new moverZombie());
			}
		}
		plantasAEliminar.clear();
	}
	
	public void removerLanzables() {
		for (Lanzable p: lanzablesAEliminar) {
			p.getFila().removerLanzable(p);
			p.getEntidadGrafica().borrarGrafica();
		}
		lanzablesAEliminar.clear();
	}
	
	//MUSICA
	
	public void reproducirMusica() {
		miRelojMusica.reproducirMusica();
	}
	
	public void pararMusica() {
		miRelojMusica.pararMusica();		
	}
	
	public boolean reproduciendoMusica() {
		if(miRelojMusica.reproduciendoMusica())
			return true;
		else
			return false;
	}
	
	//getters
	
	public Builder getBuilder() {
		return builder;
	}
	public Ventana getVentana() {
		return miVentana;
	}
	public Planta getPlantaEnEspera() {
		return plantaEnEspera;
	}
	public Fila getFila(int i) {
		return filas[i-1];
	}
	public int getSoles() {
		return soles;
	}
	
	public boolean puedeComprarPlanta() {
		return soles-plantaEnEspera.getPrecio()>=0;
	}
	
	//setters/cambios basicos en atributos
	
	public void setPlantaEnEspera(int i) {
		switch(i) {
			case 0: plantaEnEspera = null;break;
			case 1: plantaEnEspera = builder.crearPlantaDebil();break;
			case 2: plantaEnEspera = builder.crearPlantaMedio();break;
			case 3: plantaEnEspera = builder.crearPlantaFuerte();break;
		}
	}
	
	public void agregarSoles(int s) {
		soles += s;
		miVentana.controlarPlantasAComprar();
	}
	
	public void restarSoles(int s) {
		soles -= s;
		miVentana.controlarPlantasAComprar();
	}
}
