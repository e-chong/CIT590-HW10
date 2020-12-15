package dictionary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Utility class for reading from files.
 * 
 * @author Tianxiao Zhang, Eugene Chong
 */
public class ReadFile {

	/**
	 * Get rid of the illegal strings in the given file
	 * 
	 * @param filename to read
	 * @return an array list that contains only the legal strings
	 * @throws FileNotFoundException if file not found
	 */
	public List<String> cleanFile(String filename) {

		List<String> cleanedList = new ArrayList<String>();

		// create file object
		File inputFile = new File(filename);
		BufferedReader file = null;

		try {
			file = new BufferedReader(new FileReader(inputFile));

			String word = "";

			// read each word
			while ((word = file.readLine()) != null) {

				// strip whitespace from beginning and end
				word = word.strip();

				// if line is valid
				if (this.checkValid(word)) {
					cleanedList.add(word);
				}
			}
			// check for the exceptions
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// return the cleanedList without any illegal string
		return cleanedList;
	}

	/**
	 * helper method that helps checking the validity of a given string in the file
	 * 
	 * @param str
	 * @return boolean
	 */
	private boolean checkValid(String str) {

		// convert String to char array
		char[] charArray = str.toCharArray();

		// create an array list to store invalid punctuation
		List<String> invalidChars = Arrays.asList(".", "-", " ", "'");

		// create an array list to store invalid digits
		List<String> digits = Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9");

		// iterate through every character in the given string to check validity
		for (int i = 0; i < charArray.length; i++) {

			// if any character is in the invalidChar or digits array list, return false
			if (invalidChars.contains(String.valueOf(charArray[i])) || digits.contains(String.valueOf(charArray[i]))) {
				return false;
			}

			// if any character is in upper case, return false
			// since we checked for punctuation and digits before this step, only letters
			// should be left
			if (Character.isUpperCase(charArray[i])) {
				return false;
			}
		}
		// return true if none of the character is invalid
		return true;
	}
}
