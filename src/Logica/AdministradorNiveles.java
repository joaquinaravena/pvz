package Logica;

public class AdministradorNiveles {
	private Juego juego;
	private Nivel[] niveles;
	
	public AdministradorNiveles(Juego juego) {
		//Genero los niveles ya preestablecidos con su cantidad de zombies.
		niveles=new Nivel[2];
		this.juego=juego;
		
		niveles[0]=new Nivel(20,10,7);
		niveles[1]=new Nivel(20,15,8);
	}
	
	public void nuevoNivel(int nivelElegido) {
		Nivel elegido=niveles[nivelElegido];
		for(int i=0;i<elegido.getCantidadZombiesDebiles();i++) {
			juego.agregarZombieNivel(juego.getFactory().crearZombieDebil());
		}
		for(int i=0;i<elegido.getCantidadZombiesMedianos();i++) {
			juego.agregarZombieNivel(juego.getFactory().crearZombieMediano());
		}
		for(int i=0;i<elegido.getCantidadZombiesFuertes();i++) {
			juego.agregarZombieNivel(juego.getFactory().crearZombieFuerte());
		}

	}
	       
}
