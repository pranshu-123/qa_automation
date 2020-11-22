package com.qa.scripts.appdetails;

import com.qa.enums.AppDetailsApplicationType;
import com.qa.enums.ApplicationEnum;
import com.qa.enums.UserAction;
import com.qa.pagefactory.appsDetailsPage.AppDetailsPageObject;
import com.qa.utils.ActionPerformer;
import com.qa.utils.actions.UserActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author Ankur Jaiswal
 */
public class AppDetailsPage {
    private Logger logger = LoggerFactory.getLogger(AppDetailsPage.class);
    private WebDriver driver;
    private AppDetailsPageObject appDetailsPageObject;
    private UserActions userActions;

    /**
     * Constructor method to initialize object fields
     * @param driver - WebDriver instance
     */
    public AppDetailsPage(WebDriver driver) {
        this.driver = driver;
        appDetailsPageObject = new AppDetailsPageObject(driver);
        userActions = new UserActions(driver);
    }

    /**
     * Select only application in left panel of application page.
     * Click on only button of the application passed as parameter
     * @param applicationType - Application Type which we want to select
     */
    public void selectOnlyApplication(AppDetailsApplicationType applicationType) {
        for (int i=0; i < appDetailsPageObject.applicationTypeLabels.size(); i++) {
            if (appDetailsPageObject.applicationTypeLabels.get(i).getText().contains(applicationType.getValue())) {
                ActionPerformer actionPerformer = new ActionPerformer(driver);
                actionPerformer.moveToTheElement(appDetailsPageObject.applicationTypeLabels.get(i));
                userActions.performActionWithPolling(appDetailsPageObject.applicationTypeShowOnly.get(i),UserAction.CLICK);
                break;
            }
        }
    }

    /**
     * Check whether other application type is displayed on app
     * Returns true if other application type is displayed on table
     * @param appDetailsApplicationType
     * @return
     */
    public Boolean IsOtherApplicationTypesInTable(AppDetailsApplicationType appDetailsApplicationType) {
        List<WebElement> tableRows = appDetailsPageObject.allAppsTable.findElements(By.xpath("//tr"));
        Boolean isOtherApplicationPresent = false;
        for (WebElement row: tableRows) {
            if(!row.findElements(By.xpath("//td")).get(ApplicationEnum.TYPE.getIndex()).getText()
                .equalsIgnoreCase(appDetailsApplicationType.getValue())) {
                isOtherApplicationPresent = true;
            }
        }
        return isOtherApplicationPresent;
    }

    /**
     * Sort the table column of jobs application page
     * @param applicationEnum - Column to sort
     */
    public void sortColumnAllAppsTable(ApplicationEnum applicationEnum) {
        List<WebElement> tableHeadings = appDetailsPageObject.allAppsTable.findElements(By.xpath("//thead"));
        Boolean isOtherApplicationPresent = false;
        for (WebElement tableHeading: tableHeadings) {
            userActions.performActionWithPolling(tableHeading.findElements(By.xpath("//th")).get(applicationEnum.getIndex()),
                UserAction.CLICK);
        }
    }
}
