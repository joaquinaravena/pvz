package Logica;

public class Nivel {
	private int cantidadZombiesDebiles;
	private int cantidadZombiesMedianos;
	private int cantidadZombiesFuertes;
	
	public Nivel(int cantDebiles,int cantMedianos,int cantFuertes) {
		cantidadZombiesDebiles=cantDebiles;
		cantidadZombiesMedianos=cantMedianos;
		cantidadZombiesFuertes=cantFuertes;
	}

	public int getCantidadZombiesDebiles() {
		return cantidadZombiesDebiles;
	}

	public int getCantidadZombiesMedianos() {
		return cantidadZombiesMedianos;
	}

	public int getCantidadZombiesFuertes() {
		return cantidadZombiesFuertes;
	}
	
	
	
}
