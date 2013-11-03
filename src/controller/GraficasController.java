package controller;

import view.Graficas;

public class GraficasController extends Controller{

	private ProgresoController controller;
	
	public GraficasController(Graficas instance, ProgresoController controller) {
		setView(instance);
		this.controller = controller;
	}

	public void handleButtonVolver() {
		this.getView().setVisible(Boolean.FALSE);
		this.controller.getView().setVisible(Boolean.TRUE);
	}

}
