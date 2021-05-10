package projekat.knjiga;

import projekat.util.debug.Logger;
import projekat.util.IJedinstveniIdentifikator;

import java.io.Serializable;

public class Autor implements Serializable {

	private transient static final Logger logger = new Logger("AUTOR");

	private int id;
	private String ime, prezime;

	public Autor(String ime, String prezime) {
		this.id = IJedinstveniIdentifikator.generateUUID(9999);
		this.setIme(ime);
		this.setPrezime(prezime);
	}

	public int getId() {
		return id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	/**Vraca puno ime autora*/
	public String getFullName() {
		return getIme() + " " + getPrezime();
	}

	@Override
	public String toString() {
		return String.format("Ime: %s %s, ID: %d", getIme(), getPrezime(), getId());
	}
}
