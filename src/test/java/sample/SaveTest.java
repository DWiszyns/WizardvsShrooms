package sample;

import org.junit.Test;

import static org.junit.Assert.*;

public class SaveTest {

    @Test
    public void saveData() {
        Save save = new Save(7);
        save.saveData("Michal",2);
        assertEquals(save.getNameOfOurUser(),"Michal");
        assertNotEquals(save.isEmpty(),true);
        assertEquals(save.getHowManyLevelsAvailable(),2);
    }
}