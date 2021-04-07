package com.qa.io;

import java.io.FileNotFoundException;
import java.util.Map;

public interface YamlWriter {
    void writeYamlFile(String file, Map<String, Object> data) throws FileNotFoundException;
}
