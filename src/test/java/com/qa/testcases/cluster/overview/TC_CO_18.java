package com.qa.testcases.cluster.overview;
import com.qa.base.BaseClass;
import com.qa.constants.PageConstants;
import com.qa.pagefactory.HomePageObject;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.scripts.ComponentHelper;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_CO_18 extends BaseClass
{

    @Test(dataProvider = "clusterid-data-provider")
    public void TC_CO_18_VerifynewAddnewautoactionoralert(String clusterId) {
        test = extent.startTest("TC_CO_18_VerifynewAddnewautoactionoralert"+clusterId, "Validate Verify new Add new autoaction or alert page on clicking.");
        test.assignCategory("4620 - Cluster Overview");
        // Click on first alert row on home page
        HomePageObject homePageObject = new HomePageObject(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        test.log(LogStatus.PASS, "verify Clusterid : " + clusterId);

        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterId(clusterId);
        waitExecuter.sleep(3000);

        // Select this month
        DatePicker datePicker = new DatePicker(driver);
        datePicker.clickOnDatePicker();
        datePicker.selectLast30Days();

        //Click on first alert displayed at homepage
        JavaScriptExecuter.clickOnElement(driver,homePageObject.firstAlertRow);

        boolean isUrlContainResource = ComponentHelper.isUrlContain(driver,"/clusters/resources");

        Assert.assertTrue(isUrlContainResource,"Url does not contain resources path");
        test.log(LogStatus.PASS, "Url contains the resources path");

    }
}

