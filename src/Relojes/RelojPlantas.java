package Relojes;

import Logica.Juego;

public class RelojPlantas extends Thread{
	protected boolean activo;
	protected Juego miJuego;
	
	public RelojPlantas(Juego j) {
		activo = true;
		miJuego = j;
	}
	
	
	public void run() {
		try {
			while (activo) {
				Thread.sleep(1000);
				miJuego.accionPlantas();
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
