package projekat.osoba;

public class Sifra {
	private String korisnickiUUID, sifra;

	public Sifra(String korisnickiUUID, String sifra) {
		this.korisnickiUUID = korisnickiUUID;
		this.setSifra(sifrujLozinku(sifra));
	}

	public String getKorisnickiUUID() {
		return korisnickiUUID;
	}

	public void setKorisnickiUUID(String korisnickiUUID) {
		this.korisnickiUUID = korisnickiUUID;
	}

	public String getSifra() {
		return sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}

	//TODO
	private String sifrujLozinku(String sifra) {
		return "";
	}
	//TODO
	public static String desifrujLozinku(String sifra) {
		return "";
	}
}
