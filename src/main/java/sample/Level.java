package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

public class Level {
    private ImageView background;
    private List<Enemy> enemies = new ArrayList<Enemy>();
    private List<Platform> platforms = new ArrayList<Platform>();
    private Flag flag;
    private int whichLevel;
    public Level(int levelChooser)
    {
        whichLevel=levelChooser;
        switch(whichLevel){
            case 1: {initializeLevel1();break;}
            case 2: {initializeLevel2(); break;}
            case 3: {initializeLevel3(); break;}
        }
    }

    public void initializeLevel1()
    {
        Image image = new Image(this.getClass().getClassLoader().getResourceAsStream("otherbackground.png"));
        background = new ImageView(image);
        platforms.add(new Platform(0,455,310,80));
        platforms.add(new Platform(300.0,520.0,400.0,60.0));
        platforms.add(new Platform(710,480.0,200,60.0));
        platforms.add(new Platform(950,480,500,60));
        platforms.add(new Platform(1460,460,385,60));
        //height of my enemy is about 30, width is 40
        enemies.add(new Enemy(300,425));
        enemies.add(new Enemy(800,450));
        enemies.add(new Enemy(600,490));
        enemies.add(new Enemy(1000,450));
        enemies.add(new Enemy(1040,450));
        enemies.add(new Enemy(1080,450));
        enemies.add(new Enemy(1600,430));

        flag = new Flag(1750,360);

    }
    public void initializeLevel2()
    {
        Image image = new Image(this.getClass().getClassLoader().getResourceAsStream("new_background.png"));
        background = new ImageView(image);
        platforms.add(new Platform(0,455,310,80));
        platforms.add(new Platform(300.0,520.0,400.0,60.0));
        platforms.add(new Platform(710,480.0,200,60.0));
        platforms.add(new Platform(950,480,500,60));
        platforms.add(new Platform(1460,460,385,60));
        //height of my enemy is about 30, width is 40
        enemies.add(new Enemy(200,425));
        enemies.add(new Enemy(750,450));
        enemies.add(new Enemy(500,490));
        enemies.add(new Enemy(1200,450));
        enemies.add(new Enemy(1240,450));
        enemies.add(new Enemy(1280,450));
        enemies.add(new Enemy(1600,430));

        flag = new Flag(1750,360);


    }
    public void initializeLevel3()
    {
        Image image = new Image(this.getClass().getClassLoader().getResourceAsStream("new_background.png"));
        background = new ImageView(image);
        platforms.add(new Platform(0,455,310,60));
        platforms.add(new Platform(300.0,520.0,400.0,60.0));
        platforms.add(new Platform(710,480.0,200,60.0));
        platforms.add(new Platform(950,480,500,60));
        platforms.add(new Platform(1460,460,385,60));
        //height of my enemy is about 30, width is 40
        enemies.add(new MovingEnemy(300,425));
        enemies.add(new Enemy(800,450));
        enemies.add(new Enemy(600,490));
        enemies.add(new Enemy(1000,450));
        enemies.add(new Enemy(1040,450));
        enemies.add(new Enemy(1080,450));
        enemies.add(new Enemy(1600,430));

        flag = new Flag(310,420);

    }

    public ImageView getBackground() {
        return background;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public List<Platform> getPlatforms() {
        return platforms;
    }

    public Platform getPlatform(int i){
        return platforms.get(i);
    }

    public Flag getFlag() {
        return flag;
    }
}
