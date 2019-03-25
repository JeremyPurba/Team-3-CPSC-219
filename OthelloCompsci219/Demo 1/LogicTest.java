
import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;

public class LogicTest{



/*
* This unit test only tests for logic within the flip method.
*/

  @Test
  public void test_flipInNorthDirection(){
    private OthelloConsoleGame ocg = new OthelloConsoleGame();
    HumanPlayer w = new HumanPlayer();
    HumanPlayer b = new HumanPlayer();
    w.makeMove();
    b.makeMove();
    ocg.placeGamePiece(4,4);
    ocg.placeGamePiece(3,3);
    ocg.placeGamePiece(3,4);
    ocg.placeGamePiece(4,3);
    assertEquals("Flips in north direction", 4, )
  }
}
