package sample;

import javafx.animation.AnimationTimer;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.*;
import javafx.scene.layout.Pane;
import static sample.Collidable.typeOfCollision.*;



public class Player implements Collidable {
    private final Rectangle2D imageCells[]=new Rectangle2D[11]; //tablica z animacja bohatera
    private double width;
    private double height;
    private ImageView viewOfMyPlayer=new ImageView();
    private final int numberofFrames=11;
    private Point2D velocityLevel = new Point2D(0,0); //predkosc z perspektywy poziomu
    private Point2D velocityView = new Point2D(0,0); //predkosc z perspektywy widoku
    private double xLevel;
    private double yLevel;
   private double xView;
    private double yView;
    private boolean jumping;
    private int jumpingFrameIndex;
    private int timeFrame;
    private int cellFrame;//zeby zawsze chodzil tak samo
    private boolean inAir; //do skokow

    public Player()
    {
        //Image image = new Image(this.getClass().getClassLoader().getResourceAsStream("pelnaanimacja.png"));
        //viewOfMyPlayer=new ImageView(image);
        //xLevel=0d;
        xLevel=0;
        //y=440; //wspolrzedne, zeby stal na ziemi
        yLevel=300;
        /*cellWidth= 20;
        cellHeight= 34;
        xView=xLevel;
        yView=yLevel;
        //inincjalizujemy tablice klatek, z powodu roznych wielkosci obrazkow, wpisuje po kolei
        imageCells[0] = new Rectangle2D(cellWidth,0,cellWidth,cellHeight);
        imageCells[1]= new Rectangle2D(cellWidth*2,0,cellWidth,cellHeight);
        imageCells[2]= new Rectangle2D(cellWidth*3,0,cellWidth,cellHeight);
        imageCells[3]= new Rectangle2D(cellWidth*4,0,cellWidth,cellHeight);
        imageCells[4]= new Rectangle2D(cellWidth,cellHeight,cellWidth-1,cellHeight-9);
        imageCells[5]= new Rectangle2D(cellWidth*2,cellHeight,cellWidth,cellHeight);
        imageCells[6]= new Rectangle2D(cellWidth*3,cellHeight,cellWidth-1,cellHeight);
        imageCells[7]= new Rectangle2D(cellWidth*4,cellHeight,cellWidth,cellHeight);

        //klatki do skoku

        viewOfMyPlayer.setViewport(imageCells[0]);
        viewOfMyPlayer.setImage(image);
        viewOfMyPlayer.setFitWidth(60.0);
        viewOfMyPlayer.setFitHeight(105.0); */
        width=60.0;
        height=105.0;
        jumping=false;
        jumpingFrameIndex=0;
        //myTimer.start();
        timeFrame=0;
        cellFrame=0;
    }

   /* public ImageView getViewOfMyPlayer() {
        return viewOfMyPlayer;
    } */

    public void setX (double newX)
    {
        xLevel=newX;
        //viewOfMyPlayer.setX(xLevel);

    }

    public void setY(double newY)
    {
        yLevel=newY;
        //viewOfMyPlayer.setY(yLevel);

    }

    public double getX() {
        return xLevel;
    }

    public double getY() {
        return yLevel;
    }

    @Override
    public double getWidth() {
        return width;
        //return viewOfMyPlayer.getFitWidth();
    }

    @Override
    public double getHeight() {
        return height;
        //return viewOfMyPlayer.getFitHeight();
    }

    public void setVelocity(double xx, double yy)
    {
        velocityLevel=new Point2D(xx,yy);
    }

    public Point2D getVelocity()
    {
        return velocityLevel;
    }

    public boolean isJumping() {
        return jumping;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    public void jump ()
    {
        if(jumpingFrameIndex==0) {
            setVelocity(getVelocity().getX()+0,getVelocity().getY()+(-4)); setInAir(true);
            //setVelocityView(new Point2D(getVelocityView().getX()+0,getVelocityView().getY()+(-4)));
        }
        if(jumpingFrameIndex!=40) ++jumpingFrameIndex;
        else {
            setJumping(false);
            System.out.println(xLevel+" "+yLevel+" "+jumpingFrameIndex);
            jumpingFrameIndex=0;
        }
    }

    public typeOfCollision isColliding(Collidable other) { // to jest spoko tego nie zmieniaj
        if(other.getClass()==Enemy.class) return isCollidingEnemy(other);
        if (xLevel + getWidth() >= other.getX() && xLevel <= other.getX() + other.getWidth())  //sprawdzamy czy x
        {
            if (yLevel + getHeight() + 3 >= other.getY() && yLevel + getHeight() - 3 < other.getY()) return UP;
            else if (yLevel + 3 >= other.getY() + other.getHeight() && yLevel - 3 <= other.getY() + other.getHeight())
                return DOWN;
            else if (yLevel >= other.getY() && yLevel + getHeight() - 1 <= other.getY() + other.getHeight()) return SIDE;
        }
        return NO;

    }

    private typeOfCollision isCollidingEnemy(Collidable other) {

            if (yLevel + getHeight() + 3 >= other.getY() && yLevel + getHeight() - 3 < other.getY()) {
                System.out.println("o tu");
                if (xLevel + getWidth() >= other.getX() && xLevel + getWidth() <= other.getX() + other.getWidth()) return UP;
                if (xLevel <= other.getX() && xLevel + getWidth() >= other.getX() + other.getWidth() )return UP; //kiedy jestesmy znacznie wieksi od przeciwnika przyklad      ++++++ - my
                                                                                                        //                                                             ++   - przeciwnik
                if (xLevel >= other.getX() && xLevel <= other.getX() + other.getWidth()) return UP;

            }
            /*else if (y + 3 >= other.getY() + other.getHeight() && y - 3 <= other.getY() + other.getHeight())
                return DOWN; */

            else if (xLevel + getWidth() >= other.getX() && xLevel + getWidth() <= other.getX() + other.getWidth()){
                if (yLevel <= other.getY() && yLevel + getHeight() >= other.getY() + other.getHeight()) return SIDE;
                else if (yLevel >= other.getY() && yLevel + getHeight() <= other.getY() + other.getHeight()) return SIDE;
            }
        return NO;

    }

    public void update(boolean colliding,boolean moving)//obsluguje skoki, ruch, zmiane animacji, to mega trzeba zmienic
    {
        if(velocityLevel.getY()!=0 && !jumping && colliding) {
            setVelocity(velocityLevel.getX(),0);
            //setVelocityView(new Point2D(velocityView.getX(),0));
            setJumping(false);
        }
        xLevel+=velocityLevel.getX();
        yLevel+=velocityLevel.getY();
        /*xView+=velocityView.getX();
        yView+=velocityView.getY();
        viewOfMyPlayer.setTranslateX(xView);
        viewOfMyPlayer.setTranslateY(yView); */

        if(isJumping())
        {
            jump();
        }
        //setAnimation(moving);
    }
    public void setJumpingFrameIndex(int i)
    {
        jumpingFrameIndex=i;
    }

    /*public Rectangle2D getImageCells(int i) {
        return imageCells[i];
    } */

    public boolean isInAir() {
        return inAir;
    }

    public void setInAir(boolean inAir) {
        this.inAir = inAir;
    }
    /*public void setAnimation(boolean moving) //set Animation tylko patrzy czy po xLevel,yLevel itd. , bo na widoku mozemy stac w miejscu, a tak naprawde idziemy
    {
        timeFrame=(++timeFrame)%10;
        cellFrame=(++cellFrame)%4;
        if(velocityLevel.getX()==0 && velocityLevel.getY()==0 && !isInAir() &&!moving) viewOfMyPlayer.setViewport(imageCells[0]); //podstawowy widok bohatera
        else if(moving&&velocityLevel.getY()==0 && !isInAir())
        {
            if(timeFrame==0)
            {
                viewOfMyPlayer.setViewport(imageCells[cellFrame]);
            }

        }
        if(isInAir()&&velocityLevel.getY()<0) viewOfMyPlayer.setViewport(imageCells[5]);
        if(isInAir()&&velocityLevel.getY()==0) viewOfMyPlayer.setViewport(imageCells[6]);
        if(isInAir()&&velocityLevel.getY()>0) viewOfMyPlayer.setViewport(imageCells[7]);
    } */

    public int getJumpingFrameIndex() {
        return jumpingFrameIndex;
    }

    public double getxView() {
        return xView;
    }

    public double getyView() {
        return yView;
    }

    public Point2D getVelocityView() {
        return velocityView;
    }

    public void setVelocityView(Point2D velocityView) {
        this.velocityView = velocityView;
    }

}
