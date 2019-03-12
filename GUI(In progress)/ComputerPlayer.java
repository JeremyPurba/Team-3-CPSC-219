
/*
* Import necessary package(s)
*/
import java.util.Random;

public class ComputerPlayer {
	

	private TokenColor computerColor = TokenColor.BLANK;
	private GameBoard gameBoard = null;
	private int col ;
	private int row ;


	public ComputerPlayer(GameBoard aGameBoard, TokenColor aColor){
	    gameBoard = aGameBoard;
	    computerColor = aColor;
	}
	/*
	  * Result of method: Generate random input of column and row for the Computer to
	  * place their piece on their turn. A valid input which in our
	  * game must be between 1 and 8 for both rows and columns.
	  */

	public int setRandomCol () {
		Random rand = new Random();
		col = rand.nextInt(7);
		col += 1;
		return (col) ;
	}

	public int setRandomRow () {
		Random rand = new Random();
		row = rand.nextInt(7);
		row += 1;
		return (row) ;
	}

	/*
	  * Result of method: First do the setRandomCol and setRandomRow methods to get the input,
	  * then check if the random numbers entered by
	  * the Computer is a valid space on the board via the checkAdjacentCells.
	  * Once this has been done, the flip method is called upon to check to
	  * see whether a game piece placed in the row and column specified is capable of flipping
	  * the opposite color's pieces. If not, since this is a rule of how to play Othello,
	  * the Computer needs to generate another Random number for rows and columns again
	  * Which row and column you wish to move to will be re-prompted.
	  */

	public boolean makeMove() {
	      boolean status = false;

	   if (gameBoard.checkForValidMove(computerColor) == true) 
	   {
	      while (status == false) {
	        	setRandomRow();
	    		setRandomCol();
	    	    if (gameBoard.checkAdjacentCells(row, col, computerColor) == true && gameBoard.flip(row, col, computerColor, false) != 0){
	    	        status = true;
	    	    }
	    	  
/*	    		
	    		status = gameBoard.checkAdjacentCells(row, col, computerColor);
	    		if(gameBoard.flip(row, col, computerColor, false) == 0){
	    			status = false;
	    		}else {
	    			status = true;
	    		} 
*/
	      }
	   }
	  return status;
	}
}
