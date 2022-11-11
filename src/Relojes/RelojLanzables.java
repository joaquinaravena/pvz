package Relojes;

import Logica.Juego;

public class RelojLanzables extends Thread{
	protected boolean activo;
	protected boolean resetear;
	protected Juego miJuego;
	
	public RelojLanzables(Juego j) {
		activo = true;
		miJuego = j;
	}
	

	public void run() {
		try {
			while (activo) {
				Thread.sleep(100);
				for(int i=1;i<=6;i++) {
					miJuego.getFila(i).moverLanzables();
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
