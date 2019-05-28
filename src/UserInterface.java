/**
 * main method representing the user interface for the orderedDictionary
 * 
 * @author Tyler Bulley
 * ID: 250498520
 */

import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class UserInterface {
	
	public static void main(String[] args) {
		
		OrderedDictionary od = new OrderedDictionary();
		String word;
		String data;
		String type;
		
		boolean finished = false;
		
		
		// Reads in from args[0] a .txt file which gets put in the dictionary
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			
			String line;
			while ((line = br.readLine()) != null) {
				word = line;
				data = br.readLine();
				if (data.contains(".wav") || data.contains(".mid")) {
					type = "audio";
				}
				else if (data.contains(".jpg") || data.contains(".gif")) {
					type = "image";
				}
				else {
					type = "text";
				}
			
				Pair newPair = new Pair(word, type);
				Record newRecord = new Record(newPair, data);
				od.put(newRecord);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (DictionaryException e) {
			System.out.println("Record is already in Dictionary");
		}
		// while user hasn't typed in finish, continue to prompt them for next command
		while (finished == false) {
			
		
			StringReader keyboard = new StringReader();
			String line1 = keyboard.read("Enter next command: ");
			
			line1 = line1.toLowerCase();
			StringTokenizer stringTokens = new StringTokenizer(line1);
			String word1 = stringTokens.nextToken();
			String word2;
			String word3;
			String word4 = "";
			boolean passedIt = false;
			Pair parent = new Pair(" ", null);
			
			if (word1.equals("get") && stringTokens.countTokens() == 1) {
				word2 = stringTokens.nextToken();
				Record toFind = od.smallest();
				while (toFind != null) {
					// once we found a word there could be more under that word so get them all.
					if (toFind.getKey().getWord().equals(word2)) { 
						while (toFind.getKey().getWord().equals(word2)) {
							if (toFind.getKey().getType().equals("text")) {
								System.out.println(toFind.getData());
							}
							else if (toFind.getKey().getType().equals("audio")) {
								try {
									SoundPlayer player = new SoundPlayer();
								    player.play(toFind.getData());
								}
								catch (MultimediaException e) {
								    System.out.println(e.getMessage());
								}
								
							}
							else if (toFind.getKey().getType().equals("image")) {
								try {
									PictureViewer viewer = new PictureViewer();
								    viewer.show(toFind.getData());
								}
								catch (MultimediaException e) {
								    System.out.println(e.getMessage());
								}
							}
							// if it's at the end of the dictionary, break out of the loop
							if (toFind.equals(od.largest())) {
								break;
							}
							else { // else find it's successor
								toFind = od.successor(toFind.getKey());
							}
						}
						break; //break out of the while loop since we've found the word and it's data
					}
					if (toFind.getKey().getWord().compareTo(word2) <= 0 && !toFind.equals(od.largest())) {
						parent = toFind.getKey();
						toFind = od.successor(toFind.getKey());
					}
					else { // else we've passed it, break out of while loop
						passedIt = true;
						break;
						
					}
				}
				//  if toFind is null, we entered a word that exceeds every word from the dictionary
				if (toFind == null) {
					System.out.println("the word " + word2 + " is not in the ordered dictionary");
					System.out.println("Preceding word: " + parent.getWord());
					System.out.println("Following word: ");
				}
				else if (passedIt == true) { // then we passed the word, print the preceeding and following words
					System.out.println("the word " + word2 + " is not in the ordered dictionary");
					System.out.println("Preceding word: " + parent.getWord());
					System.out.println("Following word: " + od.successor(parent).getKey().getWord());
				}
			}
			else if (word1.equals("delete") && stringTokens.countTokens() == 2) {
				word2 = stringTokens.nextToken();
				word3 = stringTokens.nextToken();
				Pair newPair = new Pair(word2,word3);
				try {
					od.remove(newPair);
				}
				catch (DictionaryException e) {
					System.out.println("No record in the ordered dictionary has key (" + word2 + "," + word3 + ")" );
				}
			}
			
			else if (word1.equals("put") && stringTokens.countTokens() >= 3) {
				word2 = stringTokens.nextToken();
				word3 = stringTokens.nextToken();
				// the while loop gets all the words from data
				while (stringTokens.hasMoreTokens()) {
					word4 += stringTokens.nextToken() + " ";
				}
				
				Pair newPair = new Pair(word2,word3);
				Record newRecord = new Record(newPair, word4);
				try {
					od.put(newRecord);
				}
				catch (DictionaryException e) {
					System.out.println("A record with the given key (" + word2 + "," + word3 + ") is already in the ordered dictionary");
				}
			}
			else if (word1.equals("list") && stringTokens.countTokens() == 1) {
				word2 = stringTokens.nextToken();
				Record toFind = od.smallest();
				while (toFind != null) {
					// if the word in toFind has prefix word2, then continue to print the word until it no longer starts with the prefix
					if (toFind.getKey().getWord().startsWith(word2)) {
						while (toFind.getKey().getWord().startsWith(word2)) {
							System.out.println(toFind.getKey().getWord());	
							toFind = od.successor(toFind.getKey());
							
						}
						break;
					}
					else {
						toFind = od.successor(toFind.getKey());
					}
						
				
				}
				if (toFind == null) {
					System.out.println("No words in the ordered dictionary start with prefix " + word2);
				}
				
			}
			else if (word1.equals("smallest")) {
				Record smallest = od.smallest();
				System.out.println(smallest.getKey().getWord() + ", " + smallest.getKey().getType() + ", " + smallest.getData());
			}
			else if (word1.equals("largest")) {
				Record largest = od.largest();
				System.out.println(largest.getKey().getWord() + ", " + largest.getKey().getType() + ", " + largest.getData());
			}
			else if (word1.equals("finish")) {
				finished = true;
			}
			else { // if all if statements aren't satisfied then we've entered an incorrect command
				System.out.println("command not found");
			}
	}
}
}


