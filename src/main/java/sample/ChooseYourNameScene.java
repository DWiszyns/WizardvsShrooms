package sample;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import static sample.SceneManager.currScene.ChooseLevelView1;

public class ChooseYourNameScene extends Scene {
    private AnchorPane myPane;
    private TextField nameOfSaveInput;
    private Label myLabel;
    private Label errorLabel;
    private Button execButton;
    ChooseYourNameScene(){
        super(new AnchorPane());
        myPane =(AnchorPane) this.getRoot();
        myPane.setPrefSize(600,600);
        myPane.setBlendMode(BlendMode.EXCLUSION);
        myPane.setStyle("-fx-backgorund-color: #FFFFFF;");

        nameOfSaveInput= new TextField("My name");
        nameOfSaveInput.setLayoutY(200.0);
        nameOfSaveInput.setLayoutX(220.0);
        nameOfSaveInput.setMaxWidth(200);
        nameOfSaveInput.setMaxHeight(60);

        execButton = new Button("Create");
        execButton.setOnAction((ActionEvent e) ->
        {
            SceneManager.getInstance().changeScene(ChooseLevelView1);
        });
        execButton.setLayoutX(240.0);
        execButton.setLayoutY(300.0);
        execButton.setPrefSize(100.0,60.0);

        myLabel= new Label("Enter your desired name");
        myLabel.setFont(new Font("Bauhaus 93",31.0));
        myLabel.setLayoutX(135.0);
        myLabel.setLayoutY(135.0);

        errorLabel= new Label("Error no space for new save");
        errorLabel.setFont(new Font("Bauhaus 93",31.0));
        errorLabel.setLayoutX(40.0);
        errorLabel.setLayoutY(400.0);
        errorLabel.setFont(new Font("AclonicaFont",14));
        errorLabel.setTextFill(Color.web("#FF4500"));

        execButton = new Button("Create");
        execButton.setOnAction((ActionEvent e) ->
        {
            int x;
            if((x=SaveManager.getInstance().findFreeSave())==6){
                myPane.getChildren().add(errorLabel);
            }
            else { //if there is any free we create save and start the game
                SaveManager.getInstance().createSave(nameOfSaveInput.getText(),x);
                SceneManager.getInstance().changeScene(ChooseLevelView1);
            }
        });
        execButton.setLayoutX(240.0);
        execButton.setLayoutY(300.0);
        execButton.setPrefSize(100.0,60.0);

        myPane.getChildren().addAll(nameOfSaveInput,execButton,myLabel);
    }
}
