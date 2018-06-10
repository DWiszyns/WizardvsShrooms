package sample;

import java.io.*;

public class Save {
    private String nameOfOurUser;
    private String fileWithData;
    private int howManyLevelsAvailable;
    Save(int whichSave)
    {
        try {
            String howManyAvailable;
            FileReader fstream = null;
            fileWithData="src/main/resources/save"+whichSave+".txt";
            fstream = new FileReader(fileWithData);
            BufferedReader in = new BufferedReader(fstream);
            nameOfOurUser=in.readLine();
            howManyLevelsAvailable=in.read();
            in.close();
        }
        catch(IOException e)
        {
            System.out.println(e);
        }

    }

    public String getNameOfOurUser() {
        return nameOfOurUser;
    }

    public int getHowManyLevelsAvailable() {
        return howManyLevelsAvailable;
    }

    public void setNameOfOurUser(String nameOfOurUser) {
        this.nameOfOurUser = nameOfOurUser;
    }

    public boolean isEmpty(){
        return nameOfOurUser.equals("empty");

    }

    public void saveData(String userName,int howManyAvailable){
        try {
            nameOfOurUser=userName;
            howManyLevelsAvailable = howManyAvailable;
            FileWriter fstream2 = null;
            fstream2 = new FileWriter(fileWithData, false);
            BufferedWriter out = new BufferedWriter(fstream2);
            out.write(nameOfOurUser);
            out.newLine();
            out.write(howManyLevelsAvailable);
            out.close();
        }
        catch(IOException e){
            System.out.println(e);
        }

    }
}
