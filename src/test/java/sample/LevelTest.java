package sample;

import org.junit.Test;

import static org.junit.Assert.*;

public class LevelTest {

    @Test
    public void initializeLevel1() {
        Level myLevel = new Level(1);
        assertEquals(myLevel.getPlatforms().size(),5);
        assertEquals(myLevel.getEnemies().size(),7);
    }

    @Test
    public void initializeLevel2() {
        Level myLevel = new Level(2);
        int count=0;
        for(int i=0;i<myLevel.getEnemies().size();++i){
            if(myLevel.getEnemies().get(i) instanceof MovingEnemy) ++count;
        }
        assertEquals(count,3);
    }

    @Test
    public void initializeLevel3() {
        Level myLevel = new Level(3);
        int count=0;
        for(int i=0;i<myLevel.getEnemies().size();++i){
            if(myLevel.getEnemies().get(i) instanceof MovingEnemy) ++count;
        }
        assertEquals(count,7);
    }
}