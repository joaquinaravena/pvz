package GUI;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import Entidades.Entidad;

public class EntidadGrafica {
	protected JLabel miGrafica;
	protected Ventana ventana;
	protected String rutaBaseG;
	
	public EntidadGrafica(Ventana v, Entidad e, String s) {
		miGrafica = new JLabel();
		rutaBaseG = s;
		miGrafica.setIcon(new ImageIcon(new ImageIcon(Ventana.class.getResource(v.getPropertiesModo().getProperty(rutaBaseG))).getImage().getScaledInstance(e.getAncho(), e.getAlto(), 0)));
		miGrafica.setBounds(v.getBordeDerecho(), v.getLinea(e.getNumeroFila()), miGrafica.getIcon().getIconWidth(), miGrafica.getIcon().getIconHeight());
		ventana = v;
		actualizarGrafica();
	}
	
	public void actualizarGrafica() {
		ventana.actualizarGrafica(this);
	}
	public void borrarGrafica() {
		ventana.borrarGrafica(miGrafica);
	}
	public JLabel getGrafica() {
		return miGrafica;
	}
	public String getRutaGrafica() {
		return rutaBaseG;
	}
	public void actualizarFilaGrafica(int i) {
		miGrafica.setBounds(miGrafica.getX(), ventana.getLinea(i), miGrafica.getWidth(), miGrafica.getHeight());
	}
	public void cambiarGrafica(String cambioRuta,Entidad e) {
		//Preguntar si tambien cambio mi rutaG
		miGrafica.setIcon(new ImageIcon(new ImageIcon(Ventana.class.getResource(ventana.getPropertiesModo().getProperty(cambioRuta))).getImage().getScaledInstance(e.getAncho(),e.getAlto(), 0)));
	}
}
