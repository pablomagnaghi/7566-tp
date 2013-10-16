package controller;

import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Random;

import javax.swing.SwingWorker;

import view.EncenderMaquinas;

public class EncenderMaquinasController extends Controller implements PropertyChangeListener{
	
	class Task extends SwingWorker<Void, Void> {
        /*
         * Main task. Executed in background thread.
         */
        @Override
        public Void doInBackground() {
            Random random = new Random();
            int progress = 0;
            setProgress(0);
            while (progress < 100) {
                try {
                    Thread.sleep(random.nextInt(1000));
                } catch (InterruptedException ignore) {}
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
            ((EncenderMaquinas)getView()).notifyEndOfProgress();
        }
    }

	private Task task;
	private InicioController controller;
	
	@SuppressWarnings("unchecked")
	public EncenderMaquinasController(EncenderMaquinas view){
		this.setView(view);
		this.getView().setController(this);
	}
	
	/**
     * Invoked when the user presses the start button.
     */
    public void handleButtonEncender () {
    	EncenderMaquinas view = ((EncenderMaquinas)getView());
    	if (!view.getEncendidas()){
    		view.disableButtons();
    		task = new Task();
    		task.addPropertyChangeListener(this);
    		task.execute();
    	}
    	else {
            view.mostrarMensajeMaquinaEncendida();
    	}
    }
	
    /**
     * Invoked when task's progress property changes.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("progress" == evt.getPropertyName()) {
            int progress = (Integer) evt.getNewValue();
        	((EncenderMaquinas)getView()).setChanges(progress, task.getProgress());
        }
    }

	public void handleButtonVolver() {
		this.getView().setVisible(Boolean.FALSE);
		this.controller.getView().display();
	}

	public void setParent(InicioController inicioController) {
		this.controller = inicioController;
	}

}
