package GUI;
import Logica.*;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Window.Type;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
		//ImageIcon fondoDia = new ImageIcon(Toolkit.getDefaultToolkit().getImage("src/Imagenes/FondoDiaNuevo2.png").getScaledInstance(734, 461, 0));
		//lblNewLabel.setIcon(fondoDia);
		lblFondo.setIcon(new ImageIcon(Ventana.class.getResource("/Imagenes/FondoDiaNuevo.png")));
		lblFondo.setBounds(0, 50, 684, 411);
		panelDia.add(lblFondo);
		
		JPanel panelPlantas = new JPanel();
		panelPlantas.setBackground(new Color(139, 69, 19));
		panelPlantas.setBounds(0, 0, 684, 52);
		panelDia.add(panelPlantas);
		
		JButton btnPonerMusica = new JButton("Parar Musica");
		panelPlantas.add(btnPonerMusica);
		btnPonerMusica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miJuego.reproducirMusica();
			}
		});
		
		JButton btnPararMusica = new JButton("Poner Musica");
		panelPlantas.add(btnPararMusica);
		btnPararMusica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miJuego.pararMusica();
			}
		});
		
		JLabel lblSoles = new JLabel("Soles actuales");
		panelPlantas.add(lblSoles);
		
		JToggleButton botonPlanta1 = new JToggleButton();		
		panelPlantas.add(botonPlanta1);
		
		JToggleButton botonPlanta2 = new JToggleButton();
		panelPlantas.add(botonPlanta2);
		
		JToggleButton botonPlanta3 = new JToggleButton();
		panelPlantas.add(botonPlanta3);
		
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
		
	}
}
