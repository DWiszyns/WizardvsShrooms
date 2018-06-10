package sample;

public class Flag implements Collidable{
    private double x;
    private double y;
    private double height;
    private double width;
    Flag(double xLevel, double yLevel)
    {
        x=xLevel;
        y=yLevel;
        height = 100;
        width = 50;

    }

    @Override
    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    @Override
    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    @Override
    public typeOfCollision isColliding(Collidable other) {
        return null;
    }
}
