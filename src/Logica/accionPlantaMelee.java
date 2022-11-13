package Logica;
import Entidades.Planta;
import Entidades.Zombie;
import GUI.Ventana;

public class accionPlantaMelee implements PlantaStrategy{
	private int contadorTicks;
	private String plantaAtacando;
	private boolean plantaEstaAtacando;
	public accionPlantaMelee(String ataque) {
		plantaAtacando=ataque;
		contadorTicks=0;
		plantaEstaAtacando=false;
	}

	public  void realizarAccion(Planta p,Ventana v) {
		if(p.getFila().hayZombies() && !p.getZombiesAtacan().isEmpty() ) {
			if(!plantaEstaAtacando) {
				p.getEntidadGrafica().cambiarGrafica(plantaAtacando, p);
				plantaEstaAtacando=true;
				}
			if(contadorTicks == 1) {
				for(Zombie z:p.getZombiesAtacan())
					z.restarVida(p.getDaño());
				contadorTicks=0;
			}
			else
				contadorTicks++;
		}
		else if(plantaEstaAtacando) {
			p.getEntidadGrafica().cambiarGrafica(p.getEntidadGrafica().getRutaGrafica(),p);
			plantaEstaAtacando=false;
		}
	}

}
