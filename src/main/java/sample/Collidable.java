package sample;

public interface Collidable {
    enum typeOfCollision {NO,UP, DOWN, SIDE};
     double getX();

     double getY();
     double getWidth();

     double getHeight();
     typeOfCollision isColliding(Collidable other);

}
