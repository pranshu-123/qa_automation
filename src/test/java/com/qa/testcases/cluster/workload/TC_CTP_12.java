package com.qa.testcases.cluster.workload;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.clusters.WorkloadPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.Workload;
import com.qa.utils.*;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Sarbashree Ray
 */
@Marker.All
@Marker.ClusterWorkload
public class TC_CTP_12 extends BaseClass {
    Logger logger = LoggerFactory.getLogger(TC_CTP_12.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void VerifySearchOptionInGroupbyFilterOfJobDetails(String clusterId) {
        test = extent.startTest("TC_CTP_12.VerifySearchOptionInGroupbyFilterOfJobDetails",
                "Verify This should lists application count extecuted by particular user");
        test.assignCategory("Cluster - Workload");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        HomePage homePage = new HomePage(driver);

        //Initialize Workload Page Object
        TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);
        WorkloadPageObject workloadPageObject = new WorkloadPageObject(driver);
        waitExecuter.waitUntilElementPresent(topPanelPageObject.workloadTab);
        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.waitUntilElementClickable(topPanelPageObject.workloadTab);
        waitExecuter.sleep(3000);
        MouseActions.clickOnElement(driver, topPanelPageObject.workloadTab);
        Workload workload = new Workload(driver);
        GraphUtils graphUtils = new GraphUtils();

        // Set multi cluster
        test.log(LogStatus.INFO, "Select cluster : " + clusterId);
        Log.info("Select cluster : " + clusterId);
        homePage.selectMultiClusterId(clusterId);
        waitExecuter.sleep(1000);
        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.sleep(1000);

        DatePicker datePicker = new DatePicker(driver);
        datePicker.clickOnDatePicker();
        datePicker.selectLast7Days();
        waitExecuter.sleep(1000);

        test.log(LogStatus.PASS, "Verify Workload in selected time range :"
                + workloadPageObject.timerangeMessageElement.stream()
                .filter(WebElement::isDisplayed).findFirst().get().getText());

        workload.clickOnMonth();
        waitExecuter.sleep(1000);
        test.log(LogStatus.PASS, "Verify View By Month :-"
                + workloadPageObject.viewByMonth.stream().anyMatch(WebElement::isDisplayed));

        test.log(LogStatus.PASS, "Verify current month selected :"
                + workloadPageObject.currentmonthHeader.getText());
        waitExecuter.sleep(1000);
        int scrollY = 370;
        JavaScriptExecuter.scrollViewWithYAxis(driver, scrollY);
        scrollY = scrollY + datePicker.getDatePickerYPosition();
        waitExecuter.sleep(3000);
        workload.clickOnDate();
        waitExecuter.sleep(3000);
        test.log(LogStatus.PASS, "Verify current Date selected");
        waitExecuter.sleep(1000);

        //Checking workload Jobs Table Records populated
        workload.getworkloadJobsTableRecord();
        waitExecuter.sleep(1000);
        test.log(LogStatus.PASS, "Verified workload Jobs Table is available on workload chargeback page");

        //Validate Header Column names in workload Jobs Table
        Assert.assertTrue(workload.validateHeaderColumnNameInworkloadJobsTable(),
                "Validation failed for header column names from workload Jobs Table");
        test.log(LogStatus.PASS,
                "Verified Column names in workload Jobs Table successfully on Yarn chargeback page");
        waitExecuter.sleep(1000);
    }
}
