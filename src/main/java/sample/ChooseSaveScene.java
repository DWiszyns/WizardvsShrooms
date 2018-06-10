package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

import static sample.SceneManager.currScene.*;

public class ChooseSaveScene extends Scene {
    private AnchorPane myPane;
    private Button save1;
    private Button save2;
    private Button save3;
    private Button save4;
    private Button save5;
    private Button save6;
    private Button deleteButton;
    private Label myLabel;
    private Label deleteLabel;
    int toDelete = 6;


    ChooseSaveScene()
    {
        super(new AnchorPane());
        myPane = (AnchorPane) this.getRoot();
        myPane.setPrefSize(600,600);
        myPane.setBlendMode(BlendMode.EXCLUSION);
        myPane.setStyle("-fx-backgorund-color: #FFFFFF;");

        myLabel= new Label("Choose which game you want to continue");
        myLabel.setFont(new Font("Bauhaus 93",31.0));
        myLabel.setLayoutX(10.0);
        myLabel.setLayoutY(50.0);

        save1 = new Button(SaveManager.getInstance().getSave(0).getNameOfOurUser());
        if(!SaveManager.getInstance().getSave(0).isEmpty()) save1.setOnAction(e -> {
            SceneManager.getInstance().setWhichSave(0);
            SceneManager.getInstance().changeScene(ChooseLevel);
        });
        save1.setLayoutX(220.0);
        save1.setLayoutY(150.0);
        save1.setPrefSize(100.0,60.0);

        save2 = new Button(SaveManager.getInstance().getSave(1).getNameOfOurUser());
        if(!SaveManager.getInstance().getSave(1).isEmpty()) save2.setOnAction(e -> {
            SceneManager.getInstance().setWhichSave(1);
            SceneManager.getInstance().changeScene(ChooseLevel);
        });
        save2.setLayoutX(220.0);
        save2.setLayoutY(220.0);
        save2.setPrefSize(100.0,60.0);

        save3 = new Button(SaveManager.getInstance().getSave(2).getNameOfOurUser());
        if(!SaveManager.getInstance().getSave(2).isEmpty()) save3.setOnAction(e -> {
            SceneManager.getInstance().setWhichSave(2);
            SceneManager.getInstance().changeScene(ChooseLevel);
        });
        save3.setLayoutX(220.0);
        save3.setLayoutY(290.0);
        save3.setPrefSize(100.0,60.0);

        save4 = new Button(SaveManager.getInstance().getSave(3).getNameOfOurUser());
        if(!SaveManager.getInstance().getSave(3).isEmpty()) save4.setOnAction(e ->{
            SceneManager.getInstance().setWhichSave(3);
            SceneManager.getInstance().changeScene(ChooseLevel);
        });
        save4.setLayoutX(220.0);
        save4.setLayoutY(360.0);
        save4.setPrefSize(100.0,60.0);

        save5 = new Button(SaveManager.getInstance().getSave(4).getNameOfOurUser());
        if(!SaveManager.getInstance().getSave(4).isEmpty()) save5.setOnAction(e ->{
            SceneManager.getInstance().setWhichSave(4);
            SceneManager.getInstance().changeScene(ChooseLevel);
        });
        save5.setLayoutX(220.0);
        save5.setLayoutY(430.0);
        save5.setPrefSize(100.0,60.0);

        save6 = new Button(SaveManager.getInstance().getSave(5).getNameOfOurUser());
        if(!SaveManager.getInstance().getSave(5).isEmpty()) save6.setOnAction(e ->{
            SceneManager.getInstance().setWhichSave(5);
            SceneManager.getInstance().changeScene(ChooseLevel);
        });
        save6.setLayoutX(220.0);
        save6.setLayoutY(500.0);
        save6.setPrefSize(100.0,60.0);

        deleteLabel= new Label("If you want to delete\n any of the saves\n choose here");
        deleteLabel.setFont(new Font("Bauhaus 93",20.0));
        deleteLabel.setLayoutX(400.0);
        deleteLabel.setLayoutY(300.0);

        ChoiceBox deleteChooser = new ChoiceBox();
        deleteChooser.setLayoutX(400);
        deleteChooser.setLayoutY(400.0);
        deleteChooser.setPrefWidth(100.0);
        deleteChooser.setItems(FXCollections.observableArrayList(SaveManager.getInstance().getSave(0).getNameOfOurUser(),
                SaveManager.getInstance().getSave(1).getNameOfOurUser(), SaveManager.getInstance().getSave(2).getNameOfOurUser(),
                SaveManager.getInstance().getSave(3).getNameOfOurUser(),SaveManager.getInstance().getSave(4).getNameOfOurUser(),
                SaveManager.getInstance().getSave(5).getNameOfOurUser()));
        deleteChooser.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                toDelete=newValue.intValue();
            }
        });

        deleteButton = new Button("DELETE");
        deleteButton.setOnMousePressed(e ->{
            if(toDelete<6){
                SaveManager.getInstance().deleteSave(toDelete);
                SceneManager.getInstance().changeScene(ChooseSave);
            }
            toDelete=6;
        });
        deleteButton.setLayoutX(400.0);
        deleteButton.setLayoutY(500.0);
        deleteButton.setPrefSize(100.0,60.0);

        myPane.getChildren().addAll(myLabel,save1,save2,save3,save4,save5,save6,deleteLabel,deleteChooser,deleteButton);

    }
}
