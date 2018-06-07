package sample;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;


public class InstructionsView extends Scene {
    private AnchorPane myPane;
    private Label moveRight;
    private Label moveLeft;
    private Label jumpLabel;
    private Label myLabel;
    private Label mainGoal;

    public InstructionsView() {
        super(new AnchorPane());
        myPane = (AnchorPane) this.getRoot();
        myPane.setPrefSize(600, 600);
        myPane.setBlendMode(BlendMode.EXCLUSION);
        myPane.setStyle("-fx-backgorund-color: #FFFFFF;");

        myLabel = new Label("Instructions");
        myLabel.setFont(new Font("Bauhaus 93", 31.0));
        myLabel.setLayoutX(205.0);
        myLabel.setLayoutY(135.0);

        moveRight = new Label("D - Move right");
        moveRight.setLayoutX(230.0);
        moveRight.setLayoutY(205.0);
        moveRight.setPrefHeight(25.0);
        moveRight.setFont(new Font("System 12px",16.0));


        moveLeft = new Label("A - Move left");
        moveLeft.setLayoutX(230.0);
        moveLeft.setLayoutY(245.0);
        moveLeft.setPrefHeight(25.0);
        moveLeft.setFont(new Font("System 12px",16.0));



        jumpLabel = new Label("SPACE - Jump");
        jumpLabel.setLayoutX(230.0);
        jumpLabel.setLayoutY(285.0);
        jumpLabel.setFont(new Font("System 12px",16.0));


        mainGoal = new Label("Your main goal of the game is to kill\n all of the mushrooms that you'll see.\n " +
                "You'll do that by jumping on them. BE CAREFUL!\n If you touch them from the side you'll die. \n" +
                "Also remember that in this universe gravity exists,\n so don't fall off the platforms.\n" +
                "The only way to pass the level is to kill\n all of your enemies and go as far right\n as you can in the area. " +
                "Have fun!");
        mainGoal.setFont(new Font("System 12px",16.0));
        mainGoal.setLayoutX(150.0);
        mainGoal.setLayoutY(325.0);

        myPane.getChildren().addAll(myLabel, moveRight, moveLeft, jumpLabel, mainGoal);
    }
}