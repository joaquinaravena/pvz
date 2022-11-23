package Estrategias;

import Entidades.Lanzable;
import Entidades.Planta;
import GUI.Ventana;

public class accionPlantasGeneradoras implements PlantaStrategy{
	int contadorTicks;
	
	public accionPlantasGeneradoras() {
		contadorTicks=0;
	}
	
	/*
	 * Setea el lanzable con la imagen correspondiente. Tambien cambia la imagen de la propia planta. La planta genera un lanzable cada 12 ticks
	 */
	public  void realizarAccion(Planta p,Ventana v) {
		if(contadorTicks==12) {
			Lanzable aDisparar=p.getLanzable().clone(v);
			aDisparar.setX(p.getX());
			aDisparar.setY(p.getY());
			aDisparar.getEntidadGrafica().getGrafica().setLocation(p.getX(), p.getY());
			aDisparar.setFila(p.getFila());
			v.actualizarGrafica(aDisparar.getEntidadGrafica());
			p.getFila().agregarLanzable(aDisparar);
			contadorTicks=0;
		}
		else
			contadorTicks++;
		
	}
}
