package Relojes;

import java.util.*;

import Entidades.*;

public class RelojProyectiles {
	private List<Proyectil> misProyectiles;
	private boolean activo;
	
	public RelojProyectiles() {
		misProyectiles = new ArrayList<Proyectil>();
		activo = true;
	}
	
	public void resetearListaProyectiles() {
		misProyectiles.clear();
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
