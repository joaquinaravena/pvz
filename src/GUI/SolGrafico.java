package GUI;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Entidades.Entidad;
import Entidades.Sol;
import Logica.Juego;

public class SolGrafico extends EntidadGrafica {

	public SolGrafico(Ventana v, Entidad ent, String s, Juego j) {
		super(v, ent, s);
		this.miGrafica.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				((Sol)ent).morir();
				j.setSoles(j.getSoles()+50);
				v.controlarPlantasAComprar();
			}
		});
	}
}
