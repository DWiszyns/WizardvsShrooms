package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private MainMenu myMenu=new MainMenu();
    private GameScene myGame=new GameScene();

    @Override
    public void start(Stage primaryStage) throws Exception{
        Scene root = new Scene( myMenu.createContent());
        primaryStage.setTitle("Wizard vs Shrooms");
        primaryStage.setScene(root);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
