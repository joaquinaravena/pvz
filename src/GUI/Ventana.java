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
import javax.swing.SwingConstants;

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
		setTitle("Plants Vs Zombies");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Ventana.class.getResource("/Imagenes/logoPVZ.png")));
		miJuego = new Juego();
		setResizable(false);
		setBounds(100, 100, 900, 506);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		crearPanelDia();
		
	}
	
	private void crearPanelDia() {
		JPanel panelDia = new JPanel();
		panelDia.setBounds(0, 0, 434, 261);
		setContentPane(panelDia);
		panelDia.setLayout(null);
		
		JPanel panelPlantas = new JPanel();
		panelPlantas.setIgnoreRepaint(true);
		panelPlantas.setBackground(new Color(205, 133, 63));
		panelPlantas.setBounds(0, 0, 269, 62);
		panelDia.add(panelPlantas);
		
		JToggleButton botonPlanta1 = new JToggleButton();
		botonPlanta1.setBounds(61, 2, 45, 48);
		botonPlanta1.setIcon(new ImageIcon(new ImageIcon(Ventana.class.getResource("/Imagenes/girasol.png")).getImage().getScaledInstance(botonPlanta1.getWidth(), botonPlanta1.getHeight() , DO_NOTHING_ON_CLOSE)));
		
		JToggleButton botonPlanta2 = new JToggleButton();
		botonPlanta2.setBounds(114, 2, 45, 48);
		botonPlanta2.setIcon(new ImageIcon(new ImageIcon(Ventana.class.getResource("/Imagenes/lanzaGuisantes.png")).getImage().getScaledInstance(botonPlanta2.getWidth(), botonPlanta2.getHeight() , DO_NOTHING_ON_CLOSE)));
		
		JToggleButton botonPlanta3 = new JToggleButton();
		botonPlanta3.setBounds(169, 2, 45, 48);		
				
		JToggleButton botonMusica = new JToggleButton();
		botonMusica.setToolTipText("frena/reproduce la m\u00FAsica");
		botonMusica.setBounds(220, 2, 45, 48);
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
				
				
		JLabel lblSol = new JLabel();
		lblSol.setBounds(6, 2, 45, 48);
		lblSol.setIcon(new ImageIcon(new ImageIcon(Ventana.class.getResource("/Imagenes/sol.png")).getImage().getScaledInstance(lblSol.getWidth(), lblSol.getHeight(), DO_NOTHING_ON_CLOSE)));
		lblSol.setVerticalAlignment(SwingConstants.BOTTOM);
		lblSol.setHorizontalAlignment(SwingConstants.CENTER);
		
		panelPlantas.setLayout(null);
		panelPlantas.add(botonPlanta1);
		panelPlantas.add(botonPlanta2);
		panelPlantas.add(botonPlanta3);
		panelPlantas.add(botonMusica);
		panelPlantas.add(lblSol);
		
		JLabel lblPrecioP1 = new JLabel("25");
		lblPrecioP1.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrecioP1.setBounds(61, 48, 46, 14);
		panelPlantas.add(lblPrecioP1);
		
		JLabel lblPrecioP2 = new JLabel("75");
		lblPrecioP2.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrecioP2.setBounds(116, 48, 46, 14);
		panelPlantas.add(lblPrecioP2);
		
		JLabel lblPrecioP3 = new JLabel("100");
		lblPrecioP3.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrecioP3.setBounds(169, 48, 46, 14);
		panelPlantas.add(lblPrecioP3);
		
		JLabel lblSolesActuales = new JLabel("100");
		lblSolesActuales.setHorizontalAlignment(SwingConstants.CENTER);
		lblSolesActuales.setBounds(6, 48, 46, 14);
		panelPlantas.add(lblSolesActuales);
		
		
		
		JLabel lblFondo = new JLabel();
		lblFondo.setBounds(0, 0, 884, 467);
		lblFondo.setIcon(new ImageIcon(new ImageIcon(Ventana.class.getResource("/Imagenes/FondoDia.png")).getImage().getScaledInstance(lblFondo.getWidth(), lblFondo.getHeight(), DO_NOTHING_ON_CLOSE)));
		panelDia.add(lblFondo);
		
		gameOver();
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
	private void gameOver() {
		JLabel lblGameOver = new JLabel();
		lblGameOver.setBounds(321, 88, 357, 271);
		lblGameOver.setIcon(new ImageIcon(new ImageIcon(Ventana.class.getResource("/Imagenes/gameOver.png")).getImage().getScaledInstance(lblGameOver.getWidth(), lblGameOver.getHeight(), DO_NOTHING_ON_CLOSE)));
		getContentPane().add(lblGameOver);
		getContentPane().repaint();
	}
}
