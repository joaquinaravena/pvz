package GUI;
import Logica.*;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class Ventana extends JFrame{
	Juego miJuego;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ventana window = new Ventana();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Ventana() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Ventana.class.getResource("/Imagenes/logoPVZ.png")));
		miJuego = new Juego();
		setResizable(false);
		setBounds(100, 100, 700, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		crearPanelDia();
		
	}
	
	private void crearPanelDia() {
		JPanel panelDia = new JPanel();
		panelDia.setBounds(0, 0, 434, 261);
		setContentPane(panelDia);
		panelDia.setLayout(null);
		
		JLabel lblFondo = new JLabel();
		lblFondo.setBounds(0, 50, 684, 411);
		lblFondo.setIcon(new ImageIcon(new ImageIcon(Ventana.class.getResource("/Imagenes/FondoDia.png")).getImage().getScaledInstance(lblFondo.getWidth(), lblFondo.getHeight(), DO_NOTHING_ON_CLOSE)));
		panelDia.add(lblFondo);
		
		JPanel panelPlantas = new JPanel();
		panelPlantas.setBackground(new Color(139, 69, 19));
		panelPlantas.setBounds(0, 0, 684, 52);
		panelDia.add(panelPlantas);
		
		JToggleButton botonPlanta1 = new JToggleButton();
		botonPlanta1.setBounds(307, 7, 41, 39);
		botonPlanta1.setIcon(new ImageIcon(new ImageIcon(Ventana.class.getResource("/Imagenes/girasol.png")).getImage().getScaledInstance(botonPlanta1.getWidth(), botonPlanta1.getHeight() , DO_NOTHING_ON_CLOSE)));

		JToggleButton botonPlanta2 = new JToggleButton();
		botonPlanta2.setBounds(358, 7, 41, 39);
		JToggleButton botonPlanta3 = new JToggleButton();
		botonPlanta3.setBounds(409, 7, 41, 39);		
		
		JToggleButton botonMusica = new JToggleButton();
		botonMusica.setToolTipText("frena/reproduce la m\u00FAsica");
		botonMusica.setBounds(633, 7, 41, 39);
		botonMusica.setSelected(true);
		botonMusica.setSelectedIcon(new ImageIcon(new ImageIcon(Ventana.class.getResource("/Imagenes/stop.jpg")).getImage().getScaledInstance(botonMusica.getWidth(), botonMusica.getHeight(), DO_NOTHING_ON_CLOSE)));
		botonMusica.setIcon(new ImageIcon(new ImageIcon(Ventana.class.getResource("/Imagenes/play.jpg")).getImage().getScaledInstance(botonMusica.getWidth(), botonMusica.getHeight(), DO_NOTHING_ON_CLOSE)));
		
		botonPlanta1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				botonPlanta2.setSelected(false);
				botonPlanta3.setSelected(false);
			}
		});
		
		
		botonPlanta2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				botonPlanta1.setSelected(false);
				botonPlanta3.setSelected(false);
			}
		});
		
		
		botonPlanta3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				botonPlanta2.setSelected(false);
				botonPlanta1.setSelected(false);
			}
		});
		
		
		botonMusica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(botonMusica.isSelected()) 
					miJuego.reproducirMusica();
				else 
					miJuego.pararMusica();
			}
		});
		
		
		JLabel lblSoles = new JLabel("Soles actuales");
		lblSoles.setBounds(41, 32, 68, 14);
		panelPlantas.setLayout(null);
		panelPlantas.add(botonPlanta1);
		panelPlantas.add(botonPlanta2);
		panelPlantas.add(botonPlanta3);
		panelPlantas.add(botonMusica);
		panelPlantas.add(lblSoles);
		
	}
	
	private void botonVolver() {
		
	}
	private void actualizarGrafica(EntidadGrafica eg) {
		getContentPane().add(eg.getGrafica());
		getContentPane().repaint();
		
	}
	private void borrarGrafica(EntidadGrafica eg) {
		getContentPane().add(eg.getGrafica());
		getContentPane().repaint();
	}
}
