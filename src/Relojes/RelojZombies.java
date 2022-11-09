package Relojes;
import java.util.*;

import Entidades.*;
import Logica.*;

public class RelojZombies extends Thread{
	protected boolean activo;
	protected Juego miJuego;
	protected int contador;
	protected boolean resetear;
	
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
				contador++;
				if(contador == 5) {
					miJuego.agregarZombieActivo();
					contador = 0;
				}
				miJuego.removerZombies();
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
