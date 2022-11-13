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

		niveles[0]=new Nivel(4,0,6);
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
			juego.getVentana().cambiarNivel(nivelActual);
			juego.miRelojPlantas.setearActivo(true);
			juego.miRelojZombies.setearActivo(true);
			juego.miRelojProyectiles.setearActivo(true);
			
			nuevoNivel(nivelActual);
		}
	}
	
	public void oleada() {
		oleadaActual++;
		juego.getVentana().cambiarOleada(oleadaActual);
		int i = 1;
		int aleatorio = 0;
		while (i<=3 && !zombiesNivel.isEmpty()) {
			int filaRandom = (int)(Math.random()*2+1+aleatorio);
			zombiesNivel.get(0).setFila(juego.getFila(filaRandom+1));
			juego.getFila(filaRandom).agregarZombie(zombiesNivel.get(0), filaRandom);
			zombiesNivel.remove(0);
			i++;
			aleatorio = aleatorio + 2;
		}
	}
}
