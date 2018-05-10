package sample;

import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;

import java.util.ArrayList;
import java.util.List;

public class GameScene {
    private Player myPlayer;
    private AnchorPane myPane;
    private List<Enemy> enemies = new ArrayList<Enemy>();

    public GameScene()
    {
        myPane=new AnchorPane();
        myPane.setPrefSize(600,600);

    }

    public AnchorPane startTheGame()
    {
        return myPane;
    }
}