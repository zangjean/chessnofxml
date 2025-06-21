import chess.game.controller.GameController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GameController gameController = new GameController(primaryStage);

        Scene scene=new Scene(gameController.getGameView(),gameController.getGameView().getwidth(),gameController.getGameView().getheight());
        primaryStage.setTitle("Chess");
        primaryStage.setScene(scene);
        primaryStage.show();


    }
}