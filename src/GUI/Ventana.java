package GUI;
import Logica.*;



import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
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
import javax.swing.Timer;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class Ventana extends JFrame{
	private Juego miJuego;
	private Properties propMenu;
	private Properties propModo;
	private JPanel panelPlantas;
	private JLabel lblOleadas;
	private JLabel lblNivel;
	private JLabel lblSolesActuales;
	private JToggleButton botonVelocidad;


	public Ventana() {
		try {
			InputStream input = new FileInputStream(Ventana.class.getResource("/Archivos/configMenu.properties").getFile());
			propMenu = new Properties();
			propMenu.load(input);
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
		setTitle("Plants Vs Zombies");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Ventana.class.getResource(propMenu.getProperty("logo"))));
		miJuego = new Juego(this);
		
		setResizable(false);
		setBounds(100, 100, 900, 506);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		crearSplashScreen();
        ActionListener crearPaneles = new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent evt) {
                 crearPanelMenu();
                 miJuego.reproducirMusica();
           }
        };
	    Timer timer = new Timer(2500, crearPaneles);
	    timer.setRepeats(false);
	    timer.start();
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
				try {
					InputStream input = new FileInputStream(Ventana.class.getResource("/Archivos/configDia.properties").getFile());
					propModo = new Properties();
					propModo.load(input);
					miJuego.setBuilderDia();
				} catch (IOException ex) {
					System.out.println(ex.getMessage());
				}
				crearPanelModo();
				miJuego.jugar(0);
			}
		});
				
		btnModoDia.setText("Modo Dia");
		btnModoDia.setBounds(352, 247, 162, 50);
		panelMenu.add(btnModoDia);
		
		JButton btnModoNoche = new JButton("Modo Noche");
		btnModoNoche.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));
		btnModoNoche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					InputStream input = new FileInputStream(Ventana.class.getResource("/Archivos/configNoche.properties").getFile());
					propModo = new Properties();
					propModo.load(input);
					miJuego.setBuilderNoche();
				} catch (IOException ex) {
					System.out.println(ex.getMessage());
				}
				crearPanelModo();
				miJuego.jugar(0);
			}
		});
		btnModoNoche.setBounds(352, 308, 162, 50);
		panelMenu.add(btnModoNoche);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miJuego.salir();
			}
		});
		btnSalir.setBounds(352, 369, 162, 50);
		panelMenu.add(btnSalir);
		
		JToggleButton btnMusica = new JToggleButton();
		btnMusica.setOpaque(true);
		btnMusica.setToolTipText("frena/reproduce la m\u00FAsica");
		if(miJuego.reproduciendoMusica())
			btnMusica.setSelected(true);
		else
			btnMusica.setSelected(false);
		btnMusica.setBounds(24, 406, 53, 50);
		btnMusica.setSelectedIcon(new ImageIcon(new ImageIcon(Ventana.class.getResource(propMenu.getProperty("stop"))).getImage().getScaledInstance(btnMusica.getWidth(), btnMusica.getHeight(), DO_NOTHING_ON_CLOSE)));
		btnMusica.setIcon(new ImageIcon(new ImageIcon(Ventana.class.getResource(propMenu.getProperty("play"))).getImage().getScaledInstance(btnMusica.getWidth(), btnMusica.getHeight(), DO_NOTHING_ON_CLOSE)));
		panelMenu.add(btnMusica);
		btnMusica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnMusica.isSelected()) 
					miJuego.reproducirMusica();
				else 
					miJuego.pararMusica();
			}
		});
		
		JLabel lblLogoMenu = new JLabel();
		lblLogoMenu.setIcon(new ImageIcon(Ventana.class.getResource(propMenu.getProperty("menu"))));
		lblLogoMenu.setBounds(242, 11, 360, 229);
		panelMenu.add(lblLogoMenu);
	}
	
	
	private void crearPanelModo() {
		this.setFocusable(true);
		JLayeredPane panelModo = new JLayeredPane();
		panelModo.setBounds(0, 0, this.getWidth(), this.getHeight());
		setContentPane(panelModo);
		panelModo.setLayout(null);
		
		panelPlantas = new JPanel();
		panelPlantas.setBounds(0, 0, 443, 62);
		panelModo.add(panelPlantas, JLayeredPane.PALETTE_LAYER);
		
		JLabel fondoBotonera = new JLabel();
		fondoBotonera.setBounds(0,0, panelPlantas.getWidth(), panelPlantas.getHeight());
		fondoBotonera.setIcon(new ImageIcon(new ImageIcon(Ventana.class.getResource(propMenu.getProperty("botonera"))).getImage().getScaledInstance(fondoBotonera.getWidth(), fondoBotonera.getHeight(), DO_NOTHING_ON_CLOSE)));

		JToggleButton botonPlanta1 = new JToggleButton();
		botonPlanta1.setBounds(61, 2, 45, 48);
		botonPlanta1.setIcon(new ImageIcon(new ImageIcon(Ventana.class.getResource(propModo.getProperty("plantaGeneradoraBoton"))).getImage().getScaledInstance(botonPlanta1.getWidth(), botonPlanta1.getHeight() , DO_NOTHING_ON_CLOSE)));
		
		JToggleButton botonPlanta2 = new JToggleButton();
		botonPlanta2.setBounds(114, 2, 45, 48);
		botonPlanta2.setIcon(new ImageIcon(new ImageIcon(Ventana.class.getResource(propModo.getProperty("plantaDebilBoton"))).getImage().getScaledInstance(botonPlanta2.getWidth(), botonPlanta2.getHeight() , DO_NOTHING_ON_CLOSE)));
		
		JToggleButton botonPlanta3 = new JToggleButton();
		botonPlanta3.setBounds(169, 2, 45, 48);		
		botonPlanta3.setIcon(new ImageIcon(new ImageIcon(Ventana.class.getResource(propModo.getProperty("plantaFuerteBoton"))).getImage().getScaledInstance(botonPlanta3.getWidth(), botonPlanta3.getHeight() , DO_NOTHING_ON_CLOSE)));
		
		JToggleButton botonPlanta4= new JToggleButton();
		botonPlanta4.setBounds(224, 2, 45, 48);		
		botonPlanta4.setIcon(new ImageIcon(new ImageIcon(Ventana.class.getResource(propModo.getProperty("plantaTanqueBoton"))).getImage().getScaledInstance(botonPlanta4.getWidth(), botonPlanta4.getHeight() , DO_NOTHING_ON_CLOSE)));
		
		JToggleButton botonMusica = new JToggleButton();
		botonMusica.setToolTipText("frena/reproduce la m\u00FAsica");
		botonMusica.setBounds(279, 2, 45, 48);
		botonMusica.setSelected(true);
		if(!miJuego.reproduciendoMusica())
			botonMusica.setSelected(false);
			
		botonMusica.setSelectedIcon(new ImageIcon(new ImageIcon(Ventana.class.getResource(propMenu.getProperty("stop"))).getImage().getScaledInstance(botonMusica.getWidth(), botonMusica.getHeight(), DO_NOTHING_ON_CLOSE)));
		botonMusica.setIcon(new ImageIcon(new ImageIcon(Ventana.class.getResource(propMenu.getProperty("play"))).getImage().getScaledInstance(botonMusica.getWidth(), botonMusica.getHeight(), DO_NOTHING_ON_CLOSE)));
		
		JToggleButton botonPala = new JToggleButton();
		botonPala.setToolTipText("remueve una planta del tablero y devuelve el 50% del valor de la planta");
		botonPala.setSelected(false);
		botonPala.setBounds(389, 2, 45, 48);
		botonPala.setIcon(new ImageIcon(new ImageIcon(Ventana.class.getResource(propMenu.getProperty("pala"))).getImage().getScaledInstance(botonPala.getWidth(), botonPala.getHeight() , DO_NOTHING_ON_CLOSE)));
		
		botonPlanta1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				botonPlanta2.setSelected(false);
				botonPlanta3.setSelected(false);
				botonPlanta4.setSelected(false);
				botonPala.setSelected(false);
				if(botonPlanta1.isSelected()) {
					miJuego.setPlantaEnEspera(1);
					miJuego.setPalaSeleccionada(false);
				}else
					miJuego.setPlantaEnEspera(0);
			}
		});
		
		botonPlanta2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				botonPlanta1.setSelected(false);
				botonPlanta3.setSelected(false);
				botonPlanta4.setSelected(false);
				botonPala.setSelected(false);
				if(botonPlanta2.isSelected()) {
					miJuego.setPlantaEnEspera(2);
					miJuego.setPalaSeleccionada(false);
				}else
					miJuego.setPlantaEnEspera(0);
			}
		});
		
		
		botonPlanta3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				botonPlanta2.setSelected(false);
				botonPlanta1.setSelected(false);
				botonPlanta4.setSelected(false);
				botonPala.setSelected(false);
				if(botonPlanta3.isSelected()) { 
					miJuego.setPlantaEnEspera(3);
					miJuego.setPalaSeleccionada(false);
				}else
					miJuego.setPlantaEnEspera(0);
			}
		});
		
		botonPlanta4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				botonPlanta2.setSelected(false);
				botonPlanta3.setSelected(false);
				botonPlanta1.setSelected(false);
				botonPala.setSelected(false);
				if(botonPlanta4.isSelected()) {
					miJuego.setPlantaEnEspera(4);
					miJuego.setPalaSeleccionada(false);
				}else
					miJuego.setPlantaEnEspera(0);
			}
		});
		
		botonPala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				botonPlanta2.setSelected(false);
				botonPlanta3.setSelected(false);
				botonPlanta1.setSelected(false);
				botonPlanta4.setSelected(false);
				if(botonPala.isSelected()) {
					miJuego.setPalaSeleccionada(true);
					miJuego.setPlantaEnEspera(0);
				}else
					miJuego.setPalaSeleccionada(false);
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
		lblSol.setIcon(new ImageIcon(new ImageIcon(Ventana.class.getResource(propModo.getProperty("moneda"))).getImage().getScaledInstance(lblSol.getWidth(), lblSol.getHeight(), DO_NOTHING_ON_CLOSE)));
		lblSol.setVerticalAlignment(SwingConstants.BOTTOM);
		lblSol.setHorizontalAlignment(SwingConstants.CENTER);
		
		panelPlantas.setLayout(null);
		panelPlantas.add(botonPlanta1);
		panelPlantas.add(botonPlanta2);
		panelPlantas.add(botonPlanta3);
		panelPlantas.add(botonPlanta4);
		panelPlantas.add(botonPala);
		panelPlantas.add(botonMusica);
		
		
		botonVelocidad = new JToggleButton();
		botonVelocidad.setToolTipText("aumenta/disminuye la velocidad del juego");
		botonVelocidad.setSelected(false);
		botonVelocidad.setBounds(334, 2, 45, 48);
		botonVelocidad.setIcon(new ImageIcon(new ImageIcon(Ventana.class.getResource(propMenu.getProperty("velocidad"))).getImage().getScaledInstance(botonVelocidad.getWidth(), botonVelocidad.getHeight() , DO_NOTHING_ON_CLOSE)));
		
		botonVelocidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(botonVelocidad.isSelected())
					miJuego.cambiarVelocidad(1);
				else
					miJuego.cambiarVelocidad(2);
			}
		});
		

		
		panelPlantas.add(botonVelocidad);
		panelPlantas.add(lblSol);
		
		JLabel lblPrecioP1 = new JLabel(propModo.getProperty("precioGeneradora"));
		lblPrecioP1.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrecioP1.setBounds(61, 48, 46, 14);
		lblPrecioP1.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelPlantas.add(lblPrecioP1);
		
		JLabel lblPrecioP2 = new JLabel(propModo.getProperty("precioDebil"));
		lblPrecioP2.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrecioP2.setBounds(116, 48, 46, 14);
		lblPrecioP2.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelPlantas.add(lblPrecioP2);
		
		JLabel lblPrecioP3 = new JLabel(propModo.getProperty("precioFuerte"));
		lblPrecioP3.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrecioP3.setBounds(169, 48, 46, 14);
		lblPrecioP3.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelPlantas.add(lblPrecioP3);
		
		JLabel lblPrecioP4 = new JLabel(propModo.getProperty("precioTanque"));
		lblPrecioP4.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrecioP4.setBounds(222, 48, 46, 14);
		lblPrecioP4.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelPlantas.add(lblPrecioP4);
		
		lblSolesActuales = new JLabel(""+miJuego.getSoles());
		lblSolesActuales.setHorizontalAlignment(SwingConstants.CENTER);
		lblSolesActuales.setBounds(6, 48, 46, 14);
		lblSolesActuales.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelPlantas.add(lblSolesActuales);

		
		for(int i = 0; i < 6; i++)
			for(int j = 0; j < 9; j++) {
				int color = i+j;
				JLabel lblCelda = new JLabel();
				lblCelda.setBounds(182+74*j, 62+65*i, 74, 65);
				lblCelda.setOpaque(true);
				if((color % 2) == 0 ) {
					if(propModo.getProperty("colorOscuro").equalsIgnoreCase("true"))
						lblCelda.setBackground(new Color(25, 25, 112).darker());
					else
						lblCelda.setBackground(new Color(0, 128, 0));
					lblCelda.setForeground(lblCelda.getBackground());
				}
				else {
					if(propModo.getProperty("colorOscuro").equalsIgnoreCase("true"))
						lblCelda.setBackground(new Color(70, 130, 180).darker());
					else
						lblCelda.setBackground(new Color(50, 205, 50));
					lblCelda.setForeground(lblCelda.getBackground());
				}
				lblCelda.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if(Ventana.this.isFocusable() ) {
							if(!miJuego.getPalaSeleccionada() && miJuego.getPlantaEnEspera() != null && (lblCelda.getComponentCount() == 0 || !lblCelda.getComponent(0).isVisible())){
								if(lblCelda.getComponentCount() == 1) 
									lblCelda.remove(0);
								JLabel lblPlanta = miJuego.getPlantaEnEspera().getEntidadGrafica().getGrafica();
								lblPlanta.setBounds(0, 0, lblPlanta.getWidth(), lblPlanta.getHeight());
								lblCelda.add(lblPlanta);
								miJuego.agregarPlanta(lblCelda.getX(),lblCelda.getY());
								controlarPlantasAComprar();
								miJuego.setPlantaEnEspera(0);
							}
							else if(miJuego.getPalaSeleccionada() && lblCelda.getComponentCount() > 0) {
								miJuego.removerPlanta(lblCelda.getX(), lblCelda.getY());
								lblCelda.removeAll();
								controlarPlantasAComprar();
								miJuego.setPlantaEnEspera(0);
							}
							
						}
					}
					
					public void mouseEntered(MouseEvent e) {
						if(Ventana.this.isFocusable())
							lblCelda.setBackground(lblCelda.getBackground().brighter());
						
					}
					public void mouseExited(MouseEvent e) {
						if(Ventana.this.isFocusable())
							lblCelda.setBackground(lblCelda.getForeground());
					}
				});
				panelModo.add(lblCelda, JLayeredPane.DEFAULT_LAYER);
			}
		
		JLabel lblFondo = new JLabel();
		lblFondo.setIgnoreRepaint(true);
		lblFondo.setBounds(0, 0, 884, 467);
		lblFondo.setIcon(new ImageIcon(new ImageIcon(Ventana.class.getResource(propModo.getProperty("fondo"))).getImage().getScaledInstance(lblFondo.getWidth(), lblFondo.getHeight(), DO_NOTHING_ON_CLOSE)));
		panelModo.add(lblFondo, JLayeredPane.DEFAULT_LAYER);
		
		lblOleadas = new JLabel();
		lblOleadas.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblOleadas.setHorizontalAlignment(SwingConstants.CENTER);
		lblOleadas.setBounds(415, 21, 274, 29);
		if(propModo.getProperty("colorOscuro").equalsIgnoreCase("true"))
			lblOleadas.setForeground(new Color(255,255,255));
		else
			lblOleadas.setForeground(new Color(0,0,0));
		lblOleadas.setVisible(false);
		panelModo.add(lblOleadas, JLayeredPane.PALETTE_LAYER);
		
		lblNivel = new JLabel();
		lblNivel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNivel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNivel.setBounds(415, 21, 274, 29);
		if(propModo.getProperty("colorOscuro").equalsIgnoreCase("true"))
			lblNivel.setForeground(new Color(255,255,255));
		else
			lblNivel.setForeground(new Color(0,0,0));
		lblNivel.setVisible(false);
		panelModo.add(lblNivel,JLayeredPane.PALETTE_LAYER);
		
		controlarPlantasAComprar();
		panelPlantas.add(fondoBotonera);
		repaint();	
	}
	
	public void actualizarGrafica(EntidadGrafica eg) {
		eg.getGrafica().setVisible(true);
		getContentPane().add(eg.getGrafica(), JLayeredPane.PALETTE_LAYER);
		getContentPane().repaint();
		
	}
	public void borrarGrafica(JLabel label) {
		label.setVisible(false);
		getContentPane().remove(label);
		getContentPane().repaint();
	}
	public void gameOver() {
		JLabel lblGameOver = new JLabel();
		lblGameOver.setBounds((this.getWidth()-357)/2, 88, 357, 271);
		lblGameOver.setIcon(new ImageIcon(new ImageIcon(Ventana.class.getResource(propMenu.getProperty("gameOver"))).getImage().getScaledInstance(lblGameOver.getWidth(), lblGameOver.getHeight(), DO_NOTHING_ON_CLOSE)));
		getContentPane().add(lblGameOver, JLayeredPane.POPUP_LAYER);
		this.setFocusable(false);
		botonVolver("GAME OVER!");
		repaint();
		
	}
	public void ganarJuego() {
		JLabel lblGanar= new JLabel();
		lblGanar.setBounds((this.getWidth()-357)/2, 88, 357, 271);
		lblGanar.setIcon(new ImageIcon(new ImageIcon(Ventana.class.getResource(propMenu.getProperty("ganarJuego"))).getImage().getScaledInstance(lblGanar.getWidth(), lblGanar.getHeight(), DO_NOTHING_ON_CLOSE)));
		getContentPane().add(lblGanar, JLayeredPane.POPUP_LAYER);
		this.setFocusable(false);
		botonVolver("GANASTE!");
		repaint();
	}
	
	private void botonVolver(String s) {
		JButton btnVolver = new JButton(s);
		btnVolver.setBounds((this.getWidth()-162)/2, 369, 180, 50);
		btnVolver.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 20));
		btnVolver.setVisible(true);
		this.getContentPane().add(btnVolver, JLayeredPane.POPUP_LAYER);
		
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearPanelMenu();
			}
		});
	}
		
	public void controlarPlantasAComprar() {
		JToggleButton planta1 = (JToggleButton)panelPlantas.getComponent(0);
		JToggleButton planta2 = (JToggleButton)panelPlantas.getComponent(1);
		JToggleButton planta3 = (JToggleButton)panelPlantas.getComponent(2);
		JToggleButton planta4 = (JToggleButton)panelPlantas.getComponent(3);
		JToggleButton botonPala = (JToggleButton)panelPlantas.getComponent(4);
		miJuego.setPlantaEnEspera(0);
		lblSolesActuales.setText(""+miJuego.getSoles());
		planta1.setSelected(false);
		planta2.setSelected(false);
		planta3.setSelected(false);
		planta4.setSelected(false);
		botonPala.setSelected(false);
		planta1.setEnabled(false);
		planta2.setEnabled(false);
		planta3.setEnabled(false);
		planta4.setEnabled(false);
		if(miJuego.getSoles() >= Integer.parseInt(propModo.getProperty("precioTanque"))) 
			planta4.setEnabled(true);
		if(miJuego.getSoles() >= Integer.parseInt(propModo.getProperty("precioFuerte"))) 
			planta3.setEnabled(true);
		if(miJuego.getSoles() >= Integer.parseInt(propModo.getProperty("precioDebil"))) 
			planta2.setEnabled(true);
		if(miJuego.getSoles() >= Integer.parseInt(propModo.getProperty("precioGeneradora")))
			planta1.setEnabled(true);
	}
	
	public void cambiarOleada(int i) {
		if(i != 5)
			lblOleadas.setText("¡Oleada "+i+"!");
		else
			lblOleadas.setText("¡Oleada final!");
		lblOleadas.setVisible(true);
		
		int delay = 3500;
        ActionListener taskPerformer = new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent evt) {
                 lblOleadas.setVisible(false);
           }
        };
	    Timer timer = new Timer(delay, taskPerformer);
	    timer.setRepeats(false);
	    timer.start();
	}
	
	public void cambiarNivel(int i) {
		botonVelocidad.setSelected(false);
		lblNivel.setText("¡Nivel "+i+"!");
		lblNivel.setVisible(true);
		int delay = 3500;
        ActionListener taskPerformer = new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent evt) {
                 lblNivel.setVisible(false);
           }
        };
	    Timer timer = new Timer(delay, taskPerformer);
	    timer.setRepeats(false);
	    timer.start();
	    controlarPlantasAComprar();
	}
	
	private void crearSplashScreen() {
		JPanel splash = new JPanel();
		setContentPane(splash);
		splash.setLayout(null);
		splash.setBackground(new Color(205, 133, 63));
		JLabel img = new JLabel();
		img.setBounds(0, -30, 900, 500);
		img.setIcon(new ImageIcon(new ImageIcon("src/Recursos/splash.png").getImage().getScaledInstance(900, 450, DO_NOTHING_ON_CLOSE)));
		splash.add(img);
	}
	//getters
	
	public Properties getPropertiesModo() {
		return propModo;
	}
	public int getBordeDerecho() {
		return this.getWidth()-10;
	}
	public int getLinea(int i) {
		return i*64; 
	}
	public int getBordeInferior() {
		return this.getHeight();
	}
	public int getFinTablero() {
		return 130;
	}
	public Juego getJuego() {
		return miJuego;
	}
}
