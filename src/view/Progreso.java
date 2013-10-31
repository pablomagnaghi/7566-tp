package view;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import controller.ProgresoController;

import javax.swing.JLabel;

public class Progreso extends View<ProgresoController>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 282453734915689789L;

	private JProgressBar progressBar;
	private JButton startButton;
	private JTextArea taskOutput;
	private JPanel contentPane;

	private static Progreso instance;
	private JButton btnVolver;

	private JLabel ladrillosLabel;

	private JLabel semaforosLabel;

	/**
	 * Create the frame.
	 */
	public Progreso() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 647, 590);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		progressBar = new JProgressBar();
		progressBar.setBounds(122, 29, 349, 48);
		contentPane.add(progressBar);
		progressBar.setValue(0);
        progressBar.setStringPainted(true);
		
		startButton = new JButton("Comenzar");
		startButton.setBounds(40, 501, 117, 40);
		contentPane.add(startButton);
		startButton.setActionCommand("start");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(58, 90, 501, 199);
		contentPane.add(scrollPane);
		
		taskOutput = new JTextArea();
		scrollPane.setViewportView(taskOutput);
		
		JButton button = new JButton("Exportar informe");
		button.setBounds(210, 501, 161, 40);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getController().handleButtonExportar();
			}
		});
		contentPane.add(button);
		
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(416, 501, 161, 40);
		btnVolver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getController().handleButtonVolver();
			}
		});
		contentPane.add(btnVolver);
		
		ladrillosLabel = new JLabel();
		ladrillosLabel.setBounds(40, 300, 353, 175);
		contentPane.add(ladrillosLabel);
		
		semaforosLabel = new JLabel();
		semaforosLabel.setBounds(406, 300, 170, 175);
		contentPane.add(semaforosLabel);
        startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getController().handleButtonIniciar();
			}
		});
	}

	public static Progreso getInstance() {
		if (instance == null){
			instance = new Progreso();
		}
		return instance;
	}

	public void disableButtons() {
		taskOutput.setText("");
		startButton.setEnabled(false);
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));		
	}

	public void notifyEndOfProgress() {
        startButton.setEnabled(true);
        setCursor(null); //turn off the wait cursor
        taskOutput.append("Fin de evaluación de ladrillo!\n");
	}

	public void setChanges(int progress, String string) {
		  progressBar.setValue(progress);
          taskOutput.append(string);
	}

	public void setImagenLadrillo(String ladrillo) {
		Icon icon = this.setImageToLabel(ladrillo, 350, 200);
		this.ladrillosLabel.setIcon(icon);
	}

	public void setImagenSemaforo(String semaforo) {
		Icon icon = this.setImageToLabel(semaforo, 150, 200);
		this.semaforosLabel.setIcon(icon);
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
}
