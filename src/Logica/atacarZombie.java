package Logica;

import Entidades.Zombie;

public class atacarZombie implements ZombieStrategy{
	boolean primeraAccion;
	public atacarZombie() {
		primeraAccion=true;
	}
	public void realizarAccion(Zombie z){
		z.getPlantaAtacada().restarVida(z.getDaño());
	}
}
