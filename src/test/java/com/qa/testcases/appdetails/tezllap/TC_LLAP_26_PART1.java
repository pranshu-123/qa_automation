package com.qa.testcases.appdetails.tezllap;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.appsDetailsPage.TezLlapAppsDetailsPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.appdetails.TezLlapAppsDetailsPage;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
@Marker.AppDetailsTezLlap
@Marker.All
public class TC_LLAP_26_PART1 extends BaseClass
{

    Logger logger = LoggerFactory.getLogger(TC_LLAP_26_PART1.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void TC_LLAP_26_PART1_tezNonLLAP(String clusterId) {
        test = extent.startTest("TC_LLAP_26_PART1_tezNonLLAP: " + clusterId,
                "Verify UI should display both non-LLAP Tez apps and LLAP Tez applications ");
        test.assignCategory(" Apps Details-TezLlap");
        Log.startTestCase("TC_LLAP_26_PART1_tezNonLLAP");

        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        logger.info("Initialize all class objects");
        SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        TezLlapAppsDetailsPageObject tezLlapPage = new TezLlapAppsDetailsPageObject(driver);
        TezLlapAppsDetailsPage tezLlapApps = new TezLlapAppsDetailsPage(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        DatePicker datePicker = new DatePicker(driver);
        AllApps allApps = new AllApps(driver);

        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        tezLlapApps.navigateToJobsTabFromHeader(topPanelComponentPageObject, allApps, datePicker,
                applicationsPageObject, clusterId);
        test.log(LogStatus.INFO, "Verify that the left pane has tez check box and the apps number");

        int appCount = tezLlapApps.clickOnlyLink("Tez");
        int totalCount = Integer.parseInt(applicationsPageObject.getTotalAppCount.getText().
                replaceAll("[^\\dA-Za-z ]", "").trim());
        logger.info("AppCount is " + appCount + " total count is " + totalCount);
        test.log(LogStatus.PASS, "AppCount is " + appCount + " total count is " + totalCount);
        Assert.assertEquals(appCount, totalCount, "The tez app count of tezApp is not equal to " +
                "the total count of heading.");
        test.log(LogStatus.PASS, "The left pane has tez check box and the app counts match to that " +
                "displayed in the header");

        // Get 1st queuename from table for Tez apps
        String filterByQueue = applicationsPageObject.getQueueNameTable.getText().trim();
        String upTo10CharQueueName = StringUtils.left(filterByQueue, 10);
        logger.info("Queue name should be filtered by- " + upTo10CharQueueName);
        waitExecuter.waitUntilPageFullyLoaded();
        if (!upTo10CharQueueName.trim().isEmpty() || !upTo10CharQueueName.trim().equals("-")) {
            tezLlapPage.queueSearchBox.click();
            waitExecuter.waitUntilPageFullyLoaded();
            tezLlapPage.queueSearchBox.sendKeys(upTo10CharQueueName);
            waitExecuter.waitUntilPageFullyLoaded();
            List<WebElement> queueList = tezLlapPage.getNamesFromDropDown;
            String queuenameSelected = null;
            if (!upTo10CharQueueName.isEmpty() || !upTo10CharQueueName.equals("_"))
                for (int i = 0; i < queueList.size(); i++) {
                    if (queueList.get(i).getText().equals(upTo10CharQueueName)) {
                        queuenameSelected = queueList.get(i).getText();
                        logger.info("Selected username from dropdown " + queuenameSelected);
                        queueList.get(i).click();
                        waitExecuter.waitUntilPageFullyLoaded();
                        break;
                    }
                }
                /*
                 * Validate the QueueName --
                 */
                if (appCount > 0) {
                    String expectedQueuename = tezLlapApps.verifyQueueName(tezLlapPage);
                    Assert.assertEquals(expectedQueuename, queuenameSelected, "Hive tezllap should not be llap" +
                            " displayed.");

                } else {
                    waitExecuter.waitUntilElementPresent(applicationsPageObject.whenNoApplicationPresent);
                }
            }
        }
    }
