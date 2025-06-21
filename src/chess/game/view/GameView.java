package chess.game.view;

import chess.game.controller.GameController;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;



public class GameView extends BorderPane {
    private GameController gameController;

    private int width = 1400;
    private int height = 700;
    private Label turnLabel;


    private GridPane board;
    private Stage primaryStage;

    public GameView(GameController gameController, Stage primaryStage) {
        super();
        this.turnLabel = new Label(" WHITE ");
        this.turnLabel.getStyleClass().removeAll("white-turn", "black-turn");
        this.turnLabel.getStyleClass().add("turn-label");
        this.gameController = gameController;
        this.primaryStage = primaryStage;

        this.board = this.gameController.getBoardController().getBoardView();
        this.setCenter(board);
        board.setAlignment(Pos.CENTER);

        Label label = new Label("IT'S TURN OF : ");
        HBox hbox = new HBox(label,turnLabel);

        this.setTop(hbox);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(10);
        hbox.setPrefHeight(50);
        hbox.setPrefWidth(width);
        hbox.setAlignment(Pos.CENTER);




    }

    public GameController getGameController() {
        return gameController;
    }

    public int getwidth() {
        return width;
    }

    public int getheight() {
        return height;
    }

    public Label getTurnLabel() {
        return turnLabel;
    }

    public void setTurnLabel(String turnLabel) {
        this.turnLabel.setText(turnLabel);
    }
}
