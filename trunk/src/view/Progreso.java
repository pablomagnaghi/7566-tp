package view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;

import controller.ProgresoController;

public class Progreso extends View<ProgresoController> implements ActionListener, PropertyChangeListener{

	/**
	 * 
	 */
	private JProgressBar progressBar;
	private JButton startButton;
	private JTextArea taskOutput;
	private Task task;

	private static final long serialVersionUID = 282453734915689789L;

	private JPanel contentPane;

	private static Progreso instance;

	/**
	 * Create the frame.
	 */
	public Progreso() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		progressBar = new JProgressBar();
		progressBar.setBounds(114, 38, 208, 24);
		contentPane.add(progressBar);
		progressBar.setValue(0);
        progressBar.setStringPainted(true);
		
		startButton = new JButton("Comenzar");
		startButton.setBounds(172, 246, 117, 25);
		contentPane.add(startButton);
		startButton.setActionCommand("start");
        startButton.addActionListener(this);
		
		taskOutput = new JTextArea();
		taskOutput.setBounds(61, 90, 324, 144);
		taskOutput.setEditable(false);
		contentPane.add(taskOutput);
	}

	/**
     * Invoked when the user presses the start button.
     */
    public void actionPerformed(ActionEvent evt) {
        startButton.setEnabled(false);
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        //Instances of javax.swing.SwingWorker are not reusuable, so
        //we create new instances as needed.
        task = new Task();
        task.addPropertyChangeListener(this);
        task.execute();
    }
	
    /**
     * Invoked when task's progress property changes.
     */
    public void propertyChange(PropertyChangeEvent evt) {
        if ("progress" == evt.getPropertyName()) {
            int progress = (Integer) evt.getNewValue();
            progressBar.setValue(progress);
            taskOutput.append(String.format(
                    "Completed %d%% of task.\n", task.getProgress()));
        }
    }
 
    
	public static Progreso getInstance() {
		if (instance == null){
			instance = new Progreso();
		}
		return instance;
	}
	
	 class Task extends SwingWorker<Void, Void> {
	        /*
	         * Main task. Executed in background thread.
	         */
	        @Override
	        public Void doInBackground() {
	            Random random = new Random();
	            int progress = 0;
	            //Initialize progress property.
	            setProgress(0);
	            while (progress < 100) {
	                //Sleep for up to one second.
	                try {
	                    Thread.sleep(random.nextInt(1000));
	                } catch (InterruptedException ignore) {}
	                //Make random progress.
	                progress += random.nextInt(10);
	                setProgress(Math.min(progress, 100));
	            }
	            return null;
	        }
	 
	        /*
	         * Executed in event dispatching thread
	         */
	        @Override
	        public void done() {
	            Toolkit.getDefaultToolkit().beep();
	            startButton.setEnabled(true);
	            setCursor(null); //turn off the wait cursor
	            taskOutput.append("Done!\n");
	        }
	    }
}
