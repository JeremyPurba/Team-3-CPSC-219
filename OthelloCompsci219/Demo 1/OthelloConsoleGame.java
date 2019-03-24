
/*
* Import necessary package(s)
*/
import java.util.Scanner;

public class OthelloConsoleGame {
	/*
	* Initialize instance variables.
	*/
	private StateOfGame stateOfGame;
	private Color aTurn;
	private GameBoard gameBoard;
	private int number_of_columns = 8;
	private int number_of_rows = 8;
	private Scanner message_entered_by_user = new Scanner(System.in);

	/*
	* A constructor which takes no parameters and returns nothing.
	* This constructor creates a new object of class GameBoard and stores it
	* into the gameBoard instance variable.
	*/
	public OthelloConsoleGame() {
		gameBoard = new GameBoard(number_of_rows, number_of_columns);
	}

	/*
	* Result of method: Determines the order of turns when prompting
	* the user to either enter W or B when asking who should go first.
	* If the user does not enter a capital or smaller case W or B, this method
	* will display an error message and prompt the user again. If input entered is correct however,
	* the aTurn instance variable will be set to whosever's turn it is.
	*/
	public void AskUserForInput() {
		boolean inputValid = false;
		System.out.print("Who would like to go first? W or B? ");
		while (inputValid == false) {
			char first_turn = message_entered_by_user.next().charAt(0);
			first_turn = Character.toUpperCase(first_turn);
			if (first_turn == 'B' || first_turn == 'W') {
				inputValid = true;
				if (first_turn == 'B'){
					aTurn = Color.BLACK;
				}
				else{
					aTurn = Color.WHITE;
				}
			}
			else {
				System.out.println("Please enter a valid character. Who should go first W or B?");
			}
		}
	}

	/*
	* Result of method: This is the class that executes the Othello game on the console.
	* First it displays a welcome message and then displays options to the user
	* of whether they want to play two-player, one-player, or exit.
	* This method evalutaes the choice. If two-player is selected, two objects of the HumanPlayer class
	* are created. If one-player is selected, one object of the ComputerPlayer class is
	* created and a HumanPlayer object is created as well. If exit is chosen, the program exits without crashing.
	* After this, the AskUserForInput method and the setStartingPositions method are executed
	* and then the state of the game instance variable is set to in progress and until it is changed, the game
	* will continue to run. Then, this method draws the board for the game, and checks if the current player's
	* turn is possible.  If there is a successful play for the
	* current user to play, it will display an appropriate message such as "Black's turn!" and allow
	* the player to proceed as per the game allows via the makeMove
	* method.
	*/
	public void playOthello() {
		char choice = ' ';
    Boolean move_status_w = false;
    Boolean move_status_b = false;
    HumanPlayer human_w = null;
    HumanPlayer human_b = null;
		ComputerPlayer computer_b = null;
		System.out.println("Welcome to Othello!");
		System.out.println("Select your choice from the menu below: ");
		System.out.println("A) Two human players");
		System.out.println("B) One human player vs. computer");
		System.out.println("C) Exit Game");
		System.out.print("Enter your choice: ");
		while (choice != 'A' &&
					 choice != 'B' &&
					 choice != 'C'){
		   Scanner keyboard = new Scanner(System.in);
       choice = keyboard.next().charAt(0);
			 choice = Character.toUpperCase(choice);
		}
		if (choice == 'A'){
			human_w = new HumanPlayer(gameBoard, Color.WHITE);
			human_b = new HumanPlayer(gameBoard, Color.BLACK);
		}
		else if(choice == 'B'){
			human_w = new HumanPlayer(gameBoard, Color.WHITE);
			computer_b = new ComputerPlayer(gameBoard, Color.BLACK);
		}
		else{
			return;
		}
		AskUserForInput();
		gameBoard.setStartingPositions();
	  stateOfGame = StateOfGame.IN_PROGRESS;

		do {
			gameBoard.draw();

      if (choice == 'A'){
				if (aTurn == Color.BLACK) {
					move_status_b = false;
					move_status_b = human_b.makeMove();
				}
				else {
					move_status_w = false;
					move_status_w = human_w.makeMove();
				}
			}
			else {
				if (aTurn == Color.BLACK) {
					move_status_b = false;
					move_status_b = computer_b.makeMove();
				}
				else {
					move_status_w = false;
					move_status_w = human_w.makeMove();
				}
			}

      		checkIfWinner(move_status_w, move_status_b);
			if (stateOfGame == stateOfGame.IN_PROGRESS) {
				if (aTurn == Color.BLACK) {
					if (move_status_b == false) {
						System.out.println("No moves available for Black to make.");
					}
					aTurn = Color.WHITE;
					System.out.println("White's Turn!");
				}
				else {
					if (move_status_w == false) {
						System.out.println("No moves available for White to make.");
					}

					aTurn = Color.BLACK;
					System.out.println("Black's Turn!");
				}
			}

		} while (stateOfGame == stateOfGame.IN_PROGRESS);
		
	}

	/*
	* Result of method: Checks to if there is a winner on the board. This is determined
	* by the countBlack and countWhite methods defined in the GameBoard class. If the board
	* is filled in completely and there is more black than white, black is the winner of the game
	* and an appropriate message will be printed in the console.
	* On the contrary, if there is more white than black pieces, white is the winner and an appropriate
	* message will be printed in the console.
	* Similarly, on the rare occasion that the same amount of pieces are black and white, the game is a draw
	* and a message stating this will be printed.
	*/
	public void checkIfWinner(boolean aMoveStatus_w, boolean aMoveStatus_b) {
		if (aMoveStatus_w == false && aMoveStatus_b == false) {
		if (gameBoard.countBlack() < gameBoard.countWhite()) {
			stateOfGame = StateOfGame.WHITE_WINNER;
			System.out.println("Congratulations, White! You have won this game!");
		} else if (gameBoard.countBlack() < gameBoard.countWhite()) {
			stateOfGame = StateOfGame.BLACK_WINNER;
			System.out.println("Congratulations, Black! You have won this game!");
		} else {
			stateOfGame = StateOfGame.DRAW;
			System.out.println("You both win! It's a draw!");
		}
	}
	}

	/*
	* Result of method: Places either a white or black gamepiece on the board.
	* @param row - a value associated to a numbered row entered by the user of where the
	* game piece should be placed.
	* @param column - a value associated to a numbered column entered by the user where the
	* game piece should be placed. .
	*/
	public void placeGamePiece(int row, int column, Color aColor) {
		gameBoard.cell[row][column].set(aColor);
	}

	/*
	* Result of method: Changes the turn of the player. If the current instance variable
	* aTurn is black, meaning if it is currently Black's turn, the next turn will be allocated to white.
	*/
	public void changeTurnOfPlayer() {
		if (aTurn == Color.BLACK){
			aTurn = Color.WHITE;
		}
		else{
			aTurn = Color.BLACK;
		}
	}

	/*
	* Result of method: This main method is what will generate the game being played on
	* the console. A new object of the OthelloConsoleGame class is created and the playOthello
	* method is called upon this object.
	*/
	public static void main(String[] args) {
		OthelloConsoleGame anOthelloGame = new OthelloConsoleGame();
		anOthelloGame.playOthello();

	}

}
