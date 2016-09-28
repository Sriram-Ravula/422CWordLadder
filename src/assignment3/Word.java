package assignment3;

/*
 * This class will be used to keep track our path in BFS.
 * Once we find the end word from the start, we can find the path by finding the parent of each word.
 */
public class Word {
	public Word parent;
	public String value;
	
	/*
	 * Constructor for Word, initializes value and parent
	 * @param String s is the string this represents
	 * @param Word p is the parent of this Word to keep track in BFS
	 */
	public Word(String s, Word p){
		value = s;
		this.parent= p;
	}
	
	public Word(String s){
		value = s;
		this.parent= null;
	}
	
	public Word getParent(){
		return this.parent;
	}
	
	public String getValue(){
		return this.value;
	}

}
