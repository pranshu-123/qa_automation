package com.qa.scripts.jobs.applications;

import com.qa.pagefactory.TopPanelComponentPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.utils.WaitExecuter;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class InefficientApps {

    private static final Logger LOGGER = LoggerFactory.getLogger(InefficientApps.class);
    private WaitExecuter waitExecuter;
    private WebDriver driver;
    private ApplicationsPageObject applicationsPageObject;

    /**
     * Constructor to initialize wait, driver and necessary objects
     *
     * @param driver - WebDriver instance
     */
    public InefficientApps(WebDriver driver) {
        waitExecuter = new WaitExecuter(driver);
        this.driver = driver;
        applicationsPageObject = new ApplicationsPageObject(driver);
    }

    /* This method will do the setup for getting inefficientApp page
    * 1. Click on Jobs tab
    * 2. Click on inefficientApps tab
    * 3. Select last 30 days from date picker
    * */
    public void setupInefficientApps(){
        //Click on Jobs tab
        TopPanelComponentPageObject topPanelComponentPageObject = new TopPanelComponentPageObject(driver);
        waitExecuter.sleep(2000);
        topPanelComponentPageObject.jobs.click();
        waitExecuter.waitUntilElementPresent(applicationsPageObject.jobsPageHeader);
        waitExecuter.waitUntilPageFullyLoaded();
        LOGGER.info("Click on Job tab");

        //Click on inefficientApps tab
        waitExecuter.sleep(2000);
        applicationsPageObject.inefficientApps.click();
        waitExecuter.waitUntilPageFullyLoaded();
        LOGGER.info("Click on inefficient Apps tab");

        DatePicker datePicker = new DatePicker(driver);
        // Select last 30 days from date picker
        LOGGER.info("Select last 30 days");
        datePicker.clickOnDatePicker();
        waitExecuter.sleep(1000);
        datePicker.selectLast30Days();
        waitExecuter.sleep(2000);
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

    /* Select cluster from list */
    public void selectCluster(String clusterId) {
        waitExecuter.sleep(3000);
        waitExecuter.waitUntilElementClickable(applicationsPageObject.clusterSearchBox);
        applicationsPageObject.clusterSearchBox.click();
        waitExecuter.sleep(1000);
        LOGGER.info("Search for cluster: " + clusterId);
        applicationsPageObject.clusterSearchBox.sendKeys(clusterId);
        waitExecuter.sleep(1000);
        applicationsPageObject.select1stCluster.click();
        waitExecuter.sleep(1000);
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
        applicationsPageObject.applicationEventFirstApp.click();
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

    /* Click on Application Type check boxes */
    public void clickAllApplicationTypeChkBox() {
        waitExecuter.sleep(1000);
        int chkBoxCount = applicationsPageObject.getApplicationTypeChkBoxList.size();
        if (chkBoxCount > 0) {
            for (int i = 0; i < chkBoxCount; i++) {
                waitExecuter.sleep(2000);
                applicationsPageObject.getApplicationTypeChkBoxList.get(i).click();
                waitExecuter.sleep(2000);
                waitExecuter.waitUntilPageFullyLoaded();
            }
        }
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

    /* Click all InefficientApp event check box */
    public void clickAllApplicationEventChkBox() {
        waitExecuter.sleep(1000);
        int chkBoxCount = applicationsPageObject.getApplicationEventChkBoxList.size();
        if (chkBoxCount > 0) {
            for (int i = 0; i < chkBoxCount; i++) {
                waitExecuter.sleep(2000);
                applicationsPageObject.getApplicationEventChkBoxList.get(i).click();
                waitExecuter.sleep(2000);
                waitExecuter.waitUntilPageFullyLoaded();
            }
        }
    }

    /* Check for All Apps Event check box on InefficientApp is checked */
    public boolean verifyAllAppEventChkBoxIsChecked() {
        waitExecuter.sleep(1000);
        boolean isSelected = true;
        int chkBoxCount = applicationsPageObject.getApplicationEventChkBoxList.size();
        if (chkBoxCount > 0) {
            for (int i = 0; i < chkBoxCount; i++) {
                waitExecuter.sleep(2000);
                if (applicationsPageObject.getApplicationEventChkBoxList.get(i).isSelected()) {
                    continue;
                } else {
                    isSelected = false;
                    break;
                }
            }
        } else {
            isSelected = false;
        }
        return isSelected;
    }

    /* Check for InefficientApp table presence */
    public boolean verifyInefficientAppsTblPresent() {
        waitExecuter.sleep(2000);
        int countInefficientAppsTblRow = applicationsPageObject.inefficientAppsTblRowsList.size();
        LOGGER.info("Count of Table Rows :" + countInefficientAppsTblRow);
        if (countInefficientAppsTblRow > 0) {
            return true;
        }
        return false;
    }

    /* Get all InefficientApp application Table row count */
    public int getTotalCountOfInefficientAppsTblRow() {
        int countInefficientAppsTblRow = applicationsPageObject.inefficientAppsTblRowsList.size();
        if (countInefficientAppsTblRow > 0) {
            return countInefficientAppsTblRow;
        }
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
        List<WebElement> appJobCounts = applicationsPageObject.getEachApplicationTypeJobCounts;
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
