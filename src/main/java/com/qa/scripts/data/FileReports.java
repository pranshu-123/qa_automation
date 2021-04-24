package com.qa.scripts.data;

import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.data.FileReportsPageObject;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import org.openqa.selenium.*;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public class FileReports {

  private WaitExecuter waitExecuter;
  private WebDriver driver;
  FileReportsPageObject fileReportsPageObject;
  private static final Logger LOGGER = Logger.getLogger(FileReports.class.getName());

  /**
   * Constructor to initialize wait, driver and necessary objects
   *
   * @param driver - WebDriver instance
   */
  public FileReports(WebDriver driver) {
    waitExecuter = new WaitExecuter(driver);
    this.driver = driver;
    fileReportsPageObject = new FileReportsPageObject(driver);
  }

  public void navidateTofileReportsTab() {
    WaitExecuter waitExecuter = new WaitExecuter(driver);
    SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
    TopPanelPageObject topPanelObj = new TopPanelPageObject(driver);
    MouseActions.clickOnElement(driver, topPanelComponentPageObject.jobs);
    waitExecuter.waitUntilPageFullyLoaded();

    MouseActions.clickOnElement(driver, topPanelComponentPageObject.data);
    waitExecuter.waitUntilElementClickable(topPanelObj.fileReportsTab);
    MouseActions.clickOnElement(driver, topPanelObj.fileReportsTab);
  }

  public void verifyClusterList(List<String> expectedClusterList) {
    WebElement clusterID = fileReportsPageObject.clusterDropDown;
    MouseActions.clickOnElement(driver, clusterID);
    waitExecuter.waitUntilPageFullyLoaded();
    List<WebElement> clusterList = fileReportsPageObject.clusterList;
    Assert.assertFalse(clusterList.isEmpty(), "ClusterList is empty");
    for (int i = 0; i < clusterList.size(); i++) {
      String clusterName = clusterList.get(i).getText();
      LOGGER.info("Cluster name is " + clusterName);
      Assert.assertTrue(expectedClusterList.contains(clusterName), " Cluster name doesnot match to the " +
          "cluster name in expected Cluster list");
    }
  }

  public void selectOnlySingleCluster(String clusterID) {
    WebElement clusterDD = fileReportsPageObject.clusterDropDown;
    MouseActions.clickOnElement(driver, clusterDD);
    List<WebElement> clusterList = fileReportsPageObject.clusterList;
    for (int i = 0; i < clusterList.size(); i++) {
      String clusterName = clusterList.get(i).getText();
      LOGGER.info("Selected cluster " + clusterName);
      if (clusterName.equals(clusterID)) {
        MouseActions.clickOnElement(driver, clusterList.get(i));
        waitExecuter.waitUntilElementClickable(fileReportsPageObject.searchField);
        break;
      }
    }
  }

  public void verifyFilesForSelectedCluster(String clusterID, String fileType) {
    WebElement clusterDD = fileReportsPageObject.clusterDropDown;
    MouseActions.clickOnElement(driver, clusterDD);
    List<WebElement> clusterList = fileReportsPageObject.clusterList;
    Assert.assertFalse(clusterList.isEmpty(), "ClusterList is empty");
    for (int i = 0; i < clusterList.size(); i++) {
      String clusterName = clusterList.get(i).getText();
      LOGGER.info("Selected cluster " + clusterName);
      MouseActions.clickOnElement(driver, clusterList.get(i));
      waitExecuter.waitUntilElementClickable(fileReportsPageObject.searchField);
      List<WebElement> tableHeaderList = fileReportsPageObject.tableHeader;
      List<WebElement> tableRows = fileReportsPageObject.fileTableRows;
      try {
        for (int row = 1; row <= tableRows.size(); row++) {
          for (int col = 1; col <= tableHeaderList.size(); col++) {
            WebElement rowData = driver.findElement
                (By.xpath("//table[@class='component-data-tables']/tbody/tr[" + row + "]/td[" + col + "]"));
            Assert.assertTrue(rowData.isDisplayed(), "No data under column: " + tableHeaderList.get(col).getText() +
                " for " + fileType + " file type");
          }
        }
      } catch (org.openqa.selenium.NoSuchElementException ex) {
        WebElement noData = fileReportsPageObject.noDataText;
        Assert.assertFalse(noData.isDisplayed(), "Data not present in the table got {'" + noData.getText() + "}' message");
      }
    }
  }

  public ArrayList<Integer> getFileCnt(List<WebElement> tableRowList, String fileType) {
    ArrayList<Integer> expectedFileCnt = new ArrayList<>();
    try {
      for (int row = 1; row <= tableRowList.size(); row++) {
        WebElement rowData = driver.findElement
            (By.xpath("//table[@class='component-data-tables']/tbody/tr[" + row + "]/td[" + 2 + "]"));
        Assert.assertTrue(rowData.isDisplayed(), "No data under column: File " +
            " for " + fileType + " file type");
        int fileCnt = Integer.parseInt(rowData.getText().trim());
        LOGGER.info("The file count is " + fileCnt);
        expectedFileCnt.add(fileCnt);
      }
    } catch (org.openqa.selenium.NoSuchElementException ex) {
      WebElement noData = fileReportsPageObject.noDataText;
      Assert.assertFalse(noData.isDisplayed(), "Data not present in the table got {'" + noData.getText() + "}' message");
    }
    return expectedFileCnt;
  }

  public void checkTableContainsData() {
    String expectedMsg = "No data to display.";
    List<WebElement> tableRows = fileReportsPageObject.fileTableRows;
    Assert.assertFalse(tableRows.isEmpty(), "Table contains no data");
    WebElement rowData = driver.findElement(By.xpath("//table[@class='component-data-tables row-hover']/tbody/" +
        "tr[" + 1 + "]/td[" + 1 + "]"));
    String rowDataStr = rowData.getText();
    Assert.assertFalse(rowDataStr.contains(expectedMsg), "Table contains no data. Got '" + expectedMsg + "' message");
  }

  /**
   * Method to validate the search option for different file types
   */
  public void verifyFilePathSearchOption(String fileType, String clusterID) {
    selectOnlySingleCluster(clusterID);
    List<WebElement> tableHeaderList = fileReportsPageObject.tableHeader;
    List<WebElement> tableRows = fileReportsPageObject.fileTableRows;
    String searchString = "/";
    checkTableContainsData();

    WebElement rowData = driver.findElement
        (By.xpath("//table[@class='component-data-tables row-hover']/tbody/tr[" + 1 + "]/td[" + 1 + "]"));
    Assert.assertTrue(rowData.isDisplayed(), "No data under column: " + tableHeaderList.get(1).getText() +
        " for " + fileType + " file type");
    searchString = rowData.getText();
    LOGGER.info("The search string is " + searchString);

    fileReportsPageObject.searchField.sendKeys(searchString);
    waitExecuter.waitUntilPageFullyLoaded();
    for (int row = 1; row <= tableRows.size(); row++) {
      WebElement searchRowData = driver.findElement
          (By.xpath("//table[@class='component-data-tables row-hover']/tbody/tr[" + row + "]/td[" + 1 + "]"));
      Assert.assertTrue(searchRowData.isDisplayed(), "No data under column: File " +
          " for " + fileType + " file type");
      LOGGER.info("Search String is " + searchString + " Search result is " + searchRowData.getText());
      Assert.assertTrue(searchRowData.getText().contains(searchString), "The search result for " + fileType + "" +
          " file type donot contain the search string\n Expected '" + searchString + "' to be present in '"
          + searchRowData.getText() + "' search result");
    }
  }

  /**
   * Method to verify the sort functionality on Column 'File' for different  file types.
   */
  public void verifyFileSortOption(String fileType, String clusterID) {
    selectOnlySingleCluster(clusterID);
    List<WebElement> tableRowList = fileReportsPageObject.fileTableRows;
    ArrayList<Integer> expectedFileCntArr = new ArrayList<>(), ascendingFileCntArr, descendingFileCntArr;
    WebElement fileCol = fileReportsPageObject.fileColumn;
    int rowCnt = tableRowList.size();
    if (rowCnt < 10) {
      expectedFileCntArr = getFileCnt(tableRowList, fileType);
    } else {
      String pageCntStr = fileReportsPageObject.pagination.getText().trim();
      int pageCnt = Integer.parseInt(pageCntStr.split("\\s+")[2]);
      LOGGER.info("The pagination is " + pageCntStr + " Page Cnt is " + pageCnt);
      for (int i = 1; i <= pageCnt; i++) {
        waitExecuter.waitUntilPageFullyLoaded();
        expectedFileCntArr.addAll(getFileCnt(tableRowList, fileType));
        if (i != pageCnt)
          MouseActions.clickOnElement(driver, fileReportsPageObject.rightCaretReportCnt);
      }
      MouseActions.clickOnElement(driver, fileReportsPageObject.backwardCaretReportCnt);
      waitExecuter.waitUntilElementClickable(fileReportsPageObject.searchField);
    }
    MouseActions.clickOnElement(driver, fileCol);
    waitExecuter.waitUntilPageFullyLoaded();
    ascendingFileCntArr = getFileCnt(tableRowList, fileType);
    MouseActions.clickOnElement(driver, fileCol);
    waitExecuter.waitUntilPageFullyLoaded();
    descendingFileCntArr = getFileCnt(tableRowList, fileType);
    Assert.assertEquals(ascendingFileCntArr.size(), descendingFileCntArr.size(), " Ascending sort and Descending sort" +
        " array size do not match");
    Collections.sort(expectedFileCntArr);
    ArrayList<Integer> newExpectedFileCnt = new ArrayList<>();
    LOGGER.info("The size of ascending arr is " + ascendingFileCntArr.size() +
        " expectedArr is " + expectedFileCntArr.size());
    for (int i = 0; i < ascendingFileCntArr.size(); i++) {
      newExpectedFileCnt.add(expectedFileCntArr.get(i));
    }
    LOGGER.info("Ascending Sort fileCnt is " + ascendingFileCntArr + "\n" +
        "Descending Sort fileCnt is " + descendingFileCntArr + "\n" +
        "Expected Sort fileCnt is " + newExpectedFileCnt);
    Assert.assertTrue(expectedFileCntArr.equals(ascendingFileCntArr) ||
        expectedFileCntArr.equals(descendingFileCntArr), "The expected array do not match");
  }
}
