package projekat.osoba;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Clan extends AbstractOsoba {
	private ArrayList<Pozajmljivanje> pozajmljivanje;

	public Clan(String UUID, String ime, String prezime, String adresa, String brTelefona, int pol, String email, ArrayList<Pozajmljivanje> pozajmljivanje) {
		super(UUID, ime, prezime, adresa, brTelefona, email, pol);
		this.setPozajmljivanje(pozajmljivanje);
	}

	public Clan(String ime, String prezime, String adresa, String brTelefona, int pol, String email, ArrayList<Pozajmljivanje> pozajmljivanje) {
		super(ime, prezime, adresa, brTelefona, email, pol);
		this.setUUID(generateUUID());
		this.setPozajmljivanje(pozajmljivanje);
	}

	public Clan(String UUID, String ime, String prezime, String adresa, String brTelefona, int pol, String email) {
		super(UUID, ime, prezime, adresa, brTelefona, email, pol);
		this.setUUID(UUID);
		this.setPozajmljivanje(new ArrayList<>());
	}

	public Clan(String ime, String prezime, String adresa, String brTelefona, int pol, String email) {
		super(ime, prezime, adresa, brTelefona, email, pol);
		this.setUUID(generateUUID());
		this.setPozajmljivanje(new ArrayList<>());
	}

	public Clan() {
		super("", "", "", "", "", 0);
		this.setUUID("");
		this.setPozajmljivanje(new ArrayList<>());
	}

	@Override
	public String getPunoIme() {
		return String.format("%s %s", getIme(), getPrezime());
	}

	@Override
	public String toPrettyString() {
		return String.format("Ime: %s, Prezime: %s", getIme(), getPrezime());
	}

	public ArrayList<Pozajmljivanje> getPozajmljivanje() {
		return pozajmljivanje;
	}

	public void setPozajmljivanje(ArrayList<Pozajmljivanje> pozajmljivanje) {
		this.pozajmljivanje = pozajmljivanje;
	}

	@Override
	public String generateUUID() {
		SimpleDateFormat format = new SimpleDateFormat("ddMMHHmmss");
		Random random = new Random();
		return String.format("%s-%04d", format.format(new Date()), random.nextInt(9999));
	}

	public String toStringSerializable() {
		return String.format("%s~%s~%s~%s~%s~%d~%s", getUUID(), getIme(), getPrezime(), getAdresa(), getBrTelefona(), getPol(), getEmail());
	}
}
