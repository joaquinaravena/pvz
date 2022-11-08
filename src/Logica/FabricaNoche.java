package Logica;

import Entidades.Planta;
import Entidades.Zombie;

public class FabricaNoche extends AbstractFactory {
	protected Juego miJuego;
	
	public FabricaNoche(Juego juego) {
		miJuego = juego;
	}
	public Zombie crearZombieDebil() {
		return new Zombie(200,34,1,miJuego.getVentana(), "zombieDebil");
	}
	public Zombie crearZombieMediano() {
		return new Zombie(400,70,2,miJuego.getVentana(), "zombieMedio");
	}

	public Zombie crearZombieFuerte() {
		return new Zombie(800,150,3,miJuego.getVentana(), "zombieFuerte");
	}

	public Planta crearPlantaDebil() {
		return new Planta(25,100,50,miJuego.getVentana(), "plantaDebil");
	}

	public Planta crearPlantaMedio() {
		return new Planta(75,200,75,miJuego.getVentana(), "plantaMedio");
	}

	public Planta crearPlantaFuerte() {
		return new Planta(1000,600,200,miJuego.getVentana(), "planaFuerte");
	}
}
