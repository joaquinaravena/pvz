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
	
	public void removerProyectil(Proyectil p) {
		misProyectiles.remove(p);
	}
	
	public void agregarProyectil(Proyectil p) {
		misProyectiles.add(p);
	}
	
	public void run() {
		try {
			while (activo) {
				Thread.sleep(200);
				for (Proyectil p: misProyectiles) {
					p.mover();
				}
				if (resetear==true)
					resetearListaProyectiles();
				else {
					miJuego.removerProyectiles();
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
