package sample;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class SceneManager {
    private static SceneManager instance = new SceneManager();

    private Stage stage;

    public enum currScene {Menu,Game,ChooseLevel,Instructions,Lose,Win,ChooseSave,ChooseYourName};
    private Map<currScene,Scene> sceneMap = new HashMap<>();
    private EventHandler<KeyEvent> myEscapeHandler;
    private int whichLevel;
    private int whichSave;

    private SceneManager() {
        stage = new Stage();

        sceneMap.put(currScene.Menu,new MainMenu());
        sceneMap.put(currScene.Instructions, new InstructiuonsScene());
        sceneMap.put(currScene.Lose,new LoseScene());
        sceneMap.put(currScene.Win, new WinScene());
        sceneMap.put(currScene.ChooseYourName, new ChooseYourNameScene());
        //sceneMap.put(currScene.ChooseSave,new ChooseSaveScene());
        stage.setTitle("Wizard vs Shrooms");
        myEscapeHandler = event -> {
            if(event.getEventType()==KeyEvent.KEY_PRESSED)
            {
                if(event.getCode()== KeyCode.ESCAPE) changeScene(currScene.Menu);
            }
        };
        changeScene(currScene.Menu);
        whichLevel=0;//default values that are not possible
        whichSave=6;

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
        if(sceneName==currScene.Game){
            sceneMap.remove(currScene.Game);// I'm doing so we're not going at the same time in multiple levels
            sceneMap.put(currScene.Game,new GameScene(whichLevel,whichSave));
        }
        else if(sceneName==currScene.ChooseLevel){
            sceneMap.remove(currScene.ChooseLevel);
            sceneMap.put(currScene.ChooseLevel,new ChooseLevelScene(SaveManager.getInstance().getSave(whichSave).getHowManyLevelsAvailable()));
        }
        else if(sceneName==currScene.Win){
            sceneMap.remove(currScene.Win);
            sceneMap.put(currScene.Win,new WinScene());
        }
        if(sceneName==currScene.Lose){
            sceneMap.remove(currScene.Lose);
            sceneMap.put(currScene.Lose,new LoseScene());
        }
        else if(sceneName==currScene.ChooseSave){ //we need to update our save menu regulary, because we need change saves
            sceneMap.remove(currScene.ChooseSave);
            sceneMap.put(currScene.ChooseSave,new ChooseSaveScene());
        }
        stage.setScene(sceneMap.get(sceneName));
        stage.getScene().addEventHandler(KeyEvent.KEY_PRESSED,myEscapeHandler);
    }

    public void setWhichLevel(int whichLevel) {
        this.whichLevel = whichLevel;
    }

    public void setWhichSave(int whichSave){
        this.whichSave=whichSave;
    }
}
