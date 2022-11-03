package Logica;
import Entidades.*;
import Relojes.*;
public class Juego {
	RelojMusica miRelojMusica;
	
	public Juego() {
		miRelojMusica = new RelojMusica();
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
	
}
