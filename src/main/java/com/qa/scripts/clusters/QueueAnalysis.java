package com.qa.scripts.clusters;

import org.openqa.selenium.WebDriver;

import com.qa.pagefactory.CommonPageObject;
import com.qa.pagefactory.clusters.QueueAnalysisPageObject;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.MouseActions;
import com.qa.utils.WaitExecuter;

public class QueueAnalysis {
	private WebDriver driver;
	private WaitExecuter waitExecuter;
	private QueueAnalysisPageObject queueAnalysisPageObject;
	private CommonPageObject commonPageObject;

	public QueueAnalysis(WebDriver driver) {
		this.driver = driver;
		waitExecuter = new WaitExecuter(driver);
		queueAnalysisPageObject = new QueueAnalysisPageObject(driver);
	}

	public void closeConfirmationMessageNotification() {
		if (queueAnalysisPageObject.confirmationMessageElementClose.size() > 0) {
			waitExecuter.waitUntilElementClickable(queueAnalysisPageObject.confirmationMessageElementClose.get(0));
			JavaScriptExecuter.clickOnElement(driver, queueAnalysisPageObject.confirmationMessageElementClose.get(0));
		}
	}
	
	  //click on cluster drop down
	  public void selectMultiClusterId(String clusterId){
	    waitExecuter.sleep(2000);
	    commonPageObject = new CommonPageObject(driver);
	    MouseActions.clickOnElement(driver, commonPageObject.clusterDropdown);
	    waitExecuter.sleep(2000);
	    System.out.println("Size of cluster in dropdown: "+commonPageObject.clustersList.size());

	    waitExecuter.waitUntilElementPresent(commonPageObject.clusterSearchBox);
	    commonPageObject.clusterSearchBox.sendKeys(clusterId);
	    commonPageObject.clusterSearchFirstField.click();
	    waitExecuter.sleep(2000);
	  }

	  //Click on Run button of Report
	public void clickRunButton(){
		if(queueAnalysisPageObject.runButton.isDisplayed()) {

		}
	}

}
