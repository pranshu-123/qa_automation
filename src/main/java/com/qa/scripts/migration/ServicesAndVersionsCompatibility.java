package com.qa.scripts.migration;

import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.migration.ServicesAndVersionsCompatibilityPageObject;
import com.qa.pagefactory.reports.ReportsArchiveScheduledPageObject;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
    private WebDriver driver;
    private WaitExecuter waitExecuter;
    private ServicesAndVersionsCompatibilityPageObject servicesAndVersionsCompatibilityPageObject;
    private SubTopPanelModulePageObject subTopPanelModulePageObject;

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
    }

    public void setupServicesAndVersionsCompatibilityPage() {
        TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);
        waitExecuter.waitUntilElementPresent(topPanelPageObject.migrationTab);
        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.waitUntilElementClickable(topPanelPageObject.migrationTab);
        waitExecuter.waitUntilPageFullyLoaded();
        MouseActions.clickOnElement(driver, topPanelPageObject.migrationTab);
        waitExecuter.waitUntilElementPresent(subTopPanelModulePageObject.servicesVersionMigrationTab);
    }

    public void clickOnServicesAndVersionMigrationTab() {
        MouseActions.clickOnElement(driver, subTopPanelModulePageObject.servicesVersionMigrationTab);
    }

    public void closeMessageBanner() {
        MouseActions.clickOnElement(driver, servicesAndVersionsCompatibilityPageObject.closeMsgBanner);
    }

    public void clickOnRunButton() {
        MouseActions.clickOnElement(driver, servicesAndVersionsCompatibilityPageObject.runBtn);
    }

    public void clickOnRunNewButton() {
        MouseActions.clickOnElement(driver, servicesAndVersionsCompatibilityPageObject.runNewBtn);
    }

    public void selectCloudProduct(String cloudProductName) {
        MouseActions.clickOnElement(driver, servicesAndVersionsCompatibilityPageObject.cloudProductDropDown);
        servicesAndVersionsCompatibilityPageObject.cloudProductSearchBox.sendKeys(cloudProductName);
        servicesAndVersionsCompatibilityPageObject.cloudProductSearchFirstField.click();
    }

    public void clickOnRunModalButton() {
        MouseActions.clickOnElement(driver, servicesAndVersionsCompatibilityPageObject.runModalBtn);
    }

    //Validate the latest report generated
    public void validateLatestReport() {
        waitExecuter.waitUntilPageFullyLoaded();

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
        Assert.assertEquals(legend1.trim(), "Services and Versions are Compatible", "Legend1" +
                " value mismatch");
        logger.info("Legend1 name: [" + legend1 + "] displayed in the header");

        logger.info("Legend2: " + legend2.trim());
        Assert.assertFalse(legend2.isEmpty(), "Legend2: 'Services and Versions are not Compatible' not displayed");
        Assert.assertEquals(legend2.trim(), "Services and Versions are not Compatible", "Legend2" +
                " value mismatch");
        logger.info("Legend2 name: [" + legend2 + "] displayed in the header");

        logger.info("Legend3: " + legend3.trim());
        Assert.assertFalse(legend3.isEmpty(), "Legend3: 'Service is available in Source, but missing in Target'" +
                " not displayed");
        Assert.assertEquals(legend3.trim(), "Service is available in Source, but missing in Target",
                "Legend3 value mismatch");
        logger.info("Legend3 name: [" + legend3 + "] displayed in the header");

        logger.info("Legend4: " + legend4.trim());
        Assert.assertFalse(legend4.isEmpty(), "Legend4: 'Service is missing in Source, but available in Target'" +
                " not displayed");
        Assert.assertEquals(legend4.trim(), "Service is missing in Source, but available in Target",
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
        for (WebElement e : platformsList) {
            allPlatform.add(e.getText().trim());
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

    public String getMajorVersion(String name) {
        String[] arr = name.split(" ");
        String[] arrVersion = arr[1].split("\\.");
        return arrVersion[0];
    }


    public void verifyServicesAndVersionsAreCompatible() {
        List<String> hdpServicesList = getHDPServicesList();
        int totalHDPServicesCount = hdpServicesList.size();
        List<WebElement> rowsList = servicesAndVersionsCompatibilityPageObject.rowsList;
        Assert.assertFalse(rowsList.isEmpty(), "No Platform services data available");
        List<WebElement> colsList = servicesAndVersionsCompatibilityPageObject.colList;
        logger.info("Size of columnlist: " + colsList.size());
        Assert.assertFalse(colsList.isEmpty(), "No Platform services data available");

        for (int col = 0; col < totalHDPServicesCount - 1; col++) {
            for (int row = 0; row < rowsList.size(); row++) {
                String path = "//tbody/tr[" + (row + 1) + "]/td[" + (col + 2) + "]";
                WebElement e = driver.findElement(By.xpath(path));
                if (!e.getText().isEmpty()) {
                    String cloudClusterServiceName = e.getText().trim();
                    String majorVersionCloud = getMajorVersion(cloudClusterServiceName);

                    String testClusterServiceName = hdpServicesList.get(col);
                    String majorVersionHDP = getMajorVersion(testClusterServiceName);

                    if (majorVersionHDP.equals(majorVersionCloud)) {
                        //Now check for green //risk-0
                        String classAttributeName = e.getAttribute("class");
                        logger.info("Element class attribute name: " + classAttributeName);
                        Assert.assertTrue(classAttributeName.equals("risk-0"), "Platforms service in the box is not" +
                                " marked in Green ");

                    }
                }
            }
        }
    }

    /**
     * Method to validate report, download, delete, view report from actions tab
     */
    public void verifyReportsArchived(ReportsArchiveScheduledPageObject reportPageObj, String name, String reportAction) {
        List<WebElement> reportNameList = reportPageObj.reportNames;
        List<WebElement> reportCntList = reportPageObj.reportCnt;
        Assert.assertFalse(reportNameList.isEmpty(), "There are no reports listed.");

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
                        break;
                    case "deleteReport":
                        MouseActions.clickOnElement(driver, reportCntList.get(i));
                        waitExecuter.waitUntilPageFullyLoaded();
                        waitExecuter.waitUntilElementPresent(servicesAndVersionsCompatibilityPageObject.archiveReportSVCHeader);
                        MouseActions.clickOnElement(driver, reportPageObj.deleteReportIcon);
                        waitExecuter.waitUntilPageFullyLoaded();
                        Alert confirmationAlert = driver.switchTo().alert();
                        String alertText = confirmationAlert.getText();
                        logger.info("Alert text is " + alertText);
                        confirmationAlert.accept();
                        logger.info("Deleted report");
                        Assert.assertEquals(reportPageObj.successfulMsgBanner.getText(), "Removed successfully",
                                " Report not removed");
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
                        String [] arrDate = dateFromElement.split(" ");
                        String date = arrDate[0];
                        System.out.println(date);
                        reportPageObj.reportSearchBox.sendKeys(date);
                        List<WebElement> searchDateReportNameList = reportPageObj.reportNames;
                        Assert.assertFalse(searchDateReportNameList.isEmpty(), "There are no reports listed");
                        Assert.assertTrue(searchDateReportNameList.size() > 0, "Expected search " +
                                "result not populated data by date.");
                        logger.info("Search report by date: " + date);
                        break;
                    case "searchReportByStatus":
                        MouseActions.clickOnElement(driver, reportCntList.get(i));
                        waitExecuter.waitUntilPageFullyLoaded();
                        waitExecuter.waitUntilElementPresent(servicesAndVersionsCompatibilityPageObject.archiveReportSVCHeader);
                        String status = "fail";
                        reportPageObj.reportSearchBox.sendKeys(status);
                        List<WebElement> searchStatusReportNameList = reportPageObj.reportNames;
                        Assert.assertFalse(searchStatusReportNameList.isEmpty(), "There are no reports listed");
                        Assert.assertEquals(searchStatusReportNameList.size() > 0, "Expected search " +
                                "result not populated data by status .");
                        logger.info("Searched report for status as: " + status);
                        break;
                    case "searchReportByName":
                        MouseActions.clickOnElement(driver, reportCntList.get(i));
                        waitExecuter.waitUntilPageFullyLoaded();
                        waitExecuter.waitUntilElementPresent(servicesAndVersionsCompatibilityPageObject.archiveReportSVCHeader);
                        //Give any name which shows in the UI
                        String searchReportName = servicesAndVersionsCompatibilityPageObject.archiveReportName.getText().trim();
                        reportPageObj.reportSearchBox.sendKeys(searchReportName);
                        List<WebElement> searchNameReportNameList = reportPageObj.reportNames;
                        Assert.assertFalse(searchNameReportNameList.isEmpty(), "There are no reports listed");
                        Assert.assertEquals(searchNameReportNameList.size() > 0, "Expected search " +
                                "result not populated data by report name.");
                        logger.info("Searched report by name as :" + searchReportName);
                        break;
                }
                break;
            }
        }
    }


}