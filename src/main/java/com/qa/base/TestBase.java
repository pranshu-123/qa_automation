package com.qa.base;

import java.time.Duration;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.qa.io.ConfigReader;
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
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			String url = prop.getProperty("url");
			driver.get(url);
		}
	}
}
