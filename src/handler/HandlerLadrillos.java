package handler;

import java.util.HashMap;

import utils.Constants;
import model.Ladrillo;

public class HandlerLadrillos {

	private String reporteGlobal;
	
	private HashMap<Ladrillo, String> reportesLadrillos;
	
	public HandlerLadrillos(){
		this.setReportesLadrillos(new HashMap<Ladrillo, String>());
		this.reporteGlobal = (new String());
	}
	
	public void addLadrillo(Ladrillo l, String r){
		this.getReportesLadrillos().put(l, r);
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

	public String getReporteGlobal() {
		return reporteGlobal;
	}

	public void setReporteGlobal(Integer cantBuenas, Integer cantMalos, Integer cantRegulares) {
		this.reporteGlobal = "El lote actual presenta " + cantBuenas + " unidades buenas, "+
					cantMalos + " unidades malas y " + cantRegulares + " unidades regulares\r\n" + Constants.separador;
	}
	
}
