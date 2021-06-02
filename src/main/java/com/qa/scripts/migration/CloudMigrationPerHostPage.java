package com.qa.scripts.migration;

import com.qa.constants.CloudMappingHostConstants;
import com.qa.enums.migration.CloudProduct;
import com.qa.enums.UserAction;
import com.qa.enums.migration.InstanceSummaryTableColumn;
import com.qa.enums.migration.MigrationCloudMappingModalTable;
import com.qa.enums.migration.MigrationCloudMappingHostDetailsTable;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.migration.CloudMappingPerHostPageObject;
import com.qa.utils.LoggingUtils;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CloudMigrationPerHostPage {
    private WaitExecuter waitExecuter;
    private WebDriver driver;
    private final UserActions userAction;
    private CloudMappingPerHostPageObject cmpPageObj;
    private TopPanelPageObject topPanelPageObject;
    private static final LoggingUtils LOGGER = new LoggingUtils(CloudMigrationPerHostPage.class);
    Logger logger = Logger.getLogger(CloudMigrationPerHostPage.class.getName());

    /**
     * Constructor to initialize wait, driver and necessary objects
     *
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
        if (cmpPageObj.checkOptionIfNonActive.size() > 0) {
            waitExecuter.sleep(2000);
            cmpPageObj.nodeTypeSelection.click();
            waitExecuter.waitUntilPageFullyLoaded();
        }
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
            waitExecuter.waitUntilElementClickable(dropDownList.get(i));
            waitExecuter.sleep(2000);
            MouseActions.clickOnElement(driver, dropDownList.get(i));
            waitExecuter.waitUntilElementClickable(dropDownList.get(i));
            waitExecuter.sleep(2000);
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
    public void clickCloudProvideAndRegion(String[] expectedList) {
        List<WebElement> dropDownList = cmpPageObj.dropDownBtn;
        for (int i = 0; i < dropDownList.size() - 1; i++) {
            MouseActions.clickOnElement(driver, dropDownList.get(i));
            logger.info("Parameters are: " + expectedList[i]);
            getOptions(expectedList[i]);
        }
    }

    public void verifyCostChangeForDiffRegions(String cloudProvider, String region1, String region2) {
        waitExecuter.sleep(2000);
        runNewReport();
        String[] val = {"Amazon EMR", "Asia Pacific (Mumbai)"};
        clickCloudProvideAndRegion(val);
        HashMap<String, String> vmTypeCost = getUIVMList(region1, cloudProvider);
        logger.info("The vmTypeCost map for region " + region1 + " is " + vmTypeCost);
        waitExecuter.waitUntilElementClickable(cmpPageObj.runButton);
        waitExecuter.waitUntilPageFullyLoaded();
        runNewReport();
        String[] val1 = {"Amazon EMR", "South America (Sao Paulo)"};
        clickCloudProvideAndRegion(val1);
        HashMap<String, String> vmTypeCost2 = getUIVMList(region2, cloudProvider);
        logger.info("The vmTypeCost map for region " + region2 + " is " + vmTypeCost2);
        Assert.assertNotEquals(vmTypeCost2, vmTypeCost, "The cost/hour for different region doesnot change");
    }

    public void verifyEMRVMTypes(String region, String cloudProvider) {
        waitExecuter.sleep(2000);
        Actions action = new Actions(driver);
        String currentUrl = driver.getCurrentUrl();
        logger.info("The current url is " + currentUrl);
        ArrayList<String> expectedVMList;
        runNewReport();
        List<WebElement> cloudProDD = cmpPageObj.cloudProductDropDown;
        waitExecuter.sleep(2000);
        waitExecuter.waitUntilElementClickable(cloudProDD.get(0));
        userAction.performActionWithPolling(cloudProDD.get(0), UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(cloudProDD.get(0));
        waitExecuter.sleep(2000);
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

    public HashMap<String, String> getUIVMList(String region, String cloudProvider) {
        waitExecuter.sleep(5000);
        ArrayList<String> UI_VMList = new ArrayList<>();
        logger.info("Doc ready");
        Actions action = new Actions(driver);
        int pageCnt = getPageCnt(2);
        logger.info("PageCnt is " + pageCnt);
        HashMap<String, String> vmTypeCost = new HashMap<>();
        if (pageCnt > 1) {
            for (int i = 1; i <= pageCnt; i++) {
                List<WebElement> vmTypeList = cmpPageObj.vmTypeTableRows;
                List<WebElement> costList = cmpPageObj.vmCostTableRows;
                Assert.assertFalse(vmTypeList.isEmpty() || costList.isEmpty(), " No vms listed in the table for " + cloudProvider +
                        " in region " + region);
                for (int v = 0; v < vmTypeList.size(); v++) {
                    String vmType = vmTypeList.get(v).getText();
                    String cost = costList.get(v).getText();
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
            for (int v = 0; v < vmTypeList.size(); v++) {
                String vmType = vmTypeList.get(v).getText();
                String cost = costList.get(v).getText();
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
        final Duration MAX_POLLING_TIME = Duration.ofMillis(180000);
        Instant end = clock.instant().plus(MAX_POLLING_TIME);
        while (true) {
            List<WebElement> loaderElement = cmpPageObj.loaderElement;
            try {
                if (loaderElement.size() == 0) {
                    return;
                } else if (loaderElement.size() > 0) {
                    try {
                        if (!loaderElement.get(0).isDisplayed()) {
                            return;
                        }
                    } catch (IndexOutOfBoundsException outOfBoundsException) {}
                    catch (NullPointerException npe) {}
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
     *
     * @param cloudProduct
     */
    public void selectCloudProduct(CloudProduct cloudProduct) {
        userAction.performActionWithPolling(cmpPageObj.cloudProductServiceDropdownIcon, UserAction.CLICK);
        waitExecuter.sleep(2000);
        List<WebElement> cloudProDD = cmpPageObj.dropDownValues;
        for (WebElement cloudItem : cloudProDD) {
            if (cloudItem.getText().trim().equalsIgnoreCase(cloudProduct.getValue())) {
                userAction.performActionWithPolling(cloudItem, UserAction.CLICK);
                break;
            }
        }
    }

    /**
     * Get the cloud product value selected in dropdown
     */
    public String getCloudProvider() {
        return cmpPageObj.cloudProductOrServiceValue.getText();
    }

    /**
     * Get the region value selected in dropdown
     */
    public String getRegion() {
        return cmpPageObj.regionDropDownValue.getText();
    }

    /**
     * Verify cloud mapping table column
     *
     * @param table
     */
    public void verifyCloudMappingHostTableColumn(MigrationCloudMappingModalTable table) {
        List<WebElement> tableRows = cmpPageObj.tableRows;
        for (WebElement row : tableRows) {
            String colValue = row.findElement(By.xpath("td[" + table.getIndex() + 1 + "]")).getText();
            Assert.assertNotEquals(colValue.trim(), "");
        }
    }

    /**
     * Select Region from the dropdown if null
     * passed then select any region from the dropdown
     *
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
                    break;
                }
            }
        }
    }

    /**
     * Select Region from the dropdown if null
     * passed then select any region from the dropdown
     *
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
                    break;
                }
            }
        }
    }

    /**
     * Get the list of storage type
     */
    public List<String> getStorageList() {
        userAction.performActionWithPolling(cmpPageObj.storageTypeDropdown, UserAction.CLICK);
        return cmpPageObj.dropDownValues.stream().map(dropDown -> dropDown.getText()).collect(Collectors.toList());
    }

    /**
     * Get the values for specific column from the table
     *
     * @param column - Column which we want to get values for all rows
     * @return - List of values as String
     */
    public List<String> getColumnValuesFromModalTable(MigrationCloudMappingModalTable column) {
        List<String> columnValues = new ArrayList<>();
        List<WebElement> tableRows = cmpPageObj.modalTableRows;
        for (WebElement row : tableRows) {
            String colValue = row.findElement(By.xpath("td[" + (column.getIndex() + 1) + "]")).getText();
            columnValues.add(colValue);
        }
        return columnValues;
    }

    /**
     * Check uncheck table
     */
    public void checkUncheckColumn(Boolean uncheck) {
        waitExecuter.waitUntilElementPresent(cmpPageObj.modalTableHeadings);
        WebElement tableHeadings = cmpPageObj.modalTableHeadings;
        WebElement rowCheckbox =
                tableHeadings.findElement(By.xpath("th[" + (MigrationCloudMappingModalTable.CHECKBOX.getIndex() + 1) +
                        "]")).findElement(By.xpath("label/span[@class='checkmark']"));
        if (uncheck) {
            while (cmpPageObj.activeCheckBoxes.size() != 0) {
                userAction.performActionWithPolling(rowCheckbox, UserAction.CLICK);
            }
        } else {
            userAction.performActionWithPolling(rowCheckbox, UserAction.CLICK);
            while (cmpPageObj.activeCheckBoxes.size() == 0) {
                userAction.performActionWithPolling(rowCheckbox, UserAction.CLICK);
            }
        }
    }

    /**
     * Get list of checkbox
     */
    public List<WebElement> getCheckboxListForTable() {
        List<WebElement> checkboxList = new ArrayList<>();
        List<WebElement> tableRows = cmpPageObj.modalTableRows;
        for (WebElement row : tableRows) {
            WebElement rowCheckbox = row.findElement(By.xpath("td[" + (MigrationCloudMappingModalTable.CHECKBOX.getIndex() + 1) +
                    "]")).findElement(By.xpath("label/span[@class='checkmark']"));
            checkboxList.add(rowCheckbox);
        }
        return checkboxList;
    }

    /**
     * Return the list of custom costs
     */
    public List<WebElement> getCustomCosts(List<WebElement> tableRows) {
        List<WebElement> checkboxList = new ArrayList<>();
        for (WebElement row : tableRows) {
            WebElement rowCustomCost =
                    row.findElement(By.xpath("td[" + (MigrationCloudMappingModalTable.CUSTOM_COST.getIndex() + 1) +
                    "]"));
            checkboxList.add(rowCustomCost);
        }
        return checkboxList;
    }

    /**
     * Set the custom cost value for the field
     * @param element - WebElement whose value to be set
     * @param value - value to set
     */
    public void setCustomCost(WebElement element, String value) {
        WebElement customCostInput = element.findElement(By.tagName("input"));
        userAction.performActionWithPolling(customCostInput, UserAction.SEND_KEYS, value);
    }

    /**
     * Get confimation message element
     */
    public WebElement getConfirmationMessage() {
        return cmpPageObj.confirmationMessageElement;
    }

    /**
     * Get the values from cloud mapping per host tables
     */

    public List getDataFromCloudMappingTable(MigrationCloudMappingHostDetailsTable tableColumn) {
        List values = new ArrayList();
        List<WebElement> tableRows = cmpPageObj.cloudMappingHostDetailsTableRows;
        for (WebElement row : tableRows) {
            WebElement columnElement = row.findElement(By.xpath("td[" + (tableColumn.getIndex() + 1) + "]"));
            switch (tableColumn) {
                case HOST:
                    values.add(columnElement.getText());
                    break;
                case HOST_ROLES:
                    List<String> hostRoles = new ArrayList<>();
                    for (WebElement hostRole : columnElement.findElements(By.xpath("ul/li"))) {
                        hostRoles.add(hostRole.getText());
                    }
                    values.add(hostRoles);
                    break;
                case ACTUAL_USAGE:
                    List<WebElement> usages = columnElement.findElements(By.xpath("table/tr"));
                    Map<String, String> details = new HashMap<>();
                    for (WebElement usage : usages) {
                        details.put(usage.findElements(By.tagName("td")).get(0).getText(),
                                usage.findElements(By.tagName("td")).get(1).getText());
                    }
                    values.add(details);
                    break;
                case CAPACITY:
                    List<WebElement> capacities = columnElement.findElements(By.xpath("table/tr"));
                    Map<String, String> capacityDetails = new HashMap<>();
                    for (WebElement capacity : capacities) {
                        capacityDetails.put(capacity.findElements(By.tagName("td")).get(0).getText(),
                                capacity.findElements(By.tagName("td")).get(1).getText());
                    }
                    values.add(capacityDetails);
                    break;
                case RECOMMENDATION:
                    List<WebElement> recommendations = columnElement.findElements(By.xpath("table/tr"));
                    Map<String, Object> recommendationDetails = new HashMap<>();
                    for (int i = 0; i < recommendations.size(); i++) {
                        WebElement recommendation = recommendations.get(i);
                        WebElement tableDataElement = recommendation.findElements(By.tagName("td")).get(0);
                        if (i == recommendations.size() - 1) {
                            Map<String, String> innerTableDetails = new HashMap<>();
                            List<WebElement> tableHeadings = tableDataElement.findElements(By.xpath("table/thead/tr/th"));
                            List<WebElement> tableData = tableDataElement.findElements(By.xpath("table/tbody/tr/td"));
                            for (int j = 0; j < tableHeadings.size(); j++) {
                                innerTableDetails.put(tableHeadings.get(j).getText(), tableData.get(j).getText());
                            }
                            recommendationDetails.put(CloudMappingHostConstants.HostDetails.RecommendedUsages.DETAILS,
                                    innerTableDetails);
                        } else {
                            recommendationDetails.put(recommendation.findElements(By.tagName("td")).get(0).getText(),
                                    recommendation.findElements(By.tagName("td")).get(1).getText());
                        }
                    }
                    values.add(recommendationDetails);
                    break;
                case TOTAL_COST:
                    values.add(columnElement.getText());
                    break;
            }
        }
        return values;
    }

    /**
     * Get total hourly cost value
     */
    public Double getTotoalHourlyCostValue() {
        return Double.parseDouble(cmpPageObj.totalHourlyCostValue.getText().replace("$", ""));
    }

    /**
     * Get total attached storage cost value
     */
    public Double getTotalLocalAttachedStorageValue() {
        return Double.parseDouble(cmpPageObj.totalAttachedStorageCostValue.getText().replace("$", ""));
    }

    /**
     * Click on COST REDUCTION tab
     */
    public void clickOnCostReductionTab() {
        userAction.performActionWithPolling(cmpPageObj.costReductionTab, UserAction.CLICK);
    }

    /**
     * Click on LIFT AND SHIFT tab
     */
    public void clickOnLiftAndShiftTab() {
        userAction.performActionWithPolling(cmpPageObj.liftAndShiftTab, UserAction.CLICK);
    }

    /**
     * Get the values of specific columns from instance summary table
     * displayed in cloud mapping per host page.
     * @param column - Column to get values
     * @return - List of values for that column
     */
    public List<String> getSummaryReportDetails(InstanceSummaryTableColumn column) {
        List<String> values = new ArrayList<>();
        List<WebElement> rows = cmpPageObj.instanceSummaryRows;
        for (WebElement row : rows) {
            values.add(row.findElement(By.xpath("td["+ (column.getIndex() + 1) +"]")).getText());
        }
        return values;
    }

    /**
     * Get the values for specific column from the table
     * param isForAllInstances - true is you want instances displayed for all pagination and
     * false if you want for current page.
     * @return - List of values as String
     */
    public Map<String, List> getInstanceValuesFromModalTable(boolean isForAllInstances) {
        Map<String, List> allInstances = new HashMap();
        int pageCount = isForAllInstances ? getPageCnt(2) : 1;
        for (int i = 0; i < pageCount; i++) {
            List<WebElement> tableRows = cmpPageObj.modalTableRows;
            for (WebElement row : tableRows) {
                List instanceDetails = new ArrayList();
                Arrays.asList(MigrationCloudMappingModalTable.CORES, MigrationCloudMappingModalTable.MEMORY,
                        MigrationCloudMappingModalTable.DISK, MigrationCloudMappingModalTable.COST).stream()
                        .forEach(data -> {
                            if (data == MigrationCloudMappingModalTable.MEMORY) {
                                instanceDetails.add(convertMemoryToMB(row.findElement(By.xpath("td[" + (data.getIndex() + 1) + "]")).getText()));
                            } else if (data == MigrationCloudMappingModalTable.COST) {
                                instanceDetails.add(Double.valueOf(row.findElement(By.xpath("td[" + (data.getIndex() + 1) + "]")).getText().replace(
                                        "$", "")));
                            } else {
                                instanceDetails.add(row.findElement(By.xpath("td[" + (data.getIndex() + 1) + "]")).getText());
                            }
                        });
                allInstances.put(row.findElement(By.xpath("td[" + (MigrationCloudMappingModalTable.VM_TYPE.getIndex() + 1) + "]")).getText(), instanceDetails);
            }
            if (i < pageCount -1 )
                userAction.performActionWithPolling(cmpPageObj.forwardCaret, UserAction.CLICK);
        }
        return allInstances;
    }

    public Map.Entry<String, List> getCheapestBasedOnCapacity(Map<String, List> allInstances, Double cores, Double memory) {
        allInstances =
                allInstances.entrySet().stream().filter(kv -> (Double.parseDouble(kv.getValue().get(0).toString()) >= cores) &&
                    (Double.parseDouble(kv.getValue().get(1).toString()) > memory)).sorted(Comparator.comparing(e -> Double.valueOf(e.getValue().get(3).toString())))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        ;
        Map.Entry<String, List> entry = allInstances.entrySet().iterator().next();
        return entry;
    }

    public List<Map.Entry> getCheapestInstance(List capacityUsages, Map<String, List> instanceValuesFromModalTable) {
        List<Map.Entry> cheapestInstances = new ArrayList<>();
        for (Object capacityInfo : capacityUsages) {
            Map<String, Object> capacity = (Map<String, Object>) capacityInfo;
            Double onPremCoresCapacity =
                    Double.valueOf(capacity.get(CloudMappingHostConstants.HostDetails.CapacityUsages.CORES).toString());
            Double onPremMemoryCapacity = convertMemoryToMB(capacity.get(
                    CloudMappingHostConstants.HostDetails.CapacityUsages.MEMORY).toString());
            Map.Entry cheapestInstance = getCheapestBasedOnCapacity(instanceValuesFromModalTable,
                    onPremCoresCapacity, onPremMemoryCapacity);
            cheapestInstances.add(cheapestInstance);
        }
        return cheapestInstances;
    }

    /**
     * Convert memory values into MB
     * @param memory - memory values
     * @return - String memory in MB
     */
    public Double convertMemoryToMB(String memory) {
        String digits = memory.replaceAll("[^0-9.]", "");
        if (memory.contains("TB")) {
            return Double.parseDouble(digits) * 1024 * 1024;
        } else if (memory.contains("GB")) {
            return Double.parseDouble(digits) * 1024;
        } else {
            return Double.parseDouble(digits);
        }
    }

    /**
     * Validate whether cheapest instance displayed in recommendation
     * when multiple instance was selected during run
     *
     * @param test - ExtentTest instance to log into report
     */
    public void validateCheapestIsDisplayedInRecommendation(ExtentTest test) {
        LOGGER.info("Select first storage.", test);
        selectStorage("Object storage");
        waitTillLoaderPresent();
        checkUncheckColumn(false);
        Map<String, List> instanceValuesFromModalTable = getInstanceValuesFromModalTable(true);
        clickOnRunButton();
        try {
            waitExecuter.waitUntilTextToBeInWebElement(getConfirmationMessage(),
                    "Cloud Mapping Per Host completed successfully.");
        } catch (TimeoutException te) {
            Assert.assertTrue(false, "Cloud Mapping Per Host is not completed");
        }
        waitTillLoaderPresent();
        waitExecuter.sleep(30000);

        LOGGER.info("Validate recommended for lift and shift.", test);

        List recommendedUsages = getDataFromCloudMappingTable(
                MigrationCloudMappingHostDetailsTable.RECOMMENDATION);
        List actualRecommendedInstanceNames =
                (List) recommendedUsages.stream().filter(data -> data instanceof Map).map(data -> ((Map) data)
                        .get(CloudMappingHostConstants.HostDetails.RecommendedUsages.TYPE)).collect(Collectors.toList());

        waitTillLoaderPresent();

        List capacityUsages = getDataFromCloudMappingTable(
                MigrationCloudMappingHostDetailsTable.CAPACITY);
        List<Map.Entry> cheapestInstances = getCheapestInstance(capacityUsages, instanceValuesFromModalTable);

        for (int i = 0; i < actualRecommendedInstanceNames.size(); i++) {
            Assert.assertEquals(actualRecommendedInstanceNames.get(i).toString(), cheapestInstances.get(i).getKey());
            LOGGER.pass("Expected instance is matching for: " + cheapestInstances.get(i).getKey(), test);
        }

        clickOnCostReductionTab();
        waitExecuter.sleep(5000);
        LOGGER.info("Validate recommended for Cost Reduction.", test);
        recommendedUsages = getDataFromCloudMappingTable(
                MigrationCloudMappingHostDetailsTable.RECOMMENDATION);
        actualRecommendedInstanceNames =
                (List) recommendedUsages.stream().filter(data -> data instanceof Map).map(data -> ((Map) data)
                        .get(CloudMappingHostConstants.HostDetails.RecommendedUsages.TYPE)).collect(Collectors.toList());
        List actualUsages = getDataFromCloudMappingTable(
                MigrationCloudMappingHostDetailsTable.ACTUAL_USAGE);
        cheapestInstances = getCheapestInstance(actualUsages, instanceValuesFromModalTable);

        for (int i = 0; i < actualRecommendedInstanceNames.size(); i++) {
            Assert.assertEquals(actualRecommendedInstanceNames.get(i).toString(), cheapestInstances.get(i).getKey());
            LOGGER.pass("Expected instance is matching for: " + cheapestInstances.get(i).getKey(), test);
        }
    }

    /**
     * Verify Total Hourly cost is less for cost reduction is less
     * than what is displayed for lift and shift
     *
     * @param test
     */
    public void verifyTotalHoulyCost(ExtentTest test) {
        clickOnLiftAndShiftTab();
        Double liftAndShiftTotalHourlyCost = getTotoalHourlyCostValue();
        clickOnCostReductionTab();
        waitExecuter.sleep(1000);
        Double costReductionTotalHourlyCost = getTotoalHourlyCostValue();
        Assert.assertTrue(costReductionTotalHourlyCost <= liftAndShiftTotalHourlyCost,
                "The Total Hourly Cost not equal to or less than what is displayed for Lift and Shift.");
        LOGGER.pass("The Total Hourly Cost is equal to or less than what is displayed for Lift and Shift.", test);
    }

    /**
     * Get Storage Name value
     */
    public String getStorageName() {
        return cmpPageObj.storageNameLabel.getText().split(":")[1].trim();
    }

    /**
     * Get cloud product and services name
     */
    public String getCloudProductAndServicesOnReportPage() {
        return cmpPageObj.cloudProductServicesLabel.getText().split(":")[1].trim();
    }
}
