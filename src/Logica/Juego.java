package Logica;


import Entidades.*;
import GUI.Ventana;
import Relojes.*;
public class Juego {
	protected RelojMusica miRelojMusica;
	protected int soles;
	protected Planta plantaEnEspera;
	protected Ventana miVentana;
	
	public Juego() {
		miRelojMusica = new RelojMusica();
		soles = 75;
	}
	public void agregarZombie(Zombie z) {
		//miVentana.actualizarGrafica(z.getGrafica());
	}
	
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
		miVentana.controlarPlantasAComprar();
	}
	
	public void restarSoles(int s) {
		soles -= s;
		miVentana.controlarPlantasAComprar();
	}
	
	public void setPlantaEnEspera(Planta p) {
		plantaEnEspera = p;
	}
	
	public Planta getPlantaEnEspera() {
		return plantaEnEspera;
	}
	
}
