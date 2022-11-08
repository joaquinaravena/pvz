package GUI;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import Entidades.Entidad;

public class EntidadGrafica {
	private JLabel miGrafica;
	private Ventana ventana;
	
	public EntidadGrafica(Ventana v, Entidad e) {
		//la grafica deberia pasarse por parametro que clave de property tiene q asignar
		miGrafica = new JLabel();
		miGrafica.setIcon(new ImageIcon(new ImageIcon(Ventana.class.getResource(v.getProperties().getProperty("planta2"))).getImage().getScaledInstance(e.getAncho(), e.getAlto(), 0)));
		miGrafica.setBounds(200, 50, miGrafica.getWidth()+100, miGrafica.getHeight()+100);
		ventana = v;
		actualizarGrafica();
	}
	
	public void actualizarGrafica() {
		ventana.actualizarGrafica(this);
	}
	public void borrarGrafica() {
		ventana.borrarGrafica(this);
	}
	public JLabel getGrafica() {
		return miGrafica;
	}
	public void setGrafica() {
		//ventana.setGrafica();
	}
}
