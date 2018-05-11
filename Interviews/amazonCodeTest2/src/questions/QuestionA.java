package questions;

import java.util.ArrayList;
import java.util.List;

public class QuestionA {

	public static void main(String[] args) {
		
		String stream = "The angry dog was red. And the cat was also angry.";
		
		Stream impl = new StreamImpl(stream);
		
		System.out.println("First non-repeating word in stream: " + firstWord(impl));
		
	}
	
	public static String firstWord(final Stream input){
		
		// list of non-repeating words
		List<String> wordList = new ArrayList<String>(); 
		
		StringBuffer buffer = new StringBuffer();
		while (input.hasNext()){
			// converting characters to lowercase
			char c = Character.toLowerCase(input.getNext());
			// if character is a letter (a-z)
			if (c >=97 && c <= 122){
				buffer.append(c);  // add it to the buffer
			} else { // end of word
				System.out.println("Read: " + buffer);
				// if list already contains the word
				if (wordList.contains(buffer.toString())){
					// remove it
					wordList.remove(buffer.toString());
				} else { // else
					// add it to the list
					wordList.add(buffer.toString());
				}
				buffer.setLength(0);
			}
		}
		
		// return the first word in the list, in case there are word to return
		return wordList.isEmpty()?"":wordList.get(0);
	}
	
	
	
}
