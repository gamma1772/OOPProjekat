package projekat.osoba;

import projekat.util.IUUID;

public abstract class AbstractOsoba implements IUUID{
	protected String UUID;
	protected String ime, prezime;
	protected String adresa, brTelefona, email;
	protected int pol;

	public AbstractOsoba(String UUID, String ime, String prezime, String adresa, String brTelefona, String email, int pol) {
		this.UUID = UUID;
		this.ime = ime;
		this.prezime = prezime;
		this.adresa = adresa;
		this.brTelefona = brTelefona;
		this.email = email;
		this.pol = pol;
	}

	public AbstractOsoba(String ime, String prezime, String adresa, String brTelefona, String email, int pol) {
		this.ime = ime;
		this.prezime = prezime;
		this.adresa = adresa;
		this.brTelefona = brTelefona;
		this.email = email;
		this.pol = pol;
	}

	public AbstractOsoba() {
		this.UUID = "";
		this.ime = ""; this.prezime = "";
		this.adresa = ""; this.brTelefona = ""; this.email = "";
		this.pol = -1;
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
}