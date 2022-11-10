package Logica;
import Entidades.Zombie;
import Entidades.Planta;
import Entidades.Proyectil;
import Entidades.Sol;
public class Builder{
protected Juego miJuego;
	//Ver como hacen el properties
	public Builder(Juego juego) {
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
		return new Planta(50,100,50,miJuego.getVentana(), "plantaDebil" , new Sol(miJuego.getVentana(),"sol"));
	}

	public Planta crearPlantaMedio() {
		return new Planta(100,200,75,miJuego.getVentana(), "plantaMedio" , new Proyectil(75,miJuego.getVentana(),"guisante",null));
	}

	public Planta crearPlantaFuerte() {
		return new Planta(150,600,200,miJuego.getVentana(), "plantaFuerte",null);
	}
}
