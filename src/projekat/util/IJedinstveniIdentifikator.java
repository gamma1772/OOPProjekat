package projekat.util;

import java.util.Random;

public interface IJedinstveniIdentifikator {

	public static int generateUUID (int limiter) {
		Random random = new Random();
		return random.nextInt(limiter);
	}

}
