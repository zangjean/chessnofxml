package chess.board;

public class SwitchBoard {
    private String piece;
    //exemple black_bishop/white_king/etc

    private int row;
    private int col;

    public SwitchBoard(String name, int row, int col){
        this.piece = name;
        this.row = row;
        this.col = col;
    }

    public String getPieceName() {
        return piece;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
