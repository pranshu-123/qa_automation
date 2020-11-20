package com.qa.scripts.reports;

import com.qa.pagefactory.reports.ReportsArchiveScheduledPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.appdetails.SparkAppsDetailsPage;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
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
        if (colName.equals("Name"))
          sortByReportName(reportPageObj, expectedReportNameArr, reportAsceArr, reportDscArr, colList);
        else if (colName.equals("Status"))
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
        if (colName.equals("Name"))
          sortByReportName(reportPageObj, expectedReportNameArr, reportAsceArr, reportDscArr, colList);
        else if (colName.equals("Status"))
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
    Assert.assertFalse(reportNameList.isEmpty(), "There are no reports listed , expected 9 reports");
    if (perReport) {
      validateSortForDiffReportCol(reportNameList, reportCntList, reportPageObj, "Name");
    } else {
      for (int i = 0; i < reportNameList.size(); i++) {
        expectedReportNameArr.add(reportNameList.get(i).getText());
      }
      sortByReportName(reportPageObj, expectedReportNameArr, reportAsceArr, reportDscArr, reportNameList);
    }
  }

  /**
   * Method to sort by Report name column
   */
  public void sortByReportName(ReportsArchiveScheduledPageObject reportPageObj, ArrayList<String> expectedReportNameArr,
                               ArrayList<String> reportAsceArr, ArrayList<String> reportDscArr, List<WebElement> reportNameList) {
    //Sort the created arraylist in ascending order
    Collections.sort(expectedReportNameArr);
    ArrayList<String> newExpectedReportNameArr = new ArrayList<String>();
    WebElement sortingIcon = reportPageObj.sortingReportNameIcon;
    MouseActions.clickOnElement(driver, sortingIcon);
    List<WebElement> sortedReportNameList1 = reportPageObj.reportNames;
    Assert.assertFalse(reportNameList.isEmpty(), "There are no reports listed");
    for (int s = 0; s < sortedReportNameList1.size(); s++) {
      reportAsceArr.add(sortedReportNameList1.get(s).getText());
    }
    // Click again to reverse sort;
    MouseActions.clickOnElement(driver, sortingIcon);
    List<WebElement> sortedReportNameList2 = reportPageObj.reportNames;
    Assert.assertFalse(reportNameList.isEmpty(), "There are no reports listed ");
    for (int s = 0; s < sortedReportNameList2.size(); s++) {
      reportDscArr.add(sortedReportNameList2.get(s).getText());
    }
    for (int i = 0; i < reportDscArr.size(); i++) {
      newExpectedReportNameArr.add(expectedReportNameArr.get(i));
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
          waitExecuter.sleep(30000);
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
          waitExecuter.sleep(30000);
          status = reportStatusList.get(i).getText().trim();
          driver.navigate().refresh();
          afterReportCnt = Integer.parseInt(reportCntList.get(i).getText().trim());
          logger.info("Before cnt = " + beforeReportCnt + " After cnt = " + afterReportCnt);
      }
      Assert.assertEquals(expectedAfterCnt, afterReportCnt, " The report cnt do not match for report Name = " +
            reportName + " with status = " + status + " \n Expected = " + expectedAfterCnt + " Actual = " + afterReportCnt);
//      else
//        Assert.assertEquals(beforeReportCnt, afterReportCnt, " The report cnt do not match for report Name = " +
//            reportName + " with status = " + status + " \n Expected = " + beforeReportCnt + " Actual = " + afterReportCnt);
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
        System.out.println("Alert text is " + alertText);
        confirmationAlert.accept();
        waitExecuter.sleep(3000);
        Assert.assertEquals(reportPageObj.successfulMsgBanner.getText(), "Removed successfully",
            " Report not removed");
        MouseActions.clickOnElement(driver, reportPageObj.goBackButton);
        waitExecuter.sleep(2000);
        int reportCntAfterDelete = Integer.parseInt(reportCntList.get(i).getText().trim());
        logger.info("Before Delete report count = "+ reportCnt + "\n After delete report count is "+ reportCntAfterDelete);
        Assert.assertEquals(reportCntAfterDelete, reportCnt - 1, " Report " +reportName+ " had "+ reportCnt+
            " reports before deletion and after deletion has "+reportCntAfterDelete);
      }
    }
  }
}
