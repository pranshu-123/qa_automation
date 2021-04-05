package com.qa.testcases.cluster.tuning;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.constants.PageConstants;
import com.qa.enums.UserAction;
import com.qa.pagefactory.CommonPageObject;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.clusters.TuningPageObject;
import com.qa.pagefactory.reports.ReportsArchiveScheduledPageObject;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.Tuning;
import com.qa.scripts.reports.ReportsArchiveSchedulePage;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Marker.Tuning
@Marker.All
public class TC_CTR_05 extends BaseClass {
    private static final Logger LOGGER = Logger.getLogger(TC_CTR_05.class.getName());

    @Test(dataProvider = "clusterid-data-provider")
    public void validateTuningReportForDifferentClsuters(String clusterId) {
        test = extent.startTest("TC_CTR_05.validateTuningForDifferentDatePicker: " + clusterId,
                "Verify tuning report for different clusters.");
        test.assignCategory(" Cluster - Tuning ");
        LOGGER.info("Passed Parameter Is : " + clusterId);

        WaitExecuter waitExecuter = new WaitExecuter(driver);
//        TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);
//        waitExecuter.waitUntilElementPresent(topPanelPageObject.tuningTab);
        waitExecuter.waitUntilPageFullyLoaded();
//        waitExecuter.waitUntilElementClickable(topPanelPageObject.tuningTab);
//        waitExecuter.sleep(3000);
//        MouseActions.clickOnElement(driver, topPanelPageObject.tuningTab);
//        LOGGER.info("Clicked on Tuning Tab");
//        test.log(LogStatus.INFO, "Clicked on Tuning Tab");

        //Get all the clusters from UI and store in list and close the new report window
        Tuning tuning = new Tuning(driver);
//        tuning.closeConfirmationMessageNotification();
//        tuning.clickOnRunButton();
//        LOGGER.info("Clicked on Run button");
//        test.log(LogStatus.INFO,"Clicked on Run button");

        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        SubTopPanelModulePageObject topPanelComponentPageObject = new SubTopPanelModulePageObject(driver);
        MouseActions.clickOnElement(driver, topPanelComponentPageObject.reports);
        waitExecuter.sleep(3000);
        ReportsArchiveSchedulePage reportsPage = new ReportsArchiveSchedulePage(driver);
        ReportsArchiveScheduledPageObject reportPageObj = new ReportsArchiveScheduledPageObject(driver);
        LOGGER.info("Click on + button");
        String statusXpath = reportsPage.clickOnReportName(reportPageObj, PageConstants.ReportsArchiveNames.Tuning);
        LOGGER.info("Clicked on Tuning Tab + icon");
        test.log(LogStatus.INFO, "Clicked on Tuning Tab + icon");

        UserActions userActions = new UserActions(driver);
        CommonPageObject commonPageObject = new CommonPageObject(driver);
        userActions.performActionWithPolling(commonPageObject.clusterDropdown, UserAction.CLICK);
        List<String> allClustersUI = new ArrayList<>();
        if(commonPageObject.clustersList.size() > 0) {
            allClustersUI = tuning.getClusterOptions(commonPageObject);
        }
        tuning.closeNewReport();

        //Now traverse all cluster one by one and generate report
        HomePage homePage = new HomePage(driver);
        TuningPageObject tuningPageObject = new TuningPageObject(driver);
        for (String clusterUI : allClustersUI) {
            tuning.clickOnRunButton();
            LOGGER.info("Clicked on Run button");
            test.log(LogStatus.INFO,"Clicked on Run button");
            homePage.selectMultiClusterId(clusterUI);
            tuning.clickOnModalRunButton();
            LOGGER.info("Clicked on Run Button");
            test.log(LogStatus.INFO, "Clicked on Run Button");
            waitExecuter.waitUntilElementPresent(tuningPageObject.runButton);
            waitExecuter.waitUntilElementClickable(tuningPageObject.runButton);
//            try {
//                waitExecuter.waitUntilTextToBeInWebElement(tuningPageObject.confirmationMessageElement,
//                        "Cluster Tuning completed successfully.");
//                test.log(LogStatus.PASS, "Verified Tuning report is completed successfully for cluster : " + clusterUI);
//            } catch (TimeoutException te) {
//                throw new AssertionError("Tuning Report not completed successfully for cluster :"+ clusterUI);
//            }
            WebElement statusElement = driver.findElement(By.xpath(statusXpath));
            try{
                waitExecuter.waitUntilElementPresent(statusElement);
                test.log(LogStatus.PASS, "Verified Tuning report is completed with status " + statusElement.getText());
//                waitExecuter.waitUntilTextToBeInWebElement(statusElement,
//                        "SUCCESS");
            }catch (TimeoutException te) {
                throw new AssertionError("Tuning Report not completed successfully for cluster :"+ clusterUI);
            }
        }

    }


}
