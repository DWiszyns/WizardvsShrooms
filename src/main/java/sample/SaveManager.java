package sample;

public class SaveManager {
    private static SaveManager instance = new SaveManager();
    private Save saveTable[]=new Save[6];
    SaveManager(){
       for(int i=0;i<6;++i)
       {
           saveTable[i]=new Save(i+1);
       }

    }

    public void deleteSave(int whichSave){
        saveTable[whichSave].saveData("empty",1);

    }
    public void createSave(String name, int whichSave){
        saveTable[whichSave].saveData(name,1);
    }
    public Save getSave(int whichSave){
        return saveTable[whichSave];
    }
    public static SaveManager getInstance()
    {
        return instance;
    }

    public int findFreeSave() {
        for (int i = 0; i < 6; ++i) {
            if (saveTable[i].isEmpty()) return i;
        }
        return 6; //if it returns 6 there is no empty space for save
    }

    public boolean isNameTaken(String name){
        for(int i=0;i<6;++i)
        {
            if(name.equals(saveTable[i].getNameOfOurUser())) return true;
        }
        return false;
    }
}
