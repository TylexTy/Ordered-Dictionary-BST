/**
 * 
 * @author Tyler Bulley
 * ID: 250498520
 *
 * A class that stores Pair(word,type).
 */
public class Pair {
	
	private String word;
	private String type;
	
	public Pair(String word, String type) {
		
		this.word = word.toLowerCase();
		this.type = type;
	}
	
	public String getWord() {
		
		return word;
	}
	
	public String getType() {
		
		return type;
	}
	
	/**
	 * compares word in pair lexicographically to pair k.
	 * 
	 * @param k pair to compare
	 * @return int representing lexicographical comparison of word to k.
	 */
	public int compareTo(Pair k) {
		
		k.word = k.word.toLowerCase();
		int comp = word.compareTo(k.word);
		
		if (comp == 0 && type.equals(k.type)) { // if words are the same and type are the same
			return 0;
		}
		else if (comp < 0) {
			return -1;
		}
		else
			return 1;
		}
}

