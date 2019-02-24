# Team-3-CPSC-219
/*
* Import necessary package(s)
*/
import java.util.Scanner;

public class HumanPlayer {
  /*
  * Initializing instance variables.
  */
  private Color playerColor = Color.BLANK;
  private GameBoard gameBoard = null;
  private Scanner message_entered_by_user = new Scanner(System.in);

  /**
  * A constructor which takes two parameters
  * Result: sets aGameBoard entered by the user to the
  * gameBoard instance variable in the HumanPlayer class. This method also sets
  * the aColor entered by the user to the playerColor instance variable in
  * the HumanPlayer class.
  * @param aGameBoard - a variable associated to the game board enetered by the user.
  * @param aColor - a variable associated to the color board enetered by the user.
  */
  public HumanPlayer(GameBoard aGameBoard, Color aColor){
    gameBoard = aGameBoard;
    playerColor = aColor;
  }

  /*
  * Result of method: Gets and then validates the input of which row or column the user wishes to
  * place their piece on their turn. If the user enters a number for a row or column that does not
  * exist, a message will be displayed, prompting them to enter a valid input which in our
  * game must be between 1 and 8 for both rows and columns. i.e. 9 is entered as row, error message will be
  * displayed and row will be asked to be re-entered immediately.This message will be executed by the checkWithinboard method.
  * @ param row_or_column - a variable associated with the prompt the user will see on the console.(Either
  * "Row: " or "Column: ")
  * @ return - the integer enetred by the user, if valid and therefore within the bounds of the board.
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
  * Result of method: First checks to see if the column or row number entered by
  * the user is a valid space on the board via the GetAndValidateInputFromUser in the HumanPlayer
  * class. If not, an error message is displayed. Once user input has been validated,
  * the flip method is called upon to check to see whether a game piece placed in the row and
  * column specified is capable of flipping
  * the opposite color's pieces. If not, since this is a rule of how to play Othello,
  * this method will display a message saying that such a move is illegal and explain why and will then prompt the
  * user to enter a legal move. Which row and column you wish to move to will be re-prompted.
  * @ return status - a varibale associated with whether a move has successfully been made.
  */
  public boolean makeMove() {
    int flip_count = 0;
    boolean status = false;
    int row = 0;
    int column = 0;

    while (status == false) {
      row = GetAndValidateInputFromUser("Row: ");
      column = GetAndValidateInputFromUser("Column: ");
      status = gameBoard.checkAdjacentCells(row, column, playerColor);
      if (status == false){
        System.out.println("Invalid move. Try again.");
      }
    }

    if (gameBoard.flip(row, column, playerColor) == 0){
      System.out.println("You cannot place a piece here since you will not be overturning any of your opponent's game pieces.");
      status = false;
    }
    else {
      status = true;
    }

    return status;
  }
}

