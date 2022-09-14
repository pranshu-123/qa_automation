package com.qa.scripts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import com.qa.pagefactory.emr.EmrDateTimePicker;
import com.qa.utils.WaitExecuter;

public class EmrDatePicker {

	private final WebDriver driver;
	public EmrDateTimePicker emrDateTimePicker;
	public WaitExecuter waitExecuter;

	/**
	 * @param driver - WebDriver instance
	 *               Constructor method initialize driver, datepicker object
	 */
	public EmrDatePicker(WebDriver driver) {
		this.driver = driver;
		emrDateTimePicker = new EmrDateTimePicker(driver);
		waitExecuter = new WaitExecuter(driver);
	}

	public void selectToday() {
		waitExecuter.waitUntilElementPresent(emrDateTimePicker.today);
		emrDateTimePicker.today.click();
	}

	public void selectLast14Days() {
		waitExecuter.waitUntilElementPresent(emrDateTimePicker.last14Days);
		emrDateTimePicker.last14Days.click();
	}

	public void selectLast7Days() {
		waitExecuter.waitUntilElementPresent(emrDateTimePicker.last7Days);
		emrDateTimePicker.last7Days.click();
	}

	public void selectLast1M() {
		waitExecuter.waitUntilElementPresent(emrDateTimePicker.last1M);
		emrDateTimePicker.last1M.click();
	}

	public void selectLast3M() {
		waitExecuter.waitUntilElementPresent(emrDateTimePicker.last3M);
		emrDateTimePicker.last3M.click();
	}

	public void selectLast6M() {
		waitExecuter.waitUntilElementPresent(emrDateTimePicker.last6M);
		emrDateTimePicker.last6M.click();
	}

	public void selectLast1Y() {
		waitExecuter.waitUntilElementPresent(emrDateTimePicker.last1Y);
		emrDateTimePicker.last1Y.click();
	}


	public void setCurrentAndPastDate() {
		waitExecuter.sleep(2000);
		emrDateTimePicker.customRange.click();
		Select date = new Select(emrDateTimePicker.customRangeDateMonth.get(0));
		waitExecuter.sleep(2000);
		emrDateTimePicker.applyBtn.click();
	}
	
	public String retrievePreviousDate() {
		return emrDateTimePicker.previousDate.getText();
	}
}
