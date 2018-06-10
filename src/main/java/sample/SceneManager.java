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

    public enum currScene {Menu,Game1,Game2,ChooseLevelView,Instructions,Lose,Win};
    private Map<currScene,Scene> sceneMap = new HashMap<>();
    private EventHandler<KeyEvent> myEscapeHandler;


    private SceneManager() {
        stage = new Stage();

        sceneMap.put(currScene.Menu,new MainMenu());
        sceneMap.put(currScene.ChooseLevelView,new ChooseLevelView());
        sceneMap.put(currScene.Instructions, new InstructionsView());
        sceneMap.put(currScene.Lose,new LoseView());
        sceneMap.put(currScene.Win, new WinView());
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
        if(sceneName==currScene.Game1){
            sceneMap.put(currScene.Game1,new GameScene(1));
            sceneMap.remove(currScene.Game2);// I'm doing so we're not going at the same time in multiple levels
        }
        else if(sceneName==currScene.Game2){
            sceneMap.put(currScene.Game2,new GameScene(2));
            sceneMap.remove(currScene.Game1);
        }
        stage.setScene(sceneMap.get(sceneName));
        stage.getScene().addEventHandler(KeyEvent.KEY_PRESSED,myEscapeHandler);
    }

}
