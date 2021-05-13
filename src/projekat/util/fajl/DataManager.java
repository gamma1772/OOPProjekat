package projekat.util.fajl;

import projekat.knjiga.Autor;
import projekat.knjiga.Izdavac;
import projekat.knjiga.Knjiga;
import projekat.osoba.AbstractKorisnik;
import projekat.osoba.AbstractOsoba;
import projekat.osoba.Sifra;
import projekat.util.debug.Logger;

import java.io.*;
import java.util.ArrayList;

/**Staticka klasa koja sadrzi metode za serijalizaciju i deserijalizcaju objekata.*/
public class DataManager {
	private static final Logger LOGGER = new Logger("DATA-MANAGER");

	private static final String SIFRE = "sifre.ser";
	private static final String KNJIGE = "knjige.ser";
	private static final String KORISNICI = "korisnici.ser";
	private static final String CLANOVI = "clanovi.ser";
	private static final String AUTORI = "autori.ser";
	private static final String IZDAVACI = "izdavaci.ser";

	private static final String FOLDER = "data//";

	private static FileInputStream fis;
	private static ObjectInputStream ois;

	private static FileOutputStream fos;
	private static ObjectOutputStream oos;

	/**Serijalizuje listu sifri u data/sifre.ser, {@link DataManager#SIFRE}
	 * @param sifre Lista svih sifara
	 * @throws IOException u koliko je upisivanje objekta neuspesno*/
	public static void serializeSifre(ArrayList<Sifra> sifre) throws IOException {
		fos = createOutputStream(FOLDER, SIFRE);
		oos = new ObjectOutputStream(fos);
		oos.writeObject(sifre);
		oos.close();
		fos.close();
	}
	/**Serijalizuje listu korisnika (administratora) u data/korisnici.ser, {@link DataManager#KORISNICI}
	 * @param korisnici Lista svih korisnika
	 * @throws IOException u koliko je upisivanje objekta neuspesno*/
	public static void serializeKorisnike(ArrayList<AbstractKorisnik> korisnici) throws IOException {
		fos = createOutputStream(FOLDER, KORISNICI);
		oos = new ObjectOutputStream(fos);
		oos.writeObject(korisnici);
		oos.close();
		fos.close();
	}
	/**Serijalizuje listu knjiga u data/knjige.ser, {@link DataManager#KNJIGE}
	 * @param knjige Lista svih knjiga
	 * @throws IOException u koliko je upisivanje objekta neuspesno*/
	public static void serializeKnjige(ArrayList<Knjiga> knjige) throws IOException {
		fos = createOutputStream(FOLDER, KNJIGE);
		oos = new ObjectOutputStream(fos);
		oos.writeObject(knjige);
		oos.close();
		fos.close();
	}
	/**Serijalizuje listu autora u data/autori.ser, {@link DataManager#AUTORI}
	 * @param autori Lista svih autora
	 * @throws IOException u koliko je upisivanje objekta neuspesno*/
	public static void serializeAutore(ArrayList<Autor> autori) throws IOException {
		fos = createOutputStream(FOLDER, AUTORI);
		oos = new ObjectOutputStream(fos);
		oos.writeObject(autori);
		oos.close();
		fos.close();
	}
	/**Serijalizuje listu clanova u data/autori.ser, {@link DataManager#AUTORI}
	 * @param clanovi Lista svih autora
	 * @throws IOException u koliko je upisivanje objekta neuspesno*/
	public static void serializeClanove(ArrayList<AbstractOsoba> clanovi) throws IOException {
		fos = createOutputStream(FOLDER, CLANOVI);
		oos = new ObjectOutputStream(fos);
		oos.writeObject(clanovi);
		oos.close();
		fos.close();
	}
	/**Serijalizuje listu izdavaca u data/izdavaci.ser, {@link DataManager#IZDAVACI}
	 * @param izdavaci Lista svih izdavaca
	 * @throws IOException u koliko je upisivanje objekta neuspesno*/
	public static void serializeIzdavace(ArrayList<Izdavac> izdavaci) throws IOException {
		fos = createOutputStream(FOLDER, IZDAVACI);
		oos = new ObjectOutputStream(fos);
		oos.writeObject(izdavaci);
		oos.close();
		fos.close();
	}

	public static ArrayList<AbstractKorisnik> deserializeKorisnici() throws IOException, ClassNotFoundException {
		ArrayList<AbstractKorisnik> tempLista;
		fis = createInputStream(FOLDER, KORISNICI);
		ois = new ObjectInputStream(fis);
		tempLista = (ArrayList<AbstractKorisnik>) ois.readObject();

		return tempLista;
	}

	/**Kreira izlazni tok za serijalizcaju podataka. Ako ne postoji folder, kreira ga.
	 * @param folder Direktorijum gde treba da se sacuvaju podaci
	 * @param data Ime datoteke gde treba da se sacuvaju podaci
	 * @throws FileNotFoundException ako fajl 'data' ne postoji
	 * @return new FileOutputStream(); null ako fajl ne postoji */

	private static FileOutputStream createOutputStream(String folder, String data) {
		File file = new File(folder);
		if (!file.exists()) {
			LOGGER.info(String.format("Kreiranje foldera '%s'", folder));
			if (file.mkdir()) {
				LOGGER.info(String.format("Kreiran folder '%s'", folder));
			}
		}
		try {
			LOGGER.info(String.format("Kreiranje izlaznog toka '%s'", data));
			return new FileOutputStream(folder + data);
		} catch (FileNotFoundException exception) {
			exception.printStackTrace();
			return null;
		}
	}
	/**Kreira ulazni tok za deserijalizcaju podataka. Zahteva da postoji folder
	 * @param folder Direktorijum gde se cuvaju podaci
	 * @param data Ime datoteke u kojoj se cuvaju podaci
	 * @throws FileNotFoundException ako fajl ili folder ne postoje
	 * @return new FileInputStream(); null ako fajl ili folder ne postoje */
	private static FileInputStream createInputStream(String folder, String data) {
		try {
			LOGGER.info(String.format("Kreiranje ulaznog toka '%s'", data));
			return new FileInputStream(folder + data);
		} catch (FileNotFoundException exception) {
			exception.printStackTrace();
			LOGGER.error(String.format("createInputStream(String folder, String data) zahteva da postoji fajl '%s' i direktorijum '%s'\n%s", data, folder, exception));
			return null;
		}
	}
}
