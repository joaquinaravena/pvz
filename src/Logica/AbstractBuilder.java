package Logica;

import Entidades.Planta;
import Entidades.Zombie;

public interface AbstractBuilder {
	public Zombie crearZombieDebil();
	public Zombie crearZombieMediano();
	public Zombie crearZombieFuerte();
	public Planta crearPlantaGeneradora();
	public Planta crearPlantaDebil();
	public Planta crearPlantaFuerte();
	public Planta crearPlantaTanque();

}
