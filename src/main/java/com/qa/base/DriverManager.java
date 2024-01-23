package com.qa.base;


import com.qa.constants.SystemVariables;
import com.qa.io.ConfigReader;
import com.qa.utils.FileUtils;
import com.qa.utils.Log;


import io.github.bonigarcia.wdm.WebDriverManager;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;


public class DriverManager {


	WebDriver driver;
	static Properties prop = ConfigReader.readBaseConfig();
	static String executeOn = prop.getProperty("execution");


	public static final Logger log = (Logger) LogManager.getLogger();


	public WebDriver getDriver(String browser) {
		if (driver == null) {
			Log.info("Creating new driver **********");
			driver = initializeDriver(browser);
		}
		return driver;
	}


	public WebDriver initializeDriver(String browser) {
		String build_number = SystemVariables.BUILD_NUMBER.toString();
		log.info("Build Number: " + build_number);
		if(build_number!=null) {
			if (browser.equalsIgnoreCase("chrome")) {
				log.info("Using Chrome browser");
				try {
					driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),getChromeOptionWithNetworkEnable());
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			}
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			String url = SystemVariables.ENVIRONMENT_URL.toString();
			driver.get(url);
		}
		else {
			driver = new ChromeDriver(getChromeOptionWithNetworkEnable());
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			String url = prop.getProperty("url");
			driver.get(url);
		}
		return driver;
	}


	/**
	 *
	 * @return Chrome Options with customized configurations
	 */
	private ChromeOptions getChromeOptionWithNetworkEnable() {
		LoggingPreferences logPrefs = new LoggingPreferences();
		logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
		String build_number = SystemVariables.BUILD_NUMBER.toString();
		File folderUUID = FileUtils.createDownloadsFolder();
		HashMap<String, Object> chromePref = new HashMap<String, Object>();
		chromePref.put("credentials_enable_service", false);
		chromePref.put("profile.password_manager_enabled", false);
		chromePref.put("profile.default_content_settings.popups", 0);
		chromePref.put("download.default_directory", folderUUID.getAbsolutePath());
		if(build_number!=null) {
			System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
		}
		else {

			WebDriverManager.chromedriver().setup();
		}
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--no-sandbox", "--disable-dev-shm-usage");
		chromeOptions.addArguments("--remote-allow-origins=*");
		chromeOptions.setExperimentalOption("useAutomationExtension", false);
		chromeOptions.setExperimentalOption("prefs", chromePref);
		return chromeOptions;
	}
}
