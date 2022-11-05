package Relojes;
import java.util.*;

import Entidades.*;
import Logica.*;

public class RelojZombies extends Thread{
	protected List<Zombie> misZombies;
	protected boolean activo;
	protected Juego miJuego;
	protected boolean resetear;
	
	public RelojZombies(Juego j) {
		misZombies = new ArrayList<Zombie>();
		activo = true;
		miJuego = j;
	}
	
	public void resetearListaZombies() {
		misZombies.clear();
	}
	
	public void removerZombie(Zombie z) {
		misZombies.remove(z);
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
				miJuego.agregarZombieActivo();
				if (resetear==true)
					resetearListaZombies();
				else {
					miJuego.removerZombies();
				}
			}
		}
		catch(InterruptedException e) {
			System.out.println("Se interrumpio la accion del reloj");
		}
	}
	
	public void setearActivo(boolean a) {
		activo = a;
	}
		
	public void setearReseteo(boolean a) {
		resetear = a;
	}
}
