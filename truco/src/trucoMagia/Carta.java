package trucoMagia;

import java.util.Objects;

public class Carta implements Comparable <Carta>{
	private tPalo palo;
	private tNumero numero;
	
	public Carta(tPalo palo, tNumero numero) {
		this.palo = palo;
		this.numero = numero;
	}

	public tPalo getPalo() {
		return palo;
	}

	public void setPalo(tPalo palo) {
		this.palo = palo;
	}

	public tNumero getNumero() {
		return numero;
	}

	public void setNumero(tNumero numero) {
		this.numero = numero;
	}

	public String escribirCarta() {
		int num;
		switch (numero) {
		case dos:
			num = 2;
			break;
		case tres:
			num = 3;
			break;
		case cuatro:
			num = 4;
			break;
		case cinco:
			num = 5;
			break;
		case seis:
			num = 6;
			break;
		case siete:
			num = 7;
			break;
		case ocho:
			num = 8;
			break;
		case nueve:
			num = 9;
			break;
		case diez:
			num = 10;
			break;
		default:
			return numero +" de " + palo;
		}
		return num +" de " + palo;
	}
	
	public String formatoFichero() {
		String cartaF = null;
		
		switch (palo) {
		case picas:
			cartaF = "p";
			break;
		case treboles:
			cartaF = "t";
			break;
		case diamantes:
			cartaF = "d";
			break;
		case corazones:
			cartaF = "c";
			break;
		default:
			break;
		}
		
		switch (numero) {
		case A:
			cartaF = cartaF + " 1";
			break;
		case dos:
			cartaF = cartaF + " 2";
			break;
		case tres:
			cartaF = cartaF + " 3";
			break;
		case cuatro:
			cartaF = cartaF + " 4";
			break;
		case cinco:
			cartaF = cartaF + " 5";
			break;
		case seis:
			cartaF = cartaF + " 6";
			break;
		case siete:
			cartaF = cartaF + " 7";
			break;
		case ocho:
			cartaF = cartaF + " 8";
			break;
		case nueve:
			cartaF = cartaF + " 9";
			break;
		case diez:
			cartaF = cartaF + " 10";
			break;
		case J:
			cartaF = cartaF + " 11";
			break;
		case Q:
			cartaF = cartaF + " 12";
			break;
		case K:
			cartaF = cartaF + " 13";
			break;
		default:
			break;
		}
		return cartaF;
	}

	public int hashCode() {
		return Objects.hash(palo, numero);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carta other = (Carta) obj;
		return numero == other.numero && palo == other.palo;
	}

	public int compareTo(Carta o) {
		if(this.palo.compareTo(o.palo) == 0)
			return this.numero.compareTo(o.numero);
		return this.palo.compareTo(o.palo);
	}
}
