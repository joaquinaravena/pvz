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
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Font;

@SuppressWarnings("serial")
public class Ventana extends JFrame{
	Juego miJuego;
	Properties prop;
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
		try {
			InputStream input = new FileInputStream(Ventana.class.getResource("/Archivos/configVentana.properties").getFile());
			prop = new Properties();
			prop.load(input);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		setTitle("Plants Vs Zombies");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Ventana.class.getResource(prop.getProperty("logo"))));
		miJuego = new Juego();
		
		setResizable(false);
		setBounds(100, 100, 900, 506);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		crearPanelMenu();
		
	}
	private void crearPanelMenu() {
		JPanel panelMenu = new JPanel();
		panelMenu.setBackground(new Color(205, 133, 63));
		panelMenu.setBounds(0, 0, this.getWidth(), this.getHeight());
		setContentPane(panelMenu);
		panelMenu.setLayout(null);
		
		JButton btnModoDia = new JButton();
		btnModoDia.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));
		btnModoDia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearPanelDia();
			}
		});
		btnModoDia.setText("Modo Dia");
		btnModoDia.setBounds(352, 247, 162, 50);
		panelMenu.add(btnModoDia);
		
		JButton btnModoNoche = new JButton("Modo Noche");
		btnModoNoche.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));
		btnModoNoche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//jugar noche
			}
		});
		btnModoNoche.setBounds(352, 308, 162, 50);
		panelMenu.add(btnModoNoche);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnSalir.setBounds(352, 369, 162, 50);
		panelMenu.add(btnSalir);
		
		JToggleButton btnMusica = new JToggleButton();
		btnMusica.setOpaque(true);
		btnMusica.setToolTipText("frena/reproduce la m\u00FAsica");
		btnMusica.setSelected(true);
		btnMusica.setBounds(24, 406, 53, 50);
		btnMusica.setSelectedIcon(new ImageIcon(new ImageIcon(Ventana.class.getResource(prop.getProperty("stop"))).getImage().getScaledInstance(btnMusica.getWidth(), btnMusica.getHeight(), DO_NOTHING_ON_CLOSE)));
		btnMusica.setIcon(new ImageIcon(new ImageIcon(Ventana.class.getResource(prop.getProperty("play"))).getImage().getScaledInstance(btnMusica.getWidth(), btnMusica.getHeight(), DO_NOTHING_ON_CLOSE)));
		panelMenu.add(btnMusica);
		
		JLabel lblLogoMenu = new JLabel();
		lblLogoMenu.setIcon(new ImageIcon(Ventana.class.getResource(prop.getProperty("menu"))));
		lblLogoMenu.setBounds(242, 11, 360, 229);
		panelMenu.add(lblLogoMenu);
	}
	
	
	private void crearPanelDia() {
		JPanel panelDia = new JPanel();
		panelDia.setBounds(0, 0, this.getWidth(), this.getHeight());
		setContentPane(panelDia);
		panelDia.setLayout(null);
		
		JPanel panelPlantas = new JPanel();
		panelPlantas.setIgnoreRepaint(true);
		panelPlantas.setBackground(new Color(205, 133, 63));
		panelPlantas.setBounds(0, 0, 269, 62);
		panelDia.add(panelPlantas);
		
		JLabel lblFondo = new JLabel();
		lblFondo.setBounds(0, 0, 884, 467);
		lblFondo.setIcon(new ImageIcon(new ImageIcon(Ventana.class.getResource(prop.getProperty("fondoDia"))).getImage().getScaledInstance(lblFondo.getWidth(), lblFondo.getHeight(), DO_NOTHING_ON_CLOSE)));
		panelDia.add(lblFondo);
		
		JToggleButton botonPlanta1 = new JToggleButton();
		botonPlanta1.setBounds(61, 2, 45, 48);
		/*botonPlanta1.setEnabled(false);
		if(miJuego.getSoles() >= planta1.getPrecio())
			botonPlanta1.setEnabled(true);*/
		botonPlanta1.setIcon(new ImageIcon(new ImageIcon(Ventana.class.getResource(prop.getProperty("girasol"))).getImage().getScaledInstance(botonPlanta1.getWidth(), botonPlanta1.getHeight() , DO_NOTHING_ON_CLOSE)));
		
		JToggleButton botonPlanta2 = new JToggleButton();
		botonPlanta2.setBounds(114, 2, 45, 48);
		/*botonPlanta2.setEnabled(false);
		if(miJuego.getSoles() >= planta1.getPrecio())
			botonPlanta2.setEnabled(true);*/
		botonPlanta2.setIcon(new ImageIcon(new ImageIcon(Ventana.class.getResource(prop.getProperty("lanzaGuisantes"))).getImage().getScaledInstance(botonPlanta2.getWidth(), botonPlanta2.getHeight() , DO_NOTHING_ON_CLOSE)));
		
		JToggleButton botonPlanta3 = new JToggleButton();
		botonPlanta3.setBounds(169, 2, 45, 48);		
		/*botonPlanta2.setEnabled(false);
		if(miJuego.getSoles() >= planta1.getPrecio())
			botonPlanta2.setEnabled(true);*/
		//botonPlanta3.setIcon(new ImageIcon(new ImageIcon(Ventana.class.getResource(prop.getProperty("planta3"))).getImage().getScaledInstance(botonPlanta3.getWidth(), botonPlanta3.getHeight() , DO_NOTHING_ON_CLOSE)));

		JToggleButton botonMusica = new JToggleButton();
		botonMusica.setToolTipText("frena/reproduce la m\u00FAsica");
		botonMusica.setBounds(220, 2, 45, 48);
		botonMusica.setSelected(true);
		botonMusica.setSelectedIcon(new ImageIcon(new ImageIcon(Ventana.class.getResource(prop.getProperty("stop"))).getImage().getScaledInstance(botonMusica.getWidth(), botonMusica.getHeight(), DO_NOTHING_ON_CLOSE)));
		botonMusica.setIcon(new ImageIcon(new ImageIcon(Ventana.class.getResource(prop.getProperty("play"))).getImage().getScaledInstance(botonMusica.getWidth(), botonMusica.getHeight(), DO_NOTHING_ON_CLOSE)));
				
		botonPlanta1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				botonPlanta2.setSelected(false);
				botonPlanta3.setSelected(false);
				if(botonPlanta1.isSelected())
					miJuego.setPlantaEnEspera(null);
				else
					miJuego.setPlantaEnEspera(null);
			}
		});
				
				
		botonPlanta2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				botonPlanta1.setSelected(false);
				botonPlanta3.setSelected(false);
				if(botonPlanta2.isSelected())
					miJuego.setPlantaEnEspera(null);
				else
					miJuego.setPlantaEnEspera(null);
			}
		});
				
				
		botonPlanta3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				botonPlanta2.setSelected(false);
				botonPlanta1.setSelected(false);
				if(botonPlanta3.isSelected())
					miJuego.setPlantaEnEspera(null);
				else
					miJuego.setPlantaEnEspera(null);
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
		lblSol.setIcon(new ImageIcon(new ImageIcon(Ventana.class.getResource(prop.getProperty("sol"))).getImage().getScaledInstance(lblSol.getWidth(), lblSol.getHeight(), DO_NOTHING_ON_CLOSE)));
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
		
		JLabel lblSolesActuales = new JLabel(""+miJuego.getSoles());
		lblSolesActuales.setHorizontalAlignment(SwingConstants.CENTER);
		lblSolesActuales.setBounds(6, 48, 46, 14);
		panelPlantas.add(lblSolesActuales);
		
		
		repaint();
		//gameOver();
	}
	
	private void botonVolver() {
		
	}
	public void actualizarGrafica(EntidadGrafica eg) {
		getContentPane().add(eg.getGrafica());
		getContentPane().repaint();
		
	}
	public void borrarGrafica(EntidadGrafica eg) {
		getContentPane().add(eg.getGrafica());
		getContentPane().repaint();
	}
	private void gameOver() {
		JLabel lblGameOver = new JLabel();
		lblGameOver.setBounds(321, 88, 357, 271);
		lblGameOver.setIcon(new ImageIcon(new ImageIcon(Ventana.class.getResource(prop.getProperty("gameOver"))).getImage().getScaledInstance(lblGameOver.getWidth(), lblGameOver.getHeight(), DO_NOTHING_ON_CLOSE)));
		getContentPane().add(lblGameOver);
		getContentPane().repaint();
	}
	private void ganarJuego() {
		
	}
	private void salir() {
		
	}
	
	public void controlarPlantasAComprar() {
		
	}
}
