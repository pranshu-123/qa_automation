package com.qa.testcases.manage;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.manage.Manage;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
@Marker.Manage
public class TC_M24 extends BaseClass {
    Logger logger = LoggerFactory.getLogger(TC_M24.class);

    @Test()
    public void validateAuditSortingOption() {
        test = extent.startTest("TC_M24.validateAuditSortingOption", "Verify Audit Sorting option");
        test.assignCategory(" Manage/Audit page");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        Manage manage = new Manage(driver, test);
        manage.navigateManageTab("Audit");

        //Click on icon-sort
        manage.clickOnIconAuditSort();
        waitExecuter.sleep(3000);
        manage.validateAuditSorting("User");
        test.log(LogStatus.PASS, "Verified Audits sorting option");
        logger.info("Verified Audits sorting option");
    }
}

