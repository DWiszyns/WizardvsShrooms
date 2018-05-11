package sample;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class GameScene extends Scene {
    private Player myPlayer;
    private AnchorPane myPane;
    private ImageView background;
    private List<Enemy> enemies = new ArrayList<Enemy>();
    private EventHandler<KeyEvent> myPressedKeyHandler;

    public GameScene()
    {
        super(new AnchorPane());
        myPane = (AnchorPane) getRoot();
        myPane.setPrefSize(600,600);
        myPlayer= new Player();
        Image image = new Image("sample/Full-Background.png");
        background = new ImageView(image);
        background.setFitHeight(600);
        background.setFitWidth(600);
        myPane.getChildren().add(background);
        myPlayer=new Player();
        myPlayer.getViewOfMyPlayer().setTranslateY(myPlayer.getY());//ustawiamy wspolrzedne bohatera
        myPlayer.getViewOfMyPlayer().setTranslateX(myPlayer.getX());
        myPane.getChildren().add(myPlayer.getViewOfMyPlayer());
        this.setEventHandler(KeyEvent.KEY_PRESSED,myPressedKeyHandler); //moj EventHandler
        //jego definicja
        myPressedKeyHandler= event -> {
            if(event.getCode()== KeyCode.D)
            {
                myPlayer.setX(myPlayer.getX()+1.0);
                System.out.println("Czemu nie dziala?");
                //SceneManager.getInstance().changeScene(SceneManager.currScene.Game);
            }
        };

    }

    public AnchorPane startTheGame()
    {
        return myPane;
    }


}