package controller;

import javax.swing.JOptionPane;

import view.EncenderMaquinas;
import view.Inicio;
import view.Progreso;

@SuppressWarnings("unchecked")
public class InicioController extends Controller{
	
	private ProgresoController controller;
	private EncenderMaquinasController encenderController;

	public InicioController(Inicio instance) {
		this.controller = new ProgresoController(Progreso.getInstance());
		this.encenderController = new EncenderMaquinasController(EncenderMaquinas.getInstance());
		this.setView(instance);
		this.getView().setController(this);
	}

	public void displayView() {
		this.getView().display();
	}
	
	public void handleButtonIniciarEnsayos(){
		if (((EncenderMaquinas)encenderController.getView()).getEncendidas()){
			this.controller.setParent(this);
			this.getView().setVisible(Boolean.FALSE);
			this.controller.getView().display();
		} else {
			JOptionPane.showMessageDialog(getView(),"Necesita encener las máquinas para iniciar ensayos");
		}
	}
	
	public void handleButtonFinalizar(){
		System.exit(1);
	}

	public void handleButtonEncenderMaquinas() {
		this.encenderController.setParent(this);
		this.getView().setVisible(Boolean.FALSE);
		this.encenderController.getView().display();
	}

}
