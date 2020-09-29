package com.qa.scripts;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Logger;
import org.openqa.selenium.WebDriver;
import com.qa.pagefactory.TopPanelComponentPageObject;
import com.qa.utils.WaitExecuter;
import com.relevantcodes.extentreports.ExtentReports;

/*
 * @author - Ojasvi Pandey
 */
/* This class i used to get the unravel build info */

public class UnravelBuildInfo {
	private static final Logger LOGGER = Logger.getLogger(UnravelBuildInfo.class.getName());

	public static List<String> getBuildInfo(WebDriver driver) {
		WaitExecuter wait = new WaitExecuter(driver);
		TopPanelComponentPageObject topPanel = new TopPanelComponentPageObject(driver);
		Login login = new Login(driver);
		LOGGER.info("Loging to app to get build info");
		login.loginToApp();
		wait.sleep(3000);

		LOGGER.info("Click on about button");
		topPanel.aboutInfo.click();
		wait.sleep(1000);

		String versionDetails = topPanel.versionInfo.getText();
		List<String> list = new ArrayList<String>();
		String[] unravelDetails = versionDetails.split("\\n");
		for (int i = 4; i < 6; i++) {
			list.add(unravelDetails[i]);
			LOGGER.info("Build info " + unravelDetails[i]);
		}
		wait.sleep(1000);
		LOGGER.info("Closing About window");
		topPanel.closeAboutWindow.click();
		wait.sleep(1000);
		LOGGER.info("Loging off the app");
		login.logout();
		wait.sleep(2000);
		return list;
	}

	public static void setBuildInfo(WebDriver driver, ExtentReports extent) {
		LinkedHashMap map = new LinkedHashMap<>();
		WaitExecuter wait = new WaitExecuter(driver);
		List<String> unravelDetails = getBuildInfo(driver);
		for (int i = 0; i < unravelDetails.size(); i++) {
			String[] splitByColon = unravelDetails.get(i).split(":");
			LOGGER.info("splitByColon " + splitByColon);
			LOGGER.info("splitByColon[0] " + splitByColon[0]);
			LOGGER.info("splitByColon[1] " + splitByColon[1]);
			map.put(splitByColon[0].trim(), splitByColon[1].trim());
		}
		extent.addSystemInfo(map);
		wait.sleep(1000);

	}

}
