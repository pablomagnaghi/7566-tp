package view;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;

import controller.EncenderMaquinasController;

public class EncenderMaquinas extends View<EncenderMaquinasController> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5345685340995962184L;
	private static EncenderMaquinas instance;
	private JPanel contentPane;
	private JButton btnEncenderMaquinas;
	private JButton btnVolver;
	private JProgressBar progressBar;
	private Boolean encendidas = Boolean.FALSE;

	/**
	 * Create the frame.
	 */
	public EncenderMaquinas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		progressBar = new JProgressBar();
		progressBar.setBounds(92, 29, 265, 64);
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		contentPane.add(progressBar);
		
		btnEncenderMaquinas = new JButton("Encender Maquinas");
		btnEncenderMaquinas.setBounds(29, 158, 194, 58);
		btnEncenderMaquinas.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					getController().handleButtonEncender();
				}
			});
		contentPane.add(btnEncenderMaquinas);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(288, 158, 117, 58);
		btnVolver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getController().handleButtonVolver();
			}
		});
		contentPane.add(btnVolver);
	}
	
	public static EncenderMaquinas getInstance() {
		if (instance == null){
			instance = new EncenderMaquinas();
		}
		return instance;
	}

	public void notifyEndOfProgress() {
		btnEncenderMaquinas.setEnabled(true);
		btnVolver.setEnabled(true);
        setCursor(null); //turn off the wait cursor
        JOptionPane.showMessageDialog(this,"Maquinas encendidas correctamente");
        this.setEncendidas(Boolean.TRUE);
	}

	public void disableButtons() {
		btnEncenderMaquinas.setEnabled(false);
		btnVolver.setEnabled(false);
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));		
	}

	public void setChanges(int progress, int progress2) {
		  progressBar.setValue(progress2);
	}

	public Boolean getEncendidas() {
		return encendidas;
	}

	public void setEncendidas(Boolean encendidas) {
		this.encendidas = encendidas;
	}

	public void mostrarMensajeMaquinaEncendida() {
        JOptionPane.showMessageDialog(this,"Las máquinas ya están encendidas");
	}
}
