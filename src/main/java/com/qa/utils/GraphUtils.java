package com.qa.utils;

import com.qa.pagefactory.clusters.ImpalaPageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ankur Jaiswal
 * This class contains methods to operate on graphs.
 */
public class GraphUtils {

  private List<String> memoryTooltipValues;
  private List<String> queriesTooltipValues;

  /**
   * Constuctor to initialize members
   */
  public GraphUtils() {
    memoryTooltipValues = new ArrayList<String>();
    queriesTooltipValues = new ArrayList<String>();
  }

  /**
   * This methods returns the memory tooltip values displayed at
   * different coordinates of memory/query graph
   * @return List of tooltip values
   */
  public List<String> getMemoryTooltipValues() {
    return memoryTooltipValues;
  }

  /**
   * This methods returns the query tooltip values displayed at
   * different coordinates of memory/query graph
   * @return List of tooltip values
   */
  public List<String> getQueriesTooltipValues() {
    return queriesTooltipValues;
  }

  /**
   * This method is used to navigate the user on different point
   * of the graph and add the tooltip values to list. User will
   * pass the graph element where they want to navigate.
   * User will navigate to 8 different point and save the tooltip values
   * Point values are decided by width of graph element and it calculate the incremental value
   * by dividing its width by 10.
   * @param driver - Instance of driver
   * @param graphElement - graph element where user wants to navigate
   */
  public void navigateDifferentPointOnGraph(
    WebDriver driver, WebElement graphElement) {
    WaitExecuter waitExecuter = new WaitExecuter(driver);
    int width = graphElement.getSize().getWidth();
    int incremental = width/10;
    int runningWidth = incremental;
    ActionPerformer actionPerformer = new ActionPerformer(driver);
    // When we use moveToElement, offsets are from the center of element
    actionPerformer.moveToTheElementByOffset(graphElement, 0, 0);
    waitExecuter.sleep(2000);
    ImpalaPageObject impalaPageObject = new ImpalaPageObject(driver);

    for (int i=0; i<4; i++) {
      /** Moving the cursor to the right of the graph since offsets are from
       the center of element **/
      actionPerformer.moveToTheElementByOffset(graphElement, runningWidth, 0);
      waitExecuter.sleep(1000);
      try {
        memoryTooltipValues.add(impalaPageObject.memoryTooltip.getText());
        queriesTooltipValues.add(impalaPageObject.queryTooltip.getText());
      } catch (NoSuchElementException noSuchElementException) {

      }
      /** Moving the cursor to the left of the graph since offsets are from
      the center of element **/
      actionPerformer.moveToTheElementByOffset(graphElement, -runningWidth, 0);
      waitExecuter.sleep(1000);
      try {
        memoryTooltipValues.add(impalaPageObject.memoryTooltip.getText());
        queriesTooltipValues.add(impalaPageObject.queryTooltip.getText());
      } catch (NoSuchElementException noSuchElementException) {

      }
      runningWidth += incremental;
    }
  }

  public void navigateDifferentPointOnGraphGetTextClickCheckImpalaTbl(WebDriver driver, WebElement graphElement) {
    WaitExecuter waitExecuter = new WaitExecuter(driver);

    int width = graphElement.getSize().getWidth();
    int height = graphElement.getSize().getHeight();

    int incrementalWidth = width/10;
    int incrementalHeight = height/10;

    int runningWidth = incrementalWidth;
    int runningHeight = incrementalHeight;

    ActionPerformer actionPerformer = new ActionPerformer(driver);
    // When we use moveToElement, offsets are from the center of element
    actionPerformer.moveToTheElementByOffset(graphElement, 0, 0);
    waitExecuter.sleep(2000);
    ImpalaPageObject impalaPageObject = new ImpalaPageObject(driver);

    for (int i=0; i<4; i++) {
      /** Moving the cursor to the left of the graph since offsets are from
       the center of element **/
      actionPerformer.moveToTheElementByOffset(graphElement, -runningWidth, 0);
      memoryTooltipValues.add(impalaPageObject.memoryTooltip.getText());
      //Move and click on the graph
      actionPerformer.moveToTheElementByOffsetAndClick(graphElement, -runningWidth, 0);

      waitExecuter.sleep(2000);
      if(impalaPageObject.impalaQueriesHeader.getText().contains("No Impala queries")){
        System.out.println("Impala query header :"+impalaPageObject.impalaQueriesHeader.getText());
      }else{
        System.out.println("Impala query header :"+impalaPageObject.impalaQueriesHeader.getText());
        //If Impala queries table populated then verufy for other details.

      }

      actionPerformer.moveToTheElementByOffset(graphElement, runningWidth, 0);
      memoryTooltipValues.add(impalaPageObject.memoryTooltip.getText());
      //Move and click on the graph
      actionPerformer.moveToTheElementByOffsetAndClick(graphElement, runningWidth, 0);

      waitExecuter.sleep(2000);
      if(!impalaPageObject.impalaQueriesHeader.getText().contains("No Impala queries")){
        System.out.println("Impala query header :"+impalaPageObject.impalaQueriesHeader.getText());
      }else{
        System.out.println("Impala query header :"+impalaPageObject.impalaQueriesHeader.getText());
      }
      runningWidth += incrementalWidth;
    }
  }

  /**
   * This methods search all elements inside a graph elements and get the colors
   * from all elements.
   * @param graphElement - Graph element
   * @return All colors present in the graph
   */
  public List<String> getGraphContentColors(WebElement graphElement) {
    List<WebElement> allNestedElement = graphElement.findElements(By.cssSelector("*"));
    List<String> graphColors = new ArrayList<>();
    for (WebElement element: allNestedElement) {
      if(element.getAttribute("stroke") != null &&
              !element.getAttribute("stroke").trim().equals("")) {
        if (element.getAttribute("stroke").startsWith("#")) {
          graphColors.add(ColorHelper.convertHexToRGB(element.getAttribute("stroke")));
        }
      }
    }
    return graphColors;
  }

}
