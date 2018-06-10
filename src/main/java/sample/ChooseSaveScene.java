package sample;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

import static sample.SceneManager.currScene.*;
import static sample.SceneManager.currScene.Game2;

public class ChooseSaveScene extends Scene {
    private AnchorPane myPane;
    private Button save1;
    private Button save2;
    private Button save3;
    private Button save4;
    private Button save5;
    private Button save6;
    private Label myLabel;
    ChooseSaveScene()
    {
        super(new AnchorPane());
        myPane = (AnchorPane) this.getRoot();
        myPane.setPrefSize(600,600);
        myPane.setBlendMode(BlendMode.EXCLUSION);
        myPane.setStyle("-fx-backgorund-color: #FFFFFF;");

        myLabel= new Label("Choose which game you want to continue");
        myLabel.setFont(new Font("Bauhaus 93",31.0));
        myLabel.setLayoutX(40.0);
        myLabel.setLayoutY(135.0);

        save1 = new Button(SaveManager.getInstance().getSave(0).getNameOfOurUser());
        save1.setOnAction(e -> {
            if(SaveManager.getInstance().getSave(0).getHowManyLevelsAvailable()==1) SceneManager.getInstance().changeScene(ChooseLevelView1);
            else if(SaveManager.getInstance().getSave(0).getHowManyLevelsAvailable()==2) SceneManager.getInstance().changeScene(ChooseLevelView2);
            else if(SaveManager.getInstance().getSave(0).getHowManyLevelsAvailable()==3) SceneManager.getInstance().changeScene(ChooseLevelView3);
        });
        save1.setLayoutX(205.0);
        save1.setLayoutY(200.0);
        save1.setPrefSize(100.0,60.0);

        save2 = new Button(SaveManager.getInstance().getSave(1).getNameOfOurUser());
        save2.setOnAction(e -> {
            if(SaveManager.getInstance().getSave(1).getHowManyLevelsAvailable()==1) SceneManager.getInstance().changeScene(ChooseLevelView1);
            else if(SaveManager.getInstance().getSave(1).getHowManyLevelsAvailable()==2) SceneManager.getInstance().changeScene(ChooseLevelView2);
            else if(SaveManager.getInstance().getSave(1).getHowManyLevelsAvailable()==3) SceneManager.getInstance().changeScene(ChooseLevelView3);
        });
        save2.setLayoutX(205.0);
        save2.setLayoutY(270.0);
        save2.setPrefSize(100.0,60.0);

        save3 = new Button(SaveManager.getInstance().getSave(2).getNameOfOurUser());
        save3.setOnAction(e -> {
            if(SaveManager.getInstance().getSave(2).getHowManyLevelsAvailable()==1) SceneManager.getInstance().changeScene(ChooseLevelView1);
            else if(SaveManager.getInstance().getSave(2).getHowManyLevelsAvailable()==2) SceneManager.getInstance().changeScene(ChooseLevelView2);
            else if(SaveManager.getInstance().getSave(2).getHowManyLevelsAvailable()==3) SceneManager.getInstance().changeScene(ChooseLevelView3);
        });
        save3.setLayoutX(205.0);
        save3.setLayoutY(340.0);
        save3.setPrefSize(100.0,60.0);

        save4 = new Button(SaveManager.getInstance().getSave(3).getNameOfOurUser());
        save4.setOnAction(e ->{
            if(SaveManager.getInstance().getSave(3).getHowManyLevelsAvailable()==1) SceneManager.getInstance().changeScene(ChooseLevelView1);
            else if(SaveManager.getInstance().getSave(3).getHowManyLevelsAvailable()==2) SceneManager.getInstance().changeScene(ChooseLevelView2);
            else if(SaveManager.getInstance().getSave(3).getHowManyLevelsAvailable()==3) SceneManager.getInstance().changeScene(ChooseLevelView3);
        });
        save4.setLayoutX(205.0);
        save4.setLayoutY(410.0);
        save4.setPrefSize(100.0,60.0);

        save5 = new Button(SaveManager.getInstance().getSave(4).getNameOfOurUser());
        save5.setOnAction(e ->{
            if(SaveManager.getInstance().getSave(4).getHowManyLevelsAvailable()==1) SceneManager.getInstance().changeScene(ChooseLevelView1);
            else if(SaveManager.getInstance().getSave(4).getHowManyLevelsAvailable()==2) SceneManager.getInstance().changeScene(ChooseLevelView2);
            else if(SaveManager.getInstance().getSave(4).getHowManyLevelsAvailable()==3) SceneManager.getInstance().changeScene(ChooseLevelView3);
        });
        save5.setLayoutX(205.0);
        save5.setLayoutY(480.0);
        save5.setPrefSize(100.0,60.0);

        save6 = new Button(SaveManager.getInstance().getSave(5).getNameOfOurUser());
        save6.setOnAction(e ->{
            if(SaveManager.getInstance().getSave(5).getHowManyLevelsAvailable()==1) SceneManager.getInstance().changeScene(ChooseLevelView1);
            else if(SaveManager.getInstance().getSave(5).getHowManyLevelsAvailable()==2) SceneManager.getInstance().changeScene(ChooseLevelView2);
            else if(SaveManager.getInstance().getSave(5).getHowManyLevelsAvailable()==3) SceneManager.getInstance().changeScene(ChooseLevelView3);
        });
        save6.setLayoutX(205.0);
        save6.setLayoutY(550.0);
        save6.setPrefSize(100.0,60.0);

        myPane.getChildren().addAll(myLabel,save1,save2,save3,save4,save5,save6);

    }
}
