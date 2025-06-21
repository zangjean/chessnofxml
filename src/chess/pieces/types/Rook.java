package chess.pieces.types;

import chess.pieces.Piece;
import javafx.util.Pair;

import java.util.ArrayList;

public class Rook extends Piece {
    public Rook(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public boolean canMoveTo(int fromRow, int fromCol, int toRow, int toCol, boolean forEat) {
        //return Math.abs(fromRow-toRow) == 0 || Math.abs(fromCol-toCol) == 0;
        return this.getPositions_possible_moves().contains(new Pair<>(toRow,toCol));
    }

    @Override
    public ArrayList<Pair<Integer, Integer>> updatePositions_possible_move(int lastRow, int lastCol) {
        ArrayList<Pair<Integer,Integer>> positions= new ArrayList<>();
        //ROOK MOVES
        int row = lastRow;
        int col = lastCol;

        for(int x = row ; x <= 7 ; x++){
            positions.add(new Pair<>(x,col));
        }
        for(int x = row ; x >= 0 ; x--){
            positions.add(new Pair<>(x,col));
        }
        for (int y = col ; y <= 7 ; y++){
            positions.add(new Pair<>(row,y));
        }
        for (int y = col ; y >= 0 ; y--){
            positions.add(new Pair<>(row,y));
        }
        this.setPositions_possible_moves(positions);
        return positions;
    }


}
