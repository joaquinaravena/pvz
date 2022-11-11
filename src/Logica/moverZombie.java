package Logica;
import Entidades.Zombie;

public class moverZombie implements ZombieStrategy {
	public moverZombie(){
	}

	public void realizarAccion(Zombie z){
		if (z.getX() <= z.getFila().getJuego().getVentana().getFinTablero()) 
			z.getFila().getJuego().terminarJuego(false);
		else {
			z.setX(z.getX()-z.getVelocidad());
			z.getEntidadGrafica().getGrafica().setBounds(z.getEntidadGrafica().getGrafica().getX()- z.getVelocidad(), z.getEntidadGrafica().getGrafica().getY(), z.getEntidadGrafica().getGrafica().getWidth(), z.getEntidadGrafica().getGrafica().getHeight());
			z.getEntidadGrafica().actualizarGrafica();
		}
	}
}
