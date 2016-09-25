/* WORD LADDER Main.java
 * EE422C Project 3 submission by
 * Sriram Ravula
 * sr39533
 * 16475
 * Rahul Jain
 * <Student2 EID>
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Git URL:
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
		// TO DO
		return null;
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
		
		// Returned list should be ordered start to end.  Include start and end.
		// Return empty list if no ladder.
		// TODO some code
		Set<String> dict = makeDictionary();
		// TODO more code
		
		return null; // replace this line later with real return
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
		
		// TODO some code
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