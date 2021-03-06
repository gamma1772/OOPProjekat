package projekat.knjiga;

import projekat.util.IUUID;
import projekat.util.serijalizacija.DataManager;
import projekat.util.serijalizacija.ISerijalizacija;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Autor implements IUUID, ISerijalizacija {

	private String id;
	private String ime, prezime;

	public Autor(String id, String ime, String prezime) {
		this.setIme(ime);
		this.setPrezime(prezime);
		this.setId(id);
	}

	public Autor() {
		this.setIme("");
		this.setPrezime("");
		this.setId("");
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
	public String generateUUID() {
		SimpleDateFormat sdf = new SimpleDateFormat("mmssSS");
		Random random = new Random();
		return String.format("%05d-%s", Math.abs(random.nextInt()), sdf.format(new Date()));
	}

	@Override
	public String toString() {
		return String.format("Ime: %s, ID: %s", getFullName(), getId());
	}

	public String toStringSerializable() {
		return String.format("%s~%s~%s", getId(), getIme(), getPrezime());
	}

	@Override
	public String serializedFileName() {
		return "autor.tdb";
	}

	@Override
	public void serialize() {
		try {
			DataManager.serializeString(toStringSerializable(), serializedFileName(), true);
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
}
