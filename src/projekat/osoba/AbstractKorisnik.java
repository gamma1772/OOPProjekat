package projekat.osoba;

import java.io.Serializable;

public abstract class AbstractKorisnik implements Serializable {
	private String UUID;
	protected String ime, prezime;
	protected String jmbg, brTelefona;
	protected int pol;
	protected Dozvole dozvole;
	protected String username, email;
	protected transient Sifra password;
	/**Konstruktor u slucaju da se UUID kreira unutrasnjom {@link AbstractKorisnik#generisiUUID()} metodom*/
	public AbstractKorisnik(String ime, String prezime, String jmbg, String brTelefona, int pol, String username, Sifra password, String email, Dozvole dozvole) {
		generisiUUID();
		this.setDozvole(dozvole);
		this.setIme(ime);
		this.setPrezime(prezime);
		this.setJmbg(jmbg);
		this.setBrTelefona(brTelefona);
		this.setPol(pol);
		this.setUsername(username);
		this.setPassword(password);
		this.setEmail(email);
	}

	/**Konstruktor u slucaju da se UUID kreira spoljasnjom metodom*/
	public AbstractKorisnik(String UUID, String ime, String prezime, String jmbg, String brTelefona, int pol, String username, String email, Sifra password, Dozvole dozvole) {
		this.setDozvole(dozvole);
		this.setUUID(UUID);
		this.setIme(ime);
		this.setPrezime(prezime);
		this.setJmbg(jmbg);
		this.setBrTelefona(brTelefona);
		this.setPol(pol);
		this.setUsername(username);
		this.setEmail(email);
		this.setPassword(password);
	}

	/**Stampa tablicu dozvola u terminal / konzolu*/
//

	public void generisiUUID() {
		this.setUUID("");
	}

	public String punoIme() {
		return String.format("%s %s", getIme(), getPrezime());
	}

	public Dozvole getDozvole() {
		return dozvole;
	}

	public void setDozvole(Dozvole dozvole) {
		this.dozvole = dozvole;
	}

	public String getUUID() {
		return UUID;
	}

	public void setUUID(String UUID) {
		this.UUID = UUID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Sifra getPassword() {
		return password;
	}

	public void setPassword(Sifra password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	@Override
	public String toString() {
		return String.format("Ime: %s %s, E-mail: %s, Broj telefona: %s, JMBG: %s, Pol: %s, Korisnicko ime: %s",
			   getIme(), getPrezime(), getEmail(), getBrTelefona(), getJmbg(), pretvoriPolUString(getPol()), getUsername());
	}

	/**Unutrasnja klasa Dozvole, sluzi za postavljanje mogucnosti i dozvola korisnika. Omogucava lancano pozivanje funkcija za postavljanje dozvola*/
	public static class Dozvole implements IMogucnost{

		protected boolean isAdmin;

		//Dozvole korisnika
		protected boolean dodavanjeAdmina, dodavanjeKorisnika, dodavanjeKnjiga;
		protected boolean brisanjeAdmina, brisanjeKorisnika, brisanjeKnjiga;
		protected boolean pozajmljivanjeKnjiga, registracija;

		/**Osnovni konstruktor. Postavlja sve vrednosti na false*/
		public Dozvole() {
			this.isAdmin = false;
			this.dodavanjeAdmina = false;
			this.dodavanjeKorisnika = false;
			this.dodavanjeKnjiga = false;
			this.brisanjeAdmina = false;
			this.brisanjeKorisnika = false;
			this.brisanjeKnjiga = false;
			this.pozajmljivanjeKnjiga = false;
			this.registracija = false;
		}

		/*Sledece 'metode' se koriste za postavljanje vrednosti dozvola na true. Ove funkcije se mogu pozivati lancano*/
		public Dozvole admin() { this.isAdmin = true; return this; }
		public Dozvole dodavanjeAdmina() { this.dodavanjeAdmina = true; return this; }
		public Dozvole dodavanjeKorisnika() { this.dodavanjeKorisnika = true; return this; }
		public Dozvole dodavanjeKnjiga() { this.dodavanjeKnjiga = true; return this; }
		public Dozvole brisanjeAdmina() { this.brisanjeAdmina = true; return this; }
		public Dozvole brisanjeKorisnika() { this.brisanjeKorisnika = true; return this; }
		public Dozvole brisanjeKnjiga() { this.brisanjeKnjiga = true; return this; }
		public Dozvole pozajmljivanjeKnjiga() { this.pozajmljivanjeKnjiga = true; return this; }
		public Dozvole registracija() { this.registracija = true; return this; }

		/**Prikazuje tabelu dozvola u terminal*/
		public void proveraMogucnosti() {
			System.out.println("Dozvole:\tDodavanje\tBrisanje\tRegistracija\tPozajmljivanje");
			System.out.println("Admini:\t\t" + dodavanjeAdmina + "\t\t" + brisanjeAdmina + "\t\t" + "X" + "\t\t\t\t" + "X");
			System.out.println("Korisnici:\t" + dodavanjeKorisnika + "\t\t" + brisanjeKorisnika + "\t\t" + registracija + "\t\t\t" + "X");
			System.out.println("Knjige:\t\t" + dodavanjeKnjiga + "\t\t" + brisanjeKnjiga + "\t\t" + "X" + "\t\t\t\t" + pozajmljivanjeKnjiga);
		}

		@Override
		public boolean isAdmin() { return isAdmin; }
		@Override
		public boolean mozeDaDodajeNovogAdmina() { return dodavanjeAdmina; }
		@Override
		public boolean mozeDaDodajeNovogKorisnika() { return dodavanjeKorisnika; }
		@Override
		public boolean mozeDaDodajeNovuKnjigu() { return dodavanjeKnjiga; }
		@Override
		public boolean mozeDaSeRegistruje() { return registracija; }
		@Override
		public boolean mozeDaPozajmiKnjigu() { return pozajmljivanjeKnjiga; }
		@Override
		public boolean mozeDaBriseKorisnike() { return brisanjeKorisnika; }
		@Override
		public boolean mozeDaBriseAdministratore() { return brisanjeAdmina; }
		@Override
		public boolean mozeDaBriseKnjige() { return brisanjeKnjiga; }
	}
}
