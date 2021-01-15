package com.qa.scripts.migration;

import com.qa.enums.migration.CloudProduct;
import com.qa.enums.UserAction;
import com.qa.enums.migration.MigrationCloudMappingTable;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.migration.CloudMappingPerHostPageObject;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class CloudMigrationPerHostPage {
  private WaitExecuter waitExecuter;
  private WebDriver driver;
  private final UserActions userAction;
  private CloudMappingPerHostPageObject cmpPageObj;
  private TopPanelPageObject topPanelPageObject;
  Logger logger = Logger.getLogger(CloudMigrationPerHostPage.class.getName());

  /**
   * Constructor to initialize wait, driver and necessary objects
   * @param driver - WebDriver instance
   */
  public CloudMigrationPerHostPage(WebDriver driver) {
    waitExecuter = new WaitExecuter(driver);
    cmpPageObj = new CloudMappingPerHostPageObject(driver);
    topPanelPageObject = new TopPanelPageObject(driver);

    this.driver = driver;
    userAction = new UserActions(driver);
  }

  public void getOptions(String expectedVal) {
    List<WebElement> optionList = cmpPageObj.dropDownValues;
    Assert.assertFalse(optionList.isEmpty(), " No options are displayed");
    for (int j = 0; j < optionList.size(); j++) {
      String actualCloudProvider = optionList.get(j).getText();
      logger.info("Actual value: " + actualCloudProvider + " Expected value: " + expectedVal);
      if (actualCloudProvider.equals(expectedVal)) {
        MouseActions.clickOnElement(driver, optionList.get(j));
        waitExecuter.waitUntilElementClickable(cmpPageObj.checkBox);
        break;
      }
    }
  }

  /**
   * Method to enter text in search option field
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
      //MouseActions.clickOnElement(driver, cmpPageObj.runButton);
      List<WebElement> cloudProDD = cmpPageObj.cloudProductDropDown;
      waitExecuter.waitUntilElementClickable(cloudProDD.get(0));
    } catch (TimeoutException ex) {
      throw new AssertionError("New Report Page loading timedout");
    }
  }

  public void verifyCloudMappingPerHostReports(String[] expectedList, String vmType) {
    waitExecuter.sleep(2000);
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

  public ArrayList<String> getEMRVMList(String region, String url) {
    WebElement regions = cmpPageObj.emrRegionsDropDown;
    List<WebElement> regionList = cmpPageObj.regionsList;
    driver.navigate().to(url);
    waitExecuter.waitUntilPageFullyLoaded();
    MouseActions.clickOnElement(driver, regions);
    ArrayList<String> vmList = new ArrayList<>();
    for (int i = 0; i < regionList.size(); i++) {
      if (regionList.get(i).getText().equals(region)) {
        MouseActions.clickOnElement(driver, regionList.get(i));
        waitExecuter.waitUntilPageFullyLoaded();
        List<WebElement> emrVMList = cmpPageObj.emrVMList;
        logger.info("The vm list lenght is " + emrVMList.size());
        for (WebElement webElement : emrVMList) {
          String val = webElement.getText();
          if (!Character.isUpperCase(val.charAt(0))) {
            logger.info("The vm type is " + val);
            vmList.add(val);
          }
        }
      }
    }
    return vmList;
  }

  public ArrayList<String> getEC2VMList(String region, String url) {
    driver.navigate().to(url);
    WebElement regions = cmpPageObj.ec2RegionsDropDown;
    waitExecuter.waitUntilPageFullyLoaded();
    MouseActions.clickOnElement(driver, regions);
    List<WebElement> regionList = cmpPageObj.regionsList;
    ArrayList<String> vmList = new ArrayList<>();
    for (int i = 0; i < regionList.size(); i++) {
      if (regionList.get(i).getText().equals(region)) {
        MouseActions.clickOnElement(driver, regionList.get(i));
        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.sleep(5000);
        //vm type is coming empty dont knw y
        List<WebElement> ec2VMList = cmpPageObj.ec2VMList;
        logger.info("The vm list lenght is " + ec2VMList.size());
        for (WebElement webElement : ec2VMList) {
          String val = webElement.getText();
          if (!Character.isUpperCase(val.charAt(0))) {
            logger.info("The vm type is " + val);
            vmList.add(val);
          }
        }
      }
    }
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
    String url = null;
    switch (cloudProvider) {
      case "Amazon EMR":
        url = "https://aws.amazon.com/emr/pricing/?nc=sn&loc=4";
        vmList = getEMRVMList(region, url);
        break;
      case "Amazon EC2 (IaaS)":
        url = "https://aws.amazon.com/ec2/pricing/on-demand/";
        vmList = getEC2VMList(region, url);
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
   * :param part: integer value in 0 or 1 , to get the first or second part after split()
   */
  public int getPageCnt(int part) {
    String pageCntStr = cmpPageObj.paginationCnt.getText();
    logger.info("The pageCnt is " + pageCntStr);
    //output= 1   of 173
    int pageCnt = Integer.parseInt(pageCntStr.trim().split("\\s+")[part]);
    logger.info("The integer page count is " + pageCnt);
    return pageCnt;
  }

  /**
   * Method to click the cloud provider and corresponding region
   */
  public void clickCloudProvideAndRegion(String[] expectedList){//String cloudProvider, String region){
//    List<WebElement> cloudProDD = cmpPageObj.cloudProductDropDown;
//    waitExecuter.sleep(2000);
//    userAction.performActionWithPolling(cloudProDD.get(0), UserAction.CLICK);
//    getOptions(cloudProvider);
//    waitExecuter.waitUntilElementClickable(cmpPageObj.checkBox);
//    waitExecuter.waitUntilPageFullyLoaded();
//    userAction.performActionWithPolling(cmpPageObj.regionDropDown, UserAction.CLICK);
//    getOptions(region);
    List<WebElement> dropDownList = cmpPageObj.dropDownBtn;
    for (int i = 0; i < dropDownList.size() - 1; i++) {
      MouseActions.clickOnElement(driver, dropDownList.get(i));
      logger.info("Parameters are: " + expectedList[i]);
      getOptions(expectedList[i]);
    }
  }

  public void verifyCostChangeForDiffRegions(String cloudProvider, String region1, String region2 ){
    waitExecuter.sleep(2000);
    runNewReport();
   // clickCloudProvideAndRegion(cloudProvider, region1);
    String[] val = {"Amazon EMR", "Asia Pacific (Mumbai)"};
    clickCloudProvideAndRegion(val);
    HashMap<String, String> vmTypeCost = getUIVMList(region1, cloudProvider);
    logger.info("The vmTypeCost map for region "+ region1 + " is "+ vmTypeCost);
    waitExecuter.waitUntilElementClickable(cmpPageObj.runButton);
    waitExecuter.waitUntilPageFullyLoaded();
    runNewReport();
    String[] val1 = {"Amazon EMR", "South America (Sao Paulo)"};
    // clickCloudProvideAndRegion(cloudProvider, region2);
    clickCloudProvideAndRegion(val1);
    HashMap<String, String> vmTypeCost2 = getUIVMList(region2, cloudProvider);
    logger.info("The vmTypeCost map for region "+ region2 + " is "+ vmTypeCost2);
    Assert.assertNotEquals(vmTypeCost2, vmTypeCost, "The cost/hour for different region doesnot change");
  }

  public void verifyEMRVMTypes(String region, String cloudProvider) {
    waitExecuter.sleep(2000);
    Actions action = new Actions(driver);
    String currentUrl = driver.getCurrentUrl();
    logger.info("The current url is " + currentUrl);
    ArrayList<String> expectedVMList;
    runNewReport();
   // waitExecuter.waitUntilPageFullyLoaded();
    List<WebElement> cloudProDD = cmpPageObj.cloudProductDropDown;
    waitExecuter.sleep(2000);
   // action.moveToElement(cloudProDD.get(0)).perform();
    userAction.performActionWithPolling(cloudProDD.get(0), UserAction.CLICK);
    getOptions(cloudProvider);
    waitExecuter.waitUntilElementClickable(cmpPageObj.checkBox);
    waitExecuter.waitUntilPageFullyLoaded();
    waitExecuter.sleep(10000);
    HashMap<String, String> UIHashMap;
    //Get UI vm list
    UIHashMap = getUIVMList(region, cloudProvider);
    //Get CloudProvider vm list
    expectedVMList = getCloudProviderVMList(region, cloudProvider);
    driver.navigate().to(currentUrl);
    waitExecuter.waitUntilPageFullyLoaded();
    logger.info("The expected vmTypeList is : " + expectedVMList);
    logger.info("The Actual vmTypeList is : " + UIHashMap.keySet());
    //Validate
    for (String UI_vmType : UIHashMap.keySet()) {
      Assert.assertTrue(expectedVMList.contains(UI_vmType), "VM " + UI_vmType + " displayed in the UI is not" +
          " present in the expected list of vms");
    }
    //remove all elements from expected list
    expectedVMList.removeAll(UIHashMap.keySet());
    logger.info("The missing vmTypes are " + expectedVMList);
  }

  public HashMap<String,String> getUIVMList(String region, String cloudProvider) {
    waitExecuter.sleep(5000);
    ArrayList<String> UI_VMList = new ArrayList<>();
    logger.info("Doc ready");
    Actions action = new Actions(driver);
    int pageCnt = getPageCnt(2);
    logger.info("PageCnt is " + pageCnt);
    HashMap<String,String> vmTypeCost = new HashMap<>();
    if (pageCnt > 1) {
      for (int i = 1; i <= pageCnt; i++) {
        List<WebElement> vmTypeList = cmpPageObj.vmTypeTableRows;
        List<WebElement> costList = cmpPageObj.vmCostTableRows;
        Assert.assertFalse(vmTypeList.isEmpty() || costList.isEmpty(), " No vms listed in the table for " + cloudProvider +
            " in region " + region);
        for (int v=0;v<vmTypeList.size(); v++) {
          String vmType = vmTypeList.get(v).getText();
          String cost =  costList.get(v).getText();
         // UI_VMList.add(vmType);
          vmTypeCost.put(vmType, cost);
        }
        logger.info("SPPP: Value of i is " + i);
        if (i == pageCnt)
          action.moveToElement(cmpPageObj.closeNewReportWin).click().build().perform();
         else
          userAction.performActionWithPolling(cmpPageObj.forwardCaret, UserAction.CLICK);
      }
    } else {
      List<WebElement> vmTypeList = cmpPageObj.vmTypeTableRows;
      List<WebElement> costList = cmpPageObj.vmCostTableRows;
      Assert.assertFalse(vmTypeList.isEmpty(), " No vms listed in the table for " + cloudProvider +
          " in region " + region);
      for (int v=0;v<vmTypeList.size(); v++) {
        String vmType = vmTypeList.get(v).getText();
        String cost =  costList.get(v).getText();
        // UI_VMList.add(vmType);
        vmTypeCost.put(vmType, cost);
      }
    }
    return vmTypeCost;
  }

  /**
   * Navigate to cloud mapping per host
   */
  public void navigateToCloudMappingPerHost() {
    userAction.performActionWithPolling(topPanelPageObject.migrationTab, UserAction.CLICK);
    userAction.performActionWithPolling(cmpPageObj.cloudMappingPerHostTab, UserAction.CLICK);
  }

  /**
   * Click on run button
   */
  public void clickOnRunButton() {
    userAction.performActionWithPolling(cmpPageObj.runButton, UserAction.CLICK);
  }

  /**
   * Wait until loader is present
   */
  public void waitTillLoaderPresent() {
    Clock clock = Clock.systemDefaultZone();
    final Duration MAX_POLLING_TIME = Duration.ofMillis(120000);
    Instant end = clock.instant().plus(MAX_POLLING_TIME);
    while (true) {
      try {
        if (cmpPageObj.loaderElement.size() == 0) {
          return;
        } else if (cmpPageObj.loaderElement.size() > 0 &&
            !cmpPageObj.loaderElement.get(0).isDisplayed()) {
          return;
        } else if (end.isBefore(clock.instant())) {
          throw new TimeoutException("Page is not loaded. Loader is still running");
        }
      } catch (StaleElementReferenceException exception) {
        return;
      }
    }
  }

  /**
   * Select specific Cloud product
   * @param cloudProduct
   */
  public void selectCloudProduct(CloudProduct cloudProduct) {
    userAction.performActionWithPolling(cmpPageObj.cloudProductServiceDropdownIcon, UserAction.CLICK);
    List<WebElement> cloudProDD = cmpPageObj.dropDownValues;
    for (WebElement cloudItem : cloudProDD) {
      if (cloudItem.getText().trim().equalsIgnoreCase(cloudProduct.getValue())) {
        userAction.performActionWithPolling(cloudItem, UserAction.CLICK);
        break;
      }
    }
  }

  /**
   * Verify cloud mapping table column
   * @param table
   */
  public void verifyCloudMappingHostTableColumn(MigrationCloudMappingTable table) {
    List<WebElement> tableRows = cmpPageObj.tableRows;
    for (WebElement row : tableRows) {
      String colValue = row.findElement(By.xpath("td[" + table.getIndex() + 1 + "]")).getText();
      Assert.assertNotEquals(colValue.trim(), "");
    }
  }

  /**
   * Select Region from the dropdown if null
   * passed then select any region from the dropdown
   * @param region - Region to be passed if any the pass null
   */
  public void selectRegion(String region) {
    userAction.performActionWithPolling(cmpPageObj.regionDropDown, UserAction.CLICK);
    if (region == null) {
      Random random = new Random();
      WebElement random_region = cmpPageObj.regionsList.get
          (random.nextInt(cmpPageObj.regionsList.size()));
      userAction.performActionWithPolling(random_region, UserAction.CLICK);
    } else {
      for (WebElement reg : cmpPageObj.regionsList) {
        if (reg.getText().trim().equalsIgnoreCase(region)) {
          userAction.performActionWithPolling(reg, UserAction.CLICK);
        }
      }
    }
  }

  /**
   * Select Region from the dropdown if null
   * passed then select any region from the dropdown
   * @param storage - Region to be passed if any the pass null
   */
  public void selectStorage(String storage) {
    userAction.performActionWithPolling(cmpPageObj.storageTypeDropdown, UserAction.CLICK);
    if (storage == null) {
      Random random = new Random();
      WebElement random_region = cmpPageObj.dropDownValues.get
          (random.nextInt(cmpPageObj.dropDownValues.size()));
      userAction.performActionWithPolling(random_region, UserAction.CLICK);
    } else {
      for (WebElement storageType : cmpPageObj.dropDownValues) {
        if (storageType.getText().trim().equalsIgnoreCase(storage)) {
          userAction.performActionWithPolling(storageType, UserAction.CLICK);
        }
      }
    }
  }

  /**
   * Get the values for specific column from the table
   * @param column - Column which we want to get values for all rows
   * @return - List of values as String
   */
  public List<String> getColumnValuesFromTable(MigrationCloudMappingTable column) {
    List<String> columnValues = new ArrayList<>();
    List<WebElement> tableRows = cmpPageObj.tableRows;
    for (WebElement row : tableRows) {
      String colValue = row.findElement(By.xpath("td[" + (column.getIndex() + 1) + "]")).getText();
      columnValues.add(colValue);
    }
    return columnValues;
  }
}
