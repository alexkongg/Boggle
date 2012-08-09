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
import java.util.*;

public class AutoPlayerLexiconFirst extends AbstractPlayer {
  
  public AutoPlayerLexiconFirst(LexiconInterface lex) {
    this.initialize(lex);
  }
  
  /**
   * Find all the valid words automatically, found words should be added via the
   * <code>AbstractPlayer</code>'s <code>add</code> method.
   * 
   * @param board
   *          is the BoggleBoard on which words are found
   * @param lex
   *          is the lexicon in which words are searched/validated
   * @param minLength
   *          of words found by an autoplayer
   */
  public void findAllValidWords(BoggleBoard board, LexiconInterface lex,
                                int minLength) {
    /* Given a board and a lexicon, findAllValidWords uses the add() in AbstractPlayer.java
     * to add all words that are both in the board and in the lexicon.
     * 
     * This method will run after the human player finishes entering words. */
	// Use WordOnBoardFinder.java to search board for all words >= minLength in lexicon
	// will give coordinates, use coordinates to get word
	  WordOnBoardFinder myFinder = new WordOnBoardFinder();
	  // there is probably a simpler way of doing this but just putting it to at least have a method
	  // if lexicon is an ArrayList
	  if (lex instanceof LexiconArrayList) {
		  LexiconArrayList lexicon = (LexiconArrayList) lex;
		  for (int k = 0; k < lexicon.myDictionary.size(); k++) {
			 wordAdder(myFinder, board, lexicon.myDictionary.get(k),minLength);
		  }
	  }
	  else {
		  // lexicon is a Trie
		  LexiconTrie lexicon = (LexiconTrie) lex;
		  
		  // Find every word in each starting letter
		  for (int k = 0; k < lexicon.myDictionary.myChildren.size(); k++) {
			  findWordInTrie(lexicon.myDictionary.myChildren.get(k), "", myFinder, board, minLength);

		  }
		  
	  }
  }

  /** Traverses through a Trie and calls wordAdder to each complete word found.
   * 
   * 
   * @param node
   *                   is the node is the whose letter is added unto sofar. node is the next
   *                   LetterNode on the path that potentially contains a complete word.
   * @param sofar
   *                   is the word being constructed by traveling down the Trie.
   * @param finder
   *                   is passed in to wordAdder; it is used to verify that sofar is on the current board.
   * @param board
   *                   is the current game board.
   * @param minLength
   *                   is the minimum length of a potential word.
   */
  private void findWordInTrie (LexiconTrie.LetterNode node, String sofar, WordOnBoardFinder finder, 
		  BoggleBoard board, int minLength) {
	  
	  // Add node's letter to sofar
	  sofar += node.myLetter;

	  // check if LetterNode node completes a full word
	  if (node.completesFullWord) {
		  wordAdder(finder, board, sofar, minLength);
	  }

	  // This loop will not run if node has no children (meaning it has reached end of path)
	  for (int child = 0; child < node.myChildren.size(); child++) {
		  findWordInTrie(node.myChildren.get(child), sofar, finder, board, minLength);

	  }
  }


  /**
   * Adds the given word to this player's list of words.
   * 
   * @param myFinder
   *                    is used to verify that the word is present in the current game board.
   * @param board
   *                    is the current game board.
   * @param word
   *                    is the word added onto this player's list of words.
   * @param minLength
   *                    is the minimum length of that a word can be to be added onto this player's list of words.
   */
  public void wordAdder(WordOnBoardFinder myFinder, BoggleBoard board, String word, int minLength) {
	  
	  // If word is at least minimum length
	  if (word.length() >= minLength) {
		  List<BoardCell> coords = myFinder.cellsForWord(board, word);
		  
		  // If word present on board
		  if (coords.size() > 0) {
			  
			  String newEntry = "";
			  
			  for (BoardCell cell : coords) {
				  newEntry += board.getFace(cell.row, cell.col);
			  }
			  
			  this.add(newEntry);

		  }
	  }

  }
  
  @Override
  public String getName() {
    return "AutoPlayer";
  }
}
