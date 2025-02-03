package org.wjurgiel.crossroad.Files;

import org.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;

public class FileHandler {
    private static FileHandler instance;
    private IFileReader fileReader;
    private FileHandler(){
        fileReader = new JSONFileReader();
    }
    public static FileHandler getInstance(){
        if(instance == null){
            instance = new FileHandler();
        }
        return instance;
    }
    public JSONArray readFileAsJSONArray(String filePath) throws IOException{
        return fileReader.read(filePath);
    }
    public static void setFileReaderForTesting(IFileReader testReader){
        if(instance != null){
            instance.fileReader = testReader;
        }
    }
}
