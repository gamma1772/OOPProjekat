package projekat.knjiga;

public class Knjiga {
	private String imeKnjige, imePisca, imeIzdavaca, ISBN;
	private int[] zanrovi;
	private int godinaObjavljivanja, izdanje, brStrana, kategorija;

	public Knjiga(String imeKnjige, Autor autor, Izdavac izdavac, String ISBN, int[] zanrovi, int kategorija, int godinaObjavljivanja, int izdanje, int brStrana) {
		this.imeKnjige = imeKnjige;
		this.imePisca = autor.getImeAtributa();
		this.imeIzdavaca = izdavac.getImeAtributa();
		this.ISBN = ISBN;
		this.zanrovi = zanrovi;
		this.kategorija = kategorija;
		this.godinaObjavljivanja = godinaObjavljivanja;
		this.izdanje = izdanje;
		this.brStrana = brStrana;
	}

	public String getImeKnjige() {
		return imeKnjige;
	}

	public void setImeKnjige(String imeKnjige) {
		this.imeKnjige = imeKnjige;
	}

	public String getImePisca() {
		return imePisca;
	}

	public void setImePisca(String imePisca) {
		this.imePisca = imePisca;
	}

	public String getImeIzdavaca() {
		return imeIzdavaca;
	}

	public void setImeIzdavaca(String imeIzdavaca) {
		this.imeIzdavaca = imeIzdavaca;
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

	public void setIzdanje(int izdanje) {
		this.izdanje = izdanje;
	}

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
}
