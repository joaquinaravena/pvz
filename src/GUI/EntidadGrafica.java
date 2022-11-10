package GUI;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import Entidades.Entidad;

public class EntidadGrafica {
	private JLabel miGrafica;
	private Ventana ventana;
	private String rutaG;
	
	public EntidadGrafica(Ventana v, Entidad e, String s) {
		//la grafica deberia pasarse por parametro que clave de property tiene q asignar
		miGrafica = new JLabel();
		rutaG = s;
		miGrafica.setIcon(new ImageIcon(new ImageIcon(Ventana.class.getResource(v.getPropertiesModo().getProperty(rutaG))).getImage().getScaledInstance(e.getAncho(), e.getAlto(), 0)));
		miGrafica.setBounds(v.getBordeDerecho(), v.getLinea(e.getNumeroFila()), miGrafica.getIcon().getIconWidth(), miGrafica.getIcon().getIconHeight());
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
	public String getRutaGrafica() {
		return rutaG;
	}
	public void setGrafica() {
		//para cambiar de gif si esta comiendo/disparando
	}
	public void actualizarFilaGrafica(int i) {
		miGrafica.setBounds(miGrafica.getX(), ventana.getLinea(i), miGrafica.getWidth(), miGrafica.getHeight());
	}
}
