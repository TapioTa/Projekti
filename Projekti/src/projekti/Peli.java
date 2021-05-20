package projekti;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.nio.charset.StandardCharsets;

public class Peli {
	public static void main(String[] args) {
		Scanner lukija = new Scanner(System.in);
		String nimi = "";
		String vastaus = "";

		while (true) { // Loopataan kunnes saadaan vastaus joo tai ei
			System.out.println("Haluatko pelata? (joo/ei)");
			vastaus = lukija.nextLine();

			if (vastaus.equalsIgnoreCase("ei")) {
				break;

			}

			if (vastaus.equalsIgnoreCase("joo")) {
				while (true) {

					System.out.println("Mik‰ on nimesi?"); // Loopataan kunnes saadaan edes joku kirjain nimeksi
					nimi = lukija.nextLine();

					if (nimi.isBlank()) {
						System.out.println("Nimesi on tyhj‰..." + "\n");
						continue;
					}
					System.out.println("Hei " + nimi // Esitell‰‰n peli‰ ja k‰ynnistet‰‰n peli
							+ "! Seuraavaksi esitet‰‰n 20 kysymyst‰. Vastaa kirjaimilla a, b, tai c. Onnea peliin"
							+ "\n");
					peli();
					break;
				}
				break;
			}

			{
				System.out.println("En ymm‰rt‰nyt vastausta. Kokeile uudelleen" + "\n"); // Jos ensimm‰iseen kysymykseen
																							// ei vastata joo/ei niin
																							// tulee t‰m‰ ja palataan
																							// alkuun
				continue;
			}

		}

		if (vastaus.equalsIgnoreCase("ei")) {

		} else
			while (true) { //t‰h‰n p‰‰dyt‰‰n pelin j‰lkeen ja voidaan p‰‰tt‰‰ pelataanko uudelleen vai ei.
				System.out.println("Haluatko pelata uudelleen? (joo/ei)");
				vastaus = lukija.nextLine();

				if (vastaus.equalsIgnoreCase("ei")) {
					break;

				} else if (vastaus.equalsIgnoreCase("joo")) {
					System.out.println("Peli alkaa taas " + nimi
							+ ". Seuraavaksi esitet‰‰n 20 kysymyst‰. Vastaa kirjaimilla a, b, tai c. Onnea peliin"
							+ "\n");
					peli();

				} else {
					System.out.println("En ymm‰rt‰nyt vastausta. Kokeile uudelleen");
					continue;
				}

			}
		System.out.println("N‰kemiin!");
		lukija.close();
	}

	public static void peli() { // peli metodi
		Scanner lukija = new Scanner(System.in);
		int pisteet = 0;
		int kysytty = 0;
		String pelaajanVastaus = "";

		haeKysymykset tietokilpailu = new haeKysymykset();

		for (kysymys kysyttava : tietokilpailu.getkysymysLista()) { // looppaa listalta kysymyksi‰ kunnes kysytty 20
																	// kysymyst‰ ja sitten if lauseke lopussa lopettaa
																	// loopin
			System.out.println(kysyttava);
			System.out.println("Mik‰ on vastauksesi?");

			while (true) {
				pelaajanVastaus = lukija.nextLine();

				if (pelaajanVastaus.equalsIgnoreCase("a") || pelaajanVastaus.equalsIgnoreCase("b") // tarkistaa, ett‰
																									// onhan pelaaja
																									// vastannu a, b tai
																									// c kirjaimella.
																									// Jos ei ole niin
																									// pyyt‰‰ syˆtt‰m‰‰n
																									// uudelleen
						|| pelaajanVastaus.equalsIgnoreCase("c")) {
					break;

				} else
					System.out.println("Vastaa kirjaimilla a, b, tai c.");
				continue;

			}

			if (pelaajanVastaus.equalsIgnoreCase(kysyttava.vastaus)) { // tarkistaa onko vastaus oikein. Jos on +1 ja
																		// eteenp‰in jos ei ole vastaus oikein menn‰‰n
																		// eteenp‰in
				System.out.println("Vastasit oikein" + "\n");
				pisteet = pisteet + 1;
			} else {
				System.out.println("Vaustauksesi oli v‰‰r‰" + "\n");
			}

			kysytty = kysytty + 1;

			if (kysytty == 20) {
				break;
			}
			System.out.println("Seuraava kysymys:" + "\n");

		}

		lopputekstit(pisteet); // vie pisteet lopputekstit metodiin

	}

	public static void lopputekstit(int oikein) { // kun 20 kysymyst‰ on esitetty oikein vastausten m‰‰r‰ tulee t‰h‰n
													// metodiin ja tulostetaan halutunlainen vastaus
		if (oikein == 0) {
			System.out.println("Et saanut yht‰‰n oikeaa vastausta. Parempi onni ensikerralla");
		} else if (oikein == 20) {
			System.out.println("Mahtavaa! Sait kaikki oikein");
		} else {
			System.out.println("Oikein vastasit " + oikein + "/20");
		}
	}

}

class haeKysymykset { // hakee kysymykset, vaihtoehdot ja vastaukset ja tekee niist‰ arraylistin

	private ArrayList<kysymys> kysymysLista = new ArrayList<>();

	public haeKysymykset() {
		try {
			FileInputStream file = new FileInputStream("./Kysymykset.txt");
			InputStreamReader isr = new InputStreamReader(file, StandardCharsets.UTF_8);
			BufferedReader tiedostonLukija = new BufferedReader(isr);
			Scanner lueTiedosto = new Scanner(tiedostonLukija);

			String rivi;
			String kysymys = "";

			String[] vaihtoehdot = new String[3]; //m‰‰rittelee, ett‰ lˆytyy 3 vastausvaihtoehtoa
			String vastaus = "";
			int apu = 0;

			do {

				do {
					rivi = lueTiedosto.nextLine();

					if (rivi.contains("?")) { // etsii ? ja varastoi sen kysymyksen‰
						kysymys = rivi;

					} else if (rivi.contains(")")) { // etsii vaihtoehdot ) merkki‰ hyˆdynt‰en ja varastoi ne
														// vaihtoehtoina
						vaihtoehdot[apu++] = rivi;

					} else if (Character.isAlphabetic(rivi.indexOf(0)) || apu == 3) { // varastoi vastausksen

						vastaus = String.valueOf(rivi);
					}

				}

				while (vastaus == "");
				kysymysLista.add(new kysymys(kysymys, vaihtoehdot, vastaus)); // lis‰‰ haetut tiedot taulukkoon ja
																				// nollaa avustavat tiedot ja palaa
																				// takaisin alkuun hakemaan seuraavaa
																				// kysymyst‰
				apu = 0;
				vastaus = "";
				vaihtoehdot = new String[3];

			} while (lueTiedosto.hasNext()); // looppaa kunnes ei ole en‰‰ luottavia rivej‰

			file.close();
			tiedostonLukija.close();
			lueTiedosto.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<kysymys> getkysymysLista() {
		Collections.shuffle(kysymysLista); // sekottaa kysymysten j‰rjestyksen. Eli sekoittaa arraylistin
		return kysymysLista;
	}

}

class kysymys { // muotoilee kysymyksen ja vastausvaihtoehdot valmiiksi rimpsuksi peli metodille

	private String kysymys;
	private String[] vaihtoehdot;
	public String vastaus;

	public kysymys(String kysymys, String[] vaihtoehdot, String vastaus) {
		this.kysymys = kysymys;
		this.vaihtoehdot = vaihtoehdot;
		this.vastaus = vastaus;
	}

	public String getkysymys() {
		return kysymys;
	}

	public String[] getvaihtoehdot() {
		return vaihtoehdot;

	}

	public String getvastaus() {
		return vastaus;
	}

	@Override
	public String toString() {
		String print = kysymys + "\n";
		for (String vaihtoehto : vaihtoehdot) {
			print += vaihtoehto + "\n";
		}

		return print;
	}
}
