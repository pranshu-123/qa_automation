package com.qa.testcases.alerts.autoactions;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.alerts.AutoActions;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;
import java.util.logging.Logger;

@Marker.Alerts
@Marker.All
public class TC_AA137 extends BaseClass {
    private static final java.util.logging.Logger logger = Logger.getLogger(TC_AA137.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void validateAllTriggeredAA(String clusterId) {
        test = extent.startTest("TC_AA137.validateAllTriggeredAA",
                "Validate all triggered AAs in application badge.");
        test.assignCategory(" Alerts ");

        AutoActions aa = new AutoActions(driver);
        aa.setupForAutoActionsPage();
        test.log(LogStatus.INFO, "Verified Alerts Tab is clicked.");
        logger.info("Verified Alerts Tab is clicked.");

        aa.clickOnRunHeader();
        logger.info("Clicked on Run column header to sort.");
        aa.getTriggeredAAs();
        test.log(LogStatus.PASS, "Validate all triggered AAs in application badge.");

    }
}
