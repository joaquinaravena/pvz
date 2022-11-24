package Launcher;


import GUI.*;

//Launcher de la aplicacion
public class Launcher {
	public static void main(String [] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	Ventana v = new Ventana();
            	v.setVisible(true);
            }
        });
    }
}
