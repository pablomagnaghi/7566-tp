package handler;

import java.util.HashMap;

import model.Ladrillo;

public class HandlerLadrillos {

	private HashMap<Ladrillo, String> reportesLadrillos;
	
	public HandlerLadrillos(){
		this.reportesLadrillos = new HashMap<Ladrillo, String>();
	}
	
	public void addLadrillo(Ladrillo l, String r){
		this.reportesLadrillos.put(l, r);
	}
	
	public String reporte(){
		StringBuffer reporteL = new StringBuffer();
		for (Ladrillo l : reportesLadrillos.keySet()) {
			reporteL.append(reportesLadrillos.get(l));
		}
		return reporteL.toString();
	}
	
}
