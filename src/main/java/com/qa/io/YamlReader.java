package com.qa.io;

import java.io.FileNotFoundException;
import java.util.Map;

public interface YamlReader {
    Map<String, Object> readYamlFile(String file) throws FileNotFoundException;
}
