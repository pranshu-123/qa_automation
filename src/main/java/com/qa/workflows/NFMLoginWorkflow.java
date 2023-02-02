package com.qa.workflows;

import java.util.Properties;
import java.util.logging.Logger;
import org.openqa.selenium.WebDriver;
import com.qa.constants.ConfigConstants;
import com.qa.constants.SystemVariables;
import com.qa.io.ConfigReader;
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
		String build_number = SystemVariables.BUILD_NUMBER.toString();
		String uname;
		String password;
		if(build_number==null) {
			uname = prop.getProperty(ConfigConstants.IrisConfig.USERNAME);
			password = prop.getProperty(ConfigConstants.IrisConfig.PASSWORD);
		}
		else {
			uname = SystemVariables.USERNAME.toString();
			password = SystemVariables.PASSWORD.toString();
		}
		nfmLoginPageObject.username.sendKeys(uname);
		nfmLoginPageObject.password.sendKeys(password);
		nfmLoginPageObject.login.click();
		if(TestUtils.isElementDisplayed(nfmLoginPageObject.endSession)) {
			nfmLoginPageObject.endSession.click();
		}
	}
}
