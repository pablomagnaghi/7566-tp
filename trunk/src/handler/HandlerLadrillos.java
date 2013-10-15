package handler;

import java.util.HashMap;

import model.Ladrillo;

public class HandlerLadrillos {

	private HashMap<Ladrillo, String> reportesLadrillos;
	
	public HandlerLadrillos(){
		this.setReportesLadrillos(new HashMap<Ladrillo, String>());
	}
	
	public void addLadrillo(Ladrillo l, String r){
		this.getReportesLadrillos().put(l, r);
	}
	
	public String reporte(){
		StringBuffer reporteL = new StringBuffer();
		for (Ladrillo l : getReportesLadrillos().keySet()) {
			reporteL.append(getReportesLadrillos().get(l));
		}
		return reporteL.toString();
	}
	
	public Integer getCantLadrillos(){
		return getReportesLadrillos().size();
	}

	public HashMap<Ladrillo, String> getReportesLadrillos() {
		return reportesLadrillos;
	}

	public void setReportesLadrillos(HashMap<Ladrillo, String> reportesLadrillos) {
		this.reportesLadrillos = reportesLadrillos;
	}
	
}
