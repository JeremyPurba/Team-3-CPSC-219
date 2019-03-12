public class GameCell{
    TokenColor aColor;
    int row;
    int column;

    public void draw(){
        if(aColor == TokenColor.BLACK){
            System.out.print(" B ");
        }else if(aColor == TokenColor.WHITE){
            System.out.print(" W ");
        }else{
            System.out.print(" - ");
        }
    }

    public void clear(){
        aColor = TokenColor.BLANK;
    }

    public void set(TokenColor value){
        aColor = value;
    }

    public TokenColor get(){
      return aColor;
    }

    public GameCell(int roww, int col){
        this.row = roww;
        this.column = col;
        clear();
    }
}
