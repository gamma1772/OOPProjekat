package projekat.util;

import java.util.Random;

public interface IJedinstveniIdentifikator {

	/**Generise nasumican broj od zadatog broja. Ovaj broj se koristi kao jedinstveni identifikator, ili deo IDa.*/
	static int generateUUID(int limiter) {
		Random random = new Random();
		return random.nextInt(limiter);
	}

}
