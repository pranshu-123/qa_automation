package com.qa.testcases.cluster.overview;
import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.constants.PageConstants;
import com.qa.pagefactory.HomePageObject;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
/**
 * @author Sarbashree Ray
 */
@Marker.ClusterOverview
@Marker.GCPClusterOverview
@Marker.All
public class TC_CO_18 extends BaseClass
{

    @Test(dataProvider = "clusterid-data-provider",description="P0-Verify  add new auto action or alert all the policies are listed with violation count and new policies")
    public void TC_CO_18_VerifynewAddnewautoactionoralert(String clusterId) {
        test = extent.startTest("TC_CO_18_VerifynewAddnewautoactionoralert"+clusterId, "Validate Verify new Add new autoaction or alert page on clicking.");
        test.assignCategory(" Cluster Overview");
        // Click on first alert row on home page
        HomePageObject homePageObject = new HomePageObject(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        test.log(LogStatus.PASS, "verify Clusterid : " + clusterId);

        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterId(clusterId);
        waitExecuter.sleep(3000);

        // Select this 90 Days
        DatePicker datePicker = new DatePicker(driver);
        datePicker.clickOnDatePicker();
        datePicker.selectLast30Days();
        waitExecuter.sleep(1000);


        //Click on first alert displayed at homepage
        JavaScriptExecuter.clickOnElement(driver,homePageObject.autoalert);
        test.log(LogStatus.PASS, "Successfully verified autoaction.");
        waitExecuter.sleep(3000);
        JavaScriptExecuter.clickOnElement(driver,homePageObject.newautoalert);
        waitExecuter.sleep(3000);
        test.log(LogStatus.PASS, "Successfully verified Add new autoaction.");


        try {
            String parentWindow = driver.getWindowHandle();
            waitExecuter.sleep(3000);
            test.log(LogStatus.PASS, "Validate window is opened by clicking at New Auto Action Policy.");
            System.out.println(driver.getCurrentUrl());
            for (String window : driver.getWindowHandles()) {
                if (!window.equals(parentWindow)) {
                    driver.switchTo().window(window);
                    Assert.assertTrue(driver.getCurrentUrl().contains("/alerts/autoaction/add"), "application is not redirected on clicking on New Auto Action Policy.");
                    test.log(LogStatus.PASS, "Validate application is redirected by clicking at New Auto Action Policy.");
                    driver.close();
                }
            }
            driver.switchTo().window(parentWindow);
        } catch (TimeoutException te) {
            Assert.assertTrue(false,"Validate does not contain Auto Actions");
        }


    }

    }


