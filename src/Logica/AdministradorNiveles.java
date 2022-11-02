package Logica;

public class AdministradorNiveles {
	private Juego juego;
	private Nivel[] niveles;
	private AbstractFactory fabricaDia;
	private AbstractFactory fabricaNoche;
	
	public AdministradorNiveles(Juego juego) {
		//Genero los niveles ya preestablecidos con su cantidad de zombies.
		niveles=new Nivel[4];
		this.juego=juego;
		//Cambiar cuando esten implementadas las fabricas.
		fabricaDia=new FabricaDia();
		fabricaNoche=new FabricaNoche();
		niveles[0]=new Nivel(10,10,1,fabricaDia);
		niveles[1]=new Nivel(20,10,7,fabricaDia);
		niveles[2]=new Nivel(10,10,1,fabricaNoche);
		niveles[3]=new Nivel(20,15,5,fabricaNoche);
	}
	
	public void nuevoNivel(int nivelElegido) {
		Nivel elegido=niveles[nivelElegido];
		for(int i=0;i<elegido.getCantidadZombiesDebiles();i++) {
			juego.agregarZombie(elegido.getFactory().crearZombieDebil());
		}
		for(int i=0;i<elegido.getCantidadZombiesMedianos();i++) {
			juego.agregarZombie(elegido.getFactory().crearZombieMediano());
		}
		for(int i=0;i<elegido.getCantidadZombiesFuertes();i++) {
			juego.agregarZombie(elegido.getFactory().crearZombieFuerte());
		}
	}
	       
}
