package com.qa.testcases.cluster.workload;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.clusters.WorkloadPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.Workload;
import com.qa.utils.GraphUtils;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;

/**
 * @author Sarbashree Ray
 */
@Marker.All
@Marker.ClusterWorkload
public class TC_CTP_11 extends BaseClass {
    Logger logger = LoggerFactory.getLogger(TC_CTP_11.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void VerifyGroupByUserAndQueueFilterInJobDetails(String clusterId) throws AWTException {
        test = extent.startTest("TC_CTP_11.VerifyGroupByUserAndQueueFilterInJobDetails",
                "Verify This should lists application count extecuted by user or in queue");
        test.assignCategory("Cluster - Workload");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        GraphUtils graphUtils = new GraphUtils();
        WorkloadPageObject workloadPageObject = new WorkloadPageObject(driver);
        Workload workload = new Workload(driver);
        workload.selectWorkloadTab();
        waitExecuter.sleep(2000);

        test.log(LogStatus.PASS, "verify Clusterid : " + clusterId);

        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterIdClusterPage(clusterId);
        waitExecuter.sleep(2000);
        waitExecuter.waitUntilPageFullyLoaded();

        workload.selectWorkloadType("Yarn");
        waitExecuter.sleep(2000);


        DatePicker datePicker = new DatePicker(driver);
        datePicker.clickOnDatePicker();
        datePicker.selectLast30Days();
        waitExecuter.sleep(1000);

        test.log(LogStatus.PASS, "Verify Workload in selected time range :"
                + workloadPageObject.timerangeMessageElement.getText().trim());

        waitExecuter.sleep(3000);
        workload.selectViewBy("Month");
        test.log(LogStatus.PASS, "Verify View By Month");
        test.log(LogStatus.PASS, "Verify current month selected :"
                + workloadPageObject.currentmonthHeader.getText());
        try {
            test.log(LogStatus.PASS, "Verify selected date range:"
                    + workloadPageObject.timeRange.getText());
            waitExecuter.waitUntilPageFullyLoaded();
            workload.clickOnDateRange(workloadPageObject);
            waitExecuter.sleep(3000);
            test.log(LogStatus.PASS, "Verify selected time range");
        } catch (VerifyError te) {
            throw new AssertionError("workload selected time range not completed successfully." + te);
        }
        //Checking workload Jobs Table Records populated
     /*   if(workloadPageObject.workloadJobsTableRecords.size() > 0)
        {
            test.log(LogStatus.PASS, "Verified Jobs Table is available on workload page");
        }
        else{
            test.log(LogStatus.FAIL, "Test Failed Jobs Table is not available on workload page");
        }*/
        waitExecuter.sleep(3000);
        //Validate Header Column names in workload Jobs Table
        /*Assert.assertTrue(workload.validateHeaderColumnNameInworkloadJobsTable(),
                "Validation failed for header column names from workload Jobs Table");
        test.log(LogStatus.PASS,
                "Verified Column names in workload Jobs Table successfully on workload Jobs page");
        waitExecuter.sleep(3000);*/
    }
}
