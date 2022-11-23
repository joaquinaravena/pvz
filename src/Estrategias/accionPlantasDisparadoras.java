package Estrategias;

import Entidades.Lanzable;
import Entidades.Planta;
import GUI.Ventana;

public class accionPlantasDisparadoras implements PlantaStrategy{
	int contadorTicks;
	public accionPlantasDisparadoras() {
		contadorTicks=0;
	}
	/*
	 * Setea el lanzable con la imagen correspondiente. Tambien cambia la imagen de la propia planta. La planta dispara cada dos ticks si hay zombies en su fila
	 */
	public  void realizarAccion(Planta p,Ventana v) {
		if(p.getFila().hayZombies()) {
			if(contadorTicks == 2) {
				Lanzable aDisparar=p.getLanzable().clone(v);
				aDisparar.setX(p.getX());
				aDisparar.setY(p.getY());
				aDisparar.getEntidadGrafica().getGrafica().setLocation(p.getX(), p.getY());
				aDisparar.setFila(p.getFila());
				p.getFila().agregarLanzable(aDisparar);
				contadorTicks=0;
				aDisparar.getEntidadGrafica().actualizarGrafica();
			}
			else
				contadorTicks++;
		}
	}	
}
