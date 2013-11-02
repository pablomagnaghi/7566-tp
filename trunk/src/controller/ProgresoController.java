package controller;

import handler.HandlerLadrillos;

import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import model.Ladrillo;
import utils.Constants;
import utils.Utils;
import utils.Utils.Ensayo;
import utils.Utils.Resultado;
import view.Graficas;
import view.Progreso;

public class ProgresoController extends Controller implements PropertyChangeListener{

	private Task task;
	HandlerLadrillos handler;
	private GraficasController graficasController;
	private Map<Resultado, Integer> cantLadrillos;
	private Map<Ladrillo, HashMap<Ensayo, Resultado>> estadisticasBarra;
	private Map<Ensayo, Map<Resultado, Integer>> estadisticasEnsayo;
	
	class Task extends SwingWorker<Void, Void> {

		private String message;

		@Override
		public Void doInBackground() {
			int progress = 0;
			setProgress(0);
			handler = new HandlerLadrillos();
			reiniciarEstadisticas();
			try {
				while(handler.getCantLadrillos() < 10){
					StringBuffer reporte = new StringBuffer();
					StringBuffer reporteDimension = new StringBuffer();
					StringBuffer reporteTemperatura = new StringBuffer();
					StringBuffer reporteUltraSonido = new StringBuffer();
					StringBuffer reporteDureza = new StringBuffer();
					Ladrillo l = new Ladrillo(handler.getCantLadrillos()+1);
					HashMap<Ensayo, Resultado> estadisticas = new HashMap<Ensayo, Resultado>();
					Resultado resultadoDimensiones = l.testearDimensiones(reporteDimension, reporte);
					estadisticas.put(Ensayo.DIMENSION, resultadoDimensiones);
					asignarResultadoEnsayo(Ensayo.DIMENSION, resultadoDimensiones);
					Resultado resultadoTemperatura = l.testearTemperatura(reporteTemperatura, reporte);
					estadisticas.put(Ensayo.TEMPERATURA, resultadoTemperatura);
					asignarResultadoEnsayo(Ensayo.TEMPERATURA, resultadoTemperatura);
					Resultado resultadoUltraSonido = l.testearUltraSonido(reporteUltraSonido, reporte);
					estadisticas.put(Ensayo.VELOCIDAD, resultadoUltraSonido);
					asignarResultadoEnsayo(Ensayo.VELOCIDAD, resultadoUltraSonido);
					Resultado resultadoDureza = l.testearDureza(reporteDureza, reporte);
					estadisticas.put(Ensayo.DUREZA, resultadoDureza);
					asignarResultadoEnsayo(Ensayo.DUREZA, resultadoDureza);
					estadisticasBarra.put(l, estadisticas);
					while (progress != 100){
						Thread.sleep(3000);
						Boolean error = Boolean.FALSE;
						Integer limite = progress+25;
						String test;
						progress++;
						if (progress < 25){
							test = Constants.dimension;
							setProgress(progress);
							setMessage("Ladrillo nro " + (handler.getCantLadrillos()+1) + "\r\n" + "Realizando Test de Dimensión...\r\n");
						} else if (progress < 50 && progress > 25){
							test = Constants.temperatura;
							setProgress(progress);
							setMessage("Realizando Test de Temperatura...\r\n");
						} else if (progress < 75 && progress > 50){
							test = Constants.velocidad;
							setProgress(progress);
							setMessage("Realizando Test de Ultra Sonido...\r\n");
						} else {
							test = Constants.dureza;
							setProgress(progress);
							setMessage("Realizando Test de Dureza...\r\n");
						}
						setImagenLadrillo(test);
						setImagenSemaforo(null);
						while(progress < limite){
							Random random = new Random();
							if (!error){
								Integer randomFalla = Utils.getRandom(0, 100);
								if (randomFalla > Constants.limiteFalla){
									JOptionPane.showMessageDialog(getView(),Constants.notificarFalla(test));
									error = Boolean.TRUE;
								}
							}
							Thread.sleep(random.nextInt(Constants.tiempoSimulacion));
							progress += Utils.getRandom(0, 2);
							if (progress > limite){
								progress = limite;
							}
							setProgress(progress);
							setMessage("");
						}
						if (progress == 25){
							setProgress(progress);
							setMessage(reporteDimension.toString() + Constants.continuar);
							setImagenSemaforo(resultadoDimensiones);
						} else if (progress == 50){
							setProgress(progress);
							setMessage(reporteTemperatura.toString() + Constants.continuar);
							setImagenSemaforo(resultadoTemperatura);
						} else if (progress == 75){
							setProgress(progress);
							setMessage(reporteUltraSonido.toString() + Constants.continuar);
							setImagenSemaforo(resultadoUltraSonido);
						} else {
							setProgress(progress);
							setMessage(reporteDureza.toString() + Constants.continuarParaVerTotal);
							setImagenSemaforo(resultadoDureza);
						}
					}
					Thread.sleep(1000);

					String resultadoFinal = (Utils.definirResultadoLadrillo(resultadoDimensiones, resultadoTemperatura, 
							resultadoUltraSonido, resultadoDureza));
					reporteDureza.append(resultadoFinal);
					reporteDureza.append("Fin ladrillo " + (handler.getCantLadrillos()+1) + "\r\n");
					reporteDureza.append(Constants.separador);
					handler.addLadrillo(l, reporte.toString());
					setImagenLadrillo(Constants.ladrillo);
					setImagenSemaforo(null);
					Thread.sleep(2000);
					Resultado resultadoLadrillo = (Utils.definirResultado(resultadoDimensiones, resultadoTemperatura, 
							resultadoUltraSonido, resultadoDureza));
					if (resultadoLadrillo == Resultado.BUENO){
						setProgress(progress);
						setMessage("Ladrillo => BUENO\r\n");	
						Integer cantBuenos = cantLadrillos.get(Resultado.BUENO);
						cantBuenos++;
						cantLadrillos.put(Resultado.BUENO, cantBuenos);
					} else if (resultadoLadrillo == Resultado.MALO){
						setProgress(progress);
						setMessage("Ladrillo => MALO\r\n");
						Integer cantMalos = cantLadrillos.get(Resultado.MALO);
						cantMalos++;
						cantLadrillos.put(Resultado.MALO, cantMalos);
					} else {
						setProgress(progress);
						setMessage("Ladrillo => REGULAR\r\n");
						Integer cantReg = cantLadrillos.get(Resultado.REGULAR);
						cantReg++;
						cantLadrillos.put(Resultado.REGULAR, cantReg);
					}
					setImagenSemaforo(resultadoLadrillo);
					progress = 0;
					setProgress(progress);
					Thread.sleep(1000);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			setImagenLadrillo(Constants.ladrilloFinal);
			setImagenSemaforo(null);
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
	
	private void asignarResultadoEnsayo(Ensayo dimension,	Resultado resultadoDimensiones) {
		Map<Resultado, Integer> map = this.estadisticasEnsayo.get(dimension);
		Integer integer = map.get(resultadoDimensiones);
		integer = integer+ 1;
		map.put(resultadoDimensiones, integer);
		this.estadisticasEnsayo.put(dimension, map);
	}


	@SuppressWarnings("unchecked")
	public ProgresoController(Progreso view){
		this.setView(view);
		this.getView().setController(this);
		this.reiniciarEstadisticas();
	}

	private void reiniciarEstadisticas(){
		this.cantLadrillos = new HashMap<Resultado, Integer>();
		this.cantLadrillos.put(Resultado.BUENO, 0);
		this.cantLadrillos.put(Resultado.MALO, 0);
		this.cantLadrillos.put(Resultado.REGULAR, 0);
		this.estadisticasBarra = new HashMap<Ladrillo, HashMap<Ensayo, Resultado>>();
		this.estadisticasEnsayo = new HashMap<Ensayo, Map<Resultado, Integer>>();
		
		Map<Resultado, Integer> map = new HashMap<Resultado, Integer>();
		Map<Resultado, Integer> map2 = new HashMap<Resultado, Integer>();
		Map<Resultado, Integer> map3 = new HashMap<Resultado, Integer>();
		Map<Resultado, Integer> map4 = new HashMap<Resultado, Integer>();
		
		map.put(Resultado.BUENO, 0);
		map.put(Resultado.MALO, 0);
		map.put(Resultado.REGULAR, 0);
		
		map2.put(Resultado.BUENO, 0);
		map2.put(Resultado.MALO, 0);
		map2.put(Resultado.REGULAR, 0);
		
		map3.put(Resultado.BUENO, 0);
		map3.put(Resultado.MALO, 0);
		map3.put(Resultado.REGULAR, 0);
		
		map4.put(Resultado.BUENO, 0);
		map4.put(Resultado.MALO, 0);
		map4.put(Resultado.REGULAR, 0);
		
		this.estadisticasEnsayo.put(Ensayo.DIMENSION, map);
		this.estadisticasEnsayo.put(Ensayo.DUREZA, map2);
		this.estadisticasEnsayo.put(Ensayo.TEMPERATURA, map3);
		this.estadisticasEnsayo.put(Ensayo.VELOCIDAD, map4);

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

	public void handleButtonExportar() {
		if (handler == null){
			JOptionPane.showMessageDialog(getView(),"No hay datos para exportar");
		} else {
			try {
				FileWriter fw = new FileWriter(new File(Constants.salida));
				fw.write(handler.getReporteGlobal());
				Integer i = 1;
				for (Ladrillo l: handler.getReportesLadrillos().keySet()) {
					String string = handler.getReportesLadrillos().get(l);
					fw.write("Ladrillo nro " + i + "\r\n");
					fw.write(string);
					fw.write(Constants.separador);
					i++;
				}
				fw.close();
				JOptionPane.showMessageDialog(getView(),"Datos exportados en: " + Constants.salida);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void handleButtonGraficar() {
		Graficas graficas = new Graficas(this.cantLadrillos, this.estadisticasEnsayo);
		this.graficasController = new GraficasController(graficas);
		graficas.setController(this.graficasController);
		this.getView().setVisible(Boolean.FALSE);
		this.graficasController.getView().display();		
	}

	private void setImagenLadrillo(String test) {
		Progreso view = (Progreso)this.getView();
		if (Constants.dureza.equals(test)){
			view.setImagenLadrillo(Constants.ladrilloDureza);
		} else if (Constants.dimension.equals(test)){
			view.setImagenLadrillo(Constants.ladrilloDimension);
		} else if (Constants.temperatura.equals(test)){
			view.setImagenLadrillo(Constants.ladrilloTemperatura);
		} else if (Constants.velocidad.equals(test)){
			view.setImagenLadrillo(Constants.ladrilloVelocidad);
		} else if (Constants.ladrillo.equals(test)) {
			view.setImagenLadrillo(Constants.ladrillo);
		} else {
			view.setImagenLadrillo(Constants.ladrilloFinal);
	
		}
	}

	private void setImagenSemaforo(Resultado resultado) {
		Progreso view = (Progreso)this.getView();
		if (resultado == Resultado.BUENO){
			view.setImagenSemaforo(Constants.semaforoVerde);
		} else if (resultado == Resultado.MALO){
			view.setImagenSemaforo(Constants.semaforoRojo);
		} else if (resultado == Resultado.REGULAR){
			view.setImagenSemaforo(Constants.semaforoAmarillo);
		} else {
			view.setImagenSemaforo(Constants.semaforoApagado);
		}
	}

}
