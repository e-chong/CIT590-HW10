import java.util.List;

import java.util.Scanner;

import hangman.*;

/**
 * Controller class for the hangman game.
 * @ author Tianxiao Zhang, Eugene Chong
 * 
 */
public class HangmanGame {

	public static void main(String[] args) {
		// Read in words.txt and keep only eligible words.
		String filename = "words.txt";
		List<String> cleanWords = new dictionary.ReadFile().cleanFile(filename);

		new HangmanGame().run(cleanWords);

	}

	/**
	 * Method for running the game
	 * 
	 * @param words - list of eligible words
	 */
	public void run(List<String> words) {

		// welcome message
		this.printWelcome();

		// Initialize a traditional hangman game
		TraditionalHangman hangman = new TraditionalHangman(words);

		// initialize scanner
		Scanner scanner = new Scanner(System.in);

		boolean gameState = false;
		// run
		while (gameState == false) {
			// ask for the next letter
			this.askNextLetter(hangman);
			char letter = Character.toLowerCase(scanner.next().charAt(0));
			
			// check the letter
			hangman.checkLetter(letter);
			
			gameState = hangman.checkGameStatus();

		}

		this.printGameOver(hangman);
		
		scanner.close();
	}
	
	/**
	 * Asks the player for the next letter and prints some updates on the game.
	 * @param hangman
	 */
	void askNextLetter(TraditionalHangman hangman) {
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
	void printWelcome() {
		System.out.println("Welcome to Hangman!");
		System.out.println("This game may or may not be EVIL. O_O");
		System.out.println("");
	}
	
	void printGameOver(TraditionalHangman hangman) {
		System.out.println("Game over!");
		System.out.println("You successfully spelled " + hangman.getWord());
		System.out.println("It took you " + hangman.getCount() + " guesses.");
	}

}