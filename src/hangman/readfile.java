package dictionary;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * Utility class for reading from files.
 * @ author Tianxiao Zhang, Eugene Chong
 */
public class readfile {
	
	/*
	 * Get rid of the illegal strings in the given file
	 * @param filename to read
	 * @return an array list that contains only the legal strings
	 * @throws FileNotFoundException if file not found
	 */
	public ArrayList<String> cleanfile(String filename) throws FileNotFoundException {
		
		//create file object
		File file = new File(filename);
		
		ArrayList<String> cleanedList = new ArrayList<String>();
		
		//create scanner with given file
		Scanner scanner = new Scanner(file);
		
		//while scanner has another token to read
		while(scanner.hasNext()) {
			 
			//get next token
			String nextToken = scanner.next();
			
			//check validity with the helper method
			if (this.checkValid(nextToken)) {
				//add the token into the output list if it is valid
				cleanedList.add(nextToken);
			}
		}
		
		//close the scanner
		scanner.close();
		
		//return the cleanedList without any illegal string
		return cleanedList;
	}
	
	
	/*
	 * helper method that helps checking the validity of a given string in the file
	 * @param str
	 * @return boolean
	 */
	private boolean checkValid(String str) {
		
		//convert String to char array
		char [] charArray = str.toCharArray();
		
		//create an array list to store invalid punctuation
		ArrayList<String> invalidChars = new ArrayList<String>() {
			{
				add(".");
				add("-");
				add(" ");
				add("'");
			}
		};
		
		//create an array list to store invalid digits
		ArrayList<String> digits = new ArrayList<String>() {
			{
				add("0");
				add("1");
				add("2");
				add("3");
				add("4");
				add("5");
				add("6");
				add("7");
				add("8");
				add("9");
			}
		};
		
		//iterate through every character in the given string to check validity
		for(int i = 0; i < charArray.length; i++) {
			
			//if any character is in the invalidChar or digits array list, return false
			if (invalidChars.contains(charArray[i]) || digits.contains(charArray[i])) {
				return false;
			}
			
			//if any character is in upper case, return false
			//since we checked for punctuation and digits before this step, only letters should be left
			if (Character.isUpperCase(charArray[i])) {
				return false;
			}	
		}
		//return true if none of the character is invalid
		return true;
	}
}
