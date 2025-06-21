package chess.pieces.types;

import chess.pieces.Piece;
import javafx.util.Pair;

import java.util.ArrayList;

public class Knight extends Piece {
    public Knight(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public boolean canMoveTo(int fromRow, int fromCol, int toRow, int toCol, boolean forEat) {
        //return Math.abs(fromRow-toRow) == 2 && Math.abs(fromCol-toCol) == 1 || Math.abs(fromRow-toRow) == 1 && Math.abs(fromCol-toCol) == 2;
        return this.getPositions_possible_moves().contains(new Pair<>(toRow,toCol));
    }

    @Override
    public ArrayList<Pair<Integer, Integer>> updatePositions_possible_move(int lastRow, int lastCol) {
        ArrayList<Pair<Integer,Integer>> positions= new ArrayList<>();
        int row = lastRow;
        int col = lastCol;
        if(row-2 >=0){
            if(col-1 >=0){
                positions.add(new Pair<>(row-2,col-1));
            }
            if(col+1 <=7){
                positions.add(new Pair<>(row-2,col+1));
            }
        }
        if(row-1 >=0){
            if(col-2 >=0){
                positions.add(new Pair<>(row-1,col-2));
            }
            if(col+2 <=7){
                positions.add(new Pair<>(row-1,col+2));
            }
        }
        if(row+1 <=7){
            if(col-2 >=0){
                positions.add(new Pair<>(row+1,col-2));
            }
            if(col+2 <=7){
                positions.add(new Pair<>(row+1,col+2));
            }
        }
        if(row+2 <=7){
            if(col-1 >=0){
                positions.add(new Pair<>(row+2,col-1));
            }
            if(col+1 <=7){
                positions.add(new Pair<>(row+2,col+1));
            }
        }
        this.setPositions_possible_moves(positions);
        return positions ;
    }


}
