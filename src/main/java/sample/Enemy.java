package sample;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static sample.Collidable.typeOfCollision.*;


public class Enemy implements Collidable {
    private Image image = new Image(this.getClass().getClassLoader().getResourceAsStream("Cogucogu.png"));
    private ImageView viewOfMyEnemy=new ImageView(image);
    private double x;
    private double y;
    private double width;
    private double height;
    private final Rectangle2D zdjecie;


    Enemy()
    {
        zdjecie = new Rectangle2D(0,860,500,430);
        x=500.0;
        y=440.0;
        width=100.0;
        height=86.0;
        viewOfMyEnemy.setViewport(zdjecie);
        viewOfMyEnemy.setFitHeight(height);
        viewOfMyEnemy.setFitWidth(width);
        viewOfMyEnemy.setTranslateX(x);
        viewOfMyEnemy.setTranslateY(y);

    }

    public ImageView getViewOfMyEnemy() {
        return viewOfMyEnemy;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public double getX() {
        return x;
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
}
