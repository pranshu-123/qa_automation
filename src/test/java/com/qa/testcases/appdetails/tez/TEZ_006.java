package com.qa.testcases.appdetails.tez;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.TopPanelComponentPageObject;
import com.qa.pagefactory.appsDetailsPage.TezAppsDetailsPageObject;
import com.qa.pagefactory.jobs.ApplicationsPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.appdetails.TezAppsDetailsPage;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.testcases.appdetails.spark.TC_spark_219;
import com.qa.utils.Log;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.logging.Logger;

@Marker.AppDetailsTez
@Marker.All
public class TEZ_006 extends BaseClass {

    private static final java.util.logging.Logger LOGGER = Logger.getLogger(TEZ_006.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void TEZ_006_verifyApplicationNameID(String clusterId) {
        test = extent.startTest("TEZ_006_verifyApplicationNameAndID: " + clusterId,
                "Verify Application details in Unravel UI, application page.");
        test.assignCategory(" Apps Details-Tez");
        Log.startTestCase("TEZ_006_verifyApplicationNameAndID");

        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        TopPanelComponentPageObject topPanelComponentPageObject = new TopPanelComponentPageObject(driver);
        ApplicationsPageObject applicationsPageObject = new ApplicationsPageObject(driver);
        TezAppsDetailsPageObject tezApps = new TezAppsDetailsPageObject(driver);
        TezAppsDetailsPage tezDetailsPage = new TezAppsDetailsPage(driver);
        DatePicker datePicker = new DatePicker(driver);
        Actions actions = new Actions(driver);
        AllApps allApps = new AllApps(driver);

        test.log(LogStatus.INFO, "Navigate to jobs tab from header");
        tezDetailsPage.navigateToJobsTabFromHeader(topPanelComponentPageObject, allApps, datePicker,
                applicationsPageObject, clusterId);
        test.log(LogStatus.INFO, "Verify that the left pane has tez check box and the apps number");
        int appCount = tezDetailsPage.clickOnlyLink("Tez");
        int tezCount = Integer.parseInt(applicationsPageObject.getTotalAppCount.getText().
                replaceAll("[^\\dA-Za-z ]", "").trim());
        test.log(LogStatus.PASS, "Verify that the left pane apps count: "+tezCount);
        /*
         * Validate the username types are --
         */
        if (appCount > 0) {
            String Appname = tezDetailsPage.verifyAppname(tezApps);
            test.log(LogStatus.PASS, "Tez App name is displayed in the Table: " + Appname);

            String AppId = tezDetailsPage.verifyappId(tezApps);
            test.log(LogStatus.PASS, "Tez App Id is displayed in the Table: " + AppId);


        } else {
            test.log(LogStatus.SKIP, "No Tez Application present");
            LOGGER.severe("No Tez Application present in the " + clusterId + " cluster for the time span " +
                    "of 90 days");
            //Close apps details page
            MouseActions.clickOnElement(driver, tezApps.closeAppsPageTab);
        }
    }
}
