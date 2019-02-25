/**
 * GameCell class to create different cell points for the game board
 * @author Team 3
 */
public class GameCell{
    
    /**
     * Creating instance variables
     */
    Color aColor;
    int row;
    int column;

    /**
     * Method to set the colors for each player and blank slots on the game board.
     * Since colors dont display on console, black is seen as B, white is seen as W.
     * Prints appropriate icon for console display.
     */
    public void draw(){
        if(aColor == Color.BLACK){
            System.out.print(" B ");
        }else if(aColor == Color.WHITE){
            System.out.print(" W ");
        }else{
            System.out.print(" _ ");
        }
    }

    /**
     * Method to set cells on the game board to blank color value.
    */
    public void clear(){
        aColor = Color.BLANK;
    }

    /**
     * Sets color value variable from one of either Black, White, or Blank
     * @param value of a type Color
     */
    public void set(Color value){
        aColor = value;
    }

    /**
     * Returns the color within a givin cell
     */
    public Color get(){
        return aColor;
    }

    /**
     * Main Constructor to set values for row and column
     * @param row of type int
     * @param col of type int
     */
    public GameCell(int roww, int col){
        this.row = roww;
        this.column = col;
        clear();
    }
}