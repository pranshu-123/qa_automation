package com.qa.testcases.data.Forecasting;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.data.ForecastingPageObject;
import com.qa.scripts.data.Forecasting;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.TimeoutException;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Marker.DataForecasting
@Marker.All
public class TC_CF_92 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_CF_92.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void validateForecastingReportGeneratedForOneDays(String clusterId) {
        test = extent.startTest("TC_CF_92.validateForecastingReportGeneratedForZeroDays: " + clusterId,
                "Verify User is able to generate forecasting report for one days.");
        test.assignCategory(" Data - Forecasting ");
        LOGGER.info("Passed Parameter Is : " + clusterId);

        //Initialize all require objects
        Forecasting forecasting = new Forecasting(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        ForecastingPageObject forecastingPageObject = new ForecastingPageObject(driver);
        forecasting.generateForecastingReportHeaderTab();
        test.log(LogStatus.INFO, "Verified the Data Forecasting header tab");

        //Get the previous report data generated
        String previousReportData = forecasting.getReportData();
        LOGGER.info("Previous report generated data: " + previousReportData);
        forecasting.clickOnRunButton();
        LOGGER.info("Clicked on Run Button");
        test.log(LogStatus.INFO, "Clicked on Run Button");

        String forecastingNoOfDays = "1";
        forecasting.setForecastingDays(forecastingNoOfDays);
        LOGGER.info("Set Forecasting days as: "+ forecastingNoOfDays);
        test.log(LogStatus.INFO, "Set Forecasting days as: "+ forecastingNoOfDays);
        forecasting.clickOnModalRunButton();
        waitExecuter.waitUntilPageFullyLoaded();
        LOGGER.info("Clicked on Modal Run Button");
        test.log(LogStatus.INFO, "Clicked on Modal Run Button");

        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.waitUntilTextNotToBeInWebElement(forecastingPageObject.modalAfterRunButton, "Please Wait");
        waitExecuter.waitUntilPageFullyLoaded();
        try {
            waitExecuter.waitUntilTextToBeInWebElement(forecastingPageObject.confirmationMessageElement,
                    "Capacity Forecasting completed successfully.");
            test.log(LogStatus.PASS, "Verified Forecasting report is loaded properly.");
            LOGGER.info("Verified Forecasting report is loaded properly");
        } catch (TimeoutException te) {
            throw new AssertionError("Forecasting Report not completed successfully.");
        }
    }
}