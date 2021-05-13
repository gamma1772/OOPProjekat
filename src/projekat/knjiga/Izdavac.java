package projekat.knjiga;

import projekat.util.debug.Logger;
import projekat.util.IUUID;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Izdavac implements Serializable, IUUID {
	private String id;
	private String imeIzdavaca, zemljaPorekla;

	private transient static final Logger logger = new Logger("IZDAVAC");

	public Izdavac(String imeIzdavaca, String zemljaPorekla) {
		this.id = generateUUID();
		this.setImeIzdavaca(imeIzdavaca);
		this.setZemljaPorekla(zemljaPorekla);
	}

	/**Konstruktor u slucaju da je zemlja izdavaca nepoznata*/
	public Izdavac(String imeIzdavaca) {
		this.id = generateUUID();
		this.setImeIzdavaca(imeIzdavaca);
		this.setZemljaPorekla("Nepoznato");
	}

	public Izdavac() {
		this.setImeIzdavaca("");
		this.setZemljaPorekla("");
	}

	public String getId() {
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

	@Override
	public String generateUUID() {
		SimpleDateFormat sdf = new SimpleDateFormat("UmmssSS");
		Random random = new Random();
		return String.format("%09d-%s", random.nextInt(), sdf.format(new Date()));
	}
}
