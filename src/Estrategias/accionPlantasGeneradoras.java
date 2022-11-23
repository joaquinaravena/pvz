package Estrategias;

import Entidades.Lanzable;
import Entidades.Planta;
import GUI.Ventana;

public class accionPlantasGeneradoras implements PlantaStrategy{
	int contadorTicks;
	
	public accionPlantasGeneradoras() {
		contadorTicks=0;
	}
	
	
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
