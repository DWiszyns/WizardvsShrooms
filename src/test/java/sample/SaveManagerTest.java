package sample;

import org.junit.Test;

import static org.junit.Assert.*;

public class SaveManagerTest {

    @Test
    public void deleteSave() {
        //SaveManager saveManager = new SaveManager();
        int i = SaveManager.getInstance().findFreeSave();
        SaveManager.getInstance().createSave("Michal",i);
        assertEquals(SaveManager.getInstance().getSave(i).getNameOfOurUser(),"Michal");
        SaveManager.getInstance().deleteSave(i);
        assertEquals(SaveManager.getInstance().getSave(i).getNameOfOurUser(),"empty");
    }

    @Test
    public void findFreeSave() {
        boolean atLeastOneEmpty=false;
        for(int i=0;i<6;++i){
            if(SaveManager.getInstance().getSave(i).isEmpty()){
                atLeastOneEmpty=true;
                break;
            }
        }
        if(atLeastOneEmpty) assertNotEquals(SaveManager.getInstance().findFreeSave(),6);// it returns 6 when all of them are taken
        else assertEquals(SaveManager.getInstance().findFreeSave(),6);
    }

    @Test
    public void isNameTaken() {
        int i = SaveManager.getInstance().findFreeSave();
        SaveManager.getInstance().createSave("Michal",i);
        assertEquals(SaveManager.getInstance().isNameTaken("Michal"),true);
        SaveManager.getInstance().deleteSave(i);

    }
}