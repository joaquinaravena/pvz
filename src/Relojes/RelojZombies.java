package Relojes;
import java.util.*;

import Entidades.*;
import Logica.*;

public class RelojZombies extends Thread{
	protected boolean activo;
	protected Juego miJuego;
	protected boolean resetear;
	
	public RelojZombies(Juego j) {
		activo = true;
		miJuego = j;
	}
	
	public void run() {
		try {
			while (activo) {
				Thread.sleep(500);
				miJuego.moverZombies();
				miJuego.agregarZombieActivo();
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
