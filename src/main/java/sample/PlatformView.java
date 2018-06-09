package sample;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PlatformView {
    private Platform platform;
    private double xView;
    private double yView;
    private Image image = new Image(this.getClass().getClassLoader().getResourceAsStream("layer-3-ground.png"));
    private final Rectangle2D photoFrame;
    private ImageView viewOfMyPlatform=new ImageView(image);
    public PlatformView(Platform myPlatform)
    {
        setPlatform(myPlatform);
        photoFrame = new Rectangle2D(0,1336,200,200);
        xView=platform.getX();
        yView=platform.getY();
        viewOfMyPlatform.setViewport(photoFrame);
        viewOfMyPlatform.setFitHeight(platform.getHeight());
        viewOfMyPlatform.setFitWidth(platform.getWidth());
        viewOfMyPlatform.setTranslateX(xView);
        viewOfMyPlatform.setTranslateY(yView);

    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public ImageView getViewOfMyPlatform() {
        return viewOfMyPlatform;
    }

    public void setViewOfMyPlatform(double x, double y) {
        //setxView(x);
        //setyView(y);
        viewOfMyPlatform.setTranslateX(xView);
        viewOfMyPlatform.setTranslateY(yView);
    }

    public void update(double cameraX)
    {
        xView=xView-cameraX;
        setViewOfMyPlatform(xView,yView);
    }

    public double getyView() {
        return yView;
    }

    public double getxView() {
        return xView;
    }

    public void setxView(double xView) {
        this.xView = xView;
    }

    public void setyView(double yView) {
        this.yView = yView;
    }
}
