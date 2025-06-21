package chess.pieces;

import java.util.Objects;

public class Position {
    private int row;
    private int col;

    public Position(int posX, int posY){
        this.row = posX;
        this.col = posY;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        if(row < 0 || row > 7) {
            throw new IllegalArgumentException("ERROR: setRow -> Position row must be between 0 and 7");
        }else{
            this.row = row;
        }
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        if(col < 0 || col > 7) {
            throw new IllegalArgumentException("ERROR: setCol -> Position col must be between 0 and 7");
        }else {
            this.col = col;
        }
    }

    @Override
    public String toString() {
        return "(" + row + "," + col + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return row == position.row && col == position.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

}
