package sample;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.exit;
import static sample.Collidable.typeOfCollision.*;

public class GameScene extends Scene {
    private AnchorPane myPane;
    private Level myLevel;
    private PlayerView myPlayerView;
    private ImageView background;
    private List<EnemyView> enemies = new ArrayList<EnemyView>();
    private List<PlatformView> platformViews = new ArrayList<PlatformView>();
    private Rectangle2D cameraView;
    private EventHandler<KeyEvent> myPressedKeyHandler;
    private Point2D cameraViewVelocity = new Point2D(0,0);
    private final Point2D cameraViewSize = new Point2D(615,308);
    private FlagView flagView;
    private boolean moving = false;
    private int whichLevel;
    private int whichSave;
    private AnimationTimer collisionTimer = new AnimationTimer() { //timer obslugujacy kolizje oraz ruch bohatera
        @Override
        public void handle(long now) { //
            boolean collidingwithPlatform = false;
            Collidable.typeOfCollision x;
            // we need to make sure that we don't leave the area that we can see
            if (moving) {
                if (myPlayerView.getPlayer().getVelocity().getX() > 0) { //here we check if our Camera doesn't go too far
                    if (myPlayerView.getPlayer().getX() <= 300.0 || cameraView.getMinX() >= 1229) {
                        myPlayerView.setVelocityView(new Point2D(1, myPlayerView.getVelocityView().getY())); //ustawiamy ruch w lewo
                        cameraViewVelocity = new Point2D(0.0, cameraViewVelocity.getY());
                    } else {
                        myPlayerView.setVelocityView(new Point2D(0, myPlayerView.getVelocityView().getY()));
                        cameraViewVelocity = new Point2D(1.0, cameraViewVelocity.getY());
                    }
                    if(myPlayerView.getPlayer().getX()+myPlayerView.getPlayer().getWidth()>=1844){ // we have to check that we don't go too far
                        myPlayerView.getPlayer().setVelocity(0,myPlayerView.getPlayer().getVelocity().getY());
                        myPlayerView.setVelocityView(new Point2D(0, myPlayerView.getVelocityView().getY())); //ustawiamy ruch w lewo
                        cameraViewVelocity = new Point2D(0.0, cameraViewVelocity.getY());
                    }
                }
                else if(myPlayerView.getPlayer().getVelocity().getX()<0){
                    if (myPlayerView.getPlayer().getX() >= 1529.0 || cameraView.getMinX()+cameraViewSize.getX() <= 615) {
                        myPlayerView.setVelocityView(new Point2D(-1, myPlayerView.getVelocityView().getY())); //ustawiamy ruch w lewo
                        cameraViewVelocity = new Point2D(0.0, cameraViewVelocity.getY());
                    } else {
                        myPlayerView.setVelocityView(new Point2D(0, myPlayerView.getVelocityView().getY()));
                        cameraViewVelocity = new Point2D(-1.0, cameraViewVelocity.getY());
                    }
                    if(myPlayerView.getPlayer().getX()<=0){ // we have to check that we don't go too far
                        myPlayerView.getPlayer().setVelocity(0,myPlayerView.getPlayer().getVelocity().getY());
                        myPlayerView.setVelocityView(new Point2D(0, myPlayerView.getVelocityView().getY())); //ustawiamy ruch w lewo
                        cameraViewVelocity = new Point2D(0.0, cameraViewVelocity.getY());
                    }
                }
            }
                if (myPlayerView.getPlayer().getJumpingFrameIndex() != 1)
                    for (PlatformView platform : platformViews) { //if he didnt just start jumping check if he is colliding with platform
                        platform.update(cameraViewVelocity.getX());
                        if (!collidingwithPlatform && (x = myPlayerView.getPlayer().isColliding(platform.getPlatform())) == UP) {
                            if (myPlayerView.getPlayer().isInAir()) {
                                myPlayerView.getPlayer().setVelocity(myPlayerView.getPlayer().getVelocity().getX(), 0); //jezeli spada na platforme to
                                myPlayerView.setVelocityView(new Point2D(myPlayerView.getVelocityView().getX(), 0));
                            }
                            // }
                            myPlayerView.getPlayer().setJumping(false);
                            myPlayerView.getPlayer().setInAir(false);
                            myPlayerView.getPlayer().setJumpingFrameIndex(0); //he ain't jumping anymore
                            collidingwithPlatform = true;
                        }
                    }
                for (int i = 0; i < enemies.size(); ++i) {
                    enemies.get(i).update(cameraViewVelocity.getX(),myPlayerView.getPlayer()); //we're updating their position on our view
                    if ((x = myPlayerView.getPlayer().isColliding(enemies.get(i).getEnemy())) == UP) {
                        myPane.getChildren().remove(enemies.get(i).getViewOfMyEnemy());
                        enemies.remove(i);
                    } else if (x == SIDE) {
                        SceneManager.getInstance().changeScene(SceneManager.currScene.Lose);
                    }
                }
                if((myPlayerView.getyView()+myPlayerView.getPlayer().getHeight())>=600) SceneManager.getInstance().changeScene(SceneManager.currScene.Lose); //if we fall under our map we lose
                flagView.update(cameraViewVelocity.getX()); //we update our flag
                if (((x = myPlayerView.getPlayer().isColliding(flagView.getFlag())) != NO) && enemies.isEmpty()) {
                    //we check whether we passed the last available number or not
                    int newNumberOfAvaialableLevels = whichLevel == SaveManager.getInstance().getSave(whichSave).getHowManyLevelsAvailable() ? whichLevel + 1 : SaveManager.getInstance().getSave(whichSave).getHowManyLevelsAvailable();
                    SaveManager.getInstance().getSave(whichSave).saveData(SaveManager.getInstance().getSave(whichSave).getNameOfOurUser(), newNumberOfAvaialableLevels); //here we save that we passed this level
                    SceneManager.getInstance().changeScene(SceneManager.currScene.Win);

                }
                if (!collidingwithPlatform) {
                    myPlayerView.getPlayer().setVelocity(myPlayerView.getPlayer().getVelocity().getX(), myPlayerView.getPlayer().getVelocity().getY() + 0.2); //pseudograwitacja
                    myPlayerView.setVelocityView(new Point2D(myPlayerView.getVelocityView().getX(), myPlayerView.getVelocityView().getY() + 0.2));
                    myPlayerView.getPlayer().setInAir(true);
                }
                myPlayerView.update(collidingwithPlatform, moving);
                cameraView = new Rectangle2D(cameraView.getMinX() + cameraViewVelocity.getX(), cameraView.getMinY(), cameraView.getWidth(), cameraView.getHeight());
                background.setViewport(cameraView);
                if((myPlayerView.getyView()+myPlayerView.getPlayer().getHeight())>=600) SceneManager.getInstance().changeScene(SceneManager.currScene.Lose); //if we fall under our map we lose
                if (SceneManager.getInstance().getStage().getScene().getClass() != GameScene.class) collisionTimer.stop(); //we need to turn off our scene if we're doing something else
                //we don't want to have 100 scenes opened at the same time

            }
        };



    public GameScene(int whichLevel,int whichSave)
    {
        super(new AnchorPane());
        myPane = (AnchorPane) getRoot();
        myPane.setPrefSize(600,600);
        myLevel=new Level(whichLevel);
        this.whichLevel = whichLevel;
        this.whichSave=whichSave;

        cameraView=new Rectangle2D(cameraViewVelocity.getX(), cameraViewVelocity.getY(),cameraViewSize.getX(),cameraViewSize.getY());
        background = myLevel.getBackground();
        background.setViewport(cameraView);
        background.setFitHeight(600);
        background.setFitWidth(600);
        myPane.getChildren().add(background);

        myPlayerView= new PlayerView(new Player());
        myPlayerView.getViewOfMyPlayer().setTranslateY(myPlayerView.getyView());//ustawiamy wspolrzedne bohatera
        myPlayerView.getViewOfMyPlayer().setTranslateX(myPlayerView.getxView());
        myPane.getChildren().add(myPlayerView.getViewOfMyPlayer());

        for(Platform platform:myLevel.getPlatforms()){
            platformViews.add(new PlatformView(platform));
        }

        for(Enemy enemy: myLevel.getEnemies()){
            enemies.add(new EnemyView(enemy));
        }

        flagView = new FlagView(myLevel.getFlag());
        flagView.getViewOfMyFlag().setTranslateX(flagView.getxView());
        flagView.getViewOfMyFlag().setTranslateY(flagView.getyView());
        myPane.getChildren().add(flagView.getViewOfMyFlag());

        for(PlatformView platform: platformViews) myPane.getChildren().add(platform.getViewOfMyPlatform());
        for(EnemyView enemy: enemies) myPane.getChildren().add(enemy.getViewOfMyEnemy());
        //jego definicja
        myPressedKeyHandler= event -> { //popraw, zebys normalnie chodzil, moze ustawianie cameraViewVelocity powinno byc kontrolowane w timerze?
            if(event.getEventType()==KeyEvent.KEY_PRESSED) {
                if (event.getCode() == KeyCode.D) {
                    moving = true;
                    myPlayerView.getPlayer().setVelocity(1,myPlayerView.getPlayer().getVelocity().getY());
                    if(myPlayerView.getPlayer().getX()<=300.0|| myPlayerView.getPlayer().getX()>=1550) myPlayerView.setVelocityView(new Point2D(1,myPlayerView.getVelocityView().getY())); //ustawiamy ruch w lewo
                    else {
                        myPlayerView.setVelocityView(new Point2D(0,myPlayerView.getVelocityView().getY()));
                        cameraViewVelocity=new Point2D(1.0,cameraViewVelocity.getY());
                    }
                }
                if (event.getCode() == KeyCode.A) {
                    moving = true;
                    myPlayerView.getPlayer().setVelocity(-1,myPlayerView.getPlayer().getVelocity().getY());//ustawiamy ruch w prawo
                    myPlayerView.setVelocityView(new Point2D(-1,myPlayerView.getVelocityView().getY()));
                }
                if(event.getCode()==KeyCode.SPACE && !myPlayerView.getPlayer().isJumping()) //zapobiegamy wieloskokom
                {
                    myPlayerView.getPlayer().setJumping(true);
                    myPlayerView.getPlayer().jump();
                }
            }
            if(event.getEventType()==KeyEvent.KEY_RELEASED) {
                if (event.getCode() == KeyCode.D) {
                    moving = false;
                    myPlayerView.getPlayer().setVelocity(0,myPlayerView.getPlayer().getVelocity().getY());
                    myPlayerView.setVelocityView(new Point2D(0,myPlayerView.getVelocityView().getY()));
                    myPlayerView.getViewOfMyPlayer().setViewport(myPlayerView.getImageCells(0));
                    cameraViewVelocity=new Point2D(0.0,cameraViewVelocity.getY());

                }
                if (event.getCode() == KeyCode.A) {
                    moving = false;
                    myPlayerView.getPlayer().setVelocity(0,myPlayerView.getPlayer().getVelocity().getY());
                    myPlayerView.setVelocityView(new Point2D(0,myPlayerView.getVelocityView().getY()));
                    myPlayerView.getViewOfMyPlayer().setViewport(myPlayerView.getImageCells(0));
                    cameraViewVelocity=new Point2D(0.0,cameraViewVelocity.getY());
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



}