package sample;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class SceneManager {
    private static SceneManager instance = new SceneManager();

    private Stage stage;

    public enum currScene {Menu,Game,Settings};
    private Map<currScene,Scene> sceneMap = new HashMap<>();

    private SceneManager() {
        stage = new Stage();

        sceneMap.put(currScene.Menu,new MainMenu());
        sceneMap.put(currScene.Game,new GameScene());
        changeScene(currScene.Menu);
        stage.setTitle("Wizard vs Shrooms");
    }

    public static SceneManager getInstance() {
        return instance;
    }

    public Stage getStage()
    {
        return stage;
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void changeScene(currScene sceneName) {
        stage.setScene(sceneMap.get(sceneName));
    }

}
