package trucoMagia;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import utilidades.Entrada;

public class InterfazUsuario {

	public static void main(String[] args) throws IOException {
		boolean salir = false;

		do {
			mostrarMenu();
			System.out.print("Opción: ");
			int opcionMenu;
			try {
				opcionMenu = Entrada.entero();
			} catch (NumberFormatException e) {
				throw new IllegalArgumentException("El valor no puede ser distinto de un entero");
			}
			switch (opcionMenu) {
			case 0:
				salir = true;
				break;
			case 1:
				ejecutarManipulacionMazos();
				break;
			case 2:
				trucoTresMontones();
				break;
			case 3:
				trucoPosada();
				break;
			default:
				System.out.println("Opción no válida");
				break;
			}
		} while (!salir);

	}

	private static void trucoPosada() throws IOException {
		Mazo mazoTruco = new Mazo(new ArrayList<Carta>());
		mazoTruco.cargarMazo(new File("trucoPosada"));
		List <Mazo> mazosSeparados = Mazo.separarEnMazos(mazoTruco, 4);
		System.out.println("En un pueblo abandonado de la mano de Dios, había una posada con 4 habitaciones.");
		for (int i = 0; i < mazosSeparados.size(); i++) {
			switch (i) {
			case 0:
				System.out.println("\nA la cual, llegaron 4 caballeros y cada uno se puso en una habitación diferente");
				break;
			case 1:
				System.out.println("\nLuego llegaron 4 señoras y, para no dejarlas sin habitación, ubicaron a cada una de ellas en una de dichas habitaciones");
				break;
			case 2:
				System.out.println("\nA continuación, llegaron 4 reyes con sus 4 peones, y pusieron cada rey y cada peón en cada una de dichas habitaciones.");
				break;
			}
			boolean continuar = false; 
			String siguiente;
			do {
				System.out.println("... \n(Enter para continuar)");
				siguiente = Entrada.cadena();
				continuar = siguiente.equals("");
			} while (!continuar);
			for (int j = 0; j < mazosSeparados.size(); j++) {
				switch (i) {
				case 0:
					System.out.println("\n== Habitación " + (j+1) + " ==");
					System.out.println( mazosSeparados.get(j).getMazo().get(i).escribirCarta());
					break;
				case 1:
					System.out.println("\n== Habitación " + (j+1) + " ==");
					System.out.println( mazosSeparados.get(j).getMazo().get(i-1).escribirCarta());
					System.out.println( mazosSeparados.get(j).getMazo().get(i).escribirCarta());
					break;
				case 2:
					System.out.println("\n== Habitación " + (j+1) + " ==");
					System.out.println( mazosSeparados.get(j).getMazo().get(i-2).escribirCarta());
					System.out.println( mazosSeparados.get(j).getMazo().get(i-1).escribirCarta());
					System.out.println( mazosSeparados.get(j).getMazo().get(i).escribirCarta());
					break;
				case 3:
					System.out.println("\n== Habitación " + (j+1) + " ==");
					System.out.println( mazosSeparados.get(j).getMazo().get(i-3).escribirCarta());
					System.out.println( mazosSeparados.get(j).getMazo().get(i-2).escribirCarta());
					System.out.println( mazosSeparados.get(j).getMazo().get(i-1).escribirCarta());
					System.out.println( mazosSeparados.get(j).getMazo().get(i).escribirCarta());
					break;
				}
				
			}
		}
		System.out.println("\nEntonces se fueron todos a dormir...");
		for (int i = 1; i < mazosSeparados.size(); i++) {
			mazosSeparados.get(0).unirMazos(mazosSeparados.get(i));
		}
		mazoTruco = mazosSeparados.get(0);
		int dondeCorta = 0;
		boolean correcto = false;
		do {
			System.out.println("Por dónde quieres cortar el mazo?(máx 16)");
			try {
				dondeCorta = Entrada.entero();
			} catch (NumberFormatException e) {
				System.out.println("Como no has puesto un número válido se entiende que no quieres cortar el mazo.");
			}
			correcto = dondeCorta >= 0 && dondeCorta <= 16; 
		} while (!correcto);	
		mazoTruco.cortarMazo(dondeCorta);
		mazosSeparados = Mazo.separarEnMazos(mazoTruco, 4);
		int cont = 1;
		for (Mazo mazo : mazosSeparados) {
			System.out.println("\n== Habitación " + cont + " ==");
			mazo.mostrarMazo();
			cont++;
		}
		System.out.println("Y...\nSORPRESAAA!!!\nAmanecieron todos los caballeros juntos,\nTodas las damas juntas,\nY todos los reyes y los peones juntos!!");
	}

	private static void trucoTresMontones() throws IOException {
		Mazo mazoTruco = new Mazo(new ArrayList<Carta>());
		Baraja b = new Baraja();
		File f = new File("trucoMontones");
		int lugar;
		int cont = 1;
		boolean valido = false;
		mazoTruco.crearMazoFichero(b, f, 21);
		mazoTruco.cargarMazo(f);
		mazoTruco.barajarMazo();
		List<Mazo> mazosSeparados = Mazo.separarEnMazos(mazoTruco, 3);
		for (Mazo mazo : mazosSeparados) {
			System.out.println("== Mazo " + cont + " ==");
			mazo.mostrarMazo();
			cont++;
		}
		System.out.print("Memorice una carta e indique en qué mazo de los 3 está: ");
		do {
			lugar = Entrada.entero();
			if (lugar < 1 || lugar > 3)
				System.out.print("\nMazo no válido\nIntroduzca el mazo en el que está su carta:");
			else
				valido = true;
		} while (!valido);

		int veces = 1;
		while (veces <= 3) {

			switch (lugar) {
			case 1:
				mazosSeparados.get(1).unirMazos(mazosSeparados.get(0));
				mazosSeparados.get(1).unirMazos(mazosSeparados.get(2));
				mazoTruco = mazosSeparados.get(1);
				break;
			case 2:
				mazosSeparados.get(0).unirMazos(mazosSeparados.get(1));
				mazosSeparados.get(0).unirMazos(mazosSeparados.get(2));
				mazoTruco = mazosSeparados.get(0);
				break;
			case 3:
				mazosSeparados.get(0).unirMazos(mazosSeparados.get(2));
				mazosSeparados.get(0).unirMazos(mazosSeparados.get(1));
				mazoTruco = mazosSeparados.get(0);
				break;
			}
			if (veces < 3) {
				mazosSeparados = Mazo.separarEnMazos(mazoTruco, 3);
				cont = 1;
				for (Mazo mazo : mazosSeparados) {
					System.out.println("== Mazo " + cont + " ==");
					mazo.mostrarMazo();
					cont++;
				}
				System.out.print(
						(veces == 1) ? "En qué mazo está ahora?: " : "Y por última vez, en qué mazo está su carta?: ");
				lugar = Entrada.entero();
			}
			System.out.println("");
			veces++;
		}
		System.out.println("Tu carta es esta: " + mazoTruco.getMazo().get(10).escribirCarta());
	}

	private static void ejecutarManipulacionMazos() throws IOException {
		Mazo mazoOriginal = new Mazo(new ArrayList<Carta>());
		Baraja b = new Baraja();
		boolean salir = false;

		do {
			mostrarMenuManipulaciónMazos();
			System.out.print("Opción: ");
			int opcionMenu;
			try {
				opcionMenu = Entrada.entero();
			} catch (NumberFormatException e) {
				throw new IllegalArgumentException("El valor no puede ser distinto de un entero");
			}

			switch (opcionMenu) {
			case 0:
				salir = true;
				break;
			case 1:
				System.out.print("Introduzca un nombre de fichero donde crear el mazo: ");
				String ruta = Entrada.cadena();
				File f = new File(ruta);
				System.out.print("De cuántas cartas quieres que sea el mazo? (min. 0)(máx. 52): ");
				int numCartas = Entrada.entero();
				boolean valido = numCartas >= 0 && numCartas <= 52;
				while (!valido) {
					System.out.print("El número introducido no está en rango. \nIntroduce número entre 0 y 52: ");
					numCartas = Entrada.entero();
					valido = numCartas >= 0 && numCartas <= 52;
				}
				mazoOriginal.crearMazoFichero(b, f, numCartas);

				break;
			case 2:
				f = buscarFichero();
				if (f != null) {
					mazoOriginal.cargarMazo(f);
					mazoOriginal.mostrarMazo();
				}

				break;
			case 3:
				if (mazoOriginal.getMazo().isEmpty())
					System.out.println(
							"No se ha cargado ningún mazo. Seleccione primero la opción 2 para cargar un mazo");
				else {
					mazoOriginal.barajarMazo();
					mazoOriginal.mostrarMazo();

				}
				break;
			case 4:
				Mazo mazoAUnir = new Mazo(null);
				f = buscarFichero();
				mazoAUnir.cargarMazo(f);
				mazoOriginal.unirMazos(mazoAUnir);
				mazoOriginal.mostrarMazo();
				break;
			case 5:
				System.out.print("Por qué número de carta quiere cortar el mazo? ");
				int cuantasCoger = Entrada.entero();
				if (mazoOriginal.getMazo().isEmpty())
					System.out.println("\nDebe cargar primero un mazo con la opción 2 para poder cortarlo.");
				else {
					if (cuantasCoger < mazoOriginal.getMazo().size() && cuantasCoger > 0) {
						mazoOriginal.cortarMazo(cuantasCoger);
						mazoOriginal.mostrarMazo();
					} else
						System.out.println("\nEl mazo se mantiene sin cambios");
				}
				break;
			case 6:
				System.out.print("En qué fichero quiere guardar el mazo? ");
				ruta = Entrada.cadena();
				f = new File(ruta);
				mazoOriginal.guardarMazoFichero(f, mazoOriginal);
				break;
			case 7:
				Mazo negras = mazoOriginal.repartirNegrasRojas(true);
				Mazo rojas = mazoOriginal.repartirNegrasRojas(false);
				if (!negras.getMazo().isEmpty()) {
					System.out.println("== Negras ==");
					negras.mostrarMazo();
				}
				if (!rojas.getMazo().isEmpty()) {
					System.out.println("== Rojas ==");
					rojas.mostrarMazo();
				}
				break;
			case 8:
				Mazo bajas = mazoOriginal.repartirBajasAltas(true);
				Mazo altas = mazoOriginal.repartirBajasAltas(false);
				if (!bajas.getMazo().isEmpty()) {
					System.out.println("== Bajas ==");
					bajas.mostrarMazo();
				}
				if (!altas.getMazo().isEmpty()) {
					System.out.println("== Altas ==");
					altas.mostrarMazo();
				}
				break;
			case 9:
				System.out.print("En cuántos mazos lo quieres repartir? ");
				int cuantos = Entrada.entero();
				List<Mazo> mazosIntercalados = Mazo.separarEnMazos(mazoOriginal, cuantos);
				for (Mazo mazo : mazosIntercalados) {
					mazo.mostrarMazo();
				}
				break;
			default:
				System.out.println("Opción no válida");
				break;
			}
		} while (!salir);

	}

	private static File buscarFichero() {
		int cont = 1;
		boolean correcto;
		do {
			System.out.print("Intoduce la ruta para cargar el mazo: ");
			String ruta1 = Entrada.cadena();
			File f1 = new File(ruta1);
			correcto = cont < 3;
			if (f1.exists()) {
				return f1;
			} else {
				System.out.println("Fichero no encontrado(intentos restantes:" + (3 - cont) + ")\n");
				cont++;
			}
		} while (correcto);
		return null;
	}

	private static void mostrarMenu() {
		System.out.println("\n==== Menú ====");
		System.out.println("Seleccione una de las siguientes opciones(0 para salir):");
		System.out.println(" 1.- Manipular mazos de cartas.");
		System.out.println(" 2.- Truco de los tres montones.");
		System.out.println(" 3.- Truco de la Posada.");
	}

	private static void mostrarMenuManipulaciónMazos() {
		System.out.println("\n==== Menú Usuario (Manipular mazos)====");
		System.out.println("Seleccione una de las siguientes opciones(0 para salir):");
		System.out.println(" 1.- Crear un mazo con X cartas y guardar en fichero");
		System.out.println(" 2.- Cargar un mazo de cartas de fichero");
		System.out.println(" 3.- Barajar mazo de cartas");
		System.out.println(" 4.- Añadir mazo");
		System.out.println(" 5.- Cortar mazo");
		System.out.println(" 6.- Guardar mazo de cartas en fichero");
		System.out.println(" 7.- Separar en negras y rojas");
		System.out.println(" 8.- Separar en altas y bajas");
		System.out.println(" 9.- Repartir intercalando en X mazos");
	}
}
