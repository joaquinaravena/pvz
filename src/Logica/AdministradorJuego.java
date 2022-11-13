package Logica;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import Entidades.Lanzable;
import Entidades.Planta;
import Entidades.Zombie;

public class AdministradorJuego{
	protected List<Zombie> zombiesAEliminar;
	protected List<Planta> plantasAEliminar;
	protected List<Lanzable> lanzablesAEliminar;
	protected Juego juego;
	public AdministradorJuego(Juego j){
		juego=j;
		zombiesAEliminar = new ArrayList<Zombie>();
		plantasAEliminar = new ArrayList<Planta>();
		lanzablesAEliminar = new ArrayList<Lanzable>();
	}
	
	//METODOS PARA BORRAR ENTIDADES
	
		public void agregarZombieAEliminar(Zombie z) {
			zombiesAEliminar.add(z);
		}
		
		public void agregarPlantaAEliminar(Planta p) {
			plantasAEliminar.add(p);
		}
		
		public void agregarLanzableAEliminar(Lanzable p) {
			lanzablesAEliminar.add(p);
		}
		
		public void removerZombies() {
			for (Zombie z: zombiesAEliminar) {
				z.getFila().removerZombie(z);
				z.getEntidadGrafica().borrarGrafica();
			}
			zombiesAEliminar.clear();
		}
		
		public void removerPlantas() {
			List<Planta> plantasClone = new CopyOnWriteArrayList<Planta>(plantasAEliminar);
			List<Zombie> zombiesAtacanClone;
			Iterator<Planta> itPlantas = plantasClone.iterator();
			while(itPlantas.hasNext()) {
				Planta p = itPlantas.next();
				p.getFila().borrarPlanta((p.getX()/74)-2);
				if(p.getLanzable()!=null)
					agregarLanzableAEliminar(p.getLanzable());
				p.getEntidadGrafica().borrarGrafica();
				zombiesAtacanClone=new CopyOnWriteArrayList<Zombie>(p.getZombiesAtacan());
				for(Zombie z: zombiesAtacanClone) {
					z.setPlantaAtacada(null);
					z.setEstrategia(new moverZombie());
					z.getEntidadGrafica().cambiarGrafica(z.getMiRutaMover(), z);
				}
			}
			plantasAEliminar.clear();
		}
		
		public void removerLanzables() {
			List<Lanzable> lanzablesClone = new CopyOnWriteArrayList<Lanzable>(lanzablesAEliminar);
			for (Lanzable p: lanzablesClone) {
				if(p.getFila()==null)//Si no tiene fila asignada es un sol de juego.
					juego.getSolesJuego().remove(p);
				else
					p.getFila().removerLanzable(p);
				p.getEntidadGrafica().borrarGrafica();
			}
			lanzablesAEliminar.clear();
		}
		
		public void resetearListas() {
			removerPlantas();
			removerZombies();
			removerLanzables();
		}
		
	
}
