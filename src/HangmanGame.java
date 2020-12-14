import java.util.List;
import java.util.Scanner;
import java.util.Random;
import hangman.*;

/**
 * Controller class for the hangman game.
 * 
 * @author Tianxiao Zhang, Eugene Chong
 * 
 */
public class HangmanGame {

	public static void main(String[] args) {
		// Read in words.txt and keep only eligible words.
		String filename = "words.txt";
		List<String> cleanWords = new dictionary.ReadFile().cleanFile(filename);

		// welcome message
		printWelcome();

		// initialize scanner
		Scanner scanner = new Scanner(System.in);

		boolean keepPlaying = true;

		while (keepPlaying) {

			new HangmanGame().run(cleanWords, scanner);

			keepPlaying = askPlayAgain(scanner);

		}

		scanner.close();

	}

	/**
	 * Method for running the game
	 * 
	 * @param words - list of eligible words
	 */
	public void run(List<String> words, Scanner scanner) {

		// initialize a game state
		boolean gameState = false;

		// Choose traditional or evil
		Random rand = new Random();
		boolean traditional = rand.nextBoolean();

		if (traditional) {
			// Initialize a traditional hangman game
			TraditionalHangman hangman = new TraditionalHangman(words);

			// run
			while (gameState == false) {
				// ask for the next letter
				this.askNextLetter(hangman);
				char letter = Character.toLowerCase(scanner.next().charAt(0));

				// check the letter
				hangman.checkLetter(letter);

				gameState = hangman.checkGameStatus();

			}

			this.printGameOver(hangman, traditional);

		} else {
			// Initialize an evil hangman game
			EvilHangman hangman = new EvilHangman(words);

			// run
			while (gameState == false) {
				// ask for the next letter
				this.askNextLetter(hangman);
				char letter = Character.toLowerCase(scanner.next().charAt(0));

				// check the letter
				hangman.checkLetter(letter);

				// set the new word based on evil criteria.
				hangman.setNewWord(letter);

				gameState = hangman.checkGameStatus();

			}

			this.printGameOver(hangman, traditional);

		}

	}

	/**
	 * Asks the player for the next letter and prints some updates on the game.
	 * 
	 * @param hangman
	 */
	void askNextLetter(Hangman hangman) {
		System.out.println("");
		System.out.println("What letter do you want to guess next? Enter just one letter. ");
		hangman.printCorrectGuess();
		hangman.printIncorrectGuess();
		hangman.printGuessCount();
		System.out.println("");
	}

	/**
	 * Prints a welcome message.
	 */
	static void printWelcome() {
		System.out.println("Welcome to Hangman!");
		System.out.println("This game may or may not be EVIL. O_O");
		System.out.println("");
	}

	/**
	 * Print a game over message with some helpful info about the player's
	 * performance and the type of game they played.
	 * 
	 * @param hangman
	 * @param traditional
	 */
	void printGameOver(Hangman hangman, boolean traditional) {
		System.out.println("Game over!");
		System.out.println("You successfully spelled " + hangman.getWord());
		System.out.println("It took you " + hangman.getCount() + " guesses.");

		if (traditional) {
			System.out.println("You played a traditional game of Hangman.");
		} else {
			System.out.println("You played an EVIL game of Hangman.");
		}

	}

	/**
	 * Asks the player if they want to play again.
	 * 
	 * @param scanner
	 * @return
	 */
	static boolean askPlayAgain(Scanner scanner) {
		System.out.println("Play again (y/n)? ");
		char answer = Character.toLowerCase(scanner.next().charAt(0));

		while (answer != 'y' && answer != 'n') {
			System.out.println("Please respond with a 'y' or 'n'. ");
			answer = Character.toLowerCase(scanner.next().charAt(0));
		}

		boolean result = false;

		if (answer == 'y') {
			result = true;
		} else if (answer == 'n') {
			result = false;
		}

		return result;
	}

}
