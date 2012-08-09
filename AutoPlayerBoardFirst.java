
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

public class AutoPlayerBoardFirst extends AbstractPlayer {
  
  public AutoPlayerBoardFirst(LexiconInterface lex) {
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
	  
    // For each cell, find word paths starting from the cell
	for (int r = 0; r < board.size(); r ++ ) {
		
		for (int c = 0; c < board.size(); c ++ ) {
			
			// Initiate a clean boolean [] [] visited to pass on for each path beginning at cell 
			boolean [] [] visited = new boolean [board.size()] [board.size()];
			
			// Mark current position as visited
			visited[r][c] = true;
			
			
			findAllValidWordsHelper(board, lex, r, c, "", visited, minLength);
		}
	}
}
  /**
   * Starting at cell r,c in board, check if sofar + the cell's face is in the lexicon.
   * If yes, add the word to this.
   * Check if sofar + the cell's face is a prefix in the lexicon. If it is, call the helper
   * on every cell surrounding the cell.
   * 
   * @param board
   *          is the BoggleBoard on which words are found
   * @param lex
   *          is the lexicon in which words are searched/validated
   * @param minLength
   *          of words found by an autoplayer
   * @param r
   * 		  is the current row
   * @param c
   * 		  is the current column
   * 
   * @param sofar
   *          is the word constructed so far by traversing the board
   * @param visited
   *          is a table keeping track of which cells have been visited
   *          
   * @param minLength
   *          is the minimum length of a valid word
   */
  public void findAllValidWordsHelper(BoggleBoard board, LexiconInterface lex,
		  int r, int c, String sofar, boolean [] [] visited, int minLength) {

	  sofar += board.getFace(r,c);
	  if (lex.contains(sofar)) {
		  if (sofar.length() >= minLength) {
			  this.add(sofar);
		  }
	  } 


	  // Letter at r,c is the last letter of a prefix in lexicon
	  if (lex.containsPrefix(sofar)) {
		  
		  // Current BoardCell
		  BoardCell current = new BoardCell(r, c);
		  
		  
		  
		  for (int row = 0; row < board.size(); row ++) {
			  
			  for (int col = 0; col < board.size(); col ++) {
				  
				  BoardCell next = new BoardCell(row, col);
				  
				  // Check if cell at (row, col) is a neighbor of current cell
				  // and if (row, col) has not been visited. If both conditions
				  // are met, then call the helper method on (row, col).
				  if (current.isNeighbor(next) && visited[row][col] != true) {
					  
					  // Mark next cell as visited
					  visited[row][col] = true;
					  findAllValidWordsHelper(board, lex, row, col, sofar, visited, minLength);
					  
					  // After finding all valid paths, reset next cell as unvisited
					  visited[row][col] = false;
				  }
			  }

		  }
		  
	  }
  }


  @Override
  public String getName() {
    return "AutoPlayer";
  }
}
