package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.InicioController;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Inicio extends View<InicioController> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3187984413603970112L;
	private JPanel contentPane;
	
	private static Inicio instance;

	/**
	 * Create the frame.
	 */
	public Inicio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 398);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Encender Máquinas");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getController().handleButtonEncenderMaquinas();
			}
		});
		btnNewButton.setBounds(127, 118, 195, 60);
		contentPane.add(btnNewButton);
		
		JButton btnConsulta = new JButton("Iniciar Ensayos");
		btnConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getController().handleButtonIniciarEnsayos();
			}
		});
		btnConsulta.setBounds(127, 190, 195, 65);
		contentPane.add(btnConsulta);
		
		JButton btnFinalizar = new JButton("Finalizar");
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getController().handleButtonFinalizar();
			}
		});
		btnFinalizar.setBounds(127, 267, 195, 66);
		contentPane.add(btnFinalizar);
		
		JLabel lblNewLabel = new JLabel("Sistema de control de calidad");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		lblNewLabel.setBounds(54, 27, 349, 79);
		contentPane.add(lblNewLabel);
	}

	public static Inicio getInstance() {
		if (instance == null){
			instance = new Inicio();
		}
		return instance;
	}
}
