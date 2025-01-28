package org.wjurgiel.crossroad.Files;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileHandler{
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
    public String readFile(String filePath) throws IOException{
        return fileReader.read(filePath);
    }
    public static void setFileReaderForTesting(IFileReader testReader){
        if(instance != null){
            instance.fileReader = testReader;
        }
    }



}
