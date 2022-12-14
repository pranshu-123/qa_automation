package com.qa.base;

import com.qa.constants.ConfigConstants;
import com.qa.io.ConfigReader;
import com.qa.utils.FileUtils;
import com.qa.utils.TestUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;

import java.io.File;
import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class DriverManager {

	WebDriver driver;
	Properties prop = ConfigReader.readBaseConfig();

	public static final Logger log = (Logger) LogManager.getLogger();

	public WebDriver getDriver(String browser) {
		if (driver == null) {
			driver = initializeDriver(browser);
		}
		return driver;
	}

	public WebDriver initializeDriver(String browser) {
		if (browser.equalsIgnoreCase("chrome")) {
			log.info("Using Chrome browser");
			driver = new ChromeDriver(getChromeOptionWithNetworkEnable());
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			String url = prop.getProperty("url");
			driver.get(url);
		}else {
			System.out.println("Not Support for "+browser+ " browser.");
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
		//WebDriverManager.chromedriver().setup();
		//File folderUUID = FileUtils.createDownloadsFolder();
		System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
		ChromeOptions options = new ChromeOptions();
		// options.addArguments("headless");
	        options.addArguments("disable-gpu");
//		HashMap<String, Object> chromePref = new HashMap<String, Object>();
//		chromePref.put("credentials_enable_service", false);
//		chromePref.put("profile.password_manager_enabled", false);
//		chromePref.put("profile.default_content_settings.popups", 0);
//		//chromePref.put("download.default_directory", folderUUID.getAbsolutePath());
//		ChromeOptions options = new ChromeOptions();
//		options.setCapability("goog:loggingPrefs", logPrefs);
//		if (System.getProperty(ConfigConstants.SystemConfig.HEADLESS).equals("true")) {
//			options.addArguments("--headless","window-size=1920,1080");
//			options.addArguments("--no-sandbox","--disable-dev-shm-usage");
//		}
//		options.setExperimentalOption("useAutomationExtension", false);
//		options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
//		options.setExperimentalOption("prefs", chromePref);
//		options.setCapability(ChromeOptions.CAPABILITY,options);
		return options;
	}

}

