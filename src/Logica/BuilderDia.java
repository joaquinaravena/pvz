package Logica;
import Entidades.Zombie;
import Estrategias.accionPlantasDisparadoras;
import Estrategias.accionPlantasGeneradoras;
import Estrategias.accionPlantasTanque;
import Entidades.Planta;
import Entidades.Proyectil;
import Entidades.Sol;
public class BuilderDia implements AbstractBuilder{
	protected Juego miJuego;

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

	public Planta crearPlantaGeneradora() {
		return new Planta(Integer.parseInt(miJuego.getVentana().getPropertiesModo().getProperty("precioGeneradora")),100,0,miJuego.getVentana(), "plantaGeneradora" , new Sol(miJuego.getVentana(),"moneda",true),new accionPlantasGeneradoras());
	}

	public Planta crearPlantaDebil() {
		return new Planta(Integer.parseInt(miJuego.getVentana().getPropertiesModo().getProperty("precioDebil")),200,100,miJuego.getVentana(), "plantaDebil" , new Proyectil(100,miJuego.getVentana(),"proyectilDebil",null),new accionPlantasDisparadoras());
	}

	public Planta crearPlantaFuerte() {
		return new Planta(Integer.parseInt(miJuego.getVentana().getPropertiesModo().getProperty("precioFuerte")),300,200,miJuego.getVentana(), "plantaFuerte", new Proyectil(100,miJuego.getVentana(),"proyectilFuerte",null),new accionPlantasDisparadoras());
	}
	public Planta crearPlantaTanque() {
		return new Planta(Integer.parseInt(miJuego.getVentana().getPropertiesModo().getProperty("precioTanque")),500,0,miJuego.getVentana(), "plantaTanqueFullVida", null , new accionPlantasTanque("plantaTanqueMediaVida","plantaTanqueMuriendo"));
	}
}
