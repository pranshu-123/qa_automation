package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.qa.io.ConfigReader;
import com.qa.utils.TestUtils;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	public static WebDriver driver;
	public static Properties prop;

	public static final Logger log = (Logger) LogManager.getLogger();
	
	public TestBase() {
		prop = ConfigReader.readBaseConfig();
		log.info("Read config.property File !!");
	}

	public static void initialisation() {
		String browser = prop.getProperty("browser");
		
		if (browser.equals("Chrome")) {
			log.info("Using Chrome browser");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			//driver.manage().timeouts().pageLoadTimeout(TestUtils.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(TestUtils.IMPLICIT_WAIT, TimeUnit.SECONDS);
			String url = prop.getProperty("url");
			driver.get(url);
		}
	}
}
