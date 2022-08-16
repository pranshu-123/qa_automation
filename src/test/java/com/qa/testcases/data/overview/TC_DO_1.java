package com.qa.testcases.data.overview;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.HomePage;
import com.qa.scripts.data.DataOverview;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import org.testng.Assert;
import org.testng.annotations.Test;
@Marker.DataOverview
public class TC_DO_1 extends BaseClass {
    private final LoggingUtils loggingUtils = new LoggingUtils(TC_DO_1.class);
    @Test()
    public void TC_DO_1_verifyDataTabIsAccessible(String clusterId) {
        test = extent.startTest("TC_DO_1.verifyDataTabIsAccessible", "Verify whether Data tab is accessible");
        test.assignCategory("Data/Overview");
        DataOverview dataOverview = new DataOverview(driver, test);
        dataOverview.clickOnDataTab();
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        waitExecuter.waitUntilPageFullyLoaded();
        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterId(clusterId);

        waitExecuter.sleep(3000);
        Assert.assertTrue(driver.getCurrentUrl().contains("/data/overview"), "Data Overview Page is not opened.");
        loggingUtils.pass("Data Overview is opened.", test);
    }
}

