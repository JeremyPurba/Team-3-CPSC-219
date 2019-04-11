/*
* Import necessary package(s)
*/
import java.util.Scanner;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HumanPlayer {
	  /*
	  * Initializing instance variables.
	  */
	  private TokenColor playerColor = TokenColor.BLANK;
	  private GameBoard gameBoard = null;
	  private Scanner message_entered_by_user = new Scanner(System.in);

	  /**
	  * A constructor which takes two parameters
	  * Result of method: sets aGameBoard entered by the user to the
	  * gameBoard instance variable in the HumanPlayer class. This method also sets
	  * the aColor entered by the user to the playerColor instance variable in
	  * the HumanPlayer class.
	  * @param aGameBoard - an instance of the GameBoard class.
	  * @param aColor - an instance of the TokenColor enum.
	  */
	  public HumanPlayer(GameBoard aGameBoard, TokenColor aColor){
	    gameBoard = aGameBoard;
	    playerColor = aColor;
	  }

	  /*
	  * Note that this method is never called in the GUI version, however only during the console game, since input is
	  * being entered by the user.
	  * Result of method: Gets and then validates the input of which row or column the user wishes to
	  * place their piece on their turn. If the user enters a number for a row or column that does not
	  * exist, a message will be displayed, prompting them to enter a valid input which in our
	  * game must be between 1 and 8 for both rows and columns. i.e. 9 is entered as row, error message will be
	  * displayed and row will be asked to be re-entered immediately.This message will be executed by the checkWithinboard method.
	  * @param row_or_column - a variable associated with the prompt the user will see on the console.(Either
	  * "Row: " or "Column: ")
	  * @return - the integer entered by the user if valid and therefore within the bounds of the board.
	  */
	  public int GetAndValidateInputFromUser(String row_or_column) {
			boolean inputValid = false;
	    int rowColumn = -100;

	    do {
				System.out.print(row_or_column);
				rowColumn = message_entered_by_user.nextInt() - 1;
	      if (gameBoard.checkWithinBoard(rowColumn) == true) {
	        inputValid = true;
	      }
			} while (inputValid == false);

	    return rowColumn;
		}

	  /*
	  * Result of method: This method first checks to see if the clicked button is a valid move by
		* the formal rules of the game by calling the checkForValidMove and if this is true, this method will conduct
		* a flipping test, to see if the move will be capable of flipping adjacent cells and if it is, it will return a boolean
		* who will be later called upon in order to actually show the flipping of the tokens on the GUI game board.
		* If the button clicked by the user is not a valid move to click, a message will be displayed underneath the
		* gameBoard, alerting the player to click somewhere else to play. As a result, the return value will be false.
	  * @return status - a variable associated with whether a move can be successfully made.
	  */
	  public boolean makeMove(int aRow, int aColumn, Label aMessage) {
	    boolean status = false;
	    int row = aRow;
	    int column = aColumn;

        if (gameBoard.checkForValidMove(playerColor) == true)
        {

	      if (gameBoard.checkAdjacentCells(row, column, playerColor) == true && gameBoard.flip(row, column, playerColor, false) != 0){
	        status = true;
	      }
	      else{

	        aMessage.setText("Invalid move. Try again.");
	        status = false;
	      }
        }

	    return status;
	  }

}
