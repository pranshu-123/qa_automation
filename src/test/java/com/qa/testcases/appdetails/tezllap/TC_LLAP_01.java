package com.qa.testcases.appdetails.tezllap;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.appsDetailsPage.TezAppsDetailsPageObject;
import com.qa.pagefactory.appsDetailsPage.TezLlapAppsDetailsPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.appdetails.TezAppsDetailsPage;
import com.qa.scripts.appdetails.TezLlapAppsDetailsPage;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.testcases.appdetails.tez.TEZ_003;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.Log;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;


@Marker.AppDetailsTezLlap
@Marker.All
public class TC_LLAP_01 extends BaseClass {

    Logger logger = LoggerFactory.getLogger(TC_LLAP_01.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void TC_LLAP_01_verifyApplicationPage(String clusterId) {
        test = extent.startTest("TC_LLAP_01_verifyApplicationPage: " + clusterId,
                "Verify Unravel UI should display Tez LLAP application on the application page.");
        test.assignCategory("Apps Details-TezLlap");
        Log.startTestCase("TC_LLAP_01_verifyApplicationPage");

        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        logger.info("Initialize all class objects");
        SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        TezLlapAppsDetailsPageObject TezLlapPage = new TezLlapAppsDetailsPageObject(driver);
        TezLlapAppsDetailsPage TezLlapApps = new TezLlapAppsDetailsPage(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        DatePicker datePicker = new DatePicker(driver);
        AllApps allApps = new AllApps(driver);

        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        TezLlapApps.navigateToJobsTabFromHeader(topPanelComponentPageObject, allApps, datePicker,
                applicationsPageObject, clusterId);
        test.log(LogStatus.INFO, "Verify that the left pane has tez check box and the apps number");

        int Appname=TezLlapApps.clickOnlyLink("Tez");
        waitExecuter.waitUntilPageFullyLoaded();
        test.log(LogStatus.PASS, "Selected "+ Appname + " as option in Group By filter, yarn chargeback page");
        int totalCount = Integer.parseInt(applicationsPageObject.getTotalAppCount.getText().
                replaceAll("[^\\dA-Za-z ]", "").trim());
        logger.info("AppCount is " + Appname + " total count is " + totalCount);

        Assert.assertEquals(Appname, totalCount, "The tez app count of tezApp is not equal to " +
                "the total count of heading.");
        test.log(LogStatus.PASS, "The left pane has tez check box and the app counts match to that " +
                "displayed in the header");

        waitExecuter.waitUntilPageFullyLoaded();
        int scrollY = 370;
        JavaScriptExecuter.scrollViewWithYAxis(driver,scrollY);
        scrollY = scrollY + datePicker.getDatePickerYPosition();
        TezLlapApps.queueName();
        String optionName = "llap";
        TezLlapApps.selectOptionsInGroupBy(optionName);

        test.log(LogStatus.INFO, "Assert if in all application Tez LLAP application are present");
        logger.info("Assert if in all application TEZ application are present");
        Assert.assertTrue(TezLlapApps.getAllApplicationQueue().contains("llap"),
                "The list of applications does not contains 'llap' apps");
        test.log(LogStatus.PASS, "Verified that in all application Tez LLAP application are present.");


    }


}