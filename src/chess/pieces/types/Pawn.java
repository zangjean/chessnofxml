package chess.pieces.types;

import chess.pieces.Piece;
import javafx.util.Pair;

import java.util.ArrayList;

public class Pawn extends Piece {
    public Pawn(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public boolean canMoveTo(int fromRow, int fromCol, int toRow, int toCol, boolean forEat) {
        if(forEat){
            if(this.isWhite()){
                return (fromRow - toRow == 1 && (toCol == fromCol - 1 || toCol == fromCol + 1));
            }else{
                return (fromRow - toRow == -1 && (toCol == fromCol - 1 || toCol == fromCol + 1));
            }
        }

        /*
        if(this.isWhite()){
            if(this.isAlready_moved()){
                return (toRow == fromRow - 1) && (toCol == fromCol);
            }else{
                return (toCol == fromCol) && ((toRow == fromRow - 1) || (toRow == fromRow - 2));
            }
        }else{
            if(this.isAlready_moved()){
                return (toRow == fromRow + 1) && (toCol == fromCol);
            }else{
                return (toCol == fromCol) && ((toRow == fromRow + 1) || (toRow == fromRow + 2));

            }

        }

         */

        return this.getPositions_possible_moves().contains(new Pair<>(toRow,toCol));
    }

    @Override
    public ArrayList<Pair<Integer, Integer>> updatePositions_possible_move(int lastRow, int lastCol) {
        ArrayList<Pair<Integer,Integer>> positions= new ArrayList<>();
        int row = lastRow;
        int col = lastCol;
        if(this.isWhite()){
            positions.add(new Pair<>(row-1,col));
            if(!this.isAlready_moved()){
                positions.add(new Pair<>(row-2,col));
            }
        }else {
            positions.add(new Pair<>(row+1,col));
            if(!this.isAlready_moved()){
                positions.add(new Pair<>(row+2,col));
            }
        }
        this.setPositions_possible_moves(positions);
        return positions ;
    }

}
