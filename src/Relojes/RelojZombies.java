package Relojes;

import Logica.*;

public class RelojZombies extends Thread{
	protected boolean activo;
	protected Juego miJuego;
	protected int contador;
	
	public RelojZombies(Juego j) {
		activo = true;
		miJuego = j;
		contador = 0;
	}
	
	public void run() {
		try {
			while (activo) {
				Thread.sleep(300);
				miJuego.moverZombies();
				miJuego.getAdministradorJuego().removerZombies();
				contador++;
				if(contador % 25==0) {
					miJuego.agregarZombieActivo();
				}
				if(contador % 45==0)
					miJuego.agregarSolAJuego();
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
