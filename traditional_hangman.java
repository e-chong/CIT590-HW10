package hangman;

import java.util.ArrayList;

public class traditional_hangman {

	//keep a count of the number of guesses
	private int count;
	
	//record the word chosen by the computer
	private String word;
	
	//record the letter chosen by the human player
	private char letter;
	
	//record the correct guesses
	private ArrayList<Character> correctGuess;
	
	//record the incorrect guesses
	private ArrayList<Character> incorrectGuess;
	
	public traditional_hangman(String dic_word) {
		count = 0;
		word = dic_word;
		correctGuess = new ArrayList<Character> (word.length());
		incorrectGuess = new ArrayList<Character> ();
		
		//initialize the correct list by showing "_"
		for (int i = 0; i < word.length(); i++) {
			correctGuess.add('_');
		}
	}
	
	

	/*
	 * Check whether the given letter is in the word chosen
	 * 
	 * @param letter
	 * @param word
	 * 
	 * @return true/false
	 */
	boolean charInWord() {
		
		//convert String to char array
		char [] charArray = word.toCharArray();
				
		//iterate through every character in the given string to check
		for (int i = 0; i < charArray.length; i++) {
			if (charArray[i] == letter) {
				//if the character is in the word, return true
				return true;
			}
		}
		//if the character is not in the word, return false
		return false;
	}
	

	
	/*
	 * if the letter is in the word, add it into the correctGuess arrayList
	 * 
	 * @param letter
	 * @param word
	 * @param correctGuess
	 * 
	 * @return correctGuess
	 */
	private ArrayList<Character> addcorrectGuess() {
		
		//convert String to char array
		char [] charArray = word.toCharArray();
		
		for (int i = 0; i < charArray.length; i++) {
			if (charArray[i] == letter) {
				//if the character is in the word, add it into the corresponding place in the correct list
				correctGuess.set(i, letter);
			}
		}
		return correctGuess;
	}
	
	
	/*
	 * if the letter is not in the word and not guessed before,
	 * add it into the incorrectGuess arrayList
	 * 
	 * @param letter
	 * @param word
	 * @param correctGuess
	 * @param incorrectGuess
	 * 
	 * @return incorrectGuess
	 */
	private ArrayList<Character> addIncorrectGuess() {
		
		//only add it to the list if it is not already guessed
		if (this.alreadyGuessed() == false) {
			if (this.charInWord() == false) {
				incorrectGuess.add(letter);
			}
		}
		return incorrectGuess;
	}
	
	
	/*
	 * check if the letter has already been guessed
	 * (included in any of the lists)
	 * 
	 * @param letter
	 * @param correctGuess
	 * @param incorrectGuess
	 * 
	 * @return true/false
	 */
	boolean alreadyGuessed() {
		
		//check whether it is in the correct list
		if (correctGuess.contains(letter)) {
			return true;
		} else {
			//check whether it is in the incorrect list
			if (incorrectGuess.contains(letter)) {
				return true;
			}
		}
		//return false if it is not in either list
		return false;
	}
	
	/*
	 * update the count of guesses
	 * 
	 * @param count
	 * @param letter
	 * @param correctGuess
	 * @param incorrectGuess
	 * 
	 * @return count
	 */
	int countUpdate(int count) {
		
		//only update the count when the letter is not already guessed
		if (this.alreadyGuessed() == false) {
			//increment the count by one
			count++;
		}
		
		return count;
	}
	
	
	//getters
	/*
	 * @return the number of guesses
	 */
	int getCount() {
		return this.count;
	}
	
	/*
	 * @return the word chosen by the computer
	 */
	String getword() {
		return this.word;
	}
	
	/*
	 * @return the letter chosen by the human player
	 */
	char getletter() {
		return this.letter;
	}
	
	
	//setters
	/*
	 * Set the value of word
	 * 
	 * @param word
	 */
	public void setWord() {
		this.word = word;
	}
	
	/*
	 * Set the value of letter
	 * 
	 * @param letter
	 */
	public void setLetter() {
		this.letter = letter;
	}
	
}
