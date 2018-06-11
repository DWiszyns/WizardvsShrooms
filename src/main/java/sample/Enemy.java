package sample;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static sample.Collidable.typeOfCollision.*;


public class Enemy implements Collidable {

    private double xLevel;
    private double yLevel;
    private double width;
    private double height;
    private final Point2D velocityLevel;


    Enemy(double x, double y)
    {
        xLevel=x;
        yLevel=y;
        width=40.0;
        height=30.0;
        velocityLevel = new Point2D(0,0);
    }


    @Override
    public double getY() {
        return yLevel;
    }

    @Override
    public double getX() {
        return xLevel;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public typeOfCollision isColliding(Collidable other) {
        return UP;
    }

    public void setxLevel(double xLevel) {
        this.xLevel = xLevel;
    }

    public void setyLevel(double yLevel) {
        this.yLevel = yLevel;
    }

    public Point2D getVelocityLevel() {
        return velocityLevel;
    }

    public void setVelocityLevel(double x, double y){

    }

    public void update(Player player){
        setxLevel(xLevel);
        setyLevel(yLevel);
    }
}
