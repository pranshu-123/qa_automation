package com.qa.utils.actions;

import com.qa.enums.UserAction;
import com.qa.utils.MouseActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;

/**
 * @author Ankur Jaiswal
 * This class contain methods for user action performed on web page.
 */
public class UserActions {
    private Clock clock = Clock.systemDefaultZone();
    public final Integer MAX_SLEEP_DURATION = 60000;
    public final Integer SLEEP_INTERVAL = 500;
    public final Duration MAX_POLLING_TIME = Duration.ofMillis(MAX_SLEEP_DURATION);
    private WebDriver driver;

    /**
     * Constructor method to intialize the components
     */
    public UserActions(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Perform action like user click and pass input on page component while polling
     * intervals. If any exception occurs on component action then it will sleep for
     * defined interval and retry same.
     * @param element - Web Element to perform action
     * @param action - Action type to be performed
     * @param keys - If any input to be passed on component. mainly used for send_keys.
     */
    public void performActionWithPolling(WebElement element,
                                         UserAction action, String... keys) {
        Instant end = clock.instant().plus(MAX_POLLING_TIME);
        while (true) {
            try {
                switch (action) {
                    case CLICK:
                        MouseActions.clickOnElement(driver,element);
                        break;
                    case SEND_KEYS:
                        element.clear();
                        element.sendKeys(keys);
                        break;
                }
                return;
            } catch (Throwable throwable) {
                throwable.printStackTrace();
                if (end.isBefore(clock.instant())) {
                    Assert.assertTrue(false, "Timeout Exception: "
                        + throwable.getMessage());
                }
                try {
                    Thread.sleep(SLEEP_INTERVAL);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new WebDriverException(e);
                }
            }
        }
    }
}
