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
	/*
	 * Segun la cantidad de cambios (ataques que haya recibido la planta) se setea la gráfica correspondiente para referenciar que llego a la mitad de su vida o murió
	 */
	public void realizarAccion(Planta p, Ventana v) {
		if(p.getVida()<=350 && contCambios==0) {
			p.getEntidadGrafica().cambiarGrafica(mitadVida, p);
			contCambios++;
		}
		else if(p.getVida()<150 && contCambios==1) {
			p.getEntidadGrafica().cambiarGrafica(muerte, p);
			contCambios++;
		}
	}
	
	
	
	
}
