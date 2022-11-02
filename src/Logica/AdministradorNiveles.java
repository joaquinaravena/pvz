package Logica;

public class AdministradorNiveles {
	private Nivel[] niveles;
	
	
	public AdministradorNiveles() {
		//Genero los niveles ya preestablecidos con su cantidad de zombies.
		niveles=new Nivel[4];
		niveles[0]=new Nivel(10,10,1);
		niveles[1]=new Nivel(20,10,7);
		niveles[2]=new Nivel(10,10,1);
		niveles[3]=new Nivel(20,15,5);
	}
	

	
	
	
	
	

}
