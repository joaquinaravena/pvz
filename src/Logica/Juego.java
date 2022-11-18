package Logica;
import java.util.*;

import Entidades.*;
import GUI.Ventana;
import Relojes.*;
public class Juego {
	protected RelojMusica miRelojMusica;
	protected RelojPlantas miRelojPlantas;
	protected RelojZombies miRelojZombies;
	protected RelojLanzables miRelojProyectiles;
	protected int soles;
	protected int oleadaActual;
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
		soles = 1500;
		plantaEnEspera = null;
		miVentana = v;
		administradorJuego = new AdministradorJuego(this);
		filas = new Fila[6];
		for(int i=0;i<6;i++) {
			filas[i]=new Fila(this);
		}
		contadorZombies = 0;
		oleadaActual = 0;
		solesJuego=new ArrayList<Sol>();
	}
	
	public void setBuilderDia() {
		builder=new BuilderDia(this);
	}
	
	public void setBuilderNoche() {
		builder=new BuilderNoche(this);
	}
	
	public void jugar(){
		if(miRelojZombies != null) {
			miRelojZombies.stop();
			miRelojPlantas.stop();
			miRelojProyectiles.stop();
		}
		miRelojZombies = new RelojZombies(this);
		miRelojPlantas = new RelojPlantas(this);
		miRelojProyectiles = new RelojLanzables(this);
		miRelojZombies.start();
		miRelojPlantas.start();
		miRelojProyectiles.start();
		administradorNiveles = new AdministradorNiveles(this);
		administradorNiveles.nuevoNivel(0);
	}
		
	public void terminarJuego(boolean gane) {
		miRelojPlantas.setearActivo(false);
		miRelojZombies.setearActivo(false);
		miRelojProyectiles.setearActivo(false);
		
		if(gane)
			miVentana.ganarJuego();
		else
			miVentana.gameOver();
		
		Iterator<Sol> itSoles = solesJuego.iterator();
		while(itSoles.hasNext()){
			Sol s = itSoles.next();
			s.morir();	
		}
		for(int i = 1; i <=6; i++) {
			getFila(i).resetearListaZombies();
			getFila(i).resetearListaPlantas();
			getFila(i).resetearListaLanzables();
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
		oleadaActual = 0;
		administradorNiveles.nivelActual = 0;
	}
	
	@SuppressWarnings("deprecation")
	public void salir() {
		if(miRelojPlantas != null) {
			miRelojPlantas.stop();
			miRelojZombies.stop();
			miRelojProyectiles.stop();
		}
		miRelojMusica.pararMusica();
		System.exit(0);
	}
	
	public void cambiarVelocidad(int vel) {
		miRelojPlantas.setVelocidad(vel);
		miRelojProyectiles.setVelocidad(vel);
		miRelojZombies.setVelocidad(vel);
	}
	
	//METODOS PARA REALIZAR ACCION DE ENTIDADES
	
	public void accionPlantas() {
		administradorJuego.removerPlantas();
		for (int i=0; i<6; i++)
				filas[i].accionPlantas();
	}
	
	public void accionLanzables() {
		administradorJuego.removerLanzables();
		for (int i=0; i<6; i++)
				filas[i].moverLanzables();
		for(Sol p:solesJuego) {
			p.mover();
		}
	}
	
	public void moverZombies() {
		administradorJuego.removerZombies();
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
			if (contadorZombies % 5 == 0 && contadorZombies>0) {
				oleadaActual++;
				getVentana().cambiarOleada(oleadaActual);
				miRelojZombies.setContOleadas(oleadaActual);
				contadorZombies = 0;
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
		filas[posicionFila].agregarPlanta(plantaEnEspera, posicionArreglo);
		filas[posicionFila].getPlanta(posicionArreglo).setAncho(plantaEnEspera.getEntidadGrafica().getGrafica().getWidth());
		filas[posicionFila].getPlanta(posicionArreglo).setAlto(plantaEnEspera.getEntidadGrafica().getGrafica().getHeight());
		filas[posicionFila].getPlanta(posicionArreglo).setX(x);
		filas[posicionFila].getPlanta(posicionArreglo).setY(y);
		filas[posicionFila].getPlanta(posicionArreglo).setFila(filas[posicionFila]);
		if(filas[posicionFila].getPlanta(posicionArreglo).getLanzable()!=null){
			filas[posicionFila].getPlanta(posicionArreglo).getLanzable().setFila(filas[posicionFila]);
			filas[posicionFila].getPlanta(posicionArreglo).getLanzable().setY(y);
		}
		this.restarSoles(plantaEnEspera.getPrecio());
	}
	
	public void agregarSolAJuego() {
		Sol aAgregar=new Sol(miVentana,"moneda",false);
		int randomX=(int)(Math.random()*600);
		aAgregar.getEntidadGrafica().getGrafica().setLocation(randomX, 0);
		aAgregar.setX(randomX);
		aAgregar.setY(0);
		solesJuego.add(aAgregar);
	}
	
	
	//MUSICA
	
	public void reproducirMusica() {
		miRelojMusica.reproducirMusica();
	}
	
	public void pararMusica() {
		miRelojMusica.pararMusica();		
	}
	
	public boolean reproduciendoMusica() {
		return miRelojMusica.reproduciendoMusica();
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
	public AdministradorNiveles getAdministradorNiveles() {
		return administradorNiveles;
	}
	
	public int getSoles() {
		return soles;
	}
	
	//setters/cambios basicos en atributos
	
	public void setPlantaEnEspera(int i) {
		switch(i) {
			case 0: plantaEnEspera = null;break;
			case 1: plantaEnEspera = builder.crearPlantaGeneradora();break;
			case 2: plantaEnEspera = builder.crearPlantaDebil();break;
			case 3: plantaEnEspera = builder.crearPlantaFuerte();break;
			case 4: plantaEnEspera = builder.crearPlantaTanque();break;
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
	public void setSoles(int s) {
		soles = s;
		miVentana.controlarPlantasAComprar();
	}
}
