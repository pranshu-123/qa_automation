package com.qa.scripts.emr.Cost;

import com.qa.pagefactory.emr.Cost.BudgetPageObject;
import com.qa.scripts.DatePicker;
import com.qa.utils.WaitExecuter;
import com.qa.utils.actions.UserActions;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import java.util.logging.Logger;

public class Budget {
    private static final Logger LOGGER = Logger.getLogger(Budget.class.getName());

    private final WaitExecuter waitExecuter;
    private final WebDriver driver;
    private final BudgetPageObject budgetPageObject;
    private final DatePicker datePicker;
    private final UserActions userActions;
    public Budget(WebDriver driver) {
        waitExecuter = new WaitExecuter(driver);
        this.driver = driver;
        budgetPageObject=new BudgetPageObject(driver);
        datePicker = new DatePicker(driver);
        userActions=new UserActions(driver);
    }


    public void validateBudgetPage(){
        Assert.assertTrue(budgetPageObject.CreateBudgetButton.isDisplayed());
        Assert.assertTrue(budgetPageObject.CreateBudgetButton.isEnabled());
        LOGGER.info("Validated Create Budget Button");

        Assert.assertTrue(budgetPageObject.ActiveLink.isDisplayed());
        Assert.assertTrue(budgetPageObject.ActiveLink.isEnabled());
        LOGGER.info("Validated Active Link Button");

        Assert.assertTrue(budgetPageObject.ExpiredLink.isDisplayed());
        Assert.assertTrue(budgetPageObject.ExpiredLink.isEnabled());
        LOGGER.info("Validated Expired Link Button");
    }

    public void validateBudgetPage1(){
        Assert.assertTrue(budgetPageObject.CreateBudgetButton.isDisplayed());
        Assert.assertTrue(budgetPageObject.CreateBudgetButton.isEnabled());

        Assert.assertTrue(budgetPageObject.ActiveLink.isDisplayed());
        Assert.assertTrue(budgetPageObject.ActiveLink.isEnabled());

        Assert.assertTrue(budgetPageObject.ExpiredLink.isDisplayed());
        Assert.assertTrue(budgetPageObject.ExpiredLink.isEnabled());
    }

}
