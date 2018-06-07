package sample;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class SceneManager {
    private static SceneManager instance = new SceneManager();

    private Stage stage;

    public enum currScene {Menu,Game,LevelView,Instructions};
    private Map<currScene,Scene> sceneMap = new HashMap<>();
    private EventHandler<KeyEvent> myEscapeHandler;


    private SceneManager() {
        stage = new Stage();

        sceneMap.put(currScene.Menu,new MainMenu());
        sceneMap.put(currScene.Game,new GameScene());
        sceneMap.put(currScene.LevelView,new LevelView());
        sceneMap.put(currScene.Instructions, new InstructionsView());
        stage.setTitle("Wizard vs Shrooms");
        myEscapeHandler = event -> {
            if(event.getEventType()==KeyEvent.KEY_PRESSED)
            {
                if(event.getCode()== KeyCode.ESCAPE) changeScene(currScene.Menu);
            }
        };
        changeScene(currScene.Menu);
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
        stage.getScene().addEventHandler(KeyEvent.KEY_PRESSED,myEscapeHandler);
    }

}
