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
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.event.Event;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.MouseButton;
import javafx.fxml.FXML;

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

    private Stage window;
    private Scene startScene, gameScene, twoScene;
    private OthelloPane othelloPane;
    private int NumOfHumanPlayers = 0;
    private int row = 0;
    private int column = 0;

    @Override
    public void start(Stage primaryStage) {
	Boolean move_status_w = false;
	Boolean move_status_b = false;
	HumanPlayer human_w = null;
	HumanPlayer human_b = null;
	ComputerPlayer computer_b = null;


        window = primaryStage;
        FlowPane root1 = new FlowPane(Orientation.VERTICAL);
        gameTitle = TitleLabel(gameTitle, 80, true);
   
	//Create game board
	gameBoard = new GameBoard(number_of_rows, number_of_columns);
     
        // startScene ------------------------------------------------------------------------------------------------
        Label playLabel = new Label("Select a Game Mode: ");
        playLabel.setFont(new Font(50)); playLabel.setAlignment(Pos.CENTER);

        Button onePlayerButton = new Button("One Player");
        
        Button twoPlayerButton = new Button("Two Players");
        twoPlayerButton.setTranslateX(400);twoPlayerButton.setTranslateY(-24);

        root1.setAlignment(Pos.CENTER);
        root1.getChildren().addAll(gameTitle, playLabel, onePlayerButton, twoPlayerButton);

        startScene = new Scene(root1, 700, 700);

	        onePlayerButton.setOnAction(new EventHandler<ActionEvent>() {
	        	@Override
	        	public void handle(ActionEvent event) {
	        		NumOfHumanPlayers = 1;
				window.setScene(gameScene);
	        	}
	        	});
	        
	        twoPlayerButton.setOnAction(new EventHandler<ActionEvent>() {
	        	@Override
	        	public void handle(ActionEvent event) {
	        		NumOfHumanPlayers = 2;
				window.setScene(gameScene);
	        	}
	        	});

        //----------------------------------------------------------------------------------------------------------
        //Game Board Window
        FlowPane root2 = new FlowPane(Orientation.VERTICAL);
        root2.setAlignment(Pos.CENTER);
        gamETitle = TitleLabel(gamETitle, 80, true);

        othelloPane = new OthelloPane(BOARD_SIZE, BOX_SIZE, FLIP_DURATION);
        othelloPane.setAlignment(Pos.CENTER);

        root2.getChildren().addAll(gamETitle, othelloPane);
        gameScene = new Scene(root2, 700, 700);
        //----------------------------------------------------------------------------------------------------------

        window.setTitle(GAME_NAME);
        window.setScene(startScene);
        window.show();

/*	Insert our code to play the game */

	if (NumOfHumanPlayers == 1){
	   human_w = new HumanPlayer(gameBoard, TokenColor.WHITE);
	   computer_b = new ComputerPlayer(gameBoard, TokenColor.BLACK);    		
        }
        else {
	   human_w = new HumanPlayer(gameBoard, TokenColor.WHITE);
	   human_b = new HumanPlayer(gameBoard, TokenColor.BLACK);
        }

	gameBoard.setStartingPositions();
	stateOfGame = StateOfGame.IN_PROGRESS;
        aTurn = TokenColor.BLACK;                  //Black starts first always
        othelloPane.UpdatePane(gameBoard);

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
	private String blankBackgroundInHex = "#C47F6C";
	private String whiteBackgroundInHex = "#F8F8FF";
	private String blackBackgroundInHex = "#000000";

        private int boardSize;
        private int boxSize;
        private Duration flipDuration;
        private int[][] directions = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
    
        private Pane[][] boxes;
    
        public OthelloPane(int boardSize, int boxSize, double flipDuration) {
            super();

            boxes = new Pane[boardSize][boardSize];
    
            this.boardSize = boardSize;
            this.boxSize = boxSize;
            this.flipDuration = Duration.millis(flipDuration);
    
            // setup grid constaints
            for (int i = 0; i < boardSize; i++)
                getRowConstraints().add( new RowConstraints(boxSize));
            for (int i = 0; i < boardSize; i++)
                getColumnConstraints().add( new ColumnConstraints(boxSize));
    
            for (int i = 0; i < boardSize; i++) {
                for (int j = 0; j < boardSize; j++) {
                    Pane box = new Pane();
                    box.setStyle("-fx-background-color: " + blankBackgroundInHex + ";");
                    setBox(i, j, box);
                    add(box, j, i);
                }
            }

    
            setGridLinesVisible(true);
            setAlignment(Pos.CENTER);
        }

        private void setBox(int row, int column, Pane box) {
            boxes[row][column] = box;
        }


	public void UpdatePane(GameBoard aGameBoard)
	{
	  TokenColor cellColor;
	  for (int i = 0; i < boardSize; i++) {
	     for (int j = 0; j < boardSize; j++) {
               cellColor = gameBoard.getCellColor(i,j);
               if (cellColor == TokenColor.BLANK) {
                 boxes[i][j].setStyle("-fx-background-color: " + blankBackgroundInHex + ";");
               }
               else if (cellColor == TokenColor.WHITE) {
                 boxes[i][j].setStyle("-fx-background-color: " + whiteBackgroundInHex + ";");
               }
               else {
                 boxes[i][j].setStyle("-fx-background-color: " + blackBackgroundInHex + ";");               
               }
             }
	    }	        
	 }
    }
}
