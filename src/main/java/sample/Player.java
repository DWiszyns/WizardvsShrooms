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
    private double width;
    private double height;
    private Point2D velocityLevel = new Point2D(0,0); //predkosc z perspektywy poziomu
    private double xLevel;
    private double yLevel;
    private boolean jumping;
    private int jumpingFrameIndex;
    private boolean inAir; //do skokow

    public Player()
    {
        xLevel=0;
        yLevel=300;
        width=60.0;
        height=105.0;
        jumping=false;
        jumpingFrameIndex=0;
    }


    public void setX (double newX)
    {
        xLevel=newX;

    }

    public void setY(double newY)
    {
        yLevel=newY;
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
    }

    @Override
    public double getHeight() {
        return height;
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
            if(other.getClass()==Flag.class) System.out.println("jestesmy");
            if (yLevel + getHeight() + 3 >= other.getY() && yLevel + getHeight() - 3 < other.getY()) return UP;
            else if (yLevel + 3 >= other.getY() + other.getHeight() && yLevel - 3 <= other.getY() + other.getHeight())
                return DOWN;
            else if (yLevel >= other.getY() && yLevel + getHeight() - 1 <= other.getY() + other.getHeight()) return SIDE;
            else if (yLevel >= other.getY() && yLevel <= other.getY()+other.getHeight()) return SIDE; // glowa znajduje sie, miedzy gora, a dolem obiektu
            else if (yLevel + getHeight() >= other.getY() && yLevel+getHeight()<= other.getY()+other.getHeight()) return SIDE; // stopy znajduja sie miedzy gora a dolem obiektu
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
            setJumping(false);
        }
        xLevel+=velocityLevel.getX();
        yLevel+=velocityLevel.getY();

        if(isJumping())
        {
            jump();
        }
    }
    public void setJumpingFrameIndex(int i)
    {
        jumpingFrameIndex=i;
    }


    public boolean isInAir() {
        return inAir;
    }

    public void setInAir(boolean inAir) {
        this.inAir = inAir;
    }

    public int getJumpingFrameIndex() {
        return jumpingFrameIndex;
    }

}
