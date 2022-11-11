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
		Zombie z = new Zombie(300,50,10,miJuego.getVentana(), "zombieDebil");
		z.setX(z.getEntidadGrafica().getGrafica().getX());
		return z;
	}
	public Zombie crearZombieMediano() {
		Zombie z = new Zombie(500,75,10,miJuego.getVentana(), "zombieMedio");
		z.setX(z.getEntidadGrafica().getGrafica().getX());
		return z;
	}

	public Zombie crearZombieFuerte() {
		Zombie z = new Zombie(600,100,10,miJuego.getVentana(), "zombieFuerte");
		z.setX(z.getEntidadGrafica().getGrafica().getX());
		return z;
	}

	public Planta crearPlantaDebil() {
		return new Planta(100,100,0,miJuego.getVentana(), "plantaDebil" , new Sol(miJuego.getVentana(),"sol"));
	}

	public Planta crearPlantaMedio() {
		return new Planta(150,200,100,miJuego.getVentana(), "plantaMedio" , new Proyectil(100,miJuego.getVentana(),"guisanteVerde",null));
	}

	public Planta crearPlantaFuerte() {
		return new Planta(250,300,200,miJuego.getVentana(), "plantaFuerte", new Proyectil(100,miJuego.getVentana(),"guisanteHielo",null));
	}
}
