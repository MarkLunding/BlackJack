import java.util.Random;
import java.util.Scanner;

public class BlackJackApp {
	public static void main(String[] args) {
		// initeer het pakje
		String[] pakje = initPakje();
		// toon de kaarten op het scherm
		System.out.println("Het pakje is geschud.");
		for (int i = 0; i < pakje.length; i++) {

			System.out.print(pakje[i] + " ");

		}
		System.out.println();
		// Speler 1 kan met k om kaart vragen, q stopt het spel
		legMogelijkhedenUit();
		boolean stoppen = false;
		String keuze;
		char optie = '0';
		int i = 0;
		int score = 0;
		String[] hand = new String[10]; // is 10genoeg? meestal al af bij meer dan 6 kaarten
		// loop totdat gebruiker stopt of stuk is
		while (!stoppen) {
			keuze = gebruikersKeuze();
			optie = keuze.charAt(0);
			switch (optie) {
			case 'q':
				stoppen = true;
				score = -1; //om te achterhalen dat speler is gestopt
				break;
			case 'p':
				stoppen = true;
				break;
			case 'k':
				hand[i] = pakje[i];
				for (int j = 0; j < hand.length; j++) {
					if (hand[j] == null) {
						break;
					}
					System.out.print(hand[j] + " ");
				}
				System.out.println();

				int waardeKaart = bepaalWaarde(hand[i]);
				score = score + waardeKaart;
				System.out.println("Uw huidige score is: " + score);
				i++;
				if (score >20) {
					stoppen = true;
				}
				break;

			default:
				System.out.println("De input is ongeldig, voer aub een geldige waarde in.");
				break;
			}
		}
		
		if(score == -1) {
			System.out.println("U heeft het spel gestopt.");
		} else if (score == 21) {
			System.out.println("Gewonnen, u heeft " + score + " punten.");
		} else if (score > 21) {
			System.out.println("Helaas, u heeft " + score + " punten. Dit is te veel.");
		} else {
			System.out.println("Helaas, u heeft " + score + " punten. Dit is niet genoeg.");
		}

	}

	public static int bepaalWaarde(String kaart) {
		int waarde = 0;
		switch (kaart) {
		case "H1":
		case "K1":
		case "R1":
		case "S1":
			waarde = 11; // 1 is de Aas
			break;
		case "H2":
		case "K2":
		case "R2":
		case "S2":
			waarde = 2;
			break;
		case "H3":
		case "K3":
		case "R3":
		case "S3":
			waarde = 3;
			break;
		case "H4":
		case "K4":
		case "R4":
		case "S4":
			waarde = 4;
			break;
		case "H5":
		case "K5":
		case "R5":
		case "S5":
			waarde = 5;
			break;
		case "H6":
		case "K6":
		case "R6":
		case "S6":
			waarde = 6;
			break;
		case "H7":
		case "K7":
		case "R7":
		case "S7":
			waarde = 7;
			break;
		case "H8":
		case "K8":
		case "R8":
		case "S8":
			waarde = 8;
			break;
		case "H9":
		case "K9":
		case "R9":
		case "S9":
			waarde = 9;
			break;
		case "H10":
		case "K10":
		case "R10":
		case "S10":
			waarde = 10;
			break;
		case "H11":
		case "K11":
		case "R11":
		case "S11":
			waarde = 10; // 11 is de boer
			break;
		case "H12":
		case "K12":
		case "R12":
		case "S12":
			waarde = 10; // 11 is de vrouw
			break;
		case "H13":
		case "K13":
		case "R13":
		case "S13":
			waarde = 10; // 11 is de heer
			break;

		}
		return waarde;
	}

	public static String gebruikersKeuze() {
		System.out.println("Geef een letter:");
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		input = input.toLowerCase();

		return input.toLowerCase();

	}

	static void legMogelijkhedenUit() {
		System.out.println("Met de optie stopt q het spel");
		System.out.println("Met de optie stopt p past u en eindigt uw beurt.");
		System.out.println("Met de optie stopt k krijgt u (nog) een kaart");
	}

	public static String[] initPakje() {
		// kaarten is de niet niet geschudde standaard vulling
		String[] kaarten;
		kaarten = new String[52];
		int x = 1;
		for (int i = 0; i < 13; i++) {
			kaarten[i] = "H" + x;
			x++;
		}
		x = 1;
		for (int j = 13; j < 26; j++) {
			kaarten[j] = "K" + x;
			x++;
		}
		x = 1;
		for (int k = 26; k < 39; k++) {
			kaarten[k] = "R" + x;
			x++;
		}
		x = 1;
		for (int l = 39; l < 52; l++) {
			kaarten[l] = "S" + x;
			x++;
		}
		// het pakje wordt de geschud en terug gegeven
		String[] pakje;
		pakje = new String[52];
		// extra array om bij te houden of waarde al is gebruikt
		int[] gebruikt = new int[52];
		for (int i = 0; i < gebruikt.length; i++) {
			gebruikt[i] = 0;
		}
		// vul het pakje met waarden, als eerder gebruikt, dan nog een keer
		for (int m = 0; m < 52; m++) {
			Random kaartNummer = new Random();
			boolean nietGebruikt = true;
			while (nietGebruikt) {
				int nr = kaartNummer.nextInt(52);
				if (gebruikt[nr] == 0) {

					pakje[m] = kaarten[nr];
					gebruikt[nr] = 1;
					nietGebruikt = false;
				}
			}

		}

		return pakje;
	}

}
