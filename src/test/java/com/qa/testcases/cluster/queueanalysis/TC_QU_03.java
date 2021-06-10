package com.qa.testcases.cluster.queueanalysis;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.clusters.QueueAnalysisPageObject;
import com.qa.scripts.clusters.QueueAnalysis;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.logging.Logger;

/*
 * @author- Ojasvi Pandey
 */
@Marker.QueueAnalysis
@Marker.All
public class TC_QU_03 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_QU_03.class.getName());

    @Test
    public void validate() {
        test = extent.startTest("TC_QU_03.validateMultiClusterInRunReports",
                "Validate user is able to generate schedule report for multiple clusters for Run reports.");
        test.assignCategory("Jobs - Queue Analysis");
        test.log(LogStatus.INFO, "Login to the application");
        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        QueueAnalysisPageObject qaPageObject = new QueueAnalysisPageObject(driver);
        QueueAnalysis queueAnalysis = new QueueAnalysis(driver);
        // Navigate to Queue Analysis tab from header
        test.log(LogStatus.INFO, "Navigate to Queue Analysis tab from header");
        test.log(LogStatus.INFO, "Clicked on Queue Analysis tab");
        test.log(LogStatus.INFO, "Validate Queue Analysis tab loaded successfully");
        queueAnalysis.navigateToQueueAnalysis();
        waitExecuter.waitUntilElementClickable(qaPageObject.addIcon);
        qaPageObject.addIcon.click();
        waitExecuter.waitUntilElementClickable(qaPageObject.modalRunButton);
        waitExecuter.sleep(1000);
        test.log(LogStatus.INFO, "Set custom date for 1 year duration");
        queueAnalysis.assignCustomDate("01/01/2018", "01/01/2019");

        //Click on Run button of modal window
        test.log(LogStatus.INFO, "Click on Run button of modal window");
        LOGGER.info("Click on Run button of modal window");
        qaPageObject.modalRunButton.click();

        Assert.assertTrue(qaPageObject.invalidInputMessage.get(0).getText().trim().contains("Please Make Sure Date Range Less than 30 Days"),
                "Banner message display an in-correct message as "
                        + qaPageObject.invalidInputMessage.get(0).getText());
        test.log(LogStatus.PASS, "Verified Queue Analysis report is loaded properly.");
        waitExecuter.waitUntilElementClickable(qaPageObject.addIcon);


    }
}