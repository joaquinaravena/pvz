package Logica;

import Entidades.Planta;
import Entidades.Zombie;

public class FabricaDia extends AbstractFactory {
	protected Juego miJuego;
	//atributo properties??
	public FabricaDia(Juego juego) {
		miJuego = juego;
		//set config en dia
	}
	
	
	public Zombie crearZombieDebil() {
		Zombie z = new Zombie(200,34,1,miJuego.getVentana(), "zombieDebil");
		z.setX(z.getEntidadGrafica().getGrafica().getX());
		return z;
	}
	public Zombie crearZombieMediano() {
		return new Zombie(400,70,2,miJuego.getVentana(), "zombieMedio");
	}

	public Zombie crearZombieFuerte() {
		return new Zombie(800,150,3,miJuego.getVentana(), "zombieFuerte");
	}

	public Planta crearPlantaDebil() {
		return new Planta(50,100,50,miJuego.getVentana(), "plantaDebil");
	}

	public Planta crearPlantaMedio() {
		return new Planta(100,200,75,miJuego.getVentana(), "plantaMedio");
	}

	public Planta crearPlantaFuerte() {
		return new Planta(150,600,200,miJuego.getVentana(), "plantaFuerte");
	}

}
