package view;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import controller.ProgresoController;

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

	/**
	 * Create the frame.
	 */
	public Progreso() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 617, 535);
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
		startButton.setBounds(122, 468, 117, 40);
		contentPane.add(startButton);
		startButton.setActionCommand("start");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(58, 90, 501, 366);
		contentPane.add(scrollPane);
		
		taskOutput = new JTextArea();
		scrollPane.setViewportView(taskOutput);
		
		JButton button = new JButton("Exportar informe");
		button.setBounds(352, 468, 161, 40);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getController().handleButtonExportar();
			}
		});
		contentPane.add(button);
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
        taskOutput.append("Fin del lote de ladrillos!\n");
	}

	public void setChanges(int progress, String string) {
		  progressBar.setValue(progress);
          taskOutput.append(string);
	}
}
