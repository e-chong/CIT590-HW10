package hangman;

import java.util.List;
import java.util.Random;

/**
 * Class for traditional version of hangman
 *
 */
public class TraditionalHangman extends Hangman {

	public TraditionalHangman(List<String> words) {
		Random rand = new Random();
		String randomWord = words.get(rand.nextInt(words.size()));
		this.setWord(randomWord);

		// fill the correctGuess array with _
		this.initializeCorrectGuess(this.getWord());

	}

}
