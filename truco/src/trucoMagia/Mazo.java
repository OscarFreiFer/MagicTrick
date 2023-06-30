package trucoMagia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Mazo {
	private List<Carta> mazo = new ArrayList<Carta>();

	public Mazo(List<Carta> mazo) {
		this.mazo = mazo;
	}

	public List<Carta> getMazo() {
		return mazo;
	}

	public void setMazo(List<Carta> mazo) {
		this.mazo = mazo;
	}

	public void mostrarMazo() {
		for (Carta carta : mazo) {
			System.out.println(carta.escribirCarta());
		}
		System.out.println();
	}

	public void barajarMazo() {
		Random r = new Random();
		Carta aux = null;
		Carta sust = null;
		int contador = 0;
		while (contador < 3 * mazo.size()) {
			aux = mazo.get(r.nextInt(mazo.size()));
			sust = mazo.get(r.nextInt(mazo.size()));
			mazo.set(mazo.indexOf(aux), sust);
			mazo.set(mazo.indexOf(sust), aux);
			contador++;
		}
	}

	public void cargarMazo(File f) throws IOException {
		BufferedReader bfr = new BufferedReader(new FileReader(f));
		String linea;
		List<Carta> listaCartas = new ArrayList<Carta>();
		while ((linea = bfr.readLine()) != null) {
			if (!linea.equals("")) {
				String[] cartaArchivo = linea.split("[ ,;.]+");
				tPalo palo = null;
				tNumero numero = null;

				switch (cartaArchivo[0]) {
				case "p":
					palo = tPalo.picas;
					break;
				case "t":
					palo = tPalo.treboles;
					break;
				case "d":
					palo = tPalo.diamantes;
					break;
				case "c":
					palo = tPalo.corazones;
					break;
				default:
					break;
				}

				switch (Integer.valueOf(cartaArchivo[1])) {
				case 1:
					numero = tNumero.A;
					break;
				case 2:
					numero = tNumero.dos;
					break;
				case 3:
					numero = tNumero.tres;
					break;
				case 4:
					numero = tNumero.cuatro;
					break;
				case 5:
					numero = tNumero.cinco;
					break;
				case 6:
					numero = tNumero.seis;
					break;
				case 7:
					numero = tNumero.siete;
					break;
				case 8:
					numero = tNumero.ocho;
					break;
				case 9:
					numero = tNumero.nueve;
					break;
				case 10:
					numero = tNumero.diez;
					break;
				case 11:
					numero = tNumero.J;
					break;
				case 12:
					numero = tNumero.Q;
					break;
				case 13:
					numero = tNumero.K;
					break;
				default:
					break;
				}
				Carta carta = new Carta(palo, numero);
				if (listaCartas.size() < 52 && !listaCartas.contains(carta))
					listaCartas.add(carta);
			}
		}
		mazo = listaCartas;
		bfr.close();
	}

	public void crearMazoFichero(Baraja b, File f, int numCartas) throws IOException {
		List<Carta> mazoSinDupli = new ArrayList<Carta>();
		Random r = new Random();
		// Saco una carta aleatoria de la baraja
		Carta c = b.getBaraja().get(r.nextInt(b.getBaraja().size()));
		// Añado tantas cartas aleatorias como haya indicado el usuario sin que se
		// dupliquen
		while (numCartas - 1 >= 0) {
			if (mazoSinDupli.contains(c))
				c = b.getBaraja().get(r.nextInt(b.getBaraja().size()));
			else {
				mazoSinDupli.add(c);
				numCartas--;
			}
		}
		Mazo m = new Mazo(mazoSinDupli);
		guardarMazoFichero(f, m);
	}

	public void guardarMazoFichero(File f, Mazo m) throws IOException {
		PrintWriter pw = new PrintWriter(new FileWriter(f));
		for (Carta carta : m.getMazo()) {
			pw.println(carta.formatoFichero());
		}
		pw.close();
	}

	public void unirMazos(Mazo mazoAUnir) {
		for (Carta carta : mazoAUnir.getMazo()) {
			if (!mazo.contains(carta))
				mazo.add(carta);
		}

	}

	public void cortarMazo(int cuantasCoger) {
		Mazo aux = partirMazo(cuantasCoger);
		for (Carta carta : aux.getMazo()) {
			mazo.add(carta);
		}
	}

	public Mazo partirMazo(int cuantasCoger) {
		Mazo mazoDestino = new Mazo(new ArrayList<Carta>());
		for (int i = 0; i < cuantasCoger; i++) {
			mazoDestino.getMazo().add(mazo.get(i));
		}
		for (int i = cuantasCoger - 1; i >= 0; i--) {
			this.mazo.remove(i);
		}
		return mazoDestino;
	}

	public Mazo repartirNegrasRojas(boolean neg) {
		Mazo negras = new Mazo(new ArrayList<Carta>());
		Mazo rojas = new Mazo(new ArrayList<Carta>());
		for (Carta carta : mazo) {
			if (carta.getPalo().equals(tPalo.picas) || carta.getPalo().equals(tPalo.treboles))
				negras.getMazo().add(carta);
			else
				rojas.getMazo().add(carta);
		}
		if (neg)
			return negras;
		else
			return rojas;
	}

	public Mazo repartirBajasAltas(boolean baj) {
		Mazo bajas = new Mazo(new ArrayList<Carta>());
		Mazo altas = new Mazo(new ArrayList<Carta>());
		for (Carta carta : mazo) {
			if (carta.getNumero().compareTo(tNumero.ocho) < 0)
				bajas.getMazo().add(carta);
			else
				altas.getMazo().add(carta);
		}
		if (baj)
			return bajas;
		else
			return altas;
	}

	// Este método te devuelve un único mazo en el cual se han quedado las cartas
	// repartidas de manera alterna.
	// En un mazo en el que tiene las cartas 2,3,4,5,6,7,8 si lo quiero en 3
	// montones y le paso
	// "queMazo" = 2 devolverá un único mazo con el 3 y el 6
	public Mazo repartirIntercalando(int cuantos, int queMazo) {
		Mazo intercalado = new Mazo(new ArrayList<Carta>());
		for (int i = queMazo; i < mazo.size(); i += cuantos) {
			intercalado.getMazo().add(mazo.get(i));
		}
		return intercalado;
	}

	// Este método, devolverá una lista con todos los mazos resultantes de partir el
	// mazo que pasamos como parámetro, en el número de mazos que indiquemos,
	// repartiendo de manera alterna el mazo que le pasamos.
	public static List<Mazo> separarEnMazos(Mazo mazoASeparar, int cuantosMazos) {
		List<Mazo> mazosIntercalados = new ArrayList<Mazo>();
		Mazo intercalado;

		if (cuantosMazos <= 0 || cuantosMazos > 52)
			cuantosMazos = 1;
		if (cuantosMazos > mazoASeparar.getMazo().size())
			cuantosMazos = mazoASeparar.getMazo().size();
		for (int i = 0; i < cuantosMazos; i++) {
			intercalado = mazoASeparar.repartirIntercalando(cuantosMazos, i);
			mazosIntercalados.add(intercalado);
		}
		return mazosIntercalados;
	}
}
