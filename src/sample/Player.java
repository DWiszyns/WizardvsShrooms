package sample;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.*;
import javafx.scene.layout.Pane;


public class Player {
    private final Rectangle2D imageCells[]=new Rectangle2D[11]; //tablica z animacja bohatera
    private double cellWidth;
    private double cellHeight;
    private ImageView viewOfMyPlayer=new ImageView();
    private final int numberofFrames=11;
    private Point2D velocity = new Point2D(0,0);
    private double x;
    private double y;

    public Player()
    {
        Image image = new Image("sample/pelnaanimacja.png");
        //viewOfMyPlayer=new ImageView(image);
        x=-1;
        y=440; //wspolrzedne, zeby stal na ziemi
        cellWidth= 40;
        cellHeight= 36;

        imageCells[0] = new Rectangle2D(0,0,cellWidth,cellHeight);
        viewOfMyPlayer.setViewport(imageCells[0]);
        viewOfMyPlayer.setImage(image);
        viewOfMyPlayer.setFitWidth(100.0);
        viewOfMyPlayer.setFitHeight(90.0);
    }

    public ImageView getViewOfMyPlayer() {
        return viewOfMyPlayer;
    }

    public void setX(double newX)
    {
        x=newX;
    }

    public void setY(double newY)
    {
        y=newY;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setVelocity(double xx, double yy)
    {
        velocity=new Point2D(xx,yy);
    }
}
