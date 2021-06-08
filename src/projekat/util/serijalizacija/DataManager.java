package projekat.util.serijalizacija;

import projekat.knjiga.Autor;
import projekat.knjiga.Izdavac;
import projekat.knjiga.Knjiga;
import projekat.osoba.Administrator;
import projekat.osoba.Clan;
import projekat.osoba.Pozajmljivanje;
import projekat.osoba.Sifra;
import projekat.sistem.PravilaBiblioteke;
import projekat.util.TokProgramaException;

import java.io.*;
import java.util.ArrayList;

/**Staticka klasa koja sadrzi metode za serijalizaciju i deserijalizcaju objekata.*/
public class DataManager {

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

	private static BufferedReader br;
	private static BufferedWriter bw;

	/**Vrsi upisivanje Stringa u fajl. String sadrzi podatke objekta koji treba da se "Serijalizuje".
	 * @param object Objekat koji je formatiran pomocu toStringSerializable()
	 * @param fileName Ime fajla gde treba da se cuva objekat.
	 * @throws IOException u koliko upisivanje objekta nije uspelo*/
	public static void serializeString(String object, String fileName) throws IOException {
		if (new File(fileName).exists()) {
			bw = new BufferedWriter(new FileWriter(FOLDER + fileName, true));
		}
		else {
			bw = new BufferedWriter(new FileWriter(FOLDER + fileName));
		}

		bw.write(object + '\n');

		bw.flush();
		bw.close();
	}

	/**Vrsi serijalizaciju liste stringova koji sadrze informacije o objektu.
	 * @param objects Lista stringova
	 * @param fileName Datoteka u kojoj ce se cuvati podaci
	 * @param append Da li da obrise sve iz fajla i napise sadrzaj liste, ili da doda sadrzaj liste ispod postojeceg sadrzaja
	 * @throws IOException u koliko ne moze da se upisu podaci u fajl.*/
	public static void serializeString(ArrayList<String> objects, String fileName, boolean append) throws IOException {
		if (append) {
			bw = new BufferedWriter(new FileWriter(FOLDER + fileName, true));
		}
		else {
			bw = new BufferedWriter(new FileWriter(FOLDER + fileName));
		}

		for (String object : objects) {
			bw.write(object + '\n');
		}

		bw.flush();
		bw.close();
	}

	/**Vrsi deserijalizaciju administratora. Zahteva da se pre toga deserijalizuju sifre i dozvole*/
	public static ArrayList<Administrator> deserializeAdmins(ArrayList<Sifra> passwds, ArrayList<Administrator.Dozvole> dozvole) throws IOException, TokProgramaException {
		if (passwds == null || passwds.size() == 0) {
			throw new TokProgramaException("Sifre moraju biti deserijalizovane pre administratora!");
		} else if (dozvole == null || dozvole.size() == 0) {
			throw new TokProgramaException("Dozvole moraju biti deserijalizovane pre administratora!");
		}
		String line;
		String[] lista;
		ArrayList<Administrator> administratori = new ArrayList<>();
		br = new BufferedReader(new FileReader(FOLDER + "administrator.tdb"));

		while((line = br.readLine()) != null) {
			Administrator a = new Administrator();
			lista = line.split("~");
			a.setUUID(lista[0]);
			a.setIme(lista[1]);
			a.setPrezime(lista[2]);
			a.setAdresa(lista[3]);
			a.setBrTelefona(lista[4]);
			a.setEmail(lista[5]);
			a.setPol(Integer.parseInt(lista[6]));
			a.setUsername(lista[7]);

			for (Sifra s : passwds) {
				if (s.getKorisnickiUUID().equals(a.getUUID())) {
					a.setPassword(s);
					break;
				}
			}
			for (Administrator.Dozvole d : dozvole) {
				if (d.getUserUUID().equals(a.getUUID())) {
					a.setDozvole(d);
					break;
				}
			}

			administratori.add(a);
		}

		return administratori;
	}

	/**Vrsi deserijalizaciju clanova. Zahteva da se pre toga deserijalizuje lista pozajmljivanja*/
	public static ArrayList<Clan> deserializeClanovi(ArrayList<Pozajmljivanje> pozajmljivanja) throws IOException, TokProgramaException {
		if (pozajmljivanja == null || pozajmljivanja.size() == 0) {
			throw new TokProgramaException("Lista pozajmljivanja mora biti deserijalizovana pre clanova biblioteke!");
		}

		String line;
		String[] lista;
		ArrayList<Clan> clanovi = new ArrayList<>();
		br = new BufferedReader(new FileReader(FOLDER + "clan.tdb"));

		while((line = br.readLine()) != null) {
			Clan c = new Clan();
			lista = line.split("~");

			c.setUUID(lista[0]);
			c.setIme(lista[1]);
			c.setPrezime(lista[2]);
			c.setAdresa(lista[3]);
			c.setBrTelefona(lista[4]);
			c.setPol(Integer.parseInt(lista[5]));
			c.setEmail(lista[6]);

			for (Pozajmljivanje p : pozajmljivanja) {
				if (p.getClanUUID().equals(c.getUUID())) {
					c.getPozajmljivanje().add(p);
				}
			}

			clanovi.add(c);
		}

		return clanovi;
	}
	/**Vrsi deserijalizaciju sifara. Ne zahteva prethodne deserijalizacije*/
	public static ArrayList<Sifra> deserializeSifre() throws IOException {

		String line;
		String[] lista;
		ArrayList<Sifra> sifre = new ArrayList<>();
		br = new BufferedReader(new FileReader(FOLDER + "sifra.tdb"));

		while((line = br.readLine()) != null) {
			Sifra s = new Sifra();
			lista = line.split("~");

			s.setKorisnickiUUID(lista[0]);
			s.setSifra(lista[1]);

			sifre.add(s);
		}

		return sifre;
	}

	/**Vrsi deserijalizaciju clanova. Zahteva da se pre toga deserijalizuje lista autora i izdavaca*/
	public static ArrayList<Knjiga> deserializeKnjige(ArrayList<Autor> autori, ArrayList<Izdavac> izdavaci) throws IOException {

		String line;
		String[] lista;
		ArrayList<Knjiga> knjige = new ArrayList<>();
		br = new BufferedReader(new FileReader(FOLDER + "knjiga.tdb"));

		while((line = br.readLine()) != null) {
			String[] zanrovi;
			Knjiga k = new Knjiga();
			lista = line.split("~");

			k.setId(lista[0]);
			k.setImeKnjige(lista[1]);
			k.setGodinaObjavljivanja(Integer.parseInt(lista[4]));
			k.setIzdanje(Integer.parseInt(lista[5]));
			k.setBrStrana(Integer.parseInt(lista[6]));
			k.setISBN(lista[7]);
			k.setKategorija(Integer.parseInt(lista[8]));
			k.setKolicina(Integer.parseInt(lista[9]));

			zanrovi = lista[10].replace("(", "").replace(")", "").split(";");
			int[] zanrList = new int[zanrovi.length];

			for (int i = 0; i < zanrList.length; i++) {
				zanrList[i] = Integer.parseInt(zanrovi[i]);
			}
			k.setZanrovi(zanrList);

			for (Autor a : autori) {
				if (a.getId().equals(lista[2])) {
					k.setAutor(a);
					break;
				}
			}

			for (Izdavac i : izdavaci) {
				if (i.getId().equals(lista[3])) {
					k.setIzdavac(i);
					break;
				}
			}

			knjige.add(k);
		}

		return knjige;
	}

	/**Vrsi deserijalizaciju dozvola administratora. Ne zahteva prethodne deserijalizacije*/
	public static ArrayList<Administrator.Dozvole> deserializeDozvole() throws IOException{
		String line;
		String[] lista;
		ArrayList<Administrator.Dozvole> dozvole = new ArrayList<>();
		br = new BufferedReader(new FileReader(FOLDER + "knjiga.tdb"));

		while((line = br.readLine()) != null) {
			Administrator.Dozvole d = new Administrator.Dozvole();
			lista = line.split("~");

			d.setUserUUID(lista[0]);
			d.setAdmin(Boolean.parseBoolean(lista[1]));
			d.setCanAddAdmins(Boolean.parseBoolean(lista[2]));
			d.setCanAddMembers(Boolean.parseBoolean(lista[3]));
			d.setCanAddBooks(Boolean.parseBoolean(lista[4]));
			d.setMasterRule(Boolean.parseBoolean(lista[5]));
			d.setCanLoanBooks(Boolean.parseBoolean(lista[6]));
			d.setCanDeleteAdmins(Boolean.parseBoolean(lista[7]));
			d.setCanDeleteMembers(Boolean.parseBoolean(lista[8]));
			d.setCanDeleteBooks(Boolean.parseBoolean(lista[9]));
			d.setCanAlterRules(Boolean.parseBoolean(lista[10]));

			dozvole.add(d);
		}

		return dozvole;
	}

	public static ArrayList<Autor> deserializeAutore() throws IOException{
		//TODO
	}

	public static ArrayList<Izdavac> deserializeIzdavace() throws IOException{
		//TODO
	}

	public static ArrayList<Pozajmljivanje> deserializePozajmljivanje() throws IOException{
		String line;
		String[] lista;
		ArrayList<Pozajmljivanje> pozajmljivanjaArrayList = new ArrayList<>();
		br = new BufferedReader(new FileReader(FOLDER + "pozajmljivanja.tdb"))
		//TODO
	}

	public static PravilaBiblioteke deserializePravila() throws IOException{
		//TODO
	}
//	/**Serijalizuje listu sifri u data/sifre.ser, {@link DataManager#SIFRE}
//	 * @param sifre Lista svih sifara
//	 * @throws IOException u koliko je upisivanje objekta neuspesno*/
//	public static void serializeSifre(ArrayList<Sifra> sifre) throws IOException {
//		fos = createOutputStream(FOLDER, SIFRE);
//		oos = new ObjectOutputStream(fos);
//		oos.writeObject(sifre);
//		oos.close();
//		fos.close();
//	}
//	/**Serijalizuje listu korisnika (administratora) u data/korisnici.ser, {@link DataManager#KORISNICI}
//	 * @param korisnici Lista svih korisnika
//	 * @throws IOException u koliko je upisivanje objekta neuspesno*/
//	public static void serializeKorisnike(ArrayList<AbstractKorisnik> korisnici) throws IOException {
//		fos = createOutputStream(FOLDER, KORISNICI);
//		oos = new ObjectOutputStream(fos);
//		oos.writeObject(korisnici);
//		oos.close();
//		fos.close();
//	}
//	/**Serijalizuje listu knjiga u data/knjige.ser, {@link DataManager#KNJIGE}
//	 * @param knjige Lista svih knjiga
//	 * @throws IOException u koliko je upisivanje objekta neuspesno*/
//	public static void serializeKnjige(ArrayList<Knjiga> knjige) throws IOException {
//		fos = createOutputStream(FOLDER, KNJIGE);
//		oos = new ObjectOutputStream(fos);
//		oos.writeObject(knjige);
//		oos.close();
//		fos.close();
//	}
//	/**Serijalizuje listu autora u data/autori.ser, {@link DataManager#AUTORI}
//	 * @param autori Lista svih autora
//	 * @throws IOException u koliko je upisivanje objekta neuspesno*/
//	public static void serializeAutore(ArrayList<Autor> autori) throws IOException {
//		fos = createOutputStream(FOLDER, AUTORI);
//		oos = new ObjectOutputStream(fos);
//		oos.writeObject(autori);
//		oos.close();
//		fos.close();
//	}
//	/**Serijalizuje listu clanova u data/autori.ser, {@link DataManager#AUTORI}
//	 * @param clanovi Lista svih autora
//	 * @throws IOException u koliko je upisivanje objekta neuspesno*/
//	public static void serializeClanove(ArrayList<AbstractOsoba> clanovi) throws IOException {
//		fos = createOutputStream(FOLDER, CLANOVI);
//		oos = new ObjectOutputStream(fos);
//		oos.writeObject(clanovi);
//		oos.close();
//		fos.close();
//	}
//	/**Serijalizuje listu izdavaca u data/izdavaci.ser, {@link DataManager#IZDAVACI}
//	 * @param izdavaci Lista svih izdavaca
//	 * @throws IOException u koliko je upisivanje objekta neuspesno*/
//	public static void serializeIzdavace(ArrayList<Izdavac> izdavaci) throws IOException {
//		fos = createOutputStream(FOLDER, IZDAVACI);
//		oos = new ObjectOutputStream(fos);
//		oos.writeObject(izdavaci);
//		oos.close();
//		fos.close();
//	}
//
//	public static ArrayList<Administrator> deserializeKorisnici() throws IOException, ClassNotFoundException {
//		ArrayList<Administrator> tempLista;
//		fis = createInputStream(FOLDER, KORISNICI);
//		ois = new ObjectInputStream(fis);
//		tempLista = (ArrayList<Administrator>) ois.readObject();
//
//		return tempLista;
//	}
//
//	/**Kreira izlazni tok za serijalizcaju podataka. Ako ne postoji folder, kreira ga.
//	 * @param folder Direktorijum gde treba da se sacuvaju podaci
//	 * @param data Ime datoteke gde treba da se sacuvaju podaci
//	 * @throws FileNotFoundException ako fajl 'data' ne postoji
//	 * @return new FileOutputStream(); null ako fajl ne postoji */
//	private static FileOutputStream createOutputStream(String folder, String data) throws FileNotFoundException {
//		File file = new File(folder);
//		if (!file.exists()) {
//			//LOGGER.info(String.format("Kreiranje foldera '%s'", folder));
//			if (file.mkdir()) {
//				//LOGGER.info(String.format("Kreiran folder '%s'", folder));
//			}
//		}
//		//LOGGER.info(String.format("Kreiranje izlaznog toka '%s'", data));
//		return new FileOutputStream(folder + data);
//	}
//
//	/**Kreira ulazni tok za deserijalizcaju podataka. Zahteva da postoji folder
//	 * @param folder Direktorijum gde se cuvaju podaci
//	 * @param data Ime datoteke u kojoj se cuvaju podaci
//	 * @throws FileNotFoundException ako fajl ili folder ne postoje
//	 * @return new FileInputStream(); null ako fajl ili folder ne postoje */
//	private static FileInputStream createInputStream(String folder, String data) throws FileNotFoundException {
//		LOGGER.info(String.format("Kreiranje ulaznog toka '%s'", data));
//		return new FileInputStream(folder + data);
//	}

	/**Brise sve fajlove iz 'data' foldera
	 * @param potvrda Potvrda da li da se brisu svi fajlovi.*/
	public static void resetSystem(boolean potvrda) {
		File folder;
		if (potvrda) {
			if ((folder = new File(FOLDER)).exists()) {
				for ( File fajl : folder.listFiles() ) {
					String temp = fajl.getName();
					if (!temp.contains("pravila")) {
						if(fajl.delete()) {
							//LOGGER.info(String.format("Obrisan fajl '%s'", temp));
							System.out.println(String.format("Obrisan fajl '%s'", temp));
						}
						else {
							//LOGGER.poruka(String.format("Nemoguce obrisati fajl '%s'", temp), EnumTipovi.ERROR);
							System.out.println(String.format("Nemoguce obrisati fajl '%s'", temp));
						}
					}
				}
			}
		}
	}
}
