package Logica;

import Entidades.Planta;
import Entidades.Zombie;

public class FabricaNoche extends AbstractFactory {
	protected Juego miJuego;
	
	public FabricaNoche(Juego juego) {
		miJuego = juego;
	}
	public Zombie crearZombieDebil() {
		Zombie z = new Zombie(200,34,1,miJuego.getVentana(), "zombieDebil");
		z.setX(z.getEntidadGrafica().getGrafica().getX());
		return z;
	}
	public Zombie crearZombieMediano() {
		Zombie z = new Zombie(400,70,2,miJuego.getVentana(), "zombieMedio");
		z.setX(z.getEntidadGrafica().getGrafica().getX());
		return z;
	}

	public Zombie crearZombieFuerte() {
		Zombie z = new Zombie(800,150,3,miJuego.getVentana(), "zombieFuerte");
		z.setX(z.getEntidadGrafica().getGrafica().getX());
		return z;
	}

	public Planta crearPlantaDebil() {
		return new Planta(50,100,50,miJuego.getVentana(), "plantaDebil");
	}

	public Planta crearPlantaMedio() {
		return new Planta(100,200,75,miJuego.getVentana(), "plantaMedio");
	}

	public Planta crearPlantaFuerte() {
		return new Planta(150,600,200,miJuego.getVentana(), "planaFuerte");
	}
}
