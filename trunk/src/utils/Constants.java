package utils;

public class Constants {

	public static Integer alturaEstandard = 27;
	public static Integer anchoEstandard = 19;
	public static Integer largoEstandard = 20;
	public static Integer limiteBueno = 80;
	public static Integer limiteRegular = 95;
	public static Integer limiteFalla = 98;
	public static String alto = "Alto";
	public static String ancho = "Ancho";
	public static String largo = "Largo";
	public static int tiempoSimulacion = 1000;
	public static String separador = "-------------------\r\n";
	
	public static String notificarFalla(String ensayo){
		return "Ha ocurrido un error que no permite realizar el ensayo de "+ensayo+", chequee la maquinaria";
	}
	public static String dimensionOk(String string) {
		return "El campo " + string + " esta correcto en sus dimensiones\r\n";
	}
	public static String dimensionRegular(String string) {
		return "El campo " + string + " presenta algunas irregularidades en sus dimensiones\r\n";
	}
	public static String dimensionMal(String string) {
		return "El campo " + string + " no ha pasado la prueba de dimensiones\r\n";
	}
	public static String campoOK(String nombreCampo) {
		return "Los resultados del ensayo de " + nombreCampo + " han resultado satisfactorios\r\n" ;
	}
	public static Object campoRegular(String nombreCampo) {
		return "Los resultados del ensayo de " + nombreCampo + " han terminado con algunas irregularidades\r\n" ;
	}
	public static Object campoMal(String nombreCampo) {
		return "Los resultados del ensayo de " + nombreCampo + " han resultado negativos\r\n" ;
	}

	
}
