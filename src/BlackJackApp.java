import java.util.Random;

public class BlackJackApp {
	public static void main(String[] args) {
		// initeer het pakje
		String[] pakje = initPakje();
		
		//toon de kaarten op het scherm
		for (int i = 0; i < pakje.length; i++) {
			System.out.println(pakje[i]);
		}
		
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
