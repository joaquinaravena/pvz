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
	protected AdministradorNiveles administradorNiveles;
	protected AdministradorJuego administradorJuego;
	protected Fila[] filas;
	protected AbstractBuilder builder;
	protected int contadorZombies;
	protected List<Sol> solesJuego;
	
	public Juego(Ventana v) {
		miRelojMusica = new RelojMusica();
		soles = 150;
		plantaEnEspera = null;
		miVentana = v;
		administradorNiveles = new AdministradorNiveles(this);
		administradorJuego = new AdministradorJuego(this);
		filas = new Fila[6];
		for(int i=0;i<6;i++) {
			filas[i]=new Fila(this);
		}
		contadorZombies = 0;
		solesJuego=new ArrayList<Sol>();
	}
	
	public void setBuilderDia() {
		builder=new BuilderDia(this);
	}
	
	public void setBuilderNoche() {
		builder=new BuilderNoche(this);
	}
	
	public void jugar(){
		miRelojZombies = new RelojZombies(this);
		miRelojPlantas = new RelojPlantas(this);
		miRelojProyectiles = new RelojLanzables(this);
		miRelojZombies.start();
		miRelojPlantas.start();
		miRelojProyectiles.start();
		administradorNiveles.nuevoNivel(0);
	}
		
	public void terminarJuego(boolean gane) {
		if(gane)
			miVentana.ganarJuego();
		else
			miVentana.gameOver();
		
			miRelojPlantas.setearActivo(false);
			miRelojZombies.setearActivo(false);
			miRelojProyectiles.setearActivo(false);
				
		for(int i = 1; i <=6; i++) {
			getFila(i).resetearListaPlantas();
			getFila(i).resetearListaLanzables();
			getFila(i).resetearListaZombies();
		}
		soles = 150;
		miVentana.controlarPlantasAComprar();
		plantaEnEspera = null;
		filas = new Fila[6];
		for(int i=0;i<6;i++) {
			filas[i]=new Fila(this);
		}
		administradorJuego.resetearListas();
		contadorZombies = 0;
		administradorNiveles.oleadaActual = 0;
		administradorNiveles.nivelActual = 0;
	}
	
	@SuppressWarnings("deprecation")
	public void salir() {
		miRelojPlantas.stop();
		miRelojZombies.stop();
		miRelojMusica.stop();
		miRelojProyectiles.stop();
		System.exit(0);
	}
	
	//METODOS PARA REALIZAR ACCION DE ENTIDADES
	
	public void accionPlantas() {
		administradorJuego.removerPlantas();
		for (int i=0; i<6; i++)
				filas[i].accionPlantas();
	}
	
	public void accionLanzables() {
		for (int i=0; i<6; i++)
				filas[i].moverLanzables();
		for(Sol p:solesJuego) {
			p.mover();
		}
	}
	
	public void moverZombies() {
		for (int i=0; i<6; i++) {
			filas[i].moverZombies();
			filas[i].chequearColisiones();
		}		
	}

	
	//METODOS PARA AGREGAR PLANTAS , ZOMBIES y SOLES
	
	public void agregarZombieActivo() { 
		boolean hayZombies = false;
		for (int i=1; i<=6 && !hayZombies; i++) 
			hayZombies = filas[i-1].hayZombies();
		if (!hayZombies && administradorNiveles.getZombiesNivel().isEmpty())
			administradorNiveles.cambiarNivel();
		if (!administradorNiveles.getZombiesNivel().isEmpty()) {
			if (contadorZombies % 6 == 0 && contadorZombies>0) {
				administradorNiveles.oleada();
				contadorZombies++;
			}
			else {
				int filaRandom = (int)(Math.random()*6+1);
				administradorNiveles.getZombiesNivel().get(0).setFila(getFila(filaRandom));
				filas[filaRandom-1].agregarZombie(administradorNiveles.getZombiesNivel().get(0), filaRandom);
				administradorNiveles.getZombiesNivel().remove(0);
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
			this.restarSoles(plantaEnEspera.getPrecio());
		}
		
	}
	
	public void agregarSolAJuego() {
		Sol aAgregar=new Sol(miVentana,"sol",false);
		int randomX=(int)(Math.random()*600);
		aAgregar.getEntidadGrafica().getGrafica().setLocation(randomX, 0);
		aAgregar.setX(randomX);
		aAgregar.setY(0);
		solesJuego.add(aAgregar);
	}
	
	public void agregarSolARemover(Sol s) {
		administradorJuego.agregarLanzableAEliminar(s);
		agregarSoles(50);
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
	
	public List<Sol> getSolesJuego(){
		return solesJuego;
	}
	
	public AbstractBuilder getBuilder() {
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
	public AdministradorJuego getAdministradorJuego() {
		return administradorJuego;
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
