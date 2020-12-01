package com.qa.testcases.cluster.topx;

import com.qa.annotations.Marker;
import com.qa.base.BaseClass;
import com.qa.enums.UserAction;
import com.qa.pagefactory.TopPanelPageObject;
import com.qa.scripts.clusters.TopX;
import com.qa.utils.LoggingUtils;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Ankur Jaiswal
 */
@Marker.All
@Marker.TopX
public class TC_CTX_29 extends BaseClass {

    private final LoggingUtils LOGGER = new LoggingUtils(TC_CTX_29.class);

    /**
     * Verify Tag filter in new report page
     */
    @Test
    public void verifyTagsFilter() {
        test = extent.startTest("TC_CTX_29.verifyTagsFilterk", "Verify Tags in new report page");
        test.assignCategory(" Cluster - Top X");
        LOGGER.info("Go to TopX page.", test);
        WaitExecuter waitExecuter = new WaitExecuter(driver);
        TopPanelPageObject topPanelPageObject = new TopPanelPageObject(driver);
        UserActions userActions = new UserActions(driver);
        userActions.performActionWithPolling(topPanelPageObject.topXTab, UserAction.CLICK);
        TopX topX = new TopX(driver);
        topX.closeConfirmationMessageNotification();
        topX.clickOnRunButton();
        LOGGER.info("Click on run button", test);

        for (int i=0; i<topX.getTagsCheckbox().size(); i++) {
            LOGGER.info("Click on tags of category: ", test);
            topX.selectTagsCheckbox(topX.getTagsCheckbox().get(i));
            LOGGER.info("Click on tags textbox", null);
            userActions.performActionWithPolling(topX.getLastInputTextboxField(), UserAction.CLICK);
            for (int filterDropDown=0; filterDropDown<topX.getFilterDropDowns().size(); filterDropDown++) {
                waitExecuter.sleep(2000);
                String tagFilter = topX.getFilterDropDowns().get(filterDropDown).getText();
                LOGGER.info("Click on queue filter: " + tagFilter, test);
                userActions.performActionWithPolling(topX.getFilterDropDowns().get(filterDropDown), UserAction.CLICK);
                topX.clickOnModalRunButton();
                try {
                    waitExecuter.waitUntilTextToBeInWebElement(topX.getConfirmationMessage(),
                            "Top X Report completed successfully.");
                } catch (TimeoutException te) {
                    Assert.assertTrue(false, "TopX Report is not completed");
                }
                topX.closeConfirmationMessageNotification();
                waitExecuter.sleep(2000);
                for (WebElement row : topX.getInputParamsRowList()) {
                    if (row.findElement(By.xpath("td[1]")).getText().equalsIgnoreCase("Tags")) {
                        Assert.assertTrue(row.findElement(By.xpath("td[2]")).getText().contains(tagFilter),
                                "Applied filter is displayed for tag: " + tagFilter);
                        test.log(LogStatus.PASS, "Correct filter is displayed for queue.");
                    }
                }
                topX.clickOnRunButton();
                userActions.performActionWithPolling(topX.getLastInputTextboxField(), UserAction.CLICK);
            }
        }
    }
}
