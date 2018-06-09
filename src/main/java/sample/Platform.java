package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.geometry.Rectangle2D;

import static sample.Collidable.typeOfCollision.*;

public class Platform implements Collidable{
    private double xLevel;
    private double yLevel;
    private double width;
    private double height;
    //here we only have information about our platform's co-ordinates on the level


    Platform(double x, double y, double w, double h)
    {
        xLevel=x;
        yLevel=y;
        width=w;
        height=h;

    }


    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setX(double x) {
        this.xLevel = x;
    }

    public void setY(double y) {
        this.yLevel = y;
    }

    @Override
    public double getX() {
        return xLevel;
    }

    @Override
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

    @Override
    public typeOfCollision isColliding(Collidable other) {
        return NO;
    } //I needed to implement it, because it's collidable
}
