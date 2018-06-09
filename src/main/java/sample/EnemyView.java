package sample;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EnemyView {
    private Enemy enemy;
    private Image image = new Image(this.getClass().getClassLoader().getResourceAsStream("Cogucogu.png"));
    private ImageView viewOfMyEnemy=new ImageView(image);
    private double xView;
    private double yView;
    private final Rectangle2D photo;

    public EnemyView(Enemy myEnemy)
    {
        setEnemy(myEnemy);
        photo = new Rectangle2D(0,860,500,430);
        xView=enemy.getX();
        yView=enemy.getY();
        viewOfMyEnemy.setViewport(photo);
        viewOfMyEnemy.setFitHeight(enemy.getHeight());
        viewOfMyEnemy.setFitWidth(enemy.getWidth());
        viewOfMyEnemy.setTranslateX(xView);
        viewOfMyEnemy.setTranslateY(yView);
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public ImageView getViewOfMyEnemy() {
        return viewOfMyEnemy;
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

    public void setViewOfMyEnemy(double x, double y) {
        viewOfMyEnemy.setTranslateX(x);
        viewOfMyEnemy.setTranslateY(y);
    }

    public void update(double cameraX)
    {
        xView=xView-cameraX;
        setViewOfMyEnemy(xView,yView);
    }
}
