package chess.board.model;


import chess.pieces.Piece;
import chess.pieces.Position;


public class Cell {

    private Position position;
    private boolean isWhite;

    private Piece piece;


    public Cell(int rowIndex, int colIndex, boolean isWhite) {
        this.position = new Position(rowIndex, colIndex);
        this.isWhite = isWhite;
    }


    public int getColIndex() {
        return this.position.getCol();
    }


    public int getRowIndex() {
        return this.position.getRow();
    }


    public boolean isWhite() {
        return isWhite;
    }


    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public boolean isFree() {
        return piece == null;
    }

    public void removePiece() {
        piece = null;
    }
}
