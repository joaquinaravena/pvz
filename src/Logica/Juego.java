package Logica;
import Entidades.*;
import Relojes.*;
public class Juego {
	protected RelojMusica miRelojMusica;
	protected int soles;
	protected Planta plantaEnEspera;
	
	public Juego() {
		miRelojMusica = new RelojMusica();
		soles = 75;
	}
	public void agregarZombie(Zombie z) {}
	
	public void reproducirMusica() {
		miRelojMusica.start();
		miRelojMusica.reproducirMusica();
	}
	
	public void pararMusica() {
		miRelojMusica.pararMusica();
		miRelojMusica.stop();
	}
	
	public int getSoles() {
		return soles;
	}
	
	public void agregarSoles(int s) {
		soles += s;
	}
	
	public void restarSoles(int s) {
		soles -= s;
	}
	
	public void setPlantaEnEspera(Planta p) {
		plantaEnEspera = p;
	}
	
	public Planta getPlantaEnEspera() {
		return plantaEnEspera;
	}
	
}
