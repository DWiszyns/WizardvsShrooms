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
    private List<Platform> platforms = new ArrayList<Platform>();
    private EventHandler<KeyEvent> myPressedKeyHandler;
    private AnimationTimer collisionTimer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            boolean colliding=false;
            for(int i=0;i<platforms.size();++i)
            {
                if(myPlayer.isColliding(platforms.get(i)))
                {
                    System.out.println("KOLIDUJE");
                    myPlayer.setVelocity(myPlayer.getVelocity().getX(),0);
                    myPlayer.setJumping(false);
                    myPlayer.setJumpingFrameIndex(0);
                    colliding=true;
                }
            }
            if(!colliding && myPlayer.getY()<440 && !myPlayer.isJumping()) myPlayer.setVelocity(myPlayer.getVelocity().getX(),myPlayer.getVelocity().getY()+1);
            if(!colliding && myPlayer.getY()>=440 && !myPlayer.isJumping()) colliding=true;
            myPlayer.update(colliding);
        }
    };

    public GameScene()
    {
        super(new AnchorPane());
        myPane = (AnchorPane) getRoot();
        myPane.setPrefSize(600,600);
        Image image = new Image(this.getClass().getClassLoader().getResourceAsStream("Full-Background.png"));
        background = new ImageView(image);
        background.setFitHeight(600);
        background.setFitWidth(600);
        myPane.getChildren().add(background);
        myPlayer=new Player();
        myPlayer.getViewOfMyPlayer().setTranslateY(myPlayer.getY());//ustawiamy wspolrzedne bohatera
        myPlayer.getViewOfMyPlayer().setTranslateX(myPlayer.getX());
        myPane.getChildren().add(myPlayer.getViewOfMyPlayer());
        platforms.add(new Platform());
        myPane.getChildren().add(platforms.get(0).getViewOfMyPlatform());
        //myPane.setOnKeyPressed(myPressedKeyHandler);
        //jego definicja
        myPressedKeyHandler= event -> {
            if(event.getEventType()==KeyEvent.KEY_PRESSED) {
                if (event.getCode() == KeyCode.D) {
                    //myPlayer.setX(myPlayer.getX() + 1.0);
                    myPlayer.setVelocity(1,0); //ustawiamy ruch w lewo
                }
                if (event.getCode() == KeyCode.A) {
                    //myPlayer.setX(myPlayer.getX() - 1.0);
                    myPlayer.setVelocity(-1,0);//ustawiamy ruch w prawo
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
                    myPlayer.getViewOfMyPlayer().setViewport(myPlayer.getImageCells(0));
                }
                if (event.getCode() == KeyCode.A) {
                    //myPlayer.setX(myPlayer.getX() - 1.0);
                    myPlayer.setVelocity(0,0);
                    myPlayer.getViewOfMyPlayer().setViewport(myPlayer.getImageCells(0));
                    //SceneManager.getInstance().changeScene(SceneManager.currScene.Game);
                }
            }

        };
        this.setEventHandler(KeyEvent.ANY,myPressedKeyHandler); //moj EventHandler
        collisionTimer.start();

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