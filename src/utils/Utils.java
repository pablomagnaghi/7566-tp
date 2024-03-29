package utils;

public class Utils {
	
	public static Integer getRandom(Integer min,Integer max){
		return (int) ((Math.random()*(max-min))+min);
	}
	
	public enum Resultado {
	    MALO, REGULAR, BUENO 
	}
	
	public enum Ensayo {
	    DIMENSION, TEMPERATURA, VELOCIDAD, DUREZA 
	}
	
	public static String definirResultadoLadrillo(Resultado dimension, Resultado temperatura, 
			Resultado velocidad, Resultado dureza){
		Integer cantBuenas = getCant(dimension, temperatura, 
				 velocidad, dureza, Resultado.BUENO);
		Integer cantMalas = getCant(dimension, temperatura, 
				 velocidad, dureza, Resultado.MALO);
		if (cantMalas > 1 ){
			return Constants.ladrilloMalo;
		} else if (cantBuenas > 1 && cantMalas == 0){
			return Constants.ladrilloBueno;
		} else {
			return Constants.ladrilloRegular;
		}
	}
	
	public static Resultado definirResultado(Resultado dimension, Resultado temperatura, 
			Resultado velocidad, Resultado dureza){
		Integer cantBuenas = getCant(dimension, temperatura, 
				 velocidad, dureza, Resultado.BUENO);
		Integer cantMalas = getCant(dimension, temperatura, 
				 velocidad, dureza, Resultado.MALO);
		if (cantMalas > 1 ){
			return Resultado.MALO;
		} else if (cantBuenas > 1 && cantMalas == 0){
			return Resultado.BUENO;
		} else {
			return Resultado.REGULAR;
		}
	}
	
	
	public static Integer getCant(Resultado dimension, Resultado temperatura, 
			Resultado velocidad, Resultado dureza, Resultado referencia){
		Integer cant = 0;
		if (dimension == referencia){
			cant++;
		}
		if (temperatura == referencia){
			cant++;
		}
		if (velocidad == referencia){
			cant++;
		}
		if (dureza == referencia){
			cant++;
		}
		return cant;
	}
	
}
