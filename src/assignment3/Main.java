
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
	public static String first; //holds the start value
	public static String last; //holds the end value
	public static boolean firstRun; //tracks if this is the first run of the DFS
	public static Set<String> dictionary; //the dictionary of words
	
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
		printLadder(getWordLadderBFS(input.get(0),input.get(1))); //temporary testing delete this
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
		firstRun = true;
		dictionary = makeDictionary();
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
		if(Arrays.asList(parsed).contains("/quit")) //if the user wishes to quit, exit program
			return startEnd;
		
		while (parsed.length < 2 || (parsed.length >= 2 && parsed[0].length() != parsed[1].length())){ //if the input is invalid, read in again
			input = keyboard.nextLine(); //grab the next line from the keyboard
			parsed = (input.trim()).split("\\s+"); //trim the whitespace from the front and back of the user input, then split it into Strings separated by whitespace
			if(Arrays.asList(parsed).contains("/quit")) //if the user wishes to quit, exit program
				return startEnd;
		}
		
		first = parsed[0].toUpperCase();
		last = parsed[1].toUpperCase();

		startEnd.add(first); //add the start word converted to upper case to the return array
		startEnd.add(last); //add the end word converted to upper case to the return array
		
		return startEnd;
	}
	
	/**
	 * Determines if two strings of the same length differ by one letter
	 * @param a the first string to compare
	 * @param b the second string to compare
	 * @return true if the strings differ by one letter, false otherwise
	 */
	private static boolean oneLetterDiff(String a, String b){
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
       
        if(firstRun){
        	dictionary = makeDictionary();
        	firstRun = false;
        }
              
        ArrayList<String> branches = new ArrayList<String>(); //the arrayList containing all the branches of the current word
        for(String i: dictionary){ //iterate thru the dictionary and add all words that differ by one letter from the start
            if(oneLetterDiff(start, i))
                branches.add(i);
        }
       
        if(branches.size() == 0) //if there are no next words, then there is no ladder
            return wordLadder;
       
        if(branches.contains(end)){ //if the branch contains the last word already, return an array of size two
            wordLadder.add(start);
            wordLadder.add(end);
            return wordLadder;
        }
        
        dictionary.removeAll(branches); //remove all the branch nodes
       
        ArrayList<String> temp = new ArrayList<String>();
        for(String i: branches){
        	temp = getWordLadderDFS(i, end);
        	if(temp.size()!=0){
        		wordLadder = temp;
        		break;
        	}
        }
        
        if (temp.size() == 0) //if there is a dead end, then return an empty list
        	return temp;
       
        wordLadder.add(0, start);
        firstRun = true; //reset firstRun before the next iteration
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
    	ArrayList<String> wordLadder = new ArrayList<String>();
    	Set<String> used = new HashSet<String>();				//keep track of used words
		Set<String> dict = makeDictionary();
		String[] dictionary = dict.toArray(new String[0]);		// convert the set to an array
		Word oneaway = new Word("");							// will find new words one letter away from current
		boolean found = false;
		used.add(start);										
		
		Queue<Word> BFS = new LinkedList<Word>();
		BFS.add(new Word(start));								// start queue with the starting word
		
		while(!BFS.isEmpty()){									//keep searching while there are still items in queue
//			System.out.println(BFS.peek().getValue());  delete this
			for(int i = 0; i< dictionary.length; i++){
				if(oneLetterDiff(dictionary[i],BFS.peek().getValue()) && !used.contains(dictionary[i])){		//find words one letter away from current queued word
					oneaway = new Word(dictionary[i], BFS.peek());			//add these new words to the queue
					used.add(oneaway.getValue());
					BFS.add(oneaway);
					if(oneaway.getValue().equals(end)) {					// if the value is the end string, quit out of loops
						found = true;	
						break;
					}
				}
			}
			
			if(found) break;
			BFS.remove();			//dequeue first string in queue
		}
		
//		System.out.println(oneaway.getValue());  delete this
		if(BFS.isEmpty())			//happens when the queue while loop ended, no ladder found so return empty array
			return wordLadder;
		
		while(oneaway != null){		// we found oneaway to be end word, find ladder by finding all parent Words
			wordLadder.add(oneaway.getValue());
			oneaway = oneaway.getParent();
		}
		
		Collections.reverse(wordLadder);	//reverse order of ladder so it goes start --> end
		
		return wordLadder; 
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
		if(ladder.isEmpty()){
			System.out.println("no word ladder can be found between " + first.toLowerCase() + " and " + last.toLowerCase() + ".");
		}
		else{
			System.out.println("a " + (ladder.size()-2) + "-rung word ladder exists between " + first.toLowerCase() + " and " + last.toLowerCase() + ".");
			for(String s : ladder){
				System.out.println(s.toLowerCase());
			}
			
		}
	}
	// TODO
	// Other private static methods here
}
