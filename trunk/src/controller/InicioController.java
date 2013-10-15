package controller;

import view.Inicio;

@SuppressWarnings("unchecked")
public class InicioController extends Controller{

	public InicioController(Inicio instance) {
		this.setView(instance);
		this.getView().setController(this);
	}

	public void displayView() {
		this.getView().display();
	}
	
	public void handleButtonIniciar(){
		
	}
	
	public void handleButtonConsultar(){
		
	}
	
	public void handleButtonFinalizar(){
		System.exit(1);
	}

}
