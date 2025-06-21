package chess.board.view;

import chess.board.SwitchBoard;
import chess.board.controller.BoardController;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.util.Pair;

import java.net.URL;
import java.util.ArrayList;

public class BoardView extends GridPane {

    private int cellSize = 80;
    private BoardController boardController;
    private ArrayList<SwitchBoard> switchBoards;
    private String stylesheet;

    public BoardView(BoardController boardController) {
        super();
        try {
            URL imageURL = getClass().getResource("/ressources/styles/game.css");
            if (imageURL == null) {
                throw new IllegalArgumentException("Stylesheet 'game.css' non trouvé à /ressources/styles/");
            }
            stylesheet = imageURL.toExternalForm();
            this.getStylesheets().add(stylesheet);
        } catch (Exception e) {
            System.err.println("Erreur chargement du stylesheet : " + e.getMessage());
        }

        this.setGridLinesVisible(true);
        this.boardController = boardController;
    }

    public void initBoard(ArrayList<SwitchBoard> switchBoards) {
        this.switchBoards = switchBoards;

        // Créer les cellules du tableau
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                StackPane cell = new StackPane();
                cell.getStyleClass().add("cell");
                cell.setPrefSize(cellSize, cellSize);

                String styleClass = (row % 2 == col % 2) ? "cell-white" : "cell-black";
                cell.getStyleClass().add(styleClass);

                GridPane.setRowIndex(cell, row);
                GridPane.setColumnIndex(cell, col);
                this.getChildren().add(cell);

                setOnMouseClickedOnCell(cell);
            }
        }

        // Placer les pièces
        this.setPiecesOnBoard();
        System.out.println("Board initialized");
    }

    private void setOnMouseClickedOnCell(StackPane cell) {
        cell.setOnMouseClicked((MouseEvent event) -> {
            int row = GridPane.getRowIndex(cell);
            int col = GridPane.getColumnIndex(cell);
            System.out.println("Mouse clicked on cell [" + row + "," + col + "]");
            this.boardController.setOnMouseClickedOnCell(row,col);

        });
    }

    public ArrayList<SwitchBoard> getSwitchBoards() {
        return switchBoards;
    }

    private void setPiecesOnBoard() {
        for (SwitchBoard switchBoard : switchBoards) {
            int row = switchBoard.getRow();
            int col = switchBoard.getCol();

            // Valider les coordonnées avant de continuer
            if (row < 0 || row >= 8 || col < 0 || col >= 8) {
                System.err.println("Coordonnées invalides pour la pièce : "
                        + switchBoard.getPieceName() + " à [" + row + "," + col + "]");
                continue;
            }

            String repository = switchBoard.getPieceName().contains("white") ? "white" : "black";
            String imagePath = "/ressources/image/pieces/" + repository + "/" + switchBoard.getPieceName().toLowerCase() + ".png";

            try {
                URL imageURL = getClass().getResource(imagePath);
                if (imageURL == null) {
                    throw new IllegalArgumentException("Image non trouvée : " + imagePath);
                }

                Image pieceImage = new Image(imageURL.toExternalForm());
                ImageView imageView = new ImageView(pieceImage);
                imageView.setFitHeight(cellSize);
                imageView.setFitWidth(cellSize);

                // Obtenir la cellule appropriée et y ajouter l'image de la pièce
                StackPane cell = (StackPane) this.getChildren().get(row * 8 + col+1);

                cell.getChildren().add(imageView);

            } catch (IllegalArgumentException e) {
                System.err.println("Erreur lors du chargement de l'image : " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Erreur inattendue : " + e.getMessage());
                e.printStackTrace();
            }

            System.out.println("Pièce : " + switchBoard.getPieceName() + " chargée à [" + row + "," + col + "]");
        }
    }

    public void updateBoardView(ArrayList<SwitchBoard> switchBoards, int lastRow, int lastCol, int row, int col) {
        //je déplace l'imageview de lastRow lastCol vers row col
        this.switchBoards = switchBoards;
        StackPane lastCell = (StackPane) this.getChildren().get(lastRow * 8 + lastCol+1);
        ImageView imageView = (ImageView) lastCell.getChildren().getFirst();

        lastCell.getChildren().removeFirst();
        StackPane newCell = (StackPane) this.getChildren().get(row * 8 + col+1);
        if(!newCell.getChildren().isEmpty()) {
            newCell.getChildren().removeFirst();
        }
        newCell.getChildren().addFirst(imageView);


    }

    public void highlightCells(ArrayList<Pair<Integer, Integer>> possibleMoves) {
        for(Pair<Integer, Integer> possibleMove : possibleMoves) {
            int row = possibleMove.getKey();
            int col = possibleMove.getValue();
            StackPane cell = (StackPane) this.getChildren().get(row * 8 + col+1);
            cell.getStyleClass().add("cell-highlight");
            System.out.println("You can move the " + cell.getStyleClass().get(1) + " to [" + row + "," + col + "]");
        }
    }

    public void un_highlightCells(ArrayList<Pair<Integer, Integer>> possibleMoves) {
        for(Pair<Integer, Integer> possibleMove : possibleMoves) {
            int row = possibleMove.getKey();
            int col = possibleMove.getValue();
            StackPane cell = (StackPane) this.getChildren().get(row * 8 + col+1);
            cell.getStyleClass().remove("cell-highlight");
        }
    }
}