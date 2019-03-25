import javafx.event.ActionEvent;
import javafx.animation.*;
import javafx.scene.transform.Rotate;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.event.Event;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.MouseButton;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.event.EventHandler;
import java.lang.Thread;

import com.sun.javafx.font.freetype.HBGlyphLayout;

public class GameGUI extends javafx.application.Application {
    private StateOfGame stateOfGame;
    private TokenColor aTurn;
    private GameBoard gameBoard;
    private int number_of_columns = 8;
    private int number_of_rows = 8;

    private final String GAME_NAME = "Othello";
    private final int BOARD_SIZE = 8;
    private final int BOX_SIZE = 50;
    private final int FLIP_DURATION = 500;

    private int blackPoints = 0;
    private int whitePoints = 0;

    private Label gameTitle = new Label(GAME_NAME);
    private Label gamETitle = new Label(GAME_NAME);
    private Label gamETitlE = new Label(GAME_NAME);

    private Label game_message;

    private Stage window;
    private Scene gameScene;
    private OthelloPane othelloPane;
    private int NumOfHumanPlayers = 0;
    private int row = 0;
    private int column = 0;
	  private Boolean move_status_w = false;
	  private Boolean move_status_b = false;
	  private HumanPlayer human_w = null;
	  private HumanPlayer human_b = null;
	  private ComputerPlayer computer_w = null;


    @Override
    public void start(Stage primaryStage) {

        window = primaryStage;
        FlowPane root1 = new FlowPane(Orientation.VERTICAL);
        gameTitle = TitleLabel(gameTitle, 80, true);

        //Create game board
        gameBoard = new GameBoard(number_of_rows, number_of_columns);

        // startScene ------------------------------------------------------------------------------------------------
        Label playLabel = new Label("Select a Game Mode: ");
        playLabel.setFont(new Font(50));
        playLabel.setAlignment(Pos.CENTER);

        Button onePlayerButton = new Button("One Player");
        Button twoPlayerButton = new Button("Two Players");
        twoPlayerButton.setTranslateX(450);
        twoPlayerButton.setTranslateY(-24);

        root1.setAlignment(Pos.CENTER);
        root1.getChildren().addAll(gameTitle, playLabel, onePlayerButton, twoPlayerButton);

        Scene startScene = new Scene(root1, 700, 700);

	    onePlayerButton.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override
	        public void handle(ActionEvent event) {
	        	NumOfHumanPlayers = 1;
	    		human_b = new HumanPlayer(gameBoard, TokenColor.BLACK);
	    		computer_w = new ComputerPlayer(gameBoard, TokenColor.WHITE);
				window.setScene(gameScene);
	        }
	     });

	    twoPlayerButton.setOnAction(new EventHandler<ActionEvent>() {
	        @Override
	        public void handle(ActionEvent event) {
	        	NumOfHumanPlayers = 2;
	        	human_b = new HumanPlayer(gameBoard, TokenColor.BLACK);
	        	human_w = new HumanPlayer(gameBoard, TokenColor.WHITE);
				window.setScene(gameScene);
	        }
	    });

        //----------------------------------------------------------------------------------------------------------
        //Game Board Window
        FlowPane root2 = new FlowPane(Orientation.VERTICAL);
        root2.setAlignment(Pos.TOP_CENTER);
        gamETitle = TitleLabel(gamETitle, 50, true);

        othelloPane = new OthelloPane(BOARD_SIZE, BOX_SIZE, FLIP_DURATION);
        othelloPane.setAlignment(Pos.TOP_CENTER);

        root2.setVgap(15);
        game_message = new Label();
        game_message.setPrefWidth(600);
        game_message.setPrefHeight(50);
        game_message.setFont(Font.font("Arial", FontWeight.BOLD, 18));

        root2.getChildren().addAll(gamETitle, othelloPane, game_message);
        gameScene = new Scene(root2, 700, 700);

        //----------------------------------------------------------------------------------------------------------

        window.setTitle(GAME_NAME);
        window.setScene(startScene);
        window.show();

        gameBoard.setStartingPositions();
        stateOfGame = StateOfGame.IN_PROGRESS;
        aTurn = TokenColor.BLACK;                  //Black starts first always
        game_message.setText("Black's Turn");
        othelloPane.UpdatePane(gameBoard);
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
//			System.out.println("Congratulations, White! You have won this game!");
			game_message.setText("Congratulations, White! You have won this game!");
		} else if (gameBoard.countBlack() < gameBoard.countWhite()) {
			stateOfGame = StateOfGame.BLACK_WINNER;
//			System.out.println("Congratulations, Black! You have won this game!");
			game_message.setText("Congratulations, Black! You have won this game!");
		} else {
			stateOfGame = StateOfGame.DRAW;
//			System.out.println("You both win! It's a draw!");
			game_message.setText("You both win! It's a draw!");
		}
	}
	}


	public Label TitleLabel(Label name, int size, boolean bold){
        name.setFont(new Font(size));
        name.setMaxWidth(Double.MAX_VALUE);
        name.setAlignment(Pos.TOP_CENTER);
        if(bold == true){
            name.setStyle("-fx-font-weight: bold");
        }
        name.setPadding(new Insets(size, 0, size, 0));

        return name;
    }

    class OthelloPane extends GridPane { //{{{
	    private String blankBackgroundInHex = "#228b22";
	    private String whiteBackgroundInHex = "#F8F8FF";
	    private String blackBackgroundInHex = "#000000";
      private String btn_text = "#228b22";
        private int boardSize = 100;
        private int boxSize = 100;
        private Duration flipDuration;
        private int[][] directions = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        private Button[][] boxes;

        public OthelloPane(int boardSize, int boxSize, double flipDuration) {
            super();

            boxes = new Button[boardSize][boxSize];

            this.boardSize = boardSize;
            this.boxSize = boxSize;
            this.flipDuration = Duration.millis(flipDuration);

            // setup grid constraints
            for (int i = 0; i < boardSize; i++)
                getRowConstraints().add( new RowConstraints(boxSize));
            for (int i = 0; i < boardSize; i++)
                getColumnConstraints().add( new ColumnConstraints(boxSize));

            for (int i = 0; i < BOARD_SIZE; i++) {
                for (int j = 0; j < BOARD_SIZE; j++) {
                	 //btn_text = "(" + i + "," + j + ")";
                    Button box = new Button(/*btn_text*/);
                    box.setMinWidth(boxSize);
                    box.setMinHeight(boxSize);
                    box.setStyle("-fx-background-color: " + blankBackgroundInHex + ";");
                    /*button1.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");*/
                    setBox(i, j, box);
                    add(box, i, j);

                }
            }

            setGridLinesVisible(true);
            setAlignment(Pos.CENTER);
        }


        private void setBox(int aRow, int aColumn, Button box) {
            boxes[aRow][aColumn] = box;
	        box.setOnAction(new EventHandler<ActionEvent>() {
	        @Override
	        	public void handle(ActionEvent event) {
	        	row = aRow;
	        	column = aColumn;
	        	System.out.println("row: " + row + "Column: " + column);
	        	playGame();
	        	}
	        	});

        }


	public void UpdatePane(GameBoard aGameBoard)
	{


    Image image1 = new Image("white.png", 33, 33, false, false);
    Image image2 = new Image("black.png", 34, 34, false, false);

	  TokenColor cellColor;
	  for (int i = 0; i < boardSize; i++) {
	     for (int j = 0; j < boardSize; j++) {
               cellColor = gameBoard.getCellColor(i,j);
               if (cellColor == TokenColor.BLANK) {
                boxes[i][j].setStyle("-fx-background-color: " + blankBackgroundInHex + ";");
               }
               else if (cellColor == TokenColor.WHITE) {
                 boxes[i][j].setText("");
                 boxes[i][j].setGraphic(new ImageView(image1));
                 //boxes[i][j].setStyle("-fx-background-color: " + whiteBackgroundInHex + ";");
               }
               else {
                 boxes[i][j].setText("");
                 boxes[i][j].setGraphic(new ImageView(image2));
                 //boxes[i][j].setStyle("-fx-background-color: " + blackBackgroundInHex + ";");
               }
             }
	    }
	 }

	 }


    public void playGame() {

        	Boolean move_possible = gameBoard.checkForValidMove(aTurn);
        	System.out.println("Move possible: " + move_possible);

        	if (NumOfHumanPlayers == 2){
				if (aTurn == TokenColor.BLACK) {
					move_status_b = false;
					move_status_b = human_b.makeMove(row, column, game_message);
				}
				else {
					move_status_w = false;
					move_status_w = human_w.makeMove(row, column, game_message);
				}
			}

        	else {
				if (aTurn == TokenColor.BLACK) {
					move_status_b = false;
					move_status_b = human_b.makeMove(row, column, game_message);
          try{
               Thread.sleep(3000);
            }catch(InterruptedException e){

            }
					othelloPane.UpdatePane(gameBoard);
				}
			}

        	if (move_possible == false) {
        		checkIfWinner(move_status_w, move_status_b);
        	}

			if (stateOfGame == stateOfGame.IN_PROGRESS) {
				if (NumOfHumanPlayers == 2) {
					if (aTurn == TokenColor.BLACK) {
						if (move_status_b == false && move_possible == false) {
							aTurn = TokenColor.WHITE;
							game_message.setText("No moves available for Black to make. White's Turn!");
						}
						else if (move_status_b == false && move_possible == true) {
							aTurn = TokenColor.BLACK;
							game_message.setText("Invalid move.  Black try again!");
						}
						else {
							aTurn = TokenColor.WHITE;
							game_message.setText("White's turn!");
						}
					}
					else {
						if (move_status_w == false && move_possible == false) {
							aTurn = TokenColor.BLACK;
							game_message.setText("No moves available for White to make. Black's Turn!");
						}
						else if (move_status_w == false && move_possible == true) {
							aTurn = TokenColor.WHITE;
							game_message.setText("Invalid move.  White try again!");
						}
						else {
							aTurn = TokenColor.BLACK;
							game_message.setText("Black's turn!");
						}
					}
				}
				else {
					if (aTurn == TokenColor.BLACK) {
						if (move_status_b == false && move_possible == false) {
							aTurn = TokenColor.WHITE;
							Boolean move_possible_w = gameBoard.checkForValidMove(aTurn);
							if (move_possible_w == true) {
								game_message.setText("No moves available for Black to make. White's Turn!");
								move_status_w = false;
								move_status_w = computer_w.makeMove();
							}
							else {
								checkIfWinner(move_status_w, move_status_b);
							}
							if (move_status_w == true) {
								aTurn = TokenColor.BLACK;
								game_message.setText("Black's turn!");
							}
						}
						else if (move_status_b == false && move_possible == true) {
							aTurn = TokenColor.BLACK;
							game_message.setText("Invalid move.  Black try again!");
						}
						else {
							aTurn = TokenColor.WHITE;
							game_message.setText("White's turn!");
/*							try{
						        Thread.sleep(2000);
						     }catch(InterruptedException e){}
*/
							move_status_w = false;
							move_status_w = computer_w.makeMove();
							aTurn = TokenColor.BLACK;
							game_message.setText("Black's turn!");
						}
					}
				}
			}

			othelloPane.UpdatePane(gameBoard);
    	}
  }
