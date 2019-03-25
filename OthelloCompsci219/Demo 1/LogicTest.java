
import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;

public class LogicTest{

  private OthelloConsoleGame ocg = new OthelloConsoleGame();
  private GameBoard gameBoard = new GameBoard(8,8);
  private HumanPlayer w = new HumanPlayer(gameBoard, Color.WHITE);
  private HumanPlayer b = new HumanPlayer(gameBoard, Color.BLACK);


/*
* This unit test only tests for logic within the flip method.
* 8 tests for the 8 possible directions. 
*/

public boolean makeMove(int aRow, int aColumn, Color aPlayerColor) {
  boolean status = false;

    if (gameBoard.checkForValidMove(aPlayerColor) == true)
    {
  while (status == false) {
    //row = GetAndValidateInputFromUser("Row: ");
    //column = GetAndValidateInputFromUser("Column: ");
    if (gameBoard.checkAdjacentCells(aRow, aColumn, aPlayerColor) == true && gameBoard.flip(aRow, aColumn, aPlayerColor, false) != 0){
      status = true;
    }
    else{
      System.out.println("Invalid move. Try again.");
      status = false;
    }
    }
    }
  return status;
}

  @Test
  public void test_flipInEastDirection(){
    gameBoard.setStartingPositions();
    makeMove(3,2,Color.WHITE);
    assertEquals("Flips in east direction", gameBoard.getCellColor(3,2), Color.WHITE);
    assertEquals("Flips in east direction", gameBoard.getCellColor(3,3), Color.WHITE);
    assertEquals("Flips in east direction", gameBoard.getCellColor(3,4), Color.WHITE);
    assertEquals("Flips in east direction", gameBoard.getCellColor(4,3), Color.WHITE);
    assertEquals("Flips in east direction", gameBoard.getCellColor(4,4), Color.BLACK);
  }

  @Test
  public void test_flipInWestDirection(){
    gameBoard.setStartingPositions();
    makeMove(3,5,Color.BLACK);
    assertEquals("Flips in west direction", gameBoard.getCellColor(3,4), Color.BLACK);
    assertEquals("Flips in west direction", gameBoard.getCellColor(3,3), Color.BLACK);
    assertEquals("Flips in west direction", gameBoard.getCellColor(3,5), Color.BLACK);
  }

  @Test
  public void test_flipInSouthDirection(){
    gameBoard.setStartingPositions();
    makeMove(2,4,Color.BLACK);
    assertEquals("Flips in south direction", gameBoard.getCellColor(3,4), Color.BLACK);
  }

  @Test
  public void test_flipInNorthDirection(){
    gameBoard.setStartingPositions();
    makeMove(5,3,Color.BLACK);
    assertEquals("Flips in north direction", gameBoard.getCellColor(4,3), Color.BLACK);
  }

  @Test
  public void test_flipInSouthWestDirection(){
    gameBoard.setStartingPositions();
    makeMove(5,3,Color.BLACK);
    makeMove(5,4, Color.WHITE);
    makeMove(3,5, Color.BLACK);
    assertEquals("Flips in southwest direction", gameBoard.getCellColor(4,3), Color.BLACK);
    assertEquals("Flips in southwest direction", gameBoard.getCellColor(4,4), Color.BLACK);
  }

  @Test
  public void test_flipInSouthEastDirection(){
    gameBoard.setStartingPositions();
    makeMove(5,4,Color.WHITE);
    makeMove(2,2, Color.WHITE);
    assertEquals("Flips in southeast direction", gameBoard.getCellColor(4,4), Color.WHITE);
    assertEquals("Flips in southeast direction", gameBoard.getCellColor(3,3), Color.WHITE);
  }

  @Test
  public void test_flipInNorthWestDirection(){
    gameBoard.setStartingPositions();
    makeMove(3,2,Color.WHITE);
    makeMove(2,2, Color.BLACK);
    assertEquals("Flips in northwest direction", gameBoard.getCellColor(3,3), Color.BLACK);
  }

  @Test
  public void test_flipInNorthEastDirection(){
    gameBoard.setStartingPositions();
    makeMove(2,4,Color.BLACK);
    makeMove(5,2, Color.BLACK);
    assertEquals("Flips in northeast direction", gameBoard.getCellColor(3,4), Color.BLACK);
    assertEquals("Flips in northeast direction", gameBoard.getCellColor(4,3), Color.BLACK);
  }


}
