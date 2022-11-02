package Logica;

public class Nivel {
	private int cantidadZombiesDebiles;
	private int cantidadZombiesMedianos;
	private int cantidadZombiesFuertes;
	private AbstractFactory factory;
	
	public Nivel(int cantDebiles,int cantMedianos,int cantFuertes,AbstractFactory factory) {
		cantidadZombiesDebiles=cantDebiles;
		cantidadZombiesMedianos=cantMedianos;
		cantidadZombiesFuertes=cantFuertes;
		this.factory=factory;
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
	
	public AbstractFactory getFactory() {
		return factory;
	}
	
	
	
}
