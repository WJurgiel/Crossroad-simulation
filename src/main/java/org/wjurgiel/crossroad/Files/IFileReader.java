package org.wjurgiel.crossroad.Files;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public interface IFileReader {
    JSONArray read(String path) throws IOException;
}
