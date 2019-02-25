/**
 * GameBoard class to create the visual GameBoard for the game
 * @author Team 3
 */
public class GameBoard{

    /**
     * Create instance variables
    */
    GameCell[][] cell;
    int ROW;
    int COLUMN;
    int numBlack;
    int numWhite;
    
    /**
     * Method to count the total number of black pieces on the board
     * @return blackCount of type int
     */
    public int countBlack(){
        int blackCount = 0;
        for(int r = 0; r < ROW; r++){
            for(int c = 0; c < COLUMN; c++){
                if(cell[r][c].aColor == Color.BLACK){
                    blackCount++;
                }
            }
        }
        return blackCount;
    }

    /**
     * Method to count the total number of white pieces on the board
     * @return whiteCount of type int
     */
    public int countWhite(){
        int whiteCount = 0;
        for(int r = 0; r < ROW; r++){
            for(int c = 0; c < COLUMN; c++){
                if(cell[r][c].aColor == Color.WHITE){
                    whiteCount++;
                }
            }
        }
        return whiteCount;
    }

    /**
     * Draw method to print the gameboard onto the console.
     */
    public void draw(){
        System.out.print("    ");
        for(int c = 0; c < COLUMN; c++){
            System.out.print(c+1 + (c > 8 ? " " : "  "));
        }
        System.out.println();

        for(int r = 0; r < ROW; r++){
            System.out.print(r+1 + (r > 8 ? " " : "  "));
            for(int c = 0; c < COLUMN; c++){
                cell[r][c].draw();
            }
            System.out.println();
        }
        
        numBlack = countBlack();
        numWhite = countWhite();
        System.out.format("%nBlack: %d%nWhite: %d%n%n", numBlack, numWhite);
    }

    /**
     * Constructor to set the number of rows and columns as well as how many cells for the gameboard
     * @param row of type int
     * @param column of type int
     */
    public GameBoard(int row, int column){
        this.ROW = row;
        this.COLUMN = column;
        cell = new GameCell[ROW][COLUMN];
        for(int r = 0; r < ROW; r++){
            for(int c = 0; c < COLUMN; c++){
                cell[r][c] = new GameCell(row, column);
            }
        }
    }

    /*
    * Result of method: This sets the starting positions on the board. Within the
    * middle four-square of the 8X8 game board, the top left corner of the four-square
    * will be black, and to its right  will be white. Underneath the balck piece
    * will another white piece and to the left of the white piece will be another black piece.
    */
    public void setStartingPositions(){
        char topLeftCorner = 'B';
            this.cell[3][3].set(Color.BLACK);
            this.cell[4][3].set(Color.WHITE);
            this.cell[4][4].set(Color.BLACK);
            this.cell[3][4].set(Color.WHITE);
    }

    /*
    * Result of method: The method is called to check if the row or column value entered by a player
    * is within the bounds of the game board
    */
    public boolean checkWithinBoard(int aRowColumn){
        boolean withinBoard = true;
        if (aRowColumn+1 < 1 || aRowColumn+1 > ROW) {
            System.out.println("Invalid input. Your input must be between 1 and " + ROW);
            withinBoard = false;
        }
  
        return withinBoard;
    }

    /**
     * Method to check adjacent cell to see if they contain a White, Black, or blank
     * @return status of type boolean
     */
    public boolean checkAdjacentCells(int aRow, int aColumn, Color aColor){
        boolean status = false;
        Color oppositeColor = Color.WHITE;
        int adj_cell_row = 0;
        int adj_cell_col = 0;
  
        if (aColor == Color.WHITE){
            oppositeColor = Color.BLACK;
        }
  
        if (this.cell[aRow][aColumn].get() == Color.BLANK) {
  
            //Check cell on the north cell
            adj_cell_row = aRow - 1;
            adj_cell_col = aColumn;
            if (adj_cell_row >= 0){
                if (cell[adj_cell_row][adj_cell_col].get() == oppositeColor) {
                    status = true;
                }
            }
            //Check cell on the northeast cell
            adj_cell_row = aRow - 1;
            adj_cell_col = aColumn + 1;
            if (adj_cell_row >= 0 && adj_cell_col < COLUMN){
                if (cell[adj_cell_row][adj_cell_col].get() == oppositeColor) {
                    status = true;
                }
            }
            //Check cell on the east cell
            adj_cell_row = aRow;
            adj_cell_col = aColumn + 1;
            if (adj_cell_col < COLUMN){
                if (cell[adj_cell_row][adj_cell_col].get() == oppositeColor) {
                    status = true;
                }
            }
            //Check cell on the southeast cell
            adj_cell_row = aRow + 1;
            adj_cell_col = aColumn + 1;
            if (adj_cell_row < ROW && adj_cell_col < COLUMN){
                if (cell[adj_cell_row][adj_cell_col].get() == oppositeColor) {
                    status = true;
                }
            }
            //Check cell on the south cell
            adj_cell_row = aRow + 1;
            adj_cell_col = aColumn;
            if (adj_cell_row < ROW){
                if (cell[adj_cell_row][adj_cell_col].get() == oppositeColor) {
                    status = true;
                }
            }
            //Check cell on the southwest cell
            adj_cell_row = aRow + 1;
            adj_cell_col = aColumn - 1;
            if (adj_cell_row < ROW && adj_cell_col >= 0){
                if (cell[adj_cell_row][adj_cell_col].get() == oppositeColor) {
                    status = true;
                }
            }
            //Check cell on the west cell
            adj_cell_row = aRow;
            adj_cell_col = aColumn - 1;
            if (adj_cell_col >= 0){
                if (cell[adj_cell_row][adj_cell_col].get() == oppositeColor) {
                    status = true;
                }
            }
            //Check cell on the northwest cell
            adj_cell_row = aRow - 1;
            adj_cell_col = aColumn - 1;
            if (adj_cell_row >= 0 && adj_cell_col >= 0){
                if (cell[adj_cell_row][adj_cell_col].get() == oppositeColor) {
                    status = true;
                }
            }
        }
        return status;
    }

    /**
     * Method to return color stored within a specific game cell
     * @param aRow of type int
     * @param aColumn of type int
     * @return color of type color
     */
    public Color getCellColor(int aRow, int aColumn){
        Color color = this.cell[aRow][aColumn].get();
        return color;
    }

    /*
     * Result of method: Since when placing a game piece down, it can affect its opposite color
     * game pieces both diagonally, vertically, and horizontally, you must check to see if the game piece
     * the user places, affects the opposite game piece in any of these directions. If it does,
     * this method will also flip the pieces into the appropriate color until it meets with the aTurn(instance variable)'s color
     * while travelling along that specific direction.
     * @ param row - a variable associated with the row number entered by the user, of which row
     * they wish to place their game piece.
     *@ param column - a variable associated with the column number entered by the user, of which column
     * they wish to place their game piece.
     @ param noFlip - a variable associated with the whether a flip has not been made.
     * @ return flip_done - a varibale associated with whether flip(s) have been made in various directions.
     */
    public int flip(int row, int column, Color aTurn) {
        int flip_count = 0;
        int flips_n = 0;
        int flips_ne = 0;
        int flips_e = 0;
        int flips_se = 0;
        int flips_s = 0;
        int flips_sw = 0;
        int flips_w = 0;
        int flips_nw = 0;
        int currentRow = 0;
        int currentColumn = 0;
        int flipRow = 0;
        int flipColumn = 0;
        boolean found = false;
        Color opposite_color = Color.BLACK;
  
        if (aTurn == Color.BLACK){
            opposite_color = Color.WHITE;
        }
  
        /* Check to see if flipping in the north direction is possible.
         * Row must be greater than the smallest row (1) in order for a flip to happen in
         * the north direction since it has to be on the 8X8 board. The row above in the same
         * column of where the game piece is placed must also have the opposite colour of the game piece being placed in order
         * for a flip in this direction to actually happen.
         * This method will flip each game piece in this direction until it meets with a piece
         * of the same colour.
         */
        currentRow = row;
        found = false;
        while (currentRow > 0) {
            currentRow = currentRow - 1;
            if (this.cell[currentRow][column].get() == opposite_color){
                flips_n++;
            }
            else if (this.cell[currentRow][column].get() == aTurn){
                found = true;
                currentRow = 0;
            }
        }
  
        if (found == false) {
            flips_n = 0;
        }
  
        /* Check to see if flipping in the northeast direction is possible.
         * Row must be greater than the smallest row (1) in order for a flip to happen in
         * the northeast direction since it has to be on the 8X8 board. Column must also be
         * smaller than the overall number of columns (8) in order for a flip to happen
         * in the northeast direction since it also has to be on the 8X8 board.
         * The row above in the same column to the right of where the game piece is placed must also have the
         * opposite colour of the game piece being placed in order
         * for a flip in this direction to actually happen.
         * This method will flip each game piece in this direction until it meets with a piece
         * of the same colour.
         */
        currentRow = row;
        currentColumn = column;
        found = false;
        while (currentRow > 0 && currentColumn < COLUMN - 1) {
            currentRow = currentRow - 1;
            currentColumn = currentColumn + 1;
            if (this.cell[currentRow][currentColumn].get() == opposite_color){
                flips_ne++;
            }
            else if (this.cell[currentRow][currentColumn].get() == aTurn){
                found = true;
                currentRow = 0;
            }
        }
  
        if (found == false) {
            flips_ne = 0;
        }
  
        /* Check to see if flipping in the east direction is possible.
         * Column must be smaller than the overall number of columns (8) minus 2 in order for a flip to happen
         * in the east direction since it has to be on the 8X8 board.
         * The column to the right in the same row of where the game piece is placed must also have the
         * opposite colour of the game piece being placed in order
         * for a flip in this direction to actually happen.
         * This method will flip each game piece in this direction until it meets with a piece
         * of the same colour.
         */
        currentColumn = column;
        found = false;
        while (currentColumn < COLUMN - 1) {
            currentColumn = currentColumn + 1;
            if (this.cell[row][currentColumn].get() == opposite_color){
                flips_e++;
            }
            else if (this.cell[row][currentColumn].get() == aTurn){
                found = true;
                currentColumn = COLUMN;
            }
        }
  
        if (found == false) {
            flips_e = 0;
        }
  
        /* Check to see if flipping in the southeast direction is possible.
         * Column must be smaller than the overall number of columns (8) minus 2 in order for a flip to happen
         * in the southeast direction since it has to be on the 8X8 board. Row must also
         * be smaller than the overall number of rows (8) minus 2 in order for a flip to happen
         * in the southeast direction since it also has to be on the 8X8 board.
         * The column to the right in the row below of where the game piece is placed must also have the
         * opposite colour of the game piece being placed in order
         * for a flip in this direction to actually happen.
         * This method will flip each game piece in this direction until it meets with a piece
         * of the same colour.
         */
        currentRow = row;
        currentColumn = column;
        found = false;
        while (currentRow < ROW - 1 && currentColumn < COLUMN - 1) {
            currentColumn = currentColumn + 1;
            currentRow = currentRow + 1;
            if (this.cell[currentRow][currentColumn].get() == opposite_color){
                flips_se++;
            }
            else if (this.cell[currentRow][currentColumn].get() == aTurn){
                found = true;
                currentRow = ROW;
            }
        }
  
        if (found == false) {
            flips_se = 0;
        }
        /* Check to see if flipping in the south direction is possible.
         * Row must be smaller than the overall number of rows (8) minus 2 in order for a flip to happen
         * in the south direction since it has to be on the 8X8 board.
         * The row below in the same column of where the game piece is placed must also have the
         * opposite colour of the game piece being placed in order
         * for a flip in this direction to actually happen.
         * This method will flip each game piece in this direction until it meets with a piece
         * of the same colour.
         */
        currentRow = row;
        found = false;
        while (currentRow < ROW - 1) {
            currentRow = currentRow + 1;
            if (this.cell[currentRow][column].get() == opposite_color){
                flips_s++;
            }
            else if (this.cell[currentRow][column].get() == aTurn){
                found = true;
                currentRow = ROW;
            }
        }
  
        if (found == false) {
            flips_s = 0;
        }
  
        /* Check to see if flipping in the southwest direction is possible.
         * Row must be smaller than the overall number of rows (8) minus 2 in order for a flip to happen
         * in the southwest direction since it has to be on the 8X8 board.
         * Column must be greater than the smallest column (1) in order for a flip to happen in
         * the southwest direction since it also has to be on the 8X8 board.
         * The row below in the column to the left of where the game piece is placed must also have the
         * opposite colour of the game piece being placed in order
         * for a flip in this direction to actually happen.
         * This method will flip each game piece in this direction until it meets with a piece
         * of the same colour.
         */
        currentRow = row;
        currentColumn = column;
        found = false;
        while (currentRow < ROW -1 && currentColumn > 0) {
            currentRow = currentRow + 1;
            currentColumn = currentColumn - 1;
            if (this.cell[currentRow][currentColumn].get() == opposite_color){
                flips_sw++;
            }
            else if (this.cell[currentRow][currentColumn].get() == aTurn){
                found = true;
                currentRow = ROW;
            }
        }
  
        if (found == false) {
            flips_sw = 0;
        }
  
        /* Check to see if flipping in the west direction is possible.
         * Column must be greater than the smallest column (1) in order for a flip to happen in
         * the west direction since it has to be on the 8X8 board.
         * The column to the left in the same row of where the game piece is placed must also have the
         * opposite colour of the game piece being placed in order
         * for a flip in this direction to actually happen.
         * This method will flip each game piece in this direction until it meets with a piece
         * of the same colour.
         */
        currentColumn = column;
        found = false;
        while (currentColumn > 0) {
            currentColumn = currentColumn - 1;
            if (this.cell[row][currentColumn].get() == opposite_color){
                flips_w++;
            }
            else if (this.cell[row][currentColumn].get() == aTurn){
                found = true;
                currentColumn = 0;
            }
        }
  
        if (found == false) {
            flips_w = 0;
        }
        /* Check to see if flipping in the northwest direction is possible.
         * Column must be greater than the smallest column (1) in order for a flip to happen in
         * the northwest direction since it has to be on the 8X8 board.
         * Row must also be greater than the smallest row (1) in order for a flip to happen in
         * the northwest direction since it also has to be on the 8X8 board.
         * The column to the left in the row above of where the game piece is placed must also have the
         * opposite colour of the game piece being placed in order
         * for a flip in this direction to actually happen.
         * This method will flip each game piece in this direction until it meets with a piece
         * of the same colour.
         */
        currentRow = row;
        currentColumn = column;
        found = false;
        while (currentRow > 0 && currentColumn > 0) {
            currentColumn = currentColumn - 1;
            currentRow = currentRow - 1;
            if (this.cell[currentRow][currentColumn].get() == opposite_color){
                flips_nw++;
            }
            else if (this.cell[currentRow][currentColumn].get() == aTurn){
                found = true;
                currentRow = ROW;
            }
        }
  
        if (found == false) {
            flips_nw = 0;
        }
  
        /* Code to start flipping is below */
        /* Northern flips */
        flipRow = row;
        while (flips_n > 0){
            flipRow = row - 1;
            this.cell[flipRow][column].set(aTurn);
            flips_n--;
            flip_count++;
        }
  
        /* Northeast flips */
        flipRow = row;
        flipColumn = column;
        while (flips_ne > 0){
            flipRow = row - 1;
            flipColumn = column + 1;
            this.cell[flipRow][flipColumn].set(aTurn);
            flips_ne--;
            flip_count++;
        }
  
        /* Eastern flips */
        flipColumn = column;
        while (flips_e > 0){
            flipColumn = column + 1;
            this.cell[row][flipColumn].set(aTurn);
            flips_e--;
            flip_count++;
        }
  
        /* Southeast flips */
        flipColumn = column;
        flipRow = row;
        while (flips_se > 0){
            flipColumn = column + 1;
            flipRow = row + 1;
            this.cell[flipRow][flipColumn].set(aTurn);
            flips_se--;
            flip_count++;
        }
  
        /* South flips */
        flipRow = row;
        while (flips_s > 0){
            flipRow = row + 1;
            this.cell[flipRow][column].set(aTurn);
            flips_s--;
            flip_count++;
        }
  
        /* Southwestern flips */
        flipRow = row;
        flipColumn = column;
        while (flips_sw > 0){
            flipRow = row + 1;
            flipColumn = column - 1;
            this.cell[flipRow][flipColumn].set(aTurn);
            flips_sw--;
            flip_count++;
        }
  
        /* Western flips */
        flipColumn = column;
        while (flips_w > 0){
            flipColumn = column - 1;
            this.cell[row][flipColumn].set(aTurn);
            flips_w--;
            flip_count++;
        }
  
        /* Northwestern flips */
        flipColumn = column;
        flipRow = row;
        while (flips_nw > 0){
            flipColumn = column - 1;
            flipRow = row - 1;
            this.cell[flipRow][flipColumn].set(aTurn);
            flips_nw--;
            flip_count++;
        }
  
        if (flip_count > 0) {
            this.cell[row][column].set(aTurn);
        }
        return flip_count;
    }

}