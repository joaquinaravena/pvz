package Logica;
import Entidades.Zombie;
public class contextoZombie {
	private ZombieStrategy estrategia;
	
	public contextoZombie(ZombieStrategy estrategia) {
		this.estrategia=estrategia;
	}
	
	public void setEstrategia(ZombieStrategy nueva) {
		estrategia=nueva;
	}
	
	public void realizarEstrategia(Zombie z){
		
	}
	

}
