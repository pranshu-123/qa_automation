package com.qa.testcases.cluster.elk;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.pagefactory.DatePickerPageObject;
import com.qa.pagefactory.clusters.ELKPageObject;
import com.qa.scripts.DatePicker;
import com.qa.scripts.clusters.elk.ELKPage;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.Log;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

import java.util.List;
import java.util.logging.Logger;
@Marker.All
@Marker.ClusterELK
public class TC_ELK_20 extends BaseClass {

    private static final java.util.logging.Logger LOGGER = Logger.getLogger(TC_ELK_20.class.getName());

    @Test(dataProvider = "clusterid-data-provider",description="P1-Verify the Kibana metrics graph should be loaded and match with the Kibana UI graph..")
    public void TC_ELK_20_verifyKibanaMetricsGraphs(String clusterId) {
        test = extent.startTest("TC_ELK_20_verifyKibanaMetricsGraphs: " + clusterId,
                "Verify the Kibana metrics graph should be loaded and match with the Kibana UI graph.");
        test.assignCategory(" ELK ");
        Log.startTestCase("verifyKibanaMetricsGraphs");

        // Initialize all classes objects
        test.log(LogStatus.INFO, "Initialize all class objects");
        LOGGER.info("Initialize all class objects");
        ELKPageObject elkPageObj = new ELKPageObject(driver);
        ELKPage elkPage = new ELKPage(driver);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        DatePicker datePicker = new DatePicker(driver);

        // Navigate to pipeline tab from Logstash tab
        MouseActions.clickOnElement(driver, elkPageObj.kibanaTab);
        // waitExecuter.waitUntilElementClickable(elkPageObj.clusterDropDown);
        waitExecuter.waitUntilPageFullyLoaded();
        datePicker.clickOnDatePicker();
        waitExecuter.waitUntilPageFullyLoaded();
        datePicker.selectLast30Days();
        waitExecuter.waitUntilPageFullyLoaded();
        elkPage.verifyKibanaMetricGraphs(elkPageObj);

        test.log(LogStatus.PASS, "Verified Kibana metrics graph successfully ");
    }
}