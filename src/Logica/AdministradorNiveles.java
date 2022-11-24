package Logica;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import Entidades.Sol;
import Entidades.Zombie;

public class AdministradorNiveles {
	protected Juego juego;
	protected Nivel[] niveles;
	protected int nivelActual;
	protected List<Zombie> zombiesNivel;
	
	public AdministradorNiveles(Juego juego) {
		//Genero los niveles ya preestablecidos con su cantidad de zombies.
		niveles=new Nivel[2];
		this.juego=juego;
		nivelActual = 0;
		zombiesNivel = new ArrayList<Zombie>();
		niveles[0]=new Nivel(30,20,12);
		niveles[1]=new Nivel(25,25,12);
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
		System.out.println(nivelActual);
		if (nivelActual==2) {
			juego.terminarJuego(true);
			System.out.println("Entro");
		}
		else {
			
			List<Sol>solesClone = new CopyOnWriteArrayList<Sol>(juego.getSolesJuego());
			Iterator<Sol> itSoles = solesClone.iterator();
			while(itSoles.hasNext()){
				Sol s = itSoles.next();
				s.morir();	
			}
			for(int i=1;i<=6;i++) {
				juego.getFila(i).resetearListaZombies();
				juego.getFila(i).resetearListaPlantas();
				juego.getFila(i).resetearListaLanzables();
			}
			juego.jugar(nivelActual);
		}
	}
	
	//Inserta un zombie en una fila random entre x e y
	public void insertarZombieEntreFilas(int y, int x) {
		int filaRandom = (int)(Math.random()*(x-y+1)+y);
		zombiesNivel.get(0).setFila(juego.getFila(filaRandom));
		juego.getFila(filaRandom).agregarZombie(zombiesNivel.get(0), filaRandom); // corregido
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
	
	public void setNivelActual(int n) {
		nivelActual = n;
	}
	
}
