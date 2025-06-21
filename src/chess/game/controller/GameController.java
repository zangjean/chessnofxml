package chess.game.controller;

import chess.board.controller.BoardController;
import chess.game.model.GameModel;
import chess.game.view.GameView;
import javafx.stage.Stage;

public class GameController {

    private GameModel gameModel;
    private GameView gameView;

    private BoardController boardController;

    public GameController(Stage primaryStage) {
        // Initialize BoardController first
        this.boardController = new BoardController(this);
        
        // Initialize GameModel and GameView
        this.gameModel = new GameModel(this);
        this.gameView = new GameView(this, primaryStage);
    }

    public GameModel getGameModel() {
        return gameModel;
    }

    public GameView getGameView() {
        return gameView;
    }

    public BoardController getBoardController() {
        return boardController;
    }
}