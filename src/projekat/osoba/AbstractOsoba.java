package projekat.osoba;

import projekat.util.IUUID;
import projekat.util.serijalizacija.ISerijalizacija;

public abstract class AbstractOsoba implements IUUID, ISerijalizacija {
	protected String UUID;
	protected String ime, prezime;
	protected String adresa, brTelefona, email;
	protected int pol;

	public AbstractOsoba(String UUID, String ime, String prezime, String adresa, String brTelefona, String email, int pol) {
		this.setUUID(UUID);
		this.setIme(ime);
		this.setPrezime(prezime);
		this.setAdresa(adresa);
		this.setBrTelefona(brTelefona);
		this.setEmail(email);
		this.setPol(pol);
	}

	public AbstractOsoba(String ime, String prezime, String adresa, String brTelefona, String email, int pol) {
		this.setIme(ime);
		this.setPrezime(prezime);
		this.setAdresa(adresa);
		this.setBrTelefona(brTelefona);
		this.setEmail(email);
		this.setPol(pol);
	}

	public AbstractOsoba() {
		this.setUUID("");
		this.setIme("");
		this.setPrezime("");
		this.setAdresa("");
		this.setBrTelefona("");
		this.setEmail("");
		this.setPol(0);
	}

	public abstract String getPunoIme();
	public abstract String toPrettyString();

	public String getUUID() {
		return UUID;
	}

	public void setUUID(String UUID) {
		this.UUID = UUID;
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



	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String jmbg) {
		this.adresa = jmbg;
	}

	public String getBrTelefona() {
		return brTelefona;
	}

	public void setBrTelefona(String brTelefona) {
		this.brTelefona = brTelefona;
	}

	public int getPol() {
		return pol;
	}

	public void setPol(int pol) {
		this.pol = pol;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/**Vraca string u zavisnosti od brojne vrednosti promenljive 'pol'.
	 * U koliko vrednost nije 0 ili 1, vraca null*/
	protected String pretvoriPolUString(int pol) {
		switch(pol) {
			case 0: return "Musko";
			case 1: return "Zensko";
			default: return null;
		}
	}

	@Override
	public String toStringSerializable() {
		return null;
	}

	@Override
	public String serializedFileName() {
		return null;
	}
}
