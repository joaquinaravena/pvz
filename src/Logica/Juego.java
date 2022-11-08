package Logica;
import java.util.*;

import Entidades.*;
import GUI.Ventana;
import Relojes.*;
public class Juego {
	protected RelojMusica miRelojMusica;
	protected RelojPlantas miRelojPlantas;
	protected RelojZombies miRelojZombies;
	protected RelojProyectiles miRelojProyectiles;
	protected int soles;
	protected Planta plantaEnEspera;
	protected Ventana miVentana;
	protected int nivelActual;
	protected AdministradorNiveles administrador;
	protected Fila[] filas;
	protected List<Zombie> zombiesNivel;
	protected AbstractFactory fabrica; 
	protected int contadorZombies;
	protected List<Zombie> zombiesAEliminar;
	protected List<Planta> plantasAEliminar;
	protected List<Proyectil> proyectilesAEliminar;
	
	public Juego(Ventana v) {
		miRelojMusica = new RelojMusica();
		miRelojPlantas = new RelojPlantas(this);
		miRelojZombies = new RelojZombies(this);
		miRelojProyectiles = new RelojProyectiles(this);
		soles = 75;
		plantaEnEspera = null;
		miVentana = v;
		nivelActual = 0;
		administrador = new AdministradorNiveles(this);
		filas = new Fila[6];
		for(int i=0;i<6;i++) {
			filas[i]=new Fila();
		}
		zombiesNivel = new ArrayList<Zombie>();
		contadorZombies = 0;
		zombiesAEliminar = new ArrayList<Zombie>();
		plantasAEliminar = new ArrayList<Planta>();
		proyectilesAEliminar = new ArrayList<Proyectil>();
	}
	
	public void jugar(){
		miRelojMusica.start();
		miRelojPlantas.start();
		miRelojZombies.start();
		miRelojProyectiles.start();
		//Cambiar el administrador despues
		administrador.nuevoNivel(0);
	}
	
	public void gameOver() {
		miRelojPlantas.setearActivo(false);
		miRelojZombies.setearActivo(false);
		miRelojProyectiles.setearActivo(false);
		miRelojMusica.stop();
		miRelojPlantas.stop();
		miRelojZombies.stop();
		miRelojProyectiles.stop();
	}
	
	public void agregarZombieNivel(Zombie z) {
		zombiesNivel.add(z);
	}
	
	public void setFabricaDia() {
		fabrica = new FabricaDia(this);
	}
	
	public void setFabricaNoche() {
		fabrica = new FabricaNoche(this);
	}
	
	public AbstractFactory getFactory() {
		return fabrica;
	}
	public Ventana getVentana() {
		return miVentana;
	}
	
	public void agregarZombieActivo() {
		if (zombiesNivel.isEmpty() && nivelActual != 0)
			cambiarNivel();
		if(!zombiesNivel.isEmpty()){
			if (contadorZombies % 3 == 0 && contadorZombies>0)
				Oleada();
			else {
				int filaRandom = (int)(Math.random()*6);
				filas[filaRandom].agregarZombie(zombiesNivel.get(0));
				miRelojZombies.agregarZombie(zombiesNivel.get(0));
				zombiesNivel.remove(0);
				contadorZombies++;
			}
		}
	}
	
	public void Oleada() {
		int i = 0;
		while (i<6 && !zombiesNivel.isEmpty()) {
			filas[i].agregarZombie(zombiesNivel.get(0));
			zombiesNivel.remove(0);
			contadorZombies++;
			i++;
		}
	}
	
	public void cambiarNivel() {
		nivelActual++;
		if (nivelActual==2)
			gameOver();
		else {
			for (int i=0; i<6; i++) 
				filas[i].resetearListas();
				miRelojZombies.setearReseteo(true);
				miRelojPlantas.setearReseteo(true);
				miRelojProyectiles.setearReseteo(true);
				administrador.nuevoNivel(nivelActual);
		}
	}
	
	public void agregarPlanta() {
		//preguntar como hacerlo segun la posicion que clickea el usuario en la ventana de joaco
	}
	
	public void agregarProyectil(Proyectil p) {
		miRelojProyectiles.agregarProyectil(p);
	}
	
	public int getSoles() {
		return soles;
	}
	
	public void agregarSoles(int s) {
		soles += s;
		miVentana.controlarPlantasAComprar();
	}
	
	public void restarSoles(int s) {
		soles -= s;
		miVentana.controlarPlantasAComprar();
	}
	
	public void setPlantaEnEspera(int i) {
		switch(i) {
			case 0: plantaEnEspera = null;
			case 1: plantaEnEspera = fabrica.crearPlantaDebil();
			case 2: plantaEnEspera = fabrica.crearPlantaMedio();
			case 3: plantaEnEspera = fabrica.crearPlantaFuerte();
		}
	}
	
	public Planta getPlantaEnEspera() {
		return plantaEnEspera;
	}
	
	public Fila getFila(int i) {
		return filas[i];
	}
	
	public boolean puedeComprarPlanta() {
		return soles-plantaEnEspera.getPrecio()>=0;
	}
	
	public void reproducirMusica() {
		miRelojMusica.start();
		miRelojMusica.reproducirMusica();
	}
	
	public void pararMusica() {
		miRelojMusica.pararMusica();
		miRelojMusica.stop();
	}
	
	public void agregarZombieAEliminar(Zombie z) {
		zombiesAEliminar.add(z);
	}
	
	public void agregarPlantaAEliminar(Planta p) {
		plantasAEliminar.add(p);
	}
	
	public void agregarProyectilAEliminar(Proyectil p) {
		proyectilesAEliminar.add(p);
	}
	
	public void removerZombies() {
		for (Zombie z: zombiesAEliminar) {
			miRelojZombies.removerZombie(z);
		}
	}
	
	public void removerPlantas() {
		for (Planta p: plantasAEliminar) {
			miRelojPlantas.removerPlanta(p);
		}
	}
	
	public void removerProyectiles() {
		for (Proyectil p: proyectilesAEliminar) {
			miRelojProyectiles.removerProyectil(p);
		}
	}
}
