package Logica;

import Entidades.Zombie;

public class atacarZombie implements ZombieStrategy{

	
	public atacarZombie() {		
	}
	public void realizarAccion(Zombie z){
			z.getPlantaAtacada().restarVida(z.getDaño());
	}
}
