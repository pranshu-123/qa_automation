package com.qa.base;

import com.qa.io.ConfigReader;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;
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
		String executeOn = prop.getProperty("execution");
		if(executeOn.contains("remote")) {
			if (browser.equalsIgnoreCase("chrome")) {
				log.info("Using Chrome browser");
				try {
					driver = new RemoteWebDriver(new URL("http://localhost:4444"),getChromeOptionWithNetworkEnable());
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			}
		}
		else {
			driver = new ChromeDriver(getChromeOptionWithNetworkEnable());
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		String url = prop.getProperty("url");
		driver.get(url);

		return driver;
	}

	/**
	 *
	 * @return Chrome Options with customized configurations
	 */
	private ChromeOptions getChromeOptionWithNetworkEnable() {
		String executeOn = prop.getProperty("execution");
		LoggingPreferences logPrefs = new LoggingPreferences();
		logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
		if(executeOn.equalsIgnoreCase("remote")) {
			System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
		}
		else {
			WebDriverManager.chromedriver().setup();
		}
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--no-sandbox", "--disable-dev-shm-usage");
		chromeOptions.setExperimentalOption("useAutomationExtension", false);
		return chromeOptions;
	}
}

