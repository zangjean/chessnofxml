package chess.game.model;

import chess.game.controller.GameController;

public class GameModel {

    private GameController gameController;

    public GameModel(GameController gameController) {
        this.gameController = gameController;
    }

    public GameController getGameController() {
        return gameController;
    }
}
