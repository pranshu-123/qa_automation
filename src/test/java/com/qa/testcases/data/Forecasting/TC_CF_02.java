package com.qa.testcases.data.Forecasting;

import com.qa.base.BaseClass;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.data.ForecastingPageObject;
import com.qa.scripts.data.Forecasting;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.logging.Logger;

public class TC_CF_02 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_CF_02.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void validateCanceledForecastingReport(String clusterId) {
        test = extent.startTest("TC_CF_02.validateCanceledForecastingReport", "Verify User is able to" +
                "clicks on \"Cancel\" button and the Mini Window should close ");
        test.assignCategory(" Data - Forecasting ");
        LOGGER.info("Passed Parameter Is : " + clusterId);

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
        waitExecuter.waitUntilElementPresent(topPanelComponentPageObject.data);
        waitExecuter.sleep(2000);
        MouseActions.clickOnElement(driver, topPanelComponentPageObject.data);
        LOGGER.info("Clicked on Data Tab");

        TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);
        waitExecuter.waitUntilElementPresent(topPanelPageObject.dataForecastingTab);
        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.waitUntilElementClickable(topPanelPageObject.dataForecastingTab);
        waitExecuter.sleep(3000);
        MouseActions.clickOnElement(driver, topPanelPageObject.dataForecastingTab);
        LOGGER.info("Clicked on Forecasting Tab");

        ForecastingPageObject forecastingPageObject = new ForecastingPageObject(driver);

        Forecasting forecasting = new Forecasting(driver);
        forecasting.closeConfirmationMessageNotification();

        //Get the previous report data generated
        String previousReportData = forecasting.getReportData();
        LOGGER.info("Previous report generated data: "+previousReportData);
        forecasting.clickOnRunButton();
        LOGGER.info("Clicked on Run Button");

        String forecastingNoOfDays = "2";
        forecasting.setForecastingDays(forecastingNoOfDays);
        LOGGER.info("Set Forecasting days as: "+ forecastingNoOfDays);
        forecasting.clickOnModalRunButton();
        LOGGER.info("Clicked on Modal Run Button");
        forecasting.clickOnCancelButton();

        //Get the previous report data generated
        String reportDataAfterCancelled = forecasting.getReportData();
        LOGGER.info("Report generated data after cancelled: "+reportDataAfterCancelled);

        //Validate both the report data
        Assert.assertEquals(previousReportData, reportDataAfterCancelled);
        test.log(LogStatus.PASS, "Verified Forecasting report after user cancelled.");
        LOGGER.info("Verified Forecasting report after user cancelled.");
    }
}
