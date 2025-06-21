package chess.board.model;

import chess.board.controller.BoardController;
import chess.pieces.Piece;
import chess.pieces.types.*;

import java.util.ArrayList;

public class BoardModel {

    private BoardController boardController;
    private Cell[][] board;
    private boolean whiteTurn = true;
    private boolean gameOver = false;
    private boolean isCheck = false;
    private boolean isCheckmate = false;
    private boolean isStalemate = false;
    private boolean isDraw = false;
    private boolean isWhite_to_move = true;
    private boolean isBlack_to_move = false;
    private boolean isWhite_in_check = false;
    private boolean isBlack_in_check = false;

    public BoardModel(BoardController boardController) {
        this.boardController = boardController;
        this.board = new Cell[8][8];
    }

    public void initBoard() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                this.board[row][col] = new Cell(row, col, (row + col) % 2 == 0);

            }
        }

        // Pièces noires
        this.board[0][0].setPiece(new Rook(false));
        this.board[0][1].setPiece(new Knight(false));
        this.board[0][2].setPiece(new Bishop(false));
        this.board[0][3].setPiece(new Queen(false));
        this.board[0][4].setPiece(new King(false));
        this.board[0][5].setPiece(new Bishop(false));
        this.board[0][6].setPiece(new Knight(false));
        this.board[0][7].setPiece(new Rook(false));
        for (int i = 0; i < 8; i++) {
            this.board[1][i].setPiece(new Pawn(false));
        }

        // Pièces blanches
        this.board[7][0].setPiece(new Rook(true));
        this.board[7][1].setPiece(new Knight(true));
        this.board[7][2].setPiece(new Bishop(true));
        this.board[7][3].setPiece(new Queen(true));
        this.board[7][4].setPiece(new King(true));
        this.board[7][5].setPiece(new Bishop(true));
        this.board[7][6].setPiece(new Knight(true));
        this.board[7][7].setPiece(new Rook(true));
        for (int i = 0; i < 8; i++) {
            this.board[6][i].setPiece(new Pawn(true));
        }

        this.boardController.end_initBoard_Model(this.board);

        printBoard();
    }

    public Cell[][] getBoard() {
        return board;
    }

    public void setBoard(Cell[][] board) {
        this.board = board;
    }

    public BoardController getBoardController() {
        return boardController;
    }

    public Cell getCell(int colIndex, int rowIndex) {
        return board[rowIndex][colIndex];
    }

    public void setCell(Cell cell) {
        board[cell.getRowIndex()][cell.getColIndex()] = cell;
    }

    public String boardToString() {
        String s = "| ";
        for(int row=0; row < 8; row++) {
            for (int col=0; col < 8; col++) {
                Piece piece = board[row][col].getPiece();
                if(piece == null) {
                    s += "   ";
                    continue;
                }else{
                    String color = piece.isWhite() ? "_W" : "_B";
                    s +=piece.getClass().getSimpleName()+color+" | ";

                }
            }
            s += "\n|";
        }
        return s;
    }

    public void printBoard() {
        printLine();
        for(int row=0; row < 8; row++) {
            System.out.print("| ");
            for (int col=0; col < 8; col++) {
                Piece piece = board[row][col].getPiece();
                if(piece == null) {
                    printEmptyCell();
                    continue;
                }else{
                    String color = piece.isWhite() ? "_W" : "_B";
                    //System.out.print(piece.getClass().getSimpleName()+color+" |");

                    switch (piece.getClass().getSimpleName()) {
                        case "Bishop":
                            System.out.print(" Bishop"+color+" |");
                            break;
                        case "King":
                            System.out.print("  King"+color+"  |");
                            break;
                        case "Knight":
                            System.out.print(" Knight"+color+" |");
                            break;
                        case "Pawn":
                            System.out.print("  Pawn"+color+"  |");
                            break;
                        case "Queen":
                            System.out.print("  Queen"+color+" |");
                            break;
                        case "Rook":
                            System.out.print("  Rook"+color+"  |");
                            break;

                    }
                }
            }
            printLine();
        }

    }

    private void printLine(){
        System.out.print("\n");
        System.out.print("------------------------------------------------------------------------------------------");
        System.out.print("\n");
    }

    private void printEmptyCell(){
        System.out.print("          |");
    }

    public void mouvePieceFromTo(int lastRow, int lastCol, int row, int col, boolean forEat) {
        Piece piece = this.board[lastRow][lastCol].getPiece();

        if(piece.canMoveTo(lastRow,lastCol,row,col,forEat)) {
            piece.setAlready_moved(true);
            this.board[lastRow][lastCol].removePiece();
            this.board[row][col].setPiece(piece);
            this.board[row][col].getPiece().setPositions_possible_moves(new ArrayList<>());

            printBoard();
            this.boardController.updateBoardView(this.board,lastRow,lastCol,row,col);

            this.isWhite_to_move = !this.isWhite_to_move;
            this.isBlack_to_move = !this.isBlack_to_move;
            this.whiteTurn = !this.whiteTurn;



            String turn= (this.whiteTurn) ? " WHITE " : " BLACK ";
            this.boardController.getGameController().getGameView().setTurnLabel(turn);



        }else{
            System.out.println("YOU CAN'T MOVE");
        }


    }

    public boolean isWhiteTurn() {
        return whiteTurn;
    }

    public void setWhiteTurn(boolean whiteTurn) {
        this.whiteTurn = whiteTurn;
    }


}