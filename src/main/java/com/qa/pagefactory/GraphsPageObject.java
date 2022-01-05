package com.qa.pagefactory;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import bsh.classpath.BshClassPath.GeneratedClassSource;

/**
 * @author Ojasvi Pandey All graphs related webelements of unravel ui is present
 *         in this class. Wherever you need to access these page object create
 *         an instance of this class and access the members with that object.
 */

public class GraphsPageObject {

	@FindBy(xpath ="//div[contains(@class,'footer')]//label")
	public List<WebElement> listOfAppNameFromFooter;

	@FindBy(xpath ="//div[contains(@class,'footer')]//label/span/span[@class='checkmark']")
	public List<WebElement> listOfRGBFromFooter;

	@FindBy(xpath = "//div/h4[text()='By Type']//following::div[contains(@class,'content')]/div/div")
	public WebElement parentTagOfByTypeHexcode;

	public By childTagsOfByTypeHexcode = By
			.xpath(".//*[local-name() = 'g']/*[local-name() = 'g']/*[local-name() = 'path']");

	@FindBy(xpath = "//div[contains(@id,'JobsFinishedByStatus')] //*[name()='svg']//*[local-name()='g']//*[local-name()='g']//*[local-name()='path'][2]")
	public List<WebElement> byStatusHexcode;

	@FindBy(xpath = "//div[contains(@id,'JobsByInefficiency')] //*[name()='svg']//*[local-name()='g']//*[local-name()='g']//*[local-name()='path'][2]")
	public List<WebElement> byEventHexcode;

	@FindBy(xpath = "//div[contains(@id,'JobsFinishedByStatus')]//label/input//preceding-sibling::span")
	public List<WebElement> byStatusFooterNames;

	@FindBy(xpath = "//div[contains(@id,'JobsByInefficiency')]//label/input//preceding-sibling::span")
	public List<WebElement> evnetsFooterNames;

	@FindBy(xpath = "//div[contains(@id,'JobsByInefficiency')]//label/input//following-sibling::span")
	public List<WebElement> eventsRGBColor;

	@FindBy(xpath = "//div[contains(@id,'JobsFinishedByStatus')]//label/input//following-sibling::span")
	public List<WebElement> statusRGBColor;

	/**
	 * @param driver
	 *            The driver that will be used to look up the elements
	 */
	public GraphsPageObject(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	//Get child elements with reference of parent element
	public List<WebElement> getChildElement(WebElement parentElement, By childElement) {
		return parentElement.findElements(childElement);
	}

}
