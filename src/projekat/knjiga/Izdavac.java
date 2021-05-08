package projekat.knjiga;

import projekat.util.IJedinstveniIdentifikator;

public class Izdavac extends AbstractAtribut{
	public Izdavac(String imeIzdavaca) {
		this.imeAtributa = imeIzdavaca;
		this.id = IJedinstveniIdentifikator.generateUUID(9999);
	}
}
