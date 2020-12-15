package com.qa.scripts.migration;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.qa.enums.UserAction;
import com.qa.pagefactory.migration.CloudMappingPerHostPageObject;
import com.qa.pagefactory.reports.ReportsArchiveScheduledPageObject;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Mouse;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class CloudMigrationPerHostPage {
  private WaitExecuter waitExecuter;
  private WebDriver driver;
  private final UserActions userAction;
  private CloudMappingPerHostPageObject cmpPageObj;

  Logger logger = Logger.getLogger(CloudMigrationPerHostPage.class.getName());

  /**
   * Constructor to initialize wait, driver and necessary objects
   *
   * @param driver - WebDriver instance
   */
  public CloudMigrationPerHostPage(WebDriver driver) {
    waitExecuter = new WaitExecuter(driver);
    cmpPageObj = new CloudMappingPerHostPageObject(driver);
    this.driver = driver;
    userAction = new UserActions(driver);
  }

  public void getOptions(String expectedVal) {
    List<WebElement> optionList = cmpPageObj.dropDownValues;
    for (int j = 0; j < optionList.size(); j++) {
      if (optionList.get(j).getText().equals(expectedVal)) {
        MouseActions.clickOnElement(driver, optionList.get(j));
      }
    }
  }

  /**
   * Method to validate the search option on the Report Archive Page
   */
  public void getSearchOptValue(String vmType) {
    cmpPageObj.searchField.sendKeys(vmType);
    waitExecuter.waitUntilPageFullyLoaded();
  }

  public void verifyData(List<WebElement> usageDataKey, List<WebElement> usageDataValue, int numRows) {
    Assert.assertFalse(usageDataKey.isEmpty(), "No data in the column");
    for (int i = 0; i < numRows; i++) {
      String key = usageDataKey.get(i).getText();
      String value = usageDataValue.get(i).getText();
      logger.info("Key is: " + key + " Value is: " + value);
      boolean onlySpecialChars = value.matches("[^a-zA-Z0-9]+");
      Assert.assertFalse(value.isEmpty() || onlySpecialChars, "No values for " + key +
          "displayed \n Expected: AlphaNumeric value Actual: [" + value + "]");
    }
  }

  public void verifyActualUsageData() {
    List<WebElement> usageDataKey = cmpPageObj.actualUsageKey;
    List<WebElement> usageDataValue = cmpPageObj.actualUsageValue;
    verifyData(usageDataKey, usageDataValue, usageDataKey.size());
  }

  public void verfiyCapacityData() {
    List<WebElement> usageDataKey = cmpPageObj.capacityKey;
    List<WebElement> usageDataValue = cmpPageObj.capacityValue;
    verifyData(usageDataKey, usageDataValue, usageDataKey.size());
  }

  public void verifyRecommendationData() {
    List<WebElement> usageDataKey = cmpPageObj.recommendationsKey;
    List<WebElement> usageDataValue = cmpPageObj.recommendationsValue;
    verifyData(usageDataKey, usageDataValue, 5);
  }

  public void verifyTotalCost() {
    String totalCost = cmpPageObj.totalCost.getText();
    logger.info("Total cost is " + totalCost);
    boolean onlySpecialChars = totalCost.matches("[^a-zA-Z0-9]+");
    Assert.assertFalse(totalCost.isEmpty() || onlySpecialChars, "No values for total cost displayed " +
        "\n Expected: AlphaNumeric value but Actual: [" + totalCost + "]");
  }

  public void runNewReport() {
    try {
      userAction.performActionWithPolling(cmpPageObj.runButton, UserAction.CLICK);
      waitExecuter.waitUntilElementPresent(cmpPageObj.checkBox);
    } catch (TimeoutException ex) {
      throw new AssertionError("New Report Page loading timedout");
    }
  }

  public void verifyCloudMappingPerHostReports(String[] expectedList, String vmType) {
    runNewReport();
    List<WebElement> dropDownList = cmpPageObj.dropDownBtn;
    for (int i = 0; i < dropDownList.size(); i++) {
      MouseActions.clickOnElement(driver, dropDownList.get(i));
      logger.info("Parameters are: " + expectedList[i]);
      getOptions(expectedList[i]);
    }
    getSearchOptValue(vmType);
    MouseActions.clickOnElement(driver, cmpPageObj.newReportRunButton);
    try {
      waitExecuter.waitUntilTextToBeInWebElement(cmpPageObj.bannerMsg,
          "Cloud Mapping Per Host completed successfully.");
      waitExecuter.sleep(40000);
    } catch (TimeoutException te) {
      throw new AssertionError("Cloud Mapping Per Host not completed successfully.");
    }
    waitExecuter.waitUntilPageFullyLoaded();
    Assert.assertTrue(cmpPageObj.hostReportTable.isDisplayed(), "The report is not displayed");
    verifyActualUsageData();
    verfiyCapacityData();
    verifyRecommendationData();
    verifyTotalCost();
  }

  public ArrayList<String> getEMRVMList(String region) {
    String url = "https://aws.amazon.com/emr/pricing/?nc=sn&loc=4";
    driver.navigate().to(url);
    waitExecuter.waitUntilPageFullyLoaded();
    WebElement regions = cmpPageObj.emrRegionsDropDown;
    MouseActions.clickOnElement(driver, regions);
    List<WebElement> regionList = cmpPageObj.regionsList;
    ArrayList<String> vmList = new ArrayList<>();
    for (int i = 0; i < regionList.size(); i++) {
      if (regionList.get(i).getText().equals(region)) {
        MouseActions.clickOnElement(driver, regionList.get(i));
        waitExecuter.waitUntilPageFullyLoaded();
        List<WebElement> emrVMList = cmpPageObj.emrVMList;
        logger.info("The vm list lenght is " + emrVMList.size());
        for (WebElement webElement : emrVMList) {
          String val = webElement.getText();
          if (!Character.isUpperCase(val.charAt(0)))
            logger.info("The vm type is " + val);
          vmList.add(val);
        }
      }
    }
    return vmList;
  }

  public ArrayList<String> getEC2VMList(String region) {
    ArrayList<String> vmList = new ArrayList<>();
    return vmList;
  }

  public ArrayList<String> getAzureVMList(String region) {
    ArrayList<String> vmList = new ArrayList<>();
    return vmList;
  }

  public ArrayList<String> getGCPVMList(String region) {
    ArrayList<String> vmList = new ArrayList<>();
    return vmList;
  }

  public ArrayList<String> getHDIVMList(String region) {
    ArrayList<String> vmList = new ArrayList<>();
    return vmList;
  }

  public ArrayList<String> getCloudProviderVMList(String region, String cloudProvider) {
    ArrayList<String> vmList = new ArrayList<String>();
    switch (cloudProvider) {
      case "Amazon EMR":
        vmList = getEMRVMList(region);
        break;
      case "Amazon EC2 (IaaS)":
        vmList = getEC2VMList(region);
        break;
      case "Azure (IaaS)":
        vmList = getAzureVMList(region);
        break;
      case "Google DataProc":
        vmList = getGCPVMList(region);
        break;
      case "Azure HDInsight":
        vmList = getHDIVMList(region);
        break;
    }
    return vmList;
  }

  /**
   * Method to get the page count when pagination is enabled
   * :param part: integer value in 1 or 2 , to get the first or second part after split()
   */
  public int getPageCnt(int part) {
    String pageCntStr = cmpPageObj.paginationCnt.getText().trim();
    logger.info("The pageCnt is " + pageCntStr);
    //output= 1   of 173
    int pageCnt = Integer.parseInt(pageCntStr.split("\\s+")[part]);
    logger.info("The integer page count is " + pageCnt);
    return pageCnt;
  }

  public void selectOptions(List<WebElement> dropDownList, String expectedVal) {
    for (WebElement webElement : dropDownList) {
      if (webElement.getText().equals(expectedVal)) {
        MouseActions.clickOnElement(driver, webElement);
      }
    }
  }

  public void verifyEMRVMTypes(String region, String cloudProvider) {
    String currentUrl = driver.getCurrentUrl();
    logger.info("The current url is " + currentUrl);
    ArrayList<String> expectedVMList;
    expectedVMList = getCloudProviderVMList(region, cloudProvider);
    driver.navigate().to(currentUrl);
    waitExecuter.waitUntilPageFullyLoaded();
    runNewReport();
    WebElement cloudDropDown = cmpPageObj.cloudProductDropDown;
    MouseActions.clickOnElement(driver, cloudDropDown);
    List<WebElement> dropDownList = cmpPageObj.dropDownValues;
    selectOptions(dropDownList, cloudProvider);
    WebElement regionDropDown = cmpPageObj.regionDropDown;
    MouseActions.clickOnElement(driver, regionDropDown);
    List<WebElement> regionDropDownList = cmpPageObj.regionsList;
    selectOptions(regionDropDownList, region);
    waitExecuter.waitUntilPageFullyLoaded();
    validateVMTypes(expectedVMList, cloudProvider, region);
  }

  public void validateVMTypes(ArrayList<String> expectedVMList, String cloudProvider, String region) {
    ArrayList<String> UI_VMList = new ArrayList<>();
    int pageCnt = getPageCnt(2);
    logger.info("PageCnt is " + pageCnt);
    if (pageCnt > 1) {
      for (int i = 0; i < pageCnt; i++) {
        int incrementalPgCnt = getPageCnt(1);
        List<WebElement> vmTypeList = cmpPageObj.vmTypeTableRows;
        Assert.assertFalse(vmTypeList.isEmpty(), " No vms listed in the table for " + cloudProvider +
            " in region " + region);
        for (WebElement webElement : vmTypeList) {
          String vmType = webElement.getText();
          UI_VMList.add(vmType);
        }
        if (incrementalPgCnt == pageCnt)
          MouseActions.clickOnElement(driver, cmpPageObj.closeNewReportWin);
        else
          MouseActions.clickOnElement(driver, cmpPageObj.forwardCaret);
      }
    } else {
      List<WebElement> vmTypeList = cmpPageObj.vmTypeTableRows;
      Assert.assertFalse(vmTypeList.isEmpty(), " No vms listed in the table for " + cloudProvider +
          " in region " + region);
      for (WebElement webElement : vmTypeList) {
        String vmType = webElement.getText();
        UI_VMList.add(vmType);
      }
    }
    MouseActions.clickOnElement(driver, cmpPageObj.closeNewReportWin);
    logger.info("The expected vmTypeList is : " + expectedVMList);
    logger.info("The Actual vmTypeList is : " + UI_VMList);
    for (String UI_vmType : UI_VMList) {
      Assert.assertTrue(expectedVMList.contains(UI_vmType), "VM " + UI_vmType + " displayed in the UI is not" +
          " present in the expected list of vms");
    }
    //remove all elements from expected list
    expectedVMList.removeAll(UI_VMList);
    logger.info("The missing vmTypes are " + expectedVMList);
  }
}
