package controller;

import handler.HandlerLadrillos;

import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Random;

import javax.swing.SwingWorker;

import model.Ladrillo;
import utils.Utils;
import utils.Utils.Resultado;
import view.Progreso;

public class ProgresoController extends Controller implements PropertyChangeListener{
	
	private Task task;
	
	class Task extends SwingWorker<Void, Void> {
		
		private String message;
		
        /*
         * Main task. Executed in background thread.
         */
        @Override
        public Void doInBackground() {
            Random random = new Random();
            int progress = 0;
            setProgress(0);
            HandlerLadrillos handler = new HandlerLadrillos();
            while (handler.getCantLadrillos() < 10) {
            	try {
            		StringBuffer reporte = new StringBuffer("Ladrillo nro " + handler.getCantLadrillos());
            		StringBuffer reporteDimension = new StringBuffer();
            		StringBuffer reporteTemperatura = new StringBuffer();
            		StringBuffer reporteUltraSonido = new StringBuffer();
            		StringBuffer reporteDureza = new StringBuffer();
            		
            		Ladrillo l = new Ladrillo();
					Resultado resultadoDimensiones = l.testearDimensiones(reporteDimension );
            		progress += 2.5;
            		Thread.sleep(random.nextInt(100));
            		setProgress(progress);
            		setMessage(reporteDimension.toString());
            		reporte.append(reporteDimension);

					Resultado resultadoTemperatura = l.testearTemperatura(reporteTemperatura);
            		progress += 2.5;
            		Thread.sleep(random.nextInt(100));
            		setProgress(progress);
            		setMessage(reporteTemperatura.toString());
            		reporte.append(reporteTemperatura);

					Resultado resultadoUltraSonido = l.testearUltraSonido(reporteUltraSonido);
            		progress += 2.5;
            		Thread.sleep(random.nextInt(100));
            		setProgress(progress);
            		setMessage(reporteUltraSonido.toString());
            		reporte.append(reporteUltraSonido);

					Resultado resultadoDureza = l.testearDureza(reporteDureza);
            		progress += 2.5;
            		Thread.sleep(random.nextInt(100));
            		setProgress(progress);
            		setMessage(reporteDureza.toString());
            		reporte.append(reporteDureza);
            		
            		reporte.append(Utils.definirResultadoLadrillo(resultadoDimensiones, resultadoTemperatura, 
            				resultadoUltraSonido, resultadoDureza));
            		handler.addLadrillo(l, reporte.toString());
            		
            	} catch (InterruptedException ignore) {}
            }
            return null;
        }
 
        /*
         * Executed in event dispatching thread
         */
        @Override
        public void done() {
            Toolkit.getDefaultToolkit().beep();
            ((Progreso)getView()).notifyEndOfProgress();
        }
        
		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
    }
	
	@SuppressWarnings("unchecked")
	public ProgresoController(Progreso view){
		this.setView(view);
		this.getView().setController(this);
	}
	
	/**
     * Invoked when the user presses the start button.
     */
    public void handleButtonIniciar () {
    	((Progreso)getView()).disableButtons();
        task = new Task();
        task.addPropertyChangeListener(this);
        task.execute();
    }
	
    /**
     * Invoked when task's progress property changes.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("progress" == evt.getPropertyName()) {
            int progress = (Integer) evt.getNewValue();
        	((Progreso)getView()).setChanges(progress, task.getMessage());
        }
    }

}
