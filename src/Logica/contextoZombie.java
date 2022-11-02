package Logica;
import Entidades.Zombie;
public class contextoZombie {
	private ZombieStrategy estrategia;
	
	public contextoZombie() {
		this.estrategia=new moverZombie();
	}
	
	public void setEstrategia(ZombieStrategy nueva) {
		estrategia=nueva;
	}
	
	public void realizarEstrategia(Zombie z){
		
	}
	

}
