package Relojes;

import Logica.Juego;

public class RelojLanzables extends Thread{
	protected boolean activo;
	protected Juego miJuego;
	protected int velocidad;
	
	public RelojLanzables(Juego j) {
		activo = true;
		miJuego = j;
		velocidad = 100;
	}
	

	public void run() {
		try {
			while (activo) {
				Thread.sleep(velocidad);
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
	public void setVelocidad(int i) {
		if(i != 1)
			velocidad = velocidad*i;
		else
			velocidad = velocidad/2;
	}
}
