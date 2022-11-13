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
		Zombie z = new Zombie(200,50,10,miJuego.getVentana(), "zombieDebil");
		z.setX(z.getEntidadGrafica().getGrafica().getX());
		return z;
	}
	public Zombie crearZombieMediano() {
		Zombie z = new Zombie(300,75,10,miJuego.getVentana(), "zombieMedio");
		z.setX(z.getEntidadGrafica().getGrafica().getX());
		return z;
	}

	public Zombie crearZombieFuerte() {
		Zombie z = new Zombie(400,100,15,miJuego.getVentana(), "zombieFuerte");
		z.setX(z.getEntidadGrafica().getGrafica().getX());
		return z;
	}

	public Planta crearPlantaDebil() {
		return new Planta(50,100,0,miJuego.getVentana(), "plantaDebil" , new Sol(miJuego.getVentana(),"sol",true));
	}

	public Planta crearPlantaMedio() {
		return new Planta(100,200,100,miJuego.getVentana(), "plantaMedio" , new Proyectil(100,miJuego.getVentana(),"proyectilDebil",null));
	}

	public Planta crearPlantaFuerte() {
		return new Planta(150,300,200,miJuego.getVentana(), "plantaFuerte", new Proyectil(100,miJuego.getVentana(),"proyectilRalentizante",null));
	}
}
