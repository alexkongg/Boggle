import java.util.List;

import junit.framework.TestCase;

public class WordOnBoardFinderTest extends TestCase {
	/* TODO: Write extensive test cases for EVERY non-trivial piece of code.
	 * Private helper methods should have associated public static tester methods
	 * in WordOnBoardFinder.java. */
	private BoardMaker myMaker;
	private WordOnBoardFinder myFinder;

	/**
	 * Code that is run before each test method (automatically)
	 */
	public void setUp() {
		myMaker = new BoardMaker();
		myFinder = new WordOnBoardFinder();
	}

	/**
	 * Given a board and list of board cells, return a string of the concatenated
	 * cell characters.
	 * 
	 * @param board
	 *          The board to look at
	 * @param list
	 *          The list of cells to combine
	 * @return A string comibining the cells
	 */
	private String getWord(BoggleBoard board, List<BoardCell> list) {
		String word = "";
		for (BoardCell cell : list) {
			word += board.getFace(cell.row, cell.col);
		}
		return word;
	}

	/**
	 * This tests that we walk along the corners successfully. It is a very basic
	 * test- if you pass it, you should not assume that your code will work in all
	 * cases.
	 */

	public void testGoodCorners() {

		String[] boardContents = { "atruq", "seana", "niotc", "bdenk", "armin" };
		BoggleBoard board = myMaker.makeBoard(boardContents);

		String[] cornerWords = { "ate", "noted", "net", "urn", "bind", "aside",
				"noise", "quack", "armin" };
		for (String s : cornerWords) {
			List<BoardCell> list = myFinder.cellsForWord(board, s);
			String word = getWord(board, list);
			assertEquals("fail for " + s, s, word);
		}
	}



	public void testColumns() {

		String[] boardContents = { "acbqe", "raoal", "mkxrp", "oeatp", "rdaza" };
		BoggleBoard board = myMaker.makeBoard(boardContents);

		String[] cornerWords = { "armor", "caked", "box", "quartz", "apple"  };

		for (String s : cornerWords) {
			List<BoardCell> list = myFinder.cellsForWord(board, s);
			String word = getWord(board, list);

			assertEquals("fail for " + s, s, word);


		}

	}

	public void testRows() {

		String[] boardContents = { "armor", "caked", "xobab", "ztraq", "elppa" };
		BoggleBoard board = myMaker.makeBoard(boardContents);

		String[] cornerWords = { "armor", "caked", "box", "quartz", "apple"  };

		for (String s : cornerWords) {
			List<BoardCell> list = myFinder.cellsForWord(board, s);
			String word = getWord(board, list);

			assertEquals("fail for " + s, s, word);


		}

	}

	public void testDiagonal() {

		String[] boardContents = { "abmoa", "crkrd", "xomab", "zeroq", "dlppr" };
		BoggleBoard board = myMaker.makeBoard(boardContents);

		String[] cornerWords = { "armor", "armed"  };

		for (String s : cornerWords) {
			List<BoardCell> list = myFinder.cellsForWord(board, s);
			String word = getWord(board, list);

			assertEquals("fail for " + s, s, word);


		}

	}

}
