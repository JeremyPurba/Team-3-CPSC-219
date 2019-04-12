/**
 * import necessary packages
 */
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.event.EventHandler;

/**
 * Class to create Graphical User Interface in place of OthelloConsoleGame
 * Draws game board and takes clicks to progress gameplay
 * @author Team 3
 */
public class GameGUI extends javafx.application.Application {
    //Create instances and initialize variables
    private StateOfGame stateOfGame;
    private TokenColor aTurn;
    private GameBoard gameBoard;
    private OthelloPane othelloPane;

    private int number_of_columns = 8;
    private int number_of_rows = 8;
    private int row = 0;
    private int column = 0;
    private int NumOfHumanPlayers = 0;

    private final int BOARD_SIZE = 8;
    private final int BOX_SIZE = 50;
    private final int FLIP_DURATION = 500;

    private final String GAME_NAME = "Othello";
    private Label gameTitle = new Label(GAME_NAME);
    private Label gamETitle = new Label(GAME_NAME);

    private Label game_message;
    private Stage window;
	private Scene gameScene;
	private Scene difficultyScene;

	private Boolean move_status_w = false;
	private Boolean move_status_b = false;
	private HumanPlayer human_w = null;
	private HumanPlayer human_b = null;
	private ComputerPlayer computer_w = null;
    public String difficult;
    
    /**
     * Result of method: Create and display multiple scenes based on which buttons
	 * are clicked. Also to display gameboard and take clicks.
     * @param primaryStage of type Stage
     */
    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;

        //Create game board
		gameBoard = new GameBoard(number_of_rows, number_of_columns);

		Button homeButton = new Button("Title Screen");
		homeButton.setTranslateX(515); homeButton.setTranslateY(-50);

		// Commented out as we removed different difficulties due to bugs
		// Button homeButton2 = new Button("Title Screen"); homeButton.setAlignment(Pos.BASELINE_RIGHT);
		// homeButton2.setTranslateX(427); homeButton2.setTranslateY(175);
        
        // startScene ------------------------------------------------------------------------------------------------
        FlowPane root1 = new FlowPane(Orientation.VERTICAL);
        gameTitle = TitleLabel(gameTitle, 80, true);

        Label playLabel = new Label("Select a Game Mode: ");
		playLabel.setFont(new Font(43)); playLabel.setTranslateX(10); playLabel.setTextFill(Paint.valueOf("#cecece"));
        playLabel.setAlignment(Pos.CENTER);

        Button onePlayerButton = new Button("One Player"); onePlayerButton.setTranslateY(20);
        
        onePlayerButton.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override
	        public void handle(ActionEvent event) {
	        	NumOfHumanPlayers = 1;
				difficult = "HARD";
	    		human_b = new HumanPlayer(gameBoard, TokenColor.BLACK);
				computer_w = new ComputerPlayer(gameBoard, TokenColor.WHITE);
				
				window.setScene(gameScene);
				gameBoard.setStartingPositions();
				stateOfGame = StateOfGame.IN_PROGRESS;
				aTurn = TokenColor.BLACK;                  //Black starts first always

				game_message.setText("Black's Turn");
				othelloPane.UpdatePane(gameBoard);
	        }
	    });

        Button twoPlayerButton = new Button("Two Players");
        twoPlayerButton.setTranslateX(300); twoPlayerButton.setTranslateY(-20);

        twoPlayerButton.setOnAction(new EventHandler<ActionEvent>() {
	        @Override
	        public void handle(ActionEvent event) {
				difficult = "TWOPPL";
                NumOfHumanPlayers = 2;

	        	human_b = new HumanPlayer(gameBoard, TokenColor.BLACK);
                human_w = new HumanPlayer(gameBoard, TokenColor.WHITE);
                
				window.setScene(gameScene);
				gameBoard.setStartingPositions();
				stateOfGame = StateOfGame.IN_PROGRESS;
				aTurn = TokenColor.BLACK;                  //Black starts first always
				game_message.setText("Black's Turn");
				othelloPane.UpdatePane(gameBoard);
	        }
	    }); 

		root1.setAlignment(Pos.CENTER); root1.setId("pane");
        root1.getChildren().addAll(gameTitle, playLabel, onePlayerButton, twoPlayerButton);

        Scene startScene = new Scene(root1, 700, 700);

        //Game Board Window--------------------------------------------------------------------------------------------
        FlowPane root2 = new FlowPane(Orientation.VERTICAL);
        root2.setAlignment(Pos.TOP_CENTER); root2.setId("pane"); root2.setVgap(15);
		gamETitle = TitleLabel(gamETitle, 50, true);
        
        othelloPane = new OthelloPane(BOARD_SIZE, BOX_SIZE, FLIP_DURATION);
        othelloPane.setAlignment(Pos.TOP_CENTER);

        game_message = new Label(); game_message.setPrefWidth(600); game_message.setPrefHeight(50);
        game_message.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        game_message.setAlignment(Pos.BASELINE_CENTER); game_message.setTextFill(Paint.valueOf("#cecece"));
        
        root2.getChildren().addAll(gamETitle, othelloPane, game_message, homeButton);
		gameScene = new Scene(root2, 700, 700);
		
		homeButton.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override
	        public void handle(ActionEvent event) {
				root2.getChildren().removeAll(gamETitle, othelloPane, game_message, homeButton);

				gameBoard = new GameBoard(number_of_rows, number_of_columns);
				othelloPane = new OthelloPane(BOARD_SIZE, BOX_SIZE, FLIP_DURATION);
				othelloPane.setAlignment(Pos.TOP_CENTER);

				root2.getChildren().addAll(gamETitle, othelloPane, game_message, homeButton);
				othelloPane.UpdatePane(gameBoard);
				window.setScene(startScene);
	        }
	    });
		
		// Commented out as we removed different difficulties due to bugs
        // //Difficulty selection scene--------------------------------------------------------------------------------
        // FlowPane root3 = new FlowPane(Orientation.VERTICAL);
		// root3.setAlignment(Pos.TOP_CENTER); root3.setVgap(15); root3.setId("pane");

        // Label gameTitleDiff = new Label(GAME_NAME);
        // gameTitleDiff = TitleLabel(gameTitleDiff, 50, true);

        // Label diffMessage = new Label("Select a Difficulty Level: "); diffMessage.setTextFill(Paint.valueOf("#cecece"));
		// diffMessage.setFont(new Font(40)); diffMessage.setAlignment(Pos.CENTER); diffMessage.setTranslateX(10);

        // Button easyButton = new Button("  Easy  "); easyButton.setAlignment(Pos.TOP_CENTER);
		// easyButton.setTranslateX(170); easyButton.setTranslateY(15);
        
        // easyButton.setOnAction(new EventHandler<ActionEvent>() {
	    //     @Override
	    //     public void handle(ActionEvent event) {
		// 		difficult = "EASY";
	    // 		human_b = new HumanPlayer(gameBoard, TokenColor.BLACK);
	    // 		computer_w = new ComputerPlayer(gameBoard, TokenColor.WHITE);
		// 		window.setScene(gameScene);

		// 		gameBoard.setStartingPositions();
		// 		stateOfGame = StateOfGame.IN_PROGRESS;
		// 		aTurn = TokenColor.BLACK;                  //Black starts first always
		// 		game_message.setText("Black's Turn");
		// 		othelloPane.UpdatePane(gameBoard);
	    //     }
		// });

		// Button mediumButton = new Button(" Normal "); mediumButton.setTranslateX(210); mediumButton.setAlignment(Pos.CENTER);
		// mediumButton.setTranslateX(163); mediumButton.setTranslateY(45);

        // mediumButton.setOnAction(new EventHandler<ActionEvent>() {
	    //     @Override
	    //     public void handle(ActionEvent event) {
		// 		difficult = "MEDIUM";
	    // 		human_b = new HumanPlayer(gameBoard, TokenColor.BLACK);
	    // 		computer_w = new ComputerPlayer(gameBoard, TokenColor.WHITE);
		// 		window.setScene(gameScene);

		// 		gameBoard.setStartingPositions();
		// 		stateOfGame = StateOfGame.IN_PROGRESS;
		// 		aTurn = TokenColor.BLACK;                  //Black starts first always
		// 		game_message.setText("Black's Turn");
		// 		othelloPane.UpdatePane(gameBoard);
	    //     }
        // });
        
		// Button hardButton = new Button("  Hard  "); hardButton.setTranslateX(447); hardButton.setAlignment(Pos.BOTTOM_CENTER);
		// hardButton.setTranslateX(167); hardButton.setTranslateY(75);

        // hardButton.setOnAction(new EventHandler<ActionEvent>() {
	    //     @Override
	    //     public void handle(ActionEvent event) {
		// 		difficult = "HARD";
	    // 		human_b = new HumanPlayer(gameBoard, TokenColor.BLACK);
	    // 		computer_w = new ComputerPlayer(gameBoard, TokenColor.WHITE);
		// 		window.setScene(gameScene);

		// 		gameBoard.setStartingPositions();
		// 		stateOfGame = StateOfGame.IN_PROGRESS;
		// 		aTurn = TokenColor.BLACK;                  //Black starts first always
		// 		game_message.setText("Black's Turn");
		// 		othelloPane.UpdatePane(gameBoard);
	    //     }
		// });
		
		// homeButton2.setOnAction(new EventHandler<ActionEvent>() {
	    // 	@Override
	    //     public void handle(ActionEvent event) {
		// 		root2.getChildren().removeAll(gamETitle, othelloPane, game_message, homeButton);

		// 		gameBoard = new GameBoard(number_of_rows, number_of_columns);
		// 		othelloPane = new OthelloPane(BOARD_SIZE, BOX_SIZE, FLIP_DURATION);
		// 		othelloPane.setAlignment(Pos.TOP_CENTER);

		// 		root2.getChildren().addAll(gamETitle, othelloPane, game_message, homeButton);
		// 		othelloPane.UpdatePane(gameBoard);
		// 		window.setScene(startScene);
	    //     }
	    // });
        
        // root3.getChildren().addAll(gameTitleDiff, diffMessage, easyButton, mediumButton, hardButton, homeButton2);
        // difficultyScene = new Scene(root3, 700, 700);

        //-----------------------------------------------------------------------------------------------------------
        window.setTitle(GAME_NAME);
		window.setScene(startScene);
		
		startScene.getStylesheets().add("GameStuff.css"); startScene.getStylesheets().add("Background.css");
		// Commented out as we removed different difficulties due to bugs
		//difficultyScene.getStylesheets().add("GameStuff.css"); difficultyScene.getStylesheets().add("Background.css");
		gameScene.getStylesheets().add("Background.css");
		homeButton.getStylesheets().add("GameStuff.css"); homeButton.setId("smallButton");
		// Commented out as we removed different difficulties due to bugs
		//homeButton2.getStylesheets().add("GameStuff.css"); homeButton2.setId("smallButton");

        window.show();
    }

    /**
     * Result of method: format title labels to a specific way based on parameters
     * @param name of label to edit
     * @param size of type int to set font size
     * @param bold of type boolean of if to bold or not bold the text
     * @return name of type label after being edited in a specific way
     */
    public Label TitleLabel(Label name, int size, boolean bold){
		name.setTextFill(Paint.valueOf("#cecece"));
        name.setFont(new Font(size));
        name.setMaxWidth(Double.MAX_VALUE);
        name.setAlignment(Pos.TOP_CENTER);
        if(bold == true){
            name.setStyle("-fx-font-weight: bold");
        }
        name.setPadding(new Insets(size, 0, size, 0));

        return name;
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
				game_message.setText("Congratulations, White! You have won this game!");

			} else if (gameBoard.countBlack() < gameBoard.countWhite()) {
				stateOfGame = StateOfGame.BLACK_WINNER;
				game_message.setText("Congratulations, Black! You have won this game!");
			
			} else {
				stateOfGame = StateOfGame.DRAW;
				game_message.setText("You both win! It's a draw!");
			}
		}
    }
    
    /**
     * Innerclass to create the gameBoard grid which is a series of a 3D array of buttons
     * as well as the game tokens
     */
    class OthelloPane extends GridPane {
	    private String blankBackgroundInHex = "#077f2b";
        private int boardSize = 100;
        private int boxSize = 100;
        private Duration flipDuration;
        private Button[][] boxes;

        /**
         * Result of constructor: set up coordinate pane for gameboard (as a 3D array)
		 * and make each coordinate a button and display in start method.
		 * @param boardSize of type int
		 * @param boxSize of type int
		 * @param flipDuration of type double
         */
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

        /**
         * Result of method: gets row and column coordinate for button clicked
		 * by user. Coordinates then played through play game where 
		 * valid clicks are checked.
         * @param aRow of type int
         * @param aColumn of type int
         * @param box of type button
         */
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
        
        /**
         * Result of method: set certain coordinates (boxes) on the board to either
         * the blank board color, or the black/white game token
         * @param aGameBoard that is being played on
         */
        public void UpdatePane(GameBoard aGameBoard){
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
                    }
                    else {
                        boxes[i][j].setText("");
                        boxes[i][j].setGraphic(new ImageView(image2));
                    }
                }
            }
        }
    }

    /**
	 * Result of method: Used to run game. Checks for number of human players then correctly
	 * prompts user(s) for inputs when its their respective turn. The move entered then goes
	 * through a series of checks to ensure it is a valid move before updating the gameboard.
	 */
    public void playGame() {
        Boolean move_possible = gameBoard.checkForValidMove(aTurn);
		System.out.println("Move possible: " + move_possible);
		
		// Hard mode or two human players ---------------------------------------------------------------------------
		if(difficult == "HARD" || difficult == "TWOPPL"){
			// Two player game
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
				//One player game
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
		// Commented out as we removed different difficulties due to bugs
        // else if(difficult == "MEDIUM"){ // Medium level AI ------------------------------------------------------------
		// 	if (aTurn == TokenColor.BLACK) {
		// 		move_status_b = false;
		// 		move_status_b = human_b.makeMove(row, column, game_message);
          
		// 		othelloPane.UpdatePane(gameBoard);
		// 	}
			
        // 	if (move_possible == false) {
        // 		checkIfWinner(move_status_w, move_status_b);
        // 	}

		// 	if (stateOfGame == stateOfGame.IN_PROGRESS) {
		// 		if (aTurn == TokenColor.BLACK) {
		// 			if (move_status_b == false && move_possible == false) {
		// 				aTurn = TokenColor.WHITE;
		// 				Boolean move_possible_w = gameBoard.checkForValidMove(aTurn);
		// 				if (move_possible_w == true) {
		// 					game_message.setText("No moves available for Black to make. White's Turn!");
		// 					move_status_w = false;
		// 					move_status_w = computer_w.makeMove2();
		// 				}
		// 				else {
		// 					checkIfWinner(move_status_w, move_status_b);
		// 				}
		// 				if (move_status_w == true) {
		// 					aTurn = TokenColor.BLACK;
		// 					game_message.setText("Black's turn!");
		// 				}
		// 			}
		// 			else if (move_status_b == false && move_possible == true) {
		// 				aTurn = TokenColor.BLACK;
		// 				game_message.setText("Invalid move.  Black try again!");
		// 			}
		// 			else {
		// 				aTurn = TokenColor.WHITE;
		// 				game_message.setText("White's turn!");
		// 				move_status_w = false;
		// 				move_status_w = computer_w.makeMove2();
		// 				aTurn = TokenColor.BLACK;
		// 				game_message.setText("Black's turn!");
		// 			}
				
		// 		}
		// 	}
        //     othelloPane.UpdatePane(gameBoard);
		// } else{ // Easy level AI -----------------------------------------------------------------
		// 	if (aTurn == TokenColor.BLACK) {
		// 		move_status_b = false;
		// 		move_status_b = human_b.makeMove(row, column, game_message);
          
		// 		othelloPane.UpdatePane(gameBoard);
		// 	}
			
        // 	if (move_possible == false) {
        // 		checkIfWinner(move_status_w, move_status_b);
        // 	}

		// 	if (stateOfGame == stateOfGame.IN_PROGRESS) {
		// 		if (aTurn == TokenColor.BLACK) {
		// 			if (move_status_b == false && move_possible == false) {
		// 				aTurn = TokenColor.WHITE;
		// 				Boolean move_possible_w = gameBoard.checkForValidMove(aTurn);
		// 				if (move_possible_w == true) {
		// 					game_message.setText("No moves available for Black to make. White's Turn!");
		// 					move_status_w = false;
		// 					move_status_w = computer_w.makeMove3();
		// 				}
		// 				else {
		// 					checkIfWinner(move_status_w, move_status_b);
		// 				}
		// 				if (move_status_w == true) {
		// 					aTurn = TokenColor.BLACK;
		// 					game_message.setText("Black's turn!");
		// 				}
		// 			}
		// 			else if (move_status_b == false && move_possible == true) {
		// 				aTurn = TokenColor.BLACK;
		// 				game_message.setText("Invalid move.  Black try again!");
		// 			}
		// 			else {
		// 				aTurn = TokenColor.WHITE;
		// 				game_message.setText("White's turn!");
		// 				move_status_w = false;
		// 				move_status_w = computer_w.makeMove3();
		// 				aTurn = TokenColor.BLACK;
		// 				game_message.setText("Black's turn!");
		// 			}
		// 		}
		// 	}
        //     othelloPane.UpdatePane(gameBoard);
		// }
	}
}
