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
public class TC_LLAP_26_PART2 extends BaseClass
{

    Logger logger = LoggerFactory.getLogger(TC_LLAP_26_PART2.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void TC_LLAP_26_PART2_tezLLAP(String clusterId) {
        test = extent.startTest("TC_LLAP_26_PART2_tezLLAP: " + clusterId,
                "Verify UI should display  both non-LLAP Tez apps and LLAP Tez applications ");
        test.assignCategory(" Apps Details-TezLlap");
        Log.startTestCase("TC_LLAP_26_PART2_tezLLAP");

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

      /*  applicationsPageObject.expandStatus.click();
        int statusCount = tezLlapApps.clickOnlyLink("Success");
        test.log(LogStatus.PASS, "Selected success Count is  " + statusCount + " as Status, In Applications page");*/
        waitExecuter.waitUntilPageFullyLoaded();
        applicationsPageObject.expandQueue.click();
        waitExecuter.waitUntilPageFullyLoaded();

            // Get llap queuename from table for tez apps
            String upTo10CharQueueName = "llap";
            logger.info("Queue name should be filtered by- " + upTo10CharQueueName);
            waitExecuter.waitUntilPageFullyLoaded();
            if (!upTo10CharQueueName.trim().isEmpty() || !upTo10CharQueueName.trim().equals("-")) {
                tezLlapPage.queueSearchBox.click();
                tezLlapPage.queueSearchBox.sendKeys(upTo10CharQueueName);
                waitExecuter.waitUntilPageFullyLoaded();
                List<WebElement> QueueList = tezLlapPage.getNamesFromDropDown;
                String queuenameSelected = null;
                if (!upTo10CharQueueName.isEmpty() || !upTo10CharQueueName.equals("_"))
                    for (int i = 0; i < QueueList.size(); i++) {

                        if (QueueList.get(i).getText().equals(upTo10CharQueueName)) {
                            queuenameSelected = QueueList.get(i).getText();
                            logger.info("Selected username from dropdown " + queuenameSelected);
                            QueueList.get(i).click();
                            waitExecuter.waitUntilPageFullyLoaded();
                            break;
                        }
                    }
                /*
                 * Validate the QueueName --
                 */
                if (appCount > 0) {
                    String expectedQueuename = tezLlapApps.verifyQueueName(tezLlapPage);
                    test.log(LogStatus.PASS, "Parent App is displayed in the Hive-Tez LLAP Table: " + expectedQueuename);


                } else {
                    waitExecuter.waitUntilElementPresent(applicationsPageObject.whenNoApplicationPresent);
                }
            }
        }
    }
