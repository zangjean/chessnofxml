package chess.board.controller;

import chess.board.SwitchBoard;
import chess.board.model.BoardModel;
import chess.board.model.Cell;
import chess.board.view.BoardView;
import chess.game.controller.GameController;
import javafx.util.Pair;

import java.util.ArrayList;

public class BoardController {

    private BoardModel boardModel;
    private BoardView boardView;
    private Boolean youClickOnPieceBefore = false;
    private int lastRow = -1;
    private int lastCol = -1;
    private ArrayList<Pair<Integer, Integer>> lastPossible_moves = new ArrayList<>();
    private GameController gameController;

    public BoardController(GameController gameController) {
        this.boardModel = new BoardModel(this);
        this.boardView = new BoardView(this);
        this.gameController = gameController;

        // Appeler initBoard ici, après l'initialisation de boardView
        this.boardModel.initBoard();
    }

    public void end_initBoard_Model(Cell[][] board) {
        this.boardView.initBoard(switch_board(board));

    }

    public BoardModel getBoardModel() {
        return boardModel;
    }

    public BoardView getBoardView() {
        return boardView;
    }

    //transmettre a la view quels pieces sont ou
    private ArrayList<SwitchBoard> switch_board(Cell[][] board) {
        //
        ArrayList<SwitchBoard> switch_board = new ArrayList<>();

        for(int row=0; row < 8; row++) {
            for (int col=0; col < 8; col++) {
                if(board[row][col].getPiece() == null) continue;

                String color = board[row][col].getPiece().isWhite() ? "white_" : "black_";
                String piece = board[row][col].getPiece().getClass().getSimpleName();
                switch_board.add(new SwitchBoard(color+piece, row, col));
            }
        }
        return switch_board;
    }


    public void setOnMouseClickedOnCell(int row, int col) {
    // Désélectionner les cellules mises en surbrillance précédemment
    if (!lastPossible_moves.isEmpty()) {
        this.boardView.un_highlightCells(lastPossible_moves);
    }

    // Vérification si la cellule cliquée est libre
    if (boardModel.getCell(col, row).isFree()) {
        if (youClickOnPieceBefore) {
            // Déplacer la pièce vers une cellule vide
            this.boardModel.mouvePieceFromTo(this.lastRow, this.lastCol, row, col, false);
        }
        // Réinitialiser l'état de sélection
        this.lastCol = -1;
        this.lastRow = -1;
        youClickOnPieceBefore = false;

    } else {
        // Vérification des tours (si c'est au tour blanc ou noir de jouer)
        boolean isWhiteTurn = this.boardModel.isWhiteTurn();
        boolean isWhitePiece = this.boardModel.getCell(col, row).getPiece().isWhite();

        if ((isWhitePiece == isWhiteTurn) || (youClickOnPieceBefore && isWhiteTurn != isWhitePiece)) {
            if (youClickOnPieceBefore) {
                // Capturer une pièce adverse
                if (this.boardModel.getCell(col, row).getPiece().isWhite() !=
                    this.boardModel.getCell(this.lastCol, this.lastRow).getPiece().isWhite()) {
                    System.out.println("Vous mangez une pièce adverse!");
                    this.boardModel.mouvePieceFromTo(this.lastRow, this.lastCol, row, col, true);


                    // Réinitialiser l'état après capture pour éviter de multiples captures consécutives

                    this.lastCol = -1;
                    this.lastRow = -1;
                    youClickOnPieceBefore = false;
                    return;

                }
            }

            // Mettre à jour les coordonnées de la dernière sélection
            this.lastRow = row;
            this.lastCol = col;
            youClickOnPieceBefore = true;

            // Récupérer et mettre en surbrillance les mouvements possibles
            ArrayList<Pair<Integer, Integer>> possible_moves = 
                this.boardModel.getCell(col, row).getPiece().updatePositions_possible_move(lastRow, lastCol);

            this.boardView.highlightCells(possible_moves);
            this.lastPossible_moves = possible_moves;
        }
    }
}


    public Boolean getYouClickOnPieceBefore() {
        return youClickOnPieceBefore;
    }

    public void setYouClickOnPieceBefore(Boolean youClickOnPieceBefore) {
        this.youClickOnPieceBefore = youClickOnPieceBefore;
    }

    public void updateBoardView(Cell[][] board, int lastRow, int lastCol, int row, int col) {
        this.boardView.updateBoardView(switch_board(board),lastRow,lastCol,row,col);
    }

    public ArrayList<Pair<Integer, Integer>> getLastPossible_moves() {
        return lastPossible_moves;
    }

    public void setLastPossible_moves(ArrayList<Pair<Integer, Integer>> lastPossible_moves) {
        this.lastPossible_moves = lastPossible_moves;
    }

    public GameController getGameController() {
        return gameController;
    }
}