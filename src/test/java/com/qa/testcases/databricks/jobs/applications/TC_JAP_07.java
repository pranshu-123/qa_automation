package com.qa.testcases.databricks.jobs.applications;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.databricks.jobs.DbxApplicationsPageObject;
import com.qa.pagefactory.databricks.jobs.DbxSummaryPageObject;
import com.qa.scripts.databricks.Runs.SummaryDetailsPage;
import com.qa.scripts.databricks.jobs.DbAllApps;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.NoSuchElementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
@Marker.DbxAppDetails
@Marker.All
public class TC_JAP_07 extends BaseClass {
    Logger logger = LoggerFactory.getLogger(TC_JAP_07.class);

    @Test()
    public void verifyAppDetailsPageStage() throws InterruptedException {
        test = extent.startTest("TC_JAP_07.verifyAppDetailsPageStage",
                "Verify 1. All the spark apps must be listed on the UI\n" +
                        " 2. left pane must have spark check box and the apps number\n" +
                        " 3. clicking on the UI must go to apps detail page");
        test.assignCategory("Apps Details-Spark");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        logger.info("Initialize all class objects");
        DbxSummaryPageObject summaryPageObject = new DbxSummaryPageObject(driver);
        DbxApplicationsPageObject applicationsPageObject=new DbxApplicationsPageObject(driver);
        SummaryDetailsPage summaryPage = new SummaryDetailsPage(driver);
        DbAllApps dballApps = new DbAllApps(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        summaryPage.navigateToJobsTabFromHeader(dballApps, test);
        int appCount = summaryPage.clickOnlyLink("Success");
        int totalCount = Integer
                .parseInt(summaryPageObject.getTotalAppCount.getText().replaceAll("[^\\dA-Za-z ]", "").trim());
        Assert.assertEquals(appCount, totalCount,
                "The Spark app count of SparkApp is not equal to " + "the total count of heading.");
        waitExecuter.sleep(2000);
        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.sleep(2000);
        summaryPageObject.sortByDurationApp.click();
        waitExecuter.waitUntilPageFullyLoaded();
        summaryPageObject.sortUp.click();
        try {
            String headerAppId = summaryPage.verifyGoToSpark(summaryPageObject);
            test.log(LogStatus.PASS, "Spark Application Id is displayed in the Header: " + headerAppId);
            summaryPage.verifyAppsComponent(summaryPageObject, false, false, true);
            test.log(LogStatus.PASS, "The Failed apps have all kpis listed along with components present");
        } catch (NoSuchElementException ex) {
            logger.info("No app present by this name");
            logger.info("Error- " + ex);
        }
    }
}

