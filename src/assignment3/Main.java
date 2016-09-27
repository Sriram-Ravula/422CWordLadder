
/* WORD LADDER Main.java
 * EE422C Project 3 submission by
 * Sriram Ravula
 * sr39533
 * 16475
 * Rahul Jain
 * rj8656
 * 16470
 * Slip days used: <0>
 * Git URL: https://github.com/Sriram-Ravula/422CWordLadder
 * Fall 2016 
 */


package assignment3;
import java.util.*;
import java.io.*;

/**
 * Contains methods to find word ladders from a given user input
 */
public class Main {
	
	// static variables and constants only here.
	
	/**
	  * Reads user keyboard input and outputs the desired word ladder if one exists
	  * @param args the string array of command line arguments
	  * @return none
	  */
	public static void main(String[] args) throws Exception {
		
		Scanner kb;	// input Scanner for commands
		PrintStream ps;	// output file
		// If arguments are specified, read/write from/to files instead of Std IO.
		if (args.length != 0) {
			kb = new Scanner(new File(args[0]));
			ps = new PrintStream(new File(args[1]));
			System.setOut(ps);			// redirect output to ps
		} else {
			kb = new Scanner(System.in);// default from Stdin
			ps = System.out;			// default to Stdout
		}
		initialize();
		ArrayList<String> input = parse(kb);
		System.out.println(input); //temporary testing TODO delete this before final submission
		
		// TODO methods to read in words, output ladder
	}
	
	/**
	  * Initializes static variables and constants for the class
	  * @return none
	  */
	public static void initialize() {
		// initialize your static variables or constants here.
		// We will call this method before running our JUNIT tests.  So call it 
		// only once at the start of main.
	}
	
	/**
	 * Reads and parses user input to isolate the start and end words of ladder.
	 * If the user enters /quit, then tell the program to terminate output.
	 * @param keyboard Scanner connected to System.in
	 * @return ArrayList of 2 Strings containing start word and end word. 
	 * If command is /quit, return empty ArrayList. 
	 */
	public static ArrayList<String> parse(Scanner keyboard) {
		ArrayList<String> startEnd = new ArrayList<String>(); //create the arrayList that holds the start and end words to output
		
		String input = keyboard.nextLine(); //grab the next line from the keyboard
		
		String[] parsed = (input.trim()).split("\\s+"); //trim the whitespace from the front and back of the user input, then split it into Strings separated by whitespace
		
		while (!parsed[0].equals("/quit") && !parsed[1].equals("/quit") && parsed[0].length() != parsed[1].length()){ //if the input is invalid, read in again
			input = keyboard.nextLine(); //grab the next line from the keyboard
			parsed = (input.trim()).split("\\s+"); //trim the whitespace from the front and back of the user input, then split it into Strings separated by whitespace
		}
		
		if(parsed[0].equals("/quit") || parsed[1].equals("/quit")) //if the user wishes to quit, return an empty array
			return startEnd;

		startEnd.add(parsed[0].toUpperCase()); //add the start word converted to upper case to the return array
		startEnd.add(parsed[1].toUpperCase()); //add the end word converted to upper case to the return array
		
		return startEnd;
	}
	
	/**
	 * Determines if two strings of the same length differ by one letter
	 * @param a the first string to compare
	 * @param b the second string to compare
	 * @return true if the strings differ by one letter, false otherwise
	 */
	public static boolean oneLetterDiff(String a, String b){
		if(a.length() != b.length()) ///if the two strings are different lengths, fails conditions
			return false;
		boolean diffFound = false; //tracks if a difference has already been found
		
		for (int i = 0; i < a.length(); i++){
			if(a.charAt(i) != b.charAt(i)){
				if(diffFound) //if a difference has already been found, fail
					return false;
				diffFound = true;
			}
		}
		return diffFound;
	}
	
	/**
	 * Attempts to find a word ladder between the start and end word using a 
	 * depth-first search.
	 * @param start the starting word of the ladder
	 * @param end the ending word of the ladder
	 * @return ArrayList of word ladder ordered from start word to end word.
	 * If there exists no ladder, return empty list. 
	 */
	public static ArrayList<String> getWordLadderDFS(String start, String end) {
		ArrayList<String> wordLadder = new ArrayList<String>();
		// Returned list should be ordered start to end.  Include start and end.
		// Return empty list if no ladder.
		Set<String> dict = makeDictionary();
		
		return wordLadder; 
	}
	
	/**
	 * Attempts to find a word ladder between the start and end word using a 
	 * breadth-first search.
	 * @param start the starting word of the ladder
	 * @param end the ending word of the ladder
	 * @return ArrayList of word ladder ordered from start word to end word.
	 * If there exists no ladder, return empty list. 
	 */
    public static ArrayList<String> getWordLadderBFS(String start, String end) {
		

		Set<String> dict = makeDictionary();
		// TODO more code
		
		return null; // replace this line later with real return
	}
    
	/**
	 * Creates a dictionary given an input file of words
	 * @return Set of words (dictionary) from the given file
	 */
	public static Set<String>  makeDictionary () {
		Set<String> words = new HashSet<String>();
		Scanner infile = null;
		try {
			infile = new Scanner (new File("five_letter_words.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Dictionary File not Found!");
			e.printStackTrace();
			System.exit(1);
		}
		while (infile.hasNext()) {
			words.add(infile.next().toUpperCase());
		}
		return words;
	}
	
	/**
	 * Prints the word ladder from start to finish.
	 * If no ladder exists, prints a message stating so.
	 * @param ladder the word ladder to print
	 * @return none 
	 */
	public static void printLadder(ArrayList<String> ladder) {
		
	}
	// TODO
	// Other private static methods here
}
