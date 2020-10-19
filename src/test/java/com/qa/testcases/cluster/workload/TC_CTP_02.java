package com.qa.testcases.cluster.workload;

import com.qa.base.BaseClass;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.pagefactory.clusters.WorkloadPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.HomePage;
import com.qa.scripts.clusters.Workload;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
/**
 * @author Sarbashree Ray
 */
public class TC_CTP_02 extends BaseClass {
    Logger logger = LoggerFactory.getLogger(TC_CTP_02.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void validateclusterlistinclusterfiltertab(String clusterId) {
        test = extent.startTest("TC_CTP_02.validateclusterlistinclusterfiltertab", "Verify Cluster workload report should be generated for the selected cluster");
        test.assignCategory("Cluster - Workload");
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        WorkloadPageObject  workloadPageObject = new  WorkloadPageObject(driver);
        TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);
        waitExecuter.waitUntilElementPresent(topPanelPageObject.workloadTab);
        waitExecuter.waitUntilPageFullyLoaded();
        waitExecuter.waitUntilElementClickable(topPanelPageObject.workloadTab);
        waitExecuter.sleep(3000);
        MouseActions.clickOnElement(driver, topPanelPageObject.workloadTab);

        Workload workload = new Workload(driver);

        test.log(LogStatus.PASS, "verify Clusterid : " + clusterId);

        HomePage homePage = new HomePage(driver);
        homePage.selectMultiClusterId(clusterId);

        DatePicker datePicker = new DatePicker(driver);
        datePicker.clickOnDatePicker();
        datePicker.selectLast7Days();

        test.log(LogStatus.PASS, "Verify Workload in selected time range :"
                +workloadPageObject.timerangeMessageElement.getText());

         //TBD
        //Cluster workload report
        datePicker.clickOnDatePicker();
        datePicker.selectLast14Days();

        test.log(LogStatus.PASS, "Verify Workload in selected time range :"
                +workloadPageObject.timerangeMessageElement.getText());


        datePicker.clickOnDatePicker();
        datePicker.selectLast30Days();

        test.log(LogStatus.PASS, "Verify Workload in selected time range :"
                +workloadPageObject.timerangeMessageElement.getText());

        //TBD
        //Cluster workload report


        datePicker.clickOnDatePicker();
        datePicker.selectLast60Days();

        test.log(LogStatus.PASS, "Verify Workload in selected time range :"
                +workloadPageObject.timerangeMessageElement.getText());

        //TBD
        //Cluster workload report

        datePicker.clickOnDatePicker();
        datePicker.selectCustomRange();

        test.log(LogStatus.PASS, "Verify Workload in selected time range :"
                +workloadPageObject.ViewByCal.getText());




        //TBD
        //Cluster workload report


    }
}