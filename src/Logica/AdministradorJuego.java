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
			List<Zombie> zombiesClone= new CopyOnWriteArrayList<Zombie>(zombiesAEliminar);
			for (Zombie z: zombiesClone) {
				z.getFila().removerZombie(z);
				zombiesAEliminar.remove(z);
			}
			zombiesClone.clear();
		}
		
		public void removerPlantas() {
			List<Planta> plantasClone = new CopyOnWriteArrayList<Planta>(plantasAEliminar);
			List<Zombie> zombiesAtacanClone;
			Iterator<Planta> itPlantas = plantasClone.iterator();
			while(itPlantas.hasNext()) {
				Planta p = itPlantas.next();
				p.getFila().borrarPlanta((p.getX()/74)-2);
				if(p.getLanzable()!=null)
					p.getLanzable().morir();
				zombiesAtacanClone=new CopyOnWriteArrayList<Zombie>(p.getZombiesAtacan());
				for(Zombie z: zombiesAtacanClone) {
					z.setPlantaAtacada(null);
				}
				plantasAEliminar.remove(p);
			}
			plantasClone.clear();
		}
		
		public void removerLanzables() {			
			List<Lanzable> lanzablesClone = new CopyOnWriteArrayList<Lanzable>(lanzablesAEliminar);
			for (Lanzable p: lanzablesClone) {
				if(p.getFila()==null)//Si no tiene fila asignada es un sol de juego.
					juego.getSolesJuego().remove(p);
				else
					p.getFila().removerLanzable(p);
				lanzablesAEliminar.remove(p);
			}
			lanzablesClone.clear();
		}
		
		public void resetearListas() {
			removerPlantas();
			removerZombies();
			removerLanzables();
		}
		
	
}
