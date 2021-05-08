package projekat.osoba;

public abstract class AbstractOsoba {

	protected String ime, prezime;
	protected String jmbg, brTelefona;
	protected int pol;

	public abstract String punoIme(String ime, String prezime);

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

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
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
	/**Vraca string u zavisnosti od brojne vrednosti promenljive 'pol'.
	 * U koliko vrednost nije 0 ili 1, vraca String "Nepoznato"*/
	private String pretvoriPolUString(int pol) {
		switch(pol) {
			case 0: return "Musko";
			case 1: return "Zensko";
			default: return "Nepoznato";
		}
	}
}