package Logica;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

import Entidades.*;
import GUI.Ventana;
import Relojes.*;
public class Juego {
	protected ReproductorMusica miReproductorMusica;
	protected RelojPlantas miRelojPlantas;
	protected RelojZombies miRelojZombies;
	protected RelojLanzables miRelojLanzables;
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
		miReproductorMusica = new ReproductorMusica();
		soles = 150;
		plantaEnEspera = null;
		miVentana = v;
		administradorJuego = new AdministradorJuego(this);
		administradorNiveles = new AdministradorNiveles(this);
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
	
	public void jugar(int nivel){
		this.soles = 150;
		
		miRelojZombies = new RelojZombies(this);
		miRelojPlantas = new RelojPlantas(this);
		miRelojLanzables = new RelojLanzables(this);
		miRelojZombies.start();
		miRelojPlantas.start();
		miRelojLanzables.start();
		
		administradorNiveles.nuevoNivel(nivel);
		miVentana.cambiarNivel(nivel+1);
		
		administradorJuego.resetearListas();
		oleadaActual=0;
		contadorZombies=0;
		plantaEnEspera = null;
	}
		
	public void terminarJuego(boolean gane) {
		
		miRelojZombies.setearActivo(false);
		miRelojPlantas.setearActivo(false);
		miRelojLanzables.setearActivo(false);
		if(gane)
			miVentana.ganarJuego();
		else
			miVentana.gameOver();
		List<Sol>solesClone = new CopyOnWriteArrayList<Sol>(solesJuego);
		Iterator<Sol> itSoles = solesClone.iterator();
		while(itSoles.hasNext()){
			Sol s = itSoles.next();
			s.morir();	
		}
		for(int i = 1; i <=6; i++) {
			getFila(i).resetearListaZombies();
			getFila(i).resetearListaPlantas();
			getFila(i).resetearListaLanzables();
		}
		//saque el admin juego resetear listas porque ya se hace en cada fila

		filas = new Fila[6];
		for(int i=0;i<6;i++) {
			filas[i]=new Fila(this);
		}
		administradorJuego.resetearListas();
		administradorNiveles.setNivelActual(0);
	}
	
	@SuppressWarnings("deprecation")
	public void salir() {
		if(miRelojPlantas != null) {
			miRelojPlantas.stop();
			miRelojZombies.stop();
			miRelojLanzables.stop();
		}
		miReproductorMusica.pararMusica();
		System.exit(0);
	}
	
	public void cambiarVelocidad(int vel) {
		miRelojPlantas.setVelocidad(vel);
		miRelojLanzables.setVelocidad(vel);
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
				if (oleadaActual>3)
					miRelojZombies.setContOleadas(3);
				else
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
		miVentana.actualizarGrafica(aAgregar.getEntidadGrafica());
		solesJuego.add(aAgregar);
	}
	
	
	//MUSICA
	
	public void reproducirMusica() {
		miReproductorMusica.reproducirMusica();
	}
	
	public void pararMusica() {
		miReproductorMusica.pararMusica();		
	}
	
	public boolean reproduciendoMusica() {
		return miReproductorMusica.reproduciendoMusica();
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
