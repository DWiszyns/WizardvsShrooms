package sample;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;

import javax.management.timer.TimerNotification;
import java.io.FileInputStream;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.exit;
import static sample.Collidable.typeOfCollision.*;

public class GameScene extends Scene {
    private Player myPlayer;
    private AnchorPane myPane;
    private ImageView background;
    private List<Enemy> enemies = new ArrayList<Enemy>();
    private List<Platform> platforms = new ArrayList<Platform>();
    private Rectangle2D cameraView;
    private EventHandler<KeyEvent> myPressedKeyHandler;
    private Point2D cameraViewVelocity = new Point2D(0,0);
    private final Point2D cameraViewSize = new Point2D(615,308);
    private boolean moving = false;
    private AnimationTimer collisionTimer = new AnimationTimer() { //timer obslugujacy kolizje oraz ruch bohatera
        @Override
        public void handle(long now) { //to tez mega trzeba zmienic, bo dziala za szybko, najpierw wychodzisz poza wszystko potem zmienia sie widok
            boolean collidingwithPlatform=false;
            Collidable.typeOfCollision x;
           if(myPlayer.getJumpingFrameIndex()!=1) for (Platform platform : platforms) {
               platform.update(cameraViewVelocity.getX());
              // if(i==1) System.out.println("Sprawdzone");
               if (!collidingwithPlatform && (x = myPlayer.isColliding(platform)) == UP) {
                   if (myPlayer.isInAir()) {
                       myPlayer.setVelocity(myPlayer.getVelocity().getX(), 0); //jezeli spada na platforme to
                       myPlayer.setVelocityView(new Point2D(myPlayer.getVelocityView().getX(), 0));
                       System.out.println("koliduje z"+ platform.getxView());
                   }
                       // }
                   myPlayer.setJumping(false);
                   myPlayer.setInAir(false);
                   myPlayer.setJumpingFrameIndex(0);
                   collidingwithPlatform = true;
                   System.out.println(myPlayer.getX()+" "+myPlayer.getY()+" "+platform.getX()+" "+(platform.getX()+platform.getWidth())+" "+platform.getHeight());
               }
           }
           for(int i=0;i<enemies.size();++i)
           {
               enemies.get(i).update(cameraViewVelocity.getX());
               if ((x = myPlayer.isColliding(enemies.get(i))) == UP) {
                   System.out.println("chociaz raz");
                   myPane.getChildren().remove(enemies.get(i).getViewOfMyEnemy());
                   enemies.remove(i);
               }
                else if(x == SIDE){
                   exit(0);
               }
              // System.out.println(x);
           }
           //System.out.println(collidingwithPlatform);
            //System.out.println(myPlayer.getX()+" "+(myPlayer.getY()+myPlayer.getHeight()));
            if(!collidingwithPlatform)
            {
               // if(myPlayer.getJumpingFrameIndex()!=1) System.out.println(x);
                myPlayer.setVelocity(myPlayer.getVelocity().getX(),myPlayer.getVelocity().getY()+0.2); //pseudograwitacja
                myPlayer.setVelocityView(new Point2D(myPlayer.getVelocityView().getX(),myPlayer.getVelocityView().getY()+0.2));
                myPlayer.setInAir(true);
            }
            //if(!collidingwithPlatform && myPlayer.getY()>=440 && !myPlayer.isJumping()) collidingwithPlatform=true;
            myPlayer.update(collidingwithPlatform,moving);
           cameraView= new Rectangle2D(cameraView.getMinX()+cameraViewVelocity.getX(),cameraView.getMinY(),cameraView.getWidth(),cameraView.getHeight());
           background.setViewport(cameraView);

        }
    };

    public GameScene()
    {
        super(new AnchorPane());
        myPane = (AnchorPane) getRoot();
        myPane.setPrefSize(600,600);
        Image image = new Image(this.getClass().getClassLoader().getResourceAsStream("new_background.png"));
        cameraView=new Rectangle2D(cameraViewVelocity.getX(), cameraViewVelocity.getY(),cameraViewSize.getX(),cameraViewSize.getY());
        background = new ImageView(image);
        background.setViewport(cameraView);
        background.setFitHeight(600);
        background.setFitWidth(600);
        myPane.getChildren().add(background);
        myPlayer=new Player();
        myPlayer.getViewOfMyPlayer().setTranslateY(myPlayer.getyView());//ustawiamy wspolrzedne bohatera
        myPlayer.getViewOfMyPlayer().setTranslateX(myPlayer.getxView());
        myPane.getChildren().add(myPlayer.getViewOfMyPlayer());
        platforms.add(new Platform(0,455,310,80));
        platforms.add(new Platform(300.0,520.0,400.0,60.0));
        platforms.add(new Platform(710,500.0,200,60.0)); //cos ucieka, nie wiem dlaczego tak jest
        if(platforms.size()>2) System.out.println("ZGADZA SIE");
        enemies.add(new Enemy(300,425));
        enemies.add(new Enemy(800,470));
        myPane.getChildren().add(platforms.get(0).getViewOfMyPlatform());
        myPane.getChildren().add(platforms.get(1).getViewOfMyPlatform());
        myPane.getChildren().add(platforms.get(2).getViewOfMyPlatform());
        myPane.getChildren().add(enemies.get(0).getViewOfMyEnemy());
        myPane.getChildren().add(enemies.get(1).getViewOfMyEnemy());

        //myPane.setOnKeyPressed(myPressedKeyHandler);
        //jego definicja
        myPressedKeyHandler= event -> {
            if(event.getEventType()==KeyEvent.KEY_PRESSED) {
                if (event.getCode() == KeyCode.D) {
                    moving = true;
                    myPlayer.setVelocity(1,myPlayer.getVelocity().getY());
                    if(myPlayer.getX()<=300.0) myPlayer.setVelocityView(new Point2D(1,myPlayer.getVelocityView().getY())); //ustawiamy ruch w lewo
                    else {
                        myPlayer.setVelocityView(new Point2D(0,myPlayer.getVelocityView().getY()));
                        System.out.println(cameraViewVelocity.getX()+" "+cameraViewVelocity.getY());
                        cameraViewVelocity=new Point2D(1.0,cameraViewVelocity.getY());
                    }
                }
                if (event.getCode() == KeyCode.A) {
                    moving = true;
                    myPlayer.setVelocity(-1,myPlayer.getVelocity().getY());//ustawiamy ruch w prawo
                    myPlayer.setVelocityView(new Point2D(-1,myPlayer.getVelocityView().getY()));
                }
                if(event.getCode()==KeyCode.SPACE && !myPlayer.isJumping()) //zapobiegamy wieloskokom
                {
                    myPlayer.setJumping(true);
                    myPlayer.jump();
                }
            }
            if(event.getEventType()==KeyEvent.KEY_RELEASED) {
                if (event.getCode() == KeyCode.D) {
                    moving = false;
                    myPlayer.setVelocity(0,myPlayer.getVelocity().getY());
                    myPlayer.setVelocityView(new Point2D(0,myPlayer.getVelocityView().getY()));
                    myPlayer.getViewOfMyPlayer().setViewport(myPlayer.getImageCells(0));
                    cameraViewVelocity=new Point2D(0.0,cameraViewVelocity.getY());

                }
                if (event.getCode() == KeyCode.A) {
                    moving = false;
                    myPlayer.setVelocity(0,myPlayer.getVelocityView().getY());
                    myPlayer.setVelocityView(new Point2D(0,myPlayer.getVelocityView().getY()));
                    myPlayer.getViewOfMyPlayer().setViewport(myPlayer.getImageCells(0));
                }
            }

        };
        this.setEventHandler(KeyEvent.ANY,myPressedKeyHandler); //moj EventHandler
        collisionTimer.start();

    }
    

    public AnchorPane startTheGame()
    {
        return myPane;
    }

    public void reset()
    {

    }


}