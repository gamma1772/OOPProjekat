package projekat.osoba;

import projekat.util.serijalizacija.DataManager;
import projekat.util.serijalizacija.ISerijalizacija;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Administrator extends AbstractOsoba {

	protected Dozvole dozvole;
	protected String username;
	protected Sifra password;

	public Administrator(String UUID, String ime, String prezime, String adresa, String brTelefona, String email, int pol, String username, Sifra password, Dozvole dozvole) {
		super(UUID, ime, prezime, adresa, brTelefona, email, pol);
		this.setDozvole(dozvole);
		this.setUsername(username);
		this.setPassword(password);
	}

	public Administrator(String ime, String prezime, String adresa, String brTelefona, String email, int pol, String username, Sifra password, Dozvole dozvole) {
		super(ime, prezime, adresa, brTelefona, email, pol);
		this.setUUID(generateUUID());
		this.setDozvole(dozvole);
		this.setUsername(username);
		this.setPassword(password);
	}

	public Administrator() {
		super("", "", "", "", "" ,"", 0);
		this.setDozvole(new Dozvole());
		this.setUsername("");
		this.setPassword(new Sifra());
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

	@Override
	public String generateUUID() {
		SimpleDateFormat format = new SimpleDateFormat("ddMMyyyyHHmmss");
		Random random = new Random();
		return String.format("%s-%05d", format.format(new Date()), random.nextInt(99999));
	}

	@Override
	public String getPunoIme() {
		return String.format("%s %s", getIme(), getPrezime());
	}

	@Override
	public String toPrettyString() {
		return String.format("Ime: %s, E-mail: %s, Broj telefona: %s, Adresa: %s, Pol: %s, Korisnicko ime: %s, UUID: %s",
				getPunoIme(), getEmail(), getBrTelefona(), getAdresa(), super.pretvoriPolUString(getPol()), getUsername(), getUUID());
	}

	@Override
	public String toStringSerializable() {
		return String.format("%s~%s~%s~%s~%s~%s~%d~%s",
				getUUID(), getIme(), getPrezime(), getAdresa(), getBrTelefona(), getEmail(), getPol(), getUsername());
	}

	@Override
	public String serializedFileName() {
		return "administrator.tdb";
	}

	@Override
	public void serialize() {
		try {
			DataManager.serializeString(toStringSerializable(), serializedFileName());
			password.serialize();
			dozvole.serialize();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	/*===================================================================
	 * 						  D O Z V O L E
	 *===================================================================*/

	/**Unutrasnja klasa Dozvole, sluzi za postavljanje mogucnosti i dozvola korisnika. Omogucava lancano pozivanje funkcija za postavljanje dozvola*/
	public static class Dozvole implements IMogucnost, ISerijalizacija {
		private String userUUID;

		protected boolean isAdmin;

		//Dozvole korisnika
		private boolean canAddAdmins, canAddMembers, canAddBooks;
		private boolean canDeleteAdmins, canDeleteMembers, canDeleteBooks;
		private boolean canLoanBooks, masterRule;
		private boolean canAlterRules;

		/**Osnovni konstruktor. Postavlja sve vrednosti na false*/
		public Dozvole(String userUUID) {
			this.setUserUUID(userUUID);
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

		public Dozvole() {
			this.setUserUUID("");
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
		//TODO: Novi format.
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
		@Override
		public boolean canAlterRules() { return canAlterRules; }

		public String getUserUUID() {
			return userUUID;
		}

		public void setUserUUID(String userUUID) {
			this.userUUID = userUUID;
		}

		public void setAdmin(boolean admin) {
			isAdmin = admin;
		}

		public void setCanAddAdmins(boolean canAddAdmins) {
			this.canAddAdmins = canAddAdmins;
		}

		public void setCanAddMembers(boolean canAddMembers) {
			this.canAddMembers = canAddMembers;
		}

		public void setCanAddBooks(boolean canAddBooks) {
			this.canAddBooks = canAddBooks;
		}

		public void setCanDeleteAdmins(boolean canDeleteAdmins) {
			this.canDeleteAdmins = canDeleteAdmins;
		}

		public void setCanDeleteMembers(boolean canDeleteMembers) {
			this.canDeleteMembers = canDeleteMembers;
		}

		public void setCanDeleteBooks(boolean canDeleteBooks) {
			this.canDeleteBooks = canDeleteBooks;
		}

		public void setCanLoanBooks(boolean canLoanBooks) {
			this.canLoanBooks = canLoanBooks;
		}

		public void setMasterRule(boolean masterRule) {
			this.masterRule = masterRule;
		}

		public void setCanAlterRules(boolean canAlterRules) {
			this.canAlterRules = canAlterRules;
		}

		public String toStringSerializable() {
			return String.format("%s~%b~%b~%b~%b~%b~%b~%b~%b~%b~%b", getUserUUID().toString(), isAdmin(), canAddAdmins(), canAddMembers(), canAddBooks(), hasMasterRule(), canLoanBooks(), canDeleteAdmins(), canDeleteMembers(), canDeleteBooks(), canAlterRules());
		}

		@Override
		public String serializedFileName() {
			return "dozvole.tdb";
		}

		@Override
		public void serialize() {
			try {
				DataManager.serializeString(toStringSerializable(), serializedFileName());
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		}
	}
}
