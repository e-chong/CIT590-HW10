package hangman;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HangmanTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	//Since most of our methods are private, 
	//and most of the public methods are just printing and constructors
	//there is only one methods that we need to perform unit testing
	@Test
	void testCheckGameStatus() {
		
		//test 1
		Hangman hangman = new Hangman();
		
		ArrayList<Character> letters = new ArrayList<Character>();
		letters.add('a');	
		hangman.correctGuess = letters;
		
		hangman.setWord("a");
		assertTrue(hangman.checkGameStatus());
		
		//test 2
		Hangman hangman1 = new Hangman();
		
		ArrayList<Character> letters1 = new ArrayList<Character>();
		letters1.add('b');	
		hangman1.correctGuess = letters1;
		
		hangman1.setWord("b");
		assertTrue(hangman1.checkGameStatus());
		
		//test 2
		Hangman hangman2 = new Hangman();
		
		ArrayList<Character> letters2 = new ArrayList<Character>();
		letters2.add('w');	
		hangman2.correctGuess = letters2;
		
		hangman2.setWord("w");
		assertTrue(hangman2.checkGameStatus());
		
	}

}
