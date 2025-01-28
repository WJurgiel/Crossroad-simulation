package org.wjurgiel.crossroad.Files;

import java.io.IOException;

public interface IFileReader {
    String read(String path) throws IOException;
}
