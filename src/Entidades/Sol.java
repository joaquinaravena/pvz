package Entidades;

public class Sol extends Lanzable{
	
	public Sol() {
		super();
	}
	
	public void mover() {
		y=y-10;
	}
	
	public Sol clone() {
		Sol aRetornar=new Sol();
		return aRetornar;
	}

	@Override
	public void chocar(Zombie z) {
	}
}
