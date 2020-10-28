package com.qa.scripts.reports;

import com.qa.pagefactory.reports.ReportsArchiveScheduledPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.appdetails.SparkAppsDetailsPage;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.SingleTapAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ReportsArchiveSchedulePage {
  private WaitExecuter waitExecuter;
  private WebDriver driver;

  Logger logger = LoggerFactory.getLogger(ReportsArchiveSchedulePage.class);

  /**
   * Constructor to initialize wait, driver and necessary objects
   *
   * @param driver - WebDriver instance
   */
  public ReportsArchiveSchedulePage(WebDriver driver) {
    waitExecuter = new WaitExecuter(driver);
    this.driver = driver;
  }

  /**
   * Method to validate the report names listed in the Report Archive Page
   */
  public void validateReportNames(ReportsArchiveScheduledPageObject reportPageObj) {
    List<WebElement> reportNameList = reportPageObj.reportNames;
    Assert.assertFalse(reportNameList.isEmpty(), "There are no reports listed , expected 9 reports");
    String[] expectedReportNames = {"File Reports", "Small Files Report", "Cluster Discovery", "Top X",
        " Capacity Forecasting", "Cloud Mapping Per Host", "Services and Versions Compatibility", "Tuning", "Queue Analysis"};
    for (int i = 0; i < reportNameList.size(); i++) {
      String reportName = reportNameList.get(i).getText();
      logger.info("The report name is " + reportName);
      Assert.assertTrue(Arrays.asList(expectedReportNames).contains(reportName),
          "The expected report name is not present in the UI.");
    }
  }

  /**
   * Method to validate the report status listed in the Report Archive Page
   */
  public void validateReportStatus(ReportsArchiveScheduledPageObject reportPageObj) {
    List<WebElement> reportStatusList = reportPageObj.reportStatus;
    Assert.assertFalse(reportStatusList.isEmpty(), "There are no status against the reports");
    String[] expectedReportStatus = {"SUCCESS", "FAILURE", "NO REPORT"};
    for (int i = 0; i < reportStatusList.size(); i++) {
      String reportStatus = reportStatusList.get(i).getText();
      logger.info("The report status is " + reportStatus);
      Assert.assertTrue(Arrays.asList(expectedReportStatus).contains(reportStatus),
          "The expected report status is not present in the UI.");
    }
  }

  /**
   * Method to validate the search option on the Report Archive Page
   */
  public void validateSearchOption(ReportsArchiveScheduledPageObject reportPageObj) {
    reportPageObj.reportSearchBox.clear();
    reportPageObj.reportSearchBox.sendKeys("File");
    waitExecuter.sleep(1000);
    List<WebElement> reportNameList = reportPageObj.reportNames;
    Assert.assertFalse(reportNameList.isEmpty(), "There are no reports listed");
    Assert.assertEquals(reportNameList.size(), 2, "Expected the search result to output 2 records " +
        ",but actual is " + reportNameList.size());
  }

  /**
   * Method to validate the sorting option on the Report Archive Page
   */
  public void validateSortingOptionReportName(ReportsArchiveScheduledPageObject reportPageObj) {
    ArrayList<String> expectedReportNameArr = new ArrayList<>(), reportAsceArr = new ArrayList<>(), reportDscArr = new ArrayList<>();
    List<WebElement> reportNameList = reportPageObj.reportNames;
    Assert.assertFalse(reportNameList.isEmpty(), "There are no reports listed , expected 9 reports");
    for (int i = 0; i < reportNameList.size(); i++) {
      expectedReportNameArr.add(reportNameList.get(i).getText());
    }
    //Sort the created arraylist in ascending order
    Collections.sort(expectedReportNameArr);
    WebElement sortingIcon = reportPageObj.sortingReportNameIcon;
    MouseActions.clickOnElement(driver, sortingIcon);
    List<WebElement> sortedReportNameList1 = reportPageObj.reportNames;
    Assert.assertFalse(reportNameList.isEmpty(), "There are no reports listed , expected 9 reports");
    for (int s = 0; s < sortedReportNameList1.size(); s++) {
      reportAsceArr.add(sortedReportNameList1.get(s).getText());
    }
    // Click again to reverse sort;
    MouseActions.clickOnElement(driver, sortingIcon);
    List<WebElement> sortedReportNameList2 = reportPageObj.reportNames;
    Assert.assertFalse(reportNameList.isEmpty(), "There are no reports listed , expected 9 reports");
    for (int s = 0; s < sortedReportNameList2.size(); s++) {
      reportDscArr.add(sortedReportNameList2.get(s).getText());
    }
    Assert.assertTrue(expectedReportNameArr.equals(reportAsceArr) || expectedReportNameArr.equals(reportDscArr),
        "The sorting for reportName is not working");
  }

  /**
   * Method to validate the sorting option on the Report Archive Page
   */
  public void validateSortingOptionStatus(ReportsArchiveScheduledPageObject reportPageObj) {
    ArrayList<String> expectedReportStatusArr = new ArrayList<>(), reportAsceArr = new ArrayList<>(), reportDscArr = new ArrayList<>();
    List<WebElement> reportNameList = reportPageObj.reportStatus;
    Assert.assertFalse(reportNameList.isEmpty(), "There are no reports listed , expected 9 reports");
    for (int i = 0; i < reportNameList.size(); i++) {
      expectedReportStatusArr.add(reportNameList.get(i).getText());
    }
    //Sort the created arraylist in ascending order
    Collections.sort(expectedReportStatusArr);
    WebElement sortingIcon = reportPageObj.sortingStatusIcon;
    MouseActions.clickOnElement(driver, sortingIcon);
    List<WebElement> sortedStatusList1 = reportPageObj.reportStatus;
    Assert.assertFalse(reportNameList.isEmpty(), "There are no reports listed , expected 9 reports");
    for (int s = 0; s < sortedStatusList1.size(); s++) {
      reportAsceArr.add(sortedStatusList1.get(s).getText());
    }
    // Click again to reverse sort;
    MouseActions.clickOnElement(driver, sortingIcon);
    List<WebElement> sortedStatusList2 = reportPageObj.reportStatus;
    Assert.assertFalse(reportNameList.isEmpty(), "There are no reports listed , expected 9 reports");
    for (int s = 0; s < sortedStatusList2.size(); s++) {
      reportDscArr.add(sortedStatusList2.get(s).getText());
    }
    Assert.assertTrue(expectedReportStatusArr.equals(reportAsceArr) || expectedReportStatusArr.equals(reportDscArr),
        "The sorting for reportStatus is not working");
  }

  /**
   * Method to validate the sorting option on the Report Archive Page
   */
  public void validateSortingOptionReportCnt(ReportsArchiveScheduledPageObject reportPageObj) {
    ArrayList<String> expectedReportCntArr = new ArrayList<>(), reportAsceArr = new ArrayList<>(), reportDscArr = new ArrayList<>();
    List<WebElement> reportCntList = reportPageObj.reportCnt;
    Assert.assertFalse(reportCntList.isEmpty(), "There are no reports listed , expected 9 reports");
    for (int i = 0; i < reportCntList.size(); i++) {
      expectedReportCntArr.add(reportCntList.get(i).getText());
    }
    //Sort the created arraylist in ascending order
    Collections.sort(expectedReportCntArr);
    WebElement sortingIcon = reportPageObj.sortingReportCntIcon;
    MouseActions.clickOnElement(driver, sortingIcon);
    List<WebElement> sortedReportCnt1 = reportPageObj.reportCnt;
    Assert.assertFalse(reportCntList.isEmpty(), "There are no reports listed , expected 9 reports");
    for (int s = 0; s < sortedReportCnt1.size(); s++) {
      reportAsceArr.add(sortedReportCnt1.get(s).getText());
    }
    // Click again to reverse sort;
    MouseActions.clickOnElement(driver, sortingIcon);
    List<WebElement> sortedReportCnt2 = reportPageObj.reportCnt;
    Assert.assertFalse(reportCntList.isEmpty(), "There are no reports listed");
    for (int s = 0; s < sortedReportCnt2.size(); s++) {
      reportDscArr.add(sortedReportCnt2.get(s).getText());
    }
    Assert.assertTrue(expectedReportCntArr.equals(reportAsceArr) || expectedReportCntArr.equals(reportDscArr),
        "The sorting for reportCnt is not working");
  }

  public void validateReportCnt(ReportsArchiveScheduledPageObject reportPageObj){
    List<WebElement> reportCntList = reportPageObj.reportCnt;
    List<WebElement> reportNameList = reportPageObj.reportNames;
    Assert.assertFalse(reportCntList.isEmpty(), "No reports listed.");
    for (int i=0;i< reportCntList.size();i++){
      String reportName = reportNameList.get(i).getText();
      int reportCnt = Integer.parseInt(reportCntList.get(i).getText().trim());
      logger.info("The reportCnt is " + reportCnt);
      System.out.println("The reportCnt is " + reportCnt);
      if(reportCnt > 0){
        MouseActions.clickOnElement(driver, reportCntList.get(i));
        waitExecuter.sleep(2000);
        List<WebElement> rowList = reportPageObj.tableRows;
        Assert.assertEquals(rowList.size(), reportCnt, "The number of reports generated for "+ reportName +" " +
            "donot match to the count displayed in the Reports tab\n Expected = "+ reportCnt+ " Actual = " + rowList.size());

      }
    }
  }
}