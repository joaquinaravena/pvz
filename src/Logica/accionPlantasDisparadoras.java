package Logica;

import Entidades.Lanzable;
import Entidades.Planta;
import GUI.Ventana;

public class accionPlantasDisparadoras implements PlantaStrategy{
	
	public accionPlantasDisparadoras() {}
	
	
	public  void realizarAccion(Planta p,Ventana v) {
		if(p.getFila().hayZombies()) {
			Lanzable aDisparar=p.getLanzable().clone(v);
			aDisparar.setX(p.getX());
			aDisparar.setY(p.getY());
			aDisparar.getEntidadGrafica().getGrafica().setLocation(p.getX(), p.getY());
			aDisparar.setFila(p.getFila());
			p.getFila().agregarLanzable(aDisparar);
		}
	}	
}
