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
import org.testng.annotations.Test;

/**
 * @author Sarbashree Ray
 */
@Marker.All
@Marker.ClusterWorkload
public class TC_CTP_08 extends BaseClass {
    Logger logger = LoggerFactory.getLogger(TC_CTP_08.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void VerifyDateRangeDisplayedInWorkloadReport(String clusterId) {
        test = extent.startTest("TC_CTP_08.VerifyDateRangeDisplayedInWorkloadReport",
                "Verify Date range displayed in workload report should be same as date range selected in date picker");
        test.assignCategory("Cluster - Workload");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        GraphUtils graphUtils = new GraphUtils();
        WorkloadPageObject workloadPageObject = new WorkloadPageObject(driver);
        TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);
        MouseActions.clickOnElement(driver, topPanelPageObject.workloadTab);
        Workload workload = new Workload(driver);
        workload.selectByYarn();

        test.log(LogStatus.PASS, "verify Clusterid : " + clusterId);

        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterId(clusterId);

        DatePicker datePicker = new DatePicker(driver);
        datePicker.clickOnDatePicker();
        datePicker.selectLast30Days();
        waitExecuter.sleep(3000);

        test.log(LogStatus.PASS, "Verify Workload in selected time range :"
                + workloadPageObject.timerangeMessageElement.stream()
                .filter(WebElement::isDisplayed).findFirst().get().getText());

        workload.clickOnMonth();
        waitExecuter.sleep(3000);
        test.log(LogStatus.PASS, "Verify View By Month");

        test.log(LogStatus.PASS, "Verify current month selected :"
                + workloadPageObject.currentmonthHeader.getText());
        waitExecuter.sleep(3000);
        int scrollY = 370;
        JavaScriptExecuter.scrollViewWithYAxis(driver, scrollY);
        scrollY = scrollY + datePicker.getDatePickerYPosition();
        waitExecuter.sleep(3000);


    }
}
