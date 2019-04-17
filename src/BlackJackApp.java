import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

public class BlackJackApp {
	public static void main(String[] args) {
		PakjeKaarten pakje = new PakjeKaarten();
		pakje.schudden();
		pakje.toonPakje();
	}
}

class PakjeKaarten {
	Card[] pakjeOngeschud = new Card[52];
	Card[] pakjeGeschud = new Card[52];
	PakjeKaarten(){
		for (int i = 0; i < 13; i++) {
			this.pakjeOngeschud[i] = new Card(i+1, "Harten");
		}
		for (int i = 0; i < 13; i++) {
			this.pakjeOngeschud[i+13] = new Card(i+1, "Ruiten");
		}
		for (int i = 0; i < 13; i++) {
			this.pakjeOngeschud[i+26] = new Card(i+1, "Schoppen");
		}
		for (int i = 0; i < 13; i++) {
			this.pakjeOngeschud[i+39] = new Card(i+1, "Klaver");
		}
	}
	
	void schudden() {
		// extra array om bij te houden of waarde al is gebruikt
		int[] gebruikt = new int[52];
		for (int i = 0; i < gebruikt.length; i++) {
			gebruikt[i] = 0;
		}
		for (int i = 0; i < 52; i++) {
			Random kaartNummer = new Random();
			boolean nietGebruikt = true;
			while (nietGebruikt) {
				int nr = kaartNummer.nextInt(52);
				if (gebruikt[nr] == 0) {

					this.pakjeGeschud[i] = pakjeOngeschud[nr];
					gebruikt[nr] = 1;
					nietGebruikt = false;
				}
			}
		}
	}
	
	void toonPakje() {
		for (int i = 0; i < this.pakjeGeschud.length; i++) {
			System.out.print(this.pakjeGeschud[i].kleur + " ");
			System.out.println(this.pakjeGeschud[i].naam);
		}
	}
	
}

class Card {
	String naam;
	int waarde;
	String kleur;
	boolean aas;

	Card(int nr, String kleur) {
		this.kleur = kleur;
		if (nr == 1) {
			this.naam = "Aas";
			this.waarde = 11;
			this.aas = true;
		} else if (nr == 11) {
			this.naam = "Boer";
			this.waarde = 10;
			this.aas = false;
		} else if (nr == 12) {
			this.naam = "Vrouw";
			this.waarde = 10;
			this.aas = false;
		} else if (nr == 13) {
			this.naam = "Heer";
			this.waarde = 10;
			this.aas = false;
		} else {
			this.naam = Integer.toString(nr);
			this.waarde = nr;
			this.aas = false;
		}
	}

	Card geefKaart() {
		return this;
	}
}
