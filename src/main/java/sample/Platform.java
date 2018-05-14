package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.geometry.Rectangle2D;

import static sample.Collidable.typeOfCollision.*;

public class Platform implements Collidable{

    private Image image = new Image(this.getClass().getClassLoader().getResourceAsStream("layer-3-ground.png"));
    private ImageView viewOfMyPlatform=new ImageView(image);
    private double x;
    private double y;
    private double width;
    private double height;
    private final Rectangle2D zdjecie;


    Platform()
    {
        zdjecie = new Rectangle2D(0,1336,200,200);
        x=0.0;
        y=455.0;
        width=310.0;
        height=80.0;
        viewOfMyPlatform.setViewport(zdjecie);
        viewOfMyPlatform.setFitHeight(height);
        viewOfMyPlatform.setFitWidth(width);
        viewOfMyPlatform.setTranslateX(x);
        viewOfMyPlatform.setTranslateY(y);

    }

    public ImageView getViewOfMyPlatform() {
        return viewOfMyPlatform;
    }

    public void setViewOfMyPlatform(double x, double y, double width, double height){
        setHeight(height);
        setWidth(width);
        setX(x);
        setY(y);
        viewOfMyPlatform.setFitHeight(height);
        viewOfMyPlatform.setFitWidth(width);
        viewOfMyPlatform.setTranslateX(x);
        viewOfMyPlatform.setTranslateY(y);

    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
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
}
