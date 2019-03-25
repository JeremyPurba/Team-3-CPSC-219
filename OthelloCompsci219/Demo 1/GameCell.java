public class GameCell{
    Color aColor;
    int row;
    int column;

    public void draw(){
        if(aColor == Color.BLACK){
            System.out.print(" B ");
        }else if(aColor == Color.WHITE){
            System.out.print(" W ");
        }else{
            System.out.print(" - ");
        }
    }

    public void clear(){
        aColor = Color.BLANK;
    }

    public void set(Color value){
        aColor = value;
    }

    public Color get(){
      return aColor;
    }

    public GameCell(int roww, int col){
        this.row = roww;
        this.column = col;
        clear();
    }
}
