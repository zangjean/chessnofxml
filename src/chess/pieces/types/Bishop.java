package chess.pieces.types;


import chess.board.model.Cell;
import chess.pieces.Piece;
import javafx.util.Pair;

import java.util.ArrayList;

public class Bishop extends Piece{

    public Bishop(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public boolean canMoveTo(int fromRow, int fromCol, int toRow, int toCol,boolean forEat) {
        //return Math.abs(fromRow-toRow) == Math.abs(fromCol-toCol);
        return this.getPositions_possible_moves().contains(new Pair<>(toRow,toCol));
    }

    @Override
    public ArrayList<Pair<Integer, Integer>> updatePositions_possible_move(int lastRow, int lastCol) {
        ArrayList<Pair<Integer,Integer>> positions= new ArrayList<>();
        int row = lastRow;
        int col = lastCol;
        while(row < 7 && col < 7){
            row++;
            col++;
            positions.add(new Pair<>(row,col));
        }

        row = lastRow;
        col = lastCol;
        while(row > 0 && col > 0){
            row--;
            col--;
            positions.add(new Pair<>(row,col));
        }

        row = lastRow;
        col = lastCol;
        while(row < 7 && col > 0){
            row++;
            col--;
            positions.add(new Pair<>(row,col));
        }

        row = lastRow;
        col = lastCol;
        while(row > 0 && col < 7){
            row--;
            col++;
            positions.add(new Pair<>(row,col));
        }
        this.setPositions_possible_moves(positions);
        return positions ;
    }

}
