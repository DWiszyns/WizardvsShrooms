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
   /* public AnimationTimer myTimer = new AnimationTimer() { //obsluguje skoki, ruch, zmiane animacji
        @Override
        public void handle(long now) {
            x+=velocity.getX();
            y+=velocity.getY();
            viewOfMyPlayer.setTranslateX(x);
            viewOfMyPlayer.setTranslateY(y);
            timeFrame=(timeFrame+1)%10;
            if(isJumping())
            {
                jump();
            }
            if(velocity.getX()>0 && !jumping && timeFrame==0 )
            {
                viewOfMyPlayer.setViewport(imageCells[((int)x+1)%4]);
            }

        }
    }; */

    public Player()
    {
        Image image = new Image(this.getClass().getClassLoader().getResourceAsStream("pelnaanimacja.png"));
        //viewOfMyPlayer=new ImageView(image);
        x=-1;
        //y=440; //wspolrzedne, zeby stal na ziemi
        y=440;
        cellWidth= 20;
        cellHeight= 35;
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
        return cellWidth;
    }

    @Override
    public double getHeight() {
        return cellHeight;
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
        if(jumpingFrameIndex==0) setVelocity(getVelocity().getX()+0,getVelocity().getY()+(-4));
        if(jumpingFrameIndex<19) { viewOfMyPlayer.setViewport(imageCells[4]); System.out.println(x+" "+y+" "+jumpingFrameIndex);}
        if(jumpingFrameIndex==19) {setVelocity(getVelocity().getX(),getVelocity().getY()+4); viewOfMyPlayer.setViewport(imageCells[5]); System.out.println(x+" "+y+" "+jumpingFrameIndex);}
        if(jumpingFrameIndex==20) {viewOfMyPlayer.setViewport(imageCells[6]); System.out.println(x+" "+y+" "+jumpingFrameIndex);}
        if(jumpingFrameIndex==21) setVelocity(getVelocity().getX(),getVelocity().getY()+4);
        if(jumpingFrameIndex>20&&jumpingFrameIndex<40) {
            viewOfMyPlayer.setViewport(imageCells[7]);
            System.out.println(x+" "+y+" "+jumpingFrameIndex);
        }
        if(jumpingFrameIndex!=40) ++jumpingFrameIndex;
        else {
            setVelocity(getVelocity().getX(),0);
            setJumping(false);
            System.out.println(x+" "+y+" "+jumpingFrameIndex);
            viewOfMyPlayer.setViewport(imageCells[0]);
            jumpingFrameIndex=0;
        }
    }

    public typeOfCollision isColliding(Collidable other)
    {
        if(x+cellWidth>=other.getX() && x+cellWidth <= other.getX()+other.getWidth())  //sprawdzamy czy x
        {
            if(y+cellHeight<=other.getY() && y+cellHeight>other.getY()+other.getHeight()) return UP;
            else if(y+cellHeight<=other.getY() && y < other.getY()+other.getHeight() ) return DOWN;
            else return SIDE;
        }
        else return NO;
       //return x.getViewOfMyPlatform().getBoundsInParent().intersects(x.getViewOfMyPlatform().getBoundsInLocal());
    }

    public void update(boolean colliding)//obsluguje skoki, ruch, zmiane animacji
    {
        if(velocity.getY()!=0 && colliding) {setVelocity(velocity.getX(),0); setJumping(false);}
        x+=velocity.getX();
        y+=velocity.getY();
        viewOfMyPlayer.setTranslateX(x);
        viewOfMyPlayer.setTranslateY(y);
        timeFrame=(timeFrame+1)%10;
        if(isJumping())
        {
            jump();
        }
        if(velocity.getX()!=0 && !jumping && timeFrame==0 )
        {
            viewOfMyPlayer.setViewport(imageCells[((int)x+1)%4]);
        }
    }
    public void setJumpingFrameIndex(int i)
    {
        jumpingFrameIndex=i;
    }

    public Rectangle2D getImageCells(int i) {
        return imageCells[i];
    }
}
