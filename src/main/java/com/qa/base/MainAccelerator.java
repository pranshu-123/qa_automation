package com.qa.base;

import com.qa.connections.db.InsertRecordInMySql;
import com.qa.constants.ConfigConstants;
import com.qa.constants.DirectoryConstants;
import com.qa.constants.FileConstants;
import com.qa.constants.MarkerConstants;
import com.qa.constants.SystemVariables;
import com.qa.io.ConfigReader;
import com.qa.utils.*;
import com.qa.workflows.NFMHomepageWorkflow;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.io.File;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Properties;
import java.util.logging.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

public class MainAccelerator {

	private static final Logger LOGGER = Logger.getLogger(MainAccelerator.class.getName());
	public static WebDriver driver;
	public static ExtentReports extent;
	public static ExtentTest test;
	private static Properties prop;
	public static DateTimeFormatter dtf;
	public static LocalDateTime now;
	public static  Calendar cal;
	public static  Timestamp timestamp;

	/**
	 * This will be executed before suite starts. Start the browser. Initiate report
	 */
	@BeforeSuite
	public void setup() {
		prop = ConfigReader.readBaseConfig();
		dtf = DateTimeFormatter.ofPattern("MM-dd-YYYY hh:mm:ss"); 
		now = LocalDateTime.now();
		cal = Calendar.getInstance();  
		timestamp = new Timestamp(cal.getTimeInMillis());
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
	}

	/**
	 * This will execute before every test class execution.
	 * Login to the application
	 */
	@BeforeClass
	public void beforeClass() {
		LOGGER.info("Update config based on user input");
		System.setProperty(ConfigConstants.SystemConfig.HEADLESS, "true");
		LOGGER.info("Starting browser");
		DriverManager driverManager = new DriverManager();
		String browser = prop.getProperty(ConfigConstants.IrisConfig.BROWSER);
		driver = driverManager.getDriver(browser);

	}

	/**
	 * Executes before every method.
	 *
	 * @param method - Test case method
	 */
	@BeforeMethod(alwaysRun = true)
	public void setupBeforeMethod(Method method) {
		Log.startTestCase(method.getDeclaringClass().getName() + " - " + method.getName());
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
				String screenshotImg = ScreenshotHelper.takeScreenshotOfPage(driver);
				test.log(LogStatus.FAIL, test.addScreenCapture(screenshotImg));
			}
			Log.endTestCase(method.getDeclaringClass().getName() + " - " + method.getName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			// Code to add test result in report
			LOGGER.info("Adding test case to report");
			extent.endTest(test);
			extent.flush();
			NFMHomepageWorkflow hfMHomepageWorkflow = new NFMHomepageWorkflow(driver);
			hfMHomepageWorkflow.logout();
		}
	}

	/**
	 * This will execute after every test class execution.
	 * Logout from the application
	 */
	@AfterClass(alwaysRun = true)
	public void afterClass() {
	//	driver.close();
		driver.quit();
	}

	/**
	 * This will be executed after suite completed.
	 * Quit the browser
	 */
	@AfterSuite
	public void tearDown() {
		LOGGER.info("Suite completed. Closing the browser.");
		Properties prop = ConfigReader.readBaseConfig();
		//FileUtils.deleteDownloadsFolderFiles();
		driver.quit();
	}

	public void sendTestMethodStatus(ITestResult iTestResult, String status) {
		String build_number = SystemVariables.BUILD_NUMBER.toString();
		LOGGER.info(build_number);
		if(build_number!=null) {
			String tableName = "features";
			String marker = SystemVariables.FEATURE.toString();
			if(marker.equalsIgnoreCase(MarkerConstants.SANITY)) {
				tableName = "sanity";
			}
			else if( marker.equalsIgnoreCase(MarkerConstants.REGRESSION)) {
				tableName = "regression";
			}
			else {
				tableName = "features";
			}
			String className = iTestResult.getMethod().getRealClass().getName();
			InsertRecordInMySql insertRecordInMySql = new InsertRecordInMySql();
			insertRecordInMySql.insert(timestamp,build_number,iTestResult.getMethod().getMethodName(),className.substring(className.lastIndexOf('.')+1)
					,iTestResult.getMethod().getGroups()[0],status);
		}
	}

}
