package com.qa.testcases.data.Forecasting;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.UserAction;
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

import java.util.logging.Logger;

@Marker.DataForecasting
@Marker.All
public class TC_CF_01 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_CF_01.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void validateForecastingReportGenerated(String clusterId) {
        test = extent.startTest("TC_CF_01.validateForecastingReportGenerated: "+ clusterId,
                "Verify User is able to trigger a new report or cancel the selection");
        test.assignCategory(" Data - Forecasting ");
        LOGGER.info("Passed Parameter Is : " + clusterId);

        WaitExecuter waitExecuter = new WaitExecuter(driver);
        SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
        waitExecuter.waitUntilElementPresent(topPanelComponentPageObject.data);
        waitExecuter.sleep(2000);
        MouseActions.clickOnElement(driver, topPanelComponentPageObject.data);
        LOGGER.info("Clicked on Data Tab");
        test.log(LogStatus.INFO, "Clicked on Data Tab");

        TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);
        waitExecuter.waitUntilElementPresent(topPanelPageObject.dataForecastingTab);
        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.waitUntilElementClickable(topPanelPageObject.dataForecastingTab);
        waitExecuter.sleep(3000);
        waitExecuter.waitUntilElementPresent(topPanelPageObject.dataForecastingTab);
        MouseActions.clickOnElement(driver, topPanelPageObject.dataForecastingTab);
        LOGGER.info("Clicked on Forecasting Tab");
        test.log(LogStatus.INFO, "Clicked on Forecasting Tab");

        ForecastingPageObject forecastingPageObject = new ForecastingPageObject(driver);

        Forecasting forecasting = new Forecasting(driver);
        UserActions userActions = new UserActions(driver);
        forecasting.closeConfirmationMessageNotification();
        forecasting.clickOnRunButton();
        LOGGER.info("Clicked on Run Button");
        test.log(LogStatus.INFO, "Clicked on Run Button");

        String forecastingNoOfDays = "2";
        forecasting.setForecastingDays(forecastingNoOfDays);
        LOGGER.info("Set Forecasting days as: "+ forecastingNoOfDays);
        test.log(LogStatus.INFO, "Set Forecasting days as: "+ forecastingNoOfDays);
        forecasting.clickOnModalRunButton();
        LOGGER.info("Clicked on Modal Run Button");
        test.log(LogStatus.INFO, "Clicked on Modal Run Button");

        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.waitUntilTextNotToBeInWebElement(forecastingPageObject.modalAfterRunButton, "Please Wait");

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
