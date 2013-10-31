package utils;

public class Constants {

	public static final String salida = "/home/luisep/Escritorio/reporte.txt";
	public static final String ladrilloDimension = "/home/luisep/Escritorio/ladrillos/dimension.jpg";
	public static final String ladrilloDureza = "/home/luisep/Escritorio/ladrillos/dureza.jpg";
	public static final String ladrilloTemperatura = "/home/luisep/Escritorio/ladrillos/temperatura.jpg";
	public static final String ladrilloVelocidad = "/home/luisep/Escritorio/ladrillos/ultrasonido.jpg";
	public static final String ladrilloFinal = "/home/luisep/Escritorio/ladrillos/final.jpg";
	
	public static final String semaforoApagado = "/home/luisep/Escritorio/semaforos/apagado.jpg";
	public static final String semaforoVerde = "/home/luisep/Escritorio/semaforos/verde.jpg";
	public static final String semaforoRojo = "/home/luisep/Escritorio/semaforos/rojo.jpg";
	public static final String semaforoAmarillo = "/home/luisep/Escritorio/semaforos/amarillo.jpg";
	
	
	public static Double alturaEstandard = 27.00;
	public static Double anchoEstandard = 19.00;
	public static Double largoEstandard = 20.00;
	public static Integer limiteBueno = 80;
	public static Integer limiteRegular = 95;
	public static Integer limiteFalla = 98;
	public static String alto = "Alto";
	public static String ancho = "Ancho";
	public static String largo = "Largo";
	public static int tiempoSimulacion = 100;
	public static String separador = "-------------------\r\n";
	public static String ladrilloBueno = "El ladrillo se considera BUENO\r\n";
	public static String ladrilloMalo = "El ladrillo se considera MALO\r\n";
	public static String ladrilloRegular = "El ladrillo se considera REGULAR\r\n";
	public static String dimension = "Dimensión";
	public static String temperatura = "Temperatura";
	public static String velocidad = "Velocidad";
	public static String dureza = "Dureza";
	public static String continuar = "Fin del ensayo..\r\n";
	public static String continuarParaVerTotal = "Fin del proceso, espere para ver el resultado final..\r\n";
	
	
	public static String notificarFalla(String ensayo){
		return "Ha ocurrido un error que no permite realizar el ensayo de "+ensayo+", chequee la maquinaria";
	}
	public static String resultadoEnsayo(String string, String resultado) {
		return string + " => "+resultado+"\r\n";
	}
	
}
