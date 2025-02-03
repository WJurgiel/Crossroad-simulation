package org.wjurgiel.crossroad.Files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONFileReader implements IFileReader{

    @Override
    public JSONArray read(String path) throws IOException {
        String jsonString = new String(Files.readAllBytes(Paths.get(path)));
        JSONObject object = new JSONObject(jsonString);
        try{
           JSONArray commands = object.getJSONArray("commands");
            return commands;
        }
        catch(JSONException e){
            throw new IOException(e.getMessage());
        }
    }
}
