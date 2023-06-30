package trucoMagia;

import java.util.ArrayList;
import java.util.List;

public class Baraja {
	private List<Carta> baraja;

	public Baraja() {
		tNumero[] numero = tNumero.values();
		tPalo[] palo = tPalo.values();
		baraja = new ArrayList<Carta>();
		
		for (int i = 0; i < palo.length; i++) {
			for (int j = 0; j < numero.length; j++) {
				baraja.add(new Carta (palo[i],numero[j]));
			}
		}
	}

	public List<Carta> getBaraja() {
		return baraja;
	}

	public void setBaraja(List<Carta> baraja) {
		this.baraja = baraja;
	}

	public void escribirBaraja() {
		for (Carta carta : baraja) {
			System.out.println(carta.escribirCarta());
		}
	}
	
}
