package org.wjurgiel.crossroad;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.wjurgiel.crossroad.Files.FileHandler;
import org.wjurgiel.crossroad.Files.IFileReader;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FileHandlerTest {
    private IFileReader fileReaderMock;
    private FileHandler fileHandler;

    @BeforeEach
    void setup(){
        fileReaderMock = mock(IFileReader.class);
        fileHandler = FileHandler.getInstance();
        FileHandler.setFileReaderForTesting(fileReaderMock);
    }
    @Test
    void readFileAsJSONArray_ReturnsCorrectData() throws IOException {
        JSONArray fakeJsonCommandArray = new JSONArray();
        fakeJsonCommandArray.put(new JSONObject().put("type", "addVehicle")
                .put("vehicleId", "car1")
                .put("startRoad", "south")
                .put("endRoad", "north"));
        fakeJsonCommandArray.put(new JSONObject().put("type", "step"));

        when(fileReaderMock.read(anyString())).thenReturn(fakeJsonCommandArray);

        JSONArray result = fileHandler.readFileAsJSONArray("fakePath.json");

        assertEquals(2, result.length());
        assertEquals("addVehicle", result.getJSONObject(0).getString("type"));
        assertEquals("car1", result.getJSONObject(0).getString("vehicleId"));
        assertEquals("south", result.getJSONObject(0).getString("startRoad"));
        assertEquals("north", result.getJSONObject(0).getString("endRoad"));
    }
}
