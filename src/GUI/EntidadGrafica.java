package GUI;

import javax.swing.JLabel;
import Entidades.Entidad;

public class EntidadGrafica {
	private JLabel miGrafica;
	private Ventana ventana;
	
	public EntidadGrafica(Ventana v) {
		miGrafica = new JLabel();
		ventana = v;
	}
	
	public void actualizarGrafica() {
		//ventana.actualizarGrafica();
	}
	public void borrarGrafica() {
		//ventana.borrarGrafica(this);
	}
	public JLabel getGrafica() {
		return miGrafica;
	}
	public void setGrafica() {
		//ventana.setGrafica();
	}
}
