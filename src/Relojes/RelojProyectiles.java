package Relojes;

import java.util.*;

import Entidades.*;
import Logica.Juego;

public class RelojProyectiles extends Thread{
	protected List<Proyectil> misProyectiles;
	protected boolean activo;
	protected boolean resetear;
	protected Juego miJuego;
	
	public RelojProyectiles(Juego j) {
		misProyectiles = new ArrayList<Proyectil>();
		activo = true;
		miJuego = j;
	}
	
	public void resetearListaProyectiles() {
		misProyectiles.clear();
	}
	
	public void run() {
		try {
			while (activo) {
				
				Thread.sleep(300);
				for(int i=1;i<=6;i++) {
					miJuego.getFila(i).moverProyectiles();
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
