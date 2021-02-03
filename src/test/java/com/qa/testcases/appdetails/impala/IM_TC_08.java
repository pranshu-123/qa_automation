package com.qa.testcases.appdetails.impala;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.AppDetailsApplicationType;
import com.qa.scripts.DatePicker;
import com.qa.scripts.appdetails.AppDetailsPage;
import com.qa.scripts.jobs.applications.AllApps;
import com.qa.utils.LoggingUtils;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

/*@author- Ojasvi
 *
 */
@Marker.AppDetailsImpala
@Marker.All
public class IM_TC_08 extends BaseClass {

    private final LoggingUtils loggingUtils = new LoggingUtils(IM_TC_08.class);

    /**
     * Verify Impala application Tags table in app details page
     */

    @Test(dataProvider = "clusterid-data-provider")
    public void verifyTagsTable(String clusterId) {
        test = extent.startTest("IM_TC_08.verifyTagsTable", "Verify Impala application Tags table ");
        test.assignCategory("App Details - Impala");
        loggingUtils.info("Started test case: IM_TC_08.verifyTagsTable", test);
        AllApps allApps = new AllApps(driver);
        AppDetailsPage appDetailsPage = new AppDetailsPage(driver);
        DatePicker datePicker = new DatePicker(driver);

        try {
            appDetailsPage.navigateToJobsTab();
            allApps.selectCluster(clusterId);
            datePicker.clickOnDatePicker();
            datePicker.selectLast90Days();
            appDetailsPage.selectSuccessfulApplication();
            int appCount = appDetailsPage.selectOnlyApplication(AppDetailsApplicationType.IMPALA);
            if (appCount > 0) {
                appDetailsPage.clickOnFirstImpalaJob();
                loggingUtils.info("Clicked on the first impala application", test);
                Map<String, String> tagsMap = appDetailsPage.getImpalaTags();
                String deptValue = tagsMap.get("dept");
                String projectValue = tagsMap.get("project");
                loggingUtils.info("Department tag value- " + deptValue, test);
                loggingUtils.info("Project tag value- " + projectValue, test);
                appDetailsPage.close();
                Assert.assertNotNull(deptValue, "Dept tag value not found.");
                Assert.assertNotNull(projectValue, "Project tag value not found.");
                test.log(LogStatus.PASS, "Operator count matched with expected count.");
            } else {
                loggingUtils.info("There are no successful apps for impala for selected cluster", test);
                test.log(LogStatus.SKIP, "There are no successful apps for impala for selected cluster- " + clusterId);
            }
            appDetailsPage.reset();
        } catch (NoSuchElementException ex) {
            appDetailsPage.close();
            appDetailsPage.reset();
        }

    }
}
