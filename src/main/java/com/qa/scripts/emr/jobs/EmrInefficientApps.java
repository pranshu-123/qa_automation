package com.qa.scripts.emr.jobs;

import com.qa.enums.UserAction;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.appsDetailsPage.MrAppsDetailsPageObject;
import com.qa.pagefactory.emr.jobs.EmrApplicationsPageObject;
import com.qa.pagefactory.emr.jobs.EmrInefficientAppsPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class EmrInefficientApps {

    private static final Logger LOGGER = LoggerFactory.getLogger(com.qa.scripts.jobs.applications.InefficientApps.class);
    private final WaitExecuter waitExecuter;
    private final WebDriver driver;
    private final EmrApplicationsPageObject applicationsPageObject;

    private final EmrInefficientAppsPageObject inefficientAppsPageObject;
    private final UserActions userActions;

    /**
     * Constructor to initialize wait, driver and necessary objects
     *
     * @param driver - WebDriver instance
     */
    public EmrInefficientApps(WebDriver driver) {
        waitExecuter = new WaitExecuter(driver);
        this.driver = driver;
        applicationsPageObject = new EmrApplicationsPageObject(driver);
        inefficientAppsPageObject = new EmrInefficientAppsPageObject(driver);
        userActions = new UserActions(driver);
    }

    /* This method will do the setup for getting inefficientApp page
     * 1. Click on Jobs tab
     * 2. Click on inefficientApps tab
     * 3. Select last 30 days from date picker
     * */
    public void setupInefficientApps() {
        //Click on Jobs tab
        SubTopPanelModulePageObject subTopPanelModulePageObject = new SubTopPanelModulePageObject(driver);
        waitExecuter.sleep(2000);
        waitExecuter.waitUntilElementClickable(subTopPanelModulePageObject.jobs);
        userActions.performActionWithPolling(subTopPanelModulePageObject.jobs, UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        waitExecuter.waitUntilPageFullyLoaded();
        LOGGER.info("Click on Job tab");

        //Click on inefficientApps tab
        waitExecuter.sleep(2000);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.inefficientApps);
        userActions.performActionWithPolling(applicationsPageObject.inefficientApps, UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        LOGGER.info("Click on inefficient Apps tab");

        DatePicker datePicker = new DatePicker(driver);
        // Select last 30 days from date picker
        LOGGER.info("Select last 30 days");
        datePicker.clickOnDatePicker();
        waitExecuter.sleep(1000);
        datePicker.selectLast30Days();
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        waitExecuter.sleep(2000);
    }

    public List<WebElement> getClusterListFromDropdown() {
        return inefficientAppsPageObject.clusterList;
    }

    public void clickOnClusterDropDown() {
        LOGGER.info("Select Cluster");
        userActions.performActionWithPolling(inefficientAppsPageObject.clusterDropdown, UserAction.CLICK);
        waitExecuter.waitUntilElementPresent(inefficientAppsPageObject.resetButton);
        inefficientAppsPageObject.clusterSearchBox.clear();
        waitExecuter.sleep(1000);
        waitExecuter.waitUntilElementClickable(inefficientAppsPageObject.clusterSearchBox);
    }

    /**
     * Method to click the first app in jobs table , navigate to the details page.
     * and verify app Id .
     */
    public String verifyAppId(EmrInefficientAppsPageObject inPageObject) {
        String appId = inPageObject.getAppId.getText().trim();
        LOGGER.info("Tez application Id is " + appId);
        waitExecuter.waitUntilElementClickable(inPageObject.clickOnAppId);
        inPageObject.clickOnAppId.click();
        waitExecuter.waitUntilElementClickable(inPageObject.closeIcon);
        waitExecuter.waitUntilPageFullyLoaded();
        String headerAppId = inPageObject.getHeaderAppId.getText().trim();
        Assert.assertEquals(appId, headerAppId, "All Application Id is not displayed in the Header");
        return headerAppId;
    }

    /**
     * Method to verify the following:
     * 1.All the KPIs should be listed and the data must be populated.
     * (Duration, Start time, end time, job count, stages count)
     * 2. Owner, cluster, queue must be populated on the top right
     */
    public String verifyRightPaneKpis(EmrInefficientAppsPageObject emrEfficientApps) {
        List<WebElement> kpiList = emrEfficientApps.rightPaneKpis;
        validateLeftPaneKpis(kpiList);
        List<WebElement> appKpis = emrEfficientApps.rightPaneAppKpis;
        List<WebElement> appKpiVal = emrEfficientApps.rightPaneAppKpiVal;
        Assert.assertFalse(appKpis.isEmpty(), "No application kpis are listed in the right pane");
        Assert.assertFalse(appKpiVal.isEmpty(), "Application kpi values are empty");
        String appDuration = "0";
        for (int i = 0; i < appKpis.size(); i++) {
            Assert.assertNotNull(appKpis.get(i).getText(), "Kpi text is empty");
            Assert.assertNotNull(appKpiVal.get(i).getText(), "Kpi Value is empty");
            appDuration = (appKpiVal.get(0).getText().trim());
            LOGGER.info("Kpi Name = " + appKpis.get(i).getText() + " Value = " + appKpiVal.get(i).getText());
        }
        LOGGER.info("The application duration is " + appDuration);
        return appDuration;
    }

    /**
     * Verify that Left pane must be opened and should have KPIs listed
     * (start, end and duration are listed and should not be empty)
     */
    public void validateLeftPaneKpis(List<WebElement> kpiList) {
        for (WebElement webElement : kpiList) {
            LOGGER.info("The leftPane kpi is " + webElement.getText());
            String kpis = webElement.getText();
            Assert.assertNotNull(kpis, "The kpis is empty");
            String[] kpisOut = kpis.split(": ");
            String kpiName = kpisOut[0];
            String kpiVal = kpisOut[1];
            LOGGER.info("Kpi name = " + kpisOut[0] + "  Kpi Value = " + kpisOut[1]);
            Assert.assertNotNull(kpiName, "The kpi " + kpiName + " is empty");
            Assert.assertNotNull(kpiVal, "The kpi " + kpiVal + " is empty");
        }
    }

    /* To reset the settings made in apps page */
    public void reset() {
        LOGGER.info("Reset username filter");
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
        userActions.performActionWithPolling(applicationsPageObject.resetButton, UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.resetButton);
    }

    /* Check InefficientApp Table is populated */
    public Boolean verifyInefficientTbl() {
        waitExecuter.sleep(2000);
        waitExecuter.waitUntilElementPresent(applicationsPageObject.tblInefficientApps);
        LOGGER.info("Total Number of Rows in inefficientApp table: " + applicationsPageObject.tblInefficientAppsRowsList.size());
        Boolean boolTbl = false;
        if (applicationsPageObject.tblInefficientAppsRowsList.size() > 0) {
            boolTbl = true;
        }
        return boolTbl;
    }

    /* Click on WebElement and retrying */
    public void retryingFindClick(WebElement webElement) {
        int attempts = 0;
        while (attempts < 2) {
            try {
                waitExecuter.sleep(2000);
                webElement.click();
                waitExecuter.sleep(2000);
                break;
            } catch (StaleElementReferenceException e) {
                e.printStackTrace();
            }
            attempts++;
        }
    }

    /* Get List of ClusterIds */
    public List<String> getAllClusterIds() {
        waitExecuter.sleep(3000);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.clusterSearchBox);
        retryingFindClick(applicationsPageObject.clusterSearchBox);
        waitExecuter.sleep(2000);
        List<String> listClusterIds = new ArrayList<String>();
        for (int i = 0; i < applicationsPageObject.clusterIdsList.size(); i++) {
            listClusterIds.add(applicationsPageObject.clusterIdsList.get(i).getText());
        }
        return listClusterIds;
    }

    /* Click on Application Event check boxes */
    public void clickOnApplicationEvent() {
        waitExecuter.sleep(1000);
        applicationsPageObject.applicationEvent.click();
        waitExecuter.waitUntilPageFullyLoaded();
    }

    /* Click on First check box in Application Type and Application Event check boxes */
    public void clickFirstChkBoxOfAppTypeAndAppEventEach() {
        waitExecuter.sleep(1000);
        applicationsPageObject.applicationTypeFirstApp.click();
        waitExecuter.sleep(3000);
        waitExecuter.waitUntilPageFullyLoaded();
    }

    /* Check First check box in Application Type check boxes is checked */
    public boolean verifyFirstChkBoxOfAppTypeIsChecked() {
        waitExecuter.sleep(2000);
        return applicationsPageObject.applicationTypeFirstApp.isSelected();
    }

    /* Check First check box in Application Event check boxes is checked */
    public boolean verifyFirstChkBoxOfAppEventIsChecked() {
        waitExecuter.sleep(3000);
        return applicationsPageObject.applicationEventFirstApp.isSelected();
    }

    /**
     * Method to click on 'Only' and select a specific checkbox from job pages left
     * pane
     *
     * @param types Types can be appType |  Application Events
     */
    public int clickOnlyLink(String types) {
        Actions action = new Actions(driver);
        WebElement we = driver
                .findElement(By.xpath("(//label[contains(@class,'checkbox')])/span[contains(text(),'" + types + "')]"));
        action.moveToElement(we)
                .moveToElement(driver.findElement(By.xpath("(//label[contains(@class,'checkbox')])"
                        + "/span[contains(text(),'" + types + "')]/following-sibling::span[2]")))
                .click().build().perform();
        waitExecuter.waitUntilElementClickable(inefficientAppsPageObject.resetButton);
        WebElement ele = driver.findElement(By.xpath("(//label[contains(@class,'checkbox')])"
                + "/span[contains(text(),'" + types + "')]/following-sibling::span[1]"));
        waitExecuter.sleep(3000);
        int appCount = Integer.parseInt(ele.getText().replaceAll("[^\\dA-Za-z ]", "").trim());
        waitExecuter.sleep(3000);
        return appCount;
    }

    /* Click on Show all option */
    public void clickShowAll() {
        waitExecuter.sleep(2000);
        if (applicationsPageObject.showAll.isDisplayed()) {
            waitExecuter.sleep(2000);
            applicationsPageObject.showAll.click();
        }
    }

    /* Click on Application Type check boxes */
    public void clickOnApplicationType() {
        waitExecuter.sleep(1000);
        applicationsPageObject.applicationType.click();
        waitExecuter.waitUntilPageFullyLoaded();
    }

    public boolean verifyAllAppEventChkBoxIsChecked() {
        List<WebElement> checkboxes = inefficientAppsPageObject.getApplicationEventChkBoxList;
        boolean isSelected = true;
        JavascriptExecutor js = (JavascriptExecutor) driver;
        if (checkboxes.isEmpty()) {
            LOGGER.info("No Checkbox present in the page");
        } else {
            for (WebElement checkbox : checkboxes) {
                if (checkbox.isDisplayed()) {
                    String text = (String) js.executeScript("return arguments[0].nextSibling.textContent.trim();", checkbox);
                    LOGGER.info(text);
                } else {
                    isSelected = false;
                    break;
                }
            }
        }
        return isSelected;
    }

    /* Check for InefficientApp table presence */
    public boolean verifyInefficientAppsTblPresent() {
        waitExecuter.sleep(2000);
        int countInefficientAppsTblRow = applicationsPageObject.inefficientAppsTblRowsList.size();
        LOGGER.info("Count of Table Rows :" + countInefficientAppsTblRow);
        return countInefficientAppsTblRow > 0;
    }

    /* Get all InefficientApp application Table row count */
    public int getTotalCountOfInefficientAppsTblRow() {
        int countInefficientAppsTblRow = applicationsPageObject.inefficientAppsTblRowsList.size();
        return countInefficientAppsTblRow;
    }

    /* Click on inefficientApp table column for sorting */
    public void clickOnInefficientAppsTblColSort() {
        int countOfHeaderToSort = applicationsPageObject.inefficientAppsIconSortTbl.size();
        int countInefficientAppsTblRowBeforeClick = getTotalCountOfInefficientAppsTblRow();
        if (countOfHeaderToSort > 0) {
            for (int i = 0; i < countOfHeaderToSort; i++) {
                waitExecuter.sleep(2000);
                applicationsPageObject.inefficientAppsIconSortTbl.get(i).click();
                waitExecuter.sleep(3000);
                int countInefficientAppsTblRow = applicationsPageObject.inefficientAppsTblRowsList.size();
                Assert.assertEquals(countInefficientAppsTblRow, countInefficientAppsTblRowBeforeClick,
                        "InefficientAppTableRow count mismatch before and after sort");
            }

        }
    }

    /* Get no data message if Inefficient App table not generated */
    public String getInefficientAppTblDataMsg() {
        waitExecuter.sleep(2000);
        return applicationsPageObject.inefficientAppsTblNoDataMsg.getText();
    }

    /* Add all application count */
    public int addApplicationTypeCount() {
        List<WebElement> appJobCounts = inefficientAppsPageObject.getEachApplicationTypeJobCounts;
        List<Integer> listOfJobCounts = new ArrayList<>();
        int totalCount = 0;
        for (int i = 0; i < appJobCounts.size(); i++) {
            listOfJobCounts.add(Integer.parseInt(appJobCounts.get(i).getText().replaceAll("[^\\dA-Za-z ]", "").trim()));
        }
        for (int jobCount : listOfJobCounts) {
            totalCount += jobCount;
        }
        return totalCount;
    }
}
