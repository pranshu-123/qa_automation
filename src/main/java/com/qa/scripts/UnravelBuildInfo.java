package com.qa.scripts;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.qa.enums.UserAction;
import com.qa.utils.actions.UserActions;
import org.openqa.selenium.WebDriver;
import com.qa.pagefactory.SubTopPanelModulePageObject;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.ExtentReports;
import org.openqa.selenium.interactions.Actions;

/*
 * @author - Ojasvi Pandey
 */
/* This class i used to get the unravel build info */

public class UnravelBuildInfo {
	private static final Logger LOGGER = Logger.getLogger(UnravelBuildInfo.class.getName());
	private UserActions actions;
	private WebDriver driver;
	private LinkedHashMap buildInfo;

	public UnravelBuildInfo(WebDriver driver) {
		this.driver = driver;
		actions = new UserActions(driver);
	}

	/* Method helps in getting unravel about info from UI  */
	public List<String> getBuildInfo() {
		WaitExecuter wait = new WaitExecuter(driver);
		SubTopPanelModulePageObject topPanel = new SubTopPanelModulePageObject(driver);
		Login login = new Login(driver);
		Actions builder = new Actions(driver);
		LOGGER.info("Loging to app to get build info");
		login.loginToApp();
		LOGGER.info("Click on about button");
		wait.sleep(4000);
		/*builder.moveToElement(topPanel.supportList).build().perform();*/
		actions.performActionWithPolling(topPanel.supportList, UserAction.CLICK);
		actions.performActionWithPolling(topPanel.aboutInfo, UserAction.CLICK);
		String versionDetails = topPanel.versionInfo.getText();
		List<String> list = new ArrayList<String>();
		String[] unravelDetails = versionDetails.split("\\n");
		for (int i = 4; i < 6; i++) {
			list.add(unravelDetails[i]);
			LOGGER.info("Build info " + unravelDetails[i]);
		}
		LOGGER.info("Closing About window");
		actions.performActionWithPolling(topPanel.closeAboutWindow, UserAction.CLICK);
		LOGGER.info("Loging off the app");
		login.logout();
		return list;
	}

	/* Method helps in setting unravel about info in extent report */
	public void setBuildInfo(ExtentReports extent) {
		buildInfo = new LinkedHashMap<>();
		WaitExecuter wait = new WaitExecuter(driver);
		List<String> unravelDetails = getBuildInfo();
		for (int i = 0; i < unravelDetails.size(); i++) {
			String[] unravelBuildDetails = unravelDetails.get(i).split(":");
			LOGGER.info("unravelBuildDetails[0] " + unravelBuildDetails[0]);
			LOGGER.info("unravelBuildDetails[1] " + unravelBuildDetails[1]);
			buildInfo.put(unravelBuildDetails[0].trim(), unravelBuildDetails[1].trim());
		}
		extent.addSystemInfo(buildInfo);
		wait.sleep(1000);
	}

	public Map getUnravelBuildInfo() {
		return buildInfo;
	}
}
