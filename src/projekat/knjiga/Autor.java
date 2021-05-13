package projekat.knjiga;

import projekat.util.debug.Logger;
import projekat.util.IUUID;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Autor implements Serializable, IUUID {

	private transient static final Logger LOGGER = new Logger("AUTOR");

	private String id;
	private String ime, prezime;

	public Autor(String ime, String prezime) {
		this.setIme(ime);
		this.setPrezime(prezime);
		this.id = generateUUID();
	}

	public String getId() {
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

	@Override
	public String generateUUID() {
		SimpleDateFormat sdf = new SimpleDateFormat("mmssSS");
		Random random = new Random();
		return String.format("%05d-%s", random.nextInt(), sdf.format(new Date()));
	}
}
