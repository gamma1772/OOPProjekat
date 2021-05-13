package projekat.knjiga;

import projekat.util.debug.Logger;

import java.io.Serializable;

public class Knjiga implements Serializable {

	private transient static final Logger LOGGER = new Logger("KNJIGA");

	private String imeKnjige, ISBN;
	private Autor autor;
	private Izdavac izdavac;
	private int[] zanrovi;
	private int godinaObjavljivanja, izdanje, brStrana, kategorija, kolicina;

	public Knjiga(String imeKnjige, Autor autor, Izdavac izdavac, String ISBN, int[] zanrovi, int kategorija, int godinaObjavljivanja, int izdanje, int brStrana, int kolicina) {
		this.setImeKnjige(imeKnjige);;
		this.setAutor(autor);
		this.setIzdavac(izdavac);
		this.setISBN(ISBN);
		this.setZanrovi(zanrovi);
		this.setKategorija(kategorija);
		this.setGodinaObjavljivanja(godinaObjavljivanja);
		this.setIzdanje(izdanje);
		this.setBrStrana(brStrana);
		this.setKolicina(kolicina);
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

	/** Smesta sve zanrove knjige u jedan String. koristi se u toString metodi. */
	private String zanroviConcat() {
		StringBuilder zanrString = new StringBuilder();
		for (int zanr : getZanrovi()) {
			zanrString.append(EnumZanr.getZanr(zanr).name().toLowerCase()).append(" ");
		}
		return zanrString.toString().trim();
	}

	@Override
	public String toString() {
		return String.format("Naslov: %s, Autor: %s, Izdavac: %s, Godina izdavanja: %d, Izdanje: %d, Broj Strana: %d, ISBN: %s, Kategorija: %s, Zanrovi: %s",
  			   getImeKnjige(), getAutor().getFullName(), getIzdavac().toSimpleString(), getGodinaObjavljivanja(), getIzdanje(), getBrStrana(), getISBN(), EnumKategorija.getKategorija(getKategorija()).name().toLowerCase(), zanroviConcat());
	}
}
