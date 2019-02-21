# Team-3-CPSC-219

import java.util.ArrayList;
import java.util.Scanner;


public class HumanPlayer extends Player{

  private Move getMove(GameConfiguration aGameConfiguration){
    Move m = null;
    boolean from_coordinates = false;
    boolean to_coordinates = false;

    while (from_coordinates = false){
      Scanner keyboard = new Scanner(System.in);
      System.out.print("What is the x coordinate of the peg you would like to move?");
      int from_x_coordinate = keyboard.nextInt();
      System.out.print("What is the y coordinate of the peg you would like to move?");
      int from_y_coordinate = keyboard.nextInt();
      if (GameBoard.validCoords(from_x_coordinate, from_y_coordinate) = true && GameBoard.get(from_x_coordinate,from_y_coordinate) == Colour.aColour){
        from_coordinates = true;
      }
    }

    while (to_coordinates = false){
      Scanner keyboard = new Scanner(System.in);
      System.out.print("Which x coordinte do you  wish to move to?");
      int to_x_coordinate = keyboard.nextInt();
      System.out.print("Which y coordinate do you widh to move to?");
      int to_y_coordinate = keyboard.nextInt();
      if (GameBoard.validCoords(to_x_coordinate, to_y_coordinate) == true && GameBoard.get(to_x_coordinate,to_y_coordinate)== Colour.BLANK){
        to_coordinates = true;
      }
    }

    if (to_coordinates = true && from_coordinates = true){
       m = new Move(from_x_coordinate,from_y_coordinate,to_x_coordinate,to_y_coordinate,colour);
    }

    return m;
  }

  public boolean makeMove(GameConfiguration aGameConfiguration){
    boolean status = false;

    while (status = false){
    m = getMove(aGameConfiguration);
    if (m != null){
      status = super.makeMove(aGameConfiguration,m);
    }
    }
  }
}
