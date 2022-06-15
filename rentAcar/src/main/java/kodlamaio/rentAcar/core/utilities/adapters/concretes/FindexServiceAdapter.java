package kodlamaio.rentAcar.core.utilities.adapters.concretes;

import java.util.HashMap;
import java.util.Random;

import kodlamaio.rentAcar.core.utilities.adapters.abstracts.FindexService;

public class FindexServiceAdapter implements FindexService {

	Random random = new Random();
	HashMap<String, Integer> findexScores;

	@Override
	public int findexScore(String tcNo) {
		findexScores = new HashMap<>();
		int findexScore = random.nextInt(1900);

		findexScores.put(tcNo, findexScore);
		System.out.println(findexScore);
		return findexScore;
	}

}
