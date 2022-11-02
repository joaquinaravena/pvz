package Logica;
import Entidades.Zombie;
import Entidades.Planta;
public abstract class AbstractFactory {
	public abstract Zombie crearZombieDebil(); 
	public abstract Zombie crearZombieMediano();
	public abstract Zombie crearZombieFuerte();
	public abstract Planta crearPlantaDebil();
	public abstract Planta crearPlantaMedio();
	public abstract Planta crearPlantaFuerte();
}
