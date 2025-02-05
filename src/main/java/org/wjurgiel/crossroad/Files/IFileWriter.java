package org.wjurgiel.crossroad.Files;

import java.io.IOException;
import java.util.List;

public interface IFileWriter {
    void write(List<String> leftCars) throws IOException;
}
