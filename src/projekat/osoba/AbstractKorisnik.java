package projekat.osoba;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public abstract class AbstractKorisnik extends AbstractOsoba implements Serializable {

	protected Dozvole dozvole;
	protected String username;
	protected transient Sifra password;


	public AbstractKorisnik(String UUID, String ime, String prezime, String adresa, String brTelefona, String email, int pol, String username, Sifra password, Dozvole dozvole) {
		super(UUID, ime, prezime, adresa, brTelefona, email, pol);
		this.dozvole = dozvole;
		this.username = username;
		this.password = password;
	}
	public AbstractKorisnik(String ime, String prezime, String adresa, String brTelefona, String email, int pol, String username, Sifra password, Dozvole dozvole) {
		super(ime, prezime, adresa, brTelefona, email, pol);
		this.dozvole = dozvole;
		this.username = username;
		this.password = password;
	}

	public AbstractKorisnik() {
		this.dozvole = new Dozvole("");
		this.username = "";
		this.password = new Sifra();
	}

	public Dozvole getDozvole() {
		return dozvole;
	}

	public void setDozvole(Dozvole dozvole) {
		this.dozvole = dozvole;
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

	/**Unutrasnja klasa Dozvole, sluzi za postavljanje mogucnosti i dozvola korisnika. Omogucava lancano pozivanje funkcija za postavljanje dozvola*/
	public static class Dozvole implements IMogucnost/*, Serializable*/{
		private String userUUID;

		protected boolean isAdmin;

		//Dozvole korisnika
		private boolean canAddAdmins, canAddMembers, canAddBooks;
		private boolean canDeleteAdmins, canDeleteMembers, canDeleteBooks;
		private boolean canLoanBooks, masterRule;
		private boolean canAlterRules;

		/**Osnovni konstruktor. Postavlja sve vrednosti na false*/
		public Dozvole(String userUUID) {
			this.userUUID = userUUID;
			this.isAdmin = false;
			this.canAddAdmins = false;
			this.canAddMembers = false;
			this.canAddBooks = false;
			this.canDeleteAdmins = false;
			this.canDeleteMembers = false;
			this.canDeleteBooks = false;
			this.canLoanBooks = false;
			this.canAlterRules = false;
			this.masterRule = false;
		}

		/*Sledece 'metode' se koriste za postavljanje vrednosti dozvola na true. Ovi konstruktori se mogu pozivati lancano*/
		public Dozvole admin() { this.isAdmin = true; return this; }
		public Dozvole addAdmins() { this.canAddAdmins = true; return this; }
		public Dozvole addMembers() { this.canAddMembers = true; return this; }
		public Dozvole addBooks() { this.canAddBooks = true; return this; }
		public Dozvole deleteAdmins() { this.canDeleteAdmins = true; return this; }
		public Dozvole deleteMembers() { this.canDeleteMembers = true; return this; }
		public Dozvole deleteBooks() { this.canDeleteBooks = true; return this; }
		public Dozvole loanBooks() { this.canLoanBooks = true; return this; }
		public Dozvole alterRules() {this.canAlterRules = true; return this;}
		public Dozvole masterRule() { this.masterRule = true; return this; }

		/**Prikazuje tabelu dozvola u terminal*/
		public void proveraMogucnosti() {
			System.out.println("Dozvole:\tDodavanje\tBrisanje\tRegistracija\tPozajmljivanje");
			System.out.println("Admini:\t\t" + canAddAdmins + "\t\t" + canDeleteAdmins + "\t\t" + "X" + "\t\t\t\t" + "X");
			System.out.println("Korisnici:\t" + canAddMembers + "\t\t" + canDeleteMembers + "\t\t" + masterRule + "\t\t\t" + "X");
			System.out.println("Knjige:\t\t" + canAddBooks + "\t\t" + canDeleteBooks + "\t\t" + "X" + "\t\t\t\t" + canLoanBooks);
		}

		@Override
		public boolean isAdmin() { return isAdmin; }
		@Override
		public boolean canAddAdmins() { return canAddAdmins; }
		@Override
		public boolean canAddMembers() { return canAddMembers; }
		@Override
		public boolean canAddBooks() { return canAddBooks; }
		@Override
		public boolean hasMasterRule() { return masterRule; }
		@Override
		public boolean canLoanBooks() { return canLoanBooks; }
		@Override
		public boolean canDeleteMembers() { return canDeleteMembers; }
		@Override
		public boolean canDeleteAdmins() { return canDeleteAdmins; }
		@Override
		public boolean canDeleteBooks() { return canDeleteBooks; }

		public String toStringSerializable() {
			return String.format("%s~%b~%b~%b~%b~%b~%b~%b~%b~%b", userUUID, isAdmin(), canAddAdmins(), canAddMembers(), canAddBooks(), hasMasterRule(), canLoanBooks(), canDeleteBooks(), canDeleteAdmins(), canDeleteBooks());
		}
	}
}
