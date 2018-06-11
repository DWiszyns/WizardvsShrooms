package sample;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;


public class MovingEnemy extends Enemy {
    private Point2D velocityLevel;
    //private Rectangle2D[]animation = new Rectangle2D[5];
    MovingEnemy(double x, double y){
        super(x,y);
        velocityLevel = new Point2D(0,0);
    }

    public Point2D getVelocityLevel() {
        return velocityLevel;
    }

    public void setVelocityLevel(double x, double y) {
        velocityLevel=new Point2D(x,y);
    }

    public void update(Player player){
        if(isCloseEnough(player.getX(),player.getX()+player.getWidth())) {
           if(getX()-(player.getX()+player.getWidth())<=100 && getX() - (player.getX()+player.getWidth())>0) setVelocityLevel(-1,getVelocityLevel().getY());
           else if(player.getX()-getX()<=100 && player.getX()-getX()>0) setVelocityLevel(1,getVelocityLevel().getY());
        }
        setxLevel(getX()+velocityLevel.getX());
        setyLevel(getY()+velocityLevel.getY());
    }

    public boolean isCloseEnough(double playerX1, double playerX2){
        if(getX()-playerX1<=100 && playerX1-getX()>=-100) return true;
        else if(getX()-playerX2<=100 && playerX2-getX()>=-100) return true;
        else return false;
    }

}
