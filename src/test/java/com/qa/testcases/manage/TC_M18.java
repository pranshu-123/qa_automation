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
public class TC_M18 extends BaseClass {
    Logger LOGGER = LoggerFactory.getLogger(TC_M18.class);
    @Test()
    public void validateCopyFunctionality() {
        test = extent.startTest("TC_M18.validateCopyFunctionality", "Validate copy token functionality");
        test.assignCategory("Manage/Api Tokens");

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        String token = RandomGenerator.generateRandomName();

        Manage manage = new Manage(driver,test);
        manage.navigateManageTab("API Tokens");
        waitExecuter.sleep(1000);
        manage.selectCreateNewApiToken();
        manage.createNewToken(token);
        String successMsg = manage.retrieveSuccessMessage();
        Assert.assertEquals(successMsg,"API Token created successfully","Token not generated");
        String copiedToken = manage.validateCopyTokenFunctionality();
        Assert.assertEquals(copiedToken,"API Token copied successfully","Token not copied");
        manage.deleteToken(token);
        test.log(LogStatus.PASS, "Generated Token copied successfully.");
        LOGGER.info("Generated Token copied successfully.");
    }
}

