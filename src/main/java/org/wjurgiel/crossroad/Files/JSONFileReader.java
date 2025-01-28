package org.wjurgiel.crossroad.Files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JSONFileReader implements IFileReader{
    @Override
    public String read(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }
}
