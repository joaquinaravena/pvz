package Relojes;

import Logica.Juego;

public class RelojLanzables extends Thread{
	protected boolean activo;
	protected Juego miJuego;
	
	public RelojLanzables(Juego j) {
		activo = true;
		miJuego = j;
	}
	

	public void run() {
		try {
			while (activo) {
				miJuego.removerLanzables();
				Thread.sleep(100);
				miJuego.accionLanzables();
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
