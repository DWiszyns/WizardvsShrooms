package sample;

import javafx.animation.AnimationTimer;
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

import javax.management.timer.TimerNotification;
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
        Image image = new Image("sample/Full-Background.png");
        background = new ImageView(image);
        background.setFitHeight(600);
        background.setFitWidth(600);
        myPane.getChildren().add(background);
        myPlayer=new Player();
        myPlayer.getViewOfMyPlayer().setTranslateY(myPlayer.getY());//ustawiamy wspolrzedne bohatera
        myPlayer.getViewOfMyPlayer().setTranslateX(myPlayer.getX());
        myPane.getChildren().add(myPlayer.getViewOfMyPlayer());
        //myPane.setOnKeyPressed(myPressedKeyHandler);
        //jego definicja
        myPressedKeyHandler= event -> {
            if(event.getEventType()==KeyEvent.KEY_PRESSED) {
                if (event.getCode() == KeyCode.D) {
                    //myPlayer.setX(myPlayer.getX() + 1.0);
                    //SceneManager.getInstance().changeScene(SceneManager.currScene.Game);
                    myPlayer.setVelocity(1,0);
                }
                if (event.getCode() == KeyCode.A) {
                    //myPlayer.setX(myPlayer.getX() - 1.0);
                    myPlayer.setVelocity(-1,0);
                    //SceneManager.getInstance().changeScene(SceneManager.currScene.Game);d
                }
                if(event.getCode()==KeyCode.SPACE && !myPlayer.isJumping())
                {
                    myPlayer.setJumping(true);
                   // myPlayer.jumpTimer.start();
                    myPlayer.jump();
                }
            }
            if(event.getEventType()==KeyEvent.KEY_RELEASED) {
                if (event.getCode() == KeyCode.D) {
                    //myPlayer.setX(myPlayer.getX() + 1.0);
                    //SceneManager.getInstance().changeScene(SceneManager.currScene.Game);
                    myPlayer.setVelocity(0,0);
                }
                if (event.getCode() == KeyCode.A) {
                    //myPlayer.setX(myPlayer.getX() - 1.0);
                    myPlayer.setVelocity(0,0);
                    //SceneManager.getInstance().changeScene(SceneManager.currScene.Game);
                }
            }

        };
        this.setEventHandler(KeyEvent.ANY,myPressedKeyHandler); //moj EventHandler

    }

    public Player getMyPlayer()
    {
        return getMyPlayer();
    }

    public AnchorPane startTheGame()
    {
        return myPane;
    }


}