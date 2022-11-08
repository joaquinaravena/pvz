package Logica;
import Entidades.Zombie;

public class moverZombie implements ZombieStrategy {
	public moverZombie(){
	}

	public void realizarAccion(Zombie z){
		z.setX(z.getX()+5);
		z.getEntidadGrafica().getGrafica().setLocation(z.getX()+5, z.getY());
	}
}
