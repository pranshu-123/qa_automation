package com.qa.workflows;

import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;

import com.qa.constants.ConfigConstants;
import com.qa.io.ConfigReader;
import com.qa.pagefactory.NFMHomepagePageObject;
import com.qa.pagefactory.NFMLoginPageObject;
import com.qa.utils.TestUtils;
import com.qa.utils.WaitExecuter;

public class NFMLoginWorkflow {

	private static final Logger LOGGER = Logger.getLogger(NFMLoginWorkflow.class.getName());

	private final WaitExecuter waitExecuter;
	private final WebDriver driver;
	private final NFMLoginPageObject nfmLoginPageObject;

	/**
	 * Constructor to initialize wait, driver and necessary objects
	 *
	 * @param driver - WebDriver instance
	 */
	public NFMLoginWorkflow(WebDriver driver) {
		this.driver = driver;
		waitExecuter = new WaitExecuter(driver);
		nfmLoginPageObject = new NFMLoginPageObject(driver);
	}

	public void loginToOM() {
		Properties prop = ConfigReader.readBaseConfig();
		String uname = prop.getProperty(ConfigConstants.IrisConfig.USERNAME);
		String password = prop.getProperty(ConfigConstants.IrisConfig.PASSWORD);
		nfmLoginPageObject.username.sendKeys(uname);
		nfmLoginPageObject.password.sendKeys(password);
		nfmLoginPageObject.login.click();
	}
}
