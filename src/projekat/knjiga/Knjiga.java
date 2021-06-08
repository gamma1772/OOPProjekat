package projekat.knjiga;

import projekat.util.IUUID;
import projekat.util.serijalizacija.ISerijalizacija;

import java.util.Random;

public class Knjiga implements IUUID, ISerijalizacija {


	private String id;
	private String imeKnjige, ISBN;
	private Autor autor;
	private Izdavac izdavac;
	private int[] zanrovi;
	private int godinaObjavljivanja, izdanje, brStrana, kategorija, kolicina;

	public Knjiga(String id, String imeKnjige, String ISBN, Autor autor, Izdavac izdavac, int[] zanrovi, int godinaObjavljivanja, int izdanje, int brStrana, int kategorija, int kolicina) {
		this.setId(id);
		this.setImeKnjige(imeKnjige);
		this.setISBN(ISBN);
		this.setAutor(autor);
		this.setIzdavac(izdavac);
		this.setZanrovi(zanrovi);
		this.setGodinaObjavljivanja(godinaObjavljivanja);
		this.setIzdanje(izdanje);
		this.setBrStrana(brStrana);
		this.setKategorija(kategorija);
		this.setKolicina(kolicina);
	}

	public Knjiga(String imeKnjige, String ISBN, Autor autor, Izdavac izdavac, int[] zanrovi, int godinaObjavljivanja, int izdanje, int brStrana, int kategorija, int kolicina) {
		this.setId(generateUUID());
		this.setImeKnjige(imeKnjige);
		this.setISBN(ISBN);
		this.setAutor(autor);
		this.setIzdavac(izdavac);
		this.setZanrovi(zanrovi);
		this.setGodinaObjavljivanja(godinaObjavljivanja);
		this.setIzdanje(izdanje);
		this.setBrStrana(brStrana);
		this.setKategorija(kategorija);
		this.setKolicina(kolicina);
	}

	public Knjiga() {
		this.setId("");
		this.setImeKnjige("");
		this.setISBN("");
		this.setAutor(new Autor());
		this.setIzdavac(new Izdavac());
		this.setZanrovi(null);
		this.setGodinaObjavljivanja(0);
		this.setIzdanje(0);
		this.setBrStrana(0);
		this.setKategorija(0);
		this.setKolicina(0);
	}

	public int getKolicina() {
		return kolicina;
	}

	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}

	public String getImeKnjige() { return imeKnjige; }

	public void setImeKnjige(String imeKnjige) {
		this.imeKnjige = imeKnjige;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public Izdavac getIzdavac() {
		return izdavac;
	}

	public void setIzdavac(Izdavac izdavac) {
		this.izdavac = izdavac;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String ISBN) {
		this.ISBN = ISBN;
	}

	public int[] getZanrovi() {
		return zanrovi;
	}

	public void setZanrovi(int[] zanrovi) {
		this.zanrovi = zanrovi;
	}

	public int getGodinaObjavljivanja() {
		return godinaObjavljivanja;
	}

	public void setGodinaObjavljivanja(int godinaObjavljivanja) {
		this.godinaObjavljivanja = godinaObjavljivanja;
	}

	public int getIzdanje() {
		return izdanje;
	}

	public void setIzdanje(int izdanje) { this.izdanje = izdanje; }

	public int getBrStrana() {
		return brStrana;
	}

	public void setBrStrana(int brStrana) {
		this.brStrana = brStrana;
	}

	public int getKategorija() {
		return kategorija;
	}

	public void setKategorija(int kategorija) {
		this.kategorija = kategorija;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/** Smesta sve zanrove knjige u jedan String. koristi se u toString metodi. */
	private String zanroviConcat() {
		StringBuilder zanrString = new StringBuilder();
		for (int zanr : getZanrovi()) {
			zanrString.append(EnumZanr.getZanr(zanr).name().toLowerCase()).append(" ");
		}
		return zanrString.toString().trim();
	}

	private String zanroviConcatSerializable() {
		StringBuilder zanrString = new StringBuilder();
		for (int zanr : getZanrovi()) {
			zanrString.append(EnumZanr.getZanr(zanr).name().toLowerCase()).append(";");
		}
		return zanrString.toString();
	}

	@Override
	public String generateUUID() {
		Random random = new Random();
		int count = 0;
		for (char c : ISBN.toCharArray()) {
			count += (int)c;
		}
		return String.format("%A7d-%07d", count, random.nextInt());
	}

	@Override
	public String toString() {
		return String.format("Naslov: %s, Autor: %s, Izdavac: %s, Godina izdavanja: %d, Izdanje: %d, Broj Strana: %d, ISBN: %s, Kategorija: %s, Zanrovi: %s",
				getImeKnjige(), getAutor().getFullName(), getIzdavac().toSimpleString(), getGodinaObjavljivanja(), getIzdanje(), getBrStrana(), getISBN(), EnumKategorija.getKategorija(getKategorija()).name().toLowerCase(), zanroviConcat());
	}

	public String toStringSerializable() {
		return String.format("%s~%s~%s~%s~%d~%d~%d~%s~%s~%d~(%s)",
				getId(), getImeKnjige(), getAutor().getId(), getIzdavac().getId(), getGodinaObjavljivanja(), getIzdanje(), getBrStrana(), getISBN(), getKategorija(), getKolicina(), zanroviConcatSerializable());
	}

	@Override
	public String serializedFileName() {
		return null;
	}

	@Override
	public void serialize() {

	}
}
