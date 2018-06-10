package sample;


import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FlagView {
    private Flag flag;
    private double xView;
    private  double yView;
    private Image image = new Image(this.getClass().getClassLoader().getResourceAsStream("flags.png"));
    private ImageView viewOfMyFlag = new ImageView(image);
    private final Rectangle2D photoFrames[] =new Rectangle2D[4];
    private int timeFrame;
    private int cellFrame;

    FlagView(Flag myFlag)
    {
        setFlag(myFlag);
        xView=flag.getX();
        yView=flag.getY();
        for(int i=0;i<4;++i){
            photoFrames[i] = new Rectangle2D(0+i*16,32,16,16);
        }
        viewOfMyFlag.setViewport(photoFrames[0]);
        viewOfMyFlag.setTranslateX(xView);
        viewOfMyFlag.setTranslateY(yView);
        viewOfMyFlag.setFitWidth(flag.getWidth());
        viewOfMyFlag.setFitHeight(flag.getHeight());
        timeFrame=0;
        cellFrame=0;
    }

    public Flag getFlag() {
        return flag;
    }

    public void setFlag(Flag flag) {
        this.flag = flag;
    }

    public ImageView getViewOfMyFlag() {
        return viewOfMyFlag;
    }

    public void setAnimation(){
        timeFrame=(++timeFrame)%10;
        cellFrame=(++cellFrame)%4;
        if(timeFrame==0){
            viewOfMyFlag.setViewport(photoFrames[cellFrame]);
        }
    }

    public double getxView() {
        return xView;
    }

    public double getyView() {
        return yView;
    }

    public void setViewOfMyFlag(double x, double y) {
        viewOfMyFlag.setTranslateX(xView);
        viewOfMyFlag.setTranslateY(yView);
    }

    public void update(double cameraX){
        xView=xView-cameraX;
        setViewOfMyFlag(xView,yView);
        setAnimation();

    }
}
