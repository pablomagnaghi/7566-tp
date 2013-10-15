package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.InicioController;

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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Iniciar Proceso");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getController().handleButtonIniciar();
			}
		});
		btnNewButton.setBounds(127, 30, 195, 60);
		contentPane.add(btnNewButton);
		
		JButton btnConsulta = new JButton("Consulta");
		btnConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getController().handleButtonIniciar();
			}
		});
		btnConsulta.setBounds(127, 102, 195, 65);
		contentPane.add(btnConsulta);
		
		JButton btnFinalizar = new JButton("Finalizar");
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getController().handleButtonFinalizar();
			}
		});
		btnFinalizar.setBounds(127, 179, 195, 66);
		contentPane.add(btnFinalizar);
	}

	public static Inicio getInstance() {
		if (instance == null){
			instance = new Inicio();
		}
		return instance;
	}
}
