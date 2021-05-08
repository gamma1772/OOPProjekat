package projekat.knjiga;

import projekat.util.IJedinstveniIdentifikator;

public class Autor extends AbstractAtribut {
	public Autor(String imeAutora) {
		this.setImeAtributa(imeAutora);
		this.setId(IJedinstveniIdentifikator.generateUUID(9999));
	}
}
