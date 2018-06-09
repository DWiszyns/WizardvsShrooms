package sample;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static sample.Collidable.typeOfCollision.*;


public class Enemy implements Collidable {
    private Image image = new Image(this.getClass().getClassLoader().getResourceAsStream("Cogucogu.png"));
    private ImageView viewOfMyEnemy=new ImageView(image);
    private double xLevel;
    private double yLevel;
    private double xView;
    private double yView;
    private double width;
    private double height;
    private final Rectangle2D zdjecie;


    Enemy(double x, double y)
    {
        zdjecie = new Rectangle2D(0,860,500,430);
        xLevel=x;
        yLevel=y;
        width=40.0;
        height=30.0;
        xView=xLevel;
        yView=yLevel;
        viewOfMyEnemy.setViewport(zdjecie);
        viewOfMyEnemy.setFitHeight(height);
        viewOfMyEnemy.setFitWidth(width);
        viewOfMyEnemy.setTranslateX(xView);
        viewOfMyEnemy.setTranslateY(yView);
    }

    public ImageView getViewOfMyEnemy() {
        return viewOfMyEnemy;
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
        return NO;
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

    public void setViewOfMyEnemy(double x, double y, double width, double height) {
        viewOfMyEnemy.setFitHeight(height);
        viewOfMyEnemy.setFitWidth(width);
        viewOfMyEnemy.setTranslateX(x);
        viewOfMyEnemy.setTranslateY(y);
    }

    public void update(double cameraX)
    {
        xView=xView-cameraX;
        setViewOfMyEnemy(xView,yView,width,height);
    }
}
