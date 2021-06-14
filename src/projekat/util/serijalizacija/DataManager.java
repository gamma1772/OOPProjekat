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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**Staticka klasa koja sadrzi metode za serijalizaciju i deserijalizcaju objekata.*/
public class DataManager {

	private static final String FOLDER = "data//";

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

	/**Vrsi upisivanje Stringa u fajl. String sadrzi podatke objekta koji treba da se "Serijalizuje".
	 * @param object Objekat koji je formatiran pomocu toStringSerializable()
	 * @param fileName Ime fajla gde treba da se cuva objekat.
	 * @param append Da li da se sadrzaj dopisuje na kraj fajla, ili da pise fajl od pocetka
	 * @throws IOException u koliko upisivanje objekta nije uspelo*/
	public static void serializeString(String object, String fileName, boolean append) throws IOException {
		bw = new BufferedWriter(new FileWriter(FOLDER + fileName, append));

		bw.write(object + '\n');

		bw.flush();
		bw.close();
	}

	public static void serializeFromGenericArray(ArrayList<?> list) {
		if (list != null && list.size() != 0) {
				try {
					if (list.get(0) instanceof Administrator)
						clearData("administrator.tdb");
					else if (list.get(0) instanceof Knjiga)
						clearData("knjiga.tdb");
					else if (list.get(0) instanceof Clan)
						clearData("clan.tdb");
					else if (list.get(0) instanceof Autor)
						clearData("autor.tdb");
					else if (list.get(0) instanceof Izdavac)
						clearData("izdavac.tdb");
					else if (list.get(0) instanceof Sifra)
						clearData("sifra.tdb");
					else if (list.get(0) instanceof Pozajmljivanje)
						clearData("pozajmljivanje.tdb");
					else if (list.get(0) instanceof Administrator.Dozvole)
						clearData("dozvole.tdb");
					else if (list.get(0) instanceof PravilaBiblioteke)
						clearData("pravila.tdb");
				} catch (IOException exception) {
					exception.printStackTrace();
			}
			for (Object o : list) {
				if (o instanceof Administrator) {
					((Administrator) o).serialize();
				}
				else if (o instanceof Sifra) {
					((Sifra) o).serialize();
				}
				else if (o instanceof Administrator.Dozvole) {
					((Administrator.Dozvole) o).serialize();
				}
				else if (o instanceof Clan) {
					((Clan) o).serialize();
				}
				else if (o instanceof Knjiga) {
					((Knjiga) o).serialize();
				}
				else if (o instanceof Autor) {
					((Autor) o).serialize();
				}
				else if (o instanceof Izdavac) {
					((Izdavac) o).serialize();
				}
				else if (o instanceof Pozajmljivanje) {
					((Pozajmljivanje) o).serialize();
				}
				else if (o instanceof PravilaBiblioteke) {
					((PravilaBiblioteke) o).serialize();
				}
			}
		}
	}

	private static void clearData(String file) throws IOException {
		bw = new BufferedWriter(new FileWriter(FOLDER + file, false));
		bw.write("");
		bw.close();
	}


	public static void serializeGeneric(Object o) {
		if (o instanceof Administrator) {
			try {
				clearData("administrator.tdb");
			} catch (IOException exception) {
				exception.printStackTrace();
			}
			((Administrator) o).serialize();
		}
		else if (o instanceof Sifra) {
			try {
				clearData("sifra.tdb");
			} catch (IOException exception) {
				exception.printStackTrace();
			}
			((Sifra) o).serialize();
		}
		else if (o instanceof Administrator.Dozvole) {
			try {
				clearData("dozvole.tdb");
			} catch (IOException exception) {
				exception.printStackTrace();
			}
			((Administrator.Dozvole) o).serialize();
		}
		else if (o instanceof Clan) {
			try {
				clearData("clan.tdb");
			} catch (IOException exception) {
				exception.printStackTrace();
			}
			((Clan) o).serialize();
		}
		else if (o instanceof Knjiga) {
			try {
				clearData("knjiga.tdb");
			} catch (IOException exception) {
				exception.printStackTrace();
			}
			((Knjiga) o).serialize();
		}
		else if (o instanceof Autor) {
			try {
				clearData("autor.tdb");
			} catch (IOException exception) {
				exception.printStackTrace();
			}
			((Autor) o).serialize();
		}
		else if (o instanceof Izdavac) {
			try {
				clearData("izdavac.tdb");
			} catch (IOException exception) {
				exception.printStackTrace();
			}
			((Izdavac) o).serialize();
		}
		else if (o instanceof Pozajmljivanje) {
			try {
				clearData("pozajmljivanje.tdb");
			} catch (IOException exception) {
				exception.printStackTrace();
			}
			((Pozajmljivanje) o).serialize();
		}
		else if (o instanceof PravilaBiblioteke) {
			try {
				clearData("pravila.tdb");
			} catch (IOException exception) {
				exception.printStackTrace();
			}
			((PravilaBiblioteke) o).serialize();
		}
	}

// --Commented out by Inspection START (14.6.2021. 16:40):
//	/**Vrsi serijalizaciju liste stringova koji sadrze informacije o objektu.
//	 * @param objects Lista stringova
//	 * @param fileName Datoteka u kojoj ce se cuvati podaci
//	 * @param append Da li da obrise sve iz fajla i napise sadrzaj liste, ili da doda sadrzaj liste ispod postojeceg sadrzaja
//	 * @throws IOException u koliko ne moze da se upisu podaci u fajl.*/
//	public static void serializeString(ArrayList<String> objects, String fileName, boolean append) throws IOException {
//		bw = new BufferedWriter(new FileWriter(FOLDER + fileName, append));
//
//		for (String object : objects) {
//			bw.write(object + '\n');
//		}
//
//		bw.flush();
//		bw.close();
//	}
// --Commented out by Inspection STOP (14.6.2021. 16:40)

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

		while((line = br.readLine()) != null  && !line.equals("") && !line.equals("\n")) {
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

		br.close();
		return administratori;
	}

	/**Vrsi deserijalizaciju clanova. Zahteva da se pre toga deserijalizuje lista pozajmljivanja*/
	public static ArrayList<Clan> deserializeClanovi(ArrayList<Pozajmljivanje> pozajmljivanja) throws IOException, TokProgramaException {
		if (pozajmljivanja == null) {
			throw new TokProgramaException("Lista pozajmljivanja mora biti deserijalizovana pre clanova biblioteke!");
		}

		String line;
		String[] lista;
		ArrayList<Clan> clanovi = new ArrayList<>();
		br = new BufferedReader(new FileReader(FOLDER + "clan.tdb"));

		while((line = br.readLine()) != null  && !line.equals("") && !line.equals("\n")) {
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

		br.close();
		return clanovi;
	}
	/**Vrsi deserijalizaciju sifara. Ne zahteva prethodne deserijalizacije*/
	public static ArrayList<Sifra> deserializeSifre() throws IOException {

		String line;
		String[] lista;
		ArrayList<Sifra> sifre = new ArrayList<>();
		br = new BufferedReader(new FileReader(FOLDER + "sifra.tdb"));

		while((line = br.readLine()) != null  && !line.equals("") && !line.equals("\n")) {
			Sifra s = new Sifra();
			lista = line.split("~");

			s.setKorisnickiUUID(lista[0]);
			s.setSifra(lista[1]);

			sifre.add(s);
		}

		br.close();
		return sifre;
	}

	/**Vrsi deserijalizaciju clanova. Zahteva da se pre toga deserijalizuje lista autora i izdavaca*/
	public static ArrayList<Knjiga> deserializeKnjige(ArrayList<Autor> autori, ArrayList<Izdavac> izdavaci) throws IOException, TokProgramaException {

		if (autori == null || autori.size() == 0) {
			throw new TokProgramaException("Lista autora mora biti deserijalizovana pre knjiga!");
		}
		else if (izdavaci == null || izdavaci.size() == 0) {
			throw new TokProgramaException("Lista izdavaca mora biti deserijalizovana pre knjiga");
		}

		String line;
		String[] lista;
		ArrayList<Knjiga> knjige = new ArrayList<>();
		br = new BufferedReader(new FileReader(FOLDER + "knjiga.tdb"));

		while((line = br.readLine()) != null  && !line.equals("") && !line.equals("\n")) {
			String[] zanrovi, autors;
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
				if (zanrovi[i] != null && !zanrovi[i].equals("")) {
					zanrList[i] = Integer.parseInt(zanrovi[i]);
				}
			}
			k.setZanrovi(zanrList);

			autors = lista[2].replace("(", "").replace(")", "").split(";");
			for (Autor a : autori) {
				for (String autor : autors) {
					if (autor != null && !autor.equals("")) {
						if (a.getId().equals(autor)) {
							k.getAutori().add(a);
						}
					}
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

		br.close();
		return knjige;
	}

	/**Vrsi deserijalizaciju dozvola administratora. Ne zahteva prethodne deserijalizacije*/
	public static ArrayList<Administrator.Dozvole> deserializeDozvole() throws IOException{
		String line;
		String[] lista;
		ArrayList<Administrator.Dozvole> dozvole = new ArrayList<>();
		br = new BufferedReader(new FileReader(FOLDER + "dozvole.tdb"));

		while((line = br.readLine()) != null && !line.equals("") && !line.equals("\n")) {
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
			d.setCanEditAdmins(Boolean.parseBoolean(lista[11]));
			d.setCanEditMembers(Boolean.parseBoolean(lista[12]));
			d.setCanEditBooks(Boolean.parseBoolean(lista[13]));

			dozvole.add(d);
		}

		br.close();
		return dozvole;
	}

	public static ArrayList<Autor> deserializeAutore() throws IOException{
		String line;
		String[] lista;
		ArrayList<Autor> autori = new ArrayList<>();
		br = new BufferedReader(new FileReader(FOLDER + "autor.tdb"));

		while((line = br.readLine()) != null  && !line.equals("") && !line.equals("\n")) {
			lista = line.split("~");

			Autor a = new Autor(lista[0], lista[1], lista[2]);

			autori.add(a);
		}

		br.close();
		return autori;
	}

	public static ArrayList<Izdavac> deserializeIzdavace() throws IOException{
		String line;
		String[] lista;
		ArrayList<Izdavac> izdavaci = new ArrayList<>();
		br = new BufferedReader(new FileReader(FOLDER + "izdavac.tdb"));

		while((line = br.readLine()) != null  && !line.equals("") && !line.equals("\n")) {

			lista = line.split("~");

			Izdavac i = new Izdavac(lista[0], lista[1], lista[2]);

			izdavaci.add(i);
		}

		br.close();
		return izdavaci;
	}

	public static ArrayList<Pozajmljivanje> deserializePozajmljivanje(ArrayList<Knjiga> knjige) throws IOException, ParseException, TokProgramaException {

		if (knjige == null || knjige.size() == 0) {
			throw new TokProgramaException("Lista knjiga mora biti deserijalizovana pre liste pozajmljivanja!");
		}

		String line;
		String[] lista;
		ArrayList<Pozajmljivanje> pozajmljivanjaArrayList = new ArrayList<>();

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		br = new BufferedReader(new FileReader(FOLDER + "pozajmljivanje.tdb"));

		while((line = br.readLine()) != null  && !line.equals("") && !line.equals("\n")) {
			Pozajmljivanje p = new Pozajmljivanje();
			lista = line.split("~");

			p.setClanUUID(lista[0]);
			p.setDug(Double.parseDouble(lista[1].replace(',', '.')));

			for (Knjiga k : knjige) {
				if (k.getId().equals(lista[2])) {
					p.setPozajmljenaKnjiga(k);
				}
			}
			Calendar cal = Calendar.getInstance();
			Calendar cal2 = Calendar.getInstance();
			Date date;
			date = dateFormat.parse(lista[3]);
			cal.setTime(date);
			p.setDatumPozajmljivanja(cal);

			date = dateFormat.parse(lista[4]);
			cal2.setTime(date);
			p.setDatumVracanja(cal2);

			p.setRazreseno(Boolean.parseBoolean(lista[5]));
			p.setProduzenoPuta(Integer.parseInt(lista[6]));

			pozajmljivanjaArrayList.add(p);
		}

		br.close();
		return pozajmljivanjaArrayList;
	}

	public static PravilaBiblioteke deserializePravila() throws IOException{
		String line;
		String[] lista;
		PravilaBiblioteke pravila = new PravilaBiblioteke();
		br = new BufferedReader(new FileReader(FOLDER + "pravila.tdb"));

		if ((line = br.readLine()) != null  && !line.equals("") && !line.equals("\n")) {
			lista = line.split("~");
			pravila.maxPeriod(Integer.parseInt(lista[0]))
					.maxReloan(Integer.parseInt(lista[1]))
					.multiplier(Double.parseDouble(lista[2].replace(',', '.')))
					.loanMultipleAtOnce(Boolean.parseBoolean(lista[3]))
					.setLoanBeforeReturningPrevious(Boolean.parseBoolean(lista[4]));
		}

		br.close();
		return pravila;
	}

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
							System.out.printf("Obrisan fajl '%s'%n", temp);
						}
						else {
							System.out.printf("Nemoguce obrisati fajl '%s'%n", temp);
						}
					}
				}
			}
		}
	}

	public static void createFolder() {
		File folder = new File(FOLDER);
		if (!folder.exists()) {
			if (folder.mkdir()) {
				System.out.println("Kreiran folder data/");
			}
		}

	}

	public static void createFileEntries() throws IOException {
		createFolder();
		ArrayList<String> files = new ArrayList<>() {{
			add("administrator.tdb");
			add("clan.tdb");
			add("knjiga.tdb");
			add("autor.tdb");
			add("izdavac.tdb");
			add("dozvole.tdb");
			add("pravila.tdb");
			add("sifra.tdb");
			add("pozajmljivanje.tdb");
		}};

		for (String s : files) {
			File file = new File(FOLDER + s);
			if (file.createNewFile()) {
				System.out.printf("Kreiran fajl '%s'%n", s);
			}
		}
	}
}
