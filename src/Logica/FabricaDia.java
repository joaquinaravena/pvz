package Logica;

import Entidades.Planta;
import Entidades.Zombie;

public class FabricaDia extends AbstractFactory {
	protected Juego miJuego;
	
	public FabricaDia(Juego juego) {
		miJuego = juego;
	}
	
	
	public Zombie crearZombieDebil() {
		return new Zombie(200,34,1,miJuego.getVentana());
	}
	public Zombie crearZombieMediano() {
		return new Zombie(400,70,2,miJuego.getVentana());
	}

	public Zombie crearZombieFuerte() {
		return new Zombie(800,150,3,miJuego.getVentana());
	}

	public Planta crearPlantaDebil() {
		return new Planta(25,100,50,miJuego.getVentana());
	}

	public Planta crearPlantaMedio() {
		return new Planta(75,200,75,miJuego.getVentana());
	}

	public Planta crearPlantaFuerte() {
		return new Planta(1000,600,200,miJuego.getVentana());
	}

}
