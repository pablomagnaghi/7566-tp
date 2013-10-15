package utils;

public class Constants {

	public static final String salida = "/home/luisep/Escritorio/reporte.txt";
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
	
	public static String notificarFalla(String ensayo){
		return "Ha ocurrido un error que no permite realizar el ensayo de "+ensayo+", chequee la maquinaria";
	}
	public static String resultadoEnsayo(String string, String resultado) {
		return "El ensayo de " + string + " dice que el ladrillo es "+resultado+"\r\n";
	}
	
}
