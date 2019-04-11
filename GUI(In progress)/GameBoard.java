public class GameBoard {
    /*
    * Initializing instance variables.
    */
    GameCell[][] cell;
    int ROW;
    int COLUMN;
    int numBlack;
    int numWhite;

    /*
    * Result of method: This method counts the number of black pieces on the board at a certain point in time
    * (when this method is called).
    * @return - the integer of how many black pieces are on the board.
    */
    public int countBlack(){
        int blackCount = 0;
        for(int r = 0; r < ROW; r++){
            for(int c = 0; c < COLUMN; c++){
                if(cell[r][c].aColor == TokenColor.BLACK){
                    blackCount++;
                }
            }
        }
        return blackCount;
    }

    /*
    * Result of method: This method counts the number of white pieces on the board at a certain point in time
    * (when this method is called).
    * @return - the integer of how many white pieces are on the board.
    */
    public int countWhite(){
        int whiteCount = 0;
        for(int r = 0; r < ROW; r++){
            for(int c = 0; c < COLUMN; c++){
                if(cell[r][c].aColor == TokenColor.WHITE){
                    whiteCount++;
                }
            }
        }
        return whiteCount;
    }

    /*
    * Note that this method is only called when playing the console game.
    * Result of method:  This method draws the game board on the console.
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
    * Note that this constructor does not take input from the user for the GUI version. In this version, it
    * always takes (8,8).
    * A constructor which takes two parameters
    * Result of method: Determines the dimensions of the board. It sets the input from user as the instance
    * variables of ROW and COLUMN.
    * @param row
    * @param column
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
            this.cell[3][3].set(TokenColor.BLACK);
            this.cell[4][3].set(TokenColor.WHITE);
            this.cell[4][4].set(TokenColor.BLACK);
            this.cell[3][4].set(TokenColor.WHITE);
    }

    /*
    * Result of method: This method clears the cells on the game board by setting the token color to
    * blank (no token).
    */
    public void clearCells(){
      this.ROW = 8;
      this.COLUMN = 8;
      for(int r = 0; r < ROW; r++){
          for(int c = 0; c < COLUMN; c++){
              this.cell[r][c].set(TokenColor.BLANK);
          }
      }
    }

    /*
    * Note that this method is used only when playing the console version. Since in the GUI button, a button
    * must be clicked to play, and the buttons are within the game board only.
    * Result of method: The method is called to check if the row or column value entered by a player
    * is within the bounds of the game board.
    */
    public boolean checkWithinBoard(int aRowColumn){
      boolean withinBoard = true;
      if (aRowColumn+1 < 1 || aRowColumn+1 > ROW) {
         System.out.println("Invalid input. Your input must be between 1 and " + ROW);
         withinBoard = false;
      }

      return withinBoard;
    }

    /*
    * Result of method: This method checks the cells adjacent to the token you or the computer placed in all 8 directions.
    * If the adjacent cell is the opposite color of the piece being placed, a move is utimately valid and the status changes
    * to true.
    * @param aRow - row at which the user clicked the button.
    * @param aColumn - column at which the user clicked the button.
    * @return - status which is true if an adajacent cell in any which direction is of the opposite color.
    */
    public boolean checkAdjacentCells(int aRow, int aColumn, TokenColor aColor){
      boolean status = false;
      TokenColor oppositeColor = TokenColor.WHITE;
      int adj_cell_row = 0;
      int adj_cell_col = 0;

      if (aColor == TokenColor.WHITE){
        oppositeColor = TokenColor.BLACK;
      }

      if (this.cell[aRow][aColumn].get() == TokenColor.BLANK) {

      //Check adjacent cell in the north direction
        adj_cell_row = aRow - 1;
        adj_cell_col = aColumn;
        if (adj_cell_row >= 0){
           if (cell[adj_cell_row][adj_cell_col].get() == oppositeColor) {
              status = true;
           }
        }
        //Check adjacent cell in the northeast direction
        adj_cell_row = aRow - 1;
        adj_cell_col = aColumn + 1;
        if (adj_cell_row >= 0 && adj_cell_col < COLUMN){
          if (cell[adj_cell_row][adj_cell_col].get() == oppositeColor) {
              status = true;
          }
        }
        //Check adjacent cell in the east direction
        adj_cell_row = aRow;
        adj_cell_col = aColumn + 1;
          if (adj_cell_col < COLUMN){
            if (cell[adj_cell_row][adj_cell_col].get() == oppositeColor) {
                status = true;
            }
          }
          //Check adjacent cell in the southeast direction
          adj_cell_row = aRow + 1;
          adj_cell_col = aColumn + 1;
            if (adj_cell_row < ROW && adj_cell_col < COLUMN){
              if (cell[adj_cell_row][adj_cell_col].get() == oppositeColor) {
                  status = true;
              }
            }
            //Check adjacent cell in the south direction
            adj_cell_row = aRow + 1;
            adj_cell_col = aColumn;
            if (adj_cell_row < ROW){
              if (cell[adj_cell_row][adj_cell_col].get() == oppositeColor) {
                status = true;
              }
            }
            //Check adjacent cell in the southwest direction
            adj_cell_row = aRow + 1;
            adj_cell_col = aColumn - 1;
              if (adj_cell_row < ROW && adj_cell_col >= 0){
                  if (cell[adj_cell_row][adj_cell_col].get() == oppositeColor) {
                      status = true;
                  }
              }
            //Check adjacent cell in the west direction
            adj_cell_row = aRow;
            adj_cell_col = aColumn - 1;
              if (adj_cell_col >= 0){
                  if (cell[adj_cell_row][adj_cell_col].get() == oppositeColor) {
                      status = true;
                  }
              }
            //Check adjacent cell in the northwest direction
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

    /*
    * Result of method: This method check which token color is in a certain cell.
    * @param aRow - row of cell which needs to be checked.
    * @param aColumn - column of cell which needs to be checked.
    * @return - color of the token in the cell.
    */
    public TokenColor getCellColor(int aRow, int aColumn){
      TokenColor color = this.cell[aRow][aColumn].get();
      return color;
    }

    /*
    * Result of method: Since when placing a game piece down, it can affect its opposite color
    * game pieces both diagonally, vertically, and horizontally, you must check to see if the game piece
    * the user places, affects the opposite game piece in any of these directions. If it does,
    * this method will also flip the pieces into the appropriate color until it meets with the aTurn(instance variable)'s color
    * while travelling along that specific direction.
    * @param row - a variable associated with the row number entered by the user, of which row
    * they wish to place their game piece.
    * @param column - a variable associated with the column number entered by the user, of which column
    * they wish to place their game piece.
    * @param aTurn - a variable associated with the current player's token color.
    * @return aTest - a varibale associated with whether flip(s) are able to happen in various directions.
    */
    public int flip(int row, int column, TokenColor aTurn, boolean aTest) {
      // Instantiating the flip number in each direction to zero, and adding on to it throughout the method.
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
      TokenColor opposite_color = TokenColor.BLACK;

      if (aTurn == TokenColor.BLACK){
        opposite_color = TokenColor.WHITE;
      }

        /* Check to see if flipping in the north direction is possible.
        * Row must be greater than the smallest row (0) in order for a flip to happen in
        * the north direction since it has to be on the 8X8 board. The row above in the same
        * column of where the game piece is placed must also have the opposite colour of the game piece being placed in order
        * for a flip in this direction to actually happen.
        * This method will flip each game piece in this direction until it meets with a piece
        * of the same colour.
        */
        currentRow = row;
        found = false;
        while (currentRow > 0 && found == false) {
          currentRow = currentRow - 1;
          if (this.cell[currentRow][column].get() == opposite_color){
            flips_n++;
          }
          else if (this.cell[currentRow][column].get() == aTurn)
          {
            found = true;
          }
          else
          {
        	flips_n = 0;
            found = true;
          }
         }

         if (found == false) {
           flips_n = 0;
         }

       /* Check to see if flipping in the northeast direction is possible.
       * Row must be greater than the smallest row (0) in order for a flip to happen in
       * the northeast direction since it has to be on the 8X8 board. Column must also be
       * smaller than the overall number of columns (7) in order for a flip to happen
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
       while (currentRow > 0 && currentColumn < COLUMN - 1 && found == false) {
         currentRow = currentRow - 1;
         currentColumn = currentColumn + 1;
         if (this.cell[currentRow][currentColumn].get() == opposite_color){
           flips_ne++;
         }
         else if (this.cell[currentRow][currentColumn].get() == aTurn)
         {
           found = true;
         }
         else
         {
           flips_ne = 0;
           found = true;
         }
        }

        if (found == false) {
          flips_ne = 0;
        }

        /* Check to see if flipping in the east direction is possible.
        * Column must be smaller than the overall number of columns (7) minus 1 in order for a flip to happen
        * in the east direction since it has to be on the 8X8 board.
        * The column to the right in the same row of where the game piece is placed must also have the
        * opposite colour of the game piece being placed in order
        * for a flip in this direction to actually happen.
        * This method will flip each game piece in this direction until it meets with a piece
        * of the same colour.
        */
        currentColumn = column;
        found = false;
        while (currentColumn < COLUMN - 1 && found == false) {
          currentColumn = currentColumn + 1;
          if (this.cell[row][currentColumn].get() == opposite_color){
            flips_e++;
          }
          else if (this.cell[row][currentColumn].get() == aTurn)
          {
            found = true;
          }
          else
          {
        	flips_e = 0;
            found = true;
          }
         }

         if (found == false) {
           flips_e = 0;
         }

         /* Check to see if flipping in the southeast direction is possible.
         * Column must be smaller than the overall number of columns (7) minus 1 in order for a flip to happen
         * in the southeast direction since it has to be on the 8X8 board. Row must also
         * be smaller than the overall number of rows (7) minus 1 in order for a flip to happen
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
         while (currentRow < ROW - 1 && currentColumn < COLUMN - 1 && found == false) {
           currentColumn = currentColumn + 1;
           currentRow = currentRow + 1;
           if (this.cell[currentRow][currentColumn].get() == opposite_color){
             flips_se++;
           }
           else if (this.cell[currentRow][currentColumn].get() == aTurn)
           {
             found = true;
           }
           else
           {
         	 flips_se = 0;
             found = true;
           }
          }

          if (found == false) {
            flips_se = 0;
          }
          /* Check to see if flipping in the south direction is possible.
          * Row must be smaller than the overall number of rows (7) minus 1 in order for a flip to happen
          * in the south direction since it has to be on the 8X8 board.
          * The row below in the same column of where the game piece is placed must also have the
          * opposite colour of the game piece being placed in order
          * for a flip in this direction to actually happen.
          * This method will flip each game piece in this direction until it meets with a piece
          * of the same colour.
          */
          currentRow = row;
          found = false;
          while (currentRow < ROW - 1 && found == false) {
            currentRow = currentRow + 1;
            if (this.cell[currentRow][column].get() == opposite_color){
              flips_s++;
            }
            else if (this.cell[currentRow][column].get() == aTurn)
            {
              found = true;
            }
            else
            {
              flips_s = 0;
              found = true;
            }
           }

           if (found == false) {
             flips_s = 0;
           }

           /* Check to see if flipping in the southwest direction is possible.
           * Row must be smaller than the overall number of rows (7) minus 1 in order for a flip to happen
           * in the southwest direction since it has to be on the 8X8 board.
           * Column must be greater than the smallest column (0) in order for a flip to happen in
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
           while (currentRow < ROW -1 && currentColumn > 0 && found == false) {
             currentRow = currentRow + 1;
             currentColumn = currentColumn - 1;
             if (this.cell[currentRow][currentColumn].get() == opposite_color){
               flips_sw++;
             }
             else if (this.cell[currentRow][currentColumn].get() == aTurn)
             {
               found = true;
             }
             else
             {
               flips_sw = 0;
               found = true;
             }
            }

            if (found == false) {
              flips_sw = 0;
            }

            /* Check to see if flipping in the west direction is possible.
            * Column must be greater than the smallest column (0) in order for a flip to happen in
            * the west direction since it has to be on the 8X8 board.
            * The column to the left in the same row of where the game piece is placed must also have the
            * opposite colour of the game piece being placed in order
            * for a flip in this direction to actually happen.
            * This method will flip each game piece in this direction until it meets with a piece
            * of the same colour.
            */
            currentColumn = column;
            found = false;
            while (currentColumn > 0 && found == false) {
              currentColumn = currentColumn - 1;
              if (this.cell[row][currentColumn].get() == opposite_color){
                flips_w++;
              }
              else if (this.cell[row][currentColumn].get() == aTurn)
              {
                found = true;
              }
              else
              {
            	flips_w = 0;
                found = true;
              }
             }

             if (found == false) {
               flips_w = 0;
             }
             /* Check to see if flipping in the northwest direction is possible.
             * Column must be greater than the smallest column (0) in order for a flip to happen in
             * the northwest direction since it has to be on the 8X8 board.
             * Row must also be greater than the smallest row (0) in order for a flip to happen in
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
             while (currentRow > 0 && currentColumn > 0 && found == false) {
               currentColumn = currentColumn - 1;
               currentRow = currentRow - 1;
               if (this.cell[currentRow][currentColumn].get() == opposite_color){
                 flips_nw++;
               }
               else if (this.cell[currentRow][currentColumn].get() == aTurn)
               {
                 found = true;
               }
               else
               {
             	 flips_nw = 0;
                 found = true;
               }
              }

              if (found == false) {
                flips_nw = 0;
              }

       /* Code to actually start flipping is below */
       /* Northern flips */
          flipRow = row;
          while (flips_n > 0){
            flipRow = flipRow - 1;
            if (aTest == false) {
              this.cell[flipRow][column].set(aTurn);
            }
            flips_n--;
            flip_count++;
          }

      /* Northeast flips */
         flipRow = row;
         flipColumn = column;
         while (flips_ne > 0){
           flipRow = flipRow - 1;
           flipColumn = flipColumn + 1;
           if (aTest == false) {
        	   this.cell[flipRow][flipColumn].set(aTurn);
           }
           flips_ne--;
           flip_count++;
         }

      /* Eastern flips */
         flipColumn = column;
         while (flips_e > 0){
           flipColumn = flipColumn + 1;
           if (aTest == false) {
        	   this.cell[row][flipColumn].set(aTurn);
           }
           flips_e--;
           flip_count++;
         }

      /* Southeast flips */
         flipColumn = column;
         flipRow = row;
         while (flips_se > 0){
             flipRow = flipRow + 1;
        	 flipColumn = flipColumn + 1;
             if (aTest == false) {
            	 this.cell[flipRow][flipColumn].set(aTurn);
             }
           flips_se--;
           flip_count++;
         }

       /* South flips */
         flipRow = row;
         while (flips_s > 0){
           flipRow = flipRow + 1;
           if (aTest == false) {
        	   this.cell[flipRow][column].set(aTurn);
           }
           flips_s--;
           flip_count++;
         }

       /* Southwestern flips */
          flipRow = row;
          flipColumn = column;
          while (flips_sw > 0){
            flipRow = flipRow + 1;
            flipColumn = flipColumn - 1;
            if (aTest == false) {
            	this.cell[flipRow][flipColumn].set(aTurn);
            }
            flips_sw--;
            flip_count++;
          }

        /* Western flips */
        flipColumn = column;
        while (flips_w > 0){
          flipColumn = flipColumn - 1;
          if (aTest == false) {
        	  this.cell[row][flipColumn].set(aTurn);
          }
          flips_w--;
          flip_count++;
        }

        /* Northwestern flips */
        flipColumn = column;
        flipRow = row;
        while (flips_nw > 0){
          flipRow = flipRow - 1;
          flipColumn = flipColumn - 1;
          if (aTest == false) {
        	  this.cell[flipRow][flipColumn].set(aTurn);
          }
          flips_nw--;
          flip_count++;
        }

       if (flip_count > 0 && aTest == false) {
         this.cell[row][column].set(aTurn);
       }
       return flip_count;
     }

    /*
    * Result of method: This method checks whether a move is valid when a button is clicked.
    * @param aTurn which is the color of the player whose turn it is.
    * @return status. Returns true if the clicked button is a valid movement for the color of the current player.
    */
    public boolean checkForValidMove(TokenColor aTurn)
    {
    	boolean status = false;
    	for (int r = 0; r < ROW; r++) {
    		for (int c = 0; c < COLUMN; c++) {
    		  if (cell[r][c].get() == TokenColor.BLANK) {
    		      if (checkAdjacentCells(r, c, aTurn) == true && flip(r, c, aTurn, true) != 0){
    			        status = true;
    			  }
    		  }
    		}
    	}
    	return status;
    }
}
