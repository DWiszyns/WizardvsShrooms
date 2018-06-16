package sample;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;


import static sample.SceneManager.currScene.*;

public class ChooseLevelScene extends Scene {
    private AnchorPane myPane;
    private Button level1;
    private Button level2;
    private Button level3;
    private Label myLabel;

    public ChooseLevelScene(int howManyLevels)
    {
        super(new AnchorPane());
        myPane = (AnchorPane) this.getRoot();
        myPane.setPrefSize(600,600);
        myPane.setBlendMode(BlendMode.EXCLUSION);
        myPane.setStyle("-fx-backgorund-color: #FFFFFF;");

        myLabel= new Label("Choose which level you want to play");
        myLabel.setFont(new Font("Bauhaus 93",31.0));
        myLabel.setLayoutX(40.0);
        myLabel.setLayoutY(135.0);

        level1 = new Button("1");
        level1.setOnAction(e -> {
            SceneManager.getInstance().setWhichLevel(1);
            SceneManager.getInstance().changeScene(Game);
        });
        level1.setLayoutX(205.0);
        level1.setLayoutY(240.0);
        level1.setPrefSize(30.0,30.0);

        level2 = new Button("2");
        if(howManyLevels>1) level2.setOnAction(e -> {
            SceneManager.getInstance().setWhichLevel(2);
            SceneManager.getInstance().changeScene(Game);
        });
        level2.setLayoutX(260.0);
        level2.setLayoutY(240.0);
        level2.setPrefSize(30.0,30.0);

        level3 = new Button("3");
        if(howManyLevels>2)level3.setOnAction(e -> {
            SceneManager.getInstance().setWhichLevel(3);
            SceneManager.getInstance().changeScene(Game);
        });
        level3.setLayoutX(315.0);
        level3.setLayoutY(240.0);
        level3.setPrefSize(30.0,30.0);

        myPane.getChildren().addAll(myLabel,level1,level2,level3);

    }
}
