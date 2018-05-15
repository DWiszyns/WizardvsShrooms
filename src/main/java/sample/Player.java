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
    private double cellWidth;
    private double cellHeight;
    private ImageView viewOfMyPlayer=new ImageView();
    private final int numberofFrames=11;
    private Point2D velocity = new Point2D(0,0);
    private double x;
    private double y;
    private boolean jumping;
    private int jumpingFrameIndex;
    private int timeFrame;
    private int cellFrame;//zeby zawsze chodzil tak samo
    private boolean inAir; //do skokow

    public Player()
    {
        Image image = new Image(this.getClass().getClassLoader().getResourceAsStream("pelnaanimacja.png"));
        //viewOfMyPlayer=new ImageView(image);
        x=0d;
        //y=440; //wspolrzedne, zeby stal na ziemi
        y=300;
        cellWidth= 20;
        cellHeight= 34;
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
        viewOfMyPlayer.setFitHeight(105.0);
        jumping=false;
        jumpingFrameIndex=0;
        //myTimer.start();
        timeFrame=0;
        cellFrame=0;
    }

    public ImageView getViewOfMyPlayer() {
        return viewOfMyPlayer;
    }

    public void setX (double newX)
    {
        x=newX;
        viewOfMyPlayer.setX(x);

    }

    public void setY(double newY)
    {
        y=newY;
        viewOfMyPlayer.setY(y);

    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public double getWidth() {
        return viewOfMyPlayer.getFitWidth();
    }

    @Override
    public double getHeight() {
        return viewOfMyPlayer.getFitHeight();
    }

    public void setVelocity(double xx, double yy)
    {
        velocity=new Point2D(xx,yy);
    }

    public Point2D getVelocity()
    {
        return velocity;
    }

    public boolean isJumping() {
        return jumping;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    public void jump ()
    {
        if(jumpingFrameIndex==0) {setVelocity(getVelocity().getX()+0,getVelocity().getY()+(-4)); setInAir(true);}
        if(jumpingFrameIndex!=40) ++jumpingFrameIndex;
        else {
            setJumping(false);
            System.out.println(x+" "+y+" "+jumpingFrameIndex);
            jumpingFrameIndex=0;
        }
    }

    public typeOfCollision isColliding(Collidable other) { // to jest spoko tego nie zmieniaj
        if(other.getClass()==Enemy.class) return isCollidingEnemy(other);
        if (x + getWidth() >= other.getX() && x <= other.getX() + other.getWidth())  //sprawdzamy czy x
        {
            if (y + getHeight() + 3 >= other.getY() && y + getHeight() - 3 < other.getY()) return UP;
            else if (y + 3 >= other.getY() + other.getHeight() && y - 3 <= other.getY() + other.getHeight())
                return DOWN;
            else if (y >= other.getY() && y + getHeight() - 1 <= other.getY() + other.getHeight()) return SIDE;
        }
        return NO;

    }

    private typeOfCollision isCollidingEnemy(Collidable other) {

            if (y + getHeight() + 3 >= other.getY() && y + getHeight() - 3 < other.getY()) {
                System.out.println("o tu");
                if (x + getWidth() >= other.getX() && x + getWidth() <= other.getX() + other.getWidth()) return UP;
                if (x <= other.getX() && x + getWidth() >= other.getX() + other.getWidth() )return UP; //kiedy jestesmy znacznie wieksi od przeciwnika przyklad      ++++++ - my
                                                                                                        //                                                             ++   - przeciwnik
                if (x >= other.getX() && x <= other.getX() + other.getWidth()) return UP;

            }
            /*else if (y + 3 >= other.getY() + other.getHeight() && y - 3 <= other.getY() + other.getHeight())
                return DOWN; */

            else if (x + getWidth() >= other.getX() && x + getWidth() <= other.getX() + other.getWidth()){
                if (y <= other.getY() && y + getHeight() >= other.getY() + other.getHeight()) return SIDE;
                else if (y >= other.getY() && y + getHeight() <= other.getY() + other.getHeight()) return SIDE;
            }
        return NO;

    }

    public void update(boolean colliding)//obsluguje skoki, ruch, zmiane animacji, to mega trzeba zmienic
    {
        if(velocity.getY()!=0 && !jumping && colliding) {setVelocity(velocity.getX(),0); setJumping(false);}
        x+=velocity.getX();
        y+=velocity.getY();
        viewOfMyPlayer.setTranslateX(x);
        viewOfMyPlayer.setTranslateY(y);

        if(isJumping())
        {
            jump();
        }
       setAnimation();
    }
    public void setJumpingFrameIndex(int i)
    {
        jumpingFrameIndex=i;
    }

    public Rectangle2D getImageCells(int i) {
        return imageCells[i];
    }

    public boolean isInAir() {
        return inAir;
    }

    public void setInAir(boolean inAir) {
        this.inAir = inAir;
    }
    public void setAnimation()
    {
        timeFrame=(++timeFrame)%10;
        cellFrame=(++cellFrame)%4;
        if(velocity.getX()==0 && velocity.getY()==0 && !isInAir()) viewOfMyPlayer.setViewport(imageCells[0]); //podstawowy widok bohatera
        else if(velocity.getY()==0 && !isInAir())
        {
            if(timeFrame==0)
            {
                viewOfMyPlayer.setViewport(imageCells[cellFrame]);
            }

        }
        if(isInAir()&&velocity.getY()<0) viewOfMyPlayer.setViewport(imageCells[5]);
        if(isInAir()&&velocity.getY()==0) viewOfMyPlayer.setViewport(imageCells[6]);
        if(isInAir()&&velocity.getY()>0) viewOfMyPlayer.setViewport(imageCells[7]);
    }

    public int getJumpingFrameIndex() {
        return jumpingFrameIndex;
    }
}
