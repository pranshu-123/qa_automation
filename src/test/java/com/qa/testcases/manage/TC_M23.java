package com.qa.testcases.manage;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.manage.ManagePageObject;
import com.qa.scripts.manage.Manage;
import com.qa.utils.MouseActions;
import com.qa.utils.RandomGenerator;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.TimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
@Marker.Manage
public class TC_M23 extends BaseClass {
    Logger logger = LoggerFactory.getLogger(TC_M23.class);

   @Test()
    public void validateAuditFilter() {
        test = extent.startTest("TC_M23.validateAuditFilter", "Verify Audit Sorting option");
        test.assignCategory(" Manage/Audit page");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        Manage manage = new Manage(driver,test);
        manage.navigateManageTab("Audit");

        waitExecuter.waitUntilPageFullyLoaded();
        test.log(LogStatus.INFO, "Clicked on Audit Tab.");
        logger.info("Clicked on Audit option.");
        waitExecuter.waitUntilPageFullyLoaded();
        Assert.assertTrue(manage.validateAuditHeader(), "Audit Header is not present.");
        waitExecuter.waitUntilPageFullyLoaded();
        test.log(LogStatus.INFO, "Verified Audit Tab.");
    }
}

