package hangman;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Class for traditional version of hangman
 *
 */
public class TraditionalHangman {

	// keep a count of the number of guesses
	private int count;

	// record the word chosen by the computer
	private String word;

	// record the letter chosen by the human player
//	private char letter;

	// record the correct guesses
	private ArrayList<Character> correctGuess;

	// record the incorrect guesses
	private ArrayList<Character> incorrectGuess = new ArrayList<Character>();

	public TraditionalHangman(List<String> words) {
		Random rand = new Random();
		String randomWord = words.get(rand.nextInt(words.size()));
		this.setWord(randomWord);

		// fill the correctGuess array with _
		initializeCorrectGuess(this.getWord());

	}

	// getters
	/**
	 * @return the number of guesses
	 */
	public int getCount() {
		return this.count;
	}

	/**
	 * @return the word chosen by the computer
	 */
	public String getWord() {
		return this.word;
	}

//	/**
//	 * @return the letter chosen by the human player
//	 */
//	public char getLetter() {
//		return this.letter;
//	}

	/**
	 * 
	 * @return the array list of correct guesses
	 */
	public ArrayList<Character> getCorrectGuesses() {
		return this.correctGuess;
	}

	/**
	 * 
	 * @return the array list of incorrect guesses
	 */
	public ArrayList<Character> getIncorrectGuesses() {
		return this.incorrectGuess;
	}

	// setters
	/**
	 * Set the value of word
	 * 
	 * @param word
	 */
	public void setWord(String word) {
		this.word = word;
	}

//	/**
//	 * Set the value of letter
//	 * 
//	 * @param letter
//	 */
//	public void setLetter(char letter) {
//		this.letter = letter;
//	}

	// Game methods
	/**
	 * Check the player's guess
	 * @param letter
	 */
	public void checkLetter(char letter) {

		this.countUpdate(letter);

		if (this.charInWord(letter)) {
			this.addCorrectGuess(letter);
		} else {
			this.addIncorrectGuess(letter);
		}

	}

	/**
	 * Check whether the given letter is in the chosen word
	 * 
	 * @param letter
	 * 
	 * @return true/false
	 */
	boolean charInWord(char letter) {

		// convert String to char array
		char[] charArray = this.getWord().toCharArray();

		// iterate through every character in the given string to check
		for (int i = 0; i < charArray.length; i++) {
			if (charArray[i] == letter) {
				// if the character is in the word, return true
				return true;
			}
		}
		// if the character is not in the word, return false
		return false;
	}

	/**
	 * populate the correctGuess array with underscores
	 * 
	 * @param word
	 * 
	 */
	private void initializeCorrectGuess(String word) {

		ArrayList<Character> correctGuess = new ArrayList<Character>();

		// create a list with the same length with the chosen word
		// fill it with '_' initially
		for (int i = 0; i < word.length(); i++) {
			correctGuess.add('_');
		}
		this.correctGuess = correctGuess;
	}

	/**
	 * if the letter is in the word, add it into the correctGuess arrayList
	 * 
	 * @param letter
	 * 
	 * @return correctGuess
	 */
	private void addCorrectGuess(char letter) {

		// convert String to char array
		char[] charArray = this.getWord().toCharArray();

		for (int i = 0; i < charArray.length; i++) {
			if (charArray[i] == letter) {
				// if the character is in the word, add it into the corresponding place in the
				// correct list
				this.correctGuess.set(i, letter);
			}
		}

	}

	/**
	 * Prints the current correct guess array.
	 */
	public void printCorrectGuess() {
		String currentLetters = this.getCorrectGuesses().stream().map(String::valueOf).collect(Collectors.joining(" "));
		System.out.println(currentLetters);
	}

	/**
	 * Prints the current incorrect guess array
	 */
	public void printIncorrectGuess() {
		ArrayList<Character> incorrect = this.getIncorrectGuesses();

		if (incorrect.size() == 0) {
			return;
		} else {
			String incorrectString = String.join("", String.valueOf(incorrect));
			System.out.println(incorrectString);
		}
	}

	/**
	 * Prints the count of guesses so far.
	 */
	public void printGuessCount() {
		System.out.println("Total guesses: " + this.getCount());

	}

	/**
	 * Check the status of the game.
	 * 
	 * @return true if correct guesses match the word, else false
	 */
	public boolean checkGameStatus() {
		String currentLetters = this.getCorrectGuesses().stream().map(String::valueOf).collect(Collectors.joining());
		
		System.out.println(currentLetters);

		if (currentLetters.equals(this.getWord())) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * if the letter is not in the word and not guessed before, add it into the
	 * incorrectGuess arrayList
	 * 
	 * @param letter
	 * 
	 */
	private void addIncorrectGuess(char letter) {

		// only add it to the list if it is not already guessed
		if (this.alreadyGuessed(letter) == false) {
			if (this.charInWord(letter) == false) {
				incorrectGuess.add(letter);
			}
		}

	}

	/**
	 * check if the letter has already been guessed (included in any of the lists)
	 * 
	 * @param letter
	 * 
	 * @return true/false
	 */
	boolean alreadyGuessed(char letter) {

		// check whether it is in the correct list
		if (this.getCorrectGuesses().contains(letter) || this.getIncorrectGuesses().contains(letter)) {
			System.out.println("You've already guessed this letter! Please choose another.");			
			return true;
		} 
		// return false if it is not in either list
		return false;
	}

	/**
	 * update the count of guesses
	 * 
	 * @param letter
	 * 
	 */
	private void countUpdate(char letter) {

		// only update the count when the letter is not already guessed
		if (this.alreadyGuessed(letter) == false) {
			// increment the count by one
			this.count++;
		}

	}
}