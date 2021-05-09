package projekat.knjiga;

import projekat.debug.Logger;
import projekat.util.IJedinstveniIdentifikator;

public class Izdavac {
	private int id;
	private String imeIzdavaca, zemljaPorekla;

	private static final Logger logger = new Logger("IZDAVAC");

	public Izdavac(String imeIzdavaca, String zemljaPorekla) {
		this.id	= IJedinstveniIdentifikator.generateUUID(9999);
		this.setImeIzdavaca(imeIzdavaca);
		this.setZemljaPorekla(zemljaPorekla);
	}

	/**Konstruktor u slucaju da je zemlja izdavaca nepoznata*/
	public Izdavac(String imeIzdavaca) {
		this.id	= IJedinstveniIdentifikator.generateUUID(9999);
		this.setImeIzdavaca(imeIzdavaca);
		this.setZemljaPorekla("Nepoznato");
	}

	public int getId() {
		return id;
	}

	public String getImeIzdavaca() {
		return imeIzdavaca;
	}

	public String getZemljaPorekla() {
		return zemljaPorekla;
	}

	public void setImeIzdavaca(String imeIzdavaca) {
		this.imeIzdavaca = imeIzdavaca;
	}

	public void setZemljaPorekla(String zemljaPorekla) {
		this.zemljaPorekla = zemljaPorekla;
	}

	@Override
	public String toString() {
		return String.format("Izdavac: %s, Zemlja porekla: %s, ID: %d", getImeIzdavaca(), getZemljaPorekla(), getId());
	}

	/**Vraca pojednostavljen String izdavaca.*/
	public String toSimpleString() {
		return String.format("%s, %s", getImeIzdavaca(), getZemljaPorekla());
	}
}
