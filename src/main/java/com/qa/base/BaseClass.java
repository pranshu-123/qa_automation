package com.qa.base;

import com.qa.constants.ConfigConstants;
import com.qa.constants.DirectoryConstants;
import com.qa.constants.FileConstants;
import com.qa.io.ConfigReader;
import com.qa.pagefactory.clusters.DataProviderClass;
import com.qa.scripts.CommonComponent;
import com.qa.scripts.Login;
import com.qa.scripts.UnravelBuildInfo;
import com.qa.utils.FileUtils;
import com.qa.utils.Log;
import com.qa.utils.UnravelConfigUtils;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.logging.Logger;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

/**
 * @author Ankur Jaiswal
 * This is base of every test classes, all setup and tear down methods
 * are here. Every test class must implement this class
 */
public class BaseClass {
    public static WebDriver driver;
    public static ExtentReports extent;
    public static ExtentTest test;
    private static final Logger LOGGER = Logger.getLogger(BaseClass.class.getName());

    /**
     * This will be executed before suite starts. Start the browser. Initiate report
     */
    @BeforeSuite
    public void setup() {
        LOGGER.info("Update config based on user input");
        //UnravelConfigUtils.updateConfig();
        System.setProperty(ConfigConstants.SystemConfig.IS_MULTI_CLUSTER, "true");
        System.setProperty(ConfigConstants.SystemConfig.HEADLESS, "false");
        LOGGER.info("Starting browser");
        DriverManager driverManager = new DriverManager();
        Properties prop = ConfigReader.readBaseConfig();
        String browser = prop.getProperty(ConfigConstants.UnravelConfig.BROWSER);
        driver = driverManager.getDriver(browser);
        FileUtils.createDirectory(DirectoryConstants.getExtentResultDir());
        LOGGER.info("Moving old report to archive directory.");
        FileUtils.moveFileToArchive(FileConstants.getExtentReportFile(), true);
        FileUtils.createDirectory(DirectoryConstants.getScreenshotDir());
        LOGGER.info("Initiated html report.");
        extent = new ExtentReports(FileConstants.getExtentReportFile(), true);
        extent.loadConfig(new File(DirectoryConstants.getConfigDir() + "extent_config.xml"));
        LOGGER.info("Set build info to html report.");
        extent.addSystemInfo(ConfigConstants.ReportConfig.SELENIUM_VERSION,
            prop.getProperty(ConfigConstants.ReportConfig.SELENIUM_VERSION));
        UnravelBuildInfo.setBuildInfo(driver, extent);
    }

    /**
     * This will execute before every test class execution.
     * Login to the application
     */
    @BeforeClass
    public void beforeClass() {
        LOGGER.info("Login to the application.");
        Login login = new Login(driver);
        login.loginToApp();
    }

    /**
     * Executes before every method.
     *
     * @param method - Test case method
     */
    @BeforeMethod(alwaysRun = true)
    public void setupBeforeMethod(Method method) {
        Log.startTestCase(method.getName());
    }

    /**
     * Clean up method. Add test case to html report
     * Add failure along with exeption in report.
     *
     * @param result - Result of current execution of test cases
     * @param method - Method name of current test case.
     */
    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result, Method method) {
        try {
            /* Mark TC as FAIL if its an assertion error, else FATAL */
            if (result.getStatus() == ITestResult.FAILURE) {
                if (result.getThrowable() instanceof AssertionError) {
                    test.log(LogStatus.FAIL, "<b style='color:yellow'>" + result.getThrowable().getMessage());
                    LOGGER.info(method.getName() + " is failed");
                } else {
                    test.log(LogStatus.FATAL, result.getThrowable().getMessage());
                    LOGGER.info(method.getName() + " is failed due to code issue");
                }
            }
            Log.endTestCase(method.getName());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // Code to add test result in report
        LOGGER.info("Adding test case to report");
        extent.endTest(test);
        extent.flush();
    }

    /**
     * This will execute after every test class execution.
     * Logout from the application
     */
    @AfterClass()
    public void afterClass() {
        LOGGER.info("Logout from the application.");
        //Close if any pop modal is open
        CommonComponent.closeModalIfExists(driver);
        Login login = new Login(driver);
        login.logout();
        FileUtils.deleteDownloadsFolderFiles();
    }

    /**
     * This will be executed after suite completed.
     * Quit the browser
     */
    @AfterSuite
    public void tearDown() {
        LOGGER.info("Suite completed. Closing the browser.");
        driver.quit();
    }

    /**
     * Data provider method which will pass cluster Ids to different classes
     *
     * @param method - Method name of current test case
     * @return - clusterIds
     */
    @DataProvider(name = "clusterid-data-provider")
    public Object[][] getClusterIds(Method method) {
        if (System.getProperty(ConfigConstants.SystemConfig.IS_MULTI_CLUSTER).trim()
            .toLowerCase().equals("true")) {
            LOGGER.info("Getting multiple cluster Ids");
            return DataProviderClass.getClusterIdsForClusterType(method);
        } else {
            LOGGER.info("Getting single cluster Id");
            return new Object[][]{DataProviderClass.getClusterIdsForClusterType(method)[0]};
        }
    }
}
