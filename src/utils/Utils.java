package utils;

public class Utils {
	
	public static Integer getRandom(Integer min,Integer max){
		return (int) ((Math.random()*(max-min))+min);
	}
	
	public enum Resultado {
	    MALO, REGULAR, BUENO 
	}
	
	public static String definirResultadoLadrillo(Resultado dimension, Resultado temperatura, 
			Resultado velocidad, Resultado dureza){
		Integer cantBuenas = getCant(dimension, temperatura, 
				 velocidad, dureza, Resultado.BUENO);
		Integer cantMalas = getCant(dimension, temperatura, 
				 velocidad, dureza, Resultado.MALO);
		if (cantMalas > 1 ){
			return "El ladrillo se considera MALO";
		} else if (cantBuenas > 1 && cantMalas == 0){
			return "El ladrillo se considera BUENO";
		} else {
			return "El ladrillo se considera REGULAR";
		}
	}
	
	private static Integer getCant(Resultado dimension, Resultado temperatura, 
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