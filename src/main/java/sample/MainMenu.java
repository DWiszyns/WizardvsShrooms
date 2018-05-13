package sample;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

import static sample.SceneManager.currScene.*;

import static java.lang.System.exit;

public class MainMenu extends Scene{
    private AnchorPane myPane;
    private Button newGame;
    private Button loadGame;
    private Button settings;
    private Button exitButton;
    private Label myLabel;
    private GameScene myGame;


    public MainMenu(){
        super(new AnchorPane());
        myPane = (AnchorPane) this.getRoot();
        myPane.setPrefSize(600,600);
        myPane.setBlendMode(BlendMode.EXCLUSION);
        myPane.setStyle("-fx-backgorund-color: #FFFFFF;");

        myLabel= new Label("Wizard vs Shrooms");
        myLabel.setFont(new Font("Bauhaus 93",31.0));
        myLabel.setLayoutX(178.0);
        myLabel.setLayoutY(135.0);

        newGame = new Button("New Game");
        newGame.setOnAction(e -> SceneManager.getInstance().changeScene(Game));
        newGame.setLayoutX(260.0);
        newGame.setLayoutY(205.0);
        newGame.setPrefSize(80.0,25.0);

        loadGame = new Button("Load Game");
        loadGame.setOnAction(e->exit(0));
        loadGame.setLayoutX(260.0);
        loadGame.setLayoutY(275.0);
        loadGame.setPrefSize(80.0,25.0);

        settings = new Button("Settings");
        settings.setOnAction(e->exit(0));
        settings.setLayoutX(260.0);
        settings.setLayoutY(345.0);
        settings.setPrefSize(80.0,25.0);

        exitButton = new Button("Exit");
        exitButton.setOnAction(e->exit(0));
        exitButton.setLayoutX(260.0);
        exitButton.setLayoutY(415.0);
        exitButton.setPrefSize(80.0,25.0);


        myPane.getChildren().add(newGame);
        myPane.getChildren().add(loadGame);
        myPane.getChildren().add(exitButton);
        myPane.getChildren().add(settings);
        myPane.getChildren().add(myLabel);
    }

    public Parent createContent()
    {
        return myPane;
    }

    public void changeScene()
    {
        myPane = myGame.startTheGame();
    }

}
