package Logica;

public class AdministradorNiveles {
	private Juego juego;
	private Nivel[] niveles;
	
	public AdministradorNiveles(Juego juego) {
		//Genero los niveles ya preestablecidos con su cantidad de zombies.
		niveles=new Nivel[2];
		this.juego=juego;
		

		niveles[0]=new Nivel(2,2,2);
		niveles[1]=new Nivel(20,15,8);

	}
	
	public void nuevoNivel(int nivelElegido) {
		Nivel elegido=niveles[nivelElegido];
		for(int i=0;i<elegido.getCantidadZombiesDebiles();i++) {
			juego.agregarZombieNivel(juego.getBuilder().crearZombieDebil());
		}
		for(int i=0;i<elegido.getCantidadZombiesMedianos();i++) {
			juego.agregarZombieNivel(juego.getBuilder().crearZombieMediano());
		}
		for(int i=0;i<elegido.getCantidadZombiesFuertes();i++) {
			juego.agregarZombieNivel(juego.getBuilder().crearZombieFuerte());
		}

	}
	       
}
