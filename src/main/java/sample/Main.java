package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private SceneManager myManager;
    private Object lock = new Object();

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage=myManager.getInstance().getStage();
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
