package com.qa.testcases.data.Forecasting;

import com.qa.base.BaseClass;
import com.qa.pagefactory.TopPanelComponentPageObject;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.clusters.TopXPageObject;
import com.qa.pagefactory.data.ForecastingPageObject;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.TopX;
import com.qa.scripts.data.Forecasting;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.TimeoutException;
import org.testng.annotations.Test;

import java.util.logging.Logger;

public class TC_CF_01 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_CF_01.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void validateForecastingReportGenerated(String clusterId) {
        test = extent.startTest("TC_CF_01.validateForecastingReportGenerated", "Verify User is able " +
                "to trigger a new report or cancel the selection");
        test.assignCategory(" Data - Forecasting ");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        TopPanelComponentPageObject topPanelComponentPageObject = new TopPanelComponentPageObject(driver);
        waitExecuter.waitUntilElementPresent(topPanelComponentPageObject.data);
        waitExecuter.sleep(2000);
        MouseActions.clickOnElement(driver, topPanelComponentPageObject.data);

        TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);
        waitExecuter.waitUntilElementPresent(topPanelPageObject.dataForecastingTab);
        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.waitUntilElementClickable(topPanelPageObject.dataForecastingTab);
        waitExecuter.sleep(3000);
        MouseActions.clickOnElement(driver, topPanelPageObject.dataForecastingTab);

        ForecastingPageObject forecastingPageObject = new ForecastingPageObject(driver);

        Forecasting forecasting = new Forecasting(driver);
        forecasting.closeConfirmationMessageNotification();
        forecasting.clickOnRunButton();
        String forecastingNoOfDays = "2";
        forecasting.setForecastingDays(forecastingNoOfDays);
        forecasting.clickOnModalRunButton();
        waitExecuter.waitUntilElementPresent(forecastingPageObject.runNowButton);
        waitExecuter.waitUntilElementClickable(forecastingPageObject.runNowButton);

        try {
            waitExecuter.waitUntilTextToBeInWebElement(forecastingPageObject.confirmationMessageElement,
                    "Capacity Forecasting completed successfully.");
            test.log(LogStatus.PASS, "Verified Forecasting report is loaded properly.");
        } catch (TimeoutException te) {
            throw new AssertionError("Forecasting Report not completed successfully.");
        }
    }

}
