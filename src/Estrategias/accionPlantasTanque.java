package Estrategias;

import Entidades.Planta;
import GUI.Ventana;

public class accionPlantasTanque implements PlantaStrategy{
	private String mitadVida,muerte;
	private int contCambios;
	public accionPlantasTanque(String mitadVida,String muerte) {
		this.mitadVida=mitadVida;
		this.muerte=muerte;
		contCambios=0;
	}
	
	public void realizarAccion(Planta p, Ventana v) {
		if(p.getVida()<=250 && contCambios==0) {
			p.getEntidadGrafica().cambiarGrafica(mitadVida, p);
			contCambios++;
		}
		else if(p.getVida()<60 && contCambios==1) {
			p.getEntidadGrafica().cambiarGrafica(muerte, p);
			contCambios++;
		}
	}
	
	
	
	
}
