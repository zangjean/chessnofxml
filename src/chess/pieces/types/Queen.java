package chess.pieces.types;

import chess.pieces.Piece;
import javafx.util.Pair;

import java.util.ArrayList;

public class Queen extends Piece {
    public Queen(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public boolean canMoveTo(int fromRow, int fromCol, int toRow, int toCol, boolean forEat) {
        //return (Math.abs(fromRow - toRow) == 0 || Math.abs(fromCol - toCol) == 0) || (Math.abs(fromRow - toRow) == Math.abs(fromCol - toCol));
        return this.getPositions_possible_moves().contains(new Pair<>(toRow,toCol));
    }

    @Override
    public ArrayList<Pair<Integer, Integer>> updatePositions_possible_move(int lastRow, int lastCol) {
        ArrayList<Pair<Integer, Integer>> positions = new ArrayList<>();

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

        //BISHOP MOVES
        int x = row;
        int y = col;
        while (x < 7 && y < 7 ){
            x++;
            y++;
            positions.add(new Pair<>(x,y));
        }
        x = row;
        y = col;
        while (x > 0 && y > 0 ){
            x--;
            y--;
            positions.add(new Pair<>(x,y));
        }
        x = row;
        y = col;
        while (x < 7 && y > 0 ){
            x++;
            y--;
            positions.add(new Pair<>(x,y));
        }
        x = row;
        y = col;
        while (x > 0 && y < 7 ){
            x--;
            y++;
            positions.add(new Pair<>(x,y));
        }
        this.setPositions_possible_moves(positions);
        return positions;


    }


}

    /*
    int actual_posX = this.getActualPosition().getPosX();
        int actual_posY = this.getActualPosition().getPosY();
        ArrayList<Position> positions_possible_moves = new ArrayList<>();

        //ROOK moves:
        for(int x = actual_posX ; x <= 7 ; x++){
            Position position = new Position(x,actual_posY);
            positions_possible_moves.add(position);
        }
        for(int x = actual_posX ; x >= 0 ; x--){
            Position position = new Position(x,actual_posY);
            positions_possible_moves.add(position);
        }
        for (int y = actual_posY ; y <= 7 ; y++){
            Position position = new Position(actual_posX,y);
            positions_possible_moves.add(position);
        }
        for (int y = actual_posY ; y >= 0 ; y--){
            Position position = new Position(actual_posX,y);
            positions_possible_moves.add(position);
        }


        //BISHOP moves:

        int x = actual_posX;
        int y = actual_posY;
        while (x < 7 && y < 7 ){
            x++;
            y++;
            Position position = new Position(x,y);
            positions_possible_moves.add(position);
        }

        x = actual_posX;
        y = actual_posY;
        while (x > 0 && y > 0 ){
            x--;
            y--;
            Position position = new Position(x,y);
            positions_possible_moves.add(position);
        }

        x = actual_posX;
        y = actual_posY;
        while (x < 7 && y > 0 ){
            x++;
            y--;
            Position position = new Position(x,y);
            positions_possible_moves.add(position);
        }

        x = actual_posX;
        y = actual_posY;
        while (x > 0 && y < 7 ){
            x--;
            y++;
            Position position = new Position(x,y);
            positions_possible_moves.add(position);
        }

     */
