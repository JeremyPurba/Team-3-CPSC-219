/**
 * Class to create cells of game board for game tokens to be placed
 * @author Team 3
 */
public class GameCell{
    TokenColor aColor;
    int row;
    int column;

    /**
     * method to place player game peices on board
     */
    public void draw(){
        if(aColor == TokenColor.BLACK){
            System.out.print(" B ");
        }else if(aColor == TokenColor.WHITE){
            System.out.print(" W ");
        }else{
            System.out.print(" - ");
        }
    }

    /**
     * method to clear gameCell of color and set to default blank value
     */
    public void clear(){
        aColor = TokenColor.BLANK;
    }

    /**
     * Sets color to given token color
     * @param value of type TokenColor
     */
    public void set(TokenColor value){
        aColor = value;
    }

    /**
     * Method to get the color in a specific cell
     * @return aColor of type TokenColor
     */
    public TokenColor get(){
      return aColor;
    }

    /**
     * Constructor to clear given cell's color based on coordinates
     * @param roww of type int
     * @param col of type int
     */
    public GameCell(int roww, int col){
        this.row = roww;
        this.column = col;
        clear();
    }
}
