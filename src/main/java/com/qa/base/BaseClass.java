package com.qa.base;

import com.qa.constants.DirectoryConstants;
import com.qa.constants.FileConstants;
import com.qa.io.ConfigReader;
import com.qa.pagefactory.clusters.DataProviderClass;
import com.qa.scripts.Login;
import com.qa.scripts.UnravelBuildInfo;
import com.qa.utils.FileUtils;
import com.qa.utils.Log;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.io.File;
import java.lang.reflect.Method;
import java.util.Properties;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

public class BaseClass {
	public static WebDriver driver;
	public static ExtentReports extent;
	public static ExtentTest test;

	@BeforeSuite
	public void setup() {
		DriverManager driverManager = new DriverManager();
		Properties prop = ConfigReader.readBaseConfig();
		String browser = prop.getProperty("browser");
		// driver = driverManager.getDriver("chrome"); //Read chrome from property file.
		driver = driverManager.getDriver(browser);
		FileUtils.createDirectory(DirectoryConstants.getExtentResultDir());
		FileUtils.createDirectory(DirectoryConstants.getScreenshotDir());
		extent = new ExtentReports(FileConstants.getExtentReportFile(), true);
		extent.loadConfig(new File(DirectoryConstants.getConfigDir() + "extent_config.xml"));
		UnravelBuildInfo.setBuildInfo(driver, extent);
	}

	@BeforeClass
	public void beforeClass() {
		Login login = new Login(driver);
		login.loginToApp();
		
	}

	@BeforeMethod(alwaysRun = true)
	public void setupBeforeMethod(Method method) {
		Log.startTestCase(method.getName());
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult result, Method method) {
		try {
			/*Mark TC as FAIL if its an assertion error, else FATAL */
			if (result.getStatus() == ITestResult.FAILURE) {
				if (result.getThrowable() instanceof AssertionError)
					test.log(LogStatus.FAIL,"<b style='color:yellow'>" + result.getThrowable().getMessage());				
				else
					test.log(LogStatus.FATAL, result.getThrowable().getMessage());
			}
			Log.endTestCase(method.getName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Code to add test result in report
		extent.endTest(test);
		extent.flush();
	}

	@AfterClass()
	public void afterClass() {
		Login login = new Login(driver);
		login.logout();
		FileUtils.deleteDownloadFolderUUIDFiles();
	}

	@AfterSuite
	public void tearDown() {
		driver.quit();
	}

	/*
	 * List of available clusterid's Cluster1 [296b...2ac9] Cluster1 [f5a6...2ac9]
	 * HDP7f17c HDPabac5 default
	 */

	@DataProvider(name = "clusterid-data-provider")
	public Object[][] getClusterIds(Method method) {
		// TBD Read the cluster drop down box and store
		System.setProperty("isMultiCluster", "true");
		if (System.getProperty("isMultiCluster").trim().toLowerCase().equals("true")) {
			return DataProviderClass.getClusterIdsForClusterType(method);
		} else {
			return new Object[][] { DataProviderClass.getClusterIdsForClusterType(method)[0] };
		}
	}
}
