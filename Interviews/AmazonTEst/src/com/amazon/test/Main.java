package com.amazon.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		//String stream = "aAbBABac";
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Type in the stream sequence: ");
		String stream = sc.nextLine();
		
		CharacterStream cs = new CharacterStream(stream);
		Map<Character, Integer> mapPos = new HashMap<Character, Integer>();
		Map<Character, Integer> mapFrequency = new HashMap<Character, Integer>();
		
		int pos = 0;
		while (cs.hasNext())
		{
			// gets the character
			char c = cs.getNext();
			// saves the character position in a map
			mapPos.put(c, pos); 
			// save the character frequency in another map
			if (mapFrequency.get(c) == null){
				mapFrequency.put(c, 1);
			} else
			{
				mapFrequency.put(c, mapFrequency.get(c) + 1);
			}
			// increment position read
			pos++;
		}
		System.out.println(mapPos);
		System.out.println(mapFrequency);
		
		// assume minimum position to be the last one (worst case)
		int minPos = stream.length()-1;
		// for each character in the position map
		for (char c: mapPos.keySet())
		{
			// if the frequency of the character is exactly one
			if (mapFrequency.get(c) != null && mapFrequency.get(c) == 1)
			{
				// if the position of this character is less that the lest one found yet
				if (mapPos.get(c) < minPos)
				{ // hold on to it
					minPos = mapPos.get(c);
				}
			}
		}
		// show minimum position for character
		System.out.println("Min pos: " + minPos );
		// show the character
		System.out.println("Min pos char: " + stream.charAt(minPos));
	}

	public static char firstChar ( Stream input ) {
		return input.getNext();
	  }
	
}
