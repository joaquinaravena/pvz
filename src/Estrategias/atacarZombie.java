package Estrategias;

import Entidades.Zombie;

public class atacarZombie implements ZombieStrategy{
	public atacarZombie() {
	}
	public void realizarAccion(Zombie z){
		z.getPlantaAtacada().restarVida(z.getDa�o());
		if(z.getPlantaAtacada()==null)
			z.getEntidadGrafica().cambiarGrafica(z.getMiRutaMover(), z);
	}
}
