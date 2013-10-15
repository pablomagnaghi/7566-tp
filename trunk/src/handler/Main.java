package handler;

import java.awt.EventQueue;

import controller.InicioController;
import view.Inicio;

public class Main {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InicioController controller = new InicioController(Inicio.getInstance());
					controller.displayView();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
