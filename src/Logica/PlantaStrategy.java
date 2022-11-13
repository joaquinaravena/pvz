package Logica;

import Entidades.Planta;
import GUI.Ventana;

public interface PlantaStrategy {
	public abstract void realizarAccion(Planta z,Ventana v);	
}
