package sample;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PlayerView {
    private Player player;
    private Point2D velocityView = new Point2D(0,0); //predkosc z perspektywy widoku
    private final Rectangle2D imageCells[]=new Rectangle2D[11]; //tablica z animacja bohatera
    private double cellWidth; // size of the frame for animations
    private double cellHeight;
    private double xView; //our x on the view instead of the level
    private double yView; // our y on the view instead of the level
    private int timeFrame;
    private int cellFrame;//zeby zawsze chodzil tak samo
    private ImageView viewOfMyPlayer=new ImageView();
    public PlayerView(Player myPlayer)
    {
        Image image = new Image(this.getClass().getClassLoader().getResourceAsStream("pelnaanimacja.png"));
        setPlayer(myPlayer);
        cellWidth= 20;
        cellHeight= 34;
        xView = player.getX();
        yView = player.getY();
        //here we set the table of animations of our player, all of them have unusual size, so we have to set them manually
        imageCells[0] = new Rectangle2D(cellWidth,0,cellWidth,cellHeight);
        imageCells[1]= new Rectangle2D(cellWidth*2,0,cellWidth,cellHeight);
        imageCells[2]= new Rectangle2D(cellWidth*3,0,cellWidth,cellHeight);
        imageCells[3]= new Rectangle2D(cellWidth*4,0,cellWidth,cellHeight);
        imageCells[4]= new Rectangle2D(cellWidth,cellHeight,cellWidth-1,cellHeight-9);
        imageCells[5]= new Rectangle2D(cellWidth*2,cellHeight,cellWidth,cellHeight);
        imageCells[6]= new Rectangle2D(cellWidth*3,cellHeight,cellWidth-1,cellHeight);
        imageCells[7]= new Rectangle2D(cellWidth*4,cellHeight,cellWidth,cellHeight);
        viewOfMyPlayer.setViewport(imageCells[0]);
        viewOfMyPlayer.setImage(image);
        viewOfMyPlayer.setFitWidth(60.0);
        viewOfMyPlayer.setFitHeight(105.0);

    }


    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    public void update(boolean colliding,boolean moving)//obsluguje skoki, ruch, zmiane animacji, to mega trzeba zmienic
    {
        //first we need to update our model
        player.update(colliding,moving);
        //then we update our view
        /*
        if(player.getJumpingFrameIndex()==1)setVelocityView(new Point2D(getVelocityView().getX()+0,getVelocityView().getY()+(-4)));
        if(velocityView.getY()!=0 && !player.isJumping() && colliding) {setVelocityView(new Point2D(velocityView.getX(),0));} // if player is falling and not jumping we sho */
        setVelocityView(new Point2D(velocityView.getX(),player.getVelocity().getY())); //velocity Y should always stay the same, the difference is only in x velocity
                                                                                        //because we can move on our level, but we don't move on our view, our level moves instead
        xView+=velocityView.getX();
        yView+=velocityView.getY();
        viewOfMyPlayer.setTranslateX(xView);
        viewOfMyPlayer.setTranslateY(yView);
        setAnimation(moving);
    }

    public void setAnimation(boolean moving) //set Animation tylko patrzy czy po xLevel,yLevel itd. , bo na widoku mozemy stac w miejscu, a tak naprawde idziemy
    {
        timeFrame=(++timeFrame)%10;
        cellFrame=(++cellFrame)%4;
        if(player.getVelocity().getX()==0 && player.getVelocity().getY()==0 && !player.isInAir() &&!moving) viewOfMyPlayer.setViewport(imageCells[0]); //podstawowy widok bohatera
        else if(moving&&player.getVelocity().getY()==0 && !player.isInAir())
        {
            if(timeFrame==0)
            {
                viewOfMyPlayer.setViewport(imageCells[cellFrame]);
            }

        }
        if(player.isInAir()&&player.getVelocity().getY()<0) viewOfMyPlayer.setViewport(imageCells[5]);
        if(player.isInAir()&&player.getVelocity().getY()==0) viewOfMyPlayer.setViewport(imageCells[6]);
        if(player.isInAir()&&player.getVelocity().getY()>0) viewOfMyPlayer.setViewport(imageCells[7]);
    }

    public void setVelocityView(Point2D velocityView) {
        this.velocityView = velocityView;
    }

    public Point2D getVelocityView() {
        return velocityView;
    }

    public Rectangle2D getImageCells(int i) {
        return imageCells[i];
    }

    public ImageView getViewOfMyPlayer() {
        return viewOfMyPlayer;
    }

    public double getxView() {
        return xView;
    }

    public double getyView() {
        return yView;
    }
}
