package com.qa.scripts.reports;

import com.qa.pagefactory.reports.ReportsArchiveScheduledPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.appdetails.SparkAppsDetailsPage;
import com.qa.scripts.clusters.Tuning;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.SingleTapAction;

import java.util.logging.Logger;

import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ReportsArchiveSchedulePage {
  private WaitExecuter waitExecuter;
  private WebDriver driver;

  Logger logger = Logger.getLogger(ReportsArchiveSchedulePage.class.getName());

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
   * Method to validate the Schedule reports Edit Action
   */
  public void validateScheduledReportEditAction(ReportsArchiveScheduledPageObject reportPageObj) {
    List<WebElement> reportNameList = reportPageObj.scheduleReport;
    String reportName = reportNameList.get(0).getText();
    logger.info("The reportType is " + reportName);
    Tuning tuning = new Tuning(driver);
    MouseActions.clickOnElement(driver, reportPageObj.editReportIcon);
    waitExecuter.sleep(2000);
    tuning.verifyScheduleToRun();
    if (reportName.equals("User Report"))
      MouseActions.clickOnElement(driver, reportPageObj.saveScheduleButton);
    else
      MouseActions.clickOnElement(driver, reportPageObj.reportCreationRunButton);
  }

  /**
   * Method to validate the Schedule reports Delete Action
   */
  public void validateScheduledReportDeleteAction(ReportsArchiveScheduledPageObject reportPageObj) {
    List<WebElement> reportNameList = reportPageObj.scheduleReport;
    String reportName = reportNameList.get(0).getText();
    String expectedBannerMsg = reportName + " report removed successfully.";
    logger.info("Expected Banner msg is " + expectedBannerMsg);
    int beforeScheduledReportCnt = getReportCnt(reportPageObj, 15);
    MouseActions.clickOnElement(driver, reportPageObj.deleteReportIcon);
    waitExecuter.sleep(2000);
    String bannerMsg = reportPageObj.successfulMsgBanner.getText();
    logger.info("Banner Msg after deleting the scheduled report is " + bannerMsg);
    MouseActions.clickOnElement(driver, reportPageObj.closeBanner);
    Assert.assertEquals(bannerMsg, expectedBannerMsg, " The bannerMsg is not equal to the expected " +
        "banner msg Expected: [" + expectedBannerMsg + "] Actual: [" + bannerMsg + "]");
    int afterScheduledReportCnt = getReportCnt(reportPageObj, 15);
    logger.info("The Scheduled report count  before deletion: " + beforeScheduledReportCnt + " " +
        "after deletion is " + afterScheduledReportCnt);
    Assert.assertEquals(beforeScheduledReportCnt - 1, afterScheduledReportCnt, "The count of scheduled " +
        "reports is not 1 less than the original count after successfull deletion of the scheduled report");
  }

  /**
   * Method to validate the Schedule reports More Info Action
   */
  public void validateScheduledReportMoreInfoAction(ReportsArchiveScheduledPageObject reportPageObj) {
    MouseActions.clickOnElement(driver, reportPageObj.viewReportIcon);
    String expectedHeader = "Scheduled Info";
    waitExecuter.sleep(1000);
    Assert.assertTrue(reportPageObj.moreInfoWin.isDisplayed(), "The pop up window with all the details " +
        "(parameters) of the report is not displayed");
    String header = reportPageObj.moreInfoHeader.getText();
    Assert.assertEquals(expectedHeader, header, " The header displayed in the pop up window is incorrect\n" +
        "Expected: " + expectedHeader + " but Actual is " + header);
    Assert.assertFalse(reportPageObj.moreInfoTableRows.isEmpty(), "No data displayed in the mre info pop up window.");
  }

  /**
   * Method to validate the Schedule report Page drop down list
   */
  public void validateScheduleReportDropDown(ReportsArchiveScheduledPageObject reportPageObj) {
    waitExecuter.sleep(1000);
    MouseActions.clickOnElement(driver, reportPageObj.scheduleReportDropDown);
    waitExecuter.sleep(1000);
    List<WebElement> dropDownList = reportPageObj.dropDownList;
    Assert.assertFalse(dropDownList.isEmpty(), "There are no reports in the drop down list as it is empty");
    boolean isContainsAllOpt = false;
    int otherReportTotal = 0;
    int totalReports = 0;
    logger.info("The dropDownlist size = " + dropDownList.size());
    for (int i = 0; i < dropDownList.size(); i++) {
      String reportType = dropDownList.get(i).getText();
      logger.info("reportType = " + reportType);
      if (dropDownList.get(i).getText().contains("All")) {
        MouseActions.clickOnElement(driver, dropDownList.get(i));
        isContainsAllOpt = true;
        totalReports = getReportCnt(reportPageObj, 15);
      } else {
        logger.info("Click on reportType: " + reportType);
        MouseActions.clickOnElement(driver, dropDownList.get(i));
        waitExecuter.sleep(1000);
        otherReportTotal += getReportCnt(reportPageObj, 15);
        logger.info("The other report cnt is " + otherReportTotal);
      }
      MouseActions.clickOnElement(driver, reportPageObj.scheduleReportDropDown);
      waitExecuter.sleep(1000);
    }
    Assert.assertTrue(isContainsAllOpt, "There is no option 'All' in the dropdown list of size"
        + dropDownList.size() + " for Scheduled reports");
    logger.info("Total Report Cnt = " + totalReports + "\n Other Report Cnt = " + otherReportTotal);
    Assert.assertEquals(totalReports, otherReportTotal, "All report types are not present in the dropdown list");
  }

  public int getReportCnt(ReportsArchiveScheduledPageObject reportPageObj, Integer rowCnt) {
    int totalCnt = 0;
    List<WebElement> rowList = null;
    List<WebElement> reportTypeList = reportPageObj.reportCnt;
    Assert.assertFalse(reportTypeList.isEmpty(), " The report type column is empty");
    logger.info("The report type count is " + reportTypeList.size());
    if (reportTypeList.size() > 10) {
      String pageCntStr = reportPageObj.reportCntPerPage.getText().trim();
      logger.info("The pageCnt is " + pageCntStr);
      //output= Page: of 173
      int pageCnt = Integer.parseInt(pageCntStr.split("\\s+")[2]);
      logger.info("The integer page count is " + pageCnt);
      //Per page there are 10 rows , multiply 10 x pageCnt - 1, click the forward caret and go tot the
      // last page,cnt the last page row cnt and add it to the total
      totalCnt = rowCnt * (pageCnt - 1);
      MouseActions.clickOnElement(driver, reportPageObj.fwdCaretReportCnt);
      waitExecuter.sleep(2000);
      rowList = reportPageObj.tableRows;
      totalCnt += rowList.size();
      logger.info("The total report cnt is " + totalCnt);
    } else {
      rowList = reportPageObj.tableRows;
      totalCnt = rowList.size();
    }
    logger.info("The total number of reports are  " + totalCnt);
    MouseActions.clickOnElement(driver, reportPageObj.backwardCaretReportCnt);
    waitExecuter.sleep(2000);
    return totalCnt;
  }

  /**
   * Method to validate the Schedule report Page
   */
  public void validateScheduleReportPage(ReportsArchiveScheduledPageObject reportPageObj) {
    List<WebElement> scheduleNameList = reportPageObj.scheduleName;
    Assert.assertFalse(scheduleNameList.isEmpty(), "There are no schedule report name");
    List<WebElement> scheduleReportList = reportPageObj.scheduleReport;
    Assert.assertFalse(scheduleReportList.isEmpty(), "There are no schedule report");
    List<WebElement> nextScheduleRunList = reportPageObj.nextScheduledRun;
    Assert.assertFalse(nextScheduleRunList.isEmpty(), "There are no reports scheduled for next run");
    List<WebElement> scheduleActionList = reportPageObj.scheduleActions;
    Assert.assertFalse(scheduleActionList.isEmpty(), "There are no schedule actions");
  }

  /**
   * Method to validate the report names listed in the Report Archive Page
   */
  public void validateReportNames(ReportsArchiveScheduledPageObject reportPageObj) {
    List<WebElement> reportNameList = reportPageObj.reportNames;
    Assert.assertFalse(reportNameList.isEmpty(), "There are no reports listed , expected 9 reports");
    String[] expectedReportNames = {"File Reports", "Small Files Report", "Cluster Discovery", "Top X",
        "Capacity Forecasting", "Cloud Mapping Per Host", "Services and Versions Compatibility", "Tuning", "Queue Analysis"};
    for (int i = 0; i < reportNameList.size(); i++) {
      String reportName = reportNameList.get(i).getText().trim();
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
    // reportPageObj.reportSearchBox.clear();
    reportPageObj.reportSearchBox.sendKeys("File");
    waitExecuter.sleep(1000);
    List<WebElement> reportNameList = reportPageObj.reportNames;
    Assert.assertFalse(reportNameList.isEmpty(), "There are no reports listed");
    Assert.assertEquals(reportNameList.size(), 2, "Expected the search result to output 2 records " +
        ",but actual is " + reportNameList.size());
  }

  /**
   * Method to populate the expected array based on different columns in Report Archive
   */
  public void validateSortForDiffReportCol(List<WebElement> colList, List<WebElement> reportCntList, ReportsArchiveScheduledPageObject reportPageObj
      , String colName) {

    ArrayList<String> expectedReportNameArr = new ArrayList<>(), reportAsceArr = new ArrayList<>(),
        reportDscArr = new ArrayList<>();

    for (int i = 0; i < colList.size(); i++) {
      int reportCnt = Integer.parseInt(reportCntList.get(i).getText().trim());
      logger.info("ReportCnt is " + reportCnt);
      if (reportCnt < 50 && reportCnt > 10) {
        MouseActions.clickOnElement(driver, colList.get(i));
        String pageCntStr = reportPageObj.reportCntPerPage.getText().trim();
        int pageCnt = Integer.parseInt(pageCntStr.split("\\s+")[2]);
        for (int p = 1; p <= pageCnt; p++) {
          List<WebElement> perReportList = null;
          if (colName.equals("Name"))
            perReportList = reportPageObj.reportNames;
          else if (colName.equals("Status"))
            perReportList = reportPageObj.reportStatus;
          else {
            perReportList = reportPageObj.reportCnt;
          }
          for (int r = 0; r < perReportList.size(); r++) {
            expectedReportNameArr.add(perReportList.get(r).getText());
          }
          waitExecuter.sleep(2000);
          if (p != pageCnt)
            MouseActions.clickOnElement(driver, reportPageObj.rightCaretReportCnt);
          waitExecuter.sleep(1000);
        }
        MouseActions.clickOnElement(driver, reportPageObj.backwardCaretReportCnt);
        waitExecuter.sleep(2000);
        if (colName.equals("Name")) {
          WebElement sortingIcon = reportPageObj.sortingReportNameIcon;
          sortByReportName(reportPageObj, expectedReportNameArr, reportAsceArr, reportDscArr, colList, sortingIcon);
        } else if (colName.equals("Status"))
          sortByStatus(reportPageObj, expectedReportNameArr, reportAsceArr, reportDscArr, colList);
        else {
          sortByCreatedCnt(reportPageObj, expectedReportNameArr, reportAsceArr, reportDscArr, colList);
          // sortByReportCnt(reportPageObj, expectedReportCntArr, reportCntAsceArr, reportCntDscArr, colList);
        }
        MouseActions.clickOnElement(driver, reportPageObj.goBackButton);
        logger.info("Clicked the Go Back Button");
        waitExecuter.sleep(3000);
      } else if (reportCnt <= 10) {
        MouseActions.clickOnElement(driver, colList.get(i));
        for (int j = 0; j < colList.size(); j++) {
          expectedReportNameArr.add(colList.get(j).getText().trim());
        }
        if (colName.equals("Name")) {
          WebElement sortingIcon = reportPageObj.sortingReportNameIcon;
          sortByReportName(reportPageObj, expectedReportNameArr, reportAsceArr, reportDscArr, colList, sortingIcon);
        } else if (colName.equals("Status"))
          sortByStatus(reportPageObj, expectedReportNameArr, reportAsceArr, reportDscArr, colList);
        else
          sortByCreatedCnt(reportPageObj, expectedReportNameArr, reportAsceArr, reportDscArr, colList);
        // sortByReportCnt(reportPageObj, expectedReportNameArr, reportAsceArr, reportDscArr, colList);
        MouseActions.clickOnElement(driver, reportPageObj.goBackButton);
        logger.info("Clicked the Go Back Button");
        waitExecuter.sleep(3000);
      }
    }
  }

  /**
   * Method to validate the sorting option on the Report Archive Page
   */
  public void validateSortingOptionReportName(ReportsArchiveScheduledPageObject reportPageObj, boolean perReport) {
    ArrayList<String> expectedReportNameArr = new ArrayList<>(), reportAsceArr = new ArrayList<>(), reportDscArr = new ArrayList<>();
    List<WebElement> reportNameList = reportPageObj.reportNames;
    List<WebElement> reportCntList = reportPageObj.reportCnt;
    WebElement sortingIcon = reportPageObj.sortingReportNameIcon;
    List<WebElement> sortByReportName = reportPageObj.reportNames;
    Assert.assertFalse(reportNameList.isEmpty(), "There are no reports listed , expected 9 reports");
    if (perReport) {
      validateSortForDiffReportCol(reportNameList, reportCntList, reportPageObj, "Name");
    } else {
      for (int i = 0; i < reportNameList.size(); i++) {
        expectedReportNameArr.add(reportNameList.get(i).getText());
      }
      sortByReportName(reportPageObj, expectedReportNameArr, reportAsceArr, reportDscArr, reportNameList,
          sortingIcon);
    }
  }

  /**
   * Method to validate the sorting option on Report Type for Scheduled Page
   */
  public void validateSortingOptionReportType(ReportsArchiveScheduledPageObject reportPageObj) {
    ArrayList<String> expectedReportNameArr = new ArrayList<>(), reportAsceArr = new ArrayList<>(), reportDscArr = new ArrayList<>();
    List<WebElement> reportTypeList = reportPageObj.reportType;
    WebElement sortingIcon = reportPageObj.sortingReportCntIcon;
    List<WebElement> sortByReportType = reportPageObj.reportType;
    Assert.assertFalse(reportTypeList.isEmpty(), "There are no reports listed");
    for (int i = 0; i < reportTypeList.size(); i++) {
      expectedReportNameArr.add(reportTypeList.get(i).getText());
    }
    sortByReportName(reportPageObj, expectedReportNameArr, reportAsceArr, reportDscArr, reportTypeList,
        sortingIcon);
  }

  /**
   * Method to validate the sorting option on Next Scheduled Run for Scheduled Page
   */
  public void validateSortingOptionScheduledRun(ReportsArchiveScheduledPageObject reportPageObj) {
    ArrayList<String> expectedReportNameArr = new ArrayList<>(), reportAsceArr = new ArrayList<>(), reportDscArr = new ArrayList<>();
    List<WebElement> nextScheduledRunList = reportPageObj.nextScheduledRun;
    WebElement sortingIcon = reportPageObj.sortingStatusIcon;
    List<WebElement> sortByNextScheduledRun = reportPageObj.reportType;
    Assert.assertFalse(nextScheduledRunList.isEmpty(), "There are no reports scheduled for net run");
    for (int i = 0; i < nextScheduledRunList.size(); i++) {
      expectedReportNameArr.add(nextScheduledRunList.get(i).getText());
    }
    sortByReportName(reportPageObj, expectedReportNameArr, reportAsceArr, reportDscArr, nextScheduledRunList, sortingIcon);
  }

  /**
   * Method to sort by Report name column
   */
  public void sortByReportName(ReportsArchiveScheduledPageObject reportPageObj, ArrayList<String> expectedReportNameArr,
                               ArrayList<String> reportAsceArr, ArrayList<String> reportDscArr,
                               List<WebElement> reportNameList, WebElement sortingIcon) {
    //Sort the created arraylist in ascending order
    Collections.sort(expectedReportNameArr);
    ArrayList<String> newExpectedReportNameArr = new ArrayList<String>();
    MouseActions.clickOnElement(driver, sortingIcon);
    Assert.assertFalse(reportNameList.isEmpty(), "There are no reports listed");
    reportAsceArr.clear();
    reportDscArr.clear();
    for (int s = 0; s < reportNameList.size(); s++) {
      reportAsceArr.add(reportNameList.get(s).getText());
      logger.info("The ascending list is " + reportNameList.get(s).getText());
    }
    // Click again to reverse sort;
    MouseActions.clickOnElement(driver, sortingIcon);
    List<WebElement> sortedReportNameList2 = reportPageObj.reportNames;
    Assert.assertFalse(reportNameList.isEmpty(), "There are no reports listed ");
    for (int s = 0; s < sortedReportNameList2.size(); s++) {
      reportDscArr.add(sortedReportNameList2.get(s).getText());
      logger.info("The descending list is " + sortedReportNameList2.get(s).getText());
    }
    for (int i = 0; i < reportDscArr.size(); i++) {
      newExpectedReportNameArr.add(expectedReportNameArr.get(i));
      logger.info("The Actual list is " + expectedReportNameArr.get(i));
    }
    Assert.assertTrue(newExpectedReportNameArr.equals(reportAsceArr) || newExpectedReportNameArr.equals(reportDscArr),
        "The sorting for reportName is not working");
    expectedReportNameArr.clear();
    newExpectedReportNameArr.clear();
    reportAsceArr.clear();
    reportDscArr.clear();
  }

  /**
   * Method to validate the sorting option on the Report Archive Page
   */
  public void validateSortingOptionStatus(ReportsArchiveScheduledPageObject reportPageObj, Boolean perReport) {
    ArrayList<String> expectedReportStatusArr = new ArrayList<>(), reportAsceArr = new ArrayList<>(), reportDscArr = new ArrayList<>();
    List<WebElement> reportStatusList = reportPageObj.reportStatus;
    List<WebElement> reportCntList = reportPageObj.reportCnt;
    Assert.assertFalse(reportStatusList.isEmpty(), "There are no reports listed , expected 9 reports");
    if (perReport) {
      validateSortForDiffReportCol(reportStatusList, reportCntList, reportPageObj, "Status");
    } else {
      for (int i = 0; i < reportStatusList.size(); i++) {
        expectedReportStatusArr.add(reportStatusList.get(i).getText());
      }
      sortByStatus(reportPageObj, expectedReportStatusArr, reportAsceArr, reportDscArr, reportStatusList);
    }
  }

  /**
   * Method to sort by Report Status column
   */
  public void sortByStatus(ReportsArchiveScheduledPageObject reportPageObj, ArrayList<String> expectedReportStatusArr,
                           ArrayList<String> reportAsceArr, ArrayList<String> reportDscArr, List<WebElement> reportStatusList) {
    //Sort the created arraylist in ascending order
    Collections.sort(expectedReportStatusArr);
    WebElement sortingIcon = reportPageObj.sortingStatusIcon;
    MouseActions.clickOnElement(driver, sortingIcon);
    List<WebElement> sortedStatusList1 = reportPageObj.reportStatus;
    Assert.assertFalse(reportStatusList.isEmpty(), "There are no reports listed , expected 9 reports");
    for (int s = 0; s < sortedStatusList1.size(); s++) {
      reportAsceArr.add(sortedStatusList1.get(s).getText().trim());
    }
    // Click again to reverse sort;
    MouseActions.clickOnElement(driver, sortingIcon);
    List<WebElement> sortedStatusList2 = reportPageObj.reportStatus;
    Assert.assertFalse(reportStatusList.isEmpty(), "There are no reports listed , expected 9 reports");
    for (int s = 0; s < sortedStatusList2.size(); s++) {
      reportDscArr.add(sortedStatusList2.get(s).getText().trim());
    }
    ArrayList<String> newExpectedReportNameArr = new ArrayList<String>();
    for (int i = 0; i < reportDscArr.size(); i++) {
      newExpectedReportNameArr.add(expectedReportStatusArr.get(i));
    }
    for (int i = 0; i < newExpectedReportNameArr.size(); i++) {
      logger.info("expected " + newExpectedReportNameArr.get(i));
      logger.info("Asc " + reportAsceArr.get(i));
      logger.info("Dsc " + reportDscArr.get(i));
    }
    Assert.assertTrue(newExpectedReportNameArr.equals(reportAsceArr) || newExpectedReportNameArr.equals(reportDscArr),
        "The sorting for reportStatus is not working");
    expectedReportStatusArr.clear();
    newExpectedReportNameArr.clear();
    reportAsceArr.clear();
    reportDscArr.clear();
  }

  /**
   * Method to validate the sorting option on the Report Archive Page
   */
  public void validateSortingOptionReportCnt(ReportsArchiveScheduledPageObject reportPageObj, Boolean perReport) {
    ArrayList<Integer> expectedReportCntArr = new ArrayList<>(), reportAsceArr = new ArrayList<>(), reportDscArr = new ArrayList<>();
    List<WebElement> reportCntList = reportPageObj.reportCnt;
    Assert.assertFalse(reportCntList.isEmpty(), "There are no reports listed , expected 9 reports");
    if (perReport) {
      validateSortForDiffReportCol(reportCntList, reportCntList, reportPageObj, "Count");
    } else {
      for (int i = 0; i < reportCntList.size(); i++) {
        expectedReportCntArr.add(Integer.parseInt(reportCntList.get(i).getText().trim()));
      }
      sortByReportCnt(reportPageObj, expectedReportCntArr, reportAsceArr, reportDscArr, reportCntList);
    }
  }

  /**
   * Method to sort by Report Cnt
   */
  public void sortByReportCnt(ReportsArchiveScheduledPageObject reportPageObj, ArrayList<Integer> expectedReportCntArr,
                              ArrayList<Integer> reportAsceArr, ArrayList<Integer> reportDscArr, List<WebElement> reportCntList) {
    //Sort the created arraylist in ascending order
    Collections.sort(expectedReportCntArr);
    WebElement sortingIcon = reportPageObj.sortingReportCntIcon;
    MouseActions.clickOnElement(driver, sortingIcon);
    List<WebElement> sortedReportCnt1 = reportPageObj.reportCnt;
    Assert.assertFalse(reportCntList.isEmpty(), "There are no reports listed , expected 9 reports");
    for (int s = 0; s < sortedReportCnt1.size(); s++) {
      reportAsceArr.add(Integer.parseInt(sortedReportCnt1.get(s).getText().trim()));
    }
    // Click again to reverse sort;
    MouseActions.clickOnElement(driver, sortingIcon);
    List<WebElement> sortedReportCnt2 = reportPageObj.reportCnt;
    Assert.assertFalse(reportCntList.isEmpty(), "There are no reports listed");
    for (int s = 0; s < sortedReportCnt2.size(); s++) {
      reportDscArr.add(Integer.parseInt(sortedReportCnt2.get(s).getText().trim()));
    }
    ArrayList<Integer> newExpectedReportNameArr = new ArrayList<Integer>();
    for (int i = 0; i < reportDscArr.size(); i++) {
      newExpectedReportNameArr.add(expectedReportCntArr.get(i));
    }
    for (int i = 0; i < newExpectedReportNameArr.size(); i++) {
      logger.info("expected " + newExpectedReportNameArr.get(i));
      logger.info("Asc " + reportAsceArr.get(i));
      logger.info("Dsc " + reportDscArr.get(i));
    }
    Assert.assertTrue(newExpectedReportNameArr.equals(reportAsceArr) || newExpectedReportNameArr.equals(reportDscArr),
        "The sorting for reportCnt is not working");
    expectedReportCntArr.clear();
    newExpectedReportNameArr.clear();
    reportAsceArr.clear();
    reportDscArr.clear();
  }

  /**
   * Method to sort by  Created column
   */
  public void sortByCreatedCnt(ReportsArchiveScheduledPageObject reportPageObj, ArrayList<String> expectedReportCntArr,
                               ArrayList<String> reportAsceArr, ArrayList<String> reportDscArr, List<WebElement> reportCntList) {
    //Sort the created arraylist in ascending order
    Collections.sort(expectedReportCntArr);
    WebElement sortingIcon = reportPageObj.sortingReportCntIcon;
    MouseActions.clickOnElement(driver, sortingIcon);
    List<WebElement> sortedReportCnt1 = reportPageObj.reportCnt;
    Assert.assertFalse(reportCntList.isEmpty(), "There are no reports listed , expected 9 reports");
    for (int s = 0; s < sortedReportCnt1.size(); s++) {
      reportAsceArr.add(sortedReportCnt1.get(s).getText().trim());
    }
    // Click again to reverse sort;
    MouseActions.clickOnElement(driver, sortingIcon);
    List<WebElement> sortedReportCnt2 = reportPageObj.reportCnt;
    Assert.assertFalse(reportCntList.isEmpty(), "There are no reports listed");
    for (int s = 0; s < sortedReportCnt2.size(); s++) {
      reportDscArr.add(sortedReportCnt2.get(s).getText().trim());
    }
    ArrayList<String> newExpectedReportNameArr = new ArrayList<String>();
    for (int i = 0; i < reportDscArr.size(); i++) {
      newExpectedReportNameArr.add(expectedReportCntArr.get(i));
    }
    for (int i = 0; i < newExpectedReportNameArr.size(); i++) {
      logger.info("expected " + newExpectedReportNameArr.get(i));
      logger.info("Asc " + reportAsceArr.get(i));
      logger.info("Dsc " + reportDscArr.get(i));
    }
    Assert.assertTrue(newExpectedReportNameArr.equals(reportAsceArr) || newExpectedReportNameArr.equals(reportDscArr),
        "The sorting for reportCnt is not working");
    expectedReportCntArr.clear();
    newExpectedReportNameArr.clear();
    reportAsceArr.clear();
    reportDscArr.clear();
  }

  /**
   * Method to validate the Report count
   */
  public void validateReportCnt(ReportsArchiveScheduledPageObject reportPageObj) {
    List<WebElement> reportCntList = reportPageObj.reportCnt;
    List<WebElement> reportNameList = reportPageObj.reportNames;
    Assert.assertFalse(reportCntList.isEmpty(), "No reports listed.");
    for (int i = 0; i < reportCntList.size(); i++) {
      String reportName = reportNameList.get(i).getText();
      int reportCnt = Integer.parseInt(reportCntList.get(i).getText().trim());
      int totalCnt = 0;
      List<WebElement> rowList = null;
      logger.info("The reportCnt is " + reportCnt);
      if (reportCnt > 0) {
        MouseActions.clickOnElement(driver, reportCntList.get(i));
        waitExecuter.sleep(2000);
        if (reportCnt > 10) {
          String pageCntStr = reportPageObj.reportCntPerPage.getText().trim();
          logger.info("The pageCnt is " + pageCntStr);
          //output= Page: of 173
          int pageCnt = Integer.parseInt(pageCntStr.split("\\s+")[2]);
          logger.info("The integer page count is " + pageCnt);
          //Per page there are 10 rows , multiply 10 x pageCnt - 1, click the forward caret and go tot the
          // last page,cnt the last page row cnt and add it to the total
          totalCnt = 10 * (pageCnt - 1);
          MouseActions.clickOnElement(driver, reportPageObj.fwdCaretReportCnt);
          waitExecuter.sleep(2000);
          rowList = reportPageObj.tableRows;
          totalCnt += rowList.size();
          logger.info("The total report cnt is " + totalCnt);
        } else {
          rowList = reportPageObj.tableRows;
          totalCnt = rowList.size();
        }
        Assert.assertEquals(totalCnt, reportCnt, "The number of reports generated for " + reportName + " " +
            "donot match to the count displayed in the Reports tab\n Expected = " + reportCnt + " Actual = " + totalCnt);
        MouseActions.clickOnElement(driver, reportPageObj.goBackButton);
      } else {
        MouseActions.clickOnElement(driver, reportCntList.get(i));
        waitExecuter.sleep(2000);
        String msg = reportPageObj.noDataToDisplay.getText();
        String expectedMsg = "No data to display";
        Assert.assertTrue(msg.contains(expectedMsg), "Proper msg not displayed \n Expected = " + expectedMsg +
            " Actual = " + msg);
        MouseActions.clickOnElement(driver, reportPageObj.goBackButton);
      }
    }
  }

  /**
   * Method to validate latest report on selected date range option from actions tab
   */
  public void validateLatestReportAction(ReportsArchiveScheduledPageObject reportPageObj) {
    List<WebElement> reportNameList = reportPageObj.reportNames;
    List<WebElement> latestReportActionList = reportPageObj.latestReportIcon;
    List<WebElement> reportStatusList = reportPageObj.reportStatus;
    for (int i = 0; i < reportNameList.size(); i++) {
      String reportName = reportNameList.get(i).getText().trim();
      logger.info("The report is " + reportName);
      String status = reportStatusList.get(i).getText();
      MouseActions.clickOnElement(driver, latestReportActionList.get(i));
      waitExecuter.sleep(2000);
      String actualHeader = reportPageObj.latestReportHeader.getText();
      logger.info("The header is " + actualHeader);
      String expectedHeader = "LATEST SUCCESSFUL " + reportName.toUpperCase() + " REPORT";
      if (status.equals("SUCCESS")) {
        Assert.assertEquals(expectedHeader, actualHeader, " The latest report donot match for = " +
            reportName + " with status = " + status + " \n Expected = " + expectedHeader + " Actual = " + actualHeader);
      } else if (status.equals("FAILURE") || status.equals("NO REPORTS")) {
        Assert.assertTrue(reportPageObj.noDataToDisplay.isDisplayed(), " Expected No Data to be displayed msg");
      }
      MouseActions.clickOnElement(driver, reportPageObj.closeTab);
      waitExecuter.sleep(2000);
    }
  }

  /**
   * Method to validate tge new report option from actions tab
   */
  public void validateNewReportAction(ReportsArchiveScheduledPageObject reportPageObj) {
    List<WebElement> reportNameList = reportPageObj.reportNames;
    List<WebElement> newReportActionList = reportPageObj.newReportIcon;
    List<WebElement> reportStatusList = reportPageObj.reportStatus;
    List<WebElement> reportCntList = reportPageObj.reportCnt;
    for (int i = 0; i < reportNameList.size(); i++) {
      String reportName = reportNameList.get(i).getText().trim();
      logger.info("The report is " + reportName);
      String status = "";
      int beforeReportCnt = Integer.parseInt(reportCntList.get(i).getText().trim());
      int expectedAfterCnt = beforeReportCnt + 1;
      int afterReportCnt = 0;
      switch (reportName) {
        case "File Reports":
          MouseActions.clickOnElement(driver, newReportActionList.get(i));
          waitExecuter.sleep(1000);
          String bannerMsg = reportPageObj.reportCreationNotSup.getText().trim();
          logger.info("Msg = " + bannerMsg + " for Report = " + reportName + " with status = " + status);
          break;
        case "Small Files Report":
          MouseActions.clickOnElement(driver, newReportActionList.get(i));
          waitExecuter.sleep(1000);
          List<WebElement> fieldList = reportPageObj.newReportField;
          String[] valueArr = {"10", "20", "2", "2"};
          for (int f = 0; f < fieldList.size(); f++) {
            fieldList.get(f).sendKeys(valueArr[f]);
          }
          MouseActions.clickOnElement(driver, reportPageObj.reportCreationRunButton);
          waitExecuter.sleep(30000);
          driver.navigate().refresh();
          status = reportStatusList.get(i).getText().trim();
          afterReportCnt = Integer.parseInt(reportCntList.get(i).getText().trim());
          logger.info("Before cnt = " + beforeReportCnt + " After cnt = " + afterReportCnt);
          break;
        case "Top X":
          MouseActions.clickOnElement(driver, newReportActionList.get(i));
          waitExecuter.sleep(1000);
          List<WebElement> fieldsList = reportPageObj.topXtextFields;
          for (int f = 0; f < fieldsList.size(); f++) {
            MouseActions.clickOnElement(driver, fieldsList.get(f));
            MouseActions.clickOnElement(driver, reportPageObj.topXFieldValue);
            waitExecuter.sleep(1000);
          }
          List<WebElement> chkboxList = reportPageObj.checkBoxSelections;
          for (int c = 0; c < chkboxList.size(); c++) {
            if (chkboxList.get(c).isSelected())
              MouseActions.clickOnElement(driver, chkboxList.get(c));
            else
              MouseActions.clickOnElement(driver, chkboxList.get(c));
            MouseActions.clickOnElement(driver, reportPageObj.tagListFields);
            MouseActions.clickOnElement(driver, reportPageObj.topXFieldValue);
            waitExecuter.sleep(1000);
          }
          MouseActions.clickOnElement(driver, reportPageObj.reportCreationRunButton);
          waitExecuter.sleep(30000);
          driver.navigate().refresh();
          status = reportStatusList.get(i).getText().trim();
          afterReportCnt = Integer.parseInt(reportCntList.get(i).getText().trim());
          logger.info("Before cnt = " + beforeReportCnt + " After cnt = " + afterReportCnt);
          break;
        case "Cloud Mapping Per Host":
          MouseActions.clickOnElement(driver, newReportActionList.get(i));
          waitExecuter.sleep(25000);
          MouseActions.clickOnElement(driver, reportPageObj.cloudMappingChkBox);
          waitExecuter.sleep(1000);
          MouseActions.clickOnElement(driver, reportPageObj.reportCreationRunButton);
          waitExecuter.sleep(40000);
          driver.navigate().refresh();
          status = reportStatusList.get(i).getText().trim();
          afterReportCnt = Integer.parseInt(reportCntList.get(i).getText().trim());
          logger.info("Before cnt = " + beforeReportCnt + " After cnt = " + afterReportCnt);
          break;
        case "Cluster Discovery":
        case "Tuning":
        case "Capacity Forecasting":
        case "Queue Analysis":
        case "Services and Versions Compatibility":
          MouseActions.clickOnElement(driver, newReportActionList.get(i));
          waitExecuter.sleep(2000);
          MouseActions.clickOnElement(driver, reportPageObj.reportCreationRunButton);
          waitExecuter.sleep(40000);
          status = reportStatusList.get(i).getText().trim();
          driver.navigate().refresh();
          afterReportCnt = Integer.parseInt(reportCntList.get(i).getText().trim());
          logger.info("Before cnt = " + beforeReportCnt + " After cnt = " + afterReportCnt);
      }
      Assert.assertEquals(expectedAfterCnt, afterReportCnt, " The report cnt do not match for report Name = " +
          reportName + " with status = " + status + " \n Expected = " + expectedAfterCnt + " Actual = " + afterReportCnt);
    }
  }

  /**
   * Method to validate download, view report and remove options from actions tab
   */
  public void validateReportActions(ReportsArchiveScheduledPageObject reportPageObj) {
    List<WebElement> reportNameList = reportPageObj.reportNames;
    List<WebElement> reportCntList = reportPageObj.reportCnt;
    for (int i = 0; i < reportNameList.size(); i++) {
      int reportCnt = Integer.parseInt(reportCntList.get(i).getText().trim());
      String reportName = reportNameList.get(i).getText();
      logger.info("ReportCnt is " + reportCnt);
      if (reportCnt > 0) {
        MouseActions.clickOnElement(driver, reportCntList.get(i));
        waitExecuter.sleep(1000);
        MouseActions.clickOnElement(driver, reportPageObj.downloadReportIcon);
        waitExecuter.sleep(1000);
        Assert.assertEquals(reportPageObj.successfulMsgBanner.getText(), "Downloaded successfully",
            " No downloaded successfully message received.");

        MouseActions.clickOnElement(driver, reportPageObj.viewReportIcon);
        waitExecuter.sleep(1000);
        Assert.assertTrue(reportPageObj.viewReportDialogWin.isDisplayed(), "Report  view not present.");
        MouseActions.clickOnElement(driver, reportPageObj.closeTab);
        waitExecuter.sleep(1000);

        MouseActions.clickOnElement(driver, reportPageObj.deleteReportIcon);
        waitExecuter.sleep(1000);
        Alert confirmationAlert = driver.switchTo().alert();
        String alertText = confirmationAlert.getText();
        logger.info("Alert text is " + alertText);
        confirmationAlert.accept();
        waitExecuter.sleep(3000);
        Assert.assertEquals(reportPageObj.successfulMsgBanner.getText(), "Removed successfully",
            " Report not removed");
        MouseActions.clickOnElement(driver, reportPageObj.goBackButton);
        waitExecuter.sleep(2000);
        int reportCntAfterDelete = Integer.parseInt(reportCntList.get(i).getText().trim());
        logger.info("Before Delete report count = " + reportCnt + "\n After delete report count is " + reportCntAfterDelete);
        Assert.assertEquals(reportCntAfterDelete, reportCnt - 1, " Report " + reportName + " had " + reportCnt +
            " reports before deletion and after deletion has " + reportCntAfterDelete);
      }
    }
  }
}