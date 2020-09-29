package com.qa.base;

import com.qa.annotations.Marker;
import com.qa.constants.MarkerConstants;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Ankur Jaiswal
 */
public class Main {

  /**
   * Entrypoint of Execution. Pass the markers that you want to execute
   * Based on the marker it will pick all classed which are having these markers at run time
   * and pass it to TestNgSuite creater.
   * @param args
   * @throws MalformedURLException
   */
  public static void main(String[] args) throws MalformedURLException {
    String markers = args[0];
    Set<Class> classes = new HashSet<>();
    /**
     * Get the list of classes which are having provided markers
     */
    URL sourceClassesURL = Paths.get("target/classes").toUri().toURL();
    URL testClassesURL = Paths.get("target/test-classes").toUri().toURL();
    URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{sourceClassesURL,testClassesURL});

    for (String marker : markers.split(",")) {
    /*Reflections reflections = new Reflections(new ConfigurationBuilder()
      .setUrls(ClasspathHelper.forPackage("com.qa", classLoader))
      .setScanners(
        new TypeAnnotationsScanner(),
        new MethodAnnotationsScanner())
    );*/

      Reflections reflections = new Reflections("com.qa", classLoader, new TypeAnnotationsScanner());
      Set<Class<?>> challengeClasses = reflections.getTypesAnnotatedWith(MarkerConstants.MARKER_MAPPING.get(marker), true);
      for (Class<?> className: challengeClasses) {
        classes.add(className);
      }
    }
    TestNGSuiteManager testNGSuite = new TestNGSuiteManager();
    testNGSuite.createTestNGSuite(classes);
  }
}
