package projekat.knjiga;

import projekat.util.IUUID;
import projekat.util.debug.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Knjiga implements /*Serializable,*/ IUUID {

	private transient static final Logger LOGGER = new Logger("KNJIGA");

	private String id;
	private String imeKnjige, ISBN;
	private Autor autor;
	private Izdavac izdavac;
	private int[] zanrovi;
	private int godinaObjavljivanja, izdanje, brStrana, kategorija, kolicina;

	public Knjiga(String id, String imeKnjige, String ISBN, Autor autor, Izdavac izdavac, int[] zanrovi, int godinaObjavljivanja, int izdanje, int brStrana, int kategorija, int kolicina) {
		this.id = id;
		this.imeKnjige = imeKnjige;
		this.ISBN = ISBN;
		this.autor = autor;
		this.izdavac = izdavac;
		this.zanrovi = zanrovi;
		this.godinaObjavljivanja = godinaObjavljivanja;
		this.izdanje = izdanje;
		this.brStrana = brStrana;
		this.kategorija = kategorija;
		this.kolicina = kolicina;
	}

	public Knjiga(String imeKnjige, String ISBN, Autor autor, Izdavac izdavac, int[] zanrovi, int godinaObjavljivanja, int izdanje, int brStrana, int kategorija, int kolicina) {
		this.imeKnjige = imeKnjige;
		this.ISBN = ISBN;
		this.autor = autor;
		this.izdavac = izdavac;
		this.zanrovi = zanrovi;
		this.godinaObjavljivanja = godinaObjavljivanja;
		this.izdanje = izdanje;
		this.brStrana = brStrana;
		this.kategorija = kategorija;
		this.kolicina = kolicina;
		this.id = generateUUID();
		LOGGER.info("Generisan UUID " + id);
	}

	public Knjiga() {
		this.imeKnjige = "";
		this.ISBN = "";
		this.autor = new Autor();
		this.izdavac = new Izdavac();
		this.zanrovi = null;
		this.godinaObjavljivanja = 0;
		this.izdanje = 0;
		this.brStrana = 0;
		this.kategorija = 0;
		this.kolicina = 0;
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
}
