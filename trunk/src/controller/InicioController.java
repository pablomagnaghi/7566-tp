package controller;

import view.Inicio;
import view.Progreso;

@SuppressWarnings("unchecked")
public class InicioController extends Controller{
	
	private ProgresoController controller;

	public InicioController(Inicio instance) {
		this.controller = new ProgresoController(Progreso.getInstance());
		this.setView(instance);
		this.getView().setController(this);
	}

	public void displayView() {
		this.getView().display();
	}
	
	public void handleButtonIniciar(){
		this.getView().setVisible(Boolean.FALSE);
		this.controller.getView().display();
	}
	
	public void handleButtonFinalizar(){
		System.exit(1);
	}

}
