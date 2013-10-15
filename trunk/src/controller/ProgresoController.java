package controller;

import handler.HandlerLadrillos;

import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import model.Ladrillo;
import utils.Constants;
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
            		StringBuffer reporte = new StringBuffer();
            		StringBuffer reporteDimension = new StringBuffer("Ladrillo nro " + handler.getCantLadrillos() + "\r\n");
            		StringBuffer reporteTemperatura = new StringBuffer();
            		StringBuffer reporteUltraSonido = new StringBuffer();
            		StringBuffer reporteDureza = new StringBuffer();
            		
            		Ladrillo l = new Ladrillo();
            		Integer dimensionRandom = Utils.getRandom(0, 100);
            		if (dimensionRandom > Constants.limiteFalla){
            			JOptionPane.showMessageDialog(getView(),Constants.notificarFalla("Dimension"));
            		}
					Resultado resultadoDimensiones = l.testearDimensiones(reporteDimension );
            		progress += 3;
            		Thread.sleep(random.nextInt(Constants.tiempoSimulacion));
            		setProgress(progress);
            		setMessage(reporteDimension.toString());
            		reporte.append(reporteDimension);

            		Integer temperaturaRandom = Utils.getRandom(0, 100);
            		if (temperaturaRandom > Constants.limiteFalla){
            			JOptionPane.showMessageDialog(getView(),Constants.notificarFalla("Temperatura"));
            		}
					Resultado resultadoTemperatura = l.testearTemperatura(reporteTemperatura);
            		progress += 3;
            		Thread.sleep(random.nextInt(Constants.tiempoSimulacion));
            		setProgress(progress);
            		setMessage(reporteTemperatura.toString());
            		reporte.append(reporteTemperatura);
            		
            		Integer ultraSonidoRandom = Utils.getRandom(0, 100);
            		if (ultraSonidoRandom > Constants.limiteFalla){
            			JOptionPane.showMessageDialog(getView(),Constants.notificarFalla("UltraSonido"));
            		}
					Resultado resultadoUltraSonido = l.testearUltraSonido(reporteUltraSonido);
            		progress += 3;
            		Thread.sleep(random.nextInt(Constants.tiempoSimulacion));
            		setProgress(progress);
            		setMessage(reporteUltraSonido.toString());
            		reporte.append(reporteUltraSonido);

            		Integer durezaRandom = Utils.getRandom(0, 100);
            		if (durezaRandom > Constants.limiteFalla){
            			JOptionPane.showMessageDialog(getView(),Constants.notificarFalla("Dureza"));
            		}
					Resultado resultadoDureza = l.testearDureza(reporteDureza);
            		progress += 1;
            		Thread.sleep(random.nextInt(Constants.tiempoSimulacion));
            		String resultadoFinal = (Utils.definirResultadoLadrillo(resultadoDimensiones, resultadoTemperatura, 
            				resultadoUltraSonido, resultadoDureza));
            		reporteDureza.append(resultadoFinal);
            		reporte.append(reporteDureza);

            		reporteDureza.append("Fin ladrillo " + handler.getCantLadrillos() + "\r\n");
            		reporteDureza.append(Constants.separador);
            		setProgress(progress);
            		setMessage(reporteDureza.toString());
            		
            		handler.addLadrillo(l, reporte.toString());
            		Thread.sleep(random.nextInt(Constants.tiempoSimulacion));
            		
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
