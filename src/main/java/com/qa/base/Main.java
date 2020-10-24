package com.qa.base;

import com.qa.constants.ConfigConstants;
import com.qa.constants.MarkerConstants;
import org.reflections.Reflections;
import org.reflections.scanners.TypeAnnotationsScanner;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;

/**
 * @author Ankur Jaiswal
 */
public class Main {

  private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

  /**
   * Entrypoint of Execution. Pass the markers that you want to execute
   * Based on the marker it will pick all classed which are having these markers at run time
   * and pass it to TestNgSuite creater.
   * @param args
   * @throws MalformedURLException
   */
  public static void main(String[] args) throws MalformedURLException, ClassNotFoundException {
    String markers = System.getProperty(ConfigConstants.SystemConfig.MARKERS);
    Set<Class> classes = new TreeSet<>(Comparator.comparing(Class::getName));
    /**
     * Get the list of classes which are having provided markers
     */
    LOGGER.info("Get the list of classes which are having provided markers");
    URL sourceClassesURL = Paths.get("target/classes").toUri().toURL();
    URL testClassesURL = Paths.get("target/test-classes").toUri().toURL();
    URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{sourceClassesURL,testClassesURL});
    for (String marker : markers.split(",")) {
      Reflections reflections = new Reflections("com.qa", classLoader, new TypeAnnotationsScanner());
      Set<Class<?>> challengeClasses =
          reflections.getTypesAnnotatedWith(MarkerConstants.MARKER_MAPPING.get(marker.startsWith("!") ?
                  marker.substring(1) : marker), true);
      for (Class<?> className: challengeClasses) {
        if (marker.startsWith("!")) {
          classes.remove(className);
        } else {
          classes.add(className);
        }
      }
    }
    LOGGER.info("Creating TestNg.xml suite");
    TestNGSuiteManager testNGSuite = new TestNGSuiteManager();
    testNGSuite.createTestNGSuite(classes);
  }
}
