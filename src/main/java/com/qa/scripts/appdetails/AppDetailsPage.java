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

import java.util.ArrayList;
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
        List<WebElement> tableRows = getAppsDetailsAsPage();
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
     * Get table rows displayed on app details page
     */
    public List<WebElement> getAppsDetailsAsPage() {
        return appDetailsPageObject.allAppsTable.findElements(By.xpath("//tr"));
    }

    /**
     * Sort the table column of jobs application page
     * @param applicationEnum - Column to sort
     */
    public void sortColumnAllAppsTable(ApplicationEnum applicationEnum) {
        List<WebElement> tableHeadings = appDetailsPageObject.allAppsTable.findElements(By.xpath("//thead"));
        for (WebElement tableHeading: tableHeadings) {
            userActions.performActionWithPolling(tableHeading.findElements(By.xpath("//th")).get(applicationEnum.getIndex()),
                UserAction.CLICK);
        }
    }

    /**
     * Get status of application from job details
     */
    public String getJobStatus() {
        return appDetailsPageObject.jobStatus.getText();
    }

    /**
     * Get start date and time of application from job details
     */
    public String getJobStartTime() {
        return appDetailsPageObject.jobStartTime.getText();
    }

    /**
     * Get end date and time of application from job details
     */
    public String getJobEndTime() {
        return appDetailsPageObject.jobEndTime.getText();
    }

    /**
     * Get job duration of application from job details
     */
    public String getJobDuration() {
        return appDetailsPageObject.jobDuration.getText();
    }

    /**
     * Get uuid of application from job details
     */
    public String getApplicationUUID() {
        return appDetailsPageObject.jobUUID.getText();
    }

    /**
     * Get type of application from job details
     */
    public String getApplicationType() {
        return appDetailsPageObject.appTypeAppDetails.getText();
    }

    /**
     * Get disk io of application from job details
     */
    public String getApplicationDataIO() {
        return appDetailsPageObject.jobReadWriteData.getText();
    }

    /**
     * Get fragment rows displayed on impala application from job details
     */
    public List<WebElement> getImpalaFragmentRows() {
        return appDetailsPageObject.impalaFragmentRows;
    }

    /**
     * Get query plan components displayed on page of impala app details from job details
     */
    public List<WebElement> getImpalaQueryPlanDetails() {
        return appDetailsPageObject.impalaQueryPlanComponents;
    }

    /**
     * Get instance view displayed on page of impala app details from job details
     */
    public WebElement getImpalaInstanceView() {
        return appDetailsPageObject.impalaIFragmentnstanceView;
    }

    /**
     * Get instance view fragment rows displayed on impala application from job details
     */
    public List<WebElement> getImpalaInstanceViewFragmentRows() {
        return appDetailsPageObject.impalaFragmentInstanceViewsRows;
    }

    /**
     * Click on operators tab on impala app details page
     */
    public void clickOnImpalaOperatorsTab() {
        userActions.performActionWithPolling(appDetailsPageObject.operatorsTabImpala, UserAction.CLICK);
    }

    /**
     * Click on gantt tab on impala app details page
     */
    public void clickOnImpalaGanttChartTab() {
        userActions.performActionWithPolling(appDetailsPageObject.ganttChartTabImpala, UserAction.CLICK);
    }

    /**
     * Get gantt chart ids in numeric
     */
    public List<Integer> getGanttChartIdsInNumeric() {
        List<Integer> fragmentIds = new ArrayList<>();
        for (WebElement fragmentId : appDetailsPageObject.ganttChartIds) {
            Integer fragId = Integer.valueOf(fragmentId.getText().replaceAll("[^0-9]+", ""));
            fragmentIds.add(fragId);
        }
        return fragmentIds;
    }

    /**
     * Close the modal
     */
    public void closeModalbutton() {
        userActions.performActionWithPolling(appDetailsPageObject.closeModalbutton,UserAction.CLICK);
    }
}
