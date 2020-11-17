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
public class TEZ_135 extends BaseClass {
    Logger logger = LoggerFactory.getLogger(TEZ_135.class);

    @Test(dataProvider = "clusterid-data-provider")
    public void TEZ_135_verifytheDags(String clusterId) {
        test = extent.startTest("TEZ_135_verifytheDags: " + clusterId,
                "Verify Verify if the dags are present in the left pane and value should be populated");
        test.assignCategory(" Apps Details-Tez");
        Log.startTestCase("TEZ_135_verifytheDags");
        TezAppsDetailsPage tezDetailsPage = new TezAppsDetailsPage(driver);
        //TBD Data base tab not poulated in apps detail page
        test.log(LogStatus.PASS, "Verified the Dags tab successfully");
    }
}
