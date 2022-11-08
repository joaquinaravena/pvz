package Logica;
import Entidades.Zombie;

public class moverZombie implements ZombieStrategy {
	public moverZombie(){
	}

	public void realizarAccion(Zombie z){
		if (z.getX() <= 300) 
			z.morir();
		else {
			z.setX(z.getX()-10);
			z.getEntidadGrafica().getGrafica().setBounds(z.getEntidadGrafica().getGrafica().getX()-10, z.getEntidadGrafica().getGrafica().getY(), z.getEntidadGrafica().getGrafica().getWidth(), z.getEntidadGrafica().getGrafica().getHeight());
			z.getEntidadGrafica().actualizarGrafica();
		}
	}
}
