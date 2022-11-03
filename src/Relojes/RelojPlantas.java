package Relojes;

import java.util.*;

import Entidades.*;

public class RelojPlantas {
	protected List<Planta> misPlantas;
	protected boolean activo;
	
	public RelojPlantas() {
		misPlantas = new ArrayList<Planta>();
		activo = true;
	}
	
	public void resetearListaPlantas() {
		misPlantas.clear();
	}
	
	public void agregarPlanta(Planta p) {
		misPlantas.add(p);
	}
	
	public void run() {
		try {
			while (activo) {
				Thread.sleep(1000);
				for (Planta p: misPlantas) {
					p.realizarAccion();
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
