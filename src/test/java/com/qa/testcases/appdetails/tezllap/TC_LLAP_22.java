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
import org.openqa.selenium.WebElement;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.logging.Logger;

@Marker.AppDetailsTezLlap
@Marker.All
public class TC_LLAP_22 extends BaseClass {

    private static final java.util.logging.Logger LOGGER = Logger.getLogger(TC_LLAP_22.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void TC_LLAP_22_verifyWorkflow(String clusterId) {
        test = extent.startTest("TC_LLAP_22_verifyWorkflow: " + clusterId,
                "Validate Tez LLAP app part of a workflow");
        test.assignCategory(" Apps Details-TezLlap");
        Log.startTestCase("TC_LLAP_22_verifyWorkflow");

        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        TezLlapAppsDetailsPageObject tezLlapPage = new TezLlapAppsDetailsPageObject(driver);
        TezLlapAppsDetailsPage tezLlapApps = new TezLlapAppsDetailsPage(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        DatePicker datePicker = new DatePicker(driver);
        AllApps allApps = new AllApps(driver);

        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        tezLlapApps.navigateToJobsTabFromHeader(topPanelComponentPageObject, allApps, datePicker,
                applicationsPageObject, clusterId);
        test.log(LogStatus.INFO, "Verify that the left pane has tez check box and the apps number");

        int appCount=tezLlapApps.clickOnlyLink("Hive");
       /* applicationsPageObject.expandStatus.click();
        int statusCount = tezLlapApps.clickOnlyLink("Success");
        test.log(LogStatus.PASS, "Selected success Count is  " + statusCount + " as Status, In Applications page");*/
        waitExecuter.waitUntilPageFullyLoaded();
        applicationsPageObject.expandQueue.click();
        waitExecuter.waitUntilPageFullyLoaded();

        // Get llap username from table for tez apps
        String upTo10CharQueueName = "llap";
        LOGGER.info("Queue name should be filtered by- " + upTo10CharQueueName);
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
                        LOGGER.info("Selected username from dropdown " + queuenameSelected);
                        test.log(LogStatus.PASS, "Queue name should be filtered by- " + queuenameSelected);
                        queueList.get(i).click();
                        waitExecuter.waitUntilPageFullyLoaded();
                        break;
                    }

                }

            /*
             * Validate the AppID are --
             */
            if (appCount > 0) {
                String headerAppId = tezLlapApps.verifyappId(tezLlapPage, applicationsPageObject);
                test.log(LogStatus.PASS, "Tez Application Id is displayed in the Header: " + headerAppId);


            }else {
                Assert.assertTrue(applicationsPageObject.whenNoApplicationPresent.isDisplayed(),
                        "The clusterId does not have any application under it and also does not display 'No Data Available' for it");
                test.log(LogStatus.SKIP, "No Tez llap Application present");
                LOGGER.severe("No Tez llap Application present in the " + clusterId + " cluster for the time span " +
                        "of 90 days");
            }
        }
    }
}
