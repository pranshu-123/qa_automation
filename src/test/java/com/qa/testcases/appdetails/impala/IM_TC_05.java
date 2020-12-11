package com.qa.testcases.appdetails.impala;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.AppDetailsApplicationType;
import com.qa.enums.appdetails.impala.ImpalaFragmentDetails;
import com.qa.enums.UserAction;
import com.qa.enums.appdetails.impala.OperatorDetails;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.appdetails.AppDetailsPage;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Ankur Jaiswal
 */

@Marker.AppDetailsImpala
@Marker.All
public class IM_TC_05 extends BaseClass {

    private LoggingUtils loggingUtils = new LoggingUtils(IM_TC_05.class);

    /**
     * Verify Operator details for impala app details."
     * @param clusterId - ClusterId to run test cases on
     */
    @Test(dataProvider = "clusterid-data-provider")
    public void verifyOperatorDetails(String clusterId) {
        test = extent.startTest("IM_TC_05.verifyOperatorDetails", "Verify Operator details.");
        test.assignCategory("App Details - Impala");
        WaitExecuter executer = new WaitExecuter(driver);
        loggingUtils.info("Started test case: IM_TC_05.verifyOperatorDetails", test);
        TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);
        UserActions actions = new UserActions(driver);
        actions.performActionWithPolling(topPanelPageObject.jobsTab, UserAction.CLICK);
        loggingUtils.info("Click on Job Tabs", test);
        AllApps allApps = new AllApps(driver);
        allApps.selectCluster(clusterId);
        DatePicker datePicker = new DatePicker(driver);
        datePicker.clickOnDatePicker();
        datePicker.selectLast90Days();
        AppDetailsPage appDetailsPage = new AppDetailsPage(driver);
        try {
            appDetailsPage.selectOnlyApplication(AppDetailsApplicationType.IMPALA);
            actions.performActionWithPolling(appDetailsPage.getAppsDetailsAsPage().get(1), UserAction.CLICK);
            appDetailsPage.clickOnImpalaOperatorsTab();
            for (WebElement fragment : appDetailsPage.getImpalaFragmentRows()) {
                Assert.assertNotNull(fragment.findElements(By.tagName("td")).get(OperatorDetails.TYPE.getIndex()));
                Assert.assertNotNull(fragment.findElements(By.tagName("td")).get(OperatorDetails.OP_ID.getIndex()));
                Assert.assertNotNull(fragment.findElements(By.tagName("td")).get(OperatorDetails.FRAG_ID.getIndex()));
                Assert.assertNotNull(fragment.findElements(By.tagName("td")).get(ImpalaFragmentDetails.NUM_INSTANCES.getIndex()));
                Assert.assertNotNull(fragment.findElements(By.tagName("td")).get(ImpalaFragmentDetails.AVG_TIME.getIndex()));
                Assert.assertNotNull(fragment.findElements(By.tagName("td")).get(ImpalaFragmentDetails.MAX_TIME.getIndex()));
                Assert.assertNotNull(fragment.findElements(By.tagName("td")).get(ImpalaFragmentDetails.AVG_PEAK_MEMORY.getIndex()));
                Assert.assertNotNull(fragment.findElements(By.tagName("td")).get(ImpalaFragmentDetails.MAX_PEAK_MEMORY.getIndex()));
                Assert.assertNotNull(fragment.findElements(By.tagName("td")).get(ImpalaFragmentDetails.ROWS_PRODUCED.getIndex()));
            }
            loggingUtils.pass("Id is displayed for operators: impala", test);
            loggingUtils.pass("Operator Id is displayed for operators: impala", test);
            loggingUtils.pass("Fragment Id is displayed for operators: impala", test);
            loggingUtils.pass("Number Of Instances is displayed for operators: impala", test);
            loggingUtils.pass("Avg Time is displayed for operators: impala", test);
            loggingUtils.pass("Max Time is displayed for operators: impala", test);
            loggingUtils.pass("AVG_PEAK_MEMORY is displayed for operators: impala", test);
            loggingUtils.pass("MAX_PEAK_MEMORY is displayed for operators: impala", test);
            loggingUtils.pass("ROWS_PRODUCED is displayed for operators: impala", test);

            if (appDetailsPage.getImpalaFragmentRows().size() > 1) {
                actions.performActionWithPolling(appDetailsPage.getImpalaInstanceView(), UserAction.CLICK);
                Assert.assertTrue(appDetailsPage.getImpalaInstanceViewFragmentRows().size()>0, "Instance views are not " +
                    "displayed");
                loggingUtils.pass("Verify instance views are displayed for operator: impala", test);
                int expectedInstance =
                    Integer.parseInt(appDetailsPage.getImpalaFragmentRows().get(0).findElement(By.xpath(
                        "//td[2]")).getText());
                Assert.assertEquals(expectedInstance, appDetailsPage.getImpalaInstanceViewFragmentRows().size(),
                    "Instance count does not match");
                loggingUtils.pass("Verify whether Instance count is matching.", test);
            }
            appDetailsPage.closeModalbutton();
        } catch (Throwable ex) {
            appDetailsPage.closeModalbutton();
            throw ex;
        }
    }
}
