package chess.pieces;

import javafx.util.Pair;

import java.util.ArrayList;


public abstract class Piece {
    private boolean isWhite;
    private boolean already_moved;
    private ArrayList<Pair<Integer, Integer>> positions_possible_moves;

    public Piece(boolean isWhite) {
        this.isWhite = isWhite;
        already_moved = false;
        positions_possible_moves = new ArrayList<>();
    }

    public boolean isWhite() {
        return isWhite;
    }

    public boolean isAlready_moved() {
        return already_moved;
    }

    public boolean canMoveTo(int fromRow, int fromCol, int toRow, int toCol, boolean forEat){
        return false;
    }

    public ArrayList<Pair<Integer, Integer>> updatePositions_possible_move(int lastRow, int lastCol) {
        return positions_possible_moves;
    }

    public void setPositions_possible_moves(ArrayList<Pair<Integer, Integer>> positions_possible_moves) {
        this.positions_possible_moves = positions_possible_moves;
    }

    public void setAlready_moved(boolean already_moved) {
        this.already_moved = already_moved;
    }

    public ArrayList<Pair<Integer, Integer>> getPositions_possible_moves() {
        return this.positions_possible_moves;
    }
}
