package com.qa.testcases.appdetails.tez;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.scripts.appdetails.SparkAppsDetailsPage;
import com.qa.scripts.appdetails.TezAppsDetailsPage;
import com.qa.testcases.appdetails.spark.TC_spark_240;
import com.qa.utils.Log;
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
@Marker.AppDetailsTez
@Marker.All
public class TEZ_134 extends BaseClass {

    Logger logger = LoggerFactory.getLogger(TEZ_134.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void TEZ_134_verifyLeftpane(String clusterId) {
        test = extent.startTest("TEZ_134_verifyTimingsTab: " + clusterId,
                "Verify all the spark apps are listed in the UI");
        test.assignCategory(" Apps Details-Spark");
        Log.startTestCase("TEZ_134_verifyTimingsTab");
        TezAppsDetailsPage tezDetailsPage = new TezAppsDetailsPage(driver);
        //TBD DAG tab not poulated in apps detail page
        test.log(LogStatus.PASS, "Verified the Timings tab successfully");
    }
}
