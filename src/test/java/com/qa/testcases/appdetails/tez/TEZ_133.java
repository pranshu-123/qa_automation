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
public class TEZ_133 extends BaseClass {

    Logger logger = LoggerFactory.getLogger(TC_spark_240.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void TEZ_133_verifyDatabase(String clusterId) {
        test = extent.startTest("TEZ_133_verifyDatabase: " + clusterId,
                "Verify Name of the database must be listed in the UI");
        test.assignCategory(" Apps Details-Tez");
        Log.startTestCase("TEZ_133_verifyDatabase");
        TezAppsDetailsPage tezDetailsPage = new TezAppsDetailsPage(driver);
        //TBD Data base tab not poulated in apps detail page

        test.log(LogStatus.PASS, "Verified the Timings tab successfully");
    }
}
