package org.wjurgiel.crossroad.Files;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class JSONFileWriter implements IFileWriter {
    private static final String FILE_NAME = "results.json";

    public JSONFileWriter() {
        resetFile();
    }
    private void resetFile() {
        JSONObject rootObject = new JSONObject();
        rootObject.put("stepStatuses", new JSONArray());

        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            writer.write(rootObject.toString(4));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(List<String> leftCars) throws IOException {
        JSONObject rootObject;
        JSONArray stepStatuses;

        File file = new File(FILE_NAME);
        if(file.exists()) {
            try{
                String content = new String(Files.readAllBytes(Paths.get(FILE_NAME)));
                rootObject = new JSONObject(content);
                stepStatuses = rootObject.getJSONArray("stepStatuses");
                if(stepStatuses == null) {
                    stepStatuses = new JSONArray();
                }
            }catch(IOException e){
                e.printStackTrace();
                return;
            }
        }
        else {
            rootObject = new JSONObject();
            stepStatuses = new JSONArray();
        }
        JSONObject leftVehiclesObject = new JSONObject();
        leftVehiclesObject.put("leftVehicles", new JSONArray(leftCars));

        stepStatuses.put(leftVehiclesObject);
        rootObject.put("stepStatuses", stepStatuses);

        try(FileWriter writer = new FileWriter(FILE_NAME)) {
            writer.write(rootObject.toString(4));
            System.out.println("<Saved to file>");
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
