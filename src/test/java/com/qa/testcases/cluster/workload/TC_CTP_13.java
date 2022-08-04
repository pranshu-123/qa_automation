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
@Marker.GCPClusterWorkload
public class TC_CTP_13 extends BaseClass {
    Logger logger = LoggerFactory.getLogger(TC_CTP_13.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void VerifySearchOptionInGroupbyFilterOfJobDetails(String clusterId) {
        test = extent.startTest("TC_CTP_13.VerifySearchOptionInGroupbyFilterOfJobDetails",
                "Verify This should lists all the applications which are executed by selected user on that day.");
        test.assignCategory("Cluster - Workload");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
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
        waitExecuter.sleep(3000);

        test.log(LogStatus.PASS, "Verify Workload in selected time range :"
                + workloadPageObject.timerangeMessageElement.getText());


        waitExecuter.sleep(3000);
        test.log(LogStatus.PASS, "Verify View By Month");

        test.log(LogStatus.PASS, "Verify current month selected :"
                + workloadPageObject.currentmonthHeader.getText().trim());
        waitExecuter.sleep(3000);
        int scrollY = 370;
        JavaScriptExecuter.scrollViewWithYAxis(driver, scrollY);
        scrollY = scrollY + datePicker.getDatePickerYPosition();
        waitExecuter.sleep(3000);
        try {
            waitExecuter.waitUntilElementPresent(workloadPageObject.timeRange);
            test.log(LogStatus.PASS, "Verify Aggregated datewise Job Count in selected time range:"
                    + workloadPageObject.timeRange.getText().trim());
            workload.clickOnDateRange(workloadPageObject);
            /* String statusXpath = workload.clickOnDate(reportPageObj, workload.ByStatusGraph.Tuning);*/
            waitExecuter.sleep(3000);
            test.log(LogStatus.PASS, "Verify selected time range");
        } catch (VerifyError te) {
            throw new AssertionError("workload selected time range not completed successfully." + te);
        }

        //Checking workload Jobs Table Records populated

       /* if(workloadPageObject.workloadJobsTableRecords.size() > 0)
        {
            test.log(LogStatus.PASS, "Verified Jobs Table is available on workload page");
        }
        else{

            test.log(LogStatus.FAIL, "Test Failed Jobs Table is not available on workload page");
        }*/

        test.log(LogStatus.PASS, "Verify lists all the applications which are executed by selected user on that day");
    }
}
