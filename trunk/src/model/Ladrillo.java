package model;

import utils.Constants;
import utils.Utils;
import utils.Utils.Resultado;

public class Ladrillo {
	
	private Integer altura1;
	private Integer ancho1;
	private Integer largo1;
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
			this.setVelocidad(Utils.getRandom(60, 80));
			this.setResDureza(Resultado.BUENO);
		} else if (random < Constants.limiteRegular){
			this.setVelocidad(Utils.getRandom(40, 59));
			this.setResDureza(Resultado.REGULAR);
		} else {
			this.setVelocidad(Utils.getRandom(0, 39));
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
	
	private Double getDimension(Integer dimensionBase, String dimension){
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
	
	public Resultado testearDimensiones(StringBuffer reporte){
		switch (resAltura) {
		case BUENO:
			reporte.append(Constants.dimensionOk("Altura"));
			break;
		case REGULAR:
			reporte.append(Constants.dimensionRegular("Altura"));
			break;
		default:
			reporte.append(Constants.dimensionMal("Altura"));
			break;
		}
		switch (resAncho) {
		case BUENO:
			reporte.append(Constants.dimensionOk("Ancho"));
			break;
		case REGULAR:
			reporte.append(Constants.dimensionRegular("Ancho"));
			break;
		default:
			reporte.append(Constants.dimensionMal("Ancho"));
			break;
		}
		switch (resLargo) {
		case BUENO:
			reporte.append(Constants.dimensionOk("Largo"));
			break;
		case REGULAR:
			reporte.append(Constants.dimensionRegular("Largo"));
			break;
		default:
			reporte.append(Constants.dimensionMal("Largo"));
			break;
		}
		return definirResultadoDimensiones();
	}
	
	private Resultado definirResultadoDimensiones() {
		if (resAncho == Resultado.MALO || resLargo == Resultado.MALO || resAltura == Resultado.MALO){
			return Resultado.MALO;
		} else if (resAncho == Resultado.BUENO || resLargo == Resultado.BUENO || resAltura == Resultado.BUENO){
			return Resultado.BUENO;
		}
		return Resultado.REGULAR;
	}

	private Resultado testearCampo(StringBuffer reporte, Resultado resCampo, String nombreCampo){
		switch (resCampo) {
		case BUENO:
			reporte.append(Constants.campoOK(nombreCampo));
			break;
		case REGULAR:
			reporte.append(Constants.campoRegular(nombreCampo));
			break;
		default:
			reporte.append(Constants.campoMal(nombreCampo));
			break;
		}
		return resCampo;
	}

	public Resultado testearTemperatura(StringBuffer reporte){
		return testearCampo(reporte, resTemperatura, "Temperatura");
	}
	
	public Resultado testearUltraSonido(StringBuffer reporte){
		return testearCampo(reporte, resVelocidad, "Velocidad de ultrasonido");
	}
	
	public Resultado testearDureza(StringBuffer reporte){
		return testearCampo(reporte, resDureza, "Dureza");
	}

	public Integer getAltura1() {
		return altura1;
	}

	public void setAltura1(Integer altura1) {
		this.altura1 = altura1;
	}

	public Integer getAncho1() {
		return ancho1;
	}

	public void setAncho1(Integer ancho1) {
		this.ancho1 = ancho1;
	}

	public Integer getLargo1() {
		return largo1;
	}

	public void setLargo1(Integer largo1) {
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
