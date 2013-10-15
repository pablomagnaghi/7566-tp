package controller;

import view.Progreso;

public class ProgresoController extends Controller{
	
	@SuppressWarnings("unchecked")
	public ProgresoController(Progreso view){
		this.setView(view);
		this.getView().setController(this);
	}

}
