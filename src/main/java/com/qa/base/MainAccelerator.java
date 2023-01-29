package com.qa.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.qa.connections.db.InsertRecordInMySql;
import com.qa.constants.ConfigConstants;
import com.qa.constants.DirectoryConstants;
import com.qa.constants.FileConstants;
import com.qa.constants.MarkerConstants;
import com.qa.constants.SystemVariables;
import com.qa.io.ConfigReader;
import com.qa.utils.*;
import com.qa.workflows.NFMHomepageWorkflow;
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

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

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
	private static ExtentSparkReporter htmlReporter;

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
		htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") +"/test-output/extentTestReport.html");

		//initialize ExtentReports and attach the HtmlReporter
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		//To add system or environment info by using the setSystemInfo method.
		extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		extent.setSystemInfo("Operating System Version", System.getProperty("os.version"));
		extent.setSystemInfo("Browser", "Chrome");
		extent.setSystemInfo("User", System.getProperty("user.name"));

		//configuration items to change the look and feel
		//add content, manage tests etc
		htmlReporter.config().setDocumentTitle("Test Execution Report");
		htmlReporter.config().setReportName("Test Report");
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");

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
					test.log(Status.FAIL, "<b style='color:yellow'>" + result.getThrowable().getMessage());
					LOGGER.info(method.getName() + " is failed");
				} else {
					test.log(Status.WARNING, result.getThrowable().getMessage());
					LOGGER.info(method.getName() + " is failed due to code issue");
				}
				String screenshotImg = ScreenshotHelper.takeScreenshotOfPage(driver);
				test.addScreenCaptureFromPath(screenshotImg);
			}
			Log.endTestCase(method.getDeclaringClass().getName() + " - " + method.getName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			// Code to add test result in report
			LOGGER.info("Adding test case to report");
			//extent.endTest(test);
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

		// Create object of Property file
		Properties props = new Properties();
		// this will set host of server- you can change based on your requirement 
		props.put("mail.smtp.host", "smtp.gmail.com");
		// set the port of socket factory 
		props.put("mail.smtp.socketFactory.port", "465");
		// set socket factory
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		// set the authentication to true
		props.put("mail.smtp.auth", "true");
		// set the port of SMTP server
		props.put("mail.smtp.port", "465");
		// This will handle the complete authentication
		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("iris.automation.report@gmail.com", "bodrxbyloywqpwny");

			}

		});

		try {

			// Create object of MimeMessage class
			Message message = new MimeMessage(session);

			// Set the from address
			message.setFrom(new InternetAddress("iris.automation.report@gmail.com"));

			// Set the recipient address
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("pranshu@irislogic.com"));

			// Add the subject link
			message.setSubject("Iris UI Automation Report");

			// Create object to add multimedia type content
			BodyPart messageBodyPart1 = new MimeBodyPart();

			//			messageBodyPart1.setContent("<h3 style = \"text-align:center\">TEST AUTOMATION REPORT FOR BUILD: </br></hr>"
			//					+ "<p><b>Execution Summary Report is attached with the mail.</b> Please find the attached suite report. </p>", "text/html");

			// Set the body of email
			messageBodyPart1.setText("TEST AUTOMATION REPORT FOR BUILD: ");

			// Create another object to add another content
			MimeBodyPart messageBodyPart2 = new MimeBodyPart();

			// Mention the file which you want to send
			String filename = System.getProperty("user.dir") +"/test-output/extentTestReport.html";

			// Create data source and pass the filename
			DataSource source = new FileDataSource(filename);

			// set the handler
			messageBodyPart2.setDataHandler(new DataHandler(source));

			// set the file
			messageBodyPart2.setFileName(filename);

			// Create object of MimeMultipart class
			Multipart multipart = new MimeMultipart();

			// add body part 1
			multipart.addBodyPart(messageBodyPart2);

			// add body part 2
			multipart.addBodyPart(messageBodyPart1);

			// set the content
			message.setContent(multipart);
			// finally send the email
			Transport.send(message);

			System.out.println("=====Email Sent=====");

		} catch (Exception e) {
			e.printStackTrace();
		}

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
