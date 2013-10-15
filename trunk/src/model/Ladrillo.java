package model;

import utils.Constants;
import utils.Utils;
import utils.Utils.Resultado;

public class Ladrillo {
	
	private Double altura1;
	private Double ancho1;
	private Double largo1;
	private Double altura2;
	private Double ancho2;
	private Double largo2;
	
	private Integer temperatura;
	private Integer velocidad;
	private Integer dureza;
	
	private Resultado resAltura;
	private Resultado resAncho;
	private Resultado resLargo;
	private Resultado resTemperatura;
	private Resultado resVelocidad;
	private Resultado resDureza;
	
	public Ladrillo(){
		this.setAltura1(Constants.alturaEstandard);
		this.setAncho1(Constants.anchoEstandard);
		this.setLargo1(Constants.largoEstandard);
		this.asignarDimensiones();
		this.asignarTemperatura();
		this.asignarVelocidad();
		this.asignarDureza();
	}

	private void asignarDureza() {
		Integer random = Utils.getRandom(0, 100);
		if (random < Constants.limiteBueno){
			this.setDureza(Utils.getRandom(60, 80));
			this.setResDureza(Resultado.BUENO);
		} else if (random < Constants.limiteRegular){
			this.setDureza(Utils.getRandom(40, 59));
			this.setResDureza(Resultado.REGULAR);
		} else {
			this.setDureza(Utils.getRandom(0, 39));
			this.setResDureza(Resultado.MALO);
		}
	}

	private void asignarVelocidad() {
		Integer random = Utils.getRandom(0, 100);
		if (random < Constants.limiteBueno){
			this.setVelocidad(Utils.getRandom(4001, 6000));
			this.setResVelocidad(Resultado.BUENO);
		} else if (random < Constants.limiteRegular){
			this.setVelocidad(Utils.getRandom(3001, 4000));
			this.setResVelocidad(Resultado.REGULAR);
		} else {
			this.setVelocidad(Utils.getRandom(2000, 3000));
			this.setResVelocidad(Resultado.MALO);
		}
	}

	private void asignarTemperatura() {
		Integer random = Utils.getRandom(0, 100);
		if (random < Constants.limiteBueno){
			this.setTemperatura(Utils.getRandom(21, 40));
			this.setResTemperatura(Resultado.BUENO);
		} else if (random < Constants.limiteRegular){
			this.setTemperatura(Utils.getRandom(6, 20));
			this.setResTemperatura(Resultado.REGULAR);
		} else {
			this.setTemperatura(Utils.getRandom(0, 5));
			this.setResTemperatura(Resultado.MALO);
		}
	}

	private void asignarDimensiones() {
		this.setAltura2(getDimension(this.getAltura1(), Constants.alto));
		this.setAncho2(getDimension(this.getAncho1(), Constants.ancho));
		this.setLargo2(getDimension(this.getLargo1(), Constants.largo));
	}
	
	private Double getDimension(Double dimensionBase, String dimension){
		Integer random = Utils.getRandom(0, 100);
		Double result = dimensionBase.doubleValue();
		Resultado prueba;
		if (random < Constants.limiteBueno){
			Integer randomIncrease = Utils.getRandom(0, 2);
			result += (dimensionBase * randomIncrease /100);
			prueba = Resultado.BUENO;
		} else if (random < Constants.limiteRegular){
			Integer randomIncrease = Utils.getRandom(3, 5);
			result += (dimensionBase * randomIncrease /100);
			prueba = Resultado.REGULAR;
		} else {
			Integer randomIncrease = Utils.getRandom(6, 10);
			result += (dimensionBase * randomIncrease /100);
			prueba = Resultado.MALO;
		}
		if (Constants.alto.equals(dimension)){
			resAltura = prueba;
		}else if (Constants.ancho.equals(dimension)){
			resAncho = prueba;
		} else {
			resLargo = prueba;
		}
		return result;
	}
	
	public Resultado testearDimensiones(StringBuffer reporteDimension, StringBuffer reporte){
		switch (resAltura) {
		case BUENO:
			reporte.append(Constants.resultadoEnsayo("Altura", "BUENO"));
			break;
		case REGULAR:
			reporte.append(Constants.resultadoEnsayo("Altura", "REGULAR"));
			break;
		default:
			reporte.append(Constants.resultadoEnsayo("Altura", "MALO"));
			break;
		}
		this.agregarCaracteristicasAltura(reporte);
		switch (resAncho) {
		case BUENO:
			reporte.append(Constants.resultadoEnsayo("Ancho", "BUENO"));
			break;
		case REGULAR:
			reporte.append(Constants.resultadoEnsayo("Ancho", "REGULAR"));
			break;
		default:
			reporte.append(Constants.resultadoEnsayo("Ancho", "MALO"));
			break;
		}
		this.agregarCaracteristicasAncho(reporte);
		switch (resLargo) {
		case BUENO:
			reporte.append(Constants.resultadoEnsayo("Largo", "BUENO"));
			break;
		case REGULAR:
			reporte.append(Constants.resultadoEnsayo("Largo", "REGULAR"));
			break;
		default:
			reporte.append(Constants.resultadoEnsayo("Largo", "MALO"));
			break;
		}
		this.agregarCaracteristicasLargo(reporte);
		Resultado resultadoDimensiones = definirResultadoDimensiones();
		switch(resultadoDimensiones){
		case BUENO:
			reporteDimension.append(Constants.resultadoEnsayo("Dimensión", "BUENO"));
			reporte.append(Constants.resultadoEnsayo("Dimensión", "BUENO"));
			break;
		case REGULAR:
			reporteDimension.append(Constants.resultadoEnsayo("Dimensión", "REGULAR"));
			reporte.append(Constants.resultadoEnsayo("Dimensión", "REGULAR"));
			break;
		default:
			reporteDimension.append(Constants.resultadoEnsayo("Dimensión", "MALO"));
			reporte.append(Constants.resultadoEnsayo("Dimensión", "MALO"));
			break;
		}
		return resultadoDimensiones;
	}
	
	private void agregarCaracteristicasLargo(StringBuffer reporte) {
		reporte.append("El largo medido para las puntas dio (" + this.largo1 +", " + this.largo2 + ")\r\n");
	}

	private void agregarCaracteristicasAncho(StringBuffer reporte) {
		reporte.append("El ancho medido para las puntas dio (" + this.ancho1 +", " + this.ancho2 + ")\r\n");
	}

	private void agregarCaracteristicasAltura(StringBuffer reporte) {
		reporte.append("La altura medida para las puntas dio (" + this.altura1 +", " + this.altura2 + ")\r\n");
	}

	private Resultado definirResultadoDimensiones() {
		if (resAncho == Resultado.MALO || resLargo == Resultado.MALO || resAltura == Resultado.MALO){
			return Resultado.MALO;
		} else if (resAncho == Resultado.BUENO || resLargo == Resultado.BUENO || resAltura == Resultado.BUENO){
			return Resultado.BUENO;
		}
		return Resultado.REGULAR;
	}

	private Resultado testearCampo(StringBuffer reporteCampo, StringBuffer reporte, Resultado resCampo, String nombreCampo, Integer valor){
		switch (resCampo) {
		case BUENO:
			reporteCampo.append(Constants.resultadoEnsayo(nombreCampo, "BUENO"));
			reporte.append(Constants.resultadoEnsayo(nombreCampo, "BUENO"));
			break;
		case REGULAR:
			reporteCampo.append(Constants.resultadoEnsayo(nombreCampo, "REGULAR"));
			reporte.append(Constants.resultadoEnsayo(nombreCampo, "BUENO"));
			break;
		default:
			reporteCampo.append(Constants.resultadoEnsayo(nombreCampo, "MALO"));
			reporte.append(Constants.resultadoEnsayo(nombreCampo, "BUENO"));
			break;
		}
		reporte.append("La medición de " + nombreCampo + " dio como resultado " + valor + "\r\n");
		return resCampo;
	}

	public Resultado testearTemperatura(StringBuffer reporteTemperatura, StringBuffer reporte){
		return testearCampo(reporteTemperatura, reporte, resTemperatura, "Temperatura", temperatura);
	}
	
	public Resultado testearUltraSonido(StringBuffer reporteUltraSonido, StringBuffer reporte){
		return testearCampo(reporteUltraSonido, reporte, resVelocidad, "Velocidad de ultrasonido", velocidad);
	}
	
	public Resultado testearDureza(StringBuffer reporteDureza, StringBuffer reporte){
		return testearCampo(reporteDureza, reporte, resDureza, "Dureza", dureza);
	}

	public Double getAltura1() {
		return altura1;
	}

	public void setAltura1(Double altura1) {
		this.altura1 = altura1;
	}

	public Double getAncho1() {
		return ancho1;
	}

	public void setAncho1(Double ancho1) {
		this.ancho1 = ancho1;
	}

	public Double getLargo1() {
		return largo1;
	}

	public void setLargo1(Double largo1) {
		this.largo1 = largo1;
	}

	public Double getAltura2() {
		return altura2;
	}

	public void setAltura2(Double altura2) {
		this.altura2 = altura2;
	}

	public Double getAncho2() {
		return ancho2;
	}

	public void setAncho2(Double ancho2) {
		this.ancho2 = ancho2;
	}

	public Double getLargo2() {
		return largo2;
	}

	public void setLargo2(Double largo2) {
		this.largo2 = largo2;
	}

	public Integer getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(Integer temperatura) {
		this.temperatura = temperatura;
	}

	public Integer getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(Integer velocidad) {
		this.velocidad = velocidad;
	}

	public Integer getDureza() {
		return dureza;
	}

	public void setDureza(Integer dureza) {
		this.dureza = dureza;
	}

	public Resultado getResTemperatura() {
		return resTemperatura;
	}

	public void setResTemperatura(Resultado resTemperatura) {
		this.resTemperatura = resTemperatura;
	}

	public Resultado getResVelocidad() {
		return resVelocidad;
	}

	public void setResVelocidad(Resultado resVelocidad) {
		this.resVelocidad = resVelocidad;
	}

	public Resultado getResDureza() {
		return resDureza;
	}

	public void setResDureza(Resultado resDureza) {
		this.resDureza = resDureza;
	}

	public Resultado getResAltura() {
		return resAltura;
	}

	public void setResAltura(Resultado resAltura) {
		this.resAltura = resAltura;
	}

	public Resultado getResAncho() {
		return resAncho;
	}

	public void setResAncho(Resultado resAncho) {
		this.resAncho = resAncho;
	}

	public Resultado getResLargo() {
		return resLargo;
	}

	public void setResLargo(Resultado resLargo) {
		this.resLargo = resLargo;
	}

}
