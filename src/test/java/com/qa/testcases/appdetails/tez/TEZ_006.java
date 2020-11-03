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
        if (tezCount > 0) {
            // Hive on to the first row
            test.log(LogStatus.INFO, "Hive on to the first row");
            LOGGER.info("Hive on to the first row");
            WebElement name = applicationsPageObject.getAppNameFromTable;
            actions.moveToElement(name).perform();
            waitExecuter.sleep(1000);
            actions.moveToElement(applicationsPageObject.copyAppName).perform();
            waitExecuter.sleep(1000);
            applicationsPageObject.copyAppName.click();
            Assert.assertTrue(applicationsPageObject.successBanner.isDisplayed(), "App id is not copied");
            test.log(LogStatus.PASS, "On clicking on + app id got copied");
            waitExecuter.sleep(1000);
            applicationsPageObject.globalSearchBox.click();
            waitExecuter.sleep(1000);
            try {
                String data = (String) Toolkit.getDefaultToolkit().getSystemClipboard()
                        .getData(DataFlavor.stringFlavor);
                String[] getCopiedText = data.split(":");
                LOGGER.info("Search by ID - " + getCopiedText[2]);
                applicationsPageObject.globalSearchBox.sendKeys(getCopiedText[2]);
                applicationsPageObject.globalSearchBox.sendKeys(Keys.ENTER);
                waitExecuter.sleep(1000);
            } catch (HeadlessException | UnsupportedFlavorException | IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            waitExecuter.sleep(2000);
            /*
             * Validate that on copying the ID on globalsearch, only that row appears in
             * table
             */
            test.log(LogStatus.INFO, "Validate that on copying the ID on globalsearch, only that row appears in table");
            LOGGER.info("Validate that on copying the ID on globalsearch, only that row appears in table");
            String value = applicationsPageObject.globalSearchBox.getAttribute("value");
            Assert.assertTrue(applicationsPageObject.getStatusColumnFromTable.size() == 1,
                    "On searching by ID the table contains more than 1 row. " + value);
            test.log(LogStatus.PASS, "On searching by ID the table contains 1 row.");
            waitExecuter.sleep(1000);
        } else {
            Assert.assertTrue(applicationsPageObject.whenNoApplicationPresent.isDisplayed(),
                    "The clusterId does not have any application under it and also does not display 'No Data Available' for it"
                            + clusterId);
            test.log(LogStatus.SKIP, "The clusterId does not have any application under it.");
            throw new SkipException("The clusterId does not have any application under it");
        }
    }
}
