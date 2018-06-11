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
    private final Rectangle2D photo[]= new Rectangle2D[5];
    private int timeFrame;
    private int cellFrame;//zeby zawsze chodzil tak samo
    private boolean sleeping;

    public EnemyView(Enemy myEnemy)
    {
        setEnemy(myEnemy);
        photo[0] = new Rectangle2D(0,0,500,430);
        photo[1] = new Rectangle2D(500,0,500,430);
        photo[2] = new Rectangle2D(0,430,500,430);
        photo[3] = new Rectangle2D(500,430,500,430);
        photo[4] = new Rectangle2D(0,860,500,430);
        xView=enemy.getX();
        yView=enemy.getY();
        if(myEnemy instanceof MovingEnemy) {
            viewOfMyEnemy.setViewport(photo[0]);
            sleeping = true;
        }
        else {
            viewOfMyEnemy.setViewport(photo[4]);
            sleeping = false;
        }
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

    public void update(double cameraX, Player player)
    {
        enemy.update(player);
        if(enemy.getVelocityLevel().getX()!=0) setSleeping(false);
        xView=xView-cameraX+enemy.getVelocityLevel().getX();
        setViewOfMyEnemy(xView,yView);
        if(enemy instanceof MovingEnemy && sleeping) {
            setAnimation();
        }
        else viewOfMyEnemy.setViewport(photo[4]);

    }

    public void setAnimation(){
        timeFrame=(++timeFrame)%10;
        cellFrame=(++cellFrame)%4;
            if(timeFrame==0)
            {
                viewOfMyEnemy.setViewport(photo[cellFrame]);
            }

    }


    public void setSleeping(boolean sleeping) {
        this.sleeping = sleeping;
        if(!sleeping){
            viewOfMyEnemy.setViewport(photo[4]);
        }

    }

}
