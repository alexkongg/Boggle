/**
 * Copyright (C) 2002 Michael Green <mtgreen@cs.ucsd.edu>
 * 
 * Copyright (C) 2002 Paul Kube <kube@cs.ucsd.edu>
 * 
 * Copyright (C) 2005 Owen Astrachan <ola@cs.duke.edu>
 * 
 * Copyright (C) 2011 Hoa Long Tam <hoalong.tam@berkeley.edu> and Armin Samii
 * <samii@berkeley.edu>
 * 
 * This file is part of CS Boggle.
 * 
 * CS Boggle is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * CS Boggle is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * CS boggle. If not, see <http://www.gnu.org/licenses/>.
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class LexiconArrayList implements LexiconInterface {
	/**
	 * Load the words from an input source and store them in this lexicon.
	 * 
	 * @param input
	 *          A scanner that will provide the entire dictionary.
	 */

	protected ArrayList<String> myDictionary = new ArrayList<String>();	

	public void load(Scanner input) {
		while (input.hasNext()) {
			String nextStr = input.next();
			myDictionary.add(nextStr);
			


		}
		// Sort the ArrayList to enable binary searching
		Collections.sort(myDictionary);
	}

	/**
	 * If the prefix is in the lexicon, returns true.
	 * 
	 * @param s
	 *          The word to search for.
	 * @return True if the lexicon contains s.
	 */
	public boolean containsPrefix(String s) {

		int starting = 0;
		int ending = myDictionary.size() - 1;
		while (ending >= starting) {
			// Set index equal to midway between top and bottom boundaries
			int index = (starting + ending)/ 2;
			String current = myDictionary.get(index);
			if (current.length() >= s.length() && current.substring(0, s.length()).equalsIgnoreCase(s)) {
				return true;
			} 
			// If s comes lexicographically after current guess (Ex. s = "Phone" and current = "Cookie")
			else if (s.compareToIgnoreCase(current) > 0) {
				// Reset bottom boundary to after guess
				starting = index + 1;
			} 

			// If s comes lexicographically before current guess (Ex. s = "Banana" and current = "Chips")
			else {
				// Reset top boundary to before guess
				ending = index - 1;
			}

		}
		return false;
	}


	/**
	 * If the word is in the lexicon, returns true.
	 * 
	 * @param s
	 *          The word to search for.
	 * @return True if the lexicon contains s.
	 */
	public boolean contains(String s) {
		int starting = 0;
		int ending = myDictionary.size() - 1;
		while (ending >= starting) {
			// Set index equal to midway between top and bottom boundaries
			int index = (starting + ending)/ 2;
			String current = myDictionary.get(index);
			if (current.equalsIgnoreCase(s)) {
				return true;
			} 
			// If s comes lexicographically after current guess (Ex. s = "Phone" and current = "Cookie")
			else if (s.compareToIgnoreCase(current) > 0) {
				// Reset bottom boundary to after guess
				starting = index + 1;
			} 

			// If s comes lexicographically before current guess (Ex. s = "Banana" and current = "Chips")
			else {
				// Reset top boundary to before guess
				ending = index - 1;
			}

		}
		return false;
	}
}
