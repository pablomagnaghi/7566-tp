package view;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;

import controller.EncenderMaquinasController;

import javax.swing.JLabel;

import utils.Constants;

import java.awt.Font;
import java.io.File;
import java.io.IOException;

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
		setBounds(100, 100, 648, 473);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		progressBar = new JProgressBar();
		progressBar.setBounds(95, 64, 424, 64);
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		contentPane.add(progressBar);
		
		btnEncenderMaquinas = new JButton("Encender Maquinas");
		btnEncenderMaquinas.setBounds(71, 372, 194, 58);
		btnEncenderMaquinas.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					getController().handleButtonEncender();
				}
			});
		contentPane.add(btnEncenderMaquinas);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(429, 372, 117, 58);
		btnVolver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getController().handleButtonVolver();
			}
		});
		contentPane.add(btnVolver);
		
		JLabel lblEncenderMquinas = new JLabel("Encender Máquinas");
		lblEncenderMquinas.setFont(new Font("Dialog", Font.BOLD, 17));
		lblEncenderMquinas.setBounds(225, 12, 194, 27);
		contentPane.add(lblEncenderMquinas);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(38, 140, 560, 220);
		contentPane.add(lblNewLabel);
		Icon icon = this.setImageToLabel(Constants.ladrilloMaquinas, 600, 300);
		lblNewLabel.setIcon(icon);
	}
	
	public static EncenderMaquinas getInstance() {
		if (instance == null){
			instance = new EncenderMaquinas();
		}
		return instance;
	}
	
	private Icon setImageToLabel(String path, Integer width, Integer height) {
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File(path));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		ImageIcon imageIcon = new ImageIcon(img.getScaledInstance(width, height,
		        Image.SCALE_SMOOTH));
		return imageIcon;
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
