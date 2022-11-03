package Relojes;
import java.util.*;

import Entidades.*;
import Logica.*;

public class RelojZombies extends Thread{
	protected List<Zombie> misZombies;
	protected boolean activo;
	protected Juego miJuego;
	
	public RelojZombies(Juego j) {
		misZombies = new ArrayList<Zombie>();
		activo = true;
		miJuego = j;
	}
	
	public void resetearListaZombies() {
		misZombies.clear();
	}
	
	public void agregarZombie(Zombie z) {
		misZombies.add(z);
	}
	
	public void run() {
		try {
			while (activo) {
				Thread.sleep(500);
				for (Zombie z: misZombies) {
					z.realizarAccion();
				}
				miJuego.agregarZombie();
			}
		}
		catch(InterruptedException e) {
			System.out.println("Se interrumpio la accion del reloj");
		}
	}
	
	public void setearActivo(boolean a) {
		activo = a;
	}
		
}
