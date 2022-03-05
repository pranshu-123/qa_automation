package com.qa.scripts.migration;

import com.qa.enums.UserAction;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.migration.ServicesAndVersionsCompatibilityPageObject;
import com.qa.pagefactory.reports.ReportsArchiveScheduledPageObject;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Birender Kumar
 */
public class ServicesAndVersionsCompatibility {

    private static final Logger LOGGER = Logger.getLogger(ServicesAndVersionsCompatibility.class.getName());
    Logger logger = Logger.getLogger(ServicesAndVersionsCompatibility.class.getName());
    private final WebDriver driver;
    private final WaitExecuter waitExecuter;
    private final ServicesAndVersionsCompatibilityPageObject servicesAndVersionsCompatibilityPageObject;
    private final SubTopPanelModulePageObject subTopPanelModulePageObject;
    private final UserActions actions;
    private final TopPanelPageObject topPanelPageObject;

    /**
     * Constructor to initialize wait, driver and necessary objects
     *
     * @param driver - WebDriver instance
     */
    public ServicesAndVersionsCompatibility(WebDriver driver) {
        this.driver = driver;
        waitExecuter = new WaitExecuter(driver);
        servicesAndVersionsCompatibilityPageObject = new ServicesAndVersionsCompatibilityPageObject(driver);
        subTopPanelModulePageObject = new SubTopPanelModulePageObject(driver);
        actions = new UserActions(driver);
        topPanelPageObject = new TopPanelPageObject(driver);
    }

    public void setupServicesAndVersionsCompatibilityPage() {
        waitExecuter.waitUntilElementClickable(topPanelPageObject.migrationTab);
        actions.performActionWithPolling(topPanelPageObject.migrationTab, UserAction.CLICK);
        for (; ; )
            if (servicesAndVersionsCompatibilityPageObject.toolboxCD.size() > 0) {
                waitExecuter.sleep(2000);
                waitExecuter.waitUntilElementClickable(servicesAndVersionsCompatibilityPageObject.toolboxCD.get(0));
                break;
            } else if (servicesAndVersionsCompatibilityPageObject.reportPageEmptyClusterDiscover.size() > 0) {
                waitExecuter.waitUntilElementPresent(servicesAndVersionsCompatibilityPageObject.reportPageEmptyClusterDiscover.get(0));
                break;
            }
        waitExecuter.waitUntilElementClickable(subTopPanelModulePageObject.servicesVersionMigrationTab);
    }

    public void clickOnServicesAndVersionMigrationTab() {
        waitExecuter.waitUntilElementClickable(subTopPanelModulePageObject.servicesVersionMigrationTab);
        actions.performActionWithPolling(subTopPanelModulePageObject.servicesVersionMigrationTab, UserAction.CLICK);
        for (; ; )
            if (servicesAndVersionsCompatibilityPageObject.clusterProductHeader.size() > 0) {
                waitExecuter.waitUntilElementPresent(servicesAndVersionsCompatibilityPageObject.clusterProductHeader.get(0));
                break;
            } else if (servicesAndVersionsCompatibilityPageObject.reportPageEmptyVersionCompatibility.size() > 0) {
                waitExecuter.waitUntilElementPresent(servicesAndVersionsCompatibilityPageObject.reportPageEmptyVersionCompatibility.get(0));
                break;
            }
        waitExecuter.waitUntilElementClickable(servicesAndVersionsCompatibilityPageObject.runBtn);
    }

    public void closeMessageBanner() {
        MouseActions.clickOnElement(driver, servicesAndVersionsCompatibilityPageObject.closeMsgBanner);
    }

    public void clickOnRunButton() {
        waitExecuter.waitUntilElementPresent(servicesAndVersionsCompatibilityPageObject.runBtn);
        String runBtnText = servicesAndVersionsCompatibilityPageObject.runBtn.getText();
        if (runBtnText.equals("Running..")) {
            try {
                waitExecuter.waitUntilTextToBeInWebElement(servicesAndVersionsCompatibilityPageObject.runBtn,
                        "Run");
            } catch (TimeoutException te) {
                throw new AssertionError("Services and Versions Compatibility Report is still running");
            }
        }
        MouseActions.clickOnElement(driver, servicesAndVersionsCompatibilityPageObject.runBtn);
    }

    public void clickOnRunNewButton() {
        MouseActions.clickOnElement(driver, servicesAndVersionsCompatibilityPageObject.runNewBtn);
    }

    public void selectCloudProduct(String cloudProductName) {
        waitExecuter.waitUntilElementClickable(servicesAndVersionsCompatibilityPageObject.cloudProductDropDown);
        actions.performActionWithPolling(servicesAndVersionsCompatibilityPageObject.cloudProductDropDown, UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(servicesAndVersionsCompatibilityPageObject.cloudProductSearchBox);
        servicesAndVersionsCompatibilityPageObject.cloudProductSearchBox.sendKeys(cloudProductName);
        waitExecuter.waitUntilElementClickable(servicesAndVersionsCompatibilityPageObject.cloudProductSearchFirstField);
        actions.performActionWithPolling(servicesAndVersionsCompatibilityPageObject.cloudProductSearchFirstField, UserAction.CLICK);
        waitExecuter.waitUntilElementClickable(servicesAndVersionsCompatibilityPageObject.runModalBtn);
    }

    public void clickOnRunModalButton() {
        waitExecuter.waitUntilElementClickable(servicesAndVersionsCompatibilityPageObject.runModalBtn);
        actions.performActionWithPolling(servicesAndVersionsCompatibilityPageObject.runModalBtn, UserAction.CLICK);
    }

    //Validate the latest report generated
    public void validateLatestReport() {
        waitExecuter.sleep(3000);

        List<WebElement> reportList = servicesAndVersionsCompatibilityPageObject.latestReportList;
        Assert.assertFalse(reportList.isEmpty(), "No latest report generated.");

        String clusterName = reportList.get(0).getText().trim();
        String cloudProduct = reportList.get(1).getText().trim();
        String reportCreated = reportList.get(2).getText().trim();

        Assert.assertFalse(clusterName.isEmpty(), "Cluster Name not displayed");
        logger.info("Cluster Name: [" + clusterName + "] displayed in the header");

        Assert.assertFalse(cloudProduct.isEmpty(), "Cloud Product not displayed");
        logger.info("Cloud Product: [" + cloudProduct + "] displayed in the header");

        Assert.assertFalse(reportCreated.isEmpty(), "Report Created time not displayed");
        logger.info("Report Created time: [" + reportCreated + "] displayed in the header");

        List<WebElement> legendsList = servicesAndVersionsCompatibilityPageObject.legendList;
        Assert.assertFalse(legendsList.isEmpty(), "No Legends available in report generated.");

        String legend1 = legendsList.get(0).getText().trim();
        String legend2 = legendsList.get(1).getText().trim();
        String legend3 = legendsList.get(2).getText().trim();
        String legend4 = legendsList.get(3).getText().trim();

        Assert.assertFalse(legend1.isEmpty(), "Legend1: 'Services and Versions are Compatible' not displayed");
        Assert.assertEquals(legend1.trim(), "Services and Versions are Compatible.", "Legend1" +
                " value mismatch");
        logger.info("Legend1 name: [" + legend1 + "] displayed in the header");

        logger.info("Legend2: " + legend2.trim());
        Assert.assertFalse(legend2.isEmpty(), "Legend2: 'Services and Versions are not Compatible' not displayed");
        Assert.assertEquals(legend2.trim(), "Services and Versions are not Compatible.", "Legend2" +
                " value mismatch");
        logger.info("Legend2 name: [" + legend2 + "] displayed in the header");

        logger.info("Legend3: " + legend3.trim());
        Assert.assertFalse(legend3.isEmpty(), "Legend3: 'Service is available in Source, but missing in Target'" +
                " not displayed");
        Assert.assertEquals(legend3.trim(), "Service is available in Source, but missing in Target.",
                "Legend3 value mismatch");
        logger.info("Legend3 name: [" + legend3 + "] displayed in the header");

        logger.info("Legend4: " + legend4.trim());
        Assert.assertFalse(legend4.isEmpty(), "Legend4: 'Service is missing in Source, but available in Target'" +
                " not displayed");
        Assert.assertEquals(legend4.trim(), "Service is missing in Source , but available in Target.",
                "Legend4 value mismatch");
        logger.info("Legend4 name: [" + legend4 + "] displayed in the header");

    }

    //This method is for validating all the cloud product from drop down
    public void getCloudProducts() {
        MouseActions.clickOnElement(driver, servicesAndVersionsCompatibilityPageObject.cloudProductDropDown);
        List<WebElement> cloudProductLists = servicesAndVersionsCompatibilityPageObject.cloudProductList;
        Assert.assertFalse(cloudProductLists.isEmpty(), "Cloud Products not displayed");

        String cloudGoogle = cloudProductLists.get(0).getText().trim();
        String cloudAmazon = cloudProductLists.get(1).getText().trim();
        String cloudAzure = cloudProductLists.get(2).getText().trim();

        Assert.assertFalse(cloudGoogle.isEmpty(), "Google Cloud product not displayed");
        Assert.assertEquals(cloudGoogle.trim(), "Google Dataproc", "Google Dataproc value mismatch");
        logger.info("Google Cloud product Name: [" + cloudGoogle + "] displayed.");

        Assert.assertFalse(cloudAmazon.isEmpty(), "Amazon Cloud Product not displayed");
        Assert.assertEquals(cloudAmazon.trim(), "Amazon EMR", "Amazon EMR value mismatch");
        logger.info("Amazon Cloud product: [" + cloudAmazon + "] displayed.");

        Assert.assertFalse(cloudAzure.isEmpty(), "Azure Cloud Product not displayed");
        Assert.assertEquals(cloudAzure.trim(), "Azure HDInsight", "Amazon EMR value mismatch");
        logger.info("Azure Cloud product: [" + cloudAzure + "] displayed.");

    }

    public List<String> getPlatforms() {
        waitExecuter.waitUntilElementPresent(servicesAndVersionsCompatibilityPageObject.reportTable);
        waitExecuter.waitUntilPageFullyLoaded();
        List<WebElement> platformsList = servicesAndVersionsCompatibilityPageObject.platformList;
        Assert.assertFalse(platformsList.isEmpty(), "Platform are not present in reports.");
        List<String> allPlatform = new ArrayList<>();
        for (int i = 1; i < platformsList.size(); i++) {
            allPlatform.add(platformsList.get(i).getText().trim());
        }
        logger.info("All platforms : " + allPlatform + " displayed.");
        return allPlatform;
    }

    public List<String> getHDPServicesList() {
        List<WebElement> hdpHeaderList = servicesAndVersionsCompatibilityPageObject.hdpHeaderList;
        Assert.assertFalse(hdpHeaderList.isEmpty(), "No table generated");
        List<String> hdpServicesList = new ArrayList<>();
        String onPremInfra = hdpHeaderList.get(0).getText();
        logger.info("Local Infra: " + onPremInfra);
        for (int i = 1; i < hdpHeaderList.size(); i++) {
            if (!hdpHeaderList.get(i).getText().isEmpty()) {
                hdpServicesList.add(hdpHeaderList.get(i).getText());
            }
        }
        logger.info("All localInfra services list are: " + hdpServicesList);
        return hdpServicesList;
    }

    //Get Empty HDP source service list
    public List<String> getEmptyHDPServicesList() {
        List<WebElement> hdpHeaderList = servicesAndVersionsCompatibilityPageObject.hdpHeaderList;
        Assert.assertFalse(hdpHeaderList.isEmpty(), "No table generated");
        List<String> hdpEmptyServicesList = new ArrayList<>();
        String onPremInfra = hdpHeaderList.get(0).getText();
        logger.info("Local Infra: " + onPremInfra);
        for (int i = 1; i < hdpHeaderList.size(); i++) {
            if (hdpHeaderList.get(i).getText().isEmpty()) {
                hdpEmptyServicesList.add("");
            }
        }
        logger.info("Total count of source services list which are empty are: " + hdpEmptyServicesList.size());
        return hdpEmptyServicesList;
    }

    public String getMajorVersion(String name) {
        String[] arr = name.split(" ");
        String[] arrVersion = arr[arr.length - 1].split("\\.");
        String majorVersion = arrVersion[0].replaceAll("[a-zA-Z-]", "");
        return majorVersion;
    }

    public String getMinorVersion(String name) {
        String[] arr = name.split(" ");
        try {
            String[] arrVersion = arr[arr.length - 1].split("\\.");
            String minorVersion = arrVersion[1].replaceAll("[a-zA-Z-]", "");
            return minorVersion;
        } catch (ArrayIndexOutOfBoundsException exception) {
            return String.valueOf(0);
        }
    }

    public String getBuildVersion(String name) {
        LOGGER.info("name: " + name);
        String[] arr = name.split(" ");
        String[] arrVersion = arr[1].split("\\.");
        if (arrVersion.length > 3)
            return arrVersion[2].replaceAll("[a-zA-Z-]", "");
        else
            return null;
    }


    //Check for Services and Versions are Compatible
    public void verifyServicesAndVersionsAreCompatible() {
        List<String> hdpServicesList = getHDPServicesList();
        int totalHDPServicesCount = hdpServicesList.size();
        LOGGER.info("HDP_HEADER_LIST$$$$$$" + hdpServicesList);
        List<WebElement> rowsList = servicesAndVersionsCompatibilityPageObject.getAllOnPremisesServices;
        Assert.assertFalse(rowsList.isEmpty(), "No Platform services data available");
        List<WebElement> colsList = servicesAndVersionsCompatibilityPageObject.colList;
        logger.info("Size of columnlist: " + colsList.size());
        Assert.assertFalse(colsList.isEmpty(), "No Platform services data available");

        for (int col = 0; col < totalHDPServicesCount - 1; col++) {
            for (int row = 0; row < rowsList.size(); row++) {

                String onPremServices = rowsList.get(row).getText();
                LOGGER.info("ROW$$$$$ " + row + " COL$$$$ " + col);
                String path = "//tbody/tr[" + (row + 1) + "]/td[" + (col + 2) + "]";
                LOGGER.info("PATH$$$$$$" + path);
                WebElement e = driver.findElement(By.xpath(path));
                if (!e.getText().isEmpty()) {
                    String cloudClusterServiceName = e.getText().trim();
                    LOGGER.info("cloudClusterServiceName$$$$$$" + cloudClusterServiceName);
                    String majorVersionCloud = getMajorVersion(cloudClusterServiceName);
                    String minorVersionCloud = getMinorVersion(cloudClusterServiceName);
                    String buildVersionCloud = getBuildVersion(cloudClusterServiceName);

                    int majorVersionCloudNum = Integer.parseInt(majorVersionCloud);
                    int minorVersionCloudNum = Integer.parseInt(minorVersionCloud);
                    int buildVersionCloudNum = 0;
                    if (buildVersionCloud != null)
                        buildVersionCloudNum = Integer.parseInt(buildVersionCloud);

                    String majorVersionHDP = getMajorVersion(onPremServices);
                    String minorVersionHDP = getMinorVersion(onPremServices);
                    String buildVersionHDP = getBuildVersion(onPremServices);

                    int majorVersionHDPNum = Integer.parseInt(majorVersionHDP);
                    int minorVersionHDPNum = Integer.parseInt(minorVersionHDP);
                    int buildVersionHDPNum = 0;
                    if (buildVersionHDP != null)
                        buildVersionHDPNum = Integer.parseInt(buildVersionHDP);

                    if (majorVersionCloudNum >= majorVersionHDPNum && minorVersionCloudNum >= minorVersionHDPNum
                            && buildVersionCloudNum > buildVersionHDPNum) {
                        //Now check for green //risk-0
                        String classAttributeName = e.getAttribute("class");
                        logger.info("Element class attribute name: " + classAttributeName);
                        //check for class attribute name as 'risk-0' which is for Green color.
                        Assert.assertTrue(classAttributeName.equals("risk-0"), "Platforms service in the box is not" +
                                " marked in Green for element: " + e.getText());
                    }
                }
            }
        }
    }

    //Check for Services and Versions are not Compatible
    public void verifyServicesAndVersionsAreNotCompatible() {
        List<String> hdpServicesList = getHDPServicesList();
        int totalHDPServicesCount = hdpServicesList.size();
        List<WebElement> rowsList = servicesAndVersionsCompatibilityPageObject.getAllOnPremisesServices;
        Assert.assertFalse(rowsList.isEmpty(), "No Platform services data available");
        List<WebElement> colsList = servicesAndVersionsCompatibilityPageObject.colList;
        logger.info("Size of columnlist: " + colsList.size());
        Assert.assertFalse(colsList.isEmpty(), "No Platform services data available");

        for (int col = 0; col < totalHDPServicesCount - 1; col++) {
            for (int row = 0; row < rowsList.size(); row++) {
                String onPremServices = rowsList.get(row).getText();
                String path = "//tbody/tr[" + (row + 1) + "]/td[" + (col + 2) + "]";
                LOGGER.info("PATH$$$$$$" + path);
                WebElement e = driver.findElement(By.xpath(path));
                if (!e.getText().isEmpty()) {
                    LOGGER.info("On Cloud Service- $$$$$$ " + e.getText().trim());
                    LOGGER.info("$$$$$$- onPremServices- " + onPremServices);
                    String majorVersionOnPrem = getMajorVersion(onPremServices);
                    int majorVersionOnPremNum = Integer.parseInt(majorVersionOnPrem);
                    String cloudClusterServiceName = e.getText().trim();
                    String majorVersionCloud = getMajorVersion(cloudClusterServiceName);
                    int majorVersionCloudNum = Integer.parseInt(majorVersionCloud);
                    LOGGER.info("$$$$$$- majorVersionCloudNum- " + majorVersionCloudNum);
                    LOGGER.info("$$$$$$- majorVersionOnPremNum- " + majorVersionOnPremNum);
                    if (majorVersionOnPremNum > majorVersionCloudNum) {
                        //Now check for green //risk-2
                        String classAttributeName = e.getAttribute("class");
                        logger.info("Element class attribute name: " + classAttributeName);
                        //check for class attribute name as 'risk-2' which is for Orange color.
                        Assert.assertTrue(classAttributeName.equals("risk-2"), "Platforms service in the box is not" +
                                " marked in Orange for element: " + e.getText());
                    }
                }
            }
        }
    }


    //Check for Services and Versions are available in source but missing in target
    public void verifyServicesAndVersionsAreAvailableInSourceButMissingInTarget() {
        List<String> hdpServicesList = getHDPServicesList();
        int totalHDPServicesCount = hdpServicesList.size();
        List<WebElement> rowsList = servicesAndVersionsCompatibilityPageObject.getAllOnPremisesServices;
        Assert.assertFalse(rowsList.isEmpty(), "No Platform services data available");
        List<WebElement> colsList = servicesAndVersionsCompatibilityPageObject.colList;
        logger.info("Size of columnlist: " + colsList.size());
        Assert.assertFalse(colsList.isEmpty(), "No Platform services data available");

        for (int col = 0; col < totalHDPServicesCount - 1; col++) {
            for (int row = 0; row < rowsList.size(); row++) {
                String path = "//tbody/tr[" + (row + 1) + "]/td[" + (col + 2) + "]";
                WebElement e = driver.findElement(By.xpath(path));
                if (e.getText().isEmpty()) {
                    //Now check for brown //risk-3
                    String classAttributeName = e.getAttribute("class");
                    logger.info("Element class attribute name: " + classAttributeName);
                    //check for class attribute name as 'risk-3' which is for Orange color.
                    Assert.assertTrue(classAttributeName.equals("risk-3"), "Platforms service in the box is not" +
                            " marked in Brown for element: " + e.getText());
                }
            }
        }
    }

    //scroll window horizontally to the right
    public void scrollToRight(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollLeft = arguments[0].offsetWidth + 500", element);

    }

    //Check for Services and Versions are missing in source but available in target
    public void verifyServicesAndVersionsAreMissingInSourceButAvailableInTarget() {
        scrollToRight(driver, servicesAndVersionsCompatibilityPageObject.tableBodyElement);
        waitExecuter.sleep(3000);
        List<WebElement> missingSrcHeaderlist = servicesAndVersionsCompatibilityPageObject.missingSrcHeaderElement;
        Assert.assertFalse(missingSrcHeaderlist.isEmpty(), "No missing source headers generated");
        logger.info("Total missing src header elements found: " + missingSrcHeaderlist.size());
        List<String> missingSrcHeaderElementsList = new ArrayList<>();
        for (int i = 0; i < missingSrcHeaderlist.size(); i++) {
            if (!missingSrcHeaderlist.get(i).getText().isEmpty()) {
                missingSrcHeaderElementsList.add(missingSrcHeaderlist.get(i).getText());
            }
        }
        logger.info("All missing source header elements: " + missingSrcHeaderElementsList);
    }

    /**
     * Method to validate report, download, delete, view report from actions tab
     */
    public void verifyReportsArchived(ReportsArchiveScheduledPageObject reportPageObj, String name, String reportAction) {
        waitExecuter.waitUntilElementPresent(servicesAndVersionsCompatibilityPageObject.archivesText);
        List<WebElement> reportNameList = reportPageObj.reportNames;
        List<WebElement> reportCntList = reportPageObj.reportCnt;
        Assert.assertFalse(reportNameList.isEmpty(), "There are no reports listed.");

        waitExecuter.waitUntilPageFullyLoaded();
        for (int i = 0; i < reportNameList.size(); i++) {
            int reportCnt = Integer.parseInt(reportCntList.get(i).getText().trim());
            logger.info("ReportCnt is " + reportCnt);
            String reportName = reportNameList.get(i).getText().trim();
            logger.info("The report name is " + reportName);
            if (reportName.equals(name) && reportCnt > 0) {
                switch (reportAction) {
                    case "checkReport":
                        logger.info("Checking report");
                        MouseActions.clickOnElement(driver, reportCntList.get(i));
                        waitExecuter.waitUntilPageFullyLoaded();
                        waitExecuter.waitUntilElementPresent(servicesAndVersionsCompatibilityPageObject.archiveReportSVCHeader);
                        List<WebElement> reportTblRows = reportPageObj.tableRows;
                        Assert.assertFalse(reportTblRows.isEmpty(), "No reports archived.");
                        MouseActions.clickOnElement(driver, servicesAndVersionsCompatibilityPageObject.archives);
                        break;
                    case "downloadReport":
                        MouseActions.clickOnElement(driver, reportCntList.get(i));
                        waitExecuter.waitUntilPageFullyLoaded();
                        waitExecuter.waitUntilElementPresent(servicesAndVersionsCompatibilityPageObject.archiveReportSVCHeader);
                        MouseActions.clickOnElement(driver, reportPageObj.downloadReportIcon);
                        logger.info("Downloading report");
                        waitExecuter.waitUntilPageFullyLoaded();
                        Assert.assertEquals(reportPageObj.successfulMsgBanner.getText(), "Downloaded successfully",
                                " No downloaded successfully message received.");
                        MouseActions.clickOnElement(driver, servicesAndVersionsCompatibilityPageObject.archives);
                        break;
                    case "deleteReport":
                        MouseActions.clickOnElement(driver, reportCntList.get(i));
                        waitExecuter.waitUntilPageFullyLoaded();
                        waitExecuter.waitUntilElementPresent(servicesAndVersionsCompatibilityPageObject.archiveReportSVCHeader);
                        MouseActions.clickOnElement(driver, reportPageObj.deleteReportIcon);
                        waitExecuter.waitUntilPageFullyLoaded();
                        //Alert confirmationAlert = driver.switchTo().alert();
                        waitExecuter.waitUntilElementPresent(servicesAndVersionsCompatibilityPageObject.deletePopText);
                        String popText = servicesAndVersionsCompatibilityPageObject.deletePopText.getText();
                        //String alertText = confirmationAlert.getText();
                        logger.info("Alert text is " + popText);
                        //confirmationAlert.accept();
                        waitExecuter.waitUntilElementPresent(servicesAndVersionsCompatibilityPageObject.deleteOkBtn);
                        MouseActions.clickOnElement(driver, servicesAndVersionsCompatibilityPageObject.deleteOkBtn);
                        logger.info("Deleted report");
                        Assert.assertEquals(reportPageObj.successfulMsgBanner.getText(), "Removed successfully",
                                " Report not removed");
                        MouseActions.clickOnElement(driver, servicesAndVersionsCompatibilityPageObject.archives);
                        break;
                    case "viewReport":
                        MouseActions.clickOnElement(driver, reportCntList.get(i));
                        waitExecuter.waitUntilPageFullyLoaded();
                        waitExecuter.waitUntilElementPresent(servicesAndVersionsCompatibilityPageObject.archiveReportSVCHeader);
                        MouseActions.clickOnElement(driver, reportPageObj.viewReportIcon);
                        logger.info("Viewed report");
                        waitExecuter.waitUntilPageFullyLoaded();
                        Assert.assertTrue(reportPageObj.viewReportDialogWin.isDisplayed(), "Report  view not present.");
                        MouseActions.clickOnElement(driver, reportPageObj.closeTab);
                        waitExecuter.waitUntilPageFullyLoaded();
                        break;
                    case "searchReportByDate":
                        MouseActions.clickOnElement(driver, reportCntList.get(i));
                        waitExecuter.waitUntilPageFullyLoaded();
                        waitExecuter.waitUntilElementPresent(servicesAndVersionsCompatibilityPageObject.archiveReportSVCHeader);
                        String dateFromElement = servicesAndVersionsCompatibilityPageObject.archiveReportDate.getText().trim();
                        String[] arrDate = dateFromElement.split(" ");
                        String date = arrDate[0];
                        LOGGER.info(date);
                        reportPageObj.reportSearchBox.sendKeys(date);
                        List<WebElement> searchDateReportNameList = reportPageObj.reportNames;
                        Assert.assertFalse(searchDateReportNameList.isEmpty(), "There are no reports listed");
                        Assert.assertTrue(searchDateReportNameList.size() > 0, "Expected search " +
                                "result not populated data by date.");
                        logger.info("Search report by date: " + date);
                        MouseActions.clickOnElement(driver, servicesAndVersionsCompatibilityPageObject.archives);
                        break;
                    case "searchReportByStatus":
                        MouseActions.clickOnElement(driver, reportCntList.get(i));
                        waitExecuter.waitUntilPageFullyLoaded();
                        waitExecuter.waitUntilElementPresent(servicesAndVersionsCompatibilityPageObject.archiveReportSVCHeader);
                        String status = "success";
                        reportPageObj.reportSearchBox.sendKeys(status);
                        waitExecuter.waitUntilElementPresent(reportPageObj.sortingReportNameIcon);
                        List<WebElement> searchStatusReportNameList = reportPageObj.reportNames;
                        Assert.assertFalse(searchStatusReportNameList.isEmpty(), "There are no reports listed");
                        Assert.assertTrue(searchStatusReportNameList.size() > 0, "Expected search " +
                                "result not populated data by status .");
                        logger.info("Searched report for status as: " + status);
                        MouseActions.clickOnElement(driver, servicesAndVersionsCompatibilityPageObject.archives);
                        break;
                    case "searchReportByName":
                        MouseActions.clickOnElement(driver, reportCntList.get(i));
                        waitExecuter.waitUntilPageFullyLoaded();
                        waitExecuter.waitUntilElementPresent(servicesAndVersionsCompatibilityPageObject.archiveReportSVCHeader);
                        //Give any name which shows in the UI
                        String searchReportName = servicesAndVersionsCompatibilityPageObject.archiveReportName.getText().trim();
                        reportPageObj.reportSearchBox.sendKeys(searchReportName);
                        waitExecuter.waitUntilElementPresent(reportPageObj.sortingReportNameIcon);
                        List<WebElement> searchNameReportNameList = reportPageObj.reportNames;
                        Assert.assertFalse(searchNameReportNameList.isEmpty(), "There are no reports listed");
                        Assert.assertTrue(searchNameReportNameList.size() > 0, "Expected search " +
                                "result not populated data by report name.");
                        logger.info("Searched report by name as :" + searchReportName);
                        break;
                }
                break;
            }
        }
    }


    public void setScheduleCloudName(String cloudName) {
        waitExecuter.waitUntilElementPresent(servicesAndVersionsCompatibilityPageObject.scheduleCloudDropDown);
        selectCloudProduct(cloudName);
    }

    public void setScheduleName(String scheduleName) {
        waitExecuter.waitUntilElementPresent(servicesAndVersionsCompatibilityPageObject.scheduleName);
        actions.performActionWithPolling(servicesAndVersionsCompatibilityPageObject.scheduleName,
                UserAction.SEND_KEYS, scheduleName);
    }

    public void setScheduleToRun(String scheduleToRun) {
        waitExecuter.waitUntilElementPresent(servicesAndVersionsCompatibilityPageObject.scheduleToRun);
        Select drpScheduleToRun = new Select(servicesAndVersionsCompatibilityPageObject.scheduleToRun);
        drpScheduleToRun.selectByVisibleText(scheduleToRun);
    }

    public void setScheduleTime(String scheduleTime) {
        String[] arrTime = scheduleTime.split(":");
        String hours = arrTime[0];
        String minutes = arrTime[1];

        waitExecuter.waitUntilElementPresent(servicesAndVersionsCompatibilityPageObject.scheduleTime);
        actions.performActionWithPolling(servicesAndVersionsCompatibilityPageObject.scheduleTime, UserAction.CLICK);

        waitExecuter.waitUntilElementPresent(servicesAndVersionsCompatibilityPageObject.scheduleTimeHours);
        Select hrDrpDown = new Select(servicesAndVersionsCompatibilityPageObject.scheduleTimeHours);
        hrDrpDown.selectByVisibleText(hours);

        waitExecuter.waitUntilElementPresent(servicesAndVersionsCompatibilityPageObject.scheduleTimeMinutes);
        Select minsDrpDown = new Select(servicesAndVersionsCompatibilityPageObject.scheduleTimeMinutes);
        minsDrpDown.selectByVisibleText(minutes);
    }

    public void setScheduleNotification(String notification) {
        waitExecuter.waitUntilElementPresent(servicesAndVersionsCompatibilityPageObject.scheduleNotification);
        actions.performActionWithPolling(servicesAndVersionsCompatibilityPageObject.scheduleNotification,
                UserAction.SEND_KEYS, notification);
    }

}
