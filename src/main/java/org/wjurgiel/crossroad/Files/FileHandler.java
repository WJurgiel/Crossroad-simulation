package org.wjurgiel.crossroad.Files;

import org.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private static FileHandler instance;
    private IFileReader fileReader;
    private IFileWriter fileWriter;
    private FileHandler(){
        fileReader = new JSONFileReader();
        fileWriter = new JSONFileWriter();
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

    public void writeOutput(List<String> carsLeft) throws IOException {
        fileWriter.write(carsLeft);
    }

    public static void setFileReaderForTesting(IFileReader testReader){
        if(instance != null){
            instance.fileReader = testReader;
        }
    }
}
