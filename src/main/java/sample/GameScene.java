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

import static sample.Collidable.typeOfCollision.*;

public class GameScene extends Scene {
    private Player myPlayer;
    private AnchorPane myPane;
    private ImageView background;
    private List<Enemy> enemies = new ArrayList<Enemy>();
    private List<Platform> platforms = new ArrayList<Platform>();
    private EventHandler<KeyEvent> myPressedKeyHandler;
    private AnimationTimer collisionTimer = new AnimationTimer() { //timer obslugujacy kolizje oraz ruch bohatera
        @Override
        public void handle(long now) {
            boolean collidingwithPlatform=false;
            Collidable.typeOfCollision x;
           for(int i=0;i<platforms.size();++i) //sprawdzamy czy nasz gracz koliduja z jakakolwiek platforma
            {
                if((x=myPlayer.isColliding(platforms.get(i)))!=NO)
                {
                    System.out.println(myPlayer.getX()+" "+myPlayer.getY());
                    myPlayer.setVelocity(myPlayer.getVelocity().getX(),0);
                    myPlayer.setJumping(false);
                    myPlayer.setJumpingFrameIndex(0);
                    collidingwithPlatform=true;
                }
            }
            if(!collidingwithPlatform && myPlayer.getY()<440 && !myPlayer.isJumping()) myPlayer.setVelocity(myPlayer.getVelocity().getX(),myPlayer.getVelocity().getY()+1);
            if(!collidingwithPlatform && myPlayer.getY()>=440 && !myPlayer.isJumping()) collidingwithPlatform=true;
            myPlayer.update(collidingwithPlatform);
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
        enemies.add(new Enemy());
        myPane.getChildren().add(platforms.get(0).getViewOfMyPlatform());
        myPane.getChildren().add(enemies.get(0).getViewOfMyEnemy());
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