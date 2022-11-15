package Logica;
import Entidades.Zombie;
import Estrategias.accionPlantasDisparadoras;
import Estrategias.accionPlantasGeneradoras;
import Entidades.Planta;
import Entidades.Proyectil;
import Entidades.Sol;
public class BuilderDia implements AbstractBuilder{
	protected Juego miJuego;
	//Ver como hacen el properties
	public BuilderDia(Juego juego) {
		miJuego = juego;
	}
	public Zombie crearZombieDebil() {
		Zombie z = new Zombie(200,50,10,miJuego.getVentana(),"zombieDebil","zombieDebilAtacando");
		z.setX(z.getEntidadGrafica().getGrafica().getX());
		return z;
	}
	public Zombie crearZombieMediano() {
		Zombie z = new Zombie(300,75,10,miJuego.getVentana(),"zombieMedio","zombieMedioAtacando");
		z.setX(z.getEntidadGrafica().getGrafica().getX());
		return z;
	}

	public Zombie crearZombieFuerte() {
		Zombie z = new Zombie(400,100,10,miJuego.getVentana(),"zombieFuerte","zombieFuerteAtacando");
		z.setX(z.getEntidadGrafica().getGrafica().getX());
		return z;
	}

	public Planta crearPlantaDebil() {
		return new Planta(50,100,0,miJuego.getVentana(), "plantaDebil" , new Sol(miJuego.getVentana(),"sol",true),new accionPlantasGeneradoras());
	}

	public Planta crearPlantaMedio() {
		return new Planta(75,200,100,miJuego.getVentana(), "plantaMedio" , new Proyectil(100,miJuego.getVentana(),"proyectilDebil",null),new accionPlantasDisparadoras());
	}

	public Planta crearPlantaFuerte() {
		return new Planta(150,300,200,miJuego.getVentana(), "plantaFuerte", new Proyectil(100,miJuego.getVentana(),"proyectilFuerte",null),new accionPlantasDisparadoras());
	}
}
