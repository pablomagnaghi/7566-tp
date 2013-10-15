package view;

import javax.swing.JFrame;

import controller.Controller;

public abstract class View<C extends Controller> extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -404819740792466309L;
	private C controller;
	
	public C getController() {
		return controller;
	}

	public void setController(C controller) {
		this.controller = controller;
	}

	public void display() {
		this.setVisible(Boolean.TRUE);
	}

}
