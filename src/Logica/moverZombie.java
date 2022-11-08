package Logica;
import Entidades.Zombie;

public class moverZombie implements ZombieStrategy {
	public moverZombie(){
	}

	public void realizarAccion(Zombie z){
		z.setX(z.getX()-100);
		z.getEntidadGrafica().getGrafica().setBounds(z.getEntidadGrafica().getGrafica().getX()-20, z.getEntidadGrafica().getGrafica().getY(), z.getEntidadGrafica().getGrafica().getWidth(), z.getEntidadGrafica().getGrafica().getHeight());
		z.getEntidadGrafica().actualizarGrafica();
	}
}
