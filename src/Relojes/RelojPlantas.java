package Relojes;

import java.util.*;

import Entidades.*;
import Logica.Juego;

public class RelojPlantas extends Thread{
	protected List<Planta> misPlantas;
	protected boolean activo;
	protected boolean resetear;
	protected Juego miJuego;
	
	public RelojPlantas(Juego j) {
		misPlantas = new ArrayList<Planta>();
		activo = true;
		miJuego = j;
	}
	
	public void resetearListaPlantas() {
		misPlantas.clear();
	}
	
	public void removerPlanta(Planta p) {
		misPlantas.remove(p);
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
				if (resetear==true)
					resetearListaPlantas();
				else {
					miJuego.removerPlantas();
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
