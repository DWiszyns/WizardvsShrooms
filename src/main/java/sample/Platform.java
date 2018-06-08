package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.geometry.Rectangle2D;

import static sample.Collidable.typeOfCollision.*;

public class Platform implements Collidable{

    private Image image = new Image(this.getClass().getClassLoader().getResourceAsStream("layer-3-ground.png"));
    private ImageView viewOfMyPlatform=new ImageView(image);
    private double xLevel;
    private double yLevel;
    private double xView;
    private double yView;
    private double width;
    private double height;
    private final Rectangle2D zdjecie;
    //potrzebne beda tez wspolrzedne na obrazku, nie tylko na view


    Platform(double x, double y, double w, double h)
    {
        zdjecie = new Rectangle2D(0,1336,200,200);
        xLevel=x;
        yLevel=y;
        xView=xLevel;
        yView=yLevel;
        width=w;
        height=h;
        viewOfMyPlatform.setViewport(zdjecie);
        viewOfMyPlatform.setFitHeight(height);
        viewOfMyPlatform.setFitWidth(width);
        viewOfMyPlatform.setTranslateX(xLevel);
        viewOfMyPlatform.setTranslateY(yLevel);

    }

    public ImageView getViewOfMyPlatform() {
        return viewOfMyPlatform;
    }

    public void setViewOfMyPlatform(double x, double y, double width, double height){
        setHeight(height);
        setWidth(width);
        setxView(x);
        setyView(y);
        //setX(x);
       // setY(y);
        viewOfMyPlatform.setFitHeight(height);
        viewOfMyPlatform.setFitWidth(width);
        viewOfMyPlatform.setTranslateX(xView);
        viewOfMyPlatform.setTranslateY(yView);

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
        return UP;
    }

    public double getxView() {
        return xView;
    }

    public void setxView(double xView) {
        this.xView = xView;
    }

    public double getyView() {
        return yView;
    }

    public void setyView(double yView) {
        this.yView = yView;
    }
}
