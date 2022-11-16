package Logica;

import java.util.ArrayList;
import java.util.List;

import Entidades.Zombie;

public class AdministradorNiveles {
	private Juego juego;
	private Nivel[] niveles;
	protected int nivelActual;
	protected int oleadaActual;
	protected List<Zombie> zombiesNivel;
	
	public AdministradorNiveles(Juego juego) {
		//Genero los niveles ya preestablecidos con su cantidad de zombies.
		niveles=new Nivel[2];
		this.juego=juego;
		nivelActual = 0;
		oleadaActual = 0;
		zombiesNivel = new ArrayList<Zombie>();

		niveles[0]=new Nivel(55,0,0);
		niveles[1]=new Nivel(20,15,8);

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
			for(int i=1;i<=6;i++) {
				juego.getFila(i).resetearListaLanzables();
				juego.getFila(i).resetearListaPlantas();
			}
			juego.getVentana().cambiarNivel(nivelActual+1);
			juego.miRelojPlantas.setearActivo(true);
			juego.miRelojZombies.setearActivo(true);
			juego.miRelojProyectiles.setearActivo(true);
			juego.setSoles(150);
			nuevoNivel(nivelActual);
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
		oleadaActual++;
		int cantInsertados = 0;
		juego.getVentana().cambiarOleada(oleadaActual);
		int i = 1;
		int aleatorio = 1;
		while (i<=3 && !zombiesNivel.isEmpty()) {
			insertarZombieEntreFilas(aleatorio, aleatorio+1); //el primer ciclo inserta un zombie en la fila 1 o 2, el segundo ciclo en la fila 3 o 4, y el tercer ciclo en la fila 5 o 6
			aleatorio = aleatorio + 2;
			i++;
			cantInsertados++;
		}
		for (int j=0; j<oleadaActual && !zombiesNivel.isEmpty(); j++) {
			insertarZombieEntreFilas(1, 6);
			cantInsertados++;
		}
		System.out.println(cantInsertados);
	}
	
	public List<Zombie> getZombiesNivel(){
		return zombiesNivel;
	}
	
}
