package com.qa.testcases.manage;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.manage.Manage;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
@Marker.Manage
public class TC_M25 extends BaseClass {
    Logger logger = LoggerFactory.getLogger(TC_M25.class);

    @Test(priority = 0)
    public void validateComponentSearch() {
        test = extent.startTest("TC_M25_01.validateComponentSearch",
                "Validate that the user is able to search based on files column for Audit file reports");
        test.assignCategory(" Manage/Audit page");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        Manage manage = new Manage(driver, test);
        manage.navigateManageTab("Audit");

        waitExecuter.waitUntilPageFullyLoaded();
        test.log(LogStatus.INFO, "Clicked on Audit Tab.");
        logger.info("Clicked on Audit option.");
        waitExecuter.waitUntilPageFullyLoaded();
        Assert.assertTrue(manage.validateAuditHeader(), "Audit Header is not present.");
        waitExecuter.waitUntilPageFullyLoaded();
        test.log(LogStatus.INFO, "Verified Audit Tab.");

        //Click on Audit Search and validate details on the page
        manage.verifySearchOption( 3, 3);
        test.log(LogStatus.PASS, "Successfully Verified Audit Search functionality");
    }

    @Test(priority = 1)
    public void validateAccessTypeSearch() {
        test = extent.startTest("TC_M25_02.validateAccessTypeSearch",
                "Validate that the user is able to search based on files column for Audit file reports");
        test.assignCategory(" Manage/Audit page");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        Manage manage = new Manage(driver, test);
        manage.navigateManageTab("Audit");
        waitExecuter.waitUntilPageFullyLoaded();

        //Click on Audit Search and validate details on the page
        manage.verifySearchOption(4, 4);
        test.log(LogStatus.PASS, "Successfully Verified Access Type Search functionality");
    }

    @Test(priority = 2)
    public void validateStatusSearch() {
        test = extent.startTest("TC_M25_03.validateStatusSearch",
                "Validate that the user is able to search based on files column for Audit file reports");
        test.assignCategory(" Manage/Audit page");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        Manage manage = new Manage(driver, test);
        manage.navigateManageTab("Audit");

        //Click on Audit Search and validate details on the page
        manage.verifySearchOption( 8, 8);
        test.log(LogStatus.PASS, "Successfully Verified Status Search functionality");
    }

    @Test(priority = 3)
    public void validateUserSearch() {
        test = extent.startTest("TC_M25_04.validateUserSearch",
                "Validate that the user is able to search based on files column for Audit file reports");
        test.assignCategory(" Manage/Audit page");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        Manage manage = new Manage(driver, test);
        manage.navigateManageTab("Audit");


        //Click on Audit Search and validate details on the page
        manage.verifySearchOption( 2, 2);
        test.log(LogStatus.PASS, "Successfully Verified User Search functionality");
    }
}

