package sample;

import javafx.animation.AnimationTimer;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.*;
import javafx.scene.layout.Pane;


public class Player {
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
    public AnimationTimer myTimer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            x+=velocity.getX();
            y+=velocity.getY();
            viewOfMyPlayer.setTranslateX(x);
            viewOfMyPlayer.setTranslateY(y);
            if(isJumping())
            {
                jump();
            }

        }
    };

    public Player()
    {
        Image image = new Image("sample/pelnaanimacja.png");
        //viewOfMyPlayer=new ImageView(image);
        x=-1;
        y=440; //wspolrzedne, zeby stal na ziemi
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
        viewOfMyPlayer.setFitHeight(102.0);
        jumping=false;
        jumpingFrameIndex=0;
        myTimer.start();
    }

    public ImageView getViewOfMyPlayer() {
        return viewOfMyPlayer;
    }

    public void setX (double newX)
    {
        x=newX;
        viewOfMyPlayer.setX(x);
        if(!jumping)viewOfMyPlayer.setViewport(imageCells[((int)x+1)%4]);

    }

    public void setY(double newY)
    {
        double oldY=y;
        y=newY;
        viewOfMyPlayer.setY(y);

    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
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
        if(jumpingFrameIndex==0) setVelocity(getVelocity().getX()+0,getVelocity().getY()+(-1));
        if(jumpingFrameIndex<19) { viewOfMyPlayer.setViewport(imageCells[4]); System.out.println(x+" "+y+" "+jumpingFrameIndex);}
        if(jumpingFrameIndex==19) {setVelocity(getVelocity().getX(),getVelocity().getY()+1); viewOfMyPlayer.setViewport(imageCells[5]); System.out.println(x+" "+y+" "+jumpingFrameIndex);}
        if(jumpingFrameIndex==20) {viewOfMyPlayer.setViewport(imageCells[6]); System.out.println(x+" "+y+" "+jumpingFrameIndex);}
        if(jumpingFrameIndex>20&&jumpingFrameIndex<40) {
            if(jumpingFrameIndex==21) setVelocity(getVelocity().getX(),getVelocity().getY()+1);
            viewOfMyPlayer.setViewport(imageCells[7]);
            System.out.println(x+" "+y+" "+jumpingFrameIndex);
        }
        if(jumpingFrameIndex!=40) ++jumpingFrameIndex;
        else {
            setVelocity(getVelocity().getX(),getVelocity().getY()-1);
            setJumping(false);
            System.out.println(x+" "+y+" "+jumpingFrameIndex);
            viewOfMyPlayer.setViewport(imageCells[0]);
            jumpingFrameIndex=0;

    }
    }
}
