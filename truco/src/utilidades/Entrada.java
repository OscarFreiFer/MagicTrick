package utilidades;
import java.io.*;

public class Entrada {
	static String inicializar() {
		String buzon = "";
		InputStreamReader flujo = new InputStreamReader(System.in);
		BufferedReader teclado = new BufferedReader(flujo);
		try {
			buzon = teclado.readLine();
		} catch (Exception e) {
			System.out.append("Entrada incorrecta)");
		}
		return buzon;
	}
	
	public static long enteroLargo() {
		long valor = Long.parseLong(inicializar());
		return valor;
	}
	
	
	
	public static int entero() {
		int valor = Integer.parseInt(inicializar());
		return valor;
	}
	
	public static int entero(String solicitud, String error) {
		boolean correcto;
		int valor=0;
		do {
			System.out.print(solicitud);
			try {
				valor = entero();
				correcto = true;
			} catch (NumberFormatException e) {
				if (error == null)
					throw e;
				else {
					System.out.println(error);
					correcto = false;
				}
			}
		} while (!correcto);
		return valor;
	}
	
	public static double real() {
		double valor = Double.parseDouble(inicializar());
		return valor;
	}
	
	public static double real(String solicitud, String error) {
		boolean correcto;
		double valor=0;
		do {
			System.out.println(solicitud);
			try {
				valor = real();
				correcto = true;
			} catch (NumberFormatException e) {
				if (error == null)
					throw e;
				else {
					System.out.println(error);
					correcto = false;
				}
			}
		} while (!correcto);
		return valor;
	}
	
	public static String cadena() {
		String valor = inicializar();
		return valor;
	}

	public static char caracter() {
		String valor = inicializar();
		return valor.charAt(0);
	}
}