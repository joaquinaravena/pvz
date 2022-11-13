package Logica;
import Entidades.Planta;
import Entidades.Zombie;
import GUI.Ventana;

public class accionPlantaMelee implements PlantaStrategy{
	private int contadorTicks;
	private String plantaAtacando;
	public accionPlantaMelee(String ataque) {
		plantaAtacando=ataque;
		contadorTicks=0;
	}

	public  void realizarAccion(Planta p,Ventana v) {
		if(p.getFila().hayZombies() && !p.getZombiesAtacan().isEmpty() ) {
			p.getEntidadGrafica().cambiarGrafica(plantaAtacando, p);
			if(contadorTicks == 1) {
				for(Zombie z:p.getZombiesAtacan())
					z.restarVida(p.getDaño());
				contadorTicks=0;
			}
			else
				contadorTicks++;
		}
		else 
			p.getEntidadGrafica().cambiarGrafica(p.getEntidadGrafica().getRutaGrafica(),p);
	}

}
