package Logica;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Entidades.Sol;
import Entidades.Zombie;

public class AdministradorNiveles {
	private Juego juego;
	private Nivel[] niveles;
	protected int nivelActual;
	protected List<Zombie> zombiesNivel;
	
	public AdministradorNiveles(Juego juego) {
		//Genero los niveles ya preestablecidos con su cantidad de zombies.
		niveles=new Nivel[2];
		this.juego=juego;
		nivelActual = 0;
		zombiesNivel = new ArrayList<Zombie>();
		niveles[0]=new Nivel(20,30,10);
		niveles[1]=new Nivel(30,25,15);
	}
	
	public void nuevoNivel(int nivelElegido) {
		Nivel elegido=niveles[nivelElegido];
		for(int i=0;i<elegido.getCantidadZombiesDebiles();i++) {
			zombiesNivel.add(juego.getBuilder().crearZombieDebil());
		}
		for(int i=0;i<elegido.getCantidadZombiesMedianos();i++) {
			zombiesNivel.add(juego.getBuilder().crearZombieMediano());
		}
		for(int i=0;i<elegido.getCantidadZombiesFuertes();i++) {
			zombiesNivel.add(juego.getBuilder().crearZombieFuerte());
		}

	}
	
	public void cambiarNivel() {
		nivelActual++;
		if (nivelActual==2)
			juego.terminarJuego(true);
		else {
			juego.miRelojPlantas.setearActivo(false);
			juego.miRelojZombies.setearActivo(false);
			juego.miRelojProyectiles.setearActivo(false);
			
			Iterator<Sol> itSoles = juego.getSolesJuego().iterator();
			while(itSoles.hasNext()){
				Sol s = itSoles.next();
				s.morir();	
			}
			for(int i=1;i<=6;i++) {
				juego.getFila(i).resetearListaZombies();
				juego.getFila(i).resetearListaPlantas();
				juego.getFila(i).resetearListaLanzables();
			}
			juego.getAdministradorJuego().resetearListas();
			juego.getVentana().cambiarNivel(nivelActual+1);
			juego.miRelojPlantas.setearActivo(true);
			juego.miRelojZombies.setearActivo(true);
			juego.miRelojProyectiles.setearActivo(true);
			juego.setSoles(1500);
			nuevoNivel(nivelActual);
			juego.getVentana().controlarPlantasAComprar();
			juego.oleadaActual=0;
			juego.contadorZombies=0;
			juego.setPlantaEnEspera(0);
		}
	}
	
	//Inserta un zombie en una fila random entre x e y
	
	public void insertarZombieEntreFilas(int y, int x) {
		int filaRandom = (int)(Math.random()*(x-y+1)+y);
		zombiesNivel.get(0).setFila(juego.getFila(filaRandom));
		juego.getFila(filaRandom).agregarZombie(zombiesNivel.get(0), filaRandom);
		zombiesNivel.remove(0);
	}
	
	public void oleada() {
		int aleatorio = 1;
		for(int i = 1; i <= 3 && !zombiesNivel.isEmpty(); i++) {
			insertarZombieEntreFilas(aleatorio, aleatorio+1); //el primer ciclo inserta un zombie en la fila 1 o 2, el segundo ciclo en la fila 3 o 4, y el tercer ciclo en la fila 5 o 6
			aleatorio = aleatorio + 2;
		}
	}
	
	public List<Zombie> getZombiesNivel(){
		return zombiesNivel;
	}
	
}
