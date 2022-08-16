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
public class TC_M19 extends BaseClass {
    Logger LOGGER = LoggerFactory.getLogger(TC_M19.class);


    public void createEmptyToken() {
        test = extent.startTest("TC_M19.createEmptyToken", "Create empty API token");
        test.assignCategory("Manage/Api Tokens");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        String token = RandomGenerator.generateRandomName();

        Manage manage = new Manage(driver,test);
        manage.navigateManageTab("API Tokens");
        waitExecuter.sleep(1000);
        manage.selectCreateNewApiToken();
        String errorMsg = manage.createEmptyToken();
        Assert.assertEquals(errorMsg,"Client Id is required.","Error message not generated");
        test.log(LogStatus.PASS, "No new token generated without client id");
        LOGGER.info("No new token generated without client id.");
    }
}

