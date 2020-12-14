package hangman;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class for the evil version of Hangman
 *
 */
public class EvilHangman extends Hangman {

	private List<String> words;

	/**
	 * 
	 * @param words
	 */
	public EvilHangman(List<String> words) {

		// Set the Word Array
		this.setWordArray(words);

		Random rand = new Random();
		String randomWord = words.get(rand.nextInt(words.size()));
		this.setWord(randomWord);

		// fill the correctGuess array with _
		this.initializeCorrectGuess(this.getWord());

	}

	// Getters
	/**
	 * 
	 * @return the clean word array list
	 */
	private List<String> getWordArray() {
		return this.words;
	}

	// Setters
	/**
	 * Store the clean word array list
	 * 
	 * @param words
	 */
	private void setWordArray(List<String> words) {
		this.words = words;
	}

	/**
	 * Perform the initial filter upon the length of the chosen word
	 * 
	 * 
	 * @return newList
	 */
	private ArrayList<String> filterWords() {

		// get the length of the initial word chosen by the computer
		int len = this.getWord().length();

		// initialize a new array list to store the words with the same length
		ArrayList<String> newList = new ArrayList<String>();

		// iterate through all words
		for (int i = 0; i < this.getWordArray().size(); i++) {

			// store the string
			String token = this.getWordArray().get(i);

			// get the array of correctGuesses so far
			ArrayList<Character> correctGuesses = this.getCorrectGuesses();

			// If the length of a given word is the same length as the current word
			if (token.length() == len) {

				int tokenCounter = 0;
				// And every character in the given word is either a "_" or matches a character
				// in correctGuesses at the same index
				for (int j = 0; j < correctGuesses.size(); j++) {

					if (correctGuesses.get(j).equals("_".charAt(0)) || correctGuesses.get(j).equals(token.charAt(j))) {
						tokenCounter++;
					}
				}
				// Then add the given word to a list of new possible words.
				if (tokenCounter == token.length()) {
					newList.add(token);
				}

			}
		}
		// return the filtered list with only the words with the given length
		return newList;
	}

	/**
	 * Choose a newWord based on the letters already guessed and the letter most
	 * recently guessed.
	 * 
	 * @param letter
	 */
	public void setNewWord(char letter) {

		System.out.println("Current word: " + this.getWord());
		
		// Use the current guesses to filter the list of possible words
		String newWord = this.randomChoose(letter, this.filterWords());
		System.out.println("New word: " + newWord);
		
		this.setWord(newWord);
		
		

	}

	/**
	 * get the indices for all occurrences of the letter in the word
	 * 
	 * @param letter
	 * @param word
	 * 
	 * @return indices
	 */
	private ArrayList<Integer> getIndexofOccurrences(char letter, String word) {

		// initialize an array list to record the indices
		ArrayList<Integer> indices = new ArrayList<Integer>();

		// convert String to char array
		char[] charArray = word.toCharArray();

		// iterate through all letters in the word
		for (int i = 0; i < charArray.length; i++) {

			// record the index when the letter occurs
			if (charArray[i] == letter) {
				indices.add(i);
			}
		}
		
		// if the letter never occurs, the return list should be empty
		return indices;
	}

	/**
	 * an array list of array lists to record all unique combinations of indices
	 * 
	 * @param letter
	 * @param filterWords
	 * 
	 * @return a list of unique combinations (lists of indices)
	 */
	private ArrayList<ArrayList<Integer>> uniqueCombinations(char letter, ArrayList<String> filterWords) {

		// initialize a list of list to store the unique combinations
		ArrayList<ArrayList<Integer>> uniqueCombinations = new ArrayList<ArrayList<Integer>>();

		// iterate through all words in the filtered list
		for (int i = 0; i < filterWords.size(); i++) {

			// store the word
			String word = filterWords.get(i);

			// get the combination for that word
			ArrayList<Integer> indices = this.getIndexofOccurrences(letter, word);

			// if the combination is not already in the list of list, put it in
			if (!uniqueCombinations.contains(indices)) {
				uniqueCombinations.add(indices);
			}
		}
		// return the unique combinations
		
		return uniqueCombinations;
	}

	/**
	 * perform partition according to the unique combination of indices
	 * 
	 * @param letter
	 * @param filterWords
	 * 
	 * @return subLists
	 */
	private ArrayList<ArrayList<String>> partitioned(char letter, ArrayList<String> filterWords) {

		// store all unique combinations
		ArrayList<ArrayList<Integer>> uniqueCombinations = this.uniqueCombinations(letter, filterWords);

		int combinations = uniqueCombinations.size();

		// initialize a list of list to store the sublists
		// same length with the number of unique combinations
		ArrayList<ArrayList<String>> subLists = new ArrayList<>();		
		
		for (int i = 0; i < filterWords.size(); i++) {

			// store the word
			String word = filterWords.get(i);

			// get the combination for the specific word
			ArrayList<Integer> indices = this.getIndexofOccurrences(letter, word);

			// iterate through all existing unique combinations, and put the word into the
			// corresponding one
			for (int j = 0; j < combinations; j++) {
				
				subLists.add(new ArrayList<String>());

				// check which combination the word corresponds to
				if (indices.equals(uniqueCombinations.get(j))) {

					// put the word into the corresponding position into the list of sublists
					subLists.get(j).add(word);
				}
			}
		}
		// return all subLists with words inside
		// each subList corresponds to one combination of the given letter
		return subLists;
	}

	/**
	 * select the longest sublist from all sublists
	 * 
	 * @param letter
	 * 
	 * @param filterWords
	 * 
	 * @return longest
	 */
	private ArrayList<String> longestList(char letter, ArrayList<String> filterWords) {

		// get all sublists
		ArrayList<ArrayList<String>> subLists = this.partitioned(letter, filterWords);

		// initialize an array list to store the longest sublist
		ArrayList<String> longest = new ArrayList<String>();

		// initialize an integer to record the largest length
		int maxLen = 0;
		
		// iterate through all sublists to find their lengths
		for (int i = 0; i < subLists.size(); i++) {

			// store the sublist
			ArrayList<String> sub = subLists.get(i);

			// store the length
			int len = sub.size();
			
			// check if the sublist is longer
			if (len > maxLen) {

				// update the max length
				maxLen = len;

				// update the longest sublist
				longest = sub;
			}
		}
		// return the longest sub list as result
		return longest;
	}

	/**
	 * since the input of the traditional form is a word, choose a random word from
	 * the longest sublist as benchmark
	 * 
	 * @param letter
	 * 
	 * @param filterWords
	 * 
	 * @return wordChosen
	 */
	private String randomChoose(char letter, ArrayList<String> filterWords) {

		Random rand = new Random();

		// find the length the longest list
		int len = this.longestList(letter, filterWords).size();

		// generate a random index within the given range
		int index = rand.nextInt(len);

		// select the word by the generated index
		String wordChosen = this.longestList(letter, filterWords).get(index);

		// return the randomly chosen word as benchmark
		return wordChosen;

	}

}
