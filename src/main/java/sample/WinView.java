package sample;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

import static sample.SceneManager.currScene.ChooseLevelView;
import static sample.SceneManager.currScene.Game1;
import static sample.SceneManager.currScene.Menu;

public class WinView extends Scene{
    private AnchorPane myPane;
    private Button reTakeLevel;
    private Button levelMenu;
    private Button mainMenu;
    private Label myLabel;

    public WinView()//do poprawy, zeby wracalo do poprzedniego poziomu
    {
        super(new AnchorPane());
        myPane = (AnchorPane) this.getRoot();
        myPane.setPrefSize(600,600);
        myPane.setBlendMode(BlendMode.EXCLUSION);
        myPane.setStyle("-fx-backgorund-color: #FFFFFF;");

        myLabel= new Label("      Congratulations!!!\n You won. What do you want do next?");
        myLabel.setFont(new Font("Bauhaus 93",31.0));
        myLabel.setLayoutX(40.0);
        myLabel.setLayoutY(135.0);

        reTakeLevel = new Button("Try again");
        reTakeLevel.setOnAction(e -> SceneManager.getInstance().changeScene(Game1));
        reTakeLevel.setLayoutX(40.0);
        reTakeLevel.setLayoutY(240.0);
        reTakeLevel.setPrefSize(100.0,60.0);

        levelMenu = new Button("Go to Level Menu");
        levelMenu.setOnAction(e -> SceneManager.getInstance().changeScene(ChooseLevelView));
        levelMenu.setLayoutX(180.0);
        levelMenu.setLayoutY(240.0);
        levelMenu.setPrefSize(150.0,60.0);

        mainMenu = new Button("Go to Main Menu");
        mainMenu.setOnAction(e -> SceneManager.getInstance().changeScene(Menu));
        mainMenu.setLayoutX(360.0);
        mainMenu.setLayoutY(240.0);
        mainMenu.setPrefSize(150.0,60.0);

        myPane.getChildren().addAll(myLabel,reTakeLevel,levelMenu,mainMenu);

    }
}
