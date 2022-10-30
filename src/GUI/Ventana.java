package GUI;

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

@SuppressWarnings("serial")
public class Ventana extends JFrame{

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
		
		JPanel panelPlantas = new JPanel();
		panelPlantas.setBackground(new Color(139, 69, 19));
		panelPlantas.setBounds(0, 0, 684, 52);
		panelDia.add(panelPlantas);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		panelPlantas.add(lblNewLabel_1);
		
		JToggleButton tglbtnNewToggleButton = new JToggleButton("");
		panelPlantas.add(tglbtnNewToggleButton);
		
		JToggleButton tglbtnNewToggleButton_1 = new JToggleButton("");
		panelPlantas.add(tglbtnNewToggleButton_1);
		
		JToggleButton tglbtnNewToggleButton_2 = new JToggleButton("");
		panelPlantas.add(tglbtnNewToggleButton_2);
		
		JPanel panelTablero = new JPanel();
		panelTablero.setBounds(0, 37, 684, 424);
		panelDia.add(panelTablero);
		panelTablero.setLayout(null);
		
		JLabel lblNewLabel = new JLabel();
		ImageIcon fondoDia = new ImageIcon(Toolkit.getDefaultToolkit().getImage("src/Imagenes/FondoDia.png").getScaledInstance(684, 424, 0));
		lblNewLabel.setIcon(fondoDia);
		lblNewLabel.setBounds(0, 0, 684, 424);
		panelTablero.add(lblNewLabel);
		
	}
}
