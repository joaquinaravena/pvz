package Relojes;

import Logica.*;

public class RelojZombies extends Thread{
	protected boolean activo;
	protected Juego miJuego;
	protected int contador;
	protected int velocidad;
	protected int contOleadas;
	
	public RelojZombies(Juego j) {
		activo = true;
		miJuego = j;
		contador = 0;
		velocidad = 220;
	}
	
	public void run() {
		try {
			while (activo) {
				Thread.sleep(velocidad);
				miJuego.moverZombies();
				contador++;
				if(contOleadas != 0) {
					miJuego.getAdministradorNiveles().oleada();
					contOleadas--;
				}else {
					if(contador % 30==0) {
						miJuego.agregarZombieActivo();
					}
					if(contador % 50==0)
						miJuego.agregarSolAJuego();
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
	public void setVelocidad(int i) {
		if(i != 1)
			velocidad = velocidad*i;
		else
			velocidad = velocidad/2;
	}
	public void setContOleadas(int i) {
		contOleadas = i;
	}
}
